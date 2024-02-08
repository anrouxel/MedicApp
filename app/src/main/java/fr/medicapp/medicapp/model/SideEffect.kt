package fr.medicapp.medicapp.model

import fr.medicapp.medicapp.database.LocalDateConverter
import io.objectbox.annotation.Convert
import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id
import io.objectbox.relation.ToOne
import java.time.LocalDate

data class SideEffect(
    val id: Long = 0L,

    var date: LocalDate? = null,

    var hour: Int= 0,

    var minute: Int= 0,

    var effetsConstates: MutableList<String> = mutableListOf(),

    var description: String = "",

    var treatment: Treatment? = null
)
