package dfs_bfs;

import java.util.Scanner;

/*

정사각형으로 이루어져 있는 섬과 바다 지도가 주어진다. 
섬의 개수를 세는 프로그램을 작성하시오.

한 정사각형과 가로, 세로 또는 대각선으로 연결되어 있는 사각형은 걸어갈 수 있는 사각형이다. 

두 정사각형이 같은 섬에 있으려면, 
한 정사각형에서 다른 정사각형으로 걸어서 갈 수 있는 경로가 있어야 한다. 
지도는 바다로 둘러싸여 있으며, 지도 밖으로 나갈 수 없다.

입력
입력은 여러 개의 테스트 케이스로 이루어져 있다. 
각 테스트 케이스의 첫째 줄에는 지도의 너비 w와 높이 h가 주어진다. 
w와 h는 50보다 작거나 같은 양의 정수이다.

둘째 줄부터 h개 줄에는 지도가 주어진다. 1은 땅, 0은 바다이다.

입력의 마지막 줄에는 0이 두 개 주어진다.

출력
각 테스트 케이스에 대해서, 섬의 개수를 출력한다.


*/


// 20/9/29 못품..
public class IslandCount_4963 {
	private static int w;
	private static int h;
	private static boolean visited[][];
	private static int map[][];
	private static int cnt;
	
	public static void dfs(int start) {
		if(w == 0 || h == 0) {
			System.out.println("0");
		}
		
		for(int i = 0; i < w; i++) {
			for(int j = 0; j < h; j++) {
				if(map[i][j] == 1 && !visited[i][j]) {
					visited[i][j] = true;
					
				}
			}
		}
		
		
	}
	
	
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		do {
			cnt = 0;
			w = scan.nextInt();
			h = scan.nextInt();
			
			map = new int[w][h];
			
			for(int i = 0; i < w; i++) {
				for(int j = 0; j < h; j++) {
					map[i][j] = scan.nextInt();
				}
			}
			dfs(1);
			
			
		}while(w == 0 && h == 0);
		
		
		
		
		
	}
}
