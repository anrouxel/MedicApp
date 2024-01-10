package fr.medicapp.medicapp.repository

import android.database.sqlite.SQLiteException
import fr.medicapp.medicapp.MainActivity
import fr.medicapp.medicapp.database.AppDatabase
import fr.medicapp.medicapp.entity.UserEntity
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class UserRepositoryTest {

    lateinit var repository : UserRepository

    @BeforeEach
    fun setUp() {
        repository = UserRepository(AppDatabase.getInstance(MainActivity()).userDAO())
        repository.deleteAll()
    }
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
        //THEN
        assertDoesNotThrow(){repository.add(user2) }
    }

    @Test
    fun getOne() {
        // GIVEN
        val user = defaultUser1()
        // WHEN
        repository.add(user)
        val get = repository.getOne(user.id)
        // THEN
        assertEquals(user, get);
    }

    @Test
    fun getAll() {
        //GIVEN
        val user1 = defaultUser1()
        val user2 = defaultUser2()
        val user3 = defaultUser3()
        val user4 = defaultUser4()
        //WHEN
        repository.addAll(user1,user2,user3,user4)
        //THEN
        assertEquals(repository.getAll(), listOf( user1,user2,user3,user4))
    }

    @Test
    fun addAll() {
        //GIVEN
        val user1 = defaultUser1()
        val user2 = defaultUser2()
        //WHEN
        var a = repository.addAll(user1,user2)
        println(){a}
        //THEN
        assertEquals(repository.getAll(), listOf( user1,user2))
    }

    @Test
    fun delete() {
        val user1 = defaultUser1()
        //WHEN
        repository.add(user1)
        repository.delete(user1)
        //THEN
        assertNull(repository.getOne(user1.id))
    }

    @Test
    fun deleteAll() {
        //GIVEN
        val user1 = defaultUser1()
        val user2 = defaultUser2()
        val user3 = defaultUser3()
        val user4 = defaultUser4()
        //WHEN
        repository.addAll(user1,user2,user3,user4)
        repository.deleteAll(user1,user2,user3)
        //THEN
        assertEquals(repository.getAll(), listOf( user1))
    }

    @Test
    fun update() {
        //GIVEN
        val user1 = defaultUser1()
        //WHEN
        repository.add(user1)
        var newName = "Jean Claude"
        user1.firstName = newName
        repository.update(user1)
        //THEN
        val res = repository.getOne(user1.id)
        assertEquals(res.firstName , newName)
    }

    @Test
    fun updateAll() {
        //GIVEN
        val user1 = defaultUser1()
        val user2 = defaultUser2()
        val user3 = defaultUser3()
        val user4 = defaultUser4()

        //WHEN
        repository.addAll(user1,user2,user3,user4)
        user1.lastName = "Omelette"
        user2.firstName = "Sonic"
        repository.updateAll(user1,user2,user3)

        //THEN
        assertEquals(repository.getAll(), listOf( user1,user2,user3))
    }

    private fun defaultUser1(
        id: String = "1",
        lastName: String = "BOUTET",
        firstName: String = "Paul",
        age  : Int = 20,
        email : String = "paul.boutet@medicapp.fr"
    ) = UserEntity(id, lastName,firstName, age,email)

    private fun defaultUser2(
        id: String = "2",
        lastName: String = "HEYRENDT",
        firstName: String = "Lana",
        age  : Int = 23,
        email : String = "lana.heyrendt@medicapp.fr"
    ) = UserEntity(id, lastName,firstName, age,email)

    private fun defaultUser3(
        id: String = "3",
        lastName: String = "TEGNY",
        firstName: String = "Quentin",
        age  : Int = 19,
        email : String = "quentin.tegny@medicapp.fr"
    ) = UserEntity(id, lastName,firstName, age,email)
    private fun defaultUser4(
        id: String = "4",
        lastName: String = "OSSELIN",
        firstName: String = "Arthur",
        age  : Int = 22,
        email : String = "arthur.osselin@medicapp.fr"
    ) = UserEntity(id, lastName,firstName, age,email)
}
