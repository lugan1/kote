# [Level-1] 모의고사 3인방 - 42840
[코딩테스트 연습 - 모의고사](https://school.programmers.co.kr/learn/courses/30/lessons/42840)

### **문제 설명**

수포자는 수학을 포기한 사람의 준말입니다. 수포자 삼인방은 모의고사에 수학 문제를 전부 찍으려 합니다. 수포자는 1번 문제부터 마지막 문제까지 다음과 같이 찍습니다.

1번 수포자가 찍는 방식: 1, 2, 3, 4, 5, 1, 2, 3, 4, 5, ...2번 수포자가 찍는 방식: 2, 1, 2, 3, 2, 4, 2, 5, 2, 1, 2, 3, 2, 4, 2, 5, ...3번 수포자가 찍는 방식: 3, 3, 1, 1, 2, 2, 4, 4, 5, 5, 3, 3, 1, 1, 2, 2, 4, 4, 5, 5, ...

1번 문제부터 마지막 문제까지의 정답이 순서대로 들은 배열 answers가 주어졌을 때, 가장 많은 문제를 맞힌 사람이 누구인지 배열에 담아 return 하도록 solution 함수를 작성해주세요.

### 제한 조건

- 시험은 최대 10,000 문제로 구성되어있습니다.
- 문제의 정답은 1, 2, 3, 4, 5중 하나입니다.
- 가장 높은 점수를 받은 사람이 여럿일 경우, return하는 값을 오름차순 정렬해주세요.

### 입출력 예

| answers | return |
| --- | --- |
| [1,2,3,4,5] | [1] |
| [1,3,2,4,2] | [1,2,3] |

### 입출력 예 설명

입출력 예 #1

- 수포자 1은 모든 문제를 맞혔습니다.
- 수포자 2는 모든 문제를 틀렸습니다.
- 수포자 3은 모든 문제를 틀렸습니다.

따라서 가장 문제를 많이 맞힌 사람은 수포자 1입니다.

입출력 예 #2

- 모든 사람이 2문제씩을 맞췄습니다.

# 정답 (2024-06-01)

---

```kotlin
    fun solution(answers: IntArray): IntArray {
        val pattern1 = listOf(1,2,3,4,5)
        val pattern2 = listOf(2,1,2,3,2,4,2,5)
        val pattern3 = listOf(3,3,1,1,2,2,4,4,5,5)

        val rank = mutableListOf(Pair(1, 0), Pair(2, 0), Pair(3, 0))
        answers.forEachIndexed { i, value ->
            if(pattern1[i % pattern1.size] == value) {
                rank[0] = Pair(1, rank[0].second+1)
            }
            if(pattern2[i % pattern2.size] == value) {
                rank[1] = Pair(2, rank[1].second+1)
            }
            if(pattern3[i % pattern3.size] == value) {
                rank[2] = Pair(3, rank[2].second+1)
            }
        }

        //최고 점수
        val max = rank.maxByOrNull { item -> item.second }?.second

        val tops = rank.filter { it.second == max }
            .map{ it.first }
            .toIntArray()

        return tops
    }
```

## 교훈

---

maxBy 는 프로그래머스 기준 Deprecated 되었다. maxByOrNull 을 사용해야 한다.

IntArray 의 내용물을 출력하려면 contentToString 을 사용해야 한다.


# 정답 (2023-02-08)

---

```kotlin
fun solution(answers: IntArray): IntArray {
    val pattern1: Array<Int> = arrayOf(1, 2, 3, 4, 5) //1
    val person1: Array<Int> = Array(answers.size){ i: Int -> pattern1[i % pattern1.size] }

    //2, 1, 2, 3, 2, 4, 2, 5 -> (반복)
    val pattern2: Array<Int> = arrayOf(2, 1, 2, 3, 2, 4, 2, 5) //size 7 //1
    val person2: Array<Int> = Array(answers.size){ i: Int -> pattern2[i % pattern2.size] }

    //33 11 22 44 55 -> (반복)
    val pattern3: Array<Int> = arrayOf(3, 3, 1, 1, 2, 2, 4, 4, 5, 5) //2
    val person3: Array<Int> = Array(answers.size){ i: Int -> pattern3[i % pattern3.size] }

    val score: MutableMap<Int, Int> = mutableMapOf(1 to 0, 2 to 0, 3 to 0)

    answers.forEachIndexed { index, answer ->
        if(person1[index] == answer) score[1]?.inc()?.let { score.replace(1, it) }
        if(person2[index] == answer) score[2]?.inc()?.let { score.replace(2, it) }
        if(person3[index] == answer) score[3]?.inc()?.let { score.replace(3, it) }
    }

    if(score.filterValues { value -> value != 0 }.isEmpty()) {
        return intArrayOf(1 ,2 ,3) //맞춘사람이 한명도 없을경우 오름차순 출력
    }

    //점수 높은순 정렬
    val ranked = score.toList()
         .sortedByDescending { it.second }

    //최고점수
    val max = ranked.first().second

    //최고점수인 사람만 뽑음
    return ranked.filter { it.second == max }
        .toMap()
        .keys
        .toIntArray()
}
```

- 사람 1,2,3 패턴에 대한 정답 리스트를 만든다.
    - 사이즈는 들어오는 정답 길이로 한다.
    - 패턴을 초과할경우 1부터 돌아가도록 한다. % 연산 사용

- 정답 패턴 리스트를 만들었으면, 들어오는 정답과 비교하여 점수를 매긴다.

- 사람과 점수가 연결된 컬렉션에 점수 높은순으로 정렬한다.

- 최고 점수인 사람만 뽑는다.
    - 2명이상이면 오름차순 정렬한다.
    - **모두 틀렸을경우**, 모두가 최고점수이므로 오름차순정렬한다.

# 교훈

---

- 나올 수 있는 모든 경우의 수를 생각해보아야 한다. **모든 테스트 케이스를 생각해보아야 한다.**
- 문제를 잘 읽어야 된다. 점수 높은순으로 출력하는게 아닌, **최고점수인 사람들만** 출력하는 것이다.
