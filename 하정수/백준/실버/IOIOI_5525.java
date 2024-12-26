package 백준;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
 *   IOIOI_5525_실버1
 *   S가 I,O로만 이루어져있고 Pn이 S안에 몇군데 들어가는지 구하는 프로그램.
 *
 *   N에 따라 스트링을 초기화한다.
 *   S가 'I' 로 시작하면 그로부터 N*2+1 만큼 concat하여 문자열을 분리해주고
 *   equals를 이용해여 s 와 같으면 ans를 증가시킨다.
 *
 *   .....
 *   이렇게 하니까 50점 밖에 못받는다. 아무래도 입력에 제한이 없는 경우에는
 *   일일히 검사할 때 시간과 메모리가 터지기 때문인 것 같다.
 *
 *
 * */

public class IOIOI_5525 {
    static int N, M, count, ans;
    static String S;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        S = br.readLine();

        count = 0;
        ans = 0;

        for (int i = 1; i < M - 1; i++) {
            if (S.charAt(i - 1) == 'I' && S.charAt(i) == 'O' && S.charAt(i + 1) == 'I') {
                count++;
                i++;

                if (count == N) {
                    ans++;
                    count--;
                }
            } else {
                count = 0;
            }
        }

        System.out.println(ans);

    }
}
