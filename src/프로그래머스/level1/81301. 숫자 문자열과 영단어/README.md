# [level 1] 숫자 문자열과 영단어 - 81301
[코딩테스트 연습 - 숫자 문자열과 영단어](https://school.programmers.co.kr/learn/courses/30/lessons/81301)

# 문제

---

네오와 프로도가 숫자놀이를 하고 있습니다. 네오가 프로도에게 숫자를 건넬 때 일부 자릿수를 영단어로 바꾼 카드를 건네주면 프로도는 원래 숫자를 찾는 게임입니다.다음은 숫자의 일부 자릿수를 영단어로 바꾸는 예시입니다.

- 1478 → "one4seveneight"
- 234567 → "23four5six7"
- 10203 → "1zerotwozero3"

이렇게 숫자의 일부 자릿수가 영단어로 바뀌어졌거나, 혹은 바뀌지 않고 그대로인 문자열 `s`가 매개변수로 주어집니다. `s`가 의미하는 원래 숫자를 return 하도록 solution 함수를 완성해주세요.

참고로 각 숫자에 대응되는 영단어는 다음 표와 같습니다.

| 숫자 | 영단어 |
| --- | --- |
| 0 | zero |
| 1 | one |
| 2 | two |
| 3 | three |
| 4 | four |
| 5 | five |
| 6 | six |
| 7 | seven |
| 8 | eight |
| 9 | nine |

---

### 제한사항

- 1 ≤ `s`의 길이 ≤ 50
- `s`가 "zero" 또는 "0"으로 시작하는 경우는 주어지지 않습니다.
- return 값이 1 이상 2,000,000,000 이하의 정수가 되는 올바른 입력만 `s`로 주어집니다.

---

### 입출력 예

| s | result |
| --- | --- |
| "one4seveneight" | 1478 |
| "23four5six7" | 234567 |
| "2three45sixseven" | 234567 |
| "123" | 123 |

---

### 입출력 예 설명

**입출력 예 #1**

- 문제 예시와 같습니다.

**입출력 예 #2**

- 문제 예시와 같습니다.

**입출력 예 #3**

- "three"는 3, "six"는 6, "seven"은 7에 대응되기 때문에 정답은 입출력 예 #2와 같은 234567이 됩니다.
- 입출력 예 #2와 #3과 같이 같은 정답을 가리키는 문자열이 여러 가지가 나올 수 있습니다.

**입출력 예 #4**

- `s`에는 영단어로 바뀐 부분이 없습니다.

---

### 제한시간 안내

- 정확성 테스트 : 10초

# 정답 (2024-05-30)

---

```kotlin
class Solution {
    private val dictionary = listOf(
        "zero" to 0,
        "one" to 1,
        "two" to 2,
        "three" to 3,
        "four" to 4,
        "five" to 5,
        "six" to 6,
        "seven" to 7,
        "eight" to 8,
        "nine" to 9
    )

    fun solution(s: String): Int {
        var number: String = ""
        var text: String = ""
        s.forEach { char ->
            if(char.isDigit()) {
                number += char
                return@forEach
            }

            text += char
            val numberText = dictionary.firstOrNull { number -> text == number.first }?.second
            if(numberText != null) {
                number += numberText
                text = ""
            }
        }

        var answer: Int = 0
        answer = number.toInt()

        return answer
    }
}
```

# 정답 (2023-01-09)

---

```python
def solution(str):
    answer = ''
    
    num = { "zero":"0", "one":"1", "two":"2", "three":"3", "four":"4", "five":"5", "six":"6",
          "seven":"7", "eight":"8", "nine":"9"}
    
    temp =''

    for char in str:
        #한글자씩 따옴
        if char.isdigit():
            #만약 따온 한 문자가 숫자이면
            answer = answer+char
            #정답뒤에 이어붙이면됨, 밑에 코드는 실행안해도 되니 continue;
            continue;
        
        temp=temp+char
        #만약 숫자를 못만나면 계속 문자열을 이어붙임
        
        if temp in num:
            #만약 문자열을 이어붙이다가 사전에 정의된 문자열이 되는 순간
            answer = answer+num[temp]
            #정답에다가 해당 단어를 숫자로 바꿔서 이어붙임
            temp = ''
            #onetwo 이렇게 계속 이어붙이면 안되서 이어붙인순간 바로 초기화

    return int(answer)
```
