package 프로그래머스.level1.`131128`.` 숫자 짝꿍`

import kotlin.test.assertEquals

fun main() {
    solution("12321", "42531").run {
        assertEquals("321", this)
    }

    solution("100", "2345").run {
        assertEquals("-1", this)
    }

    solution("100", "203045").run {
        assertEquals("0", this)
    }

    solution("100", "123450").run {
        assertEquals("10", this)
    }

    // 5 3개, 2 1개 : 5 2개, 1 1개, 2 1개
    solution("5525", "1255").run {
        assertEquals("552", this)
    }
}

/**
 * @param X 짝이 필요한 X의 리스트 3 ~ 3,000,000
 * @param Y 짝이 필요한 Y의 리스트 3 ~ 3,000,000
 * @return String X 와 Y 에 겹치는 숫자들로 조합된 가장 큰 숫자
 * */
fun solution(X: String, Y: String): String {
    var answer: String = ""

    //todo: 각각의 자릿수는 0 ~ 9를 사용한다.
    //todo: string 은 매우 큰 사이즈를 갖고있으니 통계를 활용한다.
    val xMap = X.groupingBy{ it }.eachCount().toMutableMap()
    val yMap = Y.groupingBy{ it }.eachCount().toMutableMap()

    //todo: key 가 높은 순서대로 있어야 높은 숫자가 완성된다.
    //todo: x 를 기준으로 y 에 없는 숫자가 있으면 짝꿍이 아니다.
    val filter = xMap.toSortedMap(compareByDescending { it })
        .filter { yMap[it.key] != null }

    //todo: 전부다 y 에 없는 숫자이면 "-1"
    if(filter.isEmpty()) return "-1"

    answer = filter.map { map ->
        val xValue = map.value
        val yValue = requireNotNull(yMap[map.key])

        // x 의 갯수와 y 의 갯수 중에 더 낮은 값을 사용한다.
        val number = StringBuilder()
        repeat(xValue.coerceAtMost(yValue)) {
            number.append(map.key)
        }
        number.toString()
    }.reduce { acc, s -> acc + s }

    //todo: 만약 첫번째가 0 이면 "0" 을 반환한다.
    if(answer.first() == '0') return "0"

    return answer
}
