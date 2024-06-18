package 프로그래머스.level1.`72410`.` 신규 아이디 추천`

import junit.framework.TestCase.assertEquals
import java.util.*


fun main() {
    solution("...!@BaT#*..y.abcdefghijklm").run {
        assertEquals("bat.y.abcdefghi", this)
    }

    solution("z-+.^.").run {
        assertEquals("z--", this)
    }

    solution("=.=").run {
        assertEquals("aaa", this)
    }

    solution("123_.def").run {
        assertEquals("123_.def", this)
    }

    solution("abcdefghijklmn.p").run {
        assertEquals("abcdefghijklmn", this)
    }

    solution("s....s").run {
        assertEquals("s.s", this)
    }
}

/**
 * @param new_id 신규 유저가 입력한 아이디
 * @return String "네오" 가 설계한 7단계의 처리과정을 거친 후의 추천 아이디
 * */
fun solution(new_id: String): String {
    var answer: String = ""
    //todo: 1단계 new_id 의 모든 대문자를 대응되는 소문자로 치환한다.
    val step1 = new_id.lowercase(Locale.getDefault())
    //todo: 2단계 new_id 에서 알파벳 소문자, 숫자, 빼기(-), 밑줄(_), 마침표(.)를 제외한 모든 문자를 제거한다.
    val regex = Regex("[a-z0-9-_.]")
    val step2 = step1.filter { it.toString().matches(regex) }

    //todo: 3단계 new_id 에서 마침표(.)가 2번 이상 연속된 부분을 하나의 마침표(.)로 치환한다.
    val regex2 = Regex(".{2,}")
    val step3 = step2.replace(regex2, ".")

    //todo: 4단계 new_id 에서 마침표(.)가 처음이나 끝에 위치한다면 제거한다.
    var step4 = step3
    if(step4.firstOrNull() == '.') {
        step4 = step3.dropWhile { it == '.' }
    }
    if(step4.lastOrNull() == '.') {
        step4 = step4.dropLastWhile { it == '.' }
    }

    //todo: 5단계 new_id가 빈 문자열이라면, new_id 에 "a" 를 대입한다.
    val step5 = step4.ifEmpty { "a" }

    //todo: 6단계 new_id의 길이가 16자 이상이면, new_id 의 첫 15개 문자를 제외한 나머지 문자들을 모두 제거한다. 제거한 후 마침표(.)가 new_id의 끝에 위치한다면 끝에 위치한 마침표(.) 를 제거한다.
    var step6 = if(step5.length >= 16) step5.dropLast(step5.length - 15) else step5

    if(step6.last() == '.') {
        step6 = step6.dropLastWhile { it == '.' }
    }

    //todo: 7단계 new_id의 길이가 2자 이하라면, new_id 의 마지막 문자를 new_id 의 길이가 3이 될 때까지 반복해서 끝에 붙인다.
    var step7 = step6
    if(step7.length <= 2) {
        while (step7.length <= 2) {
            step7 += step7.last()
        }
    }

    answer = step7

    return answer
}
