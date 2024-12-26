import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ5525_IOIOI {

    static int N, M, result = 0;
    static char[] s;
    
    // I 가 나타나면 그 뒤에 OI가 몇번 붙는지를 카운트
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        s = br.readLine().toCharArray();

        for(int i = 0; i < M; i++) {
            if(s[i] == 'I') {
                int countOI = 0, idx = i + 1, tmp = 0;
                char OI = 'O';
                
                // OI 패턴이 몇번 등장하는지 카운트
                while(idx < M && s[idx] == OI) {
                    OI = OI == 'O' ? 'I' : 'O';
                    tmp++;
                    if(tmp == 2) {
                        tmp = 0;
                        countOI++;
                    }
                    idx++;
                }
                
                // 주어진 패턴 N이 몇번 나오는지 계산
                result += countOI >= N ? (countOI - N) + 1 : 0;

                // 이미 확인한 구간은 건너뛸 수 있도록 한다.
                i = idx - 1;
            }
        }

        System.out.println(result);
    }
}