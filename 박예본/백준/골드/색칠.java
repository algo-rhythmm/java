import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 색칠 {

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        long W, H, f, c, x1, y1, x2, y2;
        W = Long.parseLong(st.nextToken());
        H = Long.parseLong(st.nextToken());
        f = Long.parseLong(st.nextToken());
        c = Long.parseLong(st.nextToken());
        x1 = Long.parseLong(st.nextToken());
        y1 = Long.parseLong(st.nextToken());
        x2 = Long.parseLong(st.nextToken());
        y2 = Long.parseLong(st.nextToken());

        long result = W * H; //색칠된 너비

        long Y = y2-y1;
        long hX = x2-x1;

        result -= Y * hX * (c + 1); //색칠된 부분

        //f에 따라 분류

        if( f == 0  ){ //안접었을 떄
            //뺄 것이 없다.
        }else if( f <= (W/2)+(W%2) ) { //중간조금 안되게 접었을 때
            long wX = Math.min(f,x2) - x1;
            if(wX > 0) result -= Y * wX * (c+1);
        }else if(f == (W/2)+(W%2)) { //중간을 접었을 때
            result -= Y * hX * (c + 1);

//        }else if(f > (W/2)+(W%2)){ //중간보다 더 접었을 때
        }else{
//            System.out.println("WDAaweskjfblk");
            long wX = Math.min((W-f),x2)-x1;
            if (wX > 0) result -= Y * wX * (c + 1);
        }

        System.out.println(result);

    }
}
