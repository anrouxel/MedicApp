package fr.medicapp.medicapp.ui.screen.user

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import fr.medicapp.medicapp.ui.components.button.ReusableOutlinedDatePickerButton
import fr.medicapp.medicapp.ui.components.button.ReusableRadioGroup
import fr.medicapp.medicapp.ui.components.card.ReusableElevatedCard
import fr.medicapp.medicapp.ui.components.screen.Edit
import fr.medicapp.medicapp.ui.components.textfield.ReusableOutlinedTextField
import fr.medicapp.medicapp.viewModel.SharedUserEditViewModel
import java.time.LocalDate

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun UserEditGeneralInformation(
    viewModel: SharedUserEditViewModel,
    onClick: () -> Unit
) {
    val state = viewModel.sharedState.collectAsState()
    Edit(
        title = "Informations utilisateur",
        bottomText = "Suivant",
        onClick = onClick,
        enabled = state.value.lastName.isNotEmpty() && state.value.firstName.isNotEmpty()
                && state.value.birthday < LocalDate.now() && state.value.gender.isNotEmpty()
    ) {
        ReusableElevatedCard {
            Column(
                modifier = Modifier.padding(10.dp)
            ) {
                ReusableOutlinedTextField(
                    value = state.value.lastName,
                    onValueChange = { viewModel.updateLastName(it) },
                    label = "Nom"
                )

                Spacer(modifier = Modifier.padding(10.dp))

                ReusableOutlinedTextField(
                    value = state.value.firstName,
                    onValueChange = { viewModel.updateFirstName(it) },
                    label = "Prénom"
                )

                Spacer(modifier = Modifier.padding(10.dp))

                ReusableOutlinedDatePickerButton(
                    value = state.value.birthday,
                    label = "Date de naissance",
                    onSelected = { viewModel.updateBirthday(it) }
                )

                Spacer(modifier = Modifier.padding(10.dp))

                ReusableRadioGroup(
                    options = listOf("Homme", "Femme"),
                    selectedOption = state.value.gender,
                    label = "Genre de naissance",
                    onClick = { viewModel.updateGender(it) }
                )
            }
        }
    }
}
