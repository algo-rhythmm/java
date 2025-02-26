package 백준;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class LCS2_9252 {
    static char[] str1, str2;
    static int[][][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        String str = br.readLine();
        str1 = new char[str.length()];
        str1 = str.toCharArray();

        str = br.readLine();
        str2 = new char[str.length()];
        str2 = str.toCharArray();

        map = new int[str1.length + 1][str2.length + 1][2];
        for (int i = 1; i <= str1.length; i++) {
            for (int j = 1; j <= str2.length; j++) {
                if (str1[i - 1] == str2[j - 1]) {
                    map[i][j][0] = map[i - 1][j - 1][0] + 1;
                    map[i][j][1] = 1;
                } else {
                    if (map[i - 1][j][0] >= map[i][j - 1][0]) {
                        map[i][j][0] = map[i - 1][j][0];
                        map[i][j][1] = 2;
                    } else {
                        map[i][j][0] = map[i][j - 1][0];
                        map[i][j][1] = 3;
                    }
                }
            }
        }

        int a = str1.length;
        int b = str2.length;
        int idx = map[a][b][0];
        char[] result = new char[idx];

        while (idx > 0) {
            if (map[a][b][1] == 1) {
                a--;
                b--;
                result[--idx] = str1[a];
            } else if (map[a][b][1] == 2) {
                a--;
            } else {
                b--;
            }
        }

//        for (int i = 0; i <= str1.length; i++) {
//            for (int j = 0; j <= str2.length; j++) {
//                System.out.print(map[i][j][0] + " ");
//            }
//            System.out.println();
//        }
//        System.out.println();

        sb.append(map[str1.length][str2.length][0]).append("\n").append(result);

//        for (int i = 0; i <= str1.length; i++) {
//            for (int j = 0; j <= str2.length; j++) {
//                System.out.print(map[i][j][1] + " ");
//            }
//            System.out.println();
//        }

        System.out.println(sb);
    }
}
