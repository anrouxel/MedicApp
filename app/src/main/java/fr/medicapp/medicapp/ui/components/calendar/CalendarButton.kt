package fr.medicapp.medicapp.ui.components.calendar

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import io.github.boguszpawlowski.composecalendar.SelectableWeekCalendar
import io.github.boguszpawlowski.composecalendar.WeekCalendarState
import io.github.boguszpawlowski.composecalendar.rememberSelectableWeekCalendarState
import io.github.boguszpawlowski.composecalendar.selection.DynamicSelectionState



import java.util.Date

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
        calendarState = date
    )

}