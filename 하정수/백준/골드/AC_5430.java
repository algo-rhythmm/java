package 백준;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*
 *   1시간동안 배열을 돌려서 풀고 있었는데 시간초과가 난다..
 *   배열을 돌려주는 로직에서 시간을 많이 잡아먹는 듯 하다.
 *   덱을 써보도록 하자.
 * */

public class AC_5430 {
    static String p;
    static int n;
    static boolean flag, isRight;
    static Deque<Integer> deque;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for (int t = 0; t < T; t++) {
            p = br.readLine();
            n = Integer.parseInt(br.readLine());


            deque = new ArrayDeque<>();

            String a = br.readLine();


            a = a.substring(1, a.length() - 1);
            String[] s = a.split(",");


            for (int j = 0; j < n; j++) {
                deque.add(Integer.parseInt(s[j]));
            }

            isRight = true;

            for (int i = 0; i < p.length(); i++) {
                flag = false;
                function(p.charAt(i));
                if (flag) {
                    break;
                }
            }
            if (!flag) {
                sb.append("[");
                if (!deque.isEmpty()) {
                    if (isRight) {
                        sb.append(deque.pollFirst());
                        while (!deque.isEmpty()) {
                            sb.append(",").append(deque.pollFirst());
                        }
                    } else {
                        sb.append(deque.pollLast());
                        while (!deque.isEmpty()) {
                            sb.append(",").append(deque.pollLast());
                        }
                    }
                }
                sb.append("]\n");
            }
        }

        System.out.println(sb);
    }

    public static void function(char ch) {
        if (ch == 'R') {
            isRight = !isRight;
        } else {
            if (deque.size() == 0) {
                flag = true;
                sb.append("error\n");
                return;
            }
            if (isRight) {
                deque.pollFirst();
            } else {
                deque.pollLast();
            }
        }
    }
}
