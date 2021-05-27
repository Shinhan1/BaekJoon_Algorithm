package 이분탐색;

/*

랜선 자르기 분류
시간 제한   메모리 제한   제출   정답   맞은 사람   정답 비율
2 초   128 MB   71417   15842   10288   20.335%
문제
집에서 시간을 보내던 오영식은 박성원의 부름을 받고 급히 달려왔다.
박성원이 캠프 때 쓸 N개의 랜선을 만들어야 하는데 너무 바빠서 영식이에게 도움을 청했다.

이미 오영식은 자체적으로 K개의 랜선을 가지고 있다. 그러나 K개의 랜선은 길이가 제각각이다.
박성원은 랜선을 모두 N개의 같은 길이의 랜선으로 만들고 싶었기 때문에 K개의 랜선을 잘라서 만들어야 한다.
예를 들어 300cm 짜리 랜선에서 140cm 짜리 랜선을 두 개 잘라내면 20cm는 버려야 한다. (이미 자른 랜선은 붙일 수 없다.)

편의를 위해 랜선을 자르거나 만들 때 손실되는 길이는 없다고 가정하며, 기존의 K개의 랜선으로 N개의 랜선을 만들 수 없는 경우는 없다고 가정하자.
그리고 자를 때는 항상 센티미터 단위로 정수길이만큼 자른다고 가정하자.
N개보다 많이 만드는 것도 N개를 만드는 것에 포함된다.
이때 만들 수 있는 최대 랜선의 길이를 구하는 프로그램을 작성하시오.

입력
첫째 줄에는 오영식이 이미 가지고 있는 랜선의 개수 K, 그리고 필요한 랜선의 개수 N이 입력된다.
K는 1이상 10,000이하의 정수이고, N은 1이상 1,000,000이하의 정수이다.
그리고 항상 K ≦ N 이다.
그 후 K줄에 걸쳐 이미 가지고 있는 각 랜선의 길이가 센티미터 단위의 정수로 입력된다.
랜선의 길이는 231-1보다 작거나 같은 자연수이다.

출력
첫째 줄에 N개를 만들 수 있는 랜선의 최대 길이를 센티미터 단위의 정수로 출력한다.

예제 입력 1
4 11
802
743
457
539
예제 출력 1
200

*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/1654
public class 랜선자르기_1654 {
    private static int K;
    private static int N;
    private static long[] LAN_Cables;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        K = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        LAN_Cables = new long[N + 1];

        long max = -1;
        for (int i = 0; i < K; i++) {
            LAN_Cables[i] = Long.parseLong(br.readLine());
            max = Math.max(max, LAN_Cables[i]);
        }

        long maxCableLen = binarySearch(max);

        System.out.println(maxCableLen);

        br.close();
    }

    private static long binarySearch(long len) {
        long start = 1;
        long end = len;
        long mid = 0;

        while(start <= end) {
            mid = (start + end) / 2;
            long cableCount = getCable(mid);
            if (N <= cableCount) {
                start = mid + 1;

            }else {
                end = mid - 1;
            }
        }

        return end;
    }

    private static long getCable(long cutCableLen) {
        long cutCableCount = 0;
        for (int i = 0; i < K; i++) {
            cutCableCount += LAN_Cables[i] / cutCableLen;
        }

        return cutCableCount;
    }


}