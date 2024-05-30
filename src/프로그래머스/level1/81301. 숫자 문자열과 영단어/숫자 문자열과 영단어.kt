package 프로그래머스.level1.`81301`.` 숫자 문자열과 영단어`

import kotlin.test.assertEquals

fun main() {
    val answer = solution("one4seveneight")
    println(answer)
    assertEquals(1478, answer)
}

fun solution(s: String): Int {
    val dictionary = listOf(
        "zero" to 0,
        "one" to 1,
        "two" to 2,
        "three" to 3,
        "four" to 4,
        "five" to 5,
        "six" to 6,
        "seven" to 7,
        "eight" to 8,
        "nine" to 9
    )

    var number: String = ""
    var text: String = ""
    s.forEach { char ->
        if(char.isDigit()) {
            number += char
            return@forEach
        }

        text += char
        val numberText = dictionary.firstOrNull { number -> text == number.first }?.second
        if(numberText != null) {
            number += numberText
            text = ""
        }
    }

    var answer: Int = 0
    answer = number.toInt()

    return answer
}