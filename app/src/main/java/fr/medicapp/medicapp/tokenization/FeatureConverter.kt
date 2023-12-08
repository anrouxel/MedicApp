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

    fun convert(context: String): Feature {
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
        allDocTokens.forEach { Log.d("CamemBERTToken", it) }

        // -3 accounts for [CLS], [SEP] and [SEP].
        val maxContextLen = maxSeqLen - 3
        if (allDocTokens.size > maxContextLen) {
            allDocTokens = allDocTokens.subList(0, maxContextLen)
        }

        val tokens: MutableList<String> = ArrayList()
        val segmentIds: MutableList<Int> = ArrayList()

        // Map token index to original index (in feature.origTokens).
        val tokenToOrigMap: MutableMap<Int, Int> = HashMap()

        tokens.add(CamemBERT.CLS_TOKEN)
        segmentIds.add(0)

        // For Text Input.
        for (i in allDocTokens.indices) {
            val docToken = allDocTokens[i]
            tokens.add(docToken)
            segmentIds.add(0)
            tokenToOrigMap[tokens.size - 1] = tokenToOrigIndex[i]
        }

        tokens.add(CamemBERT.SEP_TOKEN)
        segmentIds.add(0)

        while (tokens.size < maxSeqLen) {
            tokens.add(CamemBERT.PAD_TOKEN)
            segmentIds.add(0)
        }

        val inputIds = tokenizer.convertTokensToIds(tokens)
        val inputMask: MutableList<Int> = ArrayList(
            Collections.nCopies(
                inputIds.size,
                1
            )
        )

        return Feature(inputIds, inputMask, segmentIds, origTokens, tokenToOrigMap)
    }

    companion object {
        fun align_word_ids(feature: Feature, labelAllTokens: Boolean = false): MutableList<Int> {
            val inputIds = feature.inputIds
            val tokenToOrigMap = feature.tokenToOrigMap
            var previousWordIdx: Int? = null
            val labelIds: MutableList<Int> = mutableListOf()

            for ((index, _) in inputIds.withIndex()) {
                val wordIdx = tokenToOrigMap[index]
                if (tokenToOrigMap[index] == null) {
                    labelIds.add(-100)
                } else if (wordIdx != previousWordIdx) {
                    try {
                        labelIds.add(1)
                    } catch (e: Exception) {
                        labelIds.add(-100)
                    }
                } else {
                    try {
                        labelIds.add(if (labelAllTokens) 1 else -100)
                    } catch (e: Exception) {
                        labelIds.add(-100)
                    }
                }
                previousWordIdx = wordIdx
            }
            return labelIds
        }
    }
}
