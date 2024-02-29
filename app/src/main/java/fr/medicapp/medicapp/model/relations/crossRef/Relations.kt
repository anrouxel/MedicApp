package fr.medicapp.medicapp.model.relations.crossRef

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Relation
import fr.medicapp.medicapp.model.relations.Interactions
import fr.medicapp.medicapp.model.relations.RelationInfo

@Entity
data class Relations(
    @Embedded
    val relationInfo: RelationInfo = RelationInfo(),

    @Relation(
        parentColumn = "relation_info_id",
        entityColumn = "interaction_id"
    )
    val interactions: List<Interactions> = emptyList()
)