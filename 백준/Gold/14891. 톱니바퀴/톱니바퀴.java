import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int[][] nlist = new int[4][8];

		String s1 = br.readLine();
		String s2 = br.readLine();
		String s3 = br.readLine();
		String s4 = br.readLine();
		for (int i = 0; i < 8; i++) {
			nlist[0][i] = s1.charAt(i) - '0';
			nlist[1][i] = s2.charAt(i) - '0';
			nlist[2][i] = s3.charAt(i) - '0';
			nlist[3][i] = s4.charAt(i) - '0';
		}
		
		int k = Integer.parseInt(br.readLine());

		int[] klist = new int[k];
		int[] dlist = new int[k];

		for (int i = 0; i < k; i++) {
			String[] s = br.readLine().split(" ");
			klist[i] = Integer.parseInt(s[0]);
			dlist[i] = Integer.parseInt(s[1]);
		}

		int[][] slist = new int[2][4];
		slist[0][0] = slist[0][1] = slist[0][2] = slist[0][3] = 2;
		slist[1][0] = slist[1][1] = slist[1][2] = slist[1][3] = 6;

		int[] tlist = new int[5];
		tlist[0] = tlist[4] = 0;
		for (int i = 0; i < k; i++) {
			int d = dlist[i];
			for (int j = 1; j <= 3; j++)
				tlist[j] = nlist[j-1][slist[0][j-1]] != nlist[j][slist[1][j]] ? 1 : 0;
			
			for(int j = klist[i]; j <5; j++) {
				slist[0][j-1] = (d == 1) ? ((slist[0][j-1] - 1 < 0) ? 7 : slist[0][j-1]-1) : (slist[0][j-1] + 1) % 8;
				slist[1][j-1] = (d == 1) ? ((slist[1][j-1] - 1 < 0) ? 7 : slist[1][j-1]-1) : (slist[1][j-1] + 1) % 8;
				d = 0 - d;
				if(tlist[j] == 0) break;
			}
			d = 0 - dlist[i];
			for(int j = klist[i]-1; j > 0; j--) {
				if(tlist[j] == 0) break;
				slist[0][j-1] = d == 1 ? (slist[0][j-1] - 1 < 0 ? 7 : slist[0][j-1]-1) : (slist[0][j-1] + 1) % 8;
				slist[1][j-1] = d == 1 ? (slist[1][j-1] - 1 < 0 ? 7 : slist[1][j-1]-1) : (slist[1][j-1] + 1) % 8;
				d = 0 - d;
			}
		}
		
		int cnt = 0;
		
		if(nlist[0][(slist[1][0]+2) % 8] == 1) cnt += 1;
		if(nlist[1][(slist[1][1]+2) % 8] == 1) cnt += 2;
		if(nlist[2][(slist[1][2]+2) % 8] == 1) cnt += 4;
		if(nlist[3][(slist[1][3]+2) % 8] == 1) cnt += 8;
		
		System.out.println(cnt);
	}
}
