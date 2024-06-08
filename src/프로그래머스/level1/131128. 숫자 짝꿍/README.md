# [Level-1] 숫자 짝꿍 - 131128
[숫자 짝꿍 - 프로그래머스](https://school.programmers.co.kr/learn/courses/30/lessons/131128)

### **문제 설명**

두 정수 `X`, `Y`의 임의의 자리에서 공통으로 나타나는 정수 k(0 ≤ k ≤ 9)들을 이용하여 만들 수 있는 가장 큰 정수를 두 수의 짝꿍이라 합니다(단, 공통으로 나타나는 정수 중 서로 짝지을 수 있는 숫자만 사용합니다). `X`, `Y`의 짝꿍이 존재하지 않으면, 짝꿍은 -1입니다. `X`, `Y`의 짝꿍이 0으로만 구성되어 있다면, 짝꿍은 0입니다.

예를 들어, `X` = 3403이고 `Y` = 13203이라면, `X`와 `Y`의 짝꿍은 `X`와 `Y`에서 공통으로 나타나는 3, 0, 3으로 만들 수 있는 가장 큰 정수인 330입니다. 다른 예시로 `X` = 5525이고 `Y` = 1255이면 `X`와 `Y`의 짝꿍은 `X`와 `Y`에서 공통으로 나타나는 2, 5, 5로 만들 수 있는 가장 큰 정수인 552입니다(`X`에는 5가 3개, `Y`에는 5가 2개 나타나므로 남는 5 한 개는 짝 지을 수 없습니다.)두 정수 `X`, `Y`가 주어졌을 때, `X`, `Y`의 짝꿍을 return하는 solution 함수를 완성해주세요.

### 제한사항

- 3 ≤ `X`, `Y`의 길이(자릿수) ≤ 3,000,000입니다.
- `X`, `Y`는 0으로 시작하지 않습니다.
- `X`, `Y`의 짝꿍은 상당히 큰 정수일 수 있으므로, 문자열로 반환합니다.

---

### 입출력 예

| X | Y | result |
| --- | --- | --- |
| "100" | "2345" | "-1" |
| "100" | "203045" | "0" |
| "100" | "123450" | "10" |
| "12321" | "42531" | "321" |
| "5525" | "1255" | "552" |

---

### 입출력 예 설명

**입출력 예 #1**

- `X`, `Y`의 짝꿍은 존재하지 않습니다. 따라서 "-1"을 return합니다.

**입출력 예 #2**

- `X`, `Y`의 공통된 숫자는 0으로만 구성되어 있기 때문에, 두 수의 짝꿍은 정수 0입니다. 따라서 "0"을 return합니다.

**입출력 예 #3**

- `X`, `Y`의 짝꿍은 10이므로, "10"을 return합니다.

**입출력 예 #4**

- `X`, `Y`의 짝꿍은 321입니다. 따라서 "321"을 return합니다.

**입출력 예 #5**

- 지문에 설명된 예시와 같습니다.

# 정답 (2024-06-07)

---

```kotlin
/**
 * @param X 짝이 필요한 X의 리스트 3 ~ 3,000,000
 * @param Y 짝이 필요한 Y의 리스트 3 ~ 3,000,000
 * @return String X 와 Y 에 겹치는 숫자들로 조합된 가장 큰 숫자
 * */
fun solution(X: String, Y: String): String {
    var answer: String = ""

    //todo: 각각의 자릿수는 0 ~ 9를 사용한다.
    //todo: string 은 매우 큰 사이즈를 갖고있으니 통계를 활용한다.
    val xMap = X.groupingBy{ it }.eachCount().toMutableMap()
    val yMap = Y.groupingBy{ it }.eachCount().toMutableMap()

    //todo: key 가 높은 순서대로 있어야 높은 숫자가 완성된다.
    //todo: x 를 기준으로 y 에 없는 숫자가 있으면 짝꿍이 아니다. 
    val filter = xMap.toSortedMap(compareByDescending { it })
        .filter { yMap[it.key] != null }

    //todo: 전부다 y 에 없는 숫자이면 "-1"
    if(filter.isEmpty()) return "-1"

    answer = filter.map { map ->
        val xValue = map.value
        val yValue = requireNotNull(yMap[map.key])

        // x 의 갯수와 y 의 갯수 중에 더 낮은 값을 사용한다.
        val number = StringBuilder()
        repeat(xValue.coerceAtMost(yValue)) {
            number.append(map.key)
        }
        number.toString()
    }.reduce { acc, s -> acc + s }

    //todo: 만약 첫번째가 0 이면 "0" 을 반환한다.
    if(answer.first() == '0') return "0"

    return answer
}

```

# 정답 (2023-02-15)

---

## 참고사항

---

[Kotlin 문자열에서 모든 문자 찾기](https://www.techiedelight.com/ko/find-all-occurrences-of-character-in-a-kotlin-string/)

## 시간초과 되었을경우 참고사항

![Untitled](https://file.notion.so/f/f/a70d74a2-1c6a-4a43-be27-1e17f9232c60/6d319eb2-b1be-4e33-be79-b16a36ccbf5c/Untitled.png?id=46724000-5d8c-46d8-a23d-8e6633c1fe3b&table=block&spaceId=a70d74a2-1c6a-4a43-be27-1e17f9232c60&expirationTimestamp=1717833600000&signature=I4yFpGgcNQr_E-QjR6n7uR4Ugwo-rWL-BDICK8Ko48U&downloadName=Untitled.png)

```kotlin
/**
 * @param X: 정수1 문자열 리스트
 * @param Y: 정수2 문자열 리스트
 * @return 두 정수의 같은숫자 짝끼리 가장 높은 숫자 (짝꿍이 존재하지 않으면 -1 리턴)
 * */
fun solution(X: String, Y: String): String {
    var answer: String = ""

    //todo: X, Y 각각의 문자열 갯수 점수판을 만든다.
    val xCntMap = mutableMapOf("9" to 0, "8" to 0, "7" to 0, "6" to 0, "5" to 0, "4" to 0, "3" to 0, "2" to 0, "1" to 0, "0" to 0)
    val yCntMap = mutableMapOf("9" to 0, "8" to 0, "7" to 0, "6" to 0, "5" to 0, "4" to 0, "3" to 0, "2" to 0, "1" to 0, "0" to 0)

    //todo: X, Y의 각각의 문자열 갯수를 센다.
    X.forEach {
        val acc = xCntMap[it.toString()] ?: 0
        xCntMap.replace(it.toString(), acc.inc())
    }

    Y.forEach {
        val acc = yCntMap[it.toString()] ?: 0
        yCntMap.replace(it.toString(), acc.inc())
    }

    //todo: 문자열 갯수가 0 개인 item은 버린다.
    val xMapNoneZero = xCntMap.filter { (_, v) -> v != 0 }
    val yMapNoneZero = yCntMap.filter { (_, v) -> v != 0 }

    //todo: X기준에서 Y의 키랑 같은거만 필터링한다.
    //todo: 문자열 갯수는 X 와 Y 둘중에 작은거를 택한다.
    val equal = xMapNoneZero.filter { yMapNoneZero.containsKey(it.key) }
                                            .mapValues {
                                                val yCount = yMapNoneZero[it.key]!!
                                                if(it.value <= yCount) it.value else yCount
                                            }

    //todo: 같은 문자열이 하나도 없다면 -1
    if(equal.isEmpty()) return "-1"

    //todo: 같은 문자열이 0밖에 없다면 0
    if(equal.none { it.key != "0" }) return "0"

    //todo: 횟수만큼 문자열을 이어 붙인다.
    //todo: 이어붙인 문자열을 key 별로 이어붙인다.
    //todo: (중요) StringBuilder로 이어붙여야 된다. 그냥 문자열로 붙이면 시간초과
    answer = equal.keys
                  .map { key ->
                      val count = equal[key] ?: 0
                      val s = StringBuilder()
                      for(i in 1 .. count){
                          s.append(key)
                      }
                      s
                  }
                  .reduce { acc, s -> acc.append(s) }
                  .toString()

    return answer
}
```

### 로직1 (시간초과)

X순회를 돌면서 Y와 같은 숫자를 찾는다.

- indexOf() 는 시간복잡도가 `O(n)` 이여서 순회중에 사용하면 계산시간이 제곱으로 늘어난다. `O(n²)`

찾은 숫자들끼리 따로 리스트를 만든다.

리스트를 높은순으로 정렬한다.

### 로직2

X의 숫자별 카운팅을 세고, Y의 숫자별 카운팅을 세는방법

- X순회를 돌고 나서, Y 순회를 돈다. `O(2n)`
- X의 숫자 카운팅과 Y의 숫자카운팅중 작은 카운팅을 선택한다.
- 숫자 카운팅 만큼 문자열을 더한다.
    - **(중요)** 문자열을 더할때는 StringBuilder를 사용하는 것이 시간이 단축된다. **(그냥 더하면 시간초과)**
- 숫자 카운팅끼리 더한 각각의 문자열을 하나로 합친다.
