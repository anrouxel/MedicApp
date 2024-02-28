package fr.medicapp.medicapp.utils

import java.util.Arrays


object JaroWinkler {
    fun jaroWinklerDistance(string1: String, string2: String): Double {
        var string1 = string1
        var string2 = string2
        var len1 = string1.length
        var len2 = string2.length
        if (len1 < len2) {
            val s = string1
            string1 = string2
            string2 = s
            val tmp = len1
            len1 = len2
            len2 = tmp
        }
        if (len2 == 0) return if (len1 == 0) 0.0 else 1.0
        val delta = Math.max(1, len1 / 2) - 1
        val flag = BooleanArray(len2)
        Arrays.fill(flag, false)
        val ch1Match = CharArray(len1)
        var matches = 0
        for (i in 0 until len1) {
            val ch1 = string1[i]
            for (j in 0 until len2) {
                val ch2 = string2[j]
                if ((j <= i + delta) && (j + delta >= i) && (ch1 == ch2) && !flag[j]) {
                    flag[j] = true
                    ch1Match[matches++] = ch1
                    break
                }
            }
        }
        if (matches == 0) return 1.0
        var transpositions = 0
        run {
            var i: Int = 0
            var j: Int = 0
            while (j < len2) {
                if (flag.get(j)) {
                    if (string2.get(j) != ch1Match.get(i)) ++transpositions
                    ++i
                }
                ++j
            }
        }
        val m = matches.toDouble()
        val jaro = ((m / len1) + (m / len2) + ((m - transpositions / 2.0) / m)) / 3.0
        var commonPrefix = 0
        len2 = Math.min(4, len2)
        for (i in 0 until len2) {
            if (string1[i] == string2[i]) ++commonPrefix
        }
        return 1.0 - (jaro + commonPrefix * 0.1 * (1.0 - jaro))
    }
}