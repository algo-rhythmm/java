package algo0220;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
    전구와 스위치
    24248KB | 148ms
 */

public class 전구와스위치 {

    static int N;
    static String a, b;
    static boolean[] e;
    static int res = Integer.MAX_VALUE;

    static void switchChange(int idx, int cnt, boolean[] s) {
        if(idx == N) {
            if(s[idx-1] == e[idx-1]) {
                res = Math.min(res, cnt);
            }
            return ;
        }

        if(s[idx-1] != e[idx-1]) {
            for(int i = idx-1; i <= idx+1; i++) {
                if(i < N) {
                    s[i] = !s[i];
                }
            }
            switchChange(idx+1, cnt+1, s);
        }
        else{
            switchChange(idx+1, cnt, s);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        a = br.readLine();
        b = br.readLine();
        boolean[] s1 = new boolean[N];
        boolean[] s2 = new boolean[N];
        e = new boolean[N];

        for(int i=0; i<N; i++) {
            s1[i] = a.charAt(i) != '0';
            s2[i] = a.charAt(i) != '0';
            e[i] = b.charAt(i) != '0';
        }

        // 첫번째 전구를 바꾼 경우
        s1[0] = !s1[0];
        s1[1] = !s1[1];
        switchChange(1, 1, s1);
        // 첫번째 전구를 바꾸지 않은 경우
        switchChange(1, 0, s2);

        System.out.println(res == Integer.MAX_VALUE ? -1 : res);
    }
}
