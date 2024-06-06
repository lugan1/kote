package 프로그래머스.level1.`138477`.` 명예의 전당`

import java.util.*
import kotlin.test.assertEquals

fun main() {
    solution(3, intArrayOf(10, 100, 20, 150, 1, 100, 20)).run {
        assertEquals("[10, 10, 10, 20, 20, 100, 100]", contentToString())
    }

    solution(4, intArrayOf(0, 300, 40, 300, 20, 70, 150, 50, 500, 1000)).run {
        assertEquals("[0, 0, 0, 0, 20, 40, 70, 70, 150, 300]", contentToString())
    }
}

/**
 * @param k 명예의 전당 최대 목록 갯수
 * @param score 일 자별로 진행된 가수의 점수들
 * @return IntArray 매일 발표되는 명예의 전당의 최하위 점수
 * */
fun solution(k: Int, score: IntArray): IntArray {
    var answer: IntArray = intArrayOf()
    val minHeap: PriorityQueue<Int> = PriorityQueue()
    val maxSize = k

    answer = score.map { value ->
        if(minHeap.size < maxSize) {
            //todo: 명예의 전당에 아직 다 차지 않았을경우.
            //todo: 아이템을 넣는다.
            minHeap.add(value)
        }
        else if(value >= minHeap.peek()) {
            //todo: 명예의 전당에 다 찼을경우.
            //todo: 현재 아이템과 최하위 아이템을 비교해서 방출하고, 현재 아이템을 넣는다.
            minHeap.poll()
            minHeap.add(value)
        }

        //todo: 현재 명예의 전당에서 최하위를 기록한다.
        return@map minHeap.peek()
    }.toIntArray()

    return answer
}
