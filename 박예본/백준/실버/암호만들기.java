
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class 암호만들기 {

    static int L;
    static int C;
    static Set<Character> ch;
    static List<Character> list;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        L = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());

        Set<Character> ch = new TreeSet<>();


        boolean[] vis = new boolean[C];
        ch = new TreeSet<>();
        for( int a = 0; a<C; a++){
        ch.add(st.nextToken().toCharArray()[0]);
        }

        list = new ArrayList<>(ch);

         combi(0,0,vis);
        System.out.println(sb);
    }

    public static void combi(int start, int cnt, boolean[] vis){

        if(cnt == L){
            check(vis);
            return;
        }
        if(start > C)return;

        for(int i = start; i<C; i++){
            if(vis[i]) continue;
            vis[i] = true;
            combi(i+1,cnt+1,vis);
            vis[i] = false;
        }

    }

    public static void check(boolean[] vis){
        String tmp = "";
        int check = 0;
        for(int b =0 ; b <C; b++){
           if(vis[b]){
            tmp += list.get(b);
            if(list.get(b) == 'a' || list.get(b) == 'e' || list.get(b) == 'i' || list.get(b) == 'o' || list.get(b) == 'u'){
                check++;
            }
           }
        }
        if(check==0||L-check<2){return;}
        sb.append(tmp).append("\n");
    }

}
