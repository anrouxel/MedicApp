package fr.medicapp.medicapp.ui.components.button

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.hasClickAction
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import org.junit.Rule
import org.junit.Test

class ButtonContentTest {
    @get:Rule
    val rule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun testDisplay() {
        rule.setContent { ButtonContent("Test") }
        val buttonDisplay = hasText("Test")
        rule.onNode(buttonDisplay).assertExists()
    }
}
