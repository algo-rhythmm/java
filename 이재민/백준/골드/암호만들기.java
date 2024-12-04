package algo1204;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
    조합 문제
    모음 자음 개수를 파라미터로 넘겨서 조건 판단
    13344KB | 92ms
 */

public class 암호만들기 {
    static int L, C;
    static char[] chr;
    static char[] picked;
    static StringBuffer sb = new StringBuffer();

    static boolean isVowel(char ch) {
        return "aeiou".contains(String.valueOf(ch));
    }

    static void combi(int cnt, int idx, int vowel, int cons) {
        if(cnt == L){
            if(vowel >= 1 && cons >= 2)
                sb.append(String.valueOf(picked)).append('\n');
            return;
        }

        for(int i=idx; i<C; i++){
            picked[cnt] = chr[i];
            if (isVowel(chr[i])) {
                combi(cnt + 1, i + 1, vowel + 1, cons);
            } else {
                combi(cnt + 1, i + 1, vowel, cons + 1);
            }
        }

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        L = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        chr = new char[C];
        picked = new char[L];

        String line = br.readLine();
        chr = line.replace(" ", "").toCharArray();
        Arrays.sort(chr);

        combi(0, 0, 0, 0);

        System.out.println(sb);

    }
}
