import java.io.*;
import java.util.*;

public class Main {
	static void rotate(char d, char[][] cube) {
		char[][] copy = new char[3][3];
		for (int x = 0; x < 3; x++)
			for (int y = 0; y < 3; y++) {
				if (d == '+')
					copy[x][y] = cube[2-y][x];
				else
					copy[x][y] = cube[y][2-x];
			}
		for (int x = 0; x < 3; x++)
			for (int y = 0; y < 3; y++)
				cube[x][y] = copy[x][y];
	}

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int T = Integer.parseInt(br.readLine());

		for (int t = 0; t < T; t++) {
			int n = Integer.parseInt(br.readLine());
			// cube initializing
			// yellow - Down
			char[][] yellow = new char[3][3];
			char[][] red = new char[3][3];
			char[][] white = new char[3][3];
			char[][] orange = new char[3][3];
			char[][] green = new char[3][3];
			char[][] blue = new char[3][3];
			for (int x = 0; x < 3; x++) {
				Arrays.fill(yellow[x], 'y');
				Arrays.fill(red[x], 'r');
				Arrays.fill(white[x], 'w');
				Arrays.fill(orange[x], 'o');
				Arrays.fill(green[x], 'g');
				Arrays.fill(blue[x], 'b');
			}

			StringTokenizer str = new StringTokenizer(br.readLine(), " ");
			for (int i = 0; i < n; i++) {
				String s = str.nextToken();
				char c = s.charAt(0);
				char d = s.charAt(1);

				if (c == 'F') {
					rotate(d, red);
					// 해당 면과 인접한 곳도 같이 돌아간다.
					char[] copy = white[2].clone();
					if (d == '+') {
						for (int j = 0; j < 3; j++)
							white[2][j] = green[2 - j][2];
						for (int j = 0; j < 3; j++)
							green[j][2] = yellow[0][j];
						for (int j = 0; j < 3; j++)
							yellow[0][j] = blue[2 - j][0];
						for (int j = 0; j < 3; j++)
							blue[j][0] = copy[j];
					} else {
						for (int j = 0; j < 3; j++)
							white[2][j] = blue[j][0];
						for (int j = 0; j < 3; j++)
							blue[j][0] = yellow[0][2-j];
						for (int j = 0; j < 3; j++)
							yellow[0][j] = green[j][2];
						for (int j = 0; j < 3; j++)
							green[j][2] = copy[2-j];
					}
				} else if (c == 'B') {
					rotate(d, orange);
					char[] copy = white[0].clone();
					if (d == '+') {
						for (int j = 0; j < 3; j++)
							white[0][j] = blue[j][2];
						for (int j = 0; j < 3; j++)
							blue[j][2] = yellow[2][2-j];
						for (int j = 0; j < 3; j++)
							yellow[2][j] = green[j][0];
						for (int j = 0; j < 3; j++)
							green[j][0] = copy[2-j];
					} else {
						for (int j = 0; j < 3; j++)
							white[0][j] = green[2 - j][0];
						for (int j = 0; j < 3; j++)
							green[j][0] = yellow[2][j];
						for (int j = 0; j < 3; j++)
							yellow[2][j] = blue[2 - j][2];
						for (int j = 0; j < 3; j++)
							blue[j][2] = copy[j];
					}
				} else if (c == 'U') {
					rotate(d, white);
					char[] copy = orange[0].clone();
					if (d == '+') {
						for (int j = 0; j < 3; j++)
							orange[0][j] = green[0][j];
						for (int j = 0; j < 3; j++)
							green[0][j] = red[0][j];
						for (int j = 0; j < 3; j++)
							red[0][j] = blue[0][j];
						for (int j = 0; j < 3; j++)
							blue[0][j] = copy[j];
					} else {
						for (int j = 0; j < 3; j++)
							orange[0][j] = blue[0][j];
						for (int j = 0; j < 3; j++)
							blue[0][j] = red[0][j];
						for (int j = 0; j < 3; j++)
							red[0][j] = green[0][j];
						for (int j = 0; j < 3; j++)
							green[0][j] = copy[j];
					}
				} else if (c == 'D') {
					rotate(d, yellow);
					char[] copy = orange[2].clone();
					if (d == '+') {
						for (int j = 0; j < 3; j++)
							orange[2][j] = blue[2][j];
						for (int j = 0; j < 3; j++)
							blue[2][j] = red[2][j];
						for (int j = 0; j < 3; j++)
							red[2][j] = green[2][j];
						for (int j = 0; j < 3; j++)
							green[2][j] = copy[j];
					} else {
						for (int j = 0; j < 3; j++)
							orange[2][j] = green[2][j];
						for (int j = 0; j < 3; j++)
							green[2][j] = red[2][j];
						for (int j = 0; j < 3; j++)
							red[2][j] = blue[2][j];
						for (int j = 0; j < 3; j++)
							blue[2][j] = copy[j];
					}
				} else if (c == 'L') {
					rotate(d, green);
					char[] copy = new char[3];
					for(int j = 0; j < 3; j++)
						copy[j] = white[j][0];
					if (d == '+') {
						for (int j = 0; j < 3; j++)
							white[j][0] = orange[2-j][2];
						for (int j = 0; j < 3; j++)
							orange[j][2] = yellow[2-j][0];
						for (int j = 0; j < 3; j++)
							yellow[j][0] = red[j][0];
						for (int j = 0; j < 3; j++)
							red[j][0] = copy[j];
					} else {
						for (int j = 0; j < 3; j++)
							white[j][0] = red[j][0];
						for (int j = 0; j < 3; j++)
							red[j][0] = yellow[j][0];
						for (int j = 0; j < 3; j++)
							yellow[j][0] = orange[2-j][2];
						for (int j = 0; j < 3; j++)
							orange[j][2] = copy[2-j];
					}
				} else if (c == 'R') {
					rotate(d, blue);
					char[] copy = new char[3];
					for(int j = 0; j < 3; j++)
						copy[j] = white[j][2];
					if (d == '+') {
						for (int j = 0; j < 3; j++)
							white[j][2] = red[j][2];
						for (int j = 0; j < 3; j++)
							red[j][2] = yellow[j][2];
						for (int j = 0; j < 3; j++)
							yellow[j][2] = orange[2-j][0];
						for (int j = 0; j < 3; j++)
							orange[j][0] = copy[2-j];
					} else {
						for (int j = 0; j < 3; j++)
							white[j][2] = orange[2-j][0];
						for (int j = 0; j < 3; j++)
							orange[j][0] = yellow[2-j][2];
						for (int j = 0; j < 3; j++)
							yellow[j][2] = red[j][2];
						for (int j = 0; j < 3; j++)
							red[j][2] = copy[j];
					}
				}
			}

			// 윗면 색깔 출력
			for(int i = 0; i < 3; i++) {
				for(char a : white[i])
					bw.write(a);
				bw.write("\n");
			}
			bw.flush();
		}
		bw.close();
		br.close();
	}
}
