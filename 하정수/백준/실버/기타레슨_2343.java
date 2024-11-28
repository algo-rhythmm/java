import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/* 시작시간 : 2024-11-27 15:30
 *  N개의 강의 M개의 블루레이
 *  각 강의 길이가 분단위로 주어짐
 *  각 강의의 길이는 10,000분을 넘지 않음.
 *  int형 N,M 선언
 *  크기 순으로 길이를 준다는 말은 없음(sort 를 하면 강의 순서가 바뀌기 때문에 하면 안됨)
 *  이분탐색 쓰는 거 같은데 어케 풀지..
 *  죄송합니다 여러분. 이분탐색을 풀어본 적이 없어서 푸는 방법을 참고했습니다 ㅜㅜ
 *
 *
 * 종료시간 : 2024-11-27 16:33
 * */

public class 기타레슨_2343 {
    static int N, M;
    static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new int[N];
        st = new StringTokenizer(br.readLine());
        int start = 0;
        int end = 0;

        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            if(start < arr[i]){
                start = arr[i];
            }
            end = end + arr[i];
        }

        while(start <= end){
            int mid = (start + end) / 2;
            int sum = 0;
            int cnt = 0;
            for(int i=0; i<N;i++){
                if(sum + arr[i] > mid){
                    cnt++;
                    sum = 0;
                }
                sum = sum + arr[i];
            }
            if(sum !=0){
                cnt++;
            }
            if(cnt > M){
                start = mid + 1;
            }
            else{
                end = mid - 1;
            }
        }
        System.out.println(start);

    }
}
