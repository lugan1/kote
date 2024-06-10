package 프로그래머스.level1.`133499`.` 옹알이`

import kotlin.test.assertEquals

fun main() {
    solution(arrayOf("aya", "yee", "u", "maa")).run {
        assertEquals(1, this)
    }

    solution(arrayOf("ayaye", "uuu", "yeye", "yemawoo", "ayaayaa")).run {
        assertEquals(2, this)
    }
}


/**
 * @param babbling 문자열 배열
 * @return Int 웅엉거린 횟수
 * */
fun solution(babbling: Array<String>): Int {
    val babble = listOf("aya", "ye", "woo", "ma")

    val answer = babbling.map { string ->
        val sb = StringBuilder()
        var last = ""
        var enable = false
        string.forEach { c ->
            sb.append(c)
            val checked = sb.toString()
            if(babble.contains(checked) && checked != last) {
                sb.clear()
                last = checked
                enable = true
            }
            else {
                enable = false
            }
        }

        if(enable) 1 else 0
    }.reduce { acc, count -> acc + count }

    return answer
}
