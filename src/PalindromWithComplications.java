import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class PalindromWithComplications {

    //https://codeforces.com/contest/1512/problem/C
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader( new InputStreamReader( System.in ) );
        int n = Integer.valueOf( br.readLine() );
        StringBuilder sb = new StringBuilder();
        for ( int i = 0; i < n; ++i ) {
            String[] s = br.readLine().split(" ");
            int a = Integer.valueOf(s[0]);
            int b = Integer.valueOf(s[1]);
            char[] arr = br.readLine().toCharArray();
            sb.append( find(arr, a, b) );
        }
        System.out.println(sb);
    }

    public static StringBuilder find( char[] arr, int a, int b ) {
        StringBuilder sb = new StringBuilder();
        int count0 = 0, count1 = 0;
        int l = arr.length;
        for ( int j = 0; j < l/2; ++j ) {
            if ( arr[j] != '?' || arr[l - j - 1] != '?' ) {
                if ( arr[j] == '?' && arr[l-j-1] != '?' ) {
                    if ( arr[l - j - 1] == '0' ) {
                        arr[j] = '0';
                        count0 += j == l - j - 1 ? 1 : 2;
                    } else {
                        arr[j] = '1';
                        count1 += j == l - j - 1 ? 1 : 2;
                    }
                } else if ( arr[l - j - 1] == '?' && arr[j] != '?' ) {
                    if ( arr[j] == '0' ) {
                        arr[l-j-1] = '0';
                        count0 += j == l - j - 1 ? 1 : 2;
                    } else {
                        arr[l-j-1] = '1';
                        count1 += j == l - j - 1 ? 1 : 2;
                    }
                } else if ( arr[j] != '?' && arr[l-j-1] != '?' && arr[j] != arr[l - j - 1] ) {
                    return sb.append("-1").append("\n");
                } else if ( arr[j] == '0' ) {
                    count0 +=  j == l - j - 1 ? 1 : 2;
                } else {
                    count1 += j == l - j - 1 ? 1 : 2;
                }
            }
        }
        if ( count0 < a || count1 < b ) {
            if ( a%2 == 1 && b%2 == 1 ) {
                return sb.append("-1").append("\n");
            } else if ( a%2 == 1 ) {
                if ( l%2 == 1 ) {
                    if ( arr[l/2] == '?' ) {
                        arr[l/2] = '0';
                        ++count0;
                    } else if ( arr[l/2] == '0' ) {
                        ++count0;
                    } else {
                        return sb.append("-1").append("\n");
                    }
                } else {
                    return sb.append("-1").append("\n");
                }
            } else if ( b%2 == 1 ) {
                if ( l%2 == 1 ) {
                    if ( arr[l/2] == '?' ) {
                        arr[l/2] = '1';
                        ++count1;
                    } else if ( arr[l/2] == '1' ) {
                        ++count1;
                    } else {
                        return sb.append("-1").append("\n");
                    }
                } else {
                    return sb.append("-1").append("\n");
                }
            }
            for ( int j = 0; j < l/2; ++j ) {
                if ( arr[j] == '?' ) {
                    if ( count0 < a ) {
                        arr[j] = '0';
                        arr[l - j - 1] = '0';
                        count0 += 2 ;
                    } else if ( count1 < b ) {
                        arr[j] = '1';
                        arr[l - j - 1] = '1';
                        count1 += 2;
                    }
                }
            }
        }
        if ( count0 != a || count1 != b ) {
            sb.append("-1").append("\n");
        } else {
            sb.append(arr).append("\n");
        }
        return sb;
    }
}
