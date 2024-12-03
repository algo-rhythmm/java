import java.io.*;
import java.io.IOException;
import java.util.StringTokenizer;

public class BOJ14889_스타트와링크 {
    
    static int N;
    static int[][] arr = new int[21][21];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            for(int j = 0; j < N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        System.out.println(permutation(0, -1, new boolean[N]));

    }

    //순열 구하기
    //idx - 선택된 선수 수, prev - 이전에 선택된 선수 번호, team - 선수 목록
    //선수가 모두 정해져야 점수를 계산할 수 있으므로 가지치기는 불가능하다고 생각합니다.
    static int permutation(int idx, int prev, boolean[] team) {
        //절반의 선수가 선택된 경우 점수 계산
        if(idx == N / 2) {
            int A = 0, B = 0;
            for(int i = 0; i < N; i++) {
                for(int j = 0; j < N; j++) {
                    if(team[i] == team[j] && team[i]) A += arr[i][j];
                    else if(team[i] == team[j]) B += arr[i][j];
                }
            }
            return Math.abs(A - B);
        }

        int res = Integer.MAX_VALUE;
        for(int i = prev + 1; i < N; i++) {
            team[i] = true;
            res = Math.min(permutation(idx + 1, i, team), res);
            team[i] = false;
        }
        return res;
    }
}
