package fr.medicapp.medicapp.dao

import fr.medicapp.medicapp.modelDAO.Medication

class UserDAO(id:Int, lastName: String, firstName: String, age: Int, email: String, mdp: String) {

    private val id: Int
    private var lastName: String
    private var firstName: String
    private var age: Int
    private var email: String
    private var mdp: String
    private var medicationMap: MutableMap<String,Medication>

    init {
        this.id = id
        this.lastName = lastName
        this.firstName = firstName
        this.age = age
        this.email = email
        this.mdp = mdp
        this.medicationMap = mutableMapOf()
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

    fun getMedicationMap(): MutableMap<String,Medication>{
        return medicationMap
    }

    fun getMedication(cisCode: String): Medication?{
        return medicationMap[cisCode]
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

    fun addMedication(newMedication: Medication){
        this.medicationMap[newMedication.cisCode] = newMedication
    }

    fun deleteMedication(deletedMedication: String){
        this.medicationMap.remove(deletedMedication)
    }
}