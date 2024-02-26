package fr.medicapp.medicapp.reportGenerator

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.pdf.PdfDocument
import android.graphics.pdf.PdfDocument.PageInfo
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.appcompat.content.res.AppCompatResources
import androidx.compose.ui.graphics.toArgb
import androidx.core.content.FileProvider
import androidx.core.graphics.drawable.toBitmap
import com.google.zxing.BarcodeFormat
import com.google.zxing.EncodeHintType
import com.google.zxing.MultiFormatWriter
import com.google.zxing.common.BitMatrix
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel
import fr.medicapp.medicapp.R
import fr.medicapp.medicapp.database.repositories.prescription.PrescriptionRepository
import fr.medicapp.medicapp.model.prescription.relationship.Notification
import fr.medicapp.medicapp.model.prescription.relationship.Prescription
import fr.medicapp.medicapp.ui.components.dialog.NoPrescriptionDialog
import fr.medicapp.medicapp.ui.theme.EUGreen130
import java.io.File
import java.io.FileOutputStream
import java.nio.file.Files
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.Locale

class NoPrescriptionException : Exception("Vous n'avez pas de prescription pour le moment.")
class ReportGenerator(private val ctx: Context) {

    private val document = PdfDocument()
    private val pageInfo = PageInfo.Builder(2100, 2970, 1).create()
    private var page = document.startPage(pageInfo)
    private var minY = 0F
    private var actualCanvas: Canvas = page.canvas

    private class Fonts {
        companion object {
            val classicFont = Paint().apply {
                textSize = 40F
                textAlign = Paint.Align.LEFT
                letterSpacing = 0.1F
                hinting = Paint.HINTING_OFF
            }

            val legendFont = Paint().apply {
                textSize = 35F
                textAlign = Paint.Align.RIGHT
                textSkewX = -0.25F
                letterSpacing = 0.1F
                hinting = Paint.HINTING_OFF
            }

            val titleFont = Paint().apply {
                textSize = 120F
                textAlign = Paint.Align.CENTER
            }

            val logoPaint = Paint().apply {
                alpha = 70
            }

            val subTitleFont = Paint().apply {
                textSize = 60F
                textAlign = Paint.Align.LEFT
            }
            val noFill = Paint().apply {
                style = Paint.Style.STROKE
            }
        }
    }


    @RequiresApi(Build.VERSION_CODES.Q)
    fun report(signature: String = "", notes: String = "") {
        try {
            val file = generate(signature, notes)
            exportInMail(file, signature)
        } catch (e: NoPrescriptionException) {
            NoPrescriptionDialog.show(ctx)
        }
    }

    @RequiresApi(Build.VERSION_CODES.Q)
    private fun generate(signature: String = "", notes: String = ""): File {
        drawLogo()

        drawHeader(signature)
        drawPrescriptions()
        drawMissingTakes()
        if (notes.isNotBlank()) {
            drawNotes(notes)
        }

        document.finishPage(page)
        val file = File.createTempFile("tempFile", ".pdf")
        val fos = FileOutputStream(file)
        document.writeTo(fos)
        document.close()
        fos.close()
        return file
    }

    private fun drawLogo() {
        val logo = R.drawable.medicapp_eu_green

        val bitmap = AppCompatResources.getDrawable(ctx, logo)?.toBitmap(1900, 1900) ?: throw Exception("Logo not found")

        actualCanvas.drawBitmap(bitmap, 100F, 535F, Fonts.logoPaint)
    }

    private fun drawPrescriptions() {
        Thread{
            val prescriptions = PrescriptionRepository(ctx).getAll()
            if (prescriptions.isEmpty())
                throw NoPrescriptionException()

            val textPrescription = mutableListOf<String>()
            for (prescription in prescriptions.filter { it.medication?.medicationInformation?.name != null }) {
                val qrCode =
                    qrCodeGenerator(prescription.medication?.medicationInformation?.cisCode.toString(), 150, 150)
                if (qrCode != null)
                    actualCanvas.drawBitmap(qrCode, 120F, 555F + 70F.times(textPrescription.size), Paint())

                val maxLineSize = 72
                val medicineText = "> ${prescription.medication?.medicationInformation?.name}".lowercase(Locale.ROOT)
                val frequency = genFrequency(prescription.notifications)

                val descriptionText = "    {${prescription.prescriptionInformation.posology.trim()}} " +
                        ((if (frequency.isNotBlank()) "[$frequency] " else "") +
                                "${prescription.duration}")
                            .lowercase(Locale.ROOT)

                textPrescription.addAll(divideText(medicineText, maxLineSize))
                textPrescription.addAll(divideText(descriptionText, maxLineSize))
                textPrescription.add("")
            }

            actualCanvas.drawRoundRect(
                100F,
                400F,
                2000F,
                650F + (70F.times(textPrescription.size)),
                30F,
                30F,
                Fonts.noFill
            )
            actualCanvas.drawText("Listes des prescriptions: ", 120F, 480F, Fonts.subTitleFont)
            for ((index, line) in textPrescription.withIndex())
                actualCanvas.drawText(line, 280F, 580F + 70F.times(index), Fonts.classicFont)

            actualCanvas.drawText(
                "{posology} [frequency]",
                1930F,
                600F + 70F.times(textPrescription.size),
                Fonts.legendFont
            )
            minY = 750F + 70F.times(textPrescription.size)
        }


    }

