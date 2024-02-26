package fr.medicapp.medicapp.reportGenerator

import fr.medicapp.medicapp.model.Alarm
import fr.medicapp.medicapp.model.Duration
import fr.medicapp.medicapp.model.Notification
import fr.medicapp.medicapp.model.Prescription
import fr.medicapp.medicapp.model.Treatment
import org.junit.Test
import java.time.DayOfWeek
import java.time.LocalDate

class ReportGeneratorTest {

    @Test
    fun testMissingTakes() {
        val treatment = Treatment(duration = Duration(startDate = LocalDate.of(2024, 2, 1), endDate = LocalDate.of(2024, 2, 16)))
        val notifications = mutableListOf(Notification(days= mutableListOf(DayOfWeek.MONDAY), alarms = mutableListOf(
            Alarm(hour = 8, minute = 0)
        )))
        val prescription = Prescription(treatment = treatment, notifications = notifications)
        ReportGenerator.calcMissingTake(prescription).let {
            assert(it.size == 3)
            assert(it[0] == LocalDate.of(2024, 2, 5).atTime(8, 0))
            assert(it[1] == LocalDate.of(2024, 2, 12).atTime(8, 0))
            assert(it[2] == LocalDate.of(2024, 2, 19).atTime(8, 0))
        }
    }

    @Test
    fun testFrequencyGen(){
        val notification = Notification(days = mutableListOf(DayOfWeek.MONDAY, DayOfWeek.WEDNESDAY), alarms = mutableListOf(Alarm(hour = 8, minute = 0)))
        val notifications = mutableListOf(notification)
        println(ReportGenerator.genFrequency(notifications))
        assert(ReportGenerator.genFrequency(notifications) == "0.28 fois par jour")
        assert(ReportGenerator.genFrequency(mutableListOf()) == "")

    }
}
