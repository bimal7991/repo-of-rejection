package Amazon;

/*
  Database security and authentication have become vital due to the increasing number of cyberattacks every day. Amazon has created a
team for the analysis of various types of cyberattacks. In one such analysis, the team finds a virus that attacks the user passwords. The
virus designed has an attacking rule defined by attackOrder, which is a permutation of length n. In the ith second of the attack, the virus
attacks at the attackOrder[ijth character of the password, replacing it with the malicious character '*1, i.e, password[attackOrder[i]] = '*'
after the ith second.
Hac
The password is said to be irrecoverable when the number of substrings of the password containing at least one malicious character '*"
on becomes greater than or equal to m. In order to estimate the threat posed by the virus, the team wishes to find the minimum time
after which the password becomes irrecoverable.
Note:
. If the password is irrecoverable at the start, report 1 as the answer.
. A substring of a string sis a contiguous segment of that string.
Example
There is a password of length n = 5, password = "bcced". The 1-based indices where characters will be replaced, attackOrder = [2, 3, 1, 4,
5], and the recoverability parameter m = 10.

After the 1st second, the password becomes:
The 8 substrings that containt at least one malicious character are ["b*", "b*c", "b*ce", "b*ced", "*", "*c", "*ce", (*ced"] and 8 is less
than m.
The 11 substrings that contain at least one malicious character are ["b*", "b ** ", "b ** e", "b ** ed", "*" " ** *** e; *** ed ** "*e;
** ed"]. This is greater than or equal to m.

After the replacement at second 2, the number of substrings is at least m. The answer is 2.

Function Description
Complete the function findMinimumTime in the editor below.

findMinimumTime has the following parameters:
string password: the initial password
int attackOrder[n]: permutation array of integers [1, 2, .. , n], the attack order of the virus
int m: the recoverability parameter

Returns
int: the minimum time after which the password becomes irrecoverable.

Constraints

. 1sns8*105
. String password consists of lowercase English characters
· 1 s attackOrderti s n
. 0smsn*(n+1)/2
 */


import java.util.*;
public class AmazonOA_7_6_25_Q2 {

    public static int minTimeToIrrecoverable(String password, int[] attackOrder, int m) {
        int n = password.length();
        long totalSubstrings = (long) n * (n + 1) / 2;
        long cleanSubstrings = totalSubstrings;
        TreeSet<Integer> attackedPositions = new TreeSet<>();
        attackedPositions.add(0);
        attackedPositions.add(n + 1);
        if (m == 0) return 1;
        for (int time = 0; time < n; time++) {
            int attackPos = attackOrder[time];

            Integer lower = attackedPositions.lower(attackPos);
            Integer higher = attackedPositions.higher(attackPos);


            int left = attackPos - lower - 1;
            int right = higher - attackPos - 1;


            long totalBefore = ((long) (left + right + 1) * (left + right + 2)) / 2;
            long totalAfter = ((long) left * (left + 1)) / 2 + ((long) right * (right + 1)) / 2;

            cleanSubstrings -= (totalBefore - totalAfter);
            attackedPositions.add(attackPos);

            long maliciousSubstrings = totalSubstrings - cleanSubstrings;
            if (maliciousSubstrings >= m) {
                return time + 1;
            }
        }
        return -1;
    }
}

