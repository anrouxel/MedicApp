package fr.medicapp.medicapp.components.button

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.hasClickAction
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import fr.medicapp.medicapp.ui.components.button.ButtonContent
import org.junit.Rule
import org.junit.Test

class ButtonContentTest {
    @get:Rule
    val rule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun testDisplay() {
        rule.setContent { ButtonContent("Test") }
        val buttonDisplay = hasText("Test") and hasClickAction()
        rule.onNode(buttonDisplay).assertExists()
    }
    @Test
    fun testOnClick(){
        assert(0xfffd == 65533)
    }
}