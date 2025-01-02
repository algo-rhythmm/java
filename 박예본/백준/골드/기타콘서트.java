import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class 기타콘서트 {
    static int result = Integer.MAX_VALUE;
    static int maxSongs = 0;
    static int N, M;
    static long[] guitar;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        guitar = new long[N];

        for(int t = 0; t<N; t++){
            st = new StringTokenizer(br.readLine());
            st.nextToken();
            char[] tmp = st.nextToken().toCharArray();

            for(int q = 0; q<M; q++){
                if(tmp[q] == 'Y') guitar[t] |= (1L<<q);
            }
        }

        dfs(0, 0L, 0);
        System.out.println(maxSongs == 0 ? -1 : result);
    }

    static void dfs(int idx, long bit, int cnt){
        int currentSongs = Long.bitCount(bit);

        if(currentSongs == maxSongs && cnt < result) {
            result = cnt;
        }
        if(currentSongs > maxSongs) {
            maxSongs = currentSongs;
            result = cnt;
        }

        if(idx == N || currentSongs == M) return;

        dfs(idx + 1, bit | guitar[idx], cnt + 1);
        dfs(idx + 1, bit, cnt);
    }
}