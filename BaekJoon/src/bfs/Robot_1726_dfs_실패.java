package bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*

로봇 출처분류
시간 제한	메모리 제한	제출	정답	맞은 사람	정답 비율
2 초	128 MB	11684	2889	1885	24.493%
문제
많은 공장에서 로봇이 이용되고 있다. 
우리 월드 공장의 로봇은 바라보는 방향으로 궤도를 따라 움직이며, 움직이는 방향은 동, 서, 남, 북 가운데 하나이다. 
로봇의 이동을 제어하는 명령어는 다음과 같이 두 가지이다.

명령 1. Go k: k는 1, 2 또는 3일 수 있다. 현재 향하고 있는 방향으로 k칸 만큼 움직인다.
명령 2. Turn dir: dir은 left 또는 right 이며, 각각 왼쪽 또는 오른쪽으로 90° 회전한다.
공장 내 궤도가 설치되어 있는 상태가 아래와 같이 0과 1로 이루어진 직사각형 모양으로 로봇에게 입력된다. 
0은 궤도가 깔려 있어 로봇이 갈 수 있는 지점이고, 1은 궤도가 없어 로봇이 갈 수 없는 지점이다. 
로봇이 (4, 2) 지점에서 남쪽을 향하고 있을 때, 이 로봇을 (2, 4) 지점에서 동쪽으로 향하도록 이동시키는 것은 아래와 같이 9번의 명령으로 가능하다.



로봇의 현재 위치와 바라보는 방향이 주어졌을 때, 로봇을 원하는 위치로 이동시키고, 원하는 방향으로 바라보도록 하는데 최소 몇 번의 명령이 필요한지 구하는 프로그램을 작성하시오.

입력
첫째 줄에 공장 내 궤도 설치 상태를 나타내는 직사각형의 세로 길이 M과 가로 길이 N이 빈칸을 사이에 두고 주어진다. 
이때 M과 N은 둘 다 100이하의 자연수이다. 
이어 M줄에 걸쳐 한 줄에 N개씩 각 지점의 궤도 설치 상태를 나타내는 숫자 0 또는 1이 빈칸을 사이에 두고 주어진다. 
다음 줄에는 로봇의 출발 지점의 위치 (행과 열의 번호)와 바라보는 방향이 빈칸을 사이에 두고 주어진다. 
마지막 줄에는 로봇의 도착 지점의 위치 (행과 열의 번호)와 바라보는 방향이 빈칸을 사이에 두고 주어진다. 
방향은 동쪽이 1, 서쪽이 2, 남쪽이 3, 북쪽이 4로 주어진다. 출발지점에서 도착지점까지는 항상 이동이 가능하다.

출력
첫째 줄에 로봇을 도착 지점에 원하는 방향으로 이동시키는데 필요한 최소 명령 횟수를 출력한다.

예제 입력 1 
5 6
0 0 0 0 0 0
0 1 1 0 1 0
0 1 0 0 0 0
0 0 1 1 1 0
1 0 0 0 0 0
4 2 3
2 4 1
예제 출력 1 
9

*/

// dfs로 풀었는데 시간초과
public class Robot_1726_dfs_실패 {
	private static int N, M;
	private static int map[][];
	private static boolean visited[][];
	private static int location[][] = new int[2][3];
	private static int min = Integer.MAX_VALUE;
	
	// dx, dy는 0, 1, 2, 3 -> 동 서 남 북 순이다
	private static int dx[] = {0, 0, 1, -1};
	private static int dy[] = {1, -1, 0, 0};
	
	// 동 : 1, 서 : 2, 남 : 3, 북 : 4
	// -> 동 : 0, 서 : 1, 남 : 2, 북 : 3 (변경)
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		visited = new boolean[N][M];
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for(int i = 0; i < 2; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < 3; j++) {
				// location[0] : 시작 위치와 방향
				// location[1] : 끝 위치와 방향
				location[i][j] = Integer.parseInt(st.nextToken())-1;
			}
		}
		
		// 시작 위치 방문처리
		visited[location[0][0]][location[0][1]] = true;
		// 시작 위치와 방향 -> dfs
		moveRobot(location[0][0], location[0][1], location[0][2], 0, 0);
		
		System.out.println(min);
		
		br.close();
	}
	
	// 로봇이 움직이는 메소드 dfs
	// 1, 2 또는 3칸을 한 번의 명령으로 직진할 수 있다. -> straight으로 판단
	// x, y 는 좌표, direction : 방향, count : 명령의 개수
	private static void moveRobot(int x, int y, int direction, int count, int straight) {
		if(x == location[1][0] && y == location[1][1]) {
			if((direction+location[1][2])%4 == 1) {
				count += 2;
			}else if(direction != location[1][2]) {
				count += 1;
			}
			System.out.println(direction + ", " + location[1][2]);
			
			min = Math.min(min, count);
			return;
		}
		
		for(int i = 0; i < 4; i++) {
			int X = x + dx[i];
			int Y = y + dy[i];
			
			// X, Y는 회전과 이동이 동시에 진행된다.
			if(X >= 0 && X < N && Y >= 0 && Y < M && !visited[X][Y] && map[X][Y] == 0) {
				// 바라보는 방향과 반대방향으로 갈 경우는 회전 명령을 2번 주어야 하므로 +2
				if((direction+i)%4 == 1) {
					count += 2;
					straight = 0;
				// 바라보는 방향이 양 옆일 때 회전 명령을 1번 주어야 하므로 +1
				}else if(direction != i) {
					count += 1;
					straight = 0;
				}
				visited[X][Y] = true;
				if(straight > 0 && straight < 3) {
					count -= 1;
				}
				if(straight == 3) {
					straight = 0;
				}
				moveRobot(X, Y, i, count+1, straight+1);
				visited[X][Y] = false;
			}
		}
	}
}
