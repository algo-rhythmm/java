package algo1202;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
    N일돈안 자신이 사용할 금액 계산

    M번만 통장에서 돈을 뺌

    K원 인출
    하루 보낼 수 있으면 사용 모자라면 남은 금액 넣고
    다시 K원 인출

    M번 맞추기 위해 남은 금액이 그날 사용할 금액보다 많아도
    다시 집어넣고 다시 K원 인출 가능

    K를 최소화 하기로함

    N의 범위, 탐색 및 검증 => 이분탐색
    20880KB | 180ms
 */

public class 용돈관리 {

    static int N, M;
    static int[] money;


    static int binary(int l, int r){
        while(l <= r){
            int m = (l+r)/2;

            int sum = 0;
            int cnt = 1;

            for(int i=0; i<N; i++){
                sum += money[i];
                if(sum > m){
                    sum = money[i];
                    cnt++;
                }
            }

            if(cnt > M) l = m+1;
            else r = m-1;

        }

        return l;
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        money = new int[N];


        int l = 0, r = 0;
        for(int i=0; i<N; i++){
            money[i] = Integer.parseInt(br.readLine());
            l = Math.max(l, money[i]);
            r += money[i];
        }

        System.out.println(binary(l, r));
    }
}
