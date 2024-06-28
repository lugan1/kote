package 프로그래머스.level1.`92334`.` 신고 결과 받기`

import kotlin.test.assertEquals

fun main() {
    solution(arrayOf("muzi", "frodo", "apeach", "neo"), arrayOf("muzi frodo", "apeach frodo", "frodo neo", "muzi neo", "apeach muzi"), 2)
        .run { assertEquals("[2, 1, 1, 0]", contentToString()) }


    solution(arrayOf("con", "ryan"), arrayOf("ryan con", "ryan con", "ryan con", "ryan con"), 3)
        .run { assertEquals("[0, 0]", contentToString()) }
}

fun solution(id_list: Array<String>, report: Array<String>, k: Int): IntArray {
    var answer: IntArray = intArrayOf()
    return answer
}
