package 백준;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


/*
 *  상자넣기_1965_실버2
 *
 *
 *  dp에 관해서는 문제를 풀어본 적이 없기 때문에 https://sskl660.tistory.com/89 사이트를 참고했다.
 *  설명이 아주 잘 나와있음.
 *
 *  LIS로 푸는 방법과 이분탐색을 이용한 dp로 푸는 방법이 있는데 dp 연습할 겸 후자로 풀어보겠다.
 *
 *  LIS로 풀면 시간복잡도는 N^2, 이분탐색을 이용해서 풀면 시간복잡도는 NlogN 
 *
 * */
public class 상자넣기_1965 {

    static int N;
    static int[] arr, dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        arr = new int[N];
        dp = new int[N];

        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int length = 0;

        for (int i = 0; i < N; i++) {
            int idx = BinarySearch(arr[i], 0, length, length + 1);

            if (idx == -1) {
                dp[length] = arr[i]; // 가장 마지막 위치에 원소를 넣고 length의 길이를 늘려준다.
                length++;
            } else {
                dp[idx] = arr[i]; // 현재 값이 dp배열 안에 있는 값 중에 작거나 같은 값이 있다면 dp값을 갱신해준다.
            }
        }

        System.out.println(length);
    }

    public static int BinarySearch(int num, int start, int end, int size) {
        int res = 0;
        while (start <= end) {
            int mid = (start + end) / 2;
            if (num <= dp[mid]) { // 현재 선택된 값이 dp배열 원소보다 작거나 같다면, res값에 저장하고 앞 부분을 더 탐색한다.
                res = mid;
                end = mid - 1;
            } else { // 만약 해당 원소보다 크다면 갱신하지 않아도 되므로 뒷 부분을 탐색한다.
                start = mid + 1;
            }
        }
        if (start == size) { // 갱신할 위치를 찾지 못한 경우(dp 배열의 모든 수보다 큰 경우)
            return -1;
        } else {
            return res; // dp 배열에서 삽일 될 위치를 찾은 경우
        }
    }
}
