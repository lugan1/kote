# [level 1] 자릿수 더하기 - 12931
[코딩테스트 연습 - 자릿수 더하기](https://school.programmers.co.kr/learn/courses/30/lessons/12931?language=python3)

### **문제 설명**

자연수 N이 주어지면, N의 각 자릿수의 합을 구해서 return 하는 solution 함수를 만들어 주세요.예를들어 N = 123이면 1 + 2 + 3 = 6을 return 하면 됩니다.

### 제한사항

- N의 범위 : 100,000,000 이하의 자연수

---

### 입출력 예

| N | answer |
| --- | --- |
| 123 | 6 |
| 987 | 24 |

### 입출력 예 설명

입출력 예 #1문제의 예시와 같습니다.

입출력 예 #29 + 8 + 7 = 24이므로 24를 return 하면 됩니다.

# 정답

---

```python
def solution(n):
    answer = 0
    
    num_list = list(map(int, str(n)))
    
    for i in num_list :
        answer += i

    return answer
```

```kotlin
fun solution(n: Int): Int {
    var answer = 0

    answer = n.toString()
        .map { c -> c.digitToInt() }
        .reduce { acc, i -> acc + i }

    return answer
}
```

# 참고

---

[[python] 파이썬 숫자 각 자리수 분리](https://clolee.tistory.com/36)