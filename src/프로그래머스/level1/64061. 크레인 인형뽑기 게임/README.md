# [Level-1] 크레인 인형뽑기 게임 - 64061

[크레인 인형뽑기 - 프로그래머스](https://school.programmers.co.kr/learn/courses/30/lessons/64061)

### **문제 설명**

게임개발자인 "죠르디"는 크레인 인형뽑기 기계를 모바일 게임으로 만들려고 합니다."죠르디"는 게임의 재미를 높이기 위해 화면 구성과 규칙을 다음과 같이 게임 로직에 반영하려고 합니다.

![크레인 문제](https://grepp-programmers.s3.ap-northeast-2.amazonaws.com/files/production/69f1cd36-09f4-4435-8363-b71a650f7448/crane_game_101.png
)

게임 화면은 **"1 x 1"** 크기의 칸들로 이루어진 **"N x N"** 크기의 정사각 격자이며 위쪽에는 크레인이 있고 오른쪽에는 바구니가 있습니다. (위 그림은 "5 x 5" 크기의 예시입니다). 각 격자 칸에는 다양한 인형이 들어 있으며 인형이 없는 칸은 빈칸입니다. 모든 인형은 "1 x 1" 크기의 격자 한 칸을 차지하며 **격자의 가장 아래 칸부터 차곡차곡 쌓여 있습니다.** 게임 사용자는 크레인을 좌우로 움직여서 멈춘 위치에서 가장 위에 있는 인형을 집어 올릴 수 있습니다. 집어 올린 인형은 바구니에 쌓이게 되는 데, 이때 바구니의 가장 아래 칸부터 인형이 순서대로 쌓이게 됩니다. 다음 그림은 [1번, 5번, 3번] 위치에서 순서대로 인형을 집어 올려 바구니에 담은 모습입니다.

![크레인 문제-2](https://grepp-programmers.s3.ap-northeast-2.amazonaws.com/files/production/638e2162-b1e4-4bbb-b0d7-62d31e97d75c/crane_game_102.png)

만약 같은 모양의 인형 두 개가 바구니에 연속해서 쌓이게 되면 두 인형은 터뜨려지면서 바구니에서 사라지게 됩니다. 위 상태에서 이어서 [5번] 위치에서 인형을 집어 바구니에 쌓으면 같은 모양 인형 **두 개**가 없어집니다.

![크레인 문제-3](https://grepp-programmers.s3.ap-northeast-2.amazonaws.com/files/production/8569d736-091e-4771-b2d3-7a6e95a20c22/crane_game_103.gif)

크레인 작동 시 인형이 집어지지 않는 경우는 없으나 만약 인형이 없는 곳에서 크레인을 작동시키는 경우에는 아무런 일도 일어나지 않습니다. 또한 바구니는 모든 인형이 들어갈 수 있을 만큼 충분히 크다고 가정합니다. (그림에서는 화면표시 제약으로 5칸만으로 표현하였음)

게임 화면의 격자의 상태가 담긴 2차원 배열 board와 인형을 집기 위해 크레인을 작동시킨 위치가 담긴 배열 moves가 매개변수로 주어질 때, 크레인을 모두 작동시킨 후 터트려져 사라진 인형의 개수를 return 하도록 solution 함수를 완성해주세요.

### **[제한사항]**

- board 배열은 2차원 배열로 크기는 "5 x 5" 이상 "30 x 30" 이하입니다.
- board의 각 칸에는 0 이상 100 이하인 정수가 담겨있습니다.
    - 0은 빈 칸을 나타냅니다.
    - 1 ~ 100의 각 숫자는 각기 다른 인형의 모양을 의미하며 같은 숫자는 같은 모양의 인형을 나타냅니다.
- moves 배열의 크기는 1 이상 1,000 이하입니다.
- moves 배열 각 원소들의 값은 1 이상이며 board 배열의 가로 크기 이하인 자연수입니다.

### **입출력 예**

| board | moves | result |
| --- | --- | --- |
| [[0,0,0,0,0],[0,0,1,0,3],[0,2,5,0,1],[4,2,4,4,2],[3,5,1,3,1]] | [1,5,3,5,1,2,1,4] | 4 |

### **입출력 예에 대한 설명**

**입출력 예 #1**

인형의 처음 상태는 문제에 주어진 예시와 같습니다. 크레인이 [1, 5, 3, 5, 1, 2, 1, 4] 번 위치에서 차례대로 인형을 집어서 바구니에 옮겨 담은 후, 상태는 아래 그림과 같으며 바구니에 담는 과정에서 터트려져 사라진 인형은 4개 입니다.

![크레인 문제-4](https://grepp-programmers.s3.ap-northeast-2.amazonaws.com/files/production/bb0f59c7-6b72-485a-8302-217fe53ea88f/crane_game_104.jpg)

# 정답

---

```kotlin
import java.util.*

fun solution(board: Array<IntArray>, moves: IntArray): Int {
    var answer = 0
    val stack: Stack<Int> = Stack()
    var count = 0

    moves.forEach { num ->
        //todo: board 에서 각 array를 순회 돌면서 (num-1) index의 자리가 0이 아닌것을 찾아야됨
        val index = (num-1)
        val filter = board.filter { it.get(index) != 0 }
        
				if(filter.isEmpty()) return@forEach //todo: 크레인이 빈곳에다가 헛손질 했을경우
        
        val level = filter.first() //제일 위에있는것을 가져온다.
        val doll = level.get(index)

        //call by value 가 아닌 call by reference 인듯, 원본 값도 변경됨
        level.set(index, 0)

        if(!stack.isEmpty() && stack.last() == doll){
            stack.pop()
            count++
        }
        else {
            stack.push(doll)
        }
    }
    
		//점수를 리턴하는게 아닌, 흭득한 모든 인형을 리턴하는것이니 * 2  
    answer = count * 2

    return answer
}
```

- **java.util. 에서 지원하는 stack 을 사용한다.**
    - 만약 stack을 지원 안하면, 직접 stack 자료구조를 사용한다.
    - pop → 마지막 아이템 반환 및 마지막 아이템 삭제
    - push → 마지막 인덱스에 아이템 삽입

- moves를 순회한다.
    - board 를 순회한다.
        - (num-1) 위치에 뽑기를 해야하므로 0이 아닌것들을 걸러낸다.
        - **걸러낸게 0이면 → 크레인이 빈곳에다가 헛손질 한것이므로 continue 한다.**
        - 걸러낸게 1개 이상이 존재하면 → 제일 위에있는 것만 가져온다.

        - 제일 위에있는 것들중에 해당하는 position 의 값을 빼오고, 대신 0으로 바꾼다.

        - 빼온 인형의 값이 스택의 마지막 값과 같은지 확인한다.
            - 같을 경우 → pop() , score 1점흭득 (count++)
            - 같지 않을경우 → push(인형)


- 모든 인형의 갯수를 반환하는 것이니 점수 * 2를 해준다.
