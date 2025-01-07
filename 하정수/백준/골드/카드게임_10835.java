package 백준;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

/*
 *   카드게임_10835_골드5
 *
 *   뭔가 왼쪽카드를 입력받으면서 최댓값을 뽑아오고 오른쪽 카드를 입력받을 때,
 *   최댓값보다 크거나 같은 값 외에 작은 카드들의 합을 출력하면 될 것 같다.
 *
 *   틀렸다.. 이유는 오른쪽 카드를 마음대로 못버리기 때문이다.
 *
 *   DP는 정말 어렵다. 이해하기 힘들다.
 *
 * */

public class 카드게임_10835 {
    static int N, max;
    static int[] leftCards, rightCards;
    static int[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        leftCards = new int[N];
        rightCards = new int[N];
        dp = new int[N + 1][N + 1];

        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < N; i++) {
            leftCards[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < N; i++) {
            rightCards[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                dp[i][j] = -1; // dp배열 초기화
            }
        }

        max = play(0, 0);

        System.out.println(max);

        for (int i = 0; i <= N; i++) {
            for (int j = 0; j <= N; j++) {
                System.out.print(dp[i][j] + " ");
            }
            System.out.println();
        }
    }

    static int play(int left, int right) {
        if (right >= N || left >= N) return 0;

        if (dp[left][right] != -1) return dp[left][right];

        int case1 = play(left + 1, right);
        int case2 = play(left + 1, right + 1);
        int case3 = 0;
        if (leftCards[left] > rightCards[right]) {
            case3 = play(left, right + 1) + rightCards[right];
        }

        dp[left][right] = Math.max(case1, Math.max(case2, case3));

        return dp[left][right];

    }


//    static int N, max, sum, count;
//
//    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        StringTokenizer st;
//
//        N = Integer.parseInt(br.readLine());
//
//        max = Integer.MIN_VALUE;
//        sum = 0;
//
//        HashMap<Integer, Integer> map = new HashMap<>();
//
//        st = new StringTokenizer(br.readLine());
//        for (int i = 0; i < N; i++) {
//            int left = Integer.parseInt(st.nextToken());
//            if (left >= max) {
//                max = left;
//            }
//            map.put(left, i);
//        }
//
//        st = new StringTokenizer(br.readLine());
//
//        count = map.get(max);
//
//        for (int i = 0; i < N; i++) {
//            if (count == 0) {
//                while (true) {
//                    max--;
//                    if (map.containsKey(max)) {
//                        count = map.get(max);
//                        break;
//                    }
//                }
//            }
//            int card = Integer.parseInt(st.nextToken());
//            if (card < max && count > 0) {
//                sum += card;
//            } else count--;
//        }
//
//        System.out.println(sum);
//    }
}
