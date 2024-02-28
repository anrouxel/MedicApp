package fr.medicapp.medicapp.ui.components.button

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Shield
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test

class ReusableAlertIconButtonTest {

    @get:Rule
    val composeTestRule = createComposeRule()
    @Test
    fun reusableAlertIconButtonOpensAlertModalOnClick() {
        composeTestRule.setContent {
            ReusableAlertIconButton(
                icon = Icons.Filled.Shield,
                onClick = {},
                title = "Alert title",
                content = "Alert content",
                dismissText = "Dismiss",
                confirmText = "Confirm"
            )
        }

        composeTestRule.onNodeWithContentDescription("Alert content").performClick()
        composeTestRule.onNodeWithText("Alert title").assertIsDisplayed()
        composeTestRule.onNodeWithText("Alert content").assertIsDisplayed()
    }

    @Test
    fun reusableAlertIconButtonClosesAlertModalOnDismiss() {
        composeTestRule.setContent {
            ReusableAlertIconButton(
                icon = Icons.Filled.Shield,
                onClick = {},
                title = "Alert title",
                content = "Alert content",
                dismissText = "Dismiss",
                confirmText = "Confirm"
            )
        }

        composeTestRule.onNodeWithContentDescription("Alert content").performClick()
        composeTestRule.onNodeWithText("Dismiss").performClick()
        composeTestRule.onNodeWithText("Alert title").assertDoesNotExist()
    }

    @Test
    fun reusableAlertIconButtonInvokesOnClickOnConfirm() {
        var clicked = false

        composeTestRule.setContent {
            ReusableAlertIconButton(
                icon = Icons.Filled.Shield,
                onClick = { clicked = true },
                title = "Alert title",
                content = "Alert content",
                dismissText = "Dismiss",
                confirmText = "Confirm"
            )
        }

        composeTestRule.onNodeWithContentDescription("Alert content").assertIsDisplayed().performClick()
        composeTestRule.onNodeWithText("Confirm").assertIsDisplayed().performClick()

        assertTrue(clicked)
    }
}