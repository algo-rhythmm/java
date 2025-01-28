import java.io.*;
import java.util.*;

public class 행복_유치원 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken()); // 원생 수
        int K = Integer.parseInt(st.nextToken()); // 그룹 수
        st = new StringTokenizer(br.readLine());

        int[] heights = new int[N];
        for (int i = 0; i < N; i++) {
            heights[i] = Integer.parseInt(st.nextToken());
        }
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> a - b);

        int[] diffs = new int[N - 1];
        for (int i = 0; i < N - 1; i++) {
            diffs[i] = heights[i + 1] - heights[i];
            pq.add(diffs[i]);
        }
        int result = 0;

        for(int i =0; i<N-K; i++){
            result+= pq.poll();
        }

        System.out.println(result);
    }
}
