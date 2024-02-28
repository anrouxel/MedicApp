package fr.medicapp.medicapp.reportGenerator

import fr.medicapp.medicapp.model.prescription.Alarm
import fr.medicapp.medicapp.model.prescription.Duration
import fr.medicapp.medicapp.model.prescription.NotificationInformation
import fr.medicapp.medicapp.model.prescription.relationship.Notification
import fr.medicapp.medicapp.model.prescription.relationship.Prescription
import org.junit.Test
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.LocalTime

class ReportGeneratorTest {

    @Test
    fun testMissingTakes() {
        val notifications = mutableListOf(
            Notification(
                notificationInformation = NotificationInformation(
                    days = mutableListOf(DayOfWeek.MONDAY)
                ),
                alarms = mutableListOf(
                    Alarm(
                        time = LocalTime.of(8, 0)
                    )
                )
            )
        )
        val prescription = Prescription(
            duration = Duration(
                startDate = LocalDate.of(2024, 2, 1),
                endDate = LocalDate.of(2024, 2, 16)
            ),
            notifications = notifications
        )
        ReportGenerator.calcMissingTake(prescription).let {
            assert(it.size == 3)
            assert(it[0] == LocalDate.of(2024, 2, 5).atTime(8, 0))
            assert(it[1] == LocalDate.of(2024, 2, 12).atTime(8, 0))
            assert(it[2] == LocalDate.of(2024, 2, 19).atTime(8, 0))
        }
    }

    @Test
    fun testFrequencyGen() {
        val notifications = mutableListOf(
            Notification(
                notificationInformation = NotificationInformation(
                    days = mutableListOf(
                        DayOfWeek.MONDAY,
                        DayOfWeek.WEDNESDAY
                    )
                ),
                alarms = mutableListOf(
                    Alarm(
                        time = LocalTime.of(8, 0)
                    )
                )
            )
        )
        println(ReportGenerator.genFrequency(notifications))
        assert(ReportGenerator.genFrequency(notifications) == "0.29 fois par jour")
        assert(ReportGenerator.genFrequency(mutableListOf()) == "")
    }
}
