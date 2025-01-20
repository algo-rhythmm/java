package week03;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
고이는 빗물의 총량 계산하기
옛날에 스택썼다가 개발린문제
====================================
입력:
    H,W (1<=H,W<=500)
    바닥 상태
====================================
현대 바닥을 기준으로 나보다 높은 기둥중 가장 낮은 것이 고이는 총량
그걸 모든 바닥마다 시행한다
만약 좌우에 다 검사후 나보다 높은게 하나라도 없다면 안고이는것임 pass
 */
public class BOJ14719빗물 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int H,W;
        int[] arr;
        st = new StringTokenizer(br.readLine());
        H = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());
        arr = new int[W];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < W; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int ans = 0;

        for(int i=1; i<W; i++){
            int cur = arr[i];
            int left = 0;
            int right = 0;
            for(int j=i-1; j>=0; j--){
                left = Math.max(left, arr[j]);
            }
            for(int j=i+1; j<W; j++){
                right = Math.max(right, arr[j]);
            }
            if(Math.min(left, right) <=cur) continue;
            ans += Math.min(left,right)-cur;
        }

        System.out.println(ans);

    }
}
