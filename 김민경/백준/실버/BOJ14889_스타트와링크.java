package study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ14889_스타트와링크 {
    static int N;
    static int[][] synergy;
    static int[] teamA;
    static int[] teamB;
    static int answer=Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        synergy = new int[N][N];
        teamA = new int[N/2];
        teamB = new int[N/2];

        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++) {
                synergy[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        comb(1, 1);

        System.out.println(answer);

    }

    static void comb(int k, int start) {
        if(k==N/2) {


            boolean[] visit = new boolean[N];

            for(int i=0; i<N/2; i++) {
                visit[teamA[i]] = true;
            }
            for(int i=0, j=0; i<N || j<N/2; i++) {
                if(!visit[i]) teamB[j++]=i;
            }

//            System.out.println("teamA : "+Arrays.toString(teamA));
//            System.out.println("teamB : " +Arrays.toString(teamB));
//            System.out.println();
            int cmp = cal_synergy();
            answer = Math.min(answer, cmp);
            return;
        }

        for(int i=start; i<N; i++) {
            teamA[k] = i;
            comb(k+1, i+1);
        }
    }

    static int cal_synergy() {
        int sumA=0, sumB=0;

        for(int i=0; i<N/2; i++) {
            for(int j=i+1; j<N/2; j++) {
                sumA+= (synergy[teamA[i]][teamA[j]] + synergy[teamA[j]][teamA[i]]);
                sumB+= (synergy[teamB[i]][teamB[j]] + synergy[teamB[j]][teamB[i]]);
            }
        }
        return Math.abs(sumA-sumB);
    }
}
