package 프로그래머스.level1.`160586`.` 대충 만든 자판`

import kotlin.test.assertEquals

fun main() {
    solution(arrayOf("ABCD", "BCEFD"), arrayOf("ABCD", "AABB")).run {
        assertEquals("[9, 4]", contentToString())
    }

    solution(arrayOf("AA"), arrayOf("B")).run {
        assertEquals("[-1]", contentToString())
    }

    solution(arrayOf("AGZ", "BSSS"), arrayOf("ASA", "BGZ")).run {
        assertEquals("[4, 6]", contentToString())
    }
}

fun solution(keyMap: Array<String>, targets: Array<String>): IntArray {
    var answer: IntArray = intArrayOf()

    return answer
}