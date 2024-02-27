package fr.medicapp.medicapp.ui.screen.prescription

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalContext
import fr.medicapp.medicapp.ui.components.screen.Loading
import fr.medicapp.medicapp.viewModel.SharedPrescriptionEditViewModel

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun PrescriptionEditLoading(
    viewModel: SharedPrescriptionEditViewModel,
    onClick: () -> Unit
) {
    val state = viewModel.sharedState.collectAsState().value.firstOrNull()
    val context = LocalContext.current
    val scope = rememberCoroutineScope()

    Loading(
        title = "Chargement de la prescription"
    )
}
