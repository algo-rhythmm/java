import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 드래곤앤던전 {

    static long ATK, HP,lostHP;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        //HP를 구하는게 목표
        int N = Integer.parseInt(st.nextToken());
        ATK = Integer.parseInt(st.nextToken());
        HP = 0;
        lostHP = 0;

        for(int i = 0; i<N; i++){ // N만큼 돌 예정이야

            st = new StringTokenizer(br.readLine());
            String room = st.nextToken();
            if(room.equals("1")){ //몬스터 만나는 방이야

                int monATK = Integer.parseInt(st.nextToken()); // 몬스터의 공격력
                int monHP = Integer.parseInt(st.nextToken())-1; // 몬스터의 체력

                long turn = monHP / ATK;  //몬스터가 나를 몇번 공격할까?
//                if( turn == 0 ) continue; //만약에 한번에 죽으면

                lostHP += turn * monATK; //잃어버릴 체력
//                if(monHP % ATK !=0) lostHP += monATK; //만약에 나눠지지 않는 숫자라면 한대 더 때려
                HP = Math.max(lostHP,HP);

            }else{ //체력 회복하는 방이야
                ATK += Integer.parseInt(st.nextToken()); //공격력이 커지고
                int heal = Integer.parseInt(st.nextToken());
                if(heal > lostHP) lostHP = 0;
                else lostHP -= heal;
            }
        }
        System.out.println(HP+1);
    }

}
