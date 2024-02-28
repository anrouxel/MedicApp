package fr.medicapp.medicapp.ui.components.calendar

import com.kizitonwose.calendar.core.Week
import com.kizitonwose.calendar.core.yearMonth

/**
 * This function returns the title of a week.
 *
 * @param week The week for which to get the title.
 * @return The title of the week.
 * example: "January 2023" or "January - February 2023" or "January 2023 - February 2024"
 */
fun getWeekPageTitle(week: Week): String {
    val firstDate = week.days.first().date
    val lastDate = week.days.last().date
    return when {
        firstDate.yearMonth == lastDate.yearMonth -> {
            firstDate.yearMonth.displayText()
        }

        firstDate.year == lastDate.year -> {
            "${firstDate.month.displayText(short = false)} - ${lastDate.yearMonth.displayText()}"
        }

        else -> {
            "${firstDate.yearMonth.displayText()} - ${lastDate.yearMonth.displayText()}"
        }
    }
}
