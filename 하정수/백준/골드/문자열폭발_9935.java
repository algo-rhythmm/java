package 백준;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

/*
 *  문자열 저장할 str 선언
 *  폭발 문자열 저장할 bomb 선언
 *  for문 돌면서 i번째
 *
 *
 *  replaceAll 을 사용하니까 메모리초과가 뜸;;
 *
 *
 * */


public class 문자열폭발_9935 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        String str = br.readLine();
        String bomb = br.readLine();

        int bomb_size = bomb.length();

        Stack<Character> stack = new Stack<>();

//        String ss = "abcdcef";
//        System.out.println(ss.replaceAll("cd",""));

        for (int i = 0; i < str.length(); i++) {
            stack.push(str.charAt(i));

            if (stack.size() >= bomb_size && stack.peek() == bomb.charAt(bomb_size - 1)) {
                int count = 0;
                for (int j = 0; j < bomb_size; j++) {
                    if (stack.get(stack.size() - bomb_size + j) == bomb.charAt(j)) {
                        count++;
                    }
                    if (count == bomb_size) {
                        for (int k = 0; k < bomb_size; k++) {
                            stack.pop();
                        }
                    }
                }
            }
        }

        if (stack.isEmpty()) {
            System.out.println("FRULA");
        } else {
            for (char ch : stack) {
                sb.append(ch);
            }
        }

        System.out.println(sb);

    }
}
