package fr.medicapp.medicapp.model.gson

import fr.medicapp.medicapp.model.relations.Interactions
import fr.medicapp.medicapp.model.relations.RelationInfo
import fr.medicapp.medicapp.model.relations.crossRef.Relations

data class RelationGSON(
    var substance: String = "",
    var com: String = "",
    var interactions: List<Interactions> = emptyList()
) {
    fun toRelations(): Relations {
        return Relations(
            relationInfo = RelationInfo(
                substance = substance,
                com = com
            ),
            interactions = interactions
        )
    }
}