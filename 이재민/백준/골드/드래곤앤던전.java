package algo0225;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/*
    16434 드래곤앤던전
    55772KB | 596ms

 */
public class 드래곤앤던전 {

    static class Dungeon {
        int t, a, h;
        public Dungeon(int t, int a, int h) {
            this.t = t;
            this.a = a;
            this.h = h;
        }
    }

    static int N;
    static long atk, mhp, start, end;
    static Dungeon[] d;

    static void binarySearch() {
        while(start <= end) {
            long mid = (start + end) / 2;
            long curHP = mid;
            long curAtk = atk;
            boolean isClear = true;

            for(Dungeon r : d) {
                if(r.t == 1) {
                    long atkCount = (r.h + curAtk -1) / curAtk;
                    long dmg = (atkCount - 1) * r.a;

                    curHP -= dmg;

                    if(curHP <= 0) {
                        isClear = false;
                        break;
                    }
                } else{
                    curAtk += r.a;
                    curHP = Math.min(mid, curHP + r.h);
                }
            }

            if(isClear) {
                mhp = mid;
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        atk = Long.parseLong(st.nextToken());
        d = new Dungeon[N];

        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            int t = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            int h = Integer.parseInt(st.nextToken());
            d[i] = new Dungeon(t, a, h);
        }

        start = 1L;
        end = Long.MAX_VALUE - 1;
        mhp = end;

        binarySearch();

        System.out.println(mhp);

    }

}
