package 프로그래머스.level1.`135808`.` 과일 장수`

import junit.framework.TestCase.assertEquals

fun main() {
    solution(3, 4, intArrayOf(1,2,3,1,2,3,1))
        .run { assertEquals(8, this) }

    solution(4, 3, intArrayOf(4,1,2,2,4,4,4,4,1,2,4,2))
        .run { assertEquals(33, this) }
}

/**
 * 1 ~ k 점까지의 사과 점수가 있음
 *
 * @param k 사과의 최대 점수
 * @param m 한 상자에 들어갈수 있는 사과의 수
 * @param score 각 과일들의 점수
 * @return Int 과일 장수가 얻을 수 있는 최대 이익
 * (최저의 사과점수) * (한개의 상자의 담긴 사과수) * (상자 수)
 * */
fun solution(k: Int, m: Int, score: IntArray): Int {

    // 가장 좋은 점수를 얻는 조건이란 무엇인가?
    // 사과 박스에서 가장 좋은 점수의 사과만 박스에 우선 담는다.

    //todo: 사과 점수별로 내림차순 정렬

    //todo: 주어진 사과박스 허용량만큼 아이템을 담는다.

    //2차원 배열 필요

    // [3, 3, 2, 2, 1, 1, 1], m: 4

    val boxs = List(score.size / m) {
        mutableListOf<Int>()
    }

    var position = 0

    score.sortedDescending()
        .forEach { apple ->
            if(position >= boxs.size) return@forEach
            val box = boxs[position]
            //box 가 아직 안찼을경우 -> 박스에 사과를 넣는다.
            //box 가 다 찼을 경우 -> position 을 다음으로 이동시킨다. 다음 박스에 넣는다.

            boxs[position].add(apple)
            if(box.size >= m) {
                position++
            }
        }

    if(boxs.isEmpty()) return 0

    return boxs.map { it.minOrNull()!! * it.size }
        .reduce { acc, apple -> acc+apple }
}


