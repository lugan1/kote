package 프로그래머스.level1.`64061`.` 크레인 인형뽑기 게임`

import kotlin.test.assertEquals

fun main() {
    solution(
        arrayOf(
            intArrayOf(0,0,0,0,0),
            intArrayOf(0,0,1,0,3),
            intArrayOf(0,2,5,0,1),
            intArrayOf(4,2,4,4,2),
            intArrayOf(3,5,1,3,1)
        ),
        intArrayOf(1,5,3,5,1,2,1,4)
    ).run { assertEquals(4, this) }
}

/**
 * @param board 크레인 게임화면의 격자 상태
 * @param moves 크레인을 작동시킨 위치가 담긴 배열
 * @return Int 크레인을 모두 움직인 후, 터트려 사라진 인형의 갯수
 * */
fun solution(board: Array<IntArray>, moves: IntArray): Int {
    var answer = 0
    return answer
}
