import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.StringTokenizer;


class BabyShark {
    int x, y, dist;
    public BabyShark(int x, int y, int dist) {
        this.x = x;
        this.y = y;
        this.dist = dist;
    }
}

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int[][] space;   // 그래프 주어진 입력을 2차원 배열로 받는 부분
    static int y;   // 아기 상어의 y좌표
    static int x;   // 아기 상어의 x좌표
    static int N;   // 공간의 크기
    static int[] dy = {-1, 0, 0, 1};    // y축 변화량
    static int[] dx = {0, -1, 1, 0};    // x축 변화량
    static int[] prey = new int[7];   // 사이즈별 물고기 수를 저장하는 배열
    static int size = 2;    // 아기 상어 초기 사이즈
    static int count = 0;   // 먹은 먹이 수
    static int total_dist = 0;  // 최종 출력

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        space = new int[N][N];

        // 이중 for 문으로 space 입력 받기
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                space[i][j] = Integer.parseInt(st.nextToken());

                // 0은 빈 공간
                if (space[i][j] != 0) {
                    // 9는 아기 상어의 초기 좌표
                    if (space[i][j] == 9) {
                        y = i;
                        x = j;
                    }
                    // 1 ~ 6 사이즈별 먹이 수
                    else {
                        prey[space[i][j]]++;
                    }
                }
            }
        }

        // 아기 상어가 있던 좌표 초기화
        space[y][x] = 0;

        // 먹이를 먹을 수 있으면 bfs 수행
        while (canEat()) {
            int move = bfs();
            if (move == -1) break;
            total_dist += move;
            eat();
        }
        System.out.println(total_dist);
    }

    // 먹을 수 있는 물고기가 있는지 여부
    static boolean canEat() {
        for (int i = 1; i < Math.min(size, 7); i++) {
            if (prey[i] > 0) return true;
        }
        return false;
    }

    static int bfs() {
        ArrayDeque<BabyShark> queue = new ArrayDeque<>();
        queue.offer(new BabyShark(x, y, 0));
        boolean[][] visited = new boolean[N][N];
        visited[y][x] = true;

        while (!queue.isEmpty()) {
            BabyShark shark = queue.poll();
            x = shark.x;
            y = shark.y;
            int dist = shark.dist;

            if (space[y][x] != 0 && size > space[y][x]) {
                while (!queue.isEmpty()) {
                    BabyShark candidate = queue.poll();

                    if (candidate.dist > dist) break;
                    int cx = candidate.x;
                    int cy = candidate.y;

                    if (space[cy][cx] != 0 && size > space[cy][cx]) {
                        if (cy < y || (cy == y && cx < x)) {
                            y = cy;
                            x = cx;
                        }
                    }
                }
                prey[space[y][x]]--;
                space[y][x] = 0;
                return dist;
            }

            // 4방향 갱신
            for (int d = 0; d < 4; d++) {
                int ny = y + dy[d];
                int nx = x + dx[d];

                // 자신의 크기보다 큰 상어가 있는 경우 가지 못한다.
                if (0 <= ny && ny < N && 0 <= nx && nx < N && !visited[ny][nx] && space[ny][nx] <= size) {
                    visited[ny][nx] = true;
                    queue.offer(new BabyShark(nx, ny, dist + 1));
                }
            }
        }
        return -1;
    }


    static void eat() {
        count++;
        if (count == size) {
            size++;
            count = 0;
        }
    }
}
