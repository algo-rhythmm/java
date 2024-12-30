package 백준;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
/*
 *   다이어트_1484_골드5
 *   long 형으로 만들어야할거 같은데
 *   그럼 long형 배열을 하나 만들고
 *
 * */


public class 다이어트_1484 {
    static ArrayList<Long> arr = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int G = Integer.parseInt(br.readLine());

        long p1 = 1;
        long p2 = 1;

        while (true) {
            long diff = p1 * p1 - p2 * p2;
            if (p1 - p2 == 1 && diff > G) {
                break;
            }
            if (diff < G) {
                p1++;
            } else {
                p2++;
            }
            if (diff == G) {
                arr.add(p1);
            }
        }

        if (arr.size() == 0) {
            sb.append(-1);
        } else {
            for (long item : arr) {
                sb.append(item + "\n");
            }
        }

        System.out.println(sb);


    }
}
