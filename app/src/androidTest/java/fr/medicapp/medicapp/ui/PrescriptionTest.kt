package fr.medicapp.medicapp.ui

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import fr.medicapp.medicapp.model.prescription.Alarm
import fr.medicapp.medicapp.model.prescription.NotificationInformation
import fr.medicapp.medicapp.model.prescription.PrescriptionInformation
import fr.medicapp.medicapp.model.prescription.relationship.Notification
import fr.medicapp.medicapp.model.prescription.relationship.Prescription
import fr.medicapp.medicapp.ui.screen.prescription.PrescriptionHome
import org.junit.Rule
import org.junit.Test
import java.time.DayOfWeek
import java.time.LocalTime

class PrescriptionTest {

    @get:Rule
    val rule = createAndroidComposeRule<ComponentActivity>()

    private val prescription = Prescription(
        prescriptionInformation = PrescriptionInformation(0L, "posology", "frequency"),
        notifications = mutableListOf(
            Notification(
                notificationInformation = NotificationInformation(
                    0L,
                    true,
                    mutableListOf(DayOfWeek.MONDAY),
                ),
                alarms = mutableListOf(
                    Alarm(
                        0L,
                        LocalTime.of(12, 0)
                    )
                )
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
