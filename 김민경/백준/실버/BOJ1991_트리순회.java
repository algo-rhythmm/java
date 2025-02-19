import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.StringTokenizer;

class Node {
    int left, right;
    int ch;
    Node (int left, int right, int ch) {
        this.left = left;
        this.right = right;
        this.ch = ch;
    }
}

public class BOJ1991_트리순회 {

    static Node[] tree;
    static StringBuilder sb;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        StringTokenizer st;
        tree = new Node[N];
        for (int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());

            int ch = st.nextToken().charAt(0)-'A';

            int left = st.nextToken().charAt(0)-'A';
            int right = st.nextToken().charAt(0)-'A';

            Node node = new Node(left, right, ch);

            tree[ch] = node;
        }

        sb = new StringBuilder();
        preorder(0);
        sb.append("\n");
        inorder(0);
        sb.append("\n");
        postorder(0);
        sb.append("\n");

        System.out.println(sb);
    }
    static void inorder(int n) {
        if (n <0) return;
        Node node = tree[n];

        inorder(node.left);
        char ch = (char) (node.ch+65) ;
        sb.append(ch);
        inorder(node.right);

    }
    static void preorder(int n) {
        if (n <0) return;
        Node node = tree[n];

        char ch = (char) (node.ch+65) ;
        sb.append(ch);
        preorder(node.left);
        preorder(node.right);
    }

    static void postorder(int n) {
        if (n <0) return;
        Node node = tree[n];

        postorder(node.left);
        postorder(node.right);
        char ch = (char) (node.ch+65) ;
        sb.append(ch);
    }
}
