package week03;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
같은 숫자가 적힌 카드가 여러장있을 수 있다.
짝수개의 카드를 뒤 섞은 후 같은 개수의 두 더미로 나눈다.
이제 각 더미의 제일 위에있는 카드끼리 비교하며 게임을 한다.
1. 언제든 왼쪽카드만 통에 버릴 수 있고 왼,오 둘다 버릴 수 있다. 이때 점수는 없다
2. 오른쪽카드에 적힌 수가 왼쪽보다 작은 경우에는 오른쪽 카드만 통에 버릴 수 있다.
   이때 오른쪽 카드에 적힌 수만큼 점수를 얻는다.
3. 위 규칙에 따라 어느쪽 더미든 남은 카드가 없다면 게임이 끝난다.
최대로 점수를 얻을 수 있는 값을 출력하자.
=======================================
입력:
    N : 한 더미 카드의 갯수 (1<=N<=2,000)
    A : 왼쪽 더미 카드 정보 (양의 정수)
    B : 오른쪽 더미 카드 정보 (양의 정수)
=======================================
카드 패가 이미 다 공개가 되어 있으니 최대로 점수를 얻으려면 어떻게 해야하는지 찾아야함
완전탐색 => 왼쪽만 버리거나 둘다 버리거나, 조건에 맞으면 오른쪽만 버리거나
결국 점수를 먹을 때는 오른쪽만 버릴때, 즉 왼쪽보다 오른쪽이 작을때
오른쪽이 크면 점수를 그만큼 크게 먹을 수 있지만 왼쪽에 더 큰 숫자가 나오길 바래야함
왼쪽보다 작은 숫자가 나오면 무조건 먹기,
오른쪽이 큰 숫자가 나온다면 왼 오 둘다 버릴 것인지 아니면 왼만 버릴 것인지 선택해야함
만약 왼쪽 마지막에 엄청 큰 숫자가 있다면 왼쪽만 계속 버려서 나중에 오른쪽만 다 먹는
것도 가능한 경우이다.
흠.. dp인가

오른쪽이 큰 숫자가 나올 때 일단 왼 오 둘다 버린다고 가정하고 다른 변수에 저장시켜놓기
그래서 그 값을 누적시켜놨다가 왼쪽에 해당 값보다 더 큰 값이 나오면 누적된 값들
다 한번에 더하기
그냥 N이 2000이니까 왼쪽값 하나씩 오른쪽값 2000번 다 돌아보면 안되나
->안되는 이유 오른쪽이 더 크면 오른쪽만 못버림...

1.오른쪽이 더 작으면 점수 더하기
2.왼쪽이 더 큰 경우
    2-1. 왼쪽값만 버리기 => 추후에 왼쪽에 더 큰 값이 나올 수도 있는 경우
    2-2. 왼쪽 오른쪽 둘다 버리기 => 아닐 경우..

왼쪽 최댓값 저장해놓고 오른쪽값이 최댓값보다 낮으면 왼쪽만 버리는걸로 했는데
이게 안되는 이유는 최댓값보다 큰 값이 오른쪽에 있다면 결국 왼쪽만 버려야 하기 때문
즉 둘다 버려서 오른쪽 큰 값을 버려야 하는 경우도 있다.

처음생각했던 것처럼  dp가 맞았고 정확하게는 메모이제이션 방식
재귀를 이용해 풀어보자

 */
public class BOJ10835카드게임 {
    static int N;
    static int[][] dp;
    static int[] A;
    static int[] B;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        //왼, 오 버린 갯수만큼 초기화시켜줌, 인덱스 터지는걸 방지하기 위해 +1
        dp = new int[N+1][N+1];
        for(int i=0; i<N+1; i++) Arrays.fill(dp[i], Integer.MIN_VALUE);
        A = new int[N];
        B = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            B[i] = Integer.parseInt(st.nextToken());
        }

        int ans = sol(0,0);
        System.out.println(ans);


    }

    public static int sol(int l, int r){
        //다뽑았으면 0 리턴
        if(l==N || r== N) return 0;

        //만약 값이 있다면 리턴
        if(dp[l][r] != Integer.MIN_VALUE) return dp[l][r];

        //오른쪽 값이 더 작다면 값 더해주고 오른쪽 카드 버리기
        if(A[l]>B[r]) return dp[l][r] = B[r] + sol(l,r+1);
        //왼쪽 값이 더 작다면 둘중 더 큰값으로 리턴 해주기
        else return dp[l][r] = Math.max(sol(l+1,r+1), sol(l+1,r));
    }
}
