# [Level-1] 과일 장수 - 135808
[코딩테스트 연습 - 과일 장수](https://school.programmers.co.kr/learn/courses/30/lessons/135808)

### **문제 설명**

과일 장수가 사과 상자를 포장하고 있습니다. 사과는 상태에 따라 1점부터 k점까지의 점수로 분류하며, k점이 최상품의 사과이고 1점이 최하품의 사과입니다. 사과 한 상자의 가격은 다음과 같이 결정됩니다.

- 한 상자에 사과를 m개씩 담아 포장합니다.
- 상자에 담긴 사과 중 가장 낮은 점수가 p (1 ≤ p ≤ k)점인 경우, 사과 한 상자의 가격은 p * m 입니다.

과일 장수가 가능한 많은 사과를 팔았을 때, 얻을 수 있는 최대 이익을 계산하고자 합니다.(사과는 상자 단위로만 판매하며, 남는 사과는 버립니다)

예를 들어, `k` = 3, `m` = 4, 사과 7개의 점수가 [1, 2, 3, 1, 2, 3, 1]이라면, 다음과 같이 [2, 3, 2, 3]으로 구성된 사과 상자 1개를 만들어 판매하여 최대 이익을 얻을 수 있습니다.

- (최저 사과 점수) x (한 상자에 담긴 사과 개수) x (상자의 개수) = 2 x 4 x 1 = 8

사과의 최대 점수 `k`, 한 상자에 들어가는 사과의 수 `m`, 사과들의 점수 `score`가 주어졌을 때, 과일 장수가 얻을 수 있는 최대 이익을 return하는 solution 함수를 완성해주세요.

### 제한사항

- 3 ≤ `k` ≤ 9
- 3 ≤ `m` ≤ 10
- 7 ≤ `score`의 길이 ≤ 1,000,000
    - 1 ≤ `score[i]` ≤ k
- 이익이 발생하지 않는 경우에는 0을 return 해주세요.

---

### 입출력 예

| k | m | score | result |
| --- | --- | --- | --- |
| 3 | 4 | [1, 2, 3, 1, 2, 3, 1] | 8 |
| 4 | 3 | [4, 1, 2, 2, 4, 4, 4, 4, 1, 2, 4, 2] | 33 |

---

### 입출력 예 설명

**입출력 예 #1**

- 문제의 예시와 같습니다.

**입출력 예 #2**

- 다음과 같이 사과 상자를 포장하여 모두 팔면 최대 이익을 낼 수 있습니다.

| 사과 상자 | 가격 |
| --- | --- |
| [1, 1, 2] | 1 x 3 = 3 |
| [2, 2, 2] | 2 x 3 = 6 |
| [4, 4, 4] | 4 x 3 = 12 |
| [4, 4, 4] | 4 x 3 = 12 |

따라서 (1 x 3 x 1) + (2 x 3 x 1) + (4 x 3 x 2) = 33을 return합니다.

# 정답 (2024-06-04)

---

# 정답 (2023-02-12)

---

## 시간초과 오답

```kotlin
class Solution {
fun solution(k: Int, m: Int, score: IntArray): Int {
    val maxScore = k
    val boxSize = m
    val apples = score

    //todo: 이익이 없는 경우에는 0 리턴
    if(apples.size == 0 || apples.size < boxSize){
        return 0
    }

    //todo: 높은 점수별로 정렬하고, 사과를 상자허용량 만큼의 박스로 나누어야 한다.
    val boxes = getBoxes(maxScore, boxSize, apples)

    //todo: 모든 상자의 총합 계산
    var total = 0
    boxes.forEach { box ->
        val min = box.last()
        //todo: 상자 가격 = (최저 사과점수) * (한 상자에 단긴 사과 갯수)
        val price = min * box.size
        total += price
    }

    return total
}

fun getBoxes(maxScore: Int, boxSize: Int, apples: IntArray) : MutableList<List<Int>> {
    //todo: 점수 높은순으로 정렬 -> 시간초과 나서 박스뽑는 방법 변경필요

    apples.sortDescending()
    var left = apples.toList()
    val boxes = mutableListOf<List<Int>>()

    while (left.size >= boxSize) {
        //todo: 앞에서부터 상자 maxSize 개씩 빼서 상자에 저장
        val box = left.take(boxSize)
        boxes.add(box)

        //todo: 박스단위가 안되는 남는 사과는 버린다.
        left = left.drop(boxSize)
    }

    return boxes
}
}
```

- **필요한건 `각 박스당 최저 점수`**이니 box를 구할 필요는 없다.

## 제출정답

---

```kotlin
fun solution(k: Int, m: Int, score: IntArray): Int {
    val boxSize = m
    val apples = score

    //todo: 이익이 없는 경우에는 0 리턴
    if(apples.size == 0 && apples.size < boxSize){
        return 0
    }

    //todo: 높은 점수별로 정렬하고, 각 박스당 최저점을 구해야된다.
    val minList = getMinBoxes(boxSize, apples)

    //todo: 모든 상자의 총합 계산
    var total = 0
    minList.forEach{ min ->
        //todo: 각 박스당 점수 계산 필요 (min * boxSize)
        val score = min * boxSize
        total += score
    }

    return total
}

fun getMinBoxes(boxSize: Int, apples: IntArray) : MutableList<Int> {
    //todo: 점수 높은순으로 정렬
    apples.sortDescending()

    //todo: 각 박스별로 최저점을 구해야함
    val minBoxes = mutableListOf<Int>()

    //todo: 각 박스별로 마지막 원소가 최저점이니 (boxSize-1) 부터 시작해서 boxSize 만큼 건너뛴다.
    for(index in (boxSize-1) until apples.size step boxSize){
        minBoxes.add(apples[index])
    }

    return minBoxes
}
```

- Box를 구하지말고, 어차피 각 박스당 마지막원소가 최저점이니 최저점 별로 건너뛴다.
    - 정렬된 박스 순회
    - boxSize 만큼 건너뛴다.
    - minList 를 만든다.

- minList 를 순회하면서 계산식 `(min * boxSize)` 로 점수를 구한다.
- 모든 점수의 합을 구하면 끝

# 교훈

---

- 로직은 무조건 순회 한번에 끝내는 식으로 처리하는게 좋다.
- 불필요한 작업은 하지 않는다.
