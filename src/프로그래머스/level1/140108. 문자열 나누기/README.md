# [Level-1] 문자열 나누기 - 140108

[](https://school.programmers.co.kr/learn/courses/30/lessons/140108)

### **문제 설명**

문자열 `s`가 입력되었을 때 다음 규칙을 따라서 이 문자열을 여러 문자열로 분해하려고 합니다.

- 먼저 첫 글자를 읽습니다. 이 글자를 x라고 합시다.
- 이제 이 문자열을 왼쪽에서 오른쪽으로 읽어나가면서, x와 x가 아닌 다른 글자들이 나온 횟수를 각각 셉니다. 처음으로 두 횟수가 같아지는 순간 멈추고, 지금까지 읽은 문자열을 분리합니다.
- `s`에서 분리한 문자열을 빼고 남은 부분에 대해서 이 과정을 반복합니다. 남은 부분이 없다면 종료합니다.
- 만약 두 횟수가 다른 상태에서 더 이상 읽을 글자가 없다면, 역시 지금까지 읽은 문자열을 분리하고, 종료합니다.

문자열 `s`가 매개변수로 주어질 때, 위 과정과 같이 문자열들로 분해하고, 분해한 문자열의 개수를 return 하는 함수 solution을 완성하세요.

---

### 제한사항

- 1 ≤ `s`의 길이 ≤ 10,000
- `s`는 영어 소문자로만 이루어져 있습니다.

---

### 입출력 예

| s | result |
| --- | --- |
| "banana" | 3 |
| "abracadabra" | 6 |
| "aaabbaccccabba" | 3 |

---

### 입출력 예 설명

입출력 예 #1`s`="banana"인 경우 ba - na - na와 같이 분해됩니다.

입출력 예 #2`s`="abracadabra"인 경우 ab - ra - ca - da - br - a와 같이 분해됩니다.

입출력 예 #3`s`="aaabbaccccabba"인 경우 aaabbacc - ccab - ba와 같이 분해됩니다.

# 정답 (2024-06-19)

---

```java
/**
 * @param s 분해할 문자열
 * @return Int 분해한 문자열 갯수
 * */
fun solution(s: String): Int {
    var answer: Int = 0

    //todo: x 의 횟수 == x 가 아닌 다른 글자들이 나온 횟수 -> 지금까지 읽은 문자열 분리
    //todo: s 에서 분리한 문자열을 제외하고 남은 부분에서 이 과정 반복
    //todo: 만약 마지막까지 같아지지 않을 경우 현재까지 읽은 문자열 분리하고 종료

    // ab - ra - ca - da - br - a
    var now: String = ""
    var equal = 0
    var diff = 0
    var count = 0

    s.forEachIndexed { i, c ->
        if(now.isEmpty()) {
            if(i == s.length-1) {
                count++
            }

            now = c.toString()
            equal++
            return@forEachIndexed
        }

        if(now == c.toString()) {
            equal++
        }
        else {
            diff++
        }

        if(equal == diff) {
            now = ""
            equal = 0
            diff = 0
            count++
        }
        else if(i == s.length-1) {
            count++
        }
    }

    answer = count

    return answer
}
```

# 정답 (2023-02-20)

---

```kotlin
fun solution(s: String): Int {
    var answer: Int = 0

    var history = StringBuilder()
    val list = mutableListOf<String>()

    var equalCount = 0
    var nonCount = 0

    s.forEach {c ->
        history.append(c)
        //로직이 실행되는 제일 첫글자가 비교 기준이 된다.
        val pivot = history.first()

        //스트림으로 들어오는 char가 기준과 같으면 카운트++ , 다르면 다른카운트++
        if(c == pivot) equalCount++ else nonCount++
        
        //카운트가 같아지는 순간, 이어붙이던 문자열을 리스트에 담고, 모든 변수들을 초기화해서 처음부터 다시 시작한다.
        if(equalCount == nonCount){
            list.add(history.toString())
            history = StringBuilder()
            equalCount = 0
            nonCount = 0
        }
    }

    //남은 글자가 있으면 마저 추가해준다.
    if(history.isNotEmpty()){
        list.add(history.toString())
    }

    answer = list.size

    return answer
}
```

- 로직이 실행되는 제일 첫글자를 저장한다.
- 문자 스트림 c 가 들어올때마다 로직의 첫글자 pivoit 을 불러온다.
- c가 pivot 과 같으면 카운트++ , 다르면 다른 카운트++
- 같은 카운트와 다른 카운트가 같아지면
    - 이어붙이던 스트링을 리스트에 저장한다.
    - 카운트를 포함한 모든 변수들 초기화한다.

- 남은 글자가 있으면 list에 마저 추가해준다.

- 리스트 자체가 분해된 글자들이므로 size 를 반환하면 된다.