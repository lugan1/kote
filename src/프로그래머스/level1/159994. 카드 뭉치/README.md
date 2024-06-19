# [Level-1] 카드 뭉치 - 159994

[카드 뭉치 - 프로그래머스](https://school.programmers.co.kr/learn/courses/30/lessons/159994)

### **문제 설명**

코니는 영어 단어가 적힌 카드 뭉치 두 개를 선물로 받았습니다. 코니는 다음과 같은 규칙으로 카드에 적힌 단어들을 사용해 원하는 순서의 단어 배열을 만들 수 있는지 알고 싶습니다.

- 원하는 카드 뭉치에서 카드를 순서대로 한 장씩 사용합니다.
- 한 번 사용한 카드는 다시 사용할 수 없습니다.
- 카드를 사용하지 않고 다음 카드로 넘어갈 수 없습니다.
- 기존에 주어진 카드 뭉치의 단어 순서는 바꿀 수 없습니다.

예를 들어 첫 번째 카드 뭉치에 순서대로 ["i", "drink", "water"], 두 번째 카드 뭉치에 순서대로 ["want", "to"]가 적혀있을 때 ["i", "want", "to", "drink", "water"] 순서의 단어 배열을 만들려고 한다면 첫 번째 카드 뭉치에서 "i"를 사용한 후 두 번째 카드 뭉치에서 "want"와 "to"를 사용하고 첫 번째 카드뭉치에 "drink"와 "water"를 차례대로 사용하면 원하는 순서의 단어 배열을 만들 수 있습니다.

문자열로 이루어진 배열 `cards1`, `cards2`와 원하는 단어 배열 `goal`이 매개변수로 주어질 때, `cards1`과 `cards2`에 적힌 단어들로 `goal`를 만들 있다면 "Yes"를, 만들 수 없다면 "No"를 return하는 solution 함수를 완성해주세요.

---

### 제한사항

- 1 ≤ `cards1`의 길이, `cards2`의 길이 ≤ 10
    - 1 ≤ `cards1[i]`의 길이, `cards2[i]`의 길이 ≤ 10
    - `cards1`과 `cards2`에는 서로 다른 단어만 존재합니다.
- 2 ≤ `goal`의 길이 ≤ `cards1`의 길이 + `cards2`의 길이
    - 1 ≤ `goal[i]`의 길이 ≤ 10
    - `goal`의 원소는 `cards1`과 `cards2`의 원소들로만 이루어져 있습니다.
- `cards1`, `cards2`, `goal`의 문자열들은 모두 알파벳 소문자로만 이루어져 있습니다.

---

### 입출력 예

| cards1 | cards2 | goal | result |
| --- | --- | --- | --- |
| ["i", "drink", "water"] | ["want", "to"] | ["i", "want", "to", "drink", "water"] | "Yes" |
| ["i", "water", "drink"] | ["want", "to"] | ["i", "want", "to", "drink", "water"] | "No" |

---

### 입출력 예 설명

입출력 예 #1

본문과 같습니다.

입출력 예 #2

`cards1`에서 "i"를 사용하고 `cards2`에서 "want"와 "to"를 사용하여 "i want to"까지는 만들 수 있지만 "water"가 "drink"보다 먼저 사용되어야 하기 때문에 해당 문장을 완성시킬 수 없습니다. 따라서 "No"를 반환합니다.

# 정답 (2024-06-20)

---

```kotlin
/**
 * @param cards1 카드뭉치1
 * @param cards2 카드뭉치2
 * @param goal 카드뭉치 1과 카드뭉치 2를 조합하여 원하는 문자열
 * @return String goal 이 되는지 안되는지에 대한 결과
 *
 * */
fun solution(cards1: Array<String>, cards2: Array<String>, goal: Array<String>): String {
    var cursor1 = 0
    var cursor2 = 0

    goal.forEach {
        if(cursor1 < cards1.size && cards1[cursor1] == it) cursor1++
        else if(cursor2 < cards2.size && cards2[cursor2] == it) cursor2++
        else return "No"
    }

    return "Yes"
}
```

# 정답 (2023-02-21)

---

```kotlin
fun solution(cards1: Array<String>, cards2: Array<String>, goal: Array<String>): String {
    var answer: String = ""

    var cursor1 = 0
    var cursor2 = 0

		//todo: 1. 카드 1의 커서와 , 카드 2의 커서를 각각 준비해서, 스트림의 char와 일치하면 커서를 앞으로 이동시킨다.
		//todo: 2. 일치하면 사용했다는 것이니 goal의 해당 글자를 null 로 바꾼다.
    val left = goal.map {
        if(cursor1 < cards1.size && cards1[cursor1] == it){
            cursor1++
            return@map null
        }

        if(cursor2 < cards2.size && cards2[cursor2] == it){
            cursor2++
            return@map null
        }

        it
    }.filterNotNull()
		//todo: 3. null이 아닌 (일치가 안된 것만 따로 필터링한다)

		//todo: 4. 잔존한게 비어있으면 Yes, 남아있으면 No
    answer = if(left.isEmpty()) "Yes" else "No"

    return answer
}
```

- 카드 1의 위치 커서와, 카드2의 위치 커서를 각각 준비한다.
- goal 을 순회하며 각각의 카드의 해당 위치커서와 일치하는지 확인한다
    - 일히하면 인덱스 커서를 앞으로 옮긴다.
    - 일치했으니 (사용되었으니) goal 의 해당 글자는 null 로 변경한다.

- goal 에서 null 이 아닌것만 필터링한다.
    - goal이 모두 비어있으면 모두 사용했다는 뜻이니 Yes
    - gaol이 잔존하는게 남아있으면 모두 사용못했다는 뜻이니 No