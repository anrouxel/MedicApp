package fr.medicapp.medicapp.ui.components.button

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import org.junit.Rule
import org.junit.Test

class ReusableRadioButtonGroupTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun radioButtonGroupSelectsCorrectOption() {
        val options = listOf("Option 1", "Option 2", "Option 3")
        var selectedOption = options[0]

        composeTestRule.setContent {
            ReusableRadioGroup(
                options = options,
                selectedOption = selectedOption,
                label = "Test",
                onClick = { selectedOption = it }
            )
        }

        composeTestRule.onNodeWithText("Option 2").performClick()
        assert(selectedOption == "Option 2")
    }

    @Test
    fun radioButtonGroupDisplaysAllOptions() {
        val options = listOf("Option 1", "Option 2", "Option 3")

        composeTestRule.setContent {
            ReusableRadioGroup(
                options = options,
                selectedOption = options[0],
                label = "Test",
                onClick = {}
            )
        }

        options.forEach { option ->
            composeTestRule.onNodeWithText(option).assertExists()
        }
    }

    @Test
    fun radioButtonGroupHandlesEmptyOptions() {
        val options = emptyList<String>()

        composeTestRule.setContent {
            ReusableRadioGroup(
                options = options,
                selectedOption = "",
                label = "Test",
                onClick = {}
            )
        }

        composeTestRule.onNodeWithText("Test").assertExists()
    }
}