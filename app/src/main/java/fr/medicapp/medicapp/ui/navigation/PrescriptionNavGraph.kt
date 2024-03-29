package fr.medicapp.medicapp.ui.navigation

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import fr.medicapp.medicapp.database.repositories.prescription.PrescriptionRepository
import fr.medicapp.medicapp.model.prescription.relationship.Prescription
import fr.medicapp.medicapp.ui.components.mozilla.Gecko
import fr.medicapp.medicapp.ui.screen.medication.MedicationDetail
import fr.medicapp.medicapp.ui.screen.prescription.PrescriptionDetail
import fr.medicapp.medicapp.ui.screen.prescription.PrescriptionHome
import fr.medicapp.medicapp.ui.screen.root.RootRoute
import fr.medicapp.medicapp.viewModel.SharedMedicationDetailViewModel
import fr.medicapp.medicapp.viewModel.SharedPrescriptionDetailViewModel

/**
 * Cette fonction construit le graphe de navigation pour les prescriptions.
 *
 * @param navController Le contrôleur de navigation.
 */
@RequiresApi(Build.VERSION_CODES.Q)
fun NavGraphBuilder.prescriptionNavGraph(
    navController: NavHostController,
) {
    /**
     * Définit la navigation pour le graphe de prescription.
     */
    navigation(
        route = RootRoute.RootRoutePrescriptionRoute.route,
        startDestination = PrescriptionRoute.PrescriptionHomeRoute.route
    ) {
        composable(route = PrescriptionRoute.PrescriptionHomeRoute.route) {
            val context = LocalContext.current
            val result: MutableList<Prescription> = mutableListOf()
            Thread {
                result.clear()
                result.addAll(PrescriptionRepository(context).getAll().toMutableList())
            }.start()

            val prescriptions = remember {
                result
            }

            PrescriptionHome(
                prescriptions = prescriptions,
                onAddPrescriptionClick = {
                    navController.navigate(PrescriptionRoute.PrescriptionEditRoute.route)
                },
                onPrescriptionClick = {
                    navController.navigate(
                        PrescriptionRoute.PrescriptionDetailRoute.route.replace(
                            "{id}",
                            it.toString()
                        )
                    )
                }
            )
        }

        composable(route = PrescriptionRoute.PrescriptionDetailRoute.route) {
            val id = it.arguments?.getString("id")?.toLongOrNull()

            val viewModel =
                it.sharedViewModel<SharedPrescriptionDetailViewModel>(navController = navController)

            val context = LocalContext.current

            if (id != null) {
                LaunchedEffect(key1 = id) {
                    viewModel.loadPrescription(context = context, id = id)
                }
            } else {
                navController.popBackStack()
            }

            PrescriptionDetail(
                viewModel = viewModel,
                onRemovePrescriptionClick = {
                    navController.popBackStack()
                },
                onMedicationClick = {
                    navController.navigate(
                        PrescriptionRoute.MedicationDetailRoute.route.replace(
                            "{id}",
                            it.toString()
                        )
                    )
                }
            )
        }

        prescriptionEditNavGraph(
            navController = navController,
        )

        composable(route = PrescriptionRoute.MedicationDetailRoute.route) {
            val id = it.arguments?.getString("id")?.toLongOrNull()

            val viewModel =
                it.sharedViewModel<SharedMedicationDetailViewModel>(navController = navController)

            val context = LocalContext.current

            if (id != null) {
                LaunchedEffect(key1 = id) {
                    viewModel.loadMedication(context = context, id = id)
                }
            } else {
                navController.popBackStack()
            }

            MedicationDetail(
                viewModel = viewModel,
                onMoreInformationClick = {
                    navController.navigate(
                        PrescriptionRoute.GeckoRoute.route.replace(
                            "{url}",
                            viewModel.getMedicationUrl().toString()
                        )
                    )
                }
            )
        }

        composable(route = PrescriptionRoute.GeckoRoute.route) {
            val uri = it.arguments?.getString("url")
            Log.d("test", "prescriptionNavGraph: $uri")
            if (uri == null) {
                navController.popBackStack()
            } else {
                Gecko(uri = uri)
            }
        }
    }
}

/**
 * Cette classe scellée définit les différentes routes pour les prescriptions.
 */
sealed class PrescriptionRoute(val route: String) {
    /**
     * Route pour la page principale des prescriptions.
     */
    object PrescriptionHomeRoute : PrescriptionRoute(route = "prescription_home")

    /**
     * Route pour afficher une prescription spécifique.
     */
    object PrescriptionDetailRoute : PrescriptionRoute(route = "prescription_detail/{id}")

    /**
     * Route pour ajouter une nouvelle prescription.
     */
    object PrescriptionEditRoute : PrescriptionRoute(route = "prescription_edit")

    /**
     * Route pour afficher les détails d'un médicament.
     */
    object MedicationDetailRoute : PrescriptionRoute(route = "medication_detail/{id}")

    /**
     * Route pour afficher une page web.
     */
    object GeckoRoute : PrescriptionRoute(route = "gecko?url={url}")
}
