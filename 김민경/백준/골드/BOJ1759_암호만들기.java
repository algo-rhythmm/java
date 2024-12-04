package study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ1759_암호만들기 {
    static int L, C;
    static char[] letters;
    static char[] picked;
    static char[] vowels = {'a', 'e', 'i', 'o', 'u'};
    public static void main(String[] args) throws IOException {
        BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
         L = Integer.parseInt(st.nextToken());
         C = Integer.parseInt(st.nextToken());

        letters = new char[C];
        picked = new char[L];
         st = new StringTokenizer(br.readLine());
         for(int i=0; i<C; i++) {
             letters[i] = st.nextToken().charAt(0);
         }
        Arrays.sort(letters);

         comb(0, 0);

    }
    static void comb(int k, int start) {
        if(k==L) {
            StringBuilder sb = new StringBuilder();
            int vowelCnt=0, consonantCnt=0;
            for(int i=0; i<L; i++) {
                boolean flag=false;
                char letter = picked[i];
                for(int j=0; j<5; j++) {
                    if(letter == vowels[j]) {
                        flag = true;
                        vowelCnt++;
                        break;
                    }
                }
                if(!flag) consonantCnt++;
                sb.append(picked[i]);
            }
            if(vowelCnt>=1 && consonantCnt>=2) System.out.println(sb);
            return;
        }

        for(int i=start; i<C; i++) {
            picked[k] = letters[i];
            comb(k+1, i+1);
        }

    }
}
