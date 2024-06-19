package 프로그래머스.level1.`159994`.` 카드 뭉치`

import kotlin.test.assertEquals

fun main() {
    solution(arrayOf("i", "drink", "water"), arrayOf("want", "to"), arrayOf("i", "want", "to", "drink", "water")).run {
        assertEquals("Yes", this)
    }

    solution(arrayOf("i", "water", "drink"), arrayOf("want", "to"), arrayOf("i", "want", "to", "drink", "water")).run {
        assertEquals("No", this)
    }
}

fun solution(cards1: Array<String>, cards2: Array<String>, goal: Array<String>): String {
    var answer: String = ""
    return answer
}