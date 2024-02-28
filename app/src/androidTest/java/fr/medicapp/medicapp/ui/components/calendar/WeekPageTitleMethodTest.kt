package fr.medicapp.medicapp.ui.components.calendar

import com.kizitonwose.calendar.core.Week
import com.kizitonwose.calendar.core.WeekDay
import com.kizitonwose.calendar.core.WeekDayPosition
import org.junit.Assert.assertEquals
import org.junit.Test
import java.time.LocalDate

class WeekPageTitleMethodTest {
    @Test
    fun weekPageTitleSameMonthAndYear() {
        val week = Week(days = listOf(
            WeekDay(LocalDate.of(2023,1,1), position = WeekDayPosition.InDate)
        ))
        val title = getWeekPageTitle(week)
        assertEquals("Janvier 2023", title)
    }

    @Test
    fun weekPageTitleDifferentMonthSameYear() {
        val week = Week(days = listOf(
            WeekDay(LocalDate.of(2023, 1, 31), position = WeekDayPosition.InDate),
            WeekDay(LocalDate.of(2023, 2, 6), position = WeekDayPosition.InDate)
        ))
        val title = getWeekPageTitle(week)
        assertEquals("Janvier - Février 2023", title)
    }

    @Test
    fun weekPageTitleDifferentMonthAndYear() {
        val week = Week(days = listOf(
            WeekDay(LocalDate.of(2023, 12, 31), position = WeekDayPosition.InDate),
            WeekDay(LocalDate.of(2024, 1, 6), position = WeekDayPosition.InDate)
        ))
        val title = getWeekPageTitle(week)
        assertEquals("Décembre 2023 - Janvier 2024", title)
    }
}