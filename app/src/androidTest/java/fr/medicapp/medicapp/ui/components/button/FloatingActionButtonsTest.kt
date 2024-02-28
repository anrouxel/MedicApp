package fr.medicapp.medicapp.ui.components.button

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasClickAction
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.performClick
import org.junit.Rule
import org.junit.Test
import org.junit.Assert.*

class FloatingActionButtonsTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun floatingActionButtonOpen() {
        composeTestRule.setContent {
            FloatingActionButtons(buttons = listOf())
        }
        composeTestRule.onNodeWithContentDescription("Bouton pour ouvrir le menu").assertIsDisplayed().performClick()

        composeTestRule.onNodeWithContentDescription("Bouton pour fermer le menu").assertIsDisplayed()


    }

    @Test
    fun floatingActionButtonClose() {
        composeTestRule.setContent {
            FloatingActionButtons(buttons = listOf())
        }
        composeTestRule.onNodeWithContentDescription("Bouton pour ouvrir le menu").assertIsDisplayed().performClick()
        composeTestRule.onNodeWithContentDescription("Bouton pour fermer le menu").assertIsDisplayed().performClick()

        composeTestRule.onNodeWithContentDescription("Bouton pour ouvrir le menu").assertIsDisplayed()
    }

    @Test
    fun floatingActionButtonCorrectNumber() {
        composeTestRule.setContent {
            FloatingActionButtons(buttons = listOf(Pair({}, {}), Pair({}, {})))
        }
        composeTestRule.onNodeWithContentDescription("Bouton pour ouvrir le menu").assertIsDisplayed().performClick()

        val childs = composeTestRule.onAllNodes(hasClickAction())
        assertEquals(2, childs.fetchSemanticsNodes().size-1)
    }

}