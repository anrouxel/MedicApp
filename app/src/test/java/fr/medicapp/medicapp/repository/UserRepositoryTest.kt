package fr.medicapp.medicapp.repository

import android.database.sqlite.SQLiteException
import fr.medicapp.medicapp.entity.UserEntity
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class UserRepositoryTest {

    lateinit var repository : UserRepository

    @Test
    fun `add once is ok`() {
        // GIVEN
        val user = defaultUser1()
        // WHEN
        repository.add(user)
        val get = repository.getOne(user.id)
        // THEN
        assertEquals(user, get);
    }

    @Test
    fun `add multiple same is failure`()  {
        //GIVEN
        val user1 = defaultUser1()
        val user2 = defaultUser1(lastName = "Jean Pierre")
        //WHEN
        repository.add(user1)
        //THEN
        assertThrows(SQLiteException::class.java) { repository.add(user2) }
    }

    @Test
    fun `add multiple different is ok`()  {
        //GIVEN
        val user1 = defaultUser1()
        val user2 = defaultUser2()
        //WHEN
        repository.add(user1)
        repository.add(user2)
        //THEN
    }

    @Test
    fun getOne() {
    }


    @Test
    fun addAll() {
    }

    @Test
    fun delete() {
    }

    @Test
    fun deleteAll() {
    }

    @Test
    fun update() {
    }

    @Test
    fun updateAll() {
    }

    private fun defaultUser1(
        id: Int = 1,
        lastName: String = "BOUTET",
        firstName: String = "Paul",
        age  : Int = 20,
        email : String = "paul.boutet@medicapp.fr"
    ) = UserEntity(id, lastName,firstName, age,email)

    private fun defaultUser2(
        id: Int = 2,
        lastName: String = "HEYRENDT",
        firstName: String = "Lana",
        age  : Int = 23,
        email : String = "lana.heyrendt@medicapp.fr"
    ) = UserEntity(id, lastName,firstName, age,email)

    private fun defaultUser3(
        id: Int = 3,
        lastName: String = "TEGNY",
        firstName: String = "Quentin",
        age  : Int = 19,
        email : String = "quentin.tegny@medicapp.fr"
    ) = UserEntity(id, lastName,firstName, age,email)
    private fun defaultUser4(
        id: Int = 4,
        lastName: String = "OSSELIN",
        firstName: String = "Arthur",
        age  : Int = 22,
        email : String = "arthur.osselin@medicapp.fr"
    ) = UserEntity(id, lastName,firstName, age,email)
}