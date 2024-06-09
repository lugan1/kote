package 프로그래머스.level1.`136798`.` 기사단원의 무기`

import kotlin.math.sqrt
import kotlin.test.assertEquals

fun main() {
    solution(5, 3, 2).run {
        assertEquals(10, this)
    }

    solution(10, 3, 2).run {
        assertEquals(21, this)
    }
}

/**
 * 자신의 번호의 약수의 갯수 : 공격력
 *
 * @param number 기사단원의 수 (1 ~ 100,000)
 * @param limit 공격력의 제한 수치 (2 ~ 100)
 * @param power 제한수치를 초과한 기사가 사용할 무기의 공격력
 * @return Int 기사단원이 사용하는 무기의 모든 공격력의 합
 * */
fun solution(number: Int, limit: Int, power: Int): Int {
    val answer = (1..number).map {
        val attack = it.divisors().size
        if(attack > limit) power else attack
    }.reduce { acc, attack -> acc + attack }

    return answer
}

fun Int.divisors(): Set<Int> {
    // 1. 제곱근을 구한다.
    val sqrt = sqrt(this.toDouble()).toInt()

    // 2. 구한 제곱근에서 원래 숫자의 약수가 되는것을 선별한다.
    // sqrt % it 이 아닌 this % it 이다.
    val divisor1 = (1..sqrt).filter { this % it == 0 }

    // 3. 선별된 약수들로 원래 숫자를 나눈다.
    val divisor2 = divisor1.map { this / it }.sorted()

    // 4. divisor1 과 divisor2 의 리스트들을 합친다. (중복제거)
    return divisor1.toSet() + divisor2
}
