package fr.medicapp.medicapp.tokenization

class Feature(
    inputIds: List<Int>,
    inputMask: List<Int>,
    segmentIds: List<Int>,
    origTokens: List<String>,
    tokenToOrigMap: Map<Int, Int>
) {
    val inputIds: IntArray
    val inputMask: IntArray
    val segmentIds: IntArray
    val origTokens: List<String>
    val tokenToOrigMap: Map<Int, Int>

    init {
        this.inputIds = inputIds.toIntArray()
        this.inputMask = inputMask.toIntArray()
        this.segmentIds = segmentIds.toIntArray()
        this.origTokens = origTokens
        this.tokenToOrigMap = tokenToOrigMap
    }
}