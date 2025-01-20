import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
부분집합문제? N이 10까지밖에 없으니까...
======
입력:
    N,M (1<=N<=10) (1<=M<=50)
    기타명, 할수있는 곡리스트
 */
public class BOJ1497기타콘서트 {
    static int N,M;
    static String[] songs;
    static boolean[] visited;
    static int ans = Integer.MAX_VALUE;
    static int maxSongs = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        visited = new boolean[N];
        songs = new String[N];
        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            st.nextToken();
            songs[i] = st.nextToken();
        }
//        System.out.println(Arrays.toString(songs));
        powerSet(0);
        System.out.println(maxSongs==0?-1:ans);

    }

    private static void powerSet(int depth) {
        if (depth==N){
//            System.out.println(Arrays.toString(visited));
            int guitars = 0;
            int cnt = 0;
            for(int i=0; i<N; i++){
                if(visited[i]){
                    guitars++;
                }
            }
            for(int i=0; i<M; i++){
                for(int j=0; j<N; j++){
                    if(visited[j]&&songs[j].charAt(i)=='Y'){
//                        System.out.println("i:"+i+" j:"+j);
                        cnt++;
                        break;
                    }
                }
            }
            if(cnt>=maxSongs){
                maxSongs = cnt;
                ans = Math.min(ans, guitars);
            }
            return;
        }
        visited[depth]= true;
        powerSet(depth+1);
        visited[depth]= false;
        powerSet(depth+1);

    }
}
