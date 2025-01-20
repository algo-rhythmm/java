import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
슬라이딩윈도우?
================
입력:
    N (1<=N<=1,000,000)
    M (2N+1<= M <=1,000,000)
    S
================
처음 정답 문자열 str 만들기
    I로 시작후 N번만큼 OI를 붙여주면됨
그 후 left는 0, right는 N*2만큼 설정후 문자열 비교해주기
같다면 +=2, 같지 않다면 +=1

subString으로 날먹 실패 투포인터 개념으로 문자열을 한번만 순회해야 한다.
문자열 관련 연산은 굉장히 오래 걸리기 때문
 */
public class BOJ5525IOIOI {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());
        String S = br.readLine();

        int ans = 0;
        int cnt = 0;

        // M-1까지 해줘야 인덱스 안터짐
        for(int i=1; i<M-1; i++){

            if(S.charAt(i) == 'O' && S.charAt(i+1)=='I'){
                cnt++;
                if(cnt==N){
                    if(S.charAt(i-2*cnt+1)=='I') ans++;
                    cnt--;
                }
                i++;
            } else{
                cnt = 0;
            }
        }


        System.out.println(ans);


    }
}
