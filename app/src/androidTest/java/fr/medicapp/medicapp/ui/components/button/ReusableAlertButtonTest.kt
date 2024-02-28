package fr.medicapp.medicapp.ui.components.button


import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import org.junit.Rule
import org.junit.Test
import org.junit.Assert.*


class ReusableAlertButtonTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun reusableAlertButtonOpensAlertModalOnClick() {
        composeTestRule.setContent {
            ReusableAlertButton(
                text = "Click me",
                onClick = {},
                title = "Alert title",
                content = "Alert content",
                dismissText = "Dismiss",
                confirmText = "Confirm"
            )
        }

        composeTestRule.onNodeWithText("Click me").performClick()
        composeTestRule.onNodeWithText("Alert title").assertIsDisplayed()
        composeTestRule.onNodeWithText("Alert content").assertIsDisplayed()
    }

    @Test
    fun reusableAlertButtonClosesAlertModalOnDismiss() {
        composeTestRule.setContent {
            ReusableAlertButton(
                text = "Click me",
                onClick = {},
                title = "Alert title",
                content = "Alert content",
                dismissText = "Dismiss",
                confirmText = "Confirm"
            )
        }

        composeTestRule.onNodeWithText("Click me").performClick()
        composeTestRule.onNodeWithText("Dismiss").performClick()
        composeTestRule.onNodeWithText("Alert title").assertDoesNotExist()
    }

    @Test
    fun reusableAlertButtonInvokesOnClickOnConfirm() {
        var clicked = false

        composeTestRule.setContent {
            ReusableAlertButton(
                text = "Click me",
                onClick = { clicked = true },
                title = "Alert title",
                content = "Alert content",
                dismissText = "Dismiss",
                confirmText = "Confirm"
            )
        }

        composeTestRule.onNodeWithText("Click me").assertIsDisplayed().performClick()
        composeTestRule.onNodeWithText("Confirm").assertIsDisplayed().performClick()

        assertTrue(clicked)
    }


}