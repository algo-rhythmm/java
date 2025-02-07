import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;



public class BOJ2251_물통 {


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int[] cup = new int[3];
        for (int i=0; i<3; i++) {
            cup[i] = Integer.parseInt(st.nextToken());

        }
        List<Integer> answers = new ArrayList<>();

        boolean[][][] visit = new boolean[cup[0]+1][cup[1]+1][cup[2]+1];

        visit[0][0][cup[2]] = true;
        Deque<Integer[]> q = new ArrayDeque<>();
        q.add(new Integer[] {0, 0, cup[2]} );
        answers.add(cup[2]);

        while (!q.isEmpty()) {

            Integer[] temp = q.poll();

            for (int i=0; i<3; i++) {
                if (temp[i]==0) continue;
                for (int c=1; c<=2; c++) {
                    int j = (i+c)%3;
                    if (temp[j]==cup[j ]) continue;

                    Integer[] nArr = Arrays.copyOf(temp, 3);
                    //남은 공간이 옮길 물보다 크면
                    if (cup[j]-temp[j]>=temp[i]) {
                        nArr[j] = temp[j]+temp[i];
                        nArr[i] = 0;
                    }
                    else {
                        nArr[i] -= (cup[j]-temp[j]);
                        nArr[j] = cup[j];
                    }

                    if (visit[nArr[0]][nArr[1]][nArr[2]]) continue;

                    visit[nArr[0]][nArr[1]][nArr[2]] = true;
                    q.add(nArr);
                    if (nArr[0]==0) answers.add(nArr[2]);
                }
            }
        }

        Collections.sort(answers);
        StringBuilder sb = new StringBuilder();
        for (Integer i : answers) {
            sb.append(i).append(" ");
        }
        System.out.println(sb);
    }
}
