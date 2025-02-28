import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Stack;
import java.util.StringTokenizer;

public class 스카이라인_쉬운거 {
    static int building;
    static Stack<Integer> stack;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        building = 0;
        stack = new Stack();
        for(int i = 0; i< N; i++){
            st = new StringTokenizer(br.readLine());
            String throwOut = st.nextToken();
            int high = Integer.parseInt(st.nextToken());
            checking(high);
        }
        System.out.println(building);
    }

    static void checking(int high){
        if(high == 0){
            stack.removeAllElements();
            return;
        }

        while( !stack.isEmpty() && stack.peek() > high ){
            stack.pop();
        }

        if(stack.isEmpty()){
            stack.push(high);
            building++;
        }else if( stack.peek()== high){

        }else{ // 더 작은애가 있다면
        stack.push(high);
        building++;
        }
    }

}
