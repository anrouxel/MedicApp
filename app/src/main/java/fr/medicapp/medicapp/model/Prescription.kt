package fr.medicapp.medicapp.model

class Prescription {
    private var _instructions: OptionInstruction? = null

    private var _doctor: Doctor? = null

    fun getInstructions(): OptionInstruction? {
        return _instructions
    }

    fun setInstructions(instructions: OptionInstruction) {
        _instructions = instructions
    }

    fun getDoctor(): Doctor? {
        return _doctor
    }

    fun setDoctor(doctor: Doctor) {
        _doctor = doctor
    }
}