    private fun drawHeader(signature: String = ""){
        actualCanvas.drawText("Suivie médical${if (signature.isNotBlank()) " de $signature" else ""}", 1050F, 200F, Fonts.titleFont)
        actualCanvas.drawText("Rapport du ${LocalDate.now()} généré par MedicApp", 120F, 300F, Fonts.subTitleFont)
    }
    private fun drawMissingTakes() {
        fun dateFormat(date: Int): String {
            return date.toString().padStart(2, '0')
        }
        Thread {
            val prescriptions = PrescriptionRepository(ctx).getAll()
            if (prescriptions.isEmpty())
                throw NoPrescriptionException()

            val textMissingTakes = mutableListOf<String>()
            for (prescription in prescriptions.filter { it.medication?.medicationInformation?.name != null }) {
                val missingTakes = calcMissingTake(prescription).map {
                    "${it.year}-" +
                            "${dateFormat(it.monthValue)}-" +
                            "${dateFormat(it.dayOfMonth)} à " +
                            "${dateFormat(it.hour)}h" +
                            dateFormat(it.minute)
                }
                if (missingTakes.isEmpty())
                    continue
                val maxLineSize = 72
                val medicineText = "> ${prescription.medication?.medicationInformation?.name}".lowercase(Locale.ROOT)

                textMissingTakes.addAll(divideText(medicineText, maxLineSize))
                for (take in missingTakes) {
                    textMissingTakes.add("Manqué le: $take")
                }
            }
            drawSecure(100F){
                actualCanvas.drawText("Prises manquées: ", 70F, minY, Fonts.subTitleFont)
            }
            for (line in textMissingTakes)
                drawSecure(75F) {
                    actualCanvas.drawText(line, 70F, minY, Fonts.classicFont)
                }
            minY += 80F
        }.start()


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

    private fun drawSecure( drawingSize: Float, drawing: () -> Unit,){
        if ((drawingSize + minY) > (page.info.pageHeight - 10)){
            document.finishPage(page)
            page = document.startPage(pageInfo)
            actualCanvas = page.canvas
            minY = 80F
            drawing()
            minY = drawingSize + 80F
            drawLogo()
        }else{
            drawing()
            minY += drawingSize
        }
    }

    private fun drawNotes(notes: String){
        actualCanvas.drawLine(70F, minY, 2030F, minY, Fonts.noFill)
        minY += 100F
        val maxLineSize = 72
        val lines = divideText(notes, maxLineSize)
        drawSecure(85F){
            actualCanvas.drawText("Notes du patient: ", 70F, minY, Fonts.subTitleFont)
        }
        minY += 50F
        for (line in lines)
            drawSecure(85F) {
                actualCanvas.drawText(line, 70F, minY, Fonts.classicFont)
            }
    }
    companion object {
        fun qrCodeGenerator(id: String, width: Int, height: Int): Bitmap? {

            val source = "https://base-donnees-publique.medicaments.gouv.fr/extrait.php?specid=$id"
            val hashMap = hashMapOf<EncodeHintType, Any?>()
            hashMap[EncodeHintType.MARGIN] = 0
            hashMap[EncodeHintType.ERROR_CORRECTION] = ErrorCorrectionLevel.H
            val result: BitMatrix = try {
                MultiFormatWriter().encode(source, BarcodeFormat.QR_CODE, width, height, hashMap)
            } catch (e: Exception) {
                return null
            }

            val w = result.width
            val h = result.height
            val pixels = IntArray(w * h)

            for (y in 0 until h) {
                val offset = y * w
                for (x in 0 until w) {
                    pixels[offset + x] = if (result[x, y]) EUGreen130.toArgb() else Color.WHITE
                }
            }

            val bitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888)
            bitmap.setPixels(pixels, 0, width, 0, 0, w, h)

            return bitmap
        }

        fun genFrequency(notifications: List<Notification>): String{
            val frequency = (notifications.sumOf { it.alarms.size * it.notificationInformation.days.size } / 7.0).round(2)
            return if (frequency == 0.0) ""
            else "$frequency fois par jour"
        }
        fun calcMissingTake(prescription: Prescription): List<LocalDateTime> {
            val takes = prescription.prescriptionInformation.takes
            val missingTakes = mutableListOf<LocalDateTime>()
            for (date in prescription.duration?.startDate!!.datesUntil(LocalDate.now())) {
                prescription.notifications.forEach { notification ->
                    if (date.dayOfWeek !in notification.notificationInformation.days)
                        return@forEach
                    notification.alarms.forEach { alarm ->
                        val defaultTake = LocalDate.of(date.year, date.month, date.dayOfMonth)
                            .atTime(alarm.time.hour, alarm.time.minute)
                        if (defaultTake !in takes)
                            missingTakes.add(defaultTake)
                    }
                }
            }
            return missingTakes
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun exportInMail(attachment: File, signature: String = "") {
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
        i.putExtra(Intent.EXTRA_TEXT, "Bonjour, veuillez trouver ci-joint le rapport${if (signature.isNotBlank()) " de $signature" else ""}.")

        i.putExtra(
            Intent.EXTRA_STREAM,
            FileProvider.getUriForFile(ctx, "fr.medicapp.medicapp.fileprovider", newFile)
        )
        ctx.startActivity(Intent.createChooser(i, "Envoyer le rapport"))
    }


}

private fun Double.round(i: Int): Double {
    var multiplier = 1.0
    repeat(i) { multiplier *= 10 }
    return kotlin.math.round(this * multiplier) / multiplier
}
