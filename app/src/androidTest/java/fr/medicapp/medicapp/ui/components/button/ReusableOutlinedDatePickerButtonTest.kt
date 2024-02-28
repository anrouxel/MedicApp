package fr.medicapp.medicapp.ui.components.button

import androidx.compose.ui.test.hasClickAction
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import org.junit.Rule
import org.junit.Test
import java.time.LocalDate


class ReusableOutlinedDatePickerButtonTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun datePickerButtonOpensModalOnClick() {

        composeTestRule.setContent {
            ReusableOutlinedDatePickerButton(
                value = LocalDate.now(),
                label = "Date",
                onSelected = {},
            )
        }

        composeTestRule.onNode(hasClickAction()).performClick()
        composeTestRule.onNodeWithTag("DatePickerModal").assertExists()
    }

    @Test
    fun datePickerButtonDisplaysCorrectLabel() {
        val label = "Test Date"

        composeTestRule.setContent {
            ReusableOutlinedDatePickerButton(
                value = LocalDate.now(),
                label = label,
                onSelected = {}
            )
        }

        composeTestRule.onNode(hasText(label)).assertExists()
    }

    @Test
    fun datePickerButtonHandlesNullDate() {
        composeTestRule.setContent {
            ReusableOutlinedDatePickerButton(
                value = null,
                label = "Date",
                onSelected = {}
            )
        }

        composeTestRule.onNode(hasText("")).assertExists()
    }

    @Test
    fun datePickerButtonHandlesNonNullDate() {
        val date = LocalDate.now()

        composeTestRule.setContent {
            ReusableOutlinedDatePickerButton(
                value = date,
                label = "Date",
                onSelected = {}
            )
        }

        composeTestRule.onNode(hasText(date.toString())).assertExists()
    }
}