package study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ6236_용돈관리 {
    static int N, M;
    static int max=0, min=0;
    static int[] prices;
    static int answer;
    public static void main(String[] args) throws IOException {
        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        prices = new int[N];

        for(int i=0; i<N; i++) {
            int price = Integer.parseInt(br.readLine());

            prices[i] = price;
            max+=price;
            min = Math.max(min, price);
        }
        binarysearch(min, max);
        System.out.println(answer);
    }

    static void binarysearch(int start, int end) {
        if(start>=end) {
            answer = start;
            return;
        }

        int mid = (start+end)/2;

        int rest_money = mid;
        int cnt =1;
        for(int i=0; i<N; i++) {
            if(prices[i]>rest_money) {
                rest_money=mid;
                cnt++;
            }
            rest_money-=prices[i];
        }
        if(cnt>M) binarysearch(mid+1, end);
        else binarysearch(start, mid);
    }
}
