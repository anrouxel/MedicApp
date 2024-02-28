package fr.medicapp.medicapp.ui.components.modal

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import fr.medicapp.medicapp.reportGenerator.ReportGenerator
import fr.medicapp.medicapp.ui.components.textfield.ReusableOutlinedTextField

@RequiresApi(Build.VERSION_CODES.Q)
@Composable
fun ConfirmReportModal(
    onDismissRequest: () -> Unit,
    onConfirmation: () -> Unit,
    noPrescriptionDialog: MutableState<Boolean>
) {
    var notes by remember { mutableStateOf("") }
    val reportGenerator = ReportGenerator(LocalContext.current)

    Dialog(onDismissRequest = { onDismissRequest() }) {
        // Draw a rectangle shape with rounded corners inside the dialog
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            shape = RoundedCornerShape(16.dp),

            ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(
                    text = "RAPPORT",
                    modifier = Modifier.padding(16.dp),
                    fontSize = 24.sp,
                )
                ReusableOutlinedTextField(
                    modifier = Modifier.padding(16.dp),
                    value = notes,
                    onValueChange = { new -> notes = new },
                    label = "Notes au médecin"
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                ) {
                    TextButton(
                        onClick = { onDismissRequest() },
                        modifier = Modifier.padding(8.dp),
                    ) {
                        Text("Annuler")
                    }
                    TextButton(
                        onClick = {
                            onConfirmation()
                            reportGenerator.report(notes, noPrescriptionDialog)
                        },
                        modifier = Modifier.padding(8.dp),
                    ) {
                        Text("Partager")
                    }
                }
            }
        }
    }
}
