package 이분탐색;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*

숫자 카드 분류
시간 제한   메모리 제한   제출   정답   맞은 사람   정답 비율
2 초   256 MB   28109   13788   9487   48.611%
문제
숫자 카드는 정수 하나가 적혀져 있는 카드이다. 상근이는 숫자 카드 N개를 가지고 있다.
정수 M개가 주어졌을 때, 이 수가 적혀있는 숫자 카드를 상근이가 가지고 있는지 아닌지를 구하는 프로그램을 작성하시오.

입력
첫째 줄에 상근이가 가지고 있는 숫자 카드의 개수 N(1 ≤ N ≤ 500,000)이 주어진다.
둘째 줄에는 숫자 카드에 적혀있는 정수가 주어진다.
숫자 카드에 적혀있는 수는 -10,000,000보다 크거나 같고, 10,000,000보다 작거나 같다.
두 숫자 카드에 같은 수가 적혀있는 경우는 없다.

셋째 줄에는 M(1 ≤ M ≤ 500,000)이 주어진다.
넷째 줄에는 상근이가 가지고 있는 숫자 카드인지 아닌지를 구해야 할 M개의 정수가 주어지며, 이 수는 공백으로 구분되어져 있다.
이 수도 -10,000,000보다 크거나 같고, 10,000,000보다 작거나 같다

출력
첫째 줄에 입력으로 주어진 M개의 수에 대해서, 각 수가 적힌 숫자 카드를 상근이가 가지고 있으면 1을, 아니면 0을 공백으로 구분해 출력한다.

예제 입력 1
5
6 3 2 10 -10
8
10 9 -5 2 3 4 5 -10
예제 출력 1
1 0 0 1 1 0 0 1

*/

public class 숫자카드_10815 {
    private static int N, M;
//    private static int arr[];
    private static List<Integer> arr = new ArrayList<>();
    private static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
//        arr = new int[N];
//        Set<Integer> set = new HashSet<>();
        for(int i = 0; i < N; i++) {
//            arr[i] = Integer.parseInt(st.nextToken());
            arr.add(Integer.parseInt(st.nextToken()));
//            set.add(Integer.parseInt(st.nextToken()));
        }

//        Arrays.sort(arr);
        Collections.sort(arr);
        M = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < M; i++) {
            binarySearch(Integer.parseInt(st.nextToken()));
        }

        System.out.println(sb.toString());

        br.close();
    }

    private static void binarySearch(int search){
        int start = 0;
        int end = N-1;

        while(start <= end) {
            int mid = (start + end) / 2;

            if(arr.get(mid) > search) {
                end = mid - 1;
            }else if(arr.get(mid) < search) {
                start = mid + 1;
            }else {
                sb.append("1 ");
                return;
            }
        }
        sb.append("0 ");
    }
}