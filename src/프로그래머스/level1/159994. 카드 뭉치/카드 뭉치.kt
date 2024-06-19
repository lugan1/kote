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

/**
 * @param cards1 카드뭉치1
 * @param cards2 카드뭉치2
 * @param goal 카드뭉치 1과 카드뭉치 2를 조합하여 원하는 문자열
 * @return String goal 이 되는지 안되는지에 대한 결과
 *
 * */
fun solution(cards1: Array<String>, cards2: Array<String>, goal: Array<String>): String {
    var cursor1 = 0
    var cursor2 = 0

    goal.forEach {
        if(cursor1 < cards1.size && cards1[cursor1] == it) cursor1++
        else if(cursor2 < cards2.size && cards2[cursor2] == it) cursor2++
        else return "No"
    }

    return "Yes"
}