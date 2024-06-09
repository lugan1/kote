# [Level-1] 기사단원의 무기 - 136798
[기사단원의 무기 - 프로그래머스](https://school.programmers.co.kr/learn/courses/30/lessons/136798)

### **문제 설명**

숫자나라 기사단의 각 기사에게는 1번부터 `number`까지 번호가 지정되어 있습니다. 기사들은 무기점에서 무기를 구매하려고 합니다.

각 기사는 자신의 기사 번호의 약수 개수에 해당하는 공격력을 가진 무기를 구매하려 합니다. 단, 이웃나라와의 협약에 의해 공격력의 제한수치를 정하고, 제한수치보다 큰 공격력을 가진 무기를 구매해야 하는 기사는 협약기관에서 정한 공격력을 가지는 무기를 구매해야 합니다.

예를 들어, 15번으로 지정된 기사단원은 15의 약수가 1, 3, 5, 15로 4개 이므로, 공격력이 4인 무기를 구매합니다. 만약, 이웃나라와의 협약으로 정해진 공격력의 제한수치가 3이고 제한수치를 초과한 기사가 사용할 무기의 공격력이 2라면, 15번으로 지정된 기사단원은 무기점에서 공격력이 2인 무기를 구매합니다. 무기를 만들 때, 무기의 공격력 1당 1kg의 철이 필요합니다. 그래서 무기점에서 무기를 모두 만들기 위해 필요한 철의 무게를 미리 계산하려 합니다.

기사단원의 수를 나타내는 정수 `number`와 이웃나라와 협약으로 정해진 공격력의 제한수치를 나타내는 정수 `limit`와 제한수치를 초과한 기사가 사용할 무기의 공격력을 나타내는 정수 `power`가 주어졌을 때, 무기점의 주인이 무기를 모두 만들기 위해 필요한 철의 무게를 return 하는 solution 함수를 완성하시오.

---

### 제한사항

- 1 ≤ `number` ≤ 100,000
- 2 ≤ `limit` ≤ 100
- 1 ≤ `power` ≤ `limit`

---

### 입출력 예

| number | limit | power | result |
| --- | --- | --- | --- |
| 5 | 3 | 2 | 10 |
| 10 | 3 | 2 | 21 |

---

### 입출력 예 설명

**입출력 예 #1**

1부터 5까지의 약수의 개수는 순서대로 [1, 2, 2, 3, 2]개입니다. 모두 공격력 제한 수치인 3을 넘지 않기 때문에 필요한 철의 무게는 해당 수들의 합인 10이 됩니다. 따라서 10을 return 합니다.

**입출력 예 #2**

1부터 10까지의 약수의 개수는 순서대로 [1, 2, 2, 3, 2, 4, 2, 4, 3, 4]개입니다. 공격력의 제한수치가 3이기 때문에, 6, 8, 10번 기사는 공격력이 2인 무기를 구매합니다. 따라서 해당 수들의 합인 21을 return 합니다.

# 참고사항

---

[약수 갯수 (시간단축)](https://www.notion.so/501d4495150141e086d915af4b0644c8?pvs=21)

# 정답 (2024-06-08)

---

```kotlin
/**
 * 자신의 번호의 약수의 갯수 : 공격력
 *
 * @param number 기사단원의 수 (1 ~ 100,000)
 * @param limit 공격력의 제한 수치 (2 ~ 100)
 * @param power 제한수치를 초과한 기사가 사용할 무기의 공격력
 * @return Int 기사단원이 사용하는 무기의 모든 공격력의 합
 * */
fun solution(number: Int, limit: Int, power: Int): Int {
    val answer = (1..number).map {
        val attack = it.divisors().size
        if(attack > limit) power else attack
    }.reduce { acc, attack -> acc + attack }

    return answer
}

fun Int.divisors(): Set<Int> {
    // 1. 제곱근을 구한다.
    val sqrt = sqrt(this.toDouble()).toInt()

    // 2. 구한 제곱근에서 원래 숫자의 약수가 되는것을 선별한다.
    // sqrt % it 이 아닌 this % it 이다.
    val divisor1 = (1..sqrt).filter { this % it == 0 }

    // 3. 선별된 약수들로 원래 숫자를 나눈다.
    val divisor2 = divisor1.map { this / it }.sorted()

    // 4. divisor1 과 divisor2 의 리스트들을 합친다. (중복제거)
    return divisor1.toSet() + divisor2
}

```

- divisor1 을 구할때     // sqrt % it 이 아닌 this % it 이다. 를 참고

# 정답 (2023-02-17)

---

```kotlin
/**
 * @param number 기사단원의 수 ( <= 100,000)
 * @param limit 최대 공격력
 * @param power 최대 공격력 초과시 이 공격력을 사용
 * @return 모든 무기의 공격력
 * */
fun solution(number: Int, limit: Int, power: Int): Int {
    var answer: Int = 0

    //todo: 1 ~ number 까지의 모든 약수의 갯수가 들어있는 리스트를 만든다.
    val dmgList = mutableListOf<Int>()

    for(i in 1 .. number){
        val dmg = (i).divisors().size //데미지 = 약수의 갯수

        //todo: 약수갯수 리스트의 각 원소가 limit를 초과하는지 확인한다.
        //todo: 초과하면 원소의 값을 power 로 바꾼다.
        if(dmg > limit)  dmgList.add(power) else dmgList.add(dmg) //초과하지 않으면 약수갯수, 초과하면 limit
    }

    //todo: 리스트의 모든 원소의 합을 구한다.
    answer = dmgList.reduce { acc, i -> acc+i }

    return answer
}

/**
 * 약수들 구하기
 * @return List 약수들
 * */
fun Int.divisors() : List<Int> {
    val number = this

    //todo: number의 제곱근을 구한다.
    val sqrt = Math.sqrt(number.toDouble()).toInt()

    //todo: 1 ~ 제곱근까지의 숫자중 number 의 약수 리스트를 구한다.
    val list = mutableListOf<Int>()
    for(n in 1 .. sqrt) {
        if (number % n == 0) {
            list.add(n)

            //todo: number를 구한 약수들로 나눈 리스트를 구한다.
            //todo: 리스트 1과 리스트2를 합친다. (중복제거)
            val tmp = number / n
            if(tmp != n) list.add(tmp)
        }
    }

    return list
}
```

- number를 순회하며 각 원소의 약수들의 갯수를 구한다.
    - 약수의 갯수를 구하는 로직은 **제곱근을 사용하는 로직으로 해야된다. `O(√N)`**
    - **(주의) 프로그래머스는 sqrt를 바로 호출하면 안되고, `Math.sqrt()` 형식으로 호출해야 된다.**

- 약수의 갯수(dmg)가 limit 를 추가하면 power 로 바꾼다.

- 모든 dmg의 합을 구한다.
