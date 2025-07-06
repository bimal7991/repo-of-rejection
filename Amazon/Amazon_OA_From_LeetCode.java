package Amazon;

/*

The Amazon Alexa development team needs to analyze request logs across numSkills different Alexa
skills to ensure optimal performance and user engagement.

The skills are indexed from 1 to numSkills, and the logs are provided as a 2D array requestLogs of size
m, where requestLogs[i] = [skill_ID, timeStamp] denotes that a request was made to the skill with ID
skill_ID at the time timeStamp.

You are given an integer numSkills, a 2D integer array requestLogs, an integer timeWindow
(representing a lookback period), and an array of queryTimes containing q queries.

For each queryTime[i], determine the number of skills that did not receive a request in the time interval
[queryTime[i] - timeWindow, queryTime[i]]. Return an array of length q containing the result of each of
the query.

Note: If for some query all the numSkills received request in the given time interval for that query, then
answer is 0.

Example
Suppose numSkills = 3, timeWindow= 5, requestLogs[] = [[1, 3], [2, 6], [1, 5]], and queryTime[]=[10,11]

ans [1,2]
 */
import java.util.*;
public class Amazon_OA_From_LeetCode {
    public static void main(String[] args) {
        int numSkills = 3;
        int[][] requestLogs = {{1, 3}, {2, 6}, {1, 5}};
        int timeWindow = 5;
        int[] queryTimes = {10, 11};

        int[] res = countInactiveSkills(numSkills, requestLogs, timeWindow, queryTimes);

        System.out.println(Arrays.toString(res)); // Output: [1, 2]
    }

    public static int[] countInactiveSkills(int numSkills, int[][] requestLogs, int timeWindow, int[] queryTimes) {
        // Map to store skill_ID -> list of timestamps
        Map<Integer, List<Integer>> skillMap = new HashMap<>();

        for (int i = 1; i <= numSkills; i++) {
            skillMap.put(i, new ArrayList<>());
        }

        // Populate the map
        for (int[] log : requestLogs) {
            int skillId = log[0];
            int time = log[1];
            skillMap.get(skillId).add(time);
        }

        // Sort the timestamps for each skill
        for (List<Integer> times : skillMap.values()) {
            Collections.sort(times);
        }

        int[] result = new int[queryTimes.length];

        for (int i = 0; i < queryTimes.length; i++) {
            int queryTime = queryTimes[i];
            int startTime = queryTime - timeWindow;
            int inactiveCount = 0;

            for (int skill = 1; skill <= numSkills; skill++) {
                List<Integer> times = skillMap.get(skill);

                // Binary search for first time > queryTime
                int index = Collections.binarySearch(times, queryTime);

                if (index < 0) {
                    index = -index - 1;
                } else {
                    index = index + 1;
                }

                // Now, the last time <= queryTime is at index - 1
                if (index == 0 || times.get(index - 1) < startTime) {
                    inactiveCount++;
                }
            }

            result[i] = inactiveCount;
        }

        return result;
    }
}
