# [Level 1] 콜라 문제 - 132267
[코딩테스트 연습 - 콜라 문제](https://school.programmers.co.kr/learn/courses/30/lessons/132267)

### **문제 설명**

오래전 유행했던 콜라 문제가 있습니다. 콜라 문제의 지문은 다음과 같습니다.

> 정답은 아무에게도 말하지 마세요.
>
>
> 콜라 빈 병 2개를 가져다주면 콜라 1병을 주는 마트가 있다. 빈 병 20개를 가져다주면 몇 병을 받을 수 있는가?
>
> 단, 보유 중인 빈 병이 2개 미만이면, 콜라를 받을 수 없다.
>

문제를 풀던 상빈이는 콜라 문제의 완벽한 해답을 찾았습니다. 상빈이가 푼 방법은 아래 그림과 같습니다. 우선 콜라 빈 병 20병을 가져가서 10병을 받습니다. 받은 10병을 모두 마신 뒤, 가져가서 5병을 받습니다. 5병 중 4병을 모두 마신 뒤 가져가서 2병을 받고, 또 2병을 모두 마신 뒤 가져가서 1병을 받습니다. 받은 1병과 5병을 받았을 때 남은 1병을 모두 마신 뒤 가져가면 1병을 또 받을 수 있습니다. 이 경우 상빈이는 총 10 + 5 + 2 + 1 + 1 = 19병의 콜라를 받을 수 있습니다.

![Untitled](https://grepp-programmers.s3.ap-northeast-2.amazonaws.com/files/production/95ce1c11-2f21-4248-8bfc-e330299cbb9a/image6.PNG)

문제를 열심히 풀던 상빈이는 일반화된 콜라 문제를 생각했습니다. 이 문제는 빈 병 `a`개를 가져다주면 콜라 `b`병을 주는 마트가 있을 때, 빈 병 `n`개를 가져다주면 몇 병을 받을 수 있는지 계산하는 문제입니다. 기존 콜라 문제와 마찬가지로, 보유 중인 빈 병이 `a`개 미만이면, 추가적으로 빈 병을 받을 순 없습니다. 상빈이는 열심히 고심했지만, 일반화된 콜라 문제의 답을 찾을 수 없었습니다. 상빈이를 도와, 일반화된 콜라 문제를 해결하는 프로그램을 만들어 주세요.

콜라를 받기 위해 마트에 주어야 하는 병 수 `a`, 빈 병 a개를 가져다 주면 마트가 주는 콜라 병 수 `b`, 상빈이가 가지고 있는 빈 병의 개수 `n`이 매개변수로 주어집니다. 상빈이가 받을 수 있는 콜라의 병 수를 return 하도록 solution 함수를 작성해주세요.

---

### 제한사항

- 1 ≤ `b` < `a` ≤ `n` ≤ 1,000,000
- 정답은 항상 int 범위를 넘지 않게 주어집니다.

---

### 입출력 예

| a | b | n | result |
| --- | --- | --- | --- |
| 2 | 1 | 20 | 19 |
| 3 | 1 | 20 | 9 |


# 정답(2024-06-01)

---

```kotlin
/*
* a : 요청 빈병 갯수 2
* b : 받는 빈병 갯수 1
* n : 현재 입력 갯수 20
* */
tailrec fun solution(a: Int, b: Int, n: Int, acc: Int = 0): Int {
    var answer: Int = 0
    val receive = (n / a) * b
    val left = receive + (n % a)
    answer = acc + receive
    if(n < a) return answer
    return solution(a, b, left, answer)
}

```

- **나누어 떨어지지** 않아서 남은 것들도 처리 포함해야 한다.



# 정답 (2023-02-07)

---

```kotlin
//a: 콜라받을려면 마트에게 빈병 줘야됨
//b: 마트에게 a 빈병주면 b 콜라받음
//n: 초기화값 가지고있는 빈병 갯수
tailrec fun solution(a: Int, b: Int, n: Int, total: Int = 0): Int {
    val receive = (n / a) * b //받은거 = 마트에게 몫만큼만 줄 수있고, 개당 b 만큼 받음
    val left = receive + (n % a) //빈병 = 받은거 + 나머지
    return if(n < a) total else solution(a, b, left, total + receive)
}
```

- 이전 계산값에서 다음 계산 값을 산출하고 → 이전값에서 다음 계산값을 산출하는 **재귀 문제이다.**
- 결과를 입력으로 넣는다.
- 입력받은 이전 결과를 받아서 다시 계산한다. (반복)
    - 받은 병 계산 → 받은병은 다음계산에 누적 (receive)
    - 남은 병 계산 → 남은 병은 다음 계산에 넘겨줌 (left)

- 빠져나가는 조건이 중요하다.
    - 이 문제의 경우 **남은 빈병 n(left) 가 마트에게 줄수있는 값(a)보다 작으면** 마트에게 못준다. (탈출)
    - `n < a`

![Untitled](https://file.notion.so/f/f/a70d74a2-1c6a-4a43-be27-1e17f9232c60/ed825ec7-419f-455e-ab3b-5a6706f82f2f/Untitled.png?id=3d19a52c-b4e8-4ce7-9f23-d76b85402605&table=block&spaceId=a70d74a2-1c6a-4a43-be27-1e17f9232c60&expirationTimestamp=1718344800000&signature=Jj8ZYPNnzeynq7VTf8a4DBy6A5lLxORGKnKFX9j64Zw&downloadName=Untitled.png)

- 유클리드 호제법의 계산방법이 생각나는 출력문이다.
- 유클리드 호제법도 재귀방법으로 할 수 있을것 같다.
