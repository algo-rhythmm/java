package 백준;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 트리순회_1991 {
    static class Node {
        char val;
        Node left;
        Node right;

        public Node(char val, Node left, Node right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    static Node head = new Node('A', null, null);
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            char parent = st.nextToken().charAt(0);
            char left = st.nextToken().charAt(0);
            char right = st.nextToken().charAt(0);

            insertNode(head, parent, left, right);
        }

        preOrder(head);
        sb.append("\n");
        inOrder(head);
        sb.append("\n");
        postOrder(head);
        sb.append("\n");

        System.out.println(sb);
    }

    static void insertNode(Node tmp, char parent, char left, char right) {
        if (tmp.val == parent) {
            tmp.left = (left == '.' ? null : new Node(left, null, null));
            tmp.right = (right == '.' ? null : new Node(right, null, null));
        } else {
            if (tmp.left != null) {
                insertNode(tmp.left, parent, left, right);
            }
            if (tmp.right != null) {
                insertNode(tmp.right, parent, left, right);
            }
        }
    }

    static void preOrder(Node node) {
        if (node == null) return;

        sb.append(node.val);
        preOrder(node.left);
        preOrder(node.right);
    }

    static void inOrder(Node node) {
        if (node == null) return;

        inOrder(node.left);
        sb.append(node.val);
        inOrder(node.right);
    }

    static void postOrder(Node node) {
        if (node == null) return;

        postOrder(node.left);
        postOrder(node.right);
        sb.append(node.val);
    }
}
