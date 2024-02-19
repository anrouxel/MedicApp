package fr.medicapp.medicapp.ui.screen.sideeffect

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import fr.medicapp.medicapp.ui.components.card.ReusableElevatedCard
import fr.medicapp.medicapp.ui.components.modal.DeleteModal
import fr.medicapp.medicapp.ui.components.screen.Detail
import fr.medicapp.medicapp.ui.components.text.ReusableTextMediumCard
import fr.medicapp.medicapp.viewModel.SharedSideEffectDetailViewModel

@Composable
fun SideEffectDetail(
    viewModel: SharedSideEffectDetailViewModel,
    onRemoveSideEffectClick: () -> Unit,
) {
    val state = viewModel.sharedState.collectAsState()
    val context = LocalContext.current

    Detail(
        title = "Détail de l'effet secondaire",
    ) {
        Column {
            ReusableElevatedCard {
                Column(
                    modifier = Modifier.padding(10.dp)
                ) {
                    state.value.prescription?.let {
                        ReusableTextMediumCard(
                            value = "Prescription : ${it.treatment.medication}"
                        )
                    }

                    state.value.date?.let {
                        Spacer(modifier = Modifier.padding(10.dp))

                        ReusableTextMediumCard(
                            value = "Date : ${it}"
                        )
                    }

                    state.value.description.let {
                        Spacer(modifier = Modifier.padding(10.dp))

                        ReusableTextMediumCard(
                            value = "Description : ${it}"
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.padding(10.dp))

            ReusableButton(text = "Supprimer", onClick = {
                showDeleteSideEffectModal.value = true
            })
            if (showDeleteSideEffectModal.value) {
                DeleteModal(
                    title = "Supprimer cet effet secondaire",
                    onDismissRequest = { showDeleteSideEffectModal.value = false },
                    onConfirm = {
                        removeSideEffect()
                        showDeleteSideEffectModal.value = false
                    }
                )
            }

        }
    }
}
