package fr.medicapp.medicapp.tokenization

final class WordpieceTokenizer(
    vocab: HashMap<String, Int>
) {
    private var dic: HashMap<String, Int>

    private val MAX_INPUTCHARS_PER_WORD = 200

    init {
        this.dic = vocab
    }

    /**
     * Tokenizes a piece of text into its word pieces. This uses a greedy longest-match-first
     * algorithm to perform tokenization using the given vocabulary. For example: input = "unaffable",
     * output = ["un", "##aff", "##able"].
     *
     * @param text: A single token or whitespace separated tokens. This should have already been
     * passed through `BasicTokenizer.
     * @return A list of wordpiece tokens.
     */
    fun tokenize(text: String?): MutableList<String> {
        if (text == null) {
            throw NullPointerException("The input String is null.")
        }
        val outputTokens: MutableList<String> = ArrayList()
        for (token in BasicTokenizer.whitespaceTokenize(text)) {
            if (token.length > MAX_INPUTCHARS_PER_WORD) {
                outputTokens.add(CamemBERT.UNK_TOKEN)
                continue
            }
            var isBad = false // Mark if a word cannot be tokenized into known subwords.
            var start = 0
            var end = token.length
            val subTokens: MutableList<String> = ArrayList()
            while (0 < end) {
                var curSubStr = ""
                 // Longer substring matches first.
                while (start < end) {
                    val subStr =
                        if (start == 0) {
                            "▁${token.substring(start, end)}"
                        } else {
                            token.substring(
                                start,
                                end
                            )
                        }
                    curSubStr = dic[subStr]?.let { subStr } ?: curSubStr
                    if (curSubStr != "") {
                        break
                    }
                    start++
                }

                // The word doesn't contain any known subwords.
                if ("" == curSubStr) {
                    isBad = true
                    break
                }

                // curSubStr is the longeset subword that can be found.
                subTokens.add(0, curSubStr)

                // Proceed to tokenize the resident string.
                end = start
                start = 0
            }
            if (isBad) {
                outputTokens.add(CamemBERT.UNK_TOKEN)
            } else {
                outputTokens.addAll(subTokens)
            }
        }

        return outputTokens
    }
}
