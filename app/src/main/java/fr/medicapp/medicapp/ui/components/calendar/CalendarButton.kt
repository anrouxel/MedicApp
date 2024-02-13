package fr.medicapp.medicapp.ui.components.calendar

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import io.github.boguszpawlowski.composecalendar.SelectableWeekCalendar
import io.github.boguszpawlowski.composecalendar.WeekCalendarState
import io.github.boguszpawlowski.composecalendar.day.DayState
import io.github.boguszpawlowski.composecalendar.day.DefaultDay
import io.github.boguszpawlowski.composecalendar.selection.DynamicSelectionState
import java.time.DayOfWeek
import java.time.format.TextStyle
import java.util.Locale

//Copyright 2022 Bogusz Pawłowski
//
//Licensed under the Apache License, Version 2.0 (the "License");
//you may not use this file except in compliance with the License.
//You may obtain a copy of the License at
//
//http://www.apache.org/licenses/LICENSE-2.0
//
//Unless required by applicable law or agreed to in writing, software
//distributed under the License is distributed on an "AS IS" BASIS,
//WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
//See the License for the specific language governing permissions and
//limitations under the License.
//https://github.com/boguszpawlowski/ComposeCalendar

@Composable
fun CalendarButton(modifier: Modifier = Modifier, date : WeekCalendarState<DynamicSelectionState>, onClick: () -> Unit) {
    SelectableWeekCalendar(
        modifier = modifier,
        calendarState = date,
//        dayContent = { dayState ->
//            MyDay(dayState)
//        },
        daysOfWeekHeader = { weekState ->
            MyWeek(weekState)
        }

    )


}

@Composable
private fun MyDay(dayState: DayState<DynamicSelectionState>, modifier: Modifier = Modifier){
    Card(
        modifier = modifier
            .aspectRatio(1f)
            .padding(2.dp),
        border = if (dayState.isCurrentDay) BorderStroke(1.dp, MaterialTheme.colorScheme.primary) else null,

    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier


        ) {

        }
    }

}

@Composable
private fun MyWeek(weekState: List<DayOfWeek>){
    Row{
        weekState.forEach {
            //take the day of the week (in the language of the device) and display it
            val day = it.getDisplayName(TextStyle.SHORT, Locale.getDefault())
            Text(
                textAlign = TextAlign.Center,
                text = day,
                modifier = Modifier
                    .weight(1f)
                    .wrapContentHeight()
            )
        }
    }
}

