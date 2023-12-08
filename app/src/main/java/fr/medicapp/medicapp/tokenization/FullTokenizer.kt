package fr.medicapp.medicapp.tokenization

import android.util.Log


final class FullTokenizer(
    inputDic: HashMap<String, Int>,
    doLowerCase: Boolean
) {
    private val basicTokenizer: BasicTokenizer
    private val wordpieceTokenizer: WordpieceTokenizer
    private val dic: HashMap<String, Int>

    init {
        this.dic = inputDic
        this.basicTokenizer = BasicTokenizer(doLowerCase)
        this.wordpieceTokenizer = WordpieceTokenizer(inputDic)
    }

    fun tokenize(text: String): MutableList<String> {
        val splitTokens: MutableList<String> = ArrayList()
        for (token in basicTokenizer.tokenize(text)) {
            splitTokens.addAll(wordpieceTokenizer.tokenize(token))
        }
        return splitTokens
    }

    fun convertTokensToIds(tokens: MutableList<String>): MutableList<Int> {
        val outputIds: MutableList<Int> = ArrayList()
        for (token in tokens) {
            outputIds.add(dic[token]!!)
        }
        return outputIds
    }
}