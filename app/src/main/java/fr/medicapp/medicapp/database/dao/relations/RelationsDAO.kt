package fr.medicapp.medicapp.database.dao.relations

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import fr.medicapp.medicapp.model.relations.RelationInfo
import fr.medicapp.medicapp.model.relations.crossRef.Relations

@Dao
interface RelationsDAO {

    @Transaction
    @Query("SELECT * FROM RelationInfo")
    fun getAll(): List<Relations>

    @Transaction
    @Query("SELECT * FROM RelationInfo WHERE relation_info_id = :id")
    fun getById(id: Long) : Relations

    @Transaction
    @Query("SELECT * FROM RelationInfo WHERE substance = :substance")
    fun getBySubstance(substance: String) : List<Relations>

    @Insert
    fun insert(relation: RelationInfo): Long

    @Insert
    fun insert(relations: List<RelationInfo>): List<Long>

    @Delete
    fun delete(relation: RelationInfo)

}