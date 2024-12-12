class Solution {
    public int solution(int[][] points, int[][] routes) {
        int answer = 0;
        int robotN = routes.length;
        int goalN = routes[0].length;
        
        
        //set robots
        
        int max_r=0, max_c=0;
        for(int i=0; i<points.length; i++) {
            max_r = Math.max(points[i][0], max_r);
            max_c = Math.max(points[i][1], max_c);
        }
        int[][] board = new int[max_r+1][max_c+1];
        int[][] robot_pos = new int[robotN][2];
        int[] robot_step = new int[robotN];
        //set robot_pos
        for(int i=0; i<robotN; i++) {
            robot_pos[i][0] = points[routes[i][0]-1][0];
            robot_pos[i][1] = points[routes[i][0]-1][1];
            // board[robot_pos[i][0]][robot_pos[i][1]] = i+1;
            robot_step[i] = 1;
        }
        boolean[][] flag = new boolean[max_r+1][max_c+1];
        
        while(true) {
            flag = new boolean[max_r+1][max_c+1];
            board = new int[max_r+1][max_c+1];
            
            
            //로봇 돌면서 체크
            for(int i=0; i<robotN; i++) {
                int r = robot_pos[i][0];
                int c = robot_pos[i][1];
                
                if(robot_step[i]>=goalN)  continue;
                if(board[r][c] !=0 && !flag[r][c]) {
                    flag[r][c] = true;
                    // System.out.println("r="+r+" c="+c+" robotN="+i);
                    answer++;
                }
                board[r][c] = i+1;
                
                int step = robot_step[i];
                int goalR = points[routes[i][step]-1][0];
                int goalC = points[routes[i][step]-1][1];
                
                if(r==goalR && c==goalC) {
                    if(step<goalN) {
                        robot_step[i]++;
                        if(robot_step[i]==goalN) continue;
                        goalR = points[routes[i][step+1]-1][0];
                        goalC = points[routes[i][step+1]-1][1];
                    }
                    
                }
    
                if(goalR>r) {
                    r++;
                }
                else if(goalR<r) {
                    r--;
                }
                else {
                    if(goalC>c) {
                        c++;
                    }
                    else if(goalC<c) {
                        c--;
                    }
                }
                      
                
                robot_pos[i][0] = r;
                robot_pos[i][1] = c;
                
            }
            
            //전부 목표 달성 체크
            int cnt=0;
            for(int i=0; i<robotN; i++) {
                if(robot_step[i]>=goalN) cnt++;
            }
            if(cnt==robotN) break;
        }
             
        
        
        return answer;
    }
}
