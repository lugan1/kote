package 프로그래머스.level1.`67256`.` 키패드 누르기`

import kotlin.test.assertEquals
import kotlin.math.*

fun main() {
    solution(intArrayOf(1,3,4,5,8,2,1,4,5,9,5), "right").run {
        assertEquals("LRLLLRLLRRL", this)
    }

    solution(intArrayOf(7,0,8,2,8,3,1,5,7,6,2), "left").run {
        assertEquals("LRLLRRLLLRR", this)
    }

    solution(intArrayOf(1,2,3,4,5,6,7,8,9,0), "right").run {
        assertEquals("LLRLLRLLRL", this)
    }
}

/**
 * 1,4,7 -> 왼손
 * 3,6,9 -> 오른손
 * 2,5,8,0 -> 더 가까운 손가락 , 같은 경우 오른손잡이는 오른손, 왼손잡이는 왼손
 * @param numbers 순서대로 누를 번호
 * @param hands 왼손잡이인지, 오른손잡이인지
 * @return String 각 번호를 누른 손가락이 왼손인지 오른손인지 나타내는 문자열
 * */
fun solution(numbers: IntArray, hand: String): String {
    data class Loc(val x: Int, val y: Int)

    var left: Char = '*'
    var right: Char = '#'

    // 각 패드별 좌표를 생성한다.
    val keyMap: Map<Char, Loc> = mapOf(
        '1' to Loc(0, 0), '2' to Loc(1, 0), '3' to Loc(2, 0),
        '4' to Loc(0, 1), '5' to Loc(1, 1), '6' to Loc(2, 1),
        '7' to Loc(0, 2), '8' to Loc(1, 2), '9' to Loc(2, 2),
        '*' to Loc(0, 3), '0' to Loc(1, 3), '#' to Loc(2, 3),
    )

    // 맨하탄 거리 측정법으로 거리를 측정한다.
    val manhattan = ma@{ input: Char ->
        val inputLoc = keyMap[input]!!
        val leftLoc = keyMap[left]!!
        val rightLoc = keyMap[right]!!

        val leftDist = abs(leftLoc.x-inputLoc.x) + abs(leftLoc.y-inputLoc.y)
        val rightDist = abs(rightLoc.x-inputLoc.x) + abs(rightLoc.y-inputLoc.y)

        if(leftDist == rightDist){
            return@ma if(hand == "left") "L" else "R"
        }

        if(min(leftDist, rightDist) == leftDist) "L" else "R"
    }

    val moveToHands = { number: Int ->
        when(val keypad = '0' + number) {
            '1','4','7' -> {
                left = keypad
                "L"
            }
            '3','6','9' -> {
                right = keypad
                "R"
            }
            '2','5','8','0' -> {
                val result = manhattan(keypad)
                if(result == "L") {
                    left = keypad
                }
                else {
                    right = keypad
                }
                result
            }
            else -> " "
        }
    }

    val answer = numbers
        .map{ number -> moveToHands(number) }
        .reduce { acc, s -> acc + s }

    return answer
}
