import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 *   용돈관리_6236_실버1
 *   이분탐색 문제
 *   이분탐색 문제는 틀이 거의 비슷한 것 같다.
 *   시작점은 배열값과 비교하여 큰 값을 시작점에 넣고
 *   끝점은 배열값들을 전부 더한 값으로 설정한다.
 *   후에 start가 end보다 작을 동안 반복문을 돌리고
 *   mid 값을 start 와 end 를 더한 값의 1/2를 해준다
 *   그 다음, sum 값을 0으로 초기화 해주고, count 값은 0이 아닌 1로 초기화한다.
 *   이유는 한번 뽑은 돈으로 모든 날을 쓸 수 있다면 count 증가가 되지 않아서 0으로 되기 때문에 1로 초기화한다.
 *   그 다음은 count 가 원하는 값 M 을 넘으면 인출량을 늘려야 해서 mid + 1 값으로 해주고
 *   M보다 작거나 같다면 인출량을 줄여야 하기 때문에 mid - 1 값으로 해준다.
 * */
public class 용돈관리_6236 {
    static int N, M, K;
    static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[N];

        int start = 1;
        int end = 0;

        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
            start = Math.max(start, arr[i]);
            end += arr[i];
        }

        int count, mid, sum;

        while (start <= end) {
            mid = (start + end) / 2;

            sum = 0;
            count = 1;

            for (int i = 0; i < N; i++) {
                sum += arr[i];
                if (sum > mid) {
                    sum = arr[i];
                    count++;
                }
            }
            if (count > M) start = mid + 1;
            else end = mid - 1;
        }

        System.out.println(start);
    }
}
