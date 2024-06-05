package 프로그래머스.level1.`77484`.` 로또의 최고순위와 최저순위`

import kotlin.test.assertEquals

fun main() {
    solution(intArrayOf(44,1,0,0,31,25), intArrayOf(31,10,45,1,6,19)).run {
        assertEquals("[3, 5]", contentToString())
    }

    solution(intArrayOf(0,0,0,0,0,0), intArrayOf(38,19,20,40,15,25)).run {
        assertEquals("[1, 6]", contentToString())
    }

    solution(intArrayOf(45,4,35,20,3,9), intArrayOf(20,9,3,45,4,35)).run {
        assertEquals("[1, 1]", contentToString())
    }
}
/**
 * 로또순위 :
 * 1등 : 6개 번호 모두 일치
 * 2등 : 5개 번호 일치
 * 3등 : 4개 번호 일치
 * 4등 : 3개 번호 일치
 * 5등 : 2개 번호 일치
 * 낙첨 : 그 외
 * */

/**
 * 알아볼 수 없는 로또번호는 0으로 표기
 * @param lottos 구매한 로또번호. 모든 아이템은 0~45 의 번호중 하나 (0을 제외하고 중복없음)
 * @param win_nums 현재 회차 당첨번호. 모든 아이템은 1~45 의 번호중 하나 (0을 제외하고 중복없음)
 * @return IntArray 당첨 가능한 최고 순위와 최저순위를 담은 배열
 * */
fun solution(lottos: IntArray, win_nums: IntArray): IntArray {
    //[44, 1, 0, 0, 31, 25] -> 최저: 2개다 틀릴경우, 최대: 2개다 맞을경우
    //[31, 10, 45, 1, 6, 19]

    val fit = win_nums.filter { lottos.contains(it) }.size
    val best = fit + lottos.filter { it == 0 }.size
    val worst = fit

    val getRank = { n: Int ->
        when(n) {
            6 -> 1
            5 -> 2
            4 -> 3
            3 -> 4
            2 -> 5
            else -> 6
        }
    }

    return intArrayOf(getRank(best), getRank(worst))
}
