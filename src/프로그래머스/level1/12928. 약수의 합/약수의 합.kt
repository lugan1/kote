package 프로그래머스.level1.`12928`.` 약수의 합`

import kotlin.test.assertEquals

fun main() {
    val answer = solution(12)
    println(answer)
    assertEquals(28, answer)
}

fun solution(n: Int): Int {
    return (1..n)
        .filter { n % it == 0 }
        .reduce { acc, i -> acc + i }
}
