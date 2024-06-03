package 프로그래머스.level1.`42889`.` 실패율`

import kotlin.test.assertEquals

fun main() {
    solution(5, intArrayOf(2,1,2,6,2,4,3,3)).run {
        assertEquals("[3, 4, 2, 1, 5]", contentToString())
    }

    solution(4, intArrayOf(4,4,4,4,4)).run {
        assertEquals("[4, 1, 2, 3]", contentToString())
    }
}

/**
 * 실패율 : 스테이지에 도착했으나 아직 클리어하지 못한 플레이어 수 / 스테이지에 도달한 플레이어 수
 * @param N 전체 스테이지 수
 * @param stages 사용자가 현재 멈추어있는 스테이지들
 * @param IntArray 실패율이 높은 스테이지부터 내림차순으로 정렬된 배열
 * **/
fun solution(N: Int, stages: IntArray): IntArray {
    // stage: 스테이지 번호 , fails: 실패율
    data class Failure(val stage: Int, val fails: Double)

    // 총 인원
    var peoples = stages.size.toDouble()

    // 스테이지별 현재 머물고 있는 통계를 구한다.
    val countMap = stages.toList()
        .groupingBy{ it }
        .eachCount()

    val answer = List(N) { i ->
        val stage = i+1

        // 현재 스테이지에서 앞으로 나아가지 못하는 플레이어를 구한다. 없을경우, 실패율은 0
        val failure = countMap[stage] ?: return@List Failure(stage, 0.0)

        //현재 스테이지를 깨지못한 수 / 현재 스테이지에 도달한 플레이어 수
        val fails: Double = failure / peoples

        // 총 인원에서 현재 실패한 사람을 제거한다. (다음 스테이지를 올라가지 못했으므로)
        peoples -= failure

        Failure(stage, fails)
    }.sortedWith(
        // 실패율이 높은 순으로 정렬한다. 같을경우 스테이지 번호순으로 정렬한다.
        compareByDescending<Failure> { it.fails }.thenBy { it.stage }
    ).map { it.stage }

    return answer.toIntArray()
}
