package algo0219;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class 트리순회 {

    static int N;
    static List<List<Integer>> list;
    static StringBuffer sb;

    static void preOrder(int ch) {
        if(ch == -1) return;
        sb.append((char) (ch + 65));
        for(int i=0; i<list.get(ch).size(); i++) {
            preOrder(list.get(ch).get(i));
        }
    }

    static void inOrder(int ch) {
        if(ch == -1) return;

        inOrder(list.get(ch).get(0));
        sb.append((char) (ch+65));
        inOrder(list.get(ch).get(1));
    }

    static void postOrder(int ch) {
        if(ch == -1) return;

        postOrder(list.get(ch).get(0));
        postOrder(list.get(ch).get(1));
        sb.append((char) (ch+65));
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        list = new ArrayList<>();
        for(int i=0; i<N; i++) {
            list.add(new ArrayList<>());
        }

        for (int i = 0; i < N; i++) {
            char[] ch = br.readLine().replace(" ", "").toCharArray();
            list.get(ch[0] - 65).add(ch[1] == '.' ? -1 : ch[1] - 65);
            list.get(ch[0] - 65).add(ch[2] == '.' ? -1 : ch[2] - 65);
        }
        sb = new StringBuffer();
        preOrder(0);
        System.out.println(sb.toString());

        sb = new StringBuffer();
        inOrder(0);
        System.out.println(sb.toString());

        sb = new StringBuffer();
        postOrder(0);
        System.out.println(sb.toString());
    }

}
