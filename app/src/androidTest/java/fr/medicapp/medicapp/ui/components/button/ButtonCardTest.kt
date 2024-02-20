package fr.medicapp.medicapp.ui.components.button

import androidx.activity.ComponentActivity
import androidx.compose.material3.Text
import androidx.compose.ui.test.hasClickAction
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.performClick
import org.junit.Rule
import org.junit.Test

class ButtonCardTest {
    @get:Rule
    val rule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun testDisplay() {
        rule.setContent { ButtonCard(onClick = {}, content = { Text("Test") }) }
        val buttonDisplay = hasText("Test") and hasClickAction()
        rule.onNode(buttonDisplay).assertExists()
    }

    @Test
    fun testOnClick() {
        var testVar = 0
        val testFunction: () -> Unit = {
            testVar++
        }
        rule.setContent { ButtonCard(onClick = testFunction) {} }
        val buttonDisplay = hasClickAction()
        rule.onNode(buttonDisplay).performClick()
        rule.onNode(buttonDisplay).performClick()
        assert(testVar == 2)
    }
}