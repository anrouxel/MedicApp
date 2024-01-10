package fr.medicapp.medicapp.model

import androidx.compose.runtime.mutableStateListOf
import java.time.LocalDate

data class SideEffect(
    var id: String = "",
    var medicament: String = "",
    var date: LocalDate? = null,
    var hour: Int? = null,
    var minute: Int? = null,
    var effetsConstates: MutableList<String> = mutableStateListOf(),
    var description: String = ""
)
