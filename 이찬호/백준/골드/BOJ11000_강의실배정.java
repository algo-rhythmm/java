import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
S에 시작해서 T에 끝나는 N개의 수업
모든 수업을 최소의 강의실을 사용해서 하자
========================
입력:
    N (1<=N<=200,000)
    S, T (0<=S, T<= 10억)
======================
N이 20만까지 인걸 보면 dp문제인거같다.
모든 수업을 "최소의 강의실"에서 해야함
A수업에서 S, T까지 한 강의실을 이용=>다른 수업에서는 이 강의실을 T-1까지 사용못함
또한 S,T가 최대 10억까지 가능하므로 일수를 기준으로 단순 +1 해주는 테이블은 안됨
N개의 수업들을 기준으로 테이블을 짜야한다.

아무리 생각해바도 dp자료구조가 안나왔는데 그리디다.
먼저 시작일 기준으로 정렬을 한 후
해당 종료일을 어디에 담아놓자.
그 후 다음 시작일이 해당 종료일보다 낮으면 그 종료일을 큐에 넣고 큐 사이즈가
필요한 강의실 갯수가된다.
만약 같거나 높으면 해당 종료일 날짜를 큐에서 빼준다. (다 뺄때까지 반복)
그렇다면 그 큐는 pq여야할듯

 */
public class BOJ11000강의실배정 {

    public static void main(String[] args) throws IOException {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            StringTokenizer st;

            int N = Integer.parseInt(br.readLine());
            int[][] arr = new int[N][2];
            for(int i=0; i<N; i++){
                st = new StringTokenizer(br.readLine());
                arr[i][0] = Integer.parseInt(st.nextToken());
                arr[i][1] = Integer.parseInt(st.nextToken());
            }
            Arrays.sort(arr, (a,b)->a[0]-b[0]);
            PriorityQueue<Integer> pq = new PriorityQueue<>();

            //방법 1. 664ms (체크를 마지막에 한번만함)
//            for(int i=0; i<N; i++){
//                if(!pq.isEmpty() && pq.peek()<=arr[i][0]){
//                    pq.poll();
//                }
//                pq.offer(arr[i][1]);
//            }
//            System.out.println(pq.size());

            //방법 2. 732ms
            int ans = 1;
            pq.offer(arr[0][1]);
            for(int i=1; i<N; i++){
                //pq안에 아무것도 없거나, 시작일이 종료일보다 더 작을때
                if(pq.peek()==null || arr[i][0] <pq.peek()){
                    pq.offer(arr[i][1]);
                    ans = Math.max(ans,pq.size());
                }
                //시작일이 종료일 이상일 때
                else{
                    //pq안에 종료일이 있고 또 해당 시작일이 종료일 이상일때
                    while(!pq.isEmpty()&&arr[i][0]>=pq.peek()){
                        pq.poll();
                    }
                    //그 후 마지막에 종료일을 또 넣어줘야함..!
                    pq.offer(arr[i][1]);
                }
            }
            System.out.println(ans);


    }
}
