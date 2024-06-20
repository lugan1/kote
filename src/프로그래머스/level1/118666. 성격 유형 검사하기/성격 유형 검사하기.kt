package 프로그래머스.level1.`118666`.` 성격 유형 검사하기`

import kotlin.math.abs
import kotlin.test.assertEquals

fun main() {
    solution(arrayOf("AN", "CF", "MJ", "RT", "NA"), intArrayOf(5, 3, 2, 7, 5)).run {
        assertEquals("TCMA", this)
    }

    solution(arrayOf("TR", "RT", "TR"), intArrayOf(7, 1, 3)).run {
        assertEquals("RCJA", this)
    }

    // T 3, T 1 , R,2
    solution(arrayOf("RT", "RT", "RT"), intArrayOf(7, 5, 2)).run {
        assertEquals("TCJA", this)
    }
}

/**
 * @param survey 질문마다 판단하는 지표 목록 (1 <= survey <= 1000), 예) ["AN"] 아파치(A) / 네오(N) 형
 * @param choices 각 질문마다 선택한 목록 (= survey), (매우 비동의(1), 비동의(2), 약간 비동의(3), 모르겠음(4), 약간동의(5), 동의(6), 매우동의(7))
 * @return String 검사자의 성격 유형 검사 결과를 지표 번호 순서대로 반환
 * */
fun solution(survey: Array<String>, choices: IntArray): String {
    var answer: String = ""

    val scoreMap = mutableMapOf(
        'R' to 0, 'T' to 0,
        'C' to 0, 'F' to 0,
        'J' to 0, 'M' to 0,
        'A' to 0, 'N' to 0
    )
    //todo: 나중에 통계 낼때 map 을 2자리씩 자른 다음 통계를 낸다.


    survey.forEachIndexed { i, value ->
        val score = abs(4 - choices[i])
        if(score == 0) return@forEachIndexed

        //todo: 먼저 문자를 분리한다. AN -> A , N
        val a = value[0]
        val b = value[1]

        //todo: 현재 인덱스에 맞는 점수를 불러온다. 불러온 후 해당 되는 점수에 + 한다.
        if(choices[i] < 4) {
            scoreMap[a] = scoreMap[a]!! + score
        }
        else {
            scoreMap[b] = scoreMap[b]!! + score
        }
    }

    //entries: Map 의 모든 엔트리를 가져온다.
    answer = scoreMap.entries
        .chunked(2) //chunked: 리스트를 지정된 크기만큼 나누어 하위 리스트들의 리스트를 반환하는 함수다.
        .map {
            //maxByOrNull 람다에 따라 특정 속성이 최대 값인 요소를 찾는다.
            val result = it.maxByOrNull { item -> item.value }
            requireNotNull(result).key
        }
        .joinToString("")

    return answer
}