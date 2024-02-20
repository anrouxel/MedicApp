package fr.medicapp.medicapp.ui

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import fr.medicapp.medicapp.model.Alarm
import fr.medicapp.medicapp.model.Notification
import fr.medicapp.medicapp.model.Prescription
import fr.medicapp.medicapp.model.Treatment
import fr.medicapp.medicapp.ui.screen.prescription.PrescriptionHome
import org.junit.Rule
import org.junit.Test
import java.time.DayOfWeek

class PrescriptionTest {

    @get:Rule
    val rule = createAndroidComposeRule<ComponentActivity>()

    private val prescription = Prescription(
        0L,
        null,
        mutableListOf(),
        null,
        Treatment(0L, "posology", "frequency", null, null),
        mutableListOf(
            Notification(
                0L,
                true,
                mutableListOf(DayOfWeek.MONDAY),
                mutableListOf(Alarm(0L, 0, 0))
            )
        )
    )

    private val nodeNoPrescription =
        hasText("Vous n'avez pas d'ordonnances.\nPour en ajouter, cliquez sur le bouton en bas")

    @Test
    fun testNoPrescription() {
        rule.setContent { PrescriptionHome(listOf()) {} }
        rule.onNode(nodeNoPrescription).assertExists()
    }

    @Test
    fun testWithPrescription() {
        rule.setContent { PrescriptionHome(listOf(prescription)) }
        rule.onNode(nodeNoPrescription).assertDoesNotExist()

    }


}