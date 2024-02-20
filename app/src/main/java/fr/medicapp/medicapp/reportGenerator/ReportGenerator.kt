package fr.medicapp.medicapp.reportGenerator

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.pdf.PdfDocument
import android.graphics.pdf.PdfDocument.PageInfo
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.content.FileProvider
import fr.medicapp.medicapp.database.ObjectBox
import fr.medicapp.medicapp.database.entity.PrescriptionEntity
import java.io.File
import java.io.FileOutputStream
import java.nio.file.Files
import java.time.LocalDate
import java.util.Locale

class NoPrescriptionException : Exception("Vous n'avez pas de prescription pour le moment.")
class ReportGenerator(private val ctx: Context) {

    private class Fonts {
        companion object {
            val classicFont = Paint().apply {
                textSize = 4F
                textAlign = Paint.Align.LEFT
                letterSpacing = 0.09F
                hinting = Paint.HINTING_OFF
            }

            val legendFont = Paint().apply {
                textSize = 3.5F
                textAlign = Paint.Align.RIGHT
                textSkewX = -0.25F
                letterSpacing = 0.09F
                hinting = Paint.HINTING_OFF
            }

            val titleFont = Paint().apply {
                textSize = 12F
                textAlign = Paint.Align.CENTER
            }

            val subTitleFont = Paint().apply {
                textSize = 6F
                textAlign = Paint.Align.LEFT
            }
            val noFill = Paint().apply {
                style = Paint.Style.STROKE
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.Q)
    fun report() {
        try {
            val file = generate()
            exportInMail(file)
        } catch (e: NoPrescriptionException) {
            AlertDialog.Builder(ctx)
                .setTitle("Erreur")
                .setMessage("Vous n'avez pas de prescription pour le moment.")
                .setPositiveButton("Ok") { dialog, _ ->
                    dialog.dismiss()
                }
                .show()
        }
    }

    @RequiresApi(Build.VERSION_CODES.Q)
    private fun generate(): File {
        val document = PdfDocument()
        val pageInfo = PageInfo.Builder(210, 297, 1).create()
        val page = document.startPage(pageInfo)
        val canvas = page.canvas

        canvas.drawText("Suivie médical du ${LocalDate.now()}", 105F, 20F, Fonts.titleFont)

        drawPrescriptions(canvas)


        document.finishPage(page)
        val file = File.createTempFile("tempFile", ".pdf")
        val fos = FileOutputStream(file)
        document.writeTo(fos)
        document.close()
        fos.close()
        return file
    }

    private fun drawPrescriptions(canvas: Canvas){
        val boxStore = ObjectBox.getInstance(ctx)
        val store = boxStore.boxFor(PrescriptionEntity::class.java)
        val prescriptions = store.query().build().find().map { it.convert() }

        if (prescriptions.isEmpty())
            throw NoPrescriptionException()

        val textPrescription = mutableListOf<String>()
        for (prescription in prescriptions.filter { it.treatment.medication?.name != null }) {
            val maxLineSize = 76
            val medicineText = "> ${prescription.treatment.medication?.name}".lowercase(Locale.ROOT)
            val descriptionText = "    {${prescription.treatment.posology}} " +
                        "[${prescription.treatment.frequency}] ${prescription.treatment.duration}"
                    .lowercase(Locale.ROOT)

            textPrescription.addAll(divideText(medicineText, maxLineSize))
            textPrescription.addAll(divideText(descriptionText, maxLineSize))
        }

        canvas.drawRoundRect(
            10F,
            30F,
            200F,
            55F + (10F.times(textPrescription.size)),
            7F,
            7F,
            Fonts.noFill
        )
        canvas.drawText("Listes des prescriptions: ", 12F, 38F, Fonts.subTitleFont)

        for ((index, line) in textPrescription.withIndex())
            canvas.drawText(line, 12F, 48F + 7F.times(index), Fonts.classicFont)

        canvas.drawText("{posology} [frequency]", 193F, 50F + 10F.times(textPrescription.size), Fonts.legendFont)
    }

    private fun divideText(text: String, maxLineSize: Int): List<String> {
        val lines = mutableListOf<String>()
        var currentLine = ""
        for (word in text.split(" ")) {
            if (currentLine.length + word.length > maxLineSize) {
                lines.add(currentLine)
                currentLine = ""
            }
            currentLine += "$word "
        }
        lines.add(currentLine)
        return lines
    }
    @RequiresApi(Build.VERSION_CODES.O)
    private fun exportInMail(attachment: File) {
        val newFile = File(attachment.parent, "Rapport-${LocalDate.now()}.pdf")
        if (Files.exists(newFile.toPath()))
            Files.delete(newFile.toPath())
        Files.move(attachment.toPath(), newFile.toPath())
        val i = Intent(Intent.ACTION_SEND)
        i.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
        i.type = "application/pdf"
        i.putExtra(Intent.EXTRA_EMAIL, arrayOf(""))
        i.putExtra(Intent.EXTRA_SUBJECT, "Rapport")
        i.putExtra(Intent.ACTION_MEDIA_SHARED, "Rapport")
        i.putExtra(Intent.EXTRA_TEXT, "Bonjour, veuillez trouver ci-joint le rapport")

        i.putExtra(
            Intent.EXTRA_STREAM,
            FileProvider.getUriForFile(ctx, "fr.medicapp.medicapp.fileprovider", newFile)
        )
        ctx.startActivity(Intent.createChooser(i, "Envoyer le rapport"))
    }


}