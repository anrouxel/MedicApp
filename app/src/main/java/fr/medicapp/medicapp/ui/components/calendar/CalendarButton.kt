package fr.medicapp.medicapp.ui.components.calendar

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronLeft
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedIconToggleButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.unit.dp
import com.kizitonwose.calendar.compose.WeekCalendar
import com.kizitonwose.calendar.compose.weekcalendar.WeekCalendarState
import com.kizitonwose.calendar.compose.weekcalendar.rememberWeekCalendarState
import com.kizitonwose.calendar.core.Week
import com.kizitonwose.calendar.core.WeekDay
import com.kizitonwose.calendar.core.atStartOfMonth
import com.kizitonwose.calendar.core.firstDayOfWeekFromLocale
import com.kizitonwose.calendar.core.yearMonth
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.launch
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.Month
import java.time.YearMonth
import java.time.format.TextStyle
import java.util.Locale

@Composable
fun Calendar(modifier: Modifier = Modifier) {
    val currentDate = remember { LocalDate.now() }
    val currentMonth = remember { YearMonth.now() }
    val startDate = remember { currentMonth.minusMonths(100).atStartOfMonth() } // Adjust as needed
    val endDate = remember { currentMonth.plusMonths(100).atEndOfMonth() } // Adjust as needed
    val firstDayOfWeek = remember { firstDayOfWeekFromLocale() } // Available from the library
    var selection by remember { mutableStateOf(currentDate) }

    val state = rememberWeekCalendarState(
        startDate = startDate,
        endDate = endDate,
        firstVisibleWeekDate = currentDate,
        firstDayOfWeek = firstDayOfWeek,
    )

    val visibleWeek = rememberFirstVisibleWeekAfterScroll(state)
    val coroutineScope = rememberCoroutineScope()

    MonthHeader(
        state = state,
        monthString = getWeekPageTitle(visibleWeek),
        onClick = suspend{
            state.animateScrollToWeek(currentDate)
            selection=currentDate
                         },
        coroutine = coroutineScope,

    )


    WeekCalendar(
        modifier = modifier,
        state = state,
        dayContent = { day ->
            Day(day, isSelected = selection == day.date) {
                if (it.date != selection) {
                    selection = it.date
                }
            }
        },
    )
}

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



fun YearMonth.displayText(short: Boolean = false): String {
    return "${this.month.displayText(short = short)
        .replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.ROOT) else it.toString() }} ${this.year}"
}

fun Month.displayText(short: Boolean = true): String {
    val style = if (short) TextStyle.SHORT else TextStyle.FULL
    return getDisplayName(style, Locale.getDefault()).replaceFirstChar {
        it.titlecase()
    }
}

fun DayOfWeek.displayText(uppercase: Boolean = false): String {
    return getDisplayName(TextStyle.SHORT, Locale.getDefault()).let { value ->
        if (uppercase) value.uppercase(Locale.getDefault()) else value
    }
}


//suspend fun returnToCurrent(state: WeekCalendarState, currentDate: LocalDate){
//    state.scrollToWeek(currentDate)
//}




