package 백준;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
*   처음에 문제 이해를 잘못했다.
*   모든 강의가 가능하도록 해야하는데 그저 최소 강의실만 찾으려고 HashMap을 사용했다가 틀렸다.
*   문제를 잘 읽자..
*
* */

public class 강의실배정_11000 {
    static int N;
    static int[][] arr;
    static PriorityQueue<Integer> pq = new PriorityQueue<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        arr = new int[N][2];

        for(int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine());
            arr[i][0] = Integer.parseInt(st.nextToken());
            arr[i][1] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr, (o1,o2)->{
            if(o1[0] == o2[0]){
                return o1[1] - o2[1];
            }
            return o1[0] - o2[0];
        });

        pq.add(arr[0][1]);

        for(int i=1; i<N;i++){
            if(pq.peek() <= arr[i][0]){
                pq.poll();
            }

            pq.add(arr[i][1]);
        }

        System.out.println(pq.size());
    }
}
