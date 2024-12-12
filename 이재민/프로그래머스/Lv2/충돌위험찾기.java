package algo1212;

/*
    구현
 */

public class 충돌위험찾기 {

    static class Robot {
        // 로봇 위치, 이동해야 하는곳의 idx
        int r, c, current;
        // 로봇이 들어왔는지
        boolean check;

        public Robot(int r, int c, int current, boolean check) {
            this.r = r;
            this.c = c;
            this.current = current;
            this.check = check;
        }

        public void move (int pointX, int pointY, int[][] map) {
            // 수평 맞추기
            if(r != pointX){
                if(r < pointX) r = r + 1;
                else r = r - 1;
            }
            else{
                if(c < pointY) c = c + 1;
                else c = c - 1;
            }

            map[r][c]++;

            if(r == pointX && c == pointY) {
                current = current + 1;
            }
        }
    }

    // 충돌 판별
    static int crash(int answer, int[][] map){
        int cnt = 0;
        for(int i=1; i<=100; i++){
            for(int j=1; j<=100; j++) {
                if(map[i][j] > 0) {
                    if(map[i][j] > 1) cnt++;
                    map[i][j] = 0;
                }
            }
        }
        return answer + cnt;
    }

    static int solution(int[][] points, int[][] routes) {
        // 충돌 판별하기 위한 배열
        int[][] map = new int[101][101];
        int answer = 0;
        // 몇개 point를 가는지
        int M = routes[0].length;
        // 로봇의 총 개수
        int rCnt = routes.length;
        // 들어간 로봇의 개수
        int cnt = 0;

        Robot[] robots = new Robot[routes.length];

        // 초기 위치 설정
        for(int i=0; i<routes.length; i++){
            int point = routes[i][0]-1;
            robots[i] = new Robot(points[point][0], points[point][1], 1, false);
            map[robots[i].r][robots[i].c]++;
        }

        // 처음 부분에서도 충돌 계산
        answer = crash(answer, map);

        while(cnt < rCnt) {
            for (int i = 0; i < routes.length; i++) {
                Robot robot = robots[i];
                if (robot.check) continue;
                int currentPos = routes[i][robot.current]-1;
                int pointX = points[currentPos][0];
                int pointY = points[currentPos][1];
                robot.move(pointX, pointY, map);
                if(robot.current == M) {
                    robot.check = true;
                    cnt++;
                }
            }

            answer = crash(answer, map);
        }

        return answer;
    }


    public static void main(String[] args) {
        int[][] points = {{3, 2}, {6, 4}, {4, 7}, {1, 4}};
        int[][] routes = {{4, 2}, {1, 3}, {2, 4}};

        int[][] points2 = {{3, 2}, {6, 4}, {4, 7}, {1, 4}};
        int[][] routes2 = {{4, 2}, {1, 3}, {4, 2}, {4, 3}};

        int[][] points3 = {{2, 2}, {2, 3}, {2, 7}, {6, 6}, {5, 2}};
        int[][] routes3 = {{2, 3, 4, 5}, {1, 3, 4, 5}};

        System.out.println(solution(points, routes));
        System.out.println(solution(points2, routes2));
        System.out.println(solution(points3, routes3));
    }
}
