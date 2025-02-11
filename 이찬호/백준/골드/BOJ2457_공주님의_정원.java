import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
3/1부터 11/30까지 매일 꽃이 한가지 이상 피어있도록 한다
꽃들의 수를 가능한 적게 한다.
=========================
입력:
    N(1<=N<=100,000)
    N줄동안 꽃 정보 (피는 달, 피는 일, 지는 달, 지는 일)
========================
그리디 문제
3월 1일부터 11월 30일까지 계속 꽃이 피어있어야함
피는 날짜 순서대로 정렬을 한다.
현재꽃지는날, 다음꽃지는날을 변수로 설정해놓음.
그 후 배열을 순회하며 다음 꽃의 피는 날짜가 지는날 안에 있는지 확인함
있다면 다음 지는날을 다음 꽃의 지는 날짜로 정해놓음
    만약 이미 정해져있다면 더 긴놈으로 선택
다음 꽃의 피는 날짜가 지는날을 넘어가면 다음놈이 선택되어있는지 확인후
있다면 꽃선택 ++ 없다면 break
만약 다음꽃피는날짜가 12월이 넘어가면 ++후 break

 */
public class BOJ2457_공주님의_정원 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int[][] flowers = new int[N][4];
        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<4; j++){
                flowers[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        Arrays.sort(flowers, (a,b) -> a[0]!=b[0]? a[0]-b[0] : a[1]-b[1]);
        int curM = 3;
        int curD = 1;
        int nextM = 0;
        int nextD = 0;
        int ans = 0;
        boolean flag = false;
        //System.out.println(Arrays.deepToString(flowers));
        for(int i=0; i<flowers.length; i++){
            //System.out.println("============");
            //System.out.println("curM = " + curM+" curD = " + curD+" nextM = " + nextM+" nextD = " + nextD+" ans = " + ans);

            if(flowers[i][0]>curM || (flowers[i][0]==curM&&flowers[i][1]>curD)) {
                if(nextM==0) break;
                curM = nextM;
                curD = nextD;
                nextM = 0;
                nextD = 0;
                ans++;
            }

            if(flowers[i][0] < curM || (flowers[i][0]==curM && flowers[i][1] <=curD)){
                if(nextM<flowers[i][2] || (nextM==flowers[i][2] && nextD<flowers[i][3])){
                    nextM = flowers[i][2];
                    nextD = flowers[i][3];
                }
            }
            if(nextM>=12) {
                ans++;
                flag = true;
                break;
            }

        }
        System.out.println(flag? ans : 0);

    }
}
