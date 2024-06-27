# [Level-1] 대충 만든 자판 - 160586

[대충 만든 자판 - 프로그래머스](https://school.programmers.co.kr/learn/courses/30/lessons/160586)

### **문제 설명**

휴대폰의 자판은 컴퓨터 키보드 자판과는 다르게 하나의 키에 여러 개의 문자가 할당될 수 있습니다. 키 하나에 여러 문자가 할당된 경우, 동일한 키를 연속해서 빠르게 누르면 할당된 순서대로 문자가 바뀝니다.

예를 들어, 1번 키에 "A", "B", "C" 순서대로 문자가 할당되어 있다면 1번 키를 한 번 누르면 "A", 두 번 누르면 "B", 세 번 누르면 "C"가 되는 식입니다.

같은 규칙을 적용해 아무렇게나 만든 휴대폰 자판이 있습니다. 이 휴대폰 자판은 키의 개수가 1개부터 최대 100개까지 있을 수 있으며, 특정 키를 눌렀을 때 입력되는 문자들도 무작위로 배열되어 있습니다. 또, 같은 문자가 자판 전체에 여러 번 할당된 경우도 있고, 키 하나에 같은 문자가 여러 번 할당된 경우도 있습니다. 심지어 아예 할당되지 않은 경우도 있습니다. 따라서 몇몇 문자열은 작성할 수 없을 수도 있습니다.

이 휴대폰 자판을 이용해 특정 문자열을 작성할 때, 키를 최소 몇 번 눌러야 그 문자열을 작성할 수 있는지 알아보고자 합니다.

1번 키부터 차례대로 할당된 문자들이 순서대로 담긴 문자열배열 `keymap`과 입력하려는 문자열들이 담긴 문자열 배열 `targets`가 주어질 때, 각 문자열을 작성하기 위해 키를 최소 몇 번씩 눌러야 하는지 순서대로 배열에 담아 return 하는 solution 함수를 완성해 주세요.

단, 목표 문자열을 작성할 수 없을 때는 -1을 저장합니다.

---

### 제한사항

- 1 ≤ `keymap`의 길이 ≤ 100
    - 1 ≤ `keymap`의 원소의 길이 ≤ 100
    - `keymap[i]`는 i + 1번 키를 눌렀을 때 순서대로 바뀌는 문자를 의미합니다.
        - 예를 들어 `keymap[0]` = "ABACD" 인 경우 1번 키를 한 번 누르면 A, 두 번 누르면 B, 세 번 누르면 A 가 됩니다.
    - `keymap`의 원소의 길이는 서로 다를 수 있습니다.
    - `keymap`의 원소는 알파벳 대문자로만 이루어져 있습니다.
- 1 ≤ `targets`의 길이 ≤ 100
    - 1 ≤ `targets`의 원소의 길이 ≤ 100
    - `targets`의 원소는 알파벳 대문자로만 이루어져 있습니다.

---

### 입출력 예

| keymap | targets | result |
| --- | --- | --- |
| ["ABACD", "BCEFD"] | ["ABCD","AABB"] | [9, 4] |
| ["AA"] | ["B"] | [-1] |
| ["AGZ", "BSSS"] | ["ASA","BGZ"] | [4, 6] |

---

### 입출력 예 설명

입출력 예 #1

- "ABCD"의 경우,
- 1번 키 한 번 → A
- 2번 키 한 번 → B
- 2번 키 두 번 → C
- 1번 키 다섯 번 → D
- 따라서 총합인 9를 첫 번째 인덱스에 저장합니다.
- "AABB"의 경우,
- 1번 키 한 번 → A
- 1번 키 한 번 → A
- 2번 키 한 번 → B
- 2번 키 한 번 → B
- 따라서 총합인 4를 두 번째 인덱스에 저장합니다.
- 결과적으로 [9,4]를 return 합니다.

입출력 예 #2

- "B"의 경우, 'B'가 어디에도 존재하지 않기 때문에 -1을 첫 번째 인덱스에 저장합니다.
- 결과적으로 [-1]을 return 합니다.

입출력 예 #3

- "ASA"의 경우,
- 1번 키 한 번 → A
- 2번 키 두 번 → S
- 1번 키 한 번 → A
- 따라서 총합인 4를 첫 번째 인덱스에 저장합니다.
- "BGZ"의 경우,
- 2번 키 한 번 → B
- 1번 키 두 번 → G
- 1번 키 세 번 → Z
- 따라서 총합인 6을 두 번째 인덱스에 저장합니다.
- 결과적으로 [4, 6]을 return 합니다.

# 정답 (2024-06-27)

---

```kotlin
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
```

![Untitled](https://file.notion.so/f/f/a70d74a2-1c6a-4a43-be27-1e17f9232c60/890c4e43-928b-4e0b-93ed-2159346077a5/Untitled.png?id=1d242b77-c3d1-497a-8e49-13a8fa68dd7f&table=block&spaceId=a70d74a2-1c6a-4a43-be27-1e17f9232c60&expirationTimestamp=1719540000000&signature=TmqQ366l2W5z0MrArawFm1orbWYFpU085vpyrtEnR_4&downloadName=Untitled.png)

- 몇 번째 원소를 사용할 것인지 확인할 필요 없이, 그냥 IndexOf 에서 가장 적은 index 를 택하면 된다.
- 가장 적은 index 를 취합한다. (reduce)

![Untitled](https://file.notion.so/f/f/a70d74a2-1c6a-4a43-be27-1e17f9232c60/0c7481f1-faa3-4206-98fe-b7698cf7e9cd/Untitled.png?id=75497b20-7df0-4693-bd6b-c905fbe1320a&table=block&spaceId=a70d74a2-1c6a-4a43-be27-1e17f9232c60&expirationTimestamp=1719540000000&signature=kVJcXyKScCMyu7lYb77WYk9Iiqg-q-2dh_lCMAkF5Ws&downloadName=Untitled.png)

<aside>
⚠️ 프로그래머스에서는 min() 이 deprecated 되어 함수를 인식을 못하니 **minOrNull** 을 사용해야한다.

</aside>

문자열을 완성할 수 없으면, 해당 원소는 -1 을 반환해야 한다.

**반례 :**
```kotlin
solution(arrayOf("BA", "A"), arrayOf("CA")).run {
    assertEquals("[-1]", contentToString())
}
```

# 정답 (2023-02-25)

---

```kotlin
fun solution(keyMap: Array<String>, targets: Array<String>): IntArray {
    var answer: IntArray = intArrayOf()

    answer = targets.map { s ->
        val map = mutableMapOf<Char, Int>()
        var result = 0

        //todo: 각 유니크한 char 별로 count map을 만든다.
        s.forEach { c ->
            if(keyMap.none { it.contains(c) }){
                map[c] = -1
                return@forEach
            }

            if(!map.contains(c)) map[c] = 0
        }

        if(map.containsValue(-1)){
            result = -1
        }
        else {
            //todo: 각 점수판별로 해당 char를 입력하기위한 최단 count 를 기록한다.
            val countMap = map.mapValues { (key, value) ->
                keyMap.filter { it.contains(key) }
                      .minOf { it.indexOf(key) } + 1
            }

            s.forEach { c -> result += countMap[c]!! }
        }
        result
    }.toIntArray()

    return answer
}
```

- targets 의 자판별로 count map 을 만든다.
    - 만약 keyMap 에 해당 char가 어떤 리스트에도 포함되어 있지 않다면 -1 리턴

- keyMap 중 가장 작은 indexOf를 선택하고, targets의 string을 순회하며 횟수를 더해나간다.

## 모범답안

---

```kotlin
fun solution(keymap: Array<String>, targets: Array<String>): IntArray =
        targets.map { str ->
            str.map { c -> keymap.map { it.indexOf(c) + 1 }
                .filterNot { it < 1 }
                .let { list ->
                    if (list.isEmpty()) -1
                    else list.minOf { it }
                }
            }.let { if ( it.contains(-1)) -1 else it.sum() }
        }.toIntArray()
```