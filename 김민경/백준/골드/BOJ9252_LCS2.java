import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ9252_LCS2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        char[] str1 = br.readLine().toCharArray();
        char[] str2 = br.readLine().toCharArray();

        int[][] LCS = new int[str1.length+1][str2.length+1];
        String LCS_val = "";


        for (int i=0; i<str1.length; i++) {
            for (int j=0; j<str2.length; j++) {
                if (str1[i] == str2[j]) {
                    LCS[i+1][j+1] = LCS[i][j] +1;
                }
                else {
                    LCS[i + 1][j + 1] = Math.max(LCS[i][j + 1], LCS[i + 1][j]);
                }
            }
        }


        System.out.println(LCS[str1.length][str2.length]);
        StringBuilder sb = new StringBuilder();
        int i = str1.length, j = str2.length;
        while (LCS[i][j]!=0) {
            if (LCS[i][j] == LCS[i-1][j]) i--;
            else if(LCS[i][j] == LCS[i][j-1]) j--;
            else {
                sb.append(str1[i-1]);
                i--;
                j--;
            }
        }

        System.out.println(sb.reverse());
    }
}
