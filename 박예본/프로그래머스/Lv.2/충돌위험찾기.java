import java.util.*;
import java.io.*;


class 충동위험찾기 {

    public class Space{
        int nowR;
        int nowC;
        int nextR;
        int nextC;
        Queue<Integer> nextGoal;

        public Space(){
            nextGoal = new LinkedList();
        }
    }

    public int solution(int[][] points, int[][] routes) {

        int answer = 0;
        boolean[][] vis = new boolean[101][101]; //중복 검사
        Set<String> visSet = new HashSet();

        Queue<Space> q = new LinkedList<>();

        for(int i = 0; i<routes.length; i++){
            Space s = new Space();
            s.nowR = points[routes[i][0]-1][0]; //현재 시작 위치
            s.nowC = points[routes[i][0]-1][1]; //현재 시작 위치
            s.nextR = points[routes[i][1]-1][0]; //다음 목표
            s.nextC = points[routes[i][1]-1][1]; //다음 목표
            for(int j = 2; j<routes[i].length;j++){ //다음 가야할 곳
                s.nextGoal.add(points[routes[i][j]-1][0]);
                s.nextGoal.add(points[routes[i][j]-1][1]);
            }

            if(vis[s.nowR][s.nowC]){
                visSet.add(s.nowR + "," + s.nowC);
            }else{
                vis[s.nowR][s.nowC]=true;
            }
            q.add(s);
        }
        answer += visSet.size();

        while(!q.isEmpty()){

            //중복 초기화
            vis = new boolean[101][101];
            visSet = new HashSet();

            //한 턴
            int time = q.size();

            for(int t = 0; t<time; t++){
                Space tmp = q.poll();

                //도착했는지 체크
                if( tmp.nowR == tmp.nextR && tmp.nowC == tmp.nextC){
                    if(tmp.nextGoal.isEmpty()){ //완전 끝이라면
                        continue;
                    }else{
                        tmp.nextR = tmp.nextGoal.poll();
                        tmp.nextC = tmp.nextGoal.poll();
                    }

                }
                //이동 R -> C
                if(tmp.nowR != tmp.nextR){ // R이 다르다면
                    if(tmp.nowR>tmp.nextR){
                        tmp.nowR--;
                    }else{
                        tmp.nowR++;
                    }
                }else{ //C가 다르다면
                    if(tmp.nowC>tmp.nextC){
                        tmp.nowC--;
                    }else{
                        tmp.nowC++;
                    }
                }

                q.add(tmp); //다시 넣기

                //중첩 잇는지 체크
                if(vis[tmp.nowR][tmp.nowC]){
                    visSet.add(tmp.nowR + "," + tmp.nowC);
                }else{
                    vis[tmp.nowR][tmp.nowC]=true;
                }

            }
            answer += visSet.size();
        }

        return answer;
    }
}