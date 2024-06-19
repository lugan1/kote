package 프로그래머스.level1.`140108`.` 문자열 나누기`

import kotlin.test.assertEquals

fun main() {
    solution("banana").run {
        assertEquals(3, this)
    }

    solution("abracadabra").run {
        assertEquals(6, this)
    }

    solution("aaabbaccccabba").run {
        assertEquals(3, this)
    }
}

/**
 * @param s 분해할 문자열
 * @return Int 분해한 문자열 갯수
 * */
fun solution(s: String): Int {
    var answer: Int = 0

    //todo: x 의 횟수 == x 가 아닌 다른 글자들이 나온 횟수 -> 지금까지 읽은 문자열 분리
    //todo: s 에서 분리한 문자열을 제외하고 남은 부분에서 이 과정 반복
    //todo: 만약 마지막까지 같아지지 않을 경우 현재까지 읽은 문자열 분리하고 종료

    // ab - ra - ca - da - br - a
    var now: String = ""
    var equal = 0
    var diff = 0
    var count = 0

    s.forEachIndexed { i, c ->
        if(now.isEmpty()) {
            if(i == s.length-1) {
                count++
            }

            now = c.toString()
            equal++
            return@forEachIndexed
        }

        if(now == c.toString()) {
            equal++
        }
        else {
            diff++
        }

        if(equal == diff) {
            now = ""
            equal = 0
            diff = 0
            count++
        }
        else if(i == s.length-1) {
            count++
        }
    }

    answer = count

    return answer
}