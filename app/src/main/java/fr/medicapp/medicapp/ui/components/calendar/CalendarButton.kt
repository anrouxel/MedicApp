package fr.medicapp.medicapp.ui.components.calendar

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedIconToggleButton
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
fun CalendarButton(modifier: Modifier = Modifier, date : WeekCalendarState<DynamicSelectionState>) {
    SelectableWeekCalendar(
        modifier = modifier,
        calendarState = date,
        dayContent = { dayState ->
            MyDay(dayState)
        },
        daysOfWeekHeader = { weekState ->
            MyWeek(weekState)
        }

    )


}

@Composable
private fun MyDay(dayState: DayState<DynamicSelectionState>, modifier: Modifier = Modifier){
    val date = dayState.date
    val selectionState = dayState.selectionState
    val isSelected = selectionState.isDateSelected(date)
    /*Card(

        colors = CardDefaults.cardColors(
            containerColor =
//                if (isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surface,
                if (isSelected) MaterialTheme.colorScheme.primary
                else if (dayState.isCurrentDay) MaterialTheme.colorScheme.surface
                else Color.Transparent,
            contentColor = if (isSelected) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onSurface
        */

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
            if (!isSelected) {
                selectionState.onDateSelected(date)
            }
        },
        shape = MaterialTheme.shapes.medium
    ) {
        Text(
            text = date.dayOfMonth.toString(),
            fontSize = MaterialTheme.typography.bodySmall.fontSize,
            fontStyle = MaterialTheme.typography.bodySmall.fontStyle,
            fontWeight = MaterialTheme.typography.bodySmall.fontWeight,
        )
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

