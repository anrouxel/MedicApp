package fr.medicapp.medicapp.model

class Prescription {
    private var _instructions: OptionInstruction? = null

    private var _doctor: OptionDoctor? = null

    fun getInstructions(): OptionInstruction? {
        return _instructions
    }

    fun setInstructions(instructions: OptionInstruction) {
        _instructions = instructions
    }

    fun getDoctor(): OptionDoctor? {
        return _doctor
    }

    fun setDoctor(doctor: OptionDoctor) {
        _doctor = doctor
    }
}
