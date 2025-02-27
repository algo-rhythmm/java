package algo0218;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/*
    2668 숫자고르기
    11532KB | 64ms
 */

public class 숫자고르기 {

    static int N;
    static int[] arr;
    static List<Integer> res;
    static boolean[] visited;

    static void dfs(int x, int target) {
        if(arr[x] == target) {
            res.add(x);
            return;
        }

        if(!visited[arr[x]]) {
            visited[arr[x]] = true;
            dfs(arr[x], target);
            visited[arr[x]] = false;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        arr = new int[N+1];
        visited = new boolean[N+1];
        res = new ArrayList<>();

        for(int i=1; i<=N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        for(int i=1; i<=N; i++) {
            visited[i] = true;
            dfs(i, i);
            visited[i] = false;
        }

        System.out.println(res.size());
        Collections.sort(res);
        for(int x : res) {
            System.out.println(x);
        }
    }

}
