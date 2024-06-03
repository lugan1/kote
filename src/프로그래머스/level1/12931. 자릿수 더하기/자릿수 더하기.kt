package 프로그래머스.level1.`12931`.` 자릿수 더하기`

import kotlin.test.assertEquals

fun main() {
    val answer = solution(123)
    println(answer)
    assertEquals(6, answer)
}
/*
* 자연수 N이 주어지면, N의 각 자릿수의 합을 구해서 return
* */
fun solution(n: Int): Int {
    var answer = 0

    answer =  n.toString()
        .map { it.digitToInt() }
        .reduce { acc, i -> acc+ i}

    return answer
}
