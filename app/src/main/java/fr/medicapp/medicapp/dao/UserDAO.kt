package fr.medicapp.medicapp.dao

class UserDAO(id:Int, lastName: String, firstName: String, age: Int, email: String, mdp: String) {

    private val id: Int
    private var lastName: String
    private var firstName: String
    private var age: Int
    private var email: String
    private var mdp: String

    init {
        this.id = id
        this.lastName = lastName
        this.firstName = firstName
        this.age = age
        this.email = email
        this.mdp = mdp
    }

    /**
     * Getters ensemble
     * Function name format: get{Variable}(): Type of {Variable}
     * */

    fun getId(): Int{
        return id
    }

    fun getLastName(): String{
        return lastName
    }

    fun getFirstName(): String{
        return firstName
    }

    fun getAge(): Int{
        return age
    }

    fun getEmail(): String{
        return email
    }

    fun getMdp(): String{
        return mdp
    }

    /**
     * Setters ensemble
     * Function name format: set{Variable}(new{Variable}: Type of {Variable})
     * */

    fun setLastName(newLastName: String){
        this.lastName = newLastName
    }

    fun setFirstName(newFirstName: String){
        this.firstName = newFirstName
    }

    fun setAge(newAge: Int){
        this.age = newAge
    }

    fun setEmail(newEmail: String){
        this.email = newEmail
    }

    fun setMdp(newMdp: String){
        this.mdp = newMdp
    }
}