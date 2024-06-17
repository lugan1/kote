package 프로그래머스.level1.`64061`.` 크레인 인형뽑기 게임`

import java.util.*
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
 * @param board 크레인 게임화면의 격자 상태 (5x5 ~ 30x30)
 * @param moves 크레인을 작동시킨 위치가 담긴 배열 ( 1 ~ 1000 )
 * @return Int 크레인을 모두 움직인 후, 터트려 사라진 인형의 갯수
 * */
fun solution(board: Array<IntArray>, moves: IntArray): Int {
    var answer = 0

    //todo: 전체 순회를 하며, 해당 index 의 자리가 0이면 다음 순회로 넘어간다.
    //todo: 숫자가 존재하면 해당 index 는 0 으로 변환되며, 숫자를 스택에 넣는다.
    //todo: 스택에 넣기전에, 똑같은 숫자가 header 에 존재하면 넣지않고, header를 제거한다. 동시에 카운터를 + 1 한다.

    val stack: Stack<Int> = Stack()
    var score: Int = 0

    moves.forEach { move ->
        val index = move-1
        for(array in board) {
            val dol = array[index]
            if(dol == 0) continue

            array[index] = 0

            if(stack.isEmpty().not() && stack.peek() == dol) {
                stack.pop()
                score++
            }
            else {
                stack.push(dol)
            }

            break
        }
    }

    answer = score * 2

    return answer
}
