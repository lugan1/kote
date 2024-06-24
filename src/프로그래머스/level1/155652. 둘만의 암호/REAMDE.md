# [Level-1] 둘만의 암호 - 155652

[둘만의 암호 - 155652](https://school.programmers.co.kr/learn/courses/30/lessons/155652)

### **문제 설명**

두 문자열 `s`와 `skip`, 그리고 자연수 `index`가 주어질 때, 다음 규칙에 따라 문자열을 만들려 합니다. 암호의 규칙은 다음과 같습니다.

- 문자열 `s`의 각 알파벳을 `index`만큼 뒤의 알파벳으로 바꿔줍니다.
- `index`만큼의 뒤의 알파벳이 `z`를 넘어갈 경우 다시 `a`로 돌아갑니다.
- `skip`에 있는 알파벳은 제외하고 건너뜁니다.

예를 들어 `s` = "aukks", `skip` = "wbqd", `index` = 5일 때, a에서 5만큼 뒤에 있는 알파벳은 f지만 [b, c, d, e, f]에서 'b'와 'd'는 `skip`에 포함되므로 세지 않습니다. 따라서 'b', 'd'를 제외하고 'a'에서 5만큼 뒤에 있는 알파벳은 [c, e, f, g, h] 순서에 의해 'h'가 됩니다. 나머지 "ukks" 또한 위 규칙대로 바꾸면 "appy"가 되며 결과는 "happy"가 됩니다.

두 문자열 `s`와 `skip`, 그리고 자연수 `index`가 매개변수로 주어질 때 위 규칙대로 `s`를 변환한 결과를 return하도록 solution 함수를 완성해주세요.

---

### 제한사항

- 5 ≤ `s`의 길이 ≤ 50
- 1 ≤ `skip`의 길이 ≤ 10
- `s`와 `skip`은 알파벳 소문자로만 이루어져 있습니다.
  - `skip`에 포함되는 알파벳은 `s`에 포함되지 않습니다.
- 1 ≤ `index` ≤ 20

---

### 입출력 예

| s | skip | index | result |
| --- | --- | --- | --- |
| "aukks" | "wbqd" | 5 | "happy" |

---

### 입출력 예 설명

입출력 예 #1본문 내용과 일치합니다.

# 모범답안

---

```kotlin
class Solution {
    fun solution(s: String, skip: String, index: Int): String {
        var answer: String = ""
        val skipped = skip.map { it.toChar() }
        val alphabet = ('a'..'z').filter { it !in skipped }

        s.forEach {
            val i = (alphabet.indexOf(it) + index) % alphabet.size
            answer += alphabet[i]
        }
        return answer
    }
}
```

# 정답 (2024-06-25)

---

```kotlin
/**
 * @param s 문자열
 * @param skip 스킵할 문자열
 * @param index 해당 숫자만큼 이동
 * @return String 완성된 암호
 * */
fun solution(s: String, skip: String, index: Int): String {
    var answer: String = ""

    // a : 97 ~ z : 122
    answer = s.map { c ->
        var count = 0
        var result = c
        while (count < index) {
            result += 1
            if(result > 'z') result = 'a'
            if(skip.contains(result)) continue

            count++
        }

        result
    }.joinToString("")

    return answer
}
```

- s 를 순회하며 c 를 증가시킨다.
  - 증가시킨 count 가 index 가 될때까지 증가시킨다
  - 만약 z 를 넘어서면 다시 ‘a’ 로 처음부터 시작한다.
  - 만약 증가시킨 횟수가 skip 에 포함되어 있으면 count 를 건너뛴다.

# 정답 (2023-02-23)

---

```kotlin
fun solution(s: String, skip: String, index: Int): String {
    var answer = ""
    answer = s.map { c ->
        var char: Char = c
        for(i in 1 .. index){
						//todo: 인덱스까지 +1 씩 더한다.
            char = char.incAndCircle()

						//todo: 만약 더한 값이 skip 글자이면 다시 더한다.
            while (skip.contains(char)){
                char = char.incAndCircle()
            }
        }
        char
    }.joinToString("")

    return answer
}

//todo: char를 +1 더한다. 더한값이 'z' 보다 큰값이면 
//todo: (a ~ z) 가 26글자이니 -26 해서 a 부터 다시시작한다.
fun Char.incAndCircle() : Char {
    val result = this + 1
    return if(result > 'z') (result.code - 26).toChar() else result
}
```

- index 까지 + 1 씩더하고, +1 더할때마다 ‘z’ 를 넘어갔는지 검사한다.
  - 넘어갔으면 -26 (a~z 가 26글자이니) 해서 처음으로 되돌린다.

- 마지막 연산으로 더한 값이 skip 에 속해있으면 +1 씩 계속 더한다.