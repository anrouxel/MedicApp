package fr.medicapp.medicapp.ui.components.button

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.performClick
import org.junit.Rule
import org.junit.Test
import org.junit.jupiter.api.Assertions.*

class FloatingActionButtonsTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun floatingActionButtonOpens() {
        composeTestRule.setContent {
            FloatingActionButtons(buttons = listOf())
        }
        composeTestRule.onNodeWithContentDescription("Bouton pour ouvrir la modal").assertIsDisplayed().performClick()

        composeTestRule.onNodeWithContentDescription("Bouton pour fermer la modal").assertIsDisplayed()
    }

}