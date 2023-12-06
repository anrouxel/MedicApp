package fr.medicapp.medicapp.tokenization

import android.util.Log
import java.util.Collections

class FeatureConverter(
    inputDic: HashMap<String, Int>,
    doLowerCase: Boolean,
    maxQueryLen: Int,
    maxSeqLen: Int
) {
    private val tokenizer: FullTokenizer
    private val maxQueryLen: Int
    private val maxSeqLen: Int

    init {
        tokenizer = FullTokenizer(inputDic, doLowerCase)
        this.maxQueryLen = maxQueryLen
        this.maxSeqLen = maxSeqLen
    }

    fun convert(query: String, context: String): Feature {
        var queryTokens: List<String> = tokenizer.tokenize(query)
        if (queryTokens.size > maxQueryLen) {
            queryTokens = queryTokens.subList(0, maxQueryLen)
        }

        val origTokens = mutableListOf(
            *context.trim { it <= ' ' }
                .split("\\s+".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
        )
        val tokenToOrigIndex: MutableList<Int> = ArrayList()
        var allDocTokens: MutableList<String> = ArrayList()
        for (i in origTokens.indices) {
            val token = origTokens[i]
            val subTokens = tokenizer.tokenize(token)
            for (subToken in subTokens) {
                tokenToOrigIndex.add(i)
                allDocTokens.add(subToken)
            }
        }

        // -3 accounts for [CLS], [SEP] and [SEP].
        val maxContextLen = maxSeqLen - queryTokens.size - 3
        if (allDocTokens.size > maxContextLen) {
            allDocTokens = allDocTokens.subList(0, maxContextLen)
        }
        val tokens: MutableList<String> = ArrayList()
        val segmentIds: MutableList<Int> = ArrayList()

        // Map token index to original index (in feature.origTokens).
        val tokenToOrigMap: MutableMap<Int, Int> = HashMap()

        // Start of generating the features.
        tokens.add(CamemBERT.CLS_TOKEN)
        segmentIds.add(0)

        // For query input.
        for (queryToken in queryTokens) {
            tokens.add(queryToken)
            segmentIds.add(0)
        }

        // For Separation.
        tokens.add(CamemBERT.SEP_TOKEN)
        segmentIds.add(0)

        // For Text Input.
        for (i in allDocTokens.indices) {
            val docToken = allDocTokens[i]
            tokens.add(docToken)
            segmentIds.add(1)
            tokenToOrigMap[tokens.size] = tokenToOrigIndex[i]
        }

        // For ending mark.
        tokens.add(CamemBERT.SEP_TOKEN)
        segmentIds.add(1)
        val inputIds = tokenizer.convertTokensToIds(tokens)
        val inputMask: MutableList<Int> = ArrayList(
            Collections.nCopies(
                inputIds.size,
                1
            )
        )
        while (inputIds.size < maxSeqLen) {
            inputIds.add(0)
            inputMask.add(0)
            segmentIds.add(0)
        }

        // Log le texte tokenizé avec une boucle for
        for (i in 0 until tokens.size) {
            Log.v("FeatureConverter", "tokens[$i] = ${tokens[i]}")
        }

        return Feature(inputIds, inputMask, segmentIds, origTokens, tokenToOrigMap)
    }
}
