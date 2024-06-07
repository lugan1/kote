# [Level-1] 체육복 - 42862
[코딩테스트 연습 - 체육복](https://school.programmers.co.kr/learn/courses/30/lessons/42862)

### **문제 설명**

점심시간에 도둑이 들어, 일부 학생이 체육복을 도난당했습니다. 다행히 여벌 체육복이 있는 학생이 이들에게 체육복을 빌려주려 합니다. 학생들의 번호는 체격 순으로 매겨져 있어, 바로 앞번호의 학생이나 바로 뒷번호의 학생에게만 체육복을 빌려줄 수 있습니다. 예를 들어, 4번 학생은 3번 학생이나 5번 학생에게만 체육복을 빌려줄 수 있습니다. 체육복이 없으면 수업을 들을 수 없기 때문에 체육복을 적절히 빌려 최대한 많은 학생이 체육수업을 들어야 합니다.

전체 학생의 수 n, 체육복을 도난당한 학생들의 번호가 담긴 배열 lost, 여벌의 체육복을 가져온 학생들의 번호가 담긴 배열 reserve가 매개변수로 주어질 때, 체육수업을 들을 수 있는 학생의 최댓값을 return 하도록 solution 함수를 작성해주세요.

### 제한사항

- 전체 학생의 수는 2명 이상 30명 이하입니다.
- 체육복을 도난당한 학생의 수는 1명 이상 n명 이하이고 중복되는 번호는 없습니다.
- 여벌의 체육복을 가져온 학생의 수는 1명 이상 n명 이하이고 중복되는 번호는 없습니다.
- 여벌 체육복이 있는 학생만 다른 학생에게 체육복을 빌려줄 수 있습니다.
- 여벌 체육복을 가져온 학생이 체육복을 도난당했을 수 있습니다. 이때 이 학생은 체육복을 하나만 도난당했다고 가정하며, 남은 체육복이 하나이기에 다른 학생에게는 체육복을 빌려줄 수 없습니다.

### 입출력 예

| n | lost | reserve | return |
| --- | --- | --- | --- |
| 5 | [2, 4] | [1, 3, 5] | 5 |
| 5 | [2, 4] | [3] | 4 |
| 3 | [3] | [1] | 2 |

### 입출력 예 설명

예제 #11번 학생이 2번 학생에게 체육복을 빌려주고, 3번 학생이나 5번 학생이 4번 학생에게 체육복을 빌려주면 학생 5명이 체육수업을 들을 수 있습니다.

예제 #23번 학생이 2번 학생이나 4번 학생에게 체육복을 빌려주면 학생 4명이 체육수업을 들을 수 있습니다.

### 문제가 잘 안풀린다면😢

힌트가 필요한가요? [코딩테스트 연습 힌트 모음집]으로 오세요! → [클릭](https://school.programmers.co.kr/learn/courses/14743?itm_content=lesson42862)

[출처](http://hsin.hr/coci/archive/2009_2010/contest6_tasks.pdf)

※ 공지 - 2019년 2월 18일 지문이 리뉴얼되었습니다.

※ 공지 - 2019년 2월 27일, 28일 테스트케이스가 추가되었습니다.

※ 공지 - 2021년 7월 28일 테스트케이스가 추가되었습니다.

※ 공지 - 2021년 8월 30일 테스트케이스가 추가되었습니다.

※ 공지 - 2022년 11월 30일 테스트케이스가 추가되었습니다.

# 모범답안

---

```kotlin
fun solution(n: Int, lost: IntArray, reserve: IntArray): Int {
    if(lost.isEmpty()) return n
    if(reserve.isEmpty()) return n - lost.size

    var answer = n

    val lostSet = lost.sorted().toSet()
    val reserveSet = reserve.sorted().toSet()

    val distinctLost = lostSet - reserveSet
    val distinctReserve = (reserveSet - lostSet).toMutableSet()

    for (i in distinctLost) {
        val prev = i+1
        val next = i-1

        when {
            next in distinctReserve -> distinctReserve.remove(next)
            prev in distinctReserve -> distinctReserve.remove(prev)
            else -> answer--
        }
    }
    return answer
}
```

- lost 와 reserve 의 겹치는 중복 제거를 **set** 를 이용해 푼다.

# 정답 (2024-06-07)

---

```kotlin
/**
 * 체육복이 도난당했고, 여벌을 가지고 있는 번호는 앞뒤 번호로 체육복을 빌려줄 수 있다.
 * 여벌을 가져온 학생이 도난당할 수 있다. 이경우 한벌만 남기때문에 빌려줄수 없다.
 * 체육 수업을 들을 수 있는 학생의 최대 수를 구하여라
 *
 * @param n 전체 학생 수 2 ~ 30
 * @param lost 체육복을 도난당한 학생들의 번호가 담긴 리스트 1 ~ n, 중복 x
 * @param reserve 여벌의 체육복을 가져온 학생들의 번호가 담긴 리스트, 1 ~ n, 중복 x
 * @return 체육 수업을 들을 수 있는 학생의 최대 명 수
 * */
fun solution(n: Int, lost: IntArray, reserve: IntArray): Int {
    // 가장 최선의 선택은 자신의 앞에 있는 번호를 먼저 처리하는 것이다.
    // 정렬을 하지 않으면 자신의 뒤에있는것을 처리할 경우가 생기고, 뒤에 여분이 있는 체육복은 거리차가 2 이상 나서 빌려주지를 못한다.
    val lostList = lost.sorted().toMutableList()
    val reserveList = reserve.sorted().toMutableList().filter {
        // 도난당했고, 여벌이 있는 경우 빌려주지를 못하므로 검사전에 미리 제거한다.
        if(lostList.contains(it)) {
            lostList.remove(it)
            false
        }
        else {
            true
        }
    }

    var possible = n - lostList.size

    // 잃어버린 체육복이 없거나, 여분의 체육복이 없는 경우
    if(lostList.isEmpty()) return n
    if(reserveList.isEmpty()) return possible

    val reserved = reserveList.sorted().map { reserveNo ->
        val prev = reserveNo - 1
        val next = reserveNo + 1

        lostList.forEach { lostNo ->
            if(lostNo == prev || lostNo == next) {
                lostList.remove(lostNo)
                return@map 1
            }
        }

        return@map 0
    }.reduce { acc , student -> acc + student }

    possible += reserved

    return possible
}

```

- 가장 최선의 빌려줄수 있는 방법은 모두가 정렬된 상태에서 자신의 앞쪽의 학생에게 빌려주는 것이다.
- 다음과 같이 여벌이 있고 , 동시에 도난당했을 경우 미리 제거해야 된다. 안그러면 순회중에 다른사람에게 빌려주는 경우가 생긴다.

```kotlin
// 도난 당했고, 동시에 여벌이 있는 경우
solution(5, intArrayOf(1, 2), intArrayOf(2, 3)).run {
    assertEquals(4, this)
}
```

# 정답 (2023-02-14)

---

```kotlin
fun main() {
    //case 1 전부다 놓고온 경우 (check!)
    //solution(5, intArrayOf(1,2,3,4,5), intArrayOf())

    //case 2 전부다 가져온 경우 (check!)
    //solution(5, intArrayOf(1,2,3,4,5), intArrayOf(1,2,3,4,5)) //5

    //case 3 한명만 가지고있음 (check!)
    //solution(5, intArrayOf(1,3,4), intArrayOf(1, 3)) //4

    //case 4 잃어버린 사람목록이 정렬 안되서 들어올경우 -> 4번에서 3번을 이미 빌려서 2번 못빌림
    solution(5, intArrayOf(4, 2), intArrayOf(3, 5)) //5
}

fun solution(n: Int, lost: IntArray, reserve: IntArray): Int {
    val students = n
    var enable = students - lost.size

    //todo: 정렬은 꼭해야됨. lost와 reserve 의 정렬이 맞아야 아다리가 맞아떨어져서 Best 의 방법이나옴
    val sortedLost = lost.sorted()

    //case1 여벌로 가져온 사람이 없음
    if(reserve.isEmpty()) return enable

    //내가 여벌옷이 없을경우만 -> 다른 사람에게 빌림
    val required = sortedLost.filter {
        val self = reserve.contains(it)
        if(self) reserve[reserve.indexOf(it)] = 0 //사용후 여벌목록에서 제거
        !self
    }

    //수업가능한사람 -> 총인원 - 빌려야되는 사람 (다시 계산)
    enable = students - required.size

    //case2 빌려야되는 사람이 없음
    if(required.isEmpty()) return students

    //todo: 앞사람과, 뒷사람이 여벌옷 있는지 확인
    //todo: 다른사람에게 이미 빌려줬는지 확인
    required.forEach {
        val prev = it-1
        val prevIndex = reserve.indexOf(prev)

        val next = it+1
        val nextIndex = reserve.indexOf(next)

        if(reserve.contains(prev) && reserve[prevIndex] != 0) {
            enable++
            reserve[prevIndex] = 0 //빌렸으니 여벌목록 제거
        }
        else if(reserve.contains(next) && reserve[nextIndex] != 0){
            enable++
            reserve[nextIndex] = 0
        }
    }

    return enable
}
```

- 문제에 **정렬이 꼭 되서 들어온다는 보장이 없다.**
    - 정렬이 안되서 들어오는 케이스도 생각해봐야됨

- 잃어버린 사람이 빌려야되니 lost 순회
    - 자신이 여벌옷 가지고 있는 경우
    - 자신이 여벌옷 없는경우
        - 앞사람에게 빌릴수 있는지 확인 → 여벌목록에 있는지 확인
        - 뒷사람에게 빌릴수 있는지 확인 → 여벌목록에 있는지 확인

- **사용후에는 여벌목록에서 꼭 제거한다.**
    - remove, delete 혹은 0 으로 제거
