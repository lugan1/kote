package 프로그래머스.level1.`160586`.` 대충 만든 자판`

import kotlin.test.assertEquals

fun main() {
    solution(arrayOf("ABACD", "BCEFD"), arrayOf("ABCD", "AABB")).run {
        assertEquals("[9, 4]", contentToString())
    }

    solution(arrayOf("AA"), arrayOf("B")).run {
        assertEquals("[-1]", contentToString())
    }

    solution(arrayOf("AGZ", "BSSS"), arrayOf("ASA", "BGZ")).run {
        assertEquals("[4, 6]", contentToString())
    }

    solution(arrayOf("BA", "A"), arrayOf("CA")).run {
        assertEquals("[-1]", contentToString())
    }
}

/**
 * @param keyMap 1번 키부터 차례대로 할당된 문자들이 담긴 문자열 (배열 사이즈 및 원소 1 ~ 100) , keymap[0] = "ABCD" 인경우 1번 키를 한번 누르면 A 두번 누르면 B, 세번 누르면 C
 * @param targets 입력하려는 문자열 (배열 사이즈 및 원소 1 ~ 100 )
 * @return IntArray 각 문자열을 작성하기 위해 키를 최소 몇 번씩 눌러야 하는지 담긴 배열, 목표 문자열을 작성할 수 없을 때는 -1 을 반환한다.
 * */
fun solution(keyMap: Array<String>, targets: Array<String>): IntArray {
    return targets.map target@{
        it.map { c ->
            //todo: 몇 번째 원소를 사용할것인지 확인할 필요 없이 그냥 indexOf 가장 적은걸 택하면 된다.
            keyMap
                .map { key -> key.indexOf(c) + 1 }
                .filterNot { key -> key == 0 }
                .minOrNull() ?: return@target -1 //key 가 맞는게 하나도 없다면 -1 반환한다.
        }.reduce { acc, i -> acc + i } // 카운트의 합계를 구한다.
    }.toIntArray()
}