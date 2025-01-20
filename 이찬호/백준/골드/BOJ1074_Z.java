import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


/*
2^N x 2^N 배열을 z모양으로 탐색하기
N이 1인경우가 기저조건
2보다 큰 경우 행, 열 나누기 2를 해서 z모양으로 재귀 탐색
전형적인 분할정복문제
==========================
입력:
    N, r, c (1<=N<=15)
==========================
r행 c열을 몇번째로 방문했는지 출력하자.
==========================
N=1일때 기저조건
나머지는Z순서대로 탐색하기
방법은 두가지가 있다.
1. 그냥 처음부터 순서대로 돌아서 방문 순서를 그래프에 다적고 나중에 R,C 출력
    -> 최대 32000*32000 = 1억이 훨씬 넘어가서 터진다.
2. 해당 위치를 다 들어가보지 않고 값을 더해줘서 바로 정답뽑기
    -> N을 나누어서 가중치를 더해주면 된다!!

기저조건은 N=1일때 r*2+c

먼저 r,c를 2^N / 2 한 값보다 큰지 작은지 확인
r이 작고 c가 작을때 => 0
r이 작고 c는 클때 => 1
r이 크고 c는 작을때 => 2
r이 크고 c도 클때 => 3

위의 값에 N/2*N/2 한 값을 곱해줌
그 후 y,x 좌표를 수정, N도 N-1을 해줌
y,x좌표는 N/2보다 크다면 N/2를 빼주고 아니라면 그대로 넣어주기

 */
public class BOJ1074Z {
    static int N,r,c;
    static int ans = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        solution(N,r,c, 0);

        System.out.println(ans);


    }

    private static void solution(int depth, int y, int x, int sum) {
        if (depth == 1) {
            sum += y*2+x;
            ans = sum;
            return;
        }

        int n = (int)Math.pow(2, depth) / 2;

        if(y<n){
            if(x<n){
                solution(depth-1, y, x, sum);
            }
            else{
                solution(depth-1, y, x-n, sum+n*n);
            }
        } else{
            if(x<n) {
                solution(depth-1, y-n, x, sum+n*n*2);
            }
            else{
                solution(depth-1, y-n, x-n, sum+n*n*3);
            }
        }
    }
}
