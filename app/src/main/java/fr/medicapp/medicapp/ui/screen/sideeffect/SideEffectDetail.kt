package fr.medicapp.medicapp.ui.screen.sideeffect

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.platform.LocalContext
import fr.medicapp.medicapp.ui.components.screen.Detail
import fr.medicapp.medicapp.viewModel.SharedSideEffectDetailViewModel

@Composable
fun SideEffectDetail(
    viewModel: SharedSideEffectDetailViewModel,
) {
    val state = viewModel.sharedState.collectAsState()
    val context = LocalContext.current

    Detail(
        title = "Détail de l'effet secondaire",
    ) {
        Column {
            /*ReusableElevatedCard {
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

            ReusableAlertButton(
                text = "Supprimer",
                onClick = {
                    viewModel.removeSideEffect(context)
                },
                title = "Suppression",
                content = "Voulez-vous vraiment supprimer cet effet secondaire ?",
                dismissText = "Annuler",
                confirmText = "Supprimer"
            )*/
        }
    }
}
