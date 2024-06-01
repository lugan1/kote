package 프로그래머스.level1.`132267`.` 콜라 문제`

import kotlin.test.assertEquals

fun main() {
    val answer = solution(2, 1, 20)
    println(answer)
    assertEquals(19, answer)

    println("========")

    val answer2 = solution(3, 1, 20)
    println(answer2)
    assertEquals(9, answer2)
}

/*
* a : 요청 빈병 갯수 2
* b : 받는 빈병 갯수 1
* n : 현재 입력 갯수 20
* */
tailrec fun solution(a: Int, b: Int, n: Int, acc: Int = 0): Int {
    var answer: Int = 0
    val receive = (n / a) * b
    val left = receive + (n % a)
    answer = acc + receive
    println("받은 병: $receive 남은 병: $left 총 받은병: $acc")
    if(n < a) return answer
    return solution(a, b, left, answer)
}
