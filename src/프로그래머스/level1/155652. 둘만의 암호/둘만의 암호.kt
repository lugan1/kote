package 프로그래머스.level1.`155652`.` 둘만의 암호`

import junit.framework.TestCase.assertEquals

fun main() {
    solution("aukks", "wbqd", 5).run {
        assertEquals("happy", this)
    }
}

/**
 * @param s 문자열
 * @param skip 스킵할 문자열
 * @param index 해당 숫자만큼 이동
 * @return String 완성된 암호
 * */
fun solution(s: String, skip: String, index: Int): String {
    var answer: String = ""

    // a : 97 ~ z : 122
    answer = s.map { c ->
        var count = 0
        var result = c
        while (count < index) {
            result += 1
            if(result > 'z') result = 'a'
            if(skip.contains(result)) continue

            count++
        }

        result
    }.joinToString("")

    return answer
}