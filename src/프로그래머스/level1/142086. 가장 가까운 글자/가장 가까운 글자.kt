package 프로그래머스.level1.`142086`.` 가장 가까운 글자`

import kotlin.test.assertEquals

fun main() {
    val answer1 = solution("banana").contentToString()
    assertEquals("[-1, -1, -1, 2, 2, 2]", answer1)

    val answer2 = solution("foobar").contentToString()
    assertEquals("[-1, -1, 1, -1, -1, -1]", answer2)
}

fun solution(s: String): IntArray {
    //todo: 1. 자신의 앞에 자신과 같은 글자가 있는지 확인
    //todo: 2. 없으면 -1, 있으면 몇칸앞에 있는지 확인
    //todo: 3. 최종 결과물은 배열에 담아 반환

    val answer = s.mapIndexed { i, char ->
        var result: Int? = null
        val drop = s.take(i+1)
        for (j in drop.length - 1 downTo 0) {
            if(j == drop.length - 1) continue
            val c = s[j]
            if(c == char) {
                result = Math.abs(j - i)
                break
            }
        }

        result ?: -1
    }

    return answer.toIntArray()
}
