package fr.medicapp.medicapp.ui.components.list

import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import java.time.LocalDate

@Composable
fun ListMedication(modifier : Modifier = Modifier, selectedDate : LocalDate) {

    LazyVerticalGrid(columns = GridCells.Fixed(1)){

    }

}