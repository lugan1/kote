package 프로그래머스.level1.`42862`.` 체육복`

import kotlin.test.assertEquals

fun main() {
    solution(5, intArrayOf(2,4), intArrayOf(1,3,5)).run {
        assertEquals(5, this)
    }

    solution(5, intArrayOf(2,4), intArrayOf(3)).run {
        assertEquals(4, this)
    }

    solution(3, intArrayOf(3), intArrayOf(1)).run {
        assertEquals(2, this)
    }

    // 잃어버린 것도 없고, 여분도 없는 경우
    solution(5, intArrayOf(), intArrayOf()).run {
        assertEquals(5, this)
    }

    // 정렬되지 않았을 경우 (2번을 빌릴 수 있는데, 순서대로 할경우 한번밖에 못빌림)
    solution(6, intArrayOf(5, 3, 2), intArrayOf(4, 6)).run {
        assertEquals(5, this)
    }

    solution(5, intArrayOf(4, 2), intArrayOf(3, 5)).run {
        assertEquals(5, this)
    }

    // 전부다 도난당했고, 전부다 여벌이 있을경우
    solution(5, intArrayOf(1,2,3,4,5), intArrayOf(1,2,3,4,5)).run {
        assertEquals(5, this)
    }

    // 도난 당했고, 동시에 여벌이 있는 경우
    solution(5, intArrayOf(1, 2), intArrayOf(2, 3)).run {
        assertEquals(4, this)
    }

    solution(5, intArrayOf(5,4,3,2,1), intArrayOf(5,4,3,2,1)).run {
        assertEquals(5, this)
    }

    // 도난당한 체육복이 없고, 전부다 여벌이 있을 경우
    solution(5, intArrayOf(), intArrayOf(1,2,3,4,5)).run {
        assertEquals(5, this)
    }

    // 전부다 도난당했고, 여벌이 없을 경우
    solution(5, intArrayOf(1,2,3,4,5), intArrayOf()).run {
        assertEquals(0, this)
    }

}

/**
 * 체육복이 도난당했고, 여벌을 가지고 있는 번호는 앞뒤 번호로 체육복을 빌려줄 수 있다.
 * 여벌을 가져온 학생이 도난당할 수 있다. 이경우 한벌만 남기때문에 빌려줄수 없다.
 * 체육 수업을 들을 수 있는 학생의 최대 수를 구하여라
 *
 * @param n 전체 학생 수 2 ~ 30
 * @param lost 체육복을 도난당한 학생들의 번호가 담긴 리스트 1 ~ n, 중복 x
 * @param reserve 여벌의 체육복을 가져온 학생들의 번호가 담긴 리스트, 1 ~ n, 중복 x
 * @return 체육 수업을 들을 수 있는 학생의 최대 명 수
 * */
fun solution(n: Int, lost: IntArray, reserve: IntArray): Int {
    // 가장 최선의 선택은 자신의 앞에 있는 번호를 먼저 처리하는 것이다.
    // 정렬을 하지 않으면 자신의 뒤에있는것을 처리할 경우가 생기고, 뒤에 여분이 있는 체육복은 거리차가 2 이상 나서 빌려주지를 못한다.
    val lostList = lost.sorted().toMutableList()
    val reserveList = reserve.sorted().toMutableList().filter {
        // 도난당했고, 여벌이 있는 경우 빌려주지를 못하므로 검사전에 미리 제거한다.
        if(lostList.contains(it)) {
            lostList.remove(it)
            false
        }
        else {
            true
        }
    }

    var possible = n - lostList.size

    // 잃어버린 체육복이 없거나, 여분의 체육복이 없는 경우
    if(lostList.isEmpty()) return n
    if(reserveList.isEmpty()) return possible

    val reserved = reserveList.sorted().map { reserveNo ->
        val prev = reserveNo - 1
        val next = reserveNo + 1

        lostList.forEach { lostNo ->
            if(lostNo == prev || lostNo == next) {
                lostList.remove(lostNo)
                return@map 1
            }
        }

        return@map 0
    }.reduce { acc , student -> acc + student }

    possible += reserved

    return possible
}
