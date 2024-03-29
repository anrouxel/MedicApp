package fr.medicapp.medicapp.ui.components.calendar

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedIconToggleButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kizitonwose.calendar.compose.WeekCalendar
import com.kizitonwose.calendar.compose.weekcalendar.WeekCalendarState
import com.kizitonwose.calendar.compose.weekcalendar.rememberWeekCalendarState
import com.kizitonwose.calendar.core.Week
import com.kizitonwose.calendar.core.WeekDay
import com.kizitonwose.calendar.core.WeekDayPosition
import com.kizitonwose.calendar.core.atStartOfMonth
import com.kizitonwose.calendar.core.firstDayOfWeekFromLocale
import fr.medicapp.medicapp.ui.theme.EUPurpleColorShema
import fr.medicapp.medicapp.ui.theme.MedicAppTheme
import kotlinx.coroutines.flow.filter
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.Month
import java.time.YearMonth
import java.time.format.TextStyle
import java.util.Locale

/**
 * This is a Composable function that creates a calendar UI component.
 *
 * @param modifier Modifier for the Composable. Default value is Modifier.
 *
 * The function does the following:
 * - Initializes several variables such as the current date, current month, start and end dates for the calendar, and the first day of the week.
 * - Creates a mutable state for the selected date on the calendar, initially set to the current date.
 * - Creates a `WeekCalendarState` object, which represents the state of the calendar.
 * - Creates a `CoroutineScope` for launching coroutines from the Composable.
 * - Creates a `Column` Composable, which is a vertical layout. Inside this `Column`, it places a `MonthHeader` Composable and a `WeekCalendar` Composable.
 * - The `MonthHeader` displays the current month and allows the user to scroll to the current date when clicked.
 * - The `WeekCalendar` displays the days of the week and allows the user to select a date. When a date is selected, it updates the `selection` state.
 */

@Composable
fun Calendar(modifier: Modifier = Modifier, selection: MutableState<LocalDate>) {
    val currentDate = remember { LocalDate.now() }
    val currentMonth = remember { YearMonth.now() }
    val startDate = remember { currentMonth.minusMonths(100).atStartOfMonth() } // Adjust as needed
    val endDate = remember { currentMonth.plusMonths(100).atEndOfMonth() } // Adjust as needed
    val firstDayOfWeek = remember { firstDayOfWeekFromLocale() } // Available from the library

    val state = rememberWeekCalendarState(
        startDate = startDate,
        endDate = endDate,
        firstVisibleWeekDate = currentDate,
        firstDayOfWeek = firstDayOfWeek,
    )

    val visibleWeek = rememberFirstVisibleWeekAfterScroll(state)

    val coroutineScope = rememberCoroutineScope()

    Column {
        MonthHeader(
            state = state,
            month = getWeekPageTitle(visibleWeek),
            onClick = suspend {
                state.animateScrollToWeek(currentDate)
                selection.value = currentDate
            },
            coroutine = coroutineScope,
        )

        Spacer(modifier = Modifier.padding(10.dp))

        WeekCalendar(
            modifier = modifier,
            state = state,
            dayContent = { day ->
                Day(day, isSelected = selection.value == day.date) {
                    if (it.date != selection.value) {
                        selection.value = it.date
                    }
                }
            },
        )
    }
}

@Preview(name = "Light Theme")
@Composable
private fun CalendarPreview() {
    MedicAppTheme(
        darkTheme = false,
        dynamicColor = false,
        theme = EUPurpleColorShema
    ) {
        val selectionLight = remember { mutableStateOf(LocalDate.now()) }
        Calendar(selection = selectionLight)
    }
}

@Preview(name = "Light Theme")
@Composable
private fun CalendarDarkPreview() {
    MedicAppTheme(
        darkTheme = true,
        dynamicColor = false,
        theme = EUPurpleColorShema
    ) {
        val selectionDark = remember { mutableStateOf(LocalDate.now()) }
        Calendar(selection = selectionDark)
    }
}

/**
 * This is a Composable function that creates the button for the day of the week.
 *
 * @param day The day of the week.
 * @param isSelected A boolean value that indicates whether the day is selected.
 * @param onClick A function to call when the user clicks the day.
 */

@Composable
private fun Day(
    day: WeekDay,
    isSelected: Boolean,
    onClick: (WeekDay) -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = day.date.dayOfWeek.displayText(),
            fontSize = MaterialTheme.typography.bodySmall.fontSize,
            fontStyle = MaterialTheme.typography.bodySmall.fontStyle,
            fontWeight = MaterialTheme.typography.bodySmall.fontWeight,
        )
        OutlinedIconToggleButton(
            colors = IconButtonDefaults.outlinedIconToggleButtonColors(
                containerColor = MaterialTheme.colorScheme.surface,
                checkedContainerColor = MaterialTheme.colorScheme.primary
            ),
            border = BorderStroke(
                width = 1.dp,
                color = MaterialTheme.colorScheme.primary
            ),
            checked = isSelected,
            onCheckedChange = {
                onClick(day)
            },
            shape = MaterialTheme.shapes.medium
        ) {
            Text(
                text = day.date.dayOfMonth.toString(),
                fontSize = MaterialTheme.typography.bodySmall.fontSize,
                fontStyle = MaterialTheme.typography.bodySmall.fontStyle,
                fontWeight = MaterialTheme.typography.bodySmall.fontWeight,
            )
        }
    }
}

@Preview(name = "Light Theme")
@Composable
private fun DayPreview() {
    MedicAppTheme(
        darkTheme = false,
        dynamicColor = false,
        theme = EUPurpleColorShema
    ) {
        Day(
            day = WeekDay(LocalDate.now(), WeekDayPosition.InDate),
            isSelected = false,
            onClick = {}
        )
    }
}

@Preview(name = "Dark Theme")
@Composable
private fun DayDarkPreview() {
    MedicAppTheme(
        darkTheme = true,
        dynamicColor = false,
        theme = EUPurpleColorShema
    ) {
        Day(
            day = WeekDay(LocalDate.now(), WeekDayPosition.InDate),
            isSelected = false,
            onClick = {}
        )
    }
}

/**
 * This is a Composable function to remember the first visible week after scrolling.
 *
 * @param state The state of the calendar.
 * @return The first visible week after scrolling.
 */

@Composable
fun rememberFirstVisibleWeekAfterScroll(state: WeekCalendarState): Week {
    val visibleWeek = remember(state) { mutableStateOf(state.firstVisibleWeek) }
    LaunchedEffect(state) {
        snapshotFlow { state.isScrollInProgress }
            .filter { scrolling -> !scrolling }
            .collect { visibleWeek.value = state.firstVisibleWeek }
    }
    return visibleWeek.value
}


/**
 * A function to display a personalized YearMonth.
 */

fun YearMonth.displayText(short: Boolean = false): String {
    return "${
        this.month.displayText(short = short)
            .replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.ROOT) else it.toString() }
    } ${this.year}"
}

/**
 * A function to display a personalized and localized Month.
 */

fun Month.displayText(short: Boolean = true): String {
    val style = if (short) TextStyle.SHORT else TextStyle.FULL
    return getDisplayName(style, Locale.getDefault()).replaceFirstChar {
        it.titlecase()
    }
}

/**
 * A function to display a personalized and localized DayOfWeek.
 */

fun DayOfWeek.displayText(uppercase: Boolean = false): String {
    return getDisplayName(TextStyle.SHORT, Locale.getDefault()).let { value ->
        if (uppercase) value.uppercase(Locale.getDefault()) else value
    }
}
