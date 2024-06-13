# [Level-1] 키패드 누르기 - 67256

[키패드 누르기 - 프로그래머스](https://school.programmers.co.kr/learn/courses/30/lessons/67256)

### **문제 설명**

스마트폰 전화 키패드의 각 칸에 다음과 같이 숫자들이 적혀 있습니다.

![Untitled](https://grepp-programmers.s3.ap-northeast-2.amazonaws.com/files/production/4b69a271-5f4a-4bf4-9ebf-6ebed5a02d8d/kakao_phone1.png)

이 전화 키패드에서 왼손과 오른손의 엄지손가락만을 이용해서 숫자만을 입력하려고 합니다.맨 처음 왼손 엄지손가락은 `*` 키패드에 오른손 엄지손가락은 `#` 키패드 위치에서 시작하며, 엄지손가락을 사용하는 규칙은 다음과 같습니다.

1. 엄지손가락은 상하좌우 4가지 방향으로만 이동할 수 있으며 키패드 이동 한 칸은 거리로 1에 해당합니다.
2. 왼쪽 열의 3개의 숫자 `1`, `4`, `7`을 입력할 때는 왼손 엄지손가락을 사용합니다.
3. 오른쪽 열의 3개의 숫자 `3`, `6`, `9`를 입력할 때는 오른손 엄지손가락을 사용합니다.
4. 가운데 열의 4개의 숫자 `2`, `5`, `8`, `0`을 입력할 때는 두 엄지손가락의 현재 키패드의 위치에서 더 가까운 엄지손가락을 사용합니다.

   4-1. 만약 두 엄지손가락의 거리가 같다면, 오른손잡이는 오른손 엄지손가락, 왼손잡이는 왼손 엄지손가락을 사용합니다.


순서대로 누를 번호가 담긴 배열 numbers, 왼손잡이인지 오른손잡이인 지를 나타내는 문자열 hand가 매개변수로 주어질 때, 각 번호를 누른 엄지손가락이 왼손인 지 오른손인 지를 나타내는 연속된 문자열 형태로 return 하도록 solution 함수를 완성해주세요.

### **[제한사항]**

- numbers 배열의 크기는 1 이상 1,000 이하입니다.
- numbers 배열 원소의 값은 0 이상 9 이하인 정수입니다.
- hand는 `"left"` 또는 `"right"` 입니다.
    - `"left"`는 왼손잡이, `"right"`는 오른손잡이를 의미합니다.
- 왼손 엄지손가락을 사용한 경우는 `L`, 오른손 엄지손가락을 사용한 경우는 `R`을 순서대로 이어붙여 문자열 형태로 return 해주세요.

---

### **입출력 예**

| numbers | hand | result |
| --- | --- | --- |
| [1, 3, 4, 5, 8, 2, 1, 4, 5, 9, 5] | "right" | "LRLLLRLLRRL" |
| [7, 0, 8, 2, 8, 3, 1, 5, 7, 6, 2] | "left" | "LRLLRRLLLRR" |
| [1, 2, 3, 4, 5, 6, 7, 8, 9, 0] | "right" | "LLRLLRLLRL" |

### **입출력 예에 대한 설명**

**입출력 예 #1**

순서대로 눌러야 할 번호가 [1, 3, 4, 5, 8, 2, 1, 4, 5, 9, 5]이고, 오른손잡이입니다.

| 왼손 위치 | 오른손 위치 | 눌러야 할 숫자 | 사용한 손 | 설명 |
| --- | --- | --- | --- | --- |
| * | # | 1 | L | 1은 왼손으로 누릅니다. |
| 1 | # | 3 | R | 3은 오른손으로 누릅니다. |
| 1 | 3 | 4 | L | 4는 왼손으로 누릅니다. |
| 4 | 3 | 5 | L | 왼손 거리는 1, 오른손 거리는 2이므로 왼손으로 5를 누릅니다. |
| 5 | 3 | 8 | L | 왼손 거리는 1, 오른손 거리는 3이므로 왼손으로 8을 누릅니다. |
| 8 | 3 | 2 | R | 왼손 거리는 2, 오른손 거리는 1이므로 오른손으로 2를 누릅니다. |
| 8 | 2 | 1 | L | 1은 왼손으로 누릅니다. |
| 1 | 2 | 4 | L | 4는 왼손으로 누릅니다. |
| 4 | 2 | 5 | R | 왼손 거리와 오른손 거리가 1로 같으므로, 오른손으로 5를 누릅니다. |
| 4 | 5 | 9 | R | 9는 오른손으로 누릅니다. |
| 4 | 9 | 5 | L | 왼손 거리는 1, 오른손 거리는 2이므로 왼손으로 5를 누릅니다. |
| 5 | 9 | - | - |  |

따라서 `"LRLLLRLLRRL"`를 return 합니다.

**입출력 예 #2**

왼손잡이가 [7, 0, 8, 2, 8, 3, 1, 5, 7, 6, 2]를 순서대로 누르면 사용한 손은 `"LRLLRRLLLRR"`이 됩니다.

**입출력 예 #3**

오른손잡이가 [1, 2, 3, 4, 5, 6, 7, 8, 9, 0]를 순서대로 누르면 사용한 손은 `"LLRLLRLLRL"`이 됩니다.

# 참고

---

[그래프 탐색](https://www.notion.so/d3f418ee1e534bbfadbbbe24e057e9e4?pvs=21)

[거리계산](https://www.notion.so/74ab934bc8a34dcc8808010e29c8eb7e?pvs=21)

# 정답 (2024-06-13)

---

```kotlin
/**
 * 1,4,7 -> 왼손
 * 3,6,9 -> 오른손
 * 2,5,8,0 -> 더 가까운 손가락 , 같은 경우 오른손잡이는 오른손, 왼손잡이는 왼손
 * @param numbers 순서대로 누를 번호
 * @param hands 왼손잡이인지, 오른손잡이인지
 * @return String 각 번호를 누른 손가락이 왼손인지 오른손인지 나타내는 문자열
 * */
fun solution(numbers: IntArray, hand: String): String {
    data class Loc(val x: Int, val y: Int)
    
    var left: Char = '*'
    var right: Char = '#'

    // 각 패드별 좌표를 생성한다.
    val keyMap: Map<Char, Loc> = mapOf(
        '1' to Loc(0, 0), '2' to Loc(1, 0), '3' to Loc(2, 0),
        '4' to Loc(0, 1), '5' to Loc(1, 1), '6' to Loc(2, 1),
        '7' to Loc(0, 2), '8' to Loc(1, 2), '9' to Loc(2, 2),
        '*' to Loc(0, 3), '0' to Loc(1, 3), '#' to Loc(2, 3),
    )

    // 맨하탄 거리 측정법으로 거리를 측정한다.
    val manhattan = ma@{ input: Char ->
        val inputLoc = keyMap[input]!!
        val leftLoc = keyMap[left]!!
        val rightLoc = keyMap[right]!!

        val leftDist = abs(leftLoc.x-inputLoc.x) + abs(leftLoc.y-inputLoc.y)
        val rightDist = abs(rightLoc.x-inputLoc.x) + abs(rightLoc.y-inputLoc.y)

        if(leftDist == rightDist){
            return@ma if(hand == "left") "L" else "R"
        }

        if(min(leftDist, rightDist) == leftDist) "L" else "R"
    }

    val moveToHands = { number: Int ->
        when(val keypad = '0' + number) {
            '1','4','7' -> {
                left = keypad
                "L"
            }
            '3','6','9' -> {
                right = keypad
                "R"
            }
            '2','5','8','0' -> {
                val result = manhattan(keypad)
                if(result == "L") {
                    left = keypad
                }
                else {
                    right = keypad
                }
                result
            }
            else -> " "
        }
    }

    val answer = numbers
        .map{ number -> moveToHands(number) }
        .reduce { acc, s -> acc + s }

    return answer
}

```

# 정답 (2023-02-17)

---

```kotlin
fun solution(numbers: IntArray, hand: String): String {
    val left = intArrayOf(1, 4, 7)
    val right = intArrayOf(3, 6, 9)
    val middle = intArrayOf(2, 5, 8, 0)

    val numPad = createNumPad()
    val history = StringBuilder()

    var leftHand = 10 //*
    var rightHand = 11 //#

    numbers.forEach { num ->

        //todo: left에 들어있으면 L, right에 들어있으면 R, 중앙이면 문제규칙에 맞추어 L,R 둘중에 하나를 고른다.
        val click = when {
            left.contains(num) -> "L"
            right.contains(num) -> "R"
            middle.contains(num) -> nearby(numPad, num, leftHand, rightHand, hand) //todo: 중앙이면 가장 가까운 손가락으로 결정
            else -> ""
        }

        //todo: num이 중앙일때의 거리차이 계산로직을위해 왼쪽검지의 위치와, 오른쪽 검지의 위치를 기록해 놓는다.
        when (click) {
            "L" -> leftHand = num
            "R" -> rightHand = num
        }

        history.append(click)
    }

    return history.toString()
}

fun nearby(numPad: Array<Array<Int>>, num: Int, leftHand: Int, rightHand: Int, hand:String) : String {
    //todo: 그래프를 이용한 거리차이 계산 구현필요
    val L_Dist = numPad.get(num).get(leftHand)
    val R_Dist = numPad.get(num).get(rightHand)
    return when {
        L_Dist < R_Dist -> "L"
        R_Dist < L_Dist -> "R"
        R_Dist == L_Dist -> if(hand == "left") "L" else "R"
        else -> ""
    }
}

fun createNumPad() : Array<Array<Int>>{
    //키패드별 걸리는 최단거리를 미리 배열로 저장
    return arrayOf(
        //      0, 1, 2, 3, 4, 5, 6, 7, 8, 9, *, #
        arrayOf(0, 4, 3, 4, 3, 2, 3, 2, 1, 2, 1, 1), //0
        arrayOf(4, 0, 1, 2, 1, 2, 3, 2, 3, 4, 3, 5), //1
        arrayOf(3, 1, 0, 1, 2, 1, 2, 3, 2, 3, 4, 4), //2
        arrayOf(4, 2, 1, 0, 3, 2, 1, 4, 3, 2, 5, 3), //3
        arrayOf(3, 1, 2, 3, 0, 1, 2, 1, 2, 3, 2, 4), //4
        arrayOf(2, 2, 1, 2, 1, 0, 1, 2, 1, 2, 3, 3), //5
        arrayOf(3, 2, 2, 1, 2, 1, 0, 3, 2, 1, 4, 2), //6
        arrayOf(2, 1, 3, 4, 1, 2, 3, 0, 1, 2, 1, 3), //7
        arrayOf(1, 3, 2, 3, 2, 1, 2, 1, 0, 1, 2, 2), //8
        arrayOf(2, 4, 3, 2, 3, 2, 1, 2, 1, 0, 3, 1), //9
        arrayOf(1, 3, 4, 5, 2, 3, 4, 1, 2, 3, 0, 2), // *
        arrayOf(1, 5, 4, 3, 4, 3, 2, 3, 2, 1, 2, 0) // #
    )
}
```

- nums 를 순회한다.
    - 왼쪽에 속하는 숫자가 들어오면 → L
    - 오른쪽에 속하는 숫자가 들어오면 → M
    - 가운데에 속하는 숫자가 들어오면 → 최단거리를 구한 후, 가장 짧은 쪽의 방향의 char

이 문제를 푸는 방법은 총 3가지로 보여진다.

1. **미리 각 숫자패드별 걸리는 최단거리를 계산해 배열로 저장**
    1. 코드가 실행되기 전 미리 최단거리별 거리를 계산해놓았으므로 시간복잡도는 가장 짧다.
    2. 불필요한 공간복잡도가 생긴다.
    3. 직접 최단거리별 거리를 일일히 찾아서 수기로 적어야 하므로 문제푸는 시간이 증가할 수 있다.
2. 각 숫자패드별 **x, y 좌표를 만들고 멘하탄 계산법으로 거리 계산**
3. 숫자패드 **그래프를 구현**하고, **BFS 로 최단거리 계산 → 최단거리의 Edge 갯수 반환**
