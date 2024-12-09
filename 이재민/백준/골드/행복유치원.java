package algo1209;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
    각 그룹을 구분 짓는 것을 막대라고 생각하고
    K개의 그룹이면 N-K개의 막대로 그룹을 나눠줌
    15 4
    4 5 12 14 15 18 | 29 32 36 | 55 | 70 76 82 87 95
    이런식으로 나눌 수 있는데
    가장 큰 차이가 나는 사람들을 막대로 그룹 나눠줌
    29 32 36에서 36 - 29 => (32-29) + (36-32)와 같음
    가장 큰 차이가 나는 사람들을 위와 같이 더해주는걸 막는 것
    62744KB | 464ms
 */

public class 행복유치원 {

    static int N, K;
    static int[] height;

    public static int minCost() {
        int[] diff = new int[N-1];
        for (int i = 0; i < N - 1; i++) {
            diff[i] = height[i + 1] - height[i];
        }

        Arrays.sort(diff);

        int cost = 0;
        for (int i = 0; i < N - K; i++) {
            cost += diff[i];
        }

        return cost;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        height = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            height[i] = Integer.parseInt(st.nextToken());
        }
        System.out.println(minCost());
    }
}

