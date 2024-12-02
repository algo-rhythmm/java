import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// 완탐으로 했다가 시간초과나서 실패
// 정답보고 이분탐색으로 풀었습니다.
public class BOJ6236_용돈관리 {

    static int N, M;
    static int[] arr;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[N];

        int min = 0;
        int max = 0;

        for(int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
            min = Math.max(min, arr[i]);
            max += arr[i];
        }

        // 이분탐색
        int mid, count, sum;
        while(min <= max) {
            mid = (max + min) / 2;
            sum = 0;
            count = 1;

            for(int i = 0; i < N; i++) {
                sum += arr[i];
                if(sum > mid) { //인출해야하는 경우
                    sum = arr[i];
                    count++;
                }
                if(count > M) break;
            }
            if(count > M) min = mid + 1;
            else max = mid - 1;
        }

        System.out.println(min);
        
    }

    // 인출금액, 남은 금액, 인출 횟수, 인덱스
    static boolean dfs(int draw, int left, int count, int idx) {
        if(idx == N) {
            return true;
        }

        if(left >= arr[idx]) {
            return dfs(draw, left - arr[idx], count, idx + 1);
        } else {
            if(draw >= arr[idx] && count + 1 <= M) return dfs(draw, draw - arr[idx], count + 1, idx + 1);
            else return false;
        }
    }

}