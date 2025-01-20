import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
G킬로그램이 더 쪘다
G란 현재 몸무게의 제곱에서 성원이가 기억하고 있던 몸무게의 제곱을 뺀 것이다.
가능한 현재 몸무게를 오름차순으로 출력하라
없을때는 -1을 출력하라
즉 현재몸무게^2 - 과거몸무게^2 = G를 만족하는 모든 현재몸무게를 구하라
======================
입력:
    G (1<=G<=100,000)
======================
투포인터인가?
1,2 부터 과거, 현재 몸무게로 시작
만약 둘의 차이가 G보다 작으면 오른쪽 포인터 전진
크면 왼쪽 포인터 전진
둘의 차이가 G랑 같다면 right값을 정답값에 추가
종료조건은.... left>right라면
 */
public class BOJ1484다이어트 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int G = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        int left = 1;
        int right = 2;


        while (left < right) {
            int diff = right * right - left * left;

            //둘의 차이가 G 이하라면 오른쪽 포인터 옮기기
            if (diff <= G) {
                if (diff == G) {
                    sb.append(right).append("\n");
                }
                right++;
            }
            //아니라면 왼쪽 포인터 옮기기
            else {
                left++;
            }
        }
        System.out.println(sb.isEmpty() ? -1 : sb);


    }
}
