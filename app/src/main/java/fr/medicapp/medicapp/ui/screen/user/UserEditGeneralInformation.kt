package fr.medicapp.medicapp.ui.screen.user

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import fr.medicapp.medicapp.ui.components.button.ReusableOutlinedDatePickerButton
import fr.medicapp.medicapp.ui.components.button.ReusableRadioGroup
import fr.medicapp.medicapp.ui.components.card.ReusableElevatedCard
import fr.medicapp.medicapp.ui.components.screen.Edit
import fr.medicapp.medicapp.ui.components.textfield.ReusableOutlinedTextField
import fr.medicapp.medicapp.ui.theme.EUPurpleColorShema
import fr.medicapp.medicapp.ui.theme.MedicAppTheme
import java.time.LocalDate

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun UserEditGeneralInformation(
    //add a viewModel for User
    onClick: () -> Unit
) {
    Edit(
        title = "Information utilisateur",
        bottomText = "Suivant",
        onClick = onClick,

        ) {
        val genderState = remember { mutableStateOf("") }
        ReusableElevatedCard {
            Column(
                modifier = Modifier.padding(10.dp)
            ) {
                ReusableOutlinedTextField(
                    value = "",
                    onValueChange = {},
                    label = "Nom"
                )

                Spacer(modifier = Modifier.padding(10.dp))

                ReusableOutlinedTextField(
                    value = "",
                    onValueChange = {},
                    label = "Prénom"
                )

                Spacer(modifier = Modifier.padding(10.dp))

                ReusableOutlinedDatePickerButton(
                    value = LocalDate.now(),
                    label = "Date de naissance",
                    onSelected = {}
                )

                Spacer(modifier = Modifier.padding(10.dp))

                ReusableRadioGroup(
                    options = listOf("Homme", "Femme"),
                    selectedOption = genderState,
                    label = "Genre de naissance"
                )
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview
@Composable
fun UserEditInformationPreview() {
    MedicAppTheme(
        darkTheme = false,
        dynamicColor = false,
        theme = EUPurpleColorShema
    ) {
        UserEditGeneralInformation(onClick = {})
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview
@Composable
fun UserEditInformationDarkPreview() {
    MedicAppTheme(
        darkTheme = true,
        dynamicColor = false,
        theme = EUPurpleColorShema
    ) {
        UserEditGeneralInformation(onClick = {})
    }
}