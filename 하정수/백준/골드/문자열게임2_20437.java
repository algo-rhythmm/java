package 백준;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
 *  문자열 게임의 수 T 선언
 *  알파벳 갯수 저장할 arr 배열 선언
 *  String 저장할 str 선언
 *  포함할 알파벳 갯수 변수 num 선언
 *
 *
 * */

public class 문자열게임2_20437 {

    static int T;
    static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        T = Integer.parseInt(br.readLine());

        for (int t = 0; t < T; t++) {
            arr = new int[26];
            String str = br.readLine();
            int num = Integer.parseInt(br.readLine());

            if(num == 1) {
                System.out.println("1 1");
                continue;
            }

            for (int i = 0; i < str.length(); i++) {
                char ch = str.charAt(i);
                int ch_num = ch - 'a';

                arr[ch_num]++;
            }

            int min = Integer.MAX_VALUE;
            int max = -1;

            for (int i = 0; i < str.length(); i++) {
                if (arr[str.charAt(i) - 'a'] < num) continue;

                int count = 1;
                for (int j = i + 1; j < str.length(); j++) {
                    if (str.charAt(i) == str.charAt(j)) count++;
                    if (count == num) {
                        min = Math.min(min, j - i + 1);
                        max = Math.max(max, j - i + 1);
                        break;
                    }
                }
            }
            if (min == Integer.MAX_VALUE || max == -1) System.out.println("-1");
            else System.out.println(min + " " + max);
        }
    }
}
