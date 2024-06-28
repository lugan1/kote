package 프로그래머스.level1.`150370`.` 개인정보 수집 유효기간`

import kotlin.test.assertEquals

fun main() {
    solution("2022.05.19", arrayOf("A 6", "B 12", "C 3"), arrayOf("2021.05.02 A", "2021.07.01 B", "2022.02.19 C", "2022.02.20 C")).run {
        assertEquals("[1, 3]", contentToString())
    }

    solution("2020.01.01", arrayOf("Z 3", "D 5"), arrayOf("2019.01.01 D", "2019.11.15 Z", "2019.08.02 D", "2019.07.01 D", "2018.12.28 Z")).run {
        assertEquals("[1, 4, 5]", contentToString())
    }
}

/**
 * @param today 오늘 날짜를 나타낸다 "YYYY.MM.DD"
 * @param terms 약관 종류, 유효기간 을 구분한 문자열 (1 ~ 20)
 * @param privacies 개인정보의 수집 일자와 약관 종류
 * @return IntArray 파기해야 할 개인정보의 번호를 오름차순으로 담은 배열
 * */
fun solution(today: String, terms: Array<String>, privacies: Array<String>): IntArray {
    var answer: IntArray = intArrayOf()
    
    return answer
}