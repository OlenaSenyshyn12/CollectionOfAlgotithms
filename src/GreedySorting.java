import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class GreedySorting {

	//https://codeforces.com/problemset/problem/1517/B
	//Morning Jogging - greedy, sorting
	public static void main( String[] args ) throws IOException {
		BufferedReader br = new BufferedReader( new InputStreamReader( System.in ) );
		int numberOfTestCases = Integer.valueOf( br.readLine() );
		for ( int i = 0; i < numberOfTestCases; ++i ) {
			String[] s = br.readLine().split(" ");
			int n = Integer.valueOf( s[0] );
			int m = Integer.valueOf( s[1] );
			int[][] a = new int[n][];
			Set<int[]> minPos = new HashSet<>();
			int[] maxWithMinPos = new int[] {-1, -1, -1};
			for ( int j = 0; j < n; ++j ) {
				s = br.readLine().split(" ");
				a[j] = new int[m];
				for ( int k = 0; k < m; ++k ) {
					a[j][k] = Integer.valueOf( s[k] );
					if (j == 0) {
						int[] arr = new int[] {a[j][k], j, k};
						minPos.add( arr );
						if ( maxWithMinPos[0] < a[j][k] || maxWithMinPos[0] == -1 ) {
							maxWithMinPos = arr;
						}
					} else {
						int[] arr = new int[] { a[j][k], j, k };
						if ( maxWithMinPos[0] > a[j][k] ) {
							minPos.remove( maxWithMinPos );
							maxWithMinPos = arr;
							for ( int[] arr1 : minPos ) {
								if ( arr1[0] > maxWithMinPos[0] ) {
									maxWithMinPos = arr1;
								}
							}
							minPos.add( arr );
						}
					}
				}
			}
			int h = 0;
			for ( int[] arr : minPos ) {
				int y1 = arr[1];
				int x1 = arr[2];
				for ( int[] arr1: minPos ) {
					if ( arr1[1] == y1 && arr1[2] == h ) {
						arr1[2] = x1;
					}
				}
				a[y1][x1] = a[y1][h];
				a[y1][h] = arr[0];
				++h;
			}
			StringBuilder sb = new StringBuilder();
			for ( int j = 0; j < n; ++j ) {
				for ( int l = 0; l < m; ++l ) {
					sb.append( a[j][l] + " " );
				}
				sb.append("\n");
			}
			System.out.print(sb);
		}
	}
	

}
