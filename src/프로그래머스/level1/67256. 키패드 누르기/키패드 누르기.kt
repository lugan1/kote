package 프로그래머스.level1.`67256`.` 키패드 누르기`

import kotlin.test.assertEquals

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

fun solution(numbers: IntArray, hand: String): String {
    return ""
}
