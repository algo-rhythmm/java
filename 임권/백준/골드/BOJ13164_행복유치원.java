import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ13164_행복유치원 {
    static PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
    static int N, K, result = 0; 

    // 가장 차가 많은 구간만 찾아서 빼주면 된다.
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int pre = Integer.parseInt(st.nextToken());
        int tmp = pre;
        for(int n = 1; n < N; n++) {
            int cur = Integer.parseInt(st.nextToken());     //이전 원생의 키와 비교하여 차이만 기록한다.
            int distance = cur - pre;
            pq.offer(distance);
            pre = cur;
        }
        result = pre - tmp;     //가장 작은 원생과 큰 원생의 차로 초기화
        
        // K - 1 만큼만 가장 큰 차이를 보이는 구간을 빼주면 된다.
        for(int k = 0; k < K - 1; k++) {
            result -= pq.poll();
        }

        System.out.println(result);

    }
}
