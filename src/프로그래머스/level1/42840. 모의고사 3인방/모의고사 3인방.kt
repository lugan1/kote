package 프로그래머스.level1.`42840`.` 모의고사 3인방`

import kotlin.test.assertEquals

fun main() {
    val answer1 = solution(intArrayOf(1,2,3,4,5))
    assertEquals("[1]", answer1.contentToString())


    val answer2 = solution(intArrayOf(1,3,2,4,2))
    assertEquals("[1, 2, 3]", answer2.contentToString())
}

fun solution(answers: IntArray): IntArray {
    val pattern1 = listOf(1,2,3,4,5)
    val pattern2 = listOf(2,1,2,3,2,4,2,5)
    val pattern3 = listOf(3,3,1,1,2,2,4,4,5,5)

    val rank = mutableListOf(Pair(1, 0), Pair(2, 0), Pair(3, 0))
    answers.forEachIndexed { i, value ->
        if(pattern1[i % pattern1.size] == value) {
            rank[0] = Pair(1, rank[0].second+1)
        }
        if(pattern2[i % pattern2.size] == value) {
            rank[1] = Pair(2, rank[1].second+1)
        }
        if(pattern3[i % pattern3.size] == value) {
            rank[2] = Pair(3, rank[2].second+1)
        }
    }

    //최고 점수
    val max = rank.maxByOrNull { item -> item.second }?.second


    val tops = rank.filter { it.second == max }
        .map{ it.first }
        .toIntArray()

    return tops
}
