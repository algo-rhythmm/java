import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class 배 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine()); // 크레인 수
        List<Integer> craneWeight = new ArrayList(); //안바뀔껏
        st = new StringTokenizer(br.readLine());

        for (int a = 0; a < N; a++) {
            craneWeight.add(Integer.parseInt(st.nextToken()));
        }

        craneWeight.sort((o1, o2) -> o2 - o1);


        int M = Integer.parseInt(br.readLine()); //화물 수
        st = new StringTokenizer(br.readLine());
        List<Integer> box = new ArrayList<>();

        for (int a = 0; a < M; a++) {
            box.add(Integer.parseInt(st.nextToken()));
        }

        box.sort((o1, o2) -> o2 - o1);
        int turn = 0;

        if (box.get(0) > craneWeight.get(0)) {
            System.out.println("-1");
            return;
        }

        while (!box.isEmpty()) {

            turn++;
            int boxStart = 0;
            int craneStart = 0;
            int boxSize = box.size();
            for (int t = 0; t < boxSize; t++) { //박스 처리 하기

                if (box.get(boxStart) <= craneWeight.get(craneStart)) {
                    box.remove(boxStart);
                    craneStart++;
                    if (craneStart >= craneWeight.size()) {
                        break;
                    }
                } else {
                    boxStart++;

                }

            }
        }

        System.out.println(turn);
    }

}
