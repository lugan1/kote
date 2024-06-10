# [Level-1] 옹알이 - 133499.

[옹알이 - 프로그래머스](https://school.programmers.co.kr/learn/courses/30/lessons/133499)

### **문제 설명**

머쓱이는 태어난 지 11개월 된 조카를 돌보고 있습니다. 조카는 아직 "aya", "ye", "woo", "ma" 네 가지 발음과 네 가지 발음을 조합해서 만들 수 있는 발음밖에 하지 못하고 연속해서 같은 발음을 하는 것을 어려워합니다. 문자열 배열 `babbling`이 매개변수로 주어질 때, 머쓱이의 조카가 발음할 수 있는 단어의 개수를 return하도록 solution 함수를 완성해주세요.

---

### 제한사항

- 1 ≤ `babbling`의 길이 ≤ 100
- 1 ≤ `babbling[i]`의 길이 ≤ 30
- 문자열은 알파벳 소문자로만 이루어져 있습니다.

---

### 입출력 예

| babbling | result |
| --- | --- |
| ["aya", "yee", "u", "maa"] | 1 |
| ["ayaye", "uuu", "yeye", "yemawoo", "ayaayaa"] | 2 |

---

### 입출력 예 설명

입출력 예 #1

- ["aya", "yee", "u", "maa"]에서 발음할 수 있는 것은 "aya"뿐입니다. 따라서 1을 return합니다.

입출력 예 #2

- ["ayaye", "uuuma", "yeye", "yemawoo", "ayaayaa"]에서 발음할 수 있는 것은 "aya" + "ye" = "ayaye", "ye" + "ma" + "woo" = "yemawoo"로 2개입니다. "yeye"는 같은 발음이 연속되므로 발음할 수 없습니다. 따라서 2를 return합니다.

---

### 유의사항

- 네 가지를 붙여 만들 수 있는 발음 이외에는 어떤 발음도 할 수 없는 것으로 규정합니다. 예를 들어 "woowo"는 "woo"는 발음할 수 있지만 "wo"를 발음할 수 없기 때문에 할 수 없는 발음입니다.

# 모범정답

---

- 정규표현식 사용

```kotlin
fun solution(babbling: Array<String>): Int = babbling.filter {
        !it.contains("ayaaya|yeye|woowoo|mama".toRegex())
    }.map { 
        it.replace("aya|ye|woo|ma".toRegex(),"")
    }.filter { it.isEmpty() }.size 
```

# 정답 (2024-06-09)

---

```kotlin
/**
 * @param babbling 문자열 배열
 * @return Int 웅엉거린 횟수
 * */
fun solution(babbling: Array<String>): Int {
    val babble = listOf("aya", "ye", "woo", "ma")

    val answer = babbling.map { string ->
        val sb = StringBuilder()
        var last = ""
        var enable = false
        string.forEach { c ->
            sb.append(c)
            val checked = sb.toString()
            if(babble.contains(checked) && checked != last) {
                sb.clear()
                last = checked
                enable = true
            }
            else {
                enable = false
            }
        }

        if(enable) 1 else 0
    }.reduce { acc, count -> acc + count }

    return answer
}
```

# 정답 (2023-02-17)

---

```kotlin
fun solution(babbling: Array<String>): Int {
    val baby = arrayOf("aya", "ye", "woo", "ma")
    var count = 0

    //todo : babbling의 각 아이템당, char 스트림을 받는다.
    babbling.forEach { s ->
        var diction = StringBuilder()
        var last = ""

        //todo: babbling의 한 아이템의 스트림이 끝나고, count를 셀지 확인한다.
        val enable = s.map { c -> //a
            //todo: char를 이어붙여서 baby의 아이템이 완성되는지 확인한다.
            diction.append(c)
            val string = diction.toString()

            //todo: 완성된 단어가 이전값이랑 같지 않아야 하고, baby의 아이템과 같은지 확인한다.
            if(string == last || !baby.contains(string)) return@map false

            last = diction.toString()
            diction = StringBuilder()
            true
        }.last()

        if(enable) count++
    }

    return count
}
```

- babbling의 각 아이템당, char 스트림을 받는다.
    - char를 이어붙여서 baby의 아이템이 완성되는지 확인한다.
    - **완성된 단어가 이전값이랑 같지 않아야 하고**, baby의 아이템과 같은지 확인한다.
- babbling의 한 아이템의 스트림이 끝나고, count를 셀지 확인한다.
