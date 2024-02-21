package fr.medicapp.medicapp.ui.components.calendar

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronLeft
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.tooling.preview.Preview
import com.kizitonwose.calendar.compose.weekcalendar.WeekCalendarState
import com.kizitonwose.calendar.compose.weekcalendar.rememberWeekCalendarState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

/**
 * This is a Composable Button that allows the user to scroll to the previous week.
 *
 * @param weekState The state of the calendar.
 * @param coroutine A `CoroutineScope` for launching coroutines from the Composable.
 *
 */
@Composable
private fun DecrementButton(weekState: WeekCalendarState, coroutine: CoroutineScope) {
    IconButton(
        onClick = {
            coroutine.launch {
                val previousWeek = weekState.firstVisibleWeek
                    .days[0].date.plusWeeks(-1)

                weekState.animateScrollToWeek(previousWeek)
            }
        }

    ) {
        Image(
            imageVector = Icons.Default.ChevronLeft,
            colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onSurface),
            contentDescription = "Previous"
        )
    }
}

/**
 * This is a Composable Button that allows the user to scroll to the next week.
 *
 * @param weekState The state of the calendar.
 * @param coroutine A `CoroutineScope` for launching coroutines from the Composable.
 *
 */
@Composable
private fun IncrementButton(weekState: WeekCalendarState, coroutine: CoroutineScope) {
    IconButton(
        onClick = {
            coroutine.launch {
                val nextWeek = weekState.firstVisibleWeek
                    .days[0].date.plusWeeks(1)

                weekState.animateScrollToWeek(nextWeek)
            }
        }

    ) {
        Image(
            imageVector = Icons.Default.ChevronRight,
            colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onSurface),
            contentDescription = "Previous"
        )
    }
}

/**
 * This is a Composable function that creates a month header UI component.
 *
 * @param state The state of the calendar.
 * @param month The current month string.
 * @param onClick A function to call when the user clicks the month header.
 * @param coroutine A `CoroutineScope` for launching coroutines from the Composable.
 * @param modifier Modifier for the Composable. Default value is Modifier.
 *
 * The function does the following:
 * - Creates a `Row` Composable, which is a horizontal layout. Inside this `Row`, it places two `IconButton` Composables and a `Text` Composable.
 * - The `IconButton` Composables allow the user to scroll to the previous or next week.
 * - The `Text` Composable displays the current month and allows the user to scroll to the current date when clicked.
 */

@Composable
fun MonthHeader(
    state: WeekCalendarState,
    month: String,
    onClick: suspend () -> Unit,
    coroutine: CoroutineScope,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        DecrementButton(weekState = state, coroutine = coroutine)
        Text(
            text = month,
            // when clicked, go back to the current week and day
            modifier.clickable {
                coroutine.launch {
                    onClick()
                }
            }
        )
        IncrementButton(weekState = state, coroutine = coroutine)
    }
}

@Preview
@Composable
fun MonthHeaderPreview() {
    MonthHeader(
        state = rememberWeekCalendarState(),
        month = "January",
        onClick = {},
        coroutine = rememberCoroutineScope()
    )
}
