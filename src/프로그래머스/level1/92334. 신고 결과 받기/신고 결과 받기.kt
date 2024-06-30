package 프로그래머스.level1.`92334`.` 신고 결과 받기`

import kotlin.test.assertEquals

fun main() {
    solution(arrayOf("muzi", "frodo", "apeach", "neo"), arrayOf("muzi frodo", "apeach frodo", "frodo neo", "muzi neo", "apeach muzi"), 2)
        .run { assertEquals("[2, 1, 1, 0]", contentToString()) }


/*    solution(arrayOf("con", "ryan"), arrayOf("ryan con", "ryan con", "ryan con", "ryan con"), 3)
        .run { assertEquals("[0, 0]", contentToString()) }*/
}


/**
 * @param id_list 이용자의 id 들을 담은 배열 ( 2 ~ 1,000 )
 * @param report "신고유저 신고된 유저" 형태의 문자열 배열 ( 1 < 200,000 )
 * @param k 정지 기준이 되는 신고 횟수
 * @IntArray 각 유저별로 처리 결과 메일을 받은 횟수
 * */
fun solution(id_list: Array<String>, report: Array<String>, k: Int): IntArray {
    var answer: IntArray = intArrayOf()
    data class Result(var send:Int, var reported: Int)

    val userMap: MutableMap<String, Result> = id_list.associateWith { Result(0, 0) }.toMutableMap()

    report.forEach {
        val split = it.split(" ")
        val reporter = userMap[split[0]]!!
        val reported = userMap[split[1]]!!

        reported.reported += 1
        if(reported.reported > k) {
            reporter.send += 1
        }
    }


    println(userMap)


    return answer
}
