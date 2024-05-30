package 프로그래머스.level1.`12931`.` 자릿수 더하기`

import kotlin.test.assertEquals

fun main() {
    val answer = solution(123)
    println(answer)
    assertEquals(6, answer)
}

fun solution(n: Int): Int {
    var answer = 0

    answer = n.toString()
        .map { c -> c.digitToInt() }
        .reduce { acc, i -> acc + i }

    return answer
}