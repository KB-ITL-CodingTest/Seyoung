import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

// 빨간 구슬과 파란 구슬의 위치 및 이동 횟수를 저장하는 클래스
class Beads {
    int ry, rx; // 빨간 구슬의 위치
    int by, bx; // 파란 구슬의 위치
    int count;  // 이동 횟수

    public Beads() {}
    public Beads(int ry, int rx, int by, int bx, int count) {
        this.ry = ry;
        this.rx = rx;
        this.by = by;
        this.bx = bx;
        this.count = count;
    }
}

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int[] dy = {-1, 1, 0, 0};
    static int[] dx = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        // 보드의 크기 입력
        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        char[][] board = new char[N][M]; // 게임 보드
        boolean[][][][] visited = new boolean[N][M][N][M]; // 4차원 방문 배열: 각 구슬의 좌표 조합을 기록
        Beads beads = new Beads(); // 초기 구슬 상태
        beads.count = 0;

        int ex = 0, ey = 0; // 구멍(O)의 좌표

        // 보드 정보 및 구슬, 구멍 위치 파싱
        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            for (int j = 0; j < M; j++) {
                char c = s.charAt(j);
                if (c == 'B') {
                    beads.by = i;
                    beads.bx = j;
                } else if (c == 'R') {
                    beads.ry = i;
                    beads.rx = j;
                } else if (c == 'O') {
                    ey = i;
                    ex = j;
                }
                board[i][j] = c;
            }
        }
        System.out.println(bfs(board, visited, ey, ex, beads));
    }

    static int bfs(char[][] board, boolean[][][][] visited, int ey, int ex, Beads init) {
        ArrayDeque<Beads> q = new ArrayDeque<>();
        q.offer(init);
        visited[init.ry][init.rx][init.by][init.bx] = true;

        while (!q.isEmpty()) {
            Beads beads = q.poll();

            // 10번 초과로 움직이면 실패
            if (beads.count == 10) return 0;

            // 4방향으로 구슬을 굴려보기
            for (int d = 0; d < 4; d++) {
                int nry = beads.ry;
                int nrx = beads.rx;
                int nby = beads.by;
                int nbx = beads.bx;

                boolean redFlag = false;
                boolean blueFlag = false;

                int rCount = 0;
                int bCount = 0;

                // 빨간 구슬 이동
                while (board[nry + dy[d]][nrx + dx[d]] != '#') {
                    nry += dy[d];
                    nrx += dx[d];
                    rCount++;
                    if (nry == ey && nrx == ex) {
                        redFlag = true;
                        break;
                    }
                }

                // 파란 구슬 이동
                while (board[nby + dy[d]][nbx + dx[d]] != '#') {
                    nby += dy[d];
                    nbx += dx[d];
                    bCount++;
                    if (nby == ey && nbx == ex) {
                        blueFlag = true;
                        break;
                    }
                }

                // 파란 구슬이 빠졌으면 실패
                if (blueFlag) continue;

                // 빨간 구슬만 빠졌으면 성공
                if (redFlag) return 1;

                // 두 구슬이 같은 위치에 도달했을 경우, 더 많이 이동한 구슬을 한 칸 뒤로
                if (nry == nby && nrx == nbx) {
                    if (rCount > bCount) {
                        nry -= dy[d];
                        nrx -= dx[d];
                    } else {
                        nby -= dy[d];
                        nbx -= dx[d];
                    }
                }

                // 아직 방문하지 않은 상태면 큐에 추가
                if (!visited[nry][nrx][nby][nbx]) {
                    visited[nry][nrx][nby][nbx] = true;
                    q.offer(new Beads(nry, nrx, nby, nbx, beads.count + 1));
                }
            }
        }

        // 10번 이내에 성공하지 못하면 0 반환
        return 0;
    }
}
