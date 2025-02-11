import java.util.*;
import java.io.*;
public class 로봇_조립 {
    static int[] master;
    static int[] cnt;

    public static void union(int a, int b){
        a = find(a);
        b = find(b);

        if(a!=b){
            master[a]= b;
            cnt[b]+=cnt[a];
        }
    }

    private static int find(int a){
        if( a == master[a] ) return a;
        return master[a] = find(master[a]);
    }

    public static int oner(int a){
        return cnt[find(a)];
    }

    public static void main(String[] args) throws Exception{



        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine());

        cnt = new int[1000001];
        master = new int[1000001];
        for(int i = 0; i<1000001; i++){
            master[i]= i;
            cnt[i] = 1;
        }

        int fir,sec;
        for(int i = 0; i< N; i ++){
            st = new StringTokenizer(br.readLine());
            char order = st.nextToken().charAt(0);
            if(order=='I'){
                //합치는 로직
                fir = Integer.parseInt(st.nextToken());
                sec = Integer.parseInt(st.nextToken());

                union(fir,sec);

            }else{
                // 구하는 로직
                int tmp = Integer.parseInt(st.nextToken());

                sb.append( oner(tmp) ).append("\n");
            }
        }
        System.out.println(sb);
    }

}
