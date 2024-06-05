package 프로그래머스.level1.`134240`.` 푸드 파이트 대회`

import kotlin.test.assertEquals

fun main() {
    val answer1 = solution(intArrayOf(1, 3, 4, 6))
    println(answer1)
    assertEquals("1223330333221", answer1)

    val answer2 = solution(intArrayOf(1, 7, 1, 2))
    println(answer2)
    assertEquals("111303111", answer2)
}

/* [1, 3, 4, 6] ->
* 0번 음식 1개 = 무조건 1
* 1번 음식 3개
* 2번 음식 4개
* 3번 음식 6개
* 122333
* 칼로리가 적은 순으로 정렬
* 마지막에는 0
* 주의, 갯수가 짝수여야 한다.
*  */
fun solution(n: IntArray): String {
    val food = StringBuilder()

    n.forEachIndexed { name, count ->
        if(name == 0) return@forEachIndexed
        val single = count / 2
        repeat(single) { food.append(name) }
    }

    val reversed = food.reversed()

    food.append("0")
        .append(reversed)

    return food.toString()
}
