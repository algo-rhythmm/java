import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

/*
서로다른 L개의 알파벳 소문자
최소 한개의 모음과 두개의 자음으로 구성되어 있음
알파벳이 암호에서 증가하는 순서대로 배열
문자의 종류 C가지 중 가능성있는 암호를 모두 구하라
=======================
입력:
    L, C (3,=L<=C<=15)
    C개의 문자들 (중복X)
=======================
C가 15까지 인걸로 봤을 때 조합 문제
그냥 모두 조합 돌려서 해당 문자열에 자음2, 모음1 있는지 체크 후
알파벳순으로 정렬해서 정답배열에 넣고 해당 정답배열을 다시 정렬한 후 출력?
=> 그냥 처음부터 정렬해놓으면 된다!
 */
public class BOJ1759암호만들기 {
    static int L,C;
    static int[] picked;
    static char[] chars;

    static String vowels = "aeiou";

    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        L = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        picked = new int[L];
        chars = br.readLine().replace(" ","").toCharArray();
        Arrays.sort(chars);
        //System.out.println(Arrays.toString(chars));
        combi(0,0);
        System.out.println(sb);
    }

    public static void combi(int depth, int start){
        if(depth==L){
            if (checkVowels()){
                char[] c = new char[L];
                for(int i=0; i<L; i++){
                    c[i] = chars[picked[i]];
                }
                sb.append(c).append("\n");
            }
            return;
        }

        for(int i=start; i<C; i++){
            picked[depth] = i;
            combi(depth+1, i+1);
        }
    }

    private static boolean checkVowels() {
        int vowelCount = 0;
        for(int i=0; i<L; i++){
            //indexOf 로 해당 문자가 포함되어있는지 아닌지 체크할 수 있다!!
            //-1이 나오면 포함하지 않는것
            if(vowels.indexOf(chars[picked[i]])!=-1){
                vowelCount++;
            }
        }
        return vowelCount >= 1 && L - vowelCount >= 2;
    }
}
