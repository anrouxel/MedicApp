package fr.medicapp.medicapp.ui.prescription

import android.app.TimePickerDialog
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TimePicker
import androidx.compose.material3.TimePickerColors
import androidx.compose.material3.TimePickerDefaults
import androidx.compose.material3.TimePickerState
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import fr.medicapp.medicapp.ui.theme.EUPurple10
import fr.medicapp.medicapp.ui.theme.EUPurple100
import fr.medicapp.medicapp.ui.theme.EUPurple20
import fr.medicapp.medicapp.ui.theme.EUPurple40
import fr.medicapp.medicapp.ui.theme.EUPurple80

/**
 * Cette fonction affiche une boîte de dialogue de sélection de l'heure avec des options spécifiques.
 *
 * @param state L'état du sélecteur de temps.
 * @param clockBackgroundColor La couleur de fond de l'horloge.
 * @param selectorColor La couleur du marqueur pour changer l'heure.
 * @param timeSelectorSelectedContainerColor La couleur du fond de l'objet sélectionné.
 * @param timeSelectorUnselectedContainerColor La couleur du fond de l'objet non sélectionné.
 * @param onDismissRequest La fonction à exécuter lorsque l'utilisateur ferme la boîte de dialogue.
 * @param onConfirm La fonction à exécuter lorsque l'utilisateur confirme la sélection.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TimePickerModal(
    state: TimePickerState,
    clockBackgroundColor : Color = EUPurple20,
    selectorColor : Color = EUPurple100,
    timeSelectorSelectedContainerColor : Color = EUPurple40,
    timeSelectorUnselectedContainerColor : Color = EUPurple20,
    onDismissRequest: () -> Unit = {},
    onConfirm: () -> Unit = {},
) {
    AlertDialog(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = MaterialTheme.colorScheme.surface,
                shape = RoundedCornerShape(size = 12.dp)
            ),
        onDismissRequest = onDismissRequest,
    ) {
        Column(
            modifier = Modifier
                .background(
                    color = Color.LightGray.copy(alpha = 0.3f)
                )
                .padding(top = 28.dp, start = 20.dp, end = 20.dp, bottom = 12.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // time picker
            TimePicker(
                state = state,
                colors = TimePickerDefaults.colors(
                    clockDialColor = clockBackgroundColor, // Couleur de fond de l'horloge
                    selectorColor = selectorColor, // Couleur du marqueur pour changer l'heure
                    timeSelectorSelectedContainerColor = timeSelectorSelectedContainerColor, // Couleur du fond de l'objet sélectionné
                    timeSelectorSelectedContentColor = Color.Black, // Couleur du texte de l'objet sélectionné
                    timeSelectorUnselectedContainerColor = timeSelectorUnselectedContainerColor, // Couleur du fond de l'objet non sélectionné
                    timeSelectorUnselectedContentColor = Color.Black // Couleur du texte de l'objet non sélectionné
                )
            )

            // buttons
            Row(
                modifier = Modifier
                    .padding(top = 12.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                // dismiss button
                TextButton(onClick = onDismissRequest) {
                    Text(text = "Annuler")
                }

                // confirm button
                TextButton(
                    onClick = onConfirm
                ) {
                    Text(text = "OK")
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true)
@Composable
private fun TimePickerModalPreview() {
    TimePickerModal(
        TimePickerState(10, 20, true),

    )
}