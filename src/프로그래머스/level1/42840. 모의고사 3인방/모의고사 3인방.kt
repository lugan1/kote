package 프로그래머스.level1.`42840`.` 모의고사 3인방`

import kotlin.test.assertEquals

fun main() {
    val answer1 = solution(intArrayOf(1,2,3,4,5))
    assertEquals("[1]", answer1.contentToString())


    val answer2 = solution(intArrayOf(1,3,2,4,2))
    assertEquals("[1, 2, 3]", answer2.contentToString())
}

/**
* 1번 수포자가 찍는 방식: 1, 2, 3, 4, 5, 1, 2, 3, 4, 5, ...
* 2번 수포자가 찍는 방식: 2, 1, 2, 3, 2, 4, 2, 5, 2, 1, 2, 3, 2, 4, 2, 5, ...
* 3번 수포자가 찍는 방식: 3, 3, 1, 1, 2, 2, 4, 4, 5, 5, 3, 3, 1, 1, 2, 2, 4, 4, 5, 5, ...
* @param answers 문제의 정답 리스트
* @return IntArray 가장 높은 점수를 받은 사람들
* */
fun solution(answers: IntArray): IntArray {
    data class Rank(val student: Int, val score: Int)
    val pattern1 = listOf(1,2,3,4,5)
    val pattern2 = listOf(2,1,2,3,2,4,2,5)
    val pattern3 = listOf(3,3,1,1,2,2,4,4,5,5)

    val ranks = (1..3).map { Rank(student = it, score = 0) }.toMutableList()
    answers.forEachIndexed { i, value ->
        if(pattern1[i % pattern1.size] == value) {
            ranks[0] = Rank(1, ranks[0].score+1)
        }
        if(pattern2[i % pattern2.size] == value) {
            ranks[1] = Rank(2, ranks[1].score+1)
        }
        if(pattern3[i % pattern3.size] == value) {
            ranks[2] = Rank(3, ranks[2].score+1)
        }
    }

    //최고 점수
    val max = ranks.maxByOrNull { item -> item.score }?.score


    val tops = ranks.filter { it.score == max }
        .map{ it.student }
        .toIntArray()

    return tops
}
