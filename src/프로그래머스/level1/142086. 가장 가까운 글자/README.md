# [Level-1] 가장 가까운 글자 - 142086
[코딩테스트 연습 - 가장 가까운 같은 글자](https://school.programmers.co.kr/learn/courses/30/lessons/142086)

### **문제 설명**

문자열 `s`가 주어졌을 때, `s`의 각 위치마다 자신보다 앞에 나왔으면서, 자신과 가장 가까운 곳에 있는 같은 글자가 어디 있는지 알고 싶습니다.예를 들어, `s`="banana"라고 할 때,  각 글자들을 왼쪽부터 오른쪽으로 읽어 나가면서 다음과 같이 진행할 수 있습니다.

- b는 처음 나왔기 때문에 자신의 앞에 같은 글자가 없습니다. 이는 -1로 표현합니다.
- a는 처음 나왔기 때문에 자신의 앞에 같은 글자가 없습니다. 이는 -1로 표현합니다.
- n은 처음 나왔기 때문에 자신의 앞에 같은 글자가 없습니다. 이는 -1로 표현합니다.
- a는 자신보다 두 칸 앞에 a가 있습니다. 이는 2로 표현합니다.
- n도 자신보다 두 칸 앞에 n이 있습니다. 이는 2로 표현합니다.
- a는 자신보다 두 칸, 네 칸 앞에 a가 있습니다. 이 중 가까운 것은 두 칸 앞이고, 이는 2로 표현합니다.

따라서 최종 결과물은 [-1, -1, -1, 2, 2, 2]가 됩니다.

문자열 `s`이 주어질 때, 위와 같이 정의된 연산을 수행하는 함수 solution을 완성해주세요.

---

### 제한사항

- 1 ≤ `s`의 길이 ≤ 10,000
    - `s`은 영어 소문자로만 이루어져 있습니다.

---

### 입출력 예

| s | result |
| --- | --- |
| "banana" | [-1, -1, -1, 2, 2, 2] |
| "foobar" | [-1, -1, 1, -1, -1, -1] |

---

### 입출력 예 설명

입출력 예 #1지문과 같습니다.

입출력 예 #2설명 생략

# 정답 (2024-06-03)
현재 진행상황까지 문자열을 자르고, 역순으로 검사
```kotlin
fun solution(s: String): IntArray {
    //todo: 1. 자신의 앞에 자신과 같은 글자가 있는지 확인
    //todo: 2. 없으면 -1, 있으면 몇칸앞에 있는지 확인
    //todo: 3. 최종 결과물은 배열에 담아 반환

    val answer = s.mapIndexed { i, char ->
        var result: Int? = null
        // 현재까지의 문자열로 자른다.
        val drop = s.take(i+1)
        
        // 현재까지의 문자열중 역순으로 검사한다.
        for (j in drop.length - 1 downTo 0) {
            // 현재의 자기자신은 검사에서 제외한다.
            if(j == drop.length - 1) continue
            val c = s[j]
            if(c == char) {
                // 만약 역순으로 검사해서 같은 문자가 존재할경우 위치의 차이를 구한다.
                result = Math.abs(j - i)
                break
            }
        }

        result ?: -1
    }

    return answer.toIntArray()
}

```


---

# 정답 (2023-02-10)

---
현재까지의 진행상황 tmp List 를 만들고, LastIndexOf 로 확인
![20230210_184547.jpg](https://file.notion.so/f/f/a70d74a2-1c6a-4a43-be27-1e17f9232c60/51a9453e-bfda-47ae-af9c-435692ef65c6/20230210_184547.jpg?id=e5330388-33a9-42a7-a256-9ca2ddacd8a3&table=block&spaceId=a70d74a2-1c6a-4a43-be27-1e17f9232c60&expirationTimestamp=1717365600000&signature=mUCGUWaSR0Ua051lZYtpoqI7CTz3Ep4xcaOcdk6mPaA&downloadName=20230210_184547.jpg)

```kotlin
fun solution(s: String): IntArray {
    val answer = MutableList(s.length, { i -> i})
    val tmp: MutableList<Char> = MutableList(s.length, {i -> ' '})

    s.forEachIndexed { index, char ->
        if(tmp.contains(char)){
            //todo: 이미 앞에 같은게 있을 경우
            answer[index] = index - tmp.lastIndexOf(char)
        }
        else {
            //todo: 없을경우 -1
            answer[index] = -1
        }

        tmp[index] = char
    }

    return answer.toIntArray()
}
```

- tmp 리스트를 하나 만들어서 히스토리를 기록한다.
- 히스토리에 없으면 -1
- 히스토리에 있으면 현재 index - lastIndexOf(char)
    - **`lastIndexOf`**: 해당되는 문자열의 위치를 반환한다. 동일한게 있을경우 제일 마지막 위치 반환


# 한줄풀이

---

```kotlin
class Solution {
    fun solution(s: String): List<Int> {
        return s.withIndex().map { (i, c) -> s.slice(0 until i).lastIndexOf(c).let { if (it >= 0) i - it else -1 } }
    }
}
```
