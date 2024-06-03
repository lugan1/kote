# [Level - 1] 실패율 - 42889
[코딩테스트 연습 - 실패율](https://school.programmers.co.kr/learn/courses/30/lessons/42889)

# **실패율**

!https://grepp-programmers.s3.amazonaws.com/files/production/bde471d8ac/48ddf1cc-c4ea-499d-b431-9727ee799191.png

슈퍼 게임 개발자 오렐리는 큰 고민에 빠졌다. 그녀가 만든 프랜즈 오천성이 대성공을 거뒀지만, 요즘 신규 사용자의 수가 급감한 것이다. 원인은 신규 사용자와 기존 사용자 사이에 스테이지 차이가 너무 큰 것이 문제였다.

이 문제를 어떻게 할까 고민 한 그녀는 동적으로 게임 시간을 늘려서 난이도를 조절하기로 했다. 역시 슈퍼 개발자라 대부분의 로직은 쉽게 구현했지만, 실패율을 구하는 부분에서 위기에 빠지고 말았다. 오렐리를 위해 실패율을 구하는 코드를 완성하라.

- 실패율은 다음과 같이 정의한다.
  - 스테이지에 도달했으나 아직 클리어하지 못한 플레이어의 수 / 스테이지에 도달한 플레이어 수

전체 스테이지의 개수 N, 게임을 이용하는 사용자가 현재 멈춰있는 스테이지의 번호가 담긴 배열 stages가 매개변수로 주어질 때, 실패율이 높은 스테이지부터 내림차순으로 스테이지의 번호가 담겨있는 배열을 return 하도록 solution 함수를 완성하라.

### 제한사항

- 스테이지의 개수 N은 `1` 이상 `500` 이하의 자연수이다.
- stages의 길이는 `1` 이상 `200,000` 이하이다.
- stages에는 `1` 이상 `N + 1` 이하의 자연수가 담겨있다.
  - 각 자연수는 사용자가 현재 도전 중인 스테이지의 번호를 나타낸다.
  - 단, `N + 1` 은 마지막 스테이지(N 번째 스테이지) 까지 클리어 한 사용자를 나타낸다.
- 만약 실패율이 같은 스테이지가 있다면 작은 번호의 스테이지가 먼저 오도록 하면 된다.
- 스테이지에 도달한 유저가 없는 경우 해당 스테이지의 실패율은 `0` 으로 정의한다.

### 입출력 예

| N | stages | result |
| --- | --- | --- |
| 5 | [2, 1, 2, 6, 2, 4, 3, 3] | [3,4,2,1,5] |
| 4 | [4,4,4,4,4] | [4,1,2,3] |

### 입출력 예 설명

입출력 예

#1 /

1번 스테이지에는 총 8명의 사용자가 도전했으며, 이 중 1명의 사용자가 아직 클리어하지 못했다. 따라서 1번 스테이지의 실패율은 다음과 같다.

- 1 번 스테이지 실패율 : 1/8

2번 스테이지에는 총 7명의 사용자가 도전했으며, 이 중 3명의 사용자가 아직 클리어하지 못했다. 따라서 2번 스테이지의 실패율은 다음과 같다.

- 2 번 스테이지 실패율 : 3/7

마찬가지로 나머지 스테이지의 실패율은 다음과 같다.

- 3 번 스테이지 실패율 : 2/4
- 4번 스테이지 실패율 : 1/2
- 5번 스테이지 실패율 : 0/1

각 스테이지의 번호를 실패율의 내림차순으로 정렬하면 다음과 같다.

- [3,4,2,1,5]

입출력 예

#2

모든 사용자가 마지막 스테이지에 있으므로 4번 스테이지의 실패율은 1이며 나머지 스테이지의 실패율은 0이다.

- [4,1,2,3]

# 정답 (2023-06-03)

---

```kotlin
/**
 * 실패율 : 스테이지에 도착했으나 아직 클리어하지 못한 플레이어 수 / 스테이지에 도달한 플레이어 수
 * @param N 전체 스테이지 수
 * @param stages 사용자가 현재 멈추어있는 스테이지들
 * @param IntArray 실패율이 높은 스테이지부터 내림차순으로 정렬된 배열
 * **/
fun solution(N: Int, stages: IntArray): IntArray {
    // stage: 스테이지 번호 , fails: 실패율
    data class Failure(val stage: Int, val fails: Double)

    // 총 인원
    var peoples = stages.size.toDouble()

    // 스테이지별 현재 머물고 있는 통계를 구한다.
    val countMap = stages.toList()
        .groupingBy{ it }
        .eachCount()

    val answer = List(N) { i ->
        val stage = i+1

        // 현재 스테이지에서 앞으로 나아가지 못하는 플레이어를 구한다. 없을경우, 실패율은 0
        val failure = countMap[stage] ?: return@List Failure(stage, 0.0)

        //현재 스테이지를 깨지못한 수 / 현재 스테이지에 도달한 플레이어 수
        val fails: Double = failure / peoples

        // 총 인원에서 현재 실패한 사람을 제거한다. (다음 스테이지를 올라가지 못했으므로)
        peoples -= failure

        Failure(stage, fails)
    }.sortedWith(
        // 실패율이 높은 순으로 정렬한다. 같을경우 스테이지 번호순으로 정렬한다.
        compareByDescending<Failure> { it.fails }.thenBy { it.stage }
    ).map { it.stage }

    return answer.toIntArray()
}

```

# 정답 (2023-02-11)

---

```kotlin
fun solution(N: Int, stages: IntArray): IntArray {
    // key: 스테이지 , value : 도전한 유저수
    val tryStages: MutableMap<Int, Int> = mutableMapOf()

    // key: 스테이지 , value : 실패한 유저수
    val failureStage: MutableMap<Int, Int> = mutableMapOf()

    // key : 스테이지, value : 실패율
    val stageWithFailureRate: MutableMap<Int,Double> = mutableMapOf()

    for (i in 0 until N){
        tryStages[i+1] = 0
        failureStage[i+1] = 0
        stageWithFailureRate[i+1] = 0.0
    }

    stages.forEach{ stoppedStage ->
        //todo: 해당 스테이지에 몇명이 도전했는지 구해야됨
        //stoppedStage == 2 라는것은 1,2 둘다 도전했다는 뜻임
        for(stage in 1 until stoppedStage+1){
            val tryCount = tryStages[stage]?.inc() ?: 0
            tryStages.replace(stage, tryCount)
        }

        //todo: 해당 스테이지에 몇명이 실패했는지 구해야됨
        //stages = [2,1,2] 2번스테이지에 2명 실패, 1번스테이지에 1명 실패
        //즉 item = 해당 스테이지 실패
        val failureCount = failureStage[stoppedStage]?.inc() ?: 0
        failureStage.replace(stoppedStage, failureCount)
    }

    //todo: 해당 스테이지에 실패율을 구해야됨
    for (stage in 1 until  N+1) {
        val failure: Double = failureStage[stage]?.toDouble() ?: 0.0
        val tried: Double = tryStages[stage]?.toDouble() ?: 0.0

        //todo: 해당 스테이지에 도전한 유저가 없는 경우 해당 스테이지의 실패율은 0으로 정의한다
        val rate: Double = if(tried != 0.0) failure / tried else 0.0
        stageWithFailureRate.replace(stage, rate)
    }

    println(stageWithFailureRate)

    //todo: 실패율이 높은 순으로 내림차순 정렬
    //todo: 실패율 동점 -> 작은번호의 스테이지가 앞으로 (이건 딱히 구현안해도 통과됨)
    val answer = stageWithFailureRate.toList()
        .sortedByDescending { it.second }
        .toMap()
        .keys
        .toIntArray()

    return answer
}
```

- 해당 스테이지에 몇명이 도전했는지 구한다. (스테이지 , 도전횟수)
- 해당 스테이지에 몇명이 실패했는지 구한다. (스테이지 , 실패횟수)
- 스테이지별로 순회하며 실패율을 구한다. (실패횟수 / 도전횟수)
  - **이때, 도전횟수가 0이면 실패율은 자동 0.0 이 되도록해야된다.**

- 실패율 높은순으로 스테이지 map 을 정렬한다.
