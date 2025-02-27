package algo0213;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
    9252 LCS2
    15956KB | 92ms
 */

public class LCS2 {

    static String A, B;
    static int[][] length;
    static StringBuffer sb = new StringBuffer();

    static void LCS() {
        for (int i = 1; i <= A.length(); i++) {
            for (int j = 1; j <= B.length(); j++) {
                if (A.charAt(i-1) == B.charAt(j-1)) {
                    length[i][j] = length[i-1][j-1] + 1;
                }
                else {
                    length[i][j] = Math.max(length[i-1][j], length[i][j-1]);
                }

            }
        }
    }

    static void LCS2(int x, int y) {
        if(x == 0 || y == 0) return;

        if(length[x][y] == length[x][y-1]) {
            LCS2(x, y-1);
        }
        else if(length[x][y] == length[x-1][y]) {
            LCS2(x-1, y);
        }
        else {
            sb.append(A.charAt(x-1));
            LCS2(x-1, y-1);
        }

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        A = br.readLine();
        B = br.readLine();
        length = new int[A.length()+1][B.length()+1];
        LCS();


        LCS2(A.length(), B.length());

        System.out.println(length[A.length()][B.length()]);
        System.out.println(sb.reverse());
    }
}
