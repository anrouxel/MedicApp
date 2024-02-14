package fr.medicapp.medicapp.ui.components.calendar

import android.util.Log
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import com.kizitonwose.calendar.compose.weekcalendar.WeekCalendarState
import com.kizitonwose.calendar.core.Week
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
private fun DecrementButton(weekState: WeekCalendarState, coroutine : CoroutineScope){
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

@Composable
private fun IncrementButton(weekState: WeekCalendarState, coroutine : CoroutineScope){
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

@Composable
fun MonthHeader(state: WeekCalendarState,
                month: String,
                onClick: suspend () -> Unit,
                coroutine: CoroutineScope,
                modifier: Modifier = Modifier){
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        DecrementButton(weekState = state, coroutine = coroutine)
        Text(
            text = month,
            //when clicked, go back to the current week and day
            modifier.clickable {
                coroutine.launch {
                    onClick()
                }
            }
        )
        IncrementButton(weekState = state, coroutine = coroutine)
    }

}