package week02;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*
파이어볼 M개
각 파이어볼의 위치는 r,c 질량은 m, 방향은 d, 속력은 s이다.
격자의 행과 열은 1번부터 N번까지 번호가 매겨져있고 1번행은 N번행, 1번열은 N번열과 연결되어있다.
(즉 범위를 벗어나면 끝으로 간다는 뜻)
파이어볼 방향은 위에서부터 0, 시계방향으로 +1씩 총 7까지 있다.
파이어볼에게 명령을 이동하면 다음 일들이 일어난다.
1. 모든 파이어볼이 자신의 방향 d로 속력 s만큼 이동
    이동 중 같은 칸에 여러개의 파이어볼이 있을 수 있다.
2. 이동 뒤 2개 이상의 파이어볼이 있는 칸에는 다음과 같은 일이 일어난다.
    2-1.파이어볼은 모두 하나로 합쳐진다.
    2-2.파이어볼은 4개의 파이어볼로 나누어진다.
    2-3.나누어진 파이어볼의 속력, 방향은 다음과 같다.
        질량은 L/5
        속력은 L/합쳐진 파이어볼의 개수
        합쳐지는 파이어볼의 방향이 모두 홀수이거나 짝수이면 방향은 0,2,4,6 아니면 1,3,5,7
        질량이 0인 파이어볼은 소멸된다.
마법사가 이동을 K번 명령한 후 남아있는 파이어볼 질량의 합을 구하자.
===================================================
입력:
    N,M,K
    M개의 줄동안 파이어볼의 정보가 하나씩 주어진다. r,c,m.s,d
    파이어볼의 위치가 같은 경우는 주어지지 않는다.
===========================================
이동 로직
    큐를 생성해서 거기에 차곡차곡 집어넣고 하나씩 빼서 사용
    그 후 이동한 좌표를 새로운 그래프에 그려넣음
    만약 해당 좌표에 이미 파이어볼이 있으면 합치는 로직 구현 (나누지는 않기)
    이동할 때 범위를 벗어나면 반대로 튕기는 것 구현 (굉장히 어려워따..)
겹치는게 있는지 판단하는 로직
    2중for문으로 다돌면서 만약 파이어볼이 하나만 있다면 다음 이동큐에 넣기
    겹쳐서 있다면 나눈후 나눠진 것을 다음 이동큐에 넣기
나누는 로직
    질량이 0이면 소멸
    아니라면 4개가 각각 다른방향을 바라보면서 재생성됨.

 */

public class BOJ20056마법사상어 {
    static int N,M,K;
    static int[][] dir = {{-1,0},{-1,1},{0,1},{1,1},{1,0},{1,-1},{0,-1},{-1,-1}};
    static Ball[][] graph;
    static Queue<Ball> moveQ = new ArrayDeque<>();
    static class Ball {
        int r,c,m,s,d;
        //합쳐진 파이어볼의 개수
        int num = 1;
        //방향 정보 저장
        ArrayList<Integer> list = new ArrayList<>();
        public Ball(int r,int c,int m,int s,int d){
            this.r=r;
            this.c=c;
            this.m=m;
            this.s=s;
            this.d=d;
            list.add(d);
        }

        //Ball 클래스 그래프에서 움직임 표현
        public void move(){
//            System.out.println("Time to move============");
//            System.out.println("r="+this.r+",c="+this.c);
            int r = calcNum(this.r, s, d, 0);
            int c = calcNum(this.c, s, d, 1);
//            System.out.println("r="+r+",c="+c);
            //그 후 객체들의 r,c좌표 꼭 업데이트 해주기
            this.r=r;
            this.c=c;
            //이미 파이어볼 존재할시 합치기.
            if(graph[r][c]!=null){
                graph[r][c].merge(this);
            }
            else {
                graph[r][c] = this;
            }
        }

        //만약 해당좌표에 이미 ball이 있다면 합치는 함수
        public void merge(Ball b){
            this.m = (this.m+b.m); //질량 더해놓고 나중에 나누기
            this.s = (this.s+b.s); //속도도 마찬가지
            this.num++;
            this.list.add(b.d);
        }

        //다 이동시킨 후 이제 나눠주는 작업
        public void divide(){
            //혼자라면 큐에 추가만 해주기
            if(this.num==1) {
                moveQ.add(this);
                return;
            }
            //질량이 5보다 낮다면 소멸시키기
            if(this.m<5){
                graph[this.r][this.c] = null;
                return;
            }
            int r = this.r;
            int c = this.c;
            int m = this.m/5;
            int s = this.s/num;
            int temp = list.get(0)%2;
            boolean flag = true;
            for(int d : list){
                if(d%2!=temp) {
                    flag = false;
                    break;
                }
            }
            if(flag){
                for(int i=0; i<8; i+=2){
                    moveQ.add(new Ball(r,c,m,s,i));
                }
            } else{
                for(int i=1; i<8; i+=2){
                    moveQ.add(new Ball(r,c,m,s,i));
                }
            }
        }

        @Override
        public String toString() {
            return "Ball{" +
                    "r=" + r +
                    ", c=" + c +
                    ", m=" + m +
                    ", s=" + s +
                    ", d=" + d +
                    ", num=" + num +
                    ", list=" + list +
                    '}';
        }
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        graph = new Ball[N][N];

        for(int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken())-1;
            int c = Integer.parseInt(st.nextToken())-1;
            int m = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            Ball ball = new Ball(r,c,m,s,d);
            //큐에 하나씩 넣기
            moveQ.add(ball);
        }

        for(int i=0; i<K; i++){
            graph = new Ball[N][N];
            while(!moveQ.isEmpty()){
                //System.out.println("move Q =====================");
                Ball ball = moveQ.poll();
                //System.out.println(ball);
                ball.move();
            }

            //System.out.println("plus ====================");
            for(int j=0; j<N; j++){
                for(int k=0; k<N; k++){
                    if(graph[j][k]==null) continue;
                    //System.out.println(graph[j][k]);
                    graph[j][k].divide();
                }
            }

        }
        //System.out.println(Arrays.deepToString(graph));
        int ans = 0;
        //System.out.println("ans======================");
        while(!moveQ.isEmpty()) {
            Ball b = moveQ.poll();
            //System.out.println(b);
            ans += b.m;
        }
        System.out.println(ans);

    }

    public static int calcNum(int num, int s, int d, int param){
        int tmp = num+(s%N*dir[d][param]);
        return tmp>=0?tmp%N:tmp+N;
    }
}
