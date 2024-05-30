package 프로그래머스.level1.`134240`.` 푸드 파이트 대회`

import kotlin.test.assertEquals

fun main() {
    val answer1 = solution(listOf(1, 3, 4, 6))
    println(answer1)
    assertEquals("1223330333221", answer1)

    val answer2 = solution(listOf(1, 7, 1, 2))
    println(answer2)
    assertEquals("111303111", answer2)
}

// 1, 3, 4, 6 -> 0번음식 1개 , 1번음식 3개, 2번음식 4개, 3번음식 6개
fun solution(n: List<Int>): String {
    var answer = ""

    fun pre(n: List<Int>): String {
        return ""
    }

    fun post(n: List<Int>): String {
        return ""
    }

    answer = pre(n) + "0" + post(n)

    return answer
}
