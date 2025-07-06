package Amazon;

/*
  2. Code Question 2

The developers at Amazon want to perform a reliability drill on some servers. There are n servers
where the ith server can serve request[i] number of requests and has an initial health of
health[i] units.
Each second, the developers send the maximum possible number of requests that can be served by
all the available servers. With the request, the developers can also send a virus to one of the servers
that can decrease the health of a particular server by kunits. The developers can choose the server
where the virus should be sent. A server goes down when its health is less than or equal to 0.
After all the servers are down, the developers must send one more request to conclude the failure of
the application.
Find the minimum total number of requests that the developers must use to bring all the servers
down.
Example
Consider n =2, request = [3, 4], health = [4, 6], k=3,
The minimum number of requests required is 21.
No. of Request

7
7
3
3
1
Total
Requests
3 + 4 = 7
3 + 4 = 7
Virus
Server
1
1
3
0
- conclude the failure
3
Server's
New Health

6 - 3 = 3
3 - 3 = 0 server 1 dies
4- 3 =1
1 - 3 = -2 server 0 dies
 */


import java.util.*;

class Server {
    int id;
    int req;
    int health;

    Server(int id, int req, int health) {
        this.id = id;
        this.req = req;
        this.health = health;
    }
}
public class Amazon_OA_From_LeetCodeQ2 {

    public static void main(String[] args) {
        int[] request = {3, 4};
        int[] health = {4, 6};
        int k = 3;

        int result = minTotalRequests(request, health, k);
        System.out.println(result);  // Output: 21
    }

    public static int minTotalRequests(int[] request, int[] health, int k) {
        int n = request.length;
        int totalRequests = 0;
        PriorityQueue<Server> pq = new PriorityQueue<>((a, b) -> b.req - a.req);

        int totalReqPerSecond = 0;

        for (int i = 0; i < n; i++) {
            pq.add(new Server(i, request[i], health[i]));
            totalReqPerSecond += request[i];
        }

        boolean[] isAlive = new boolean[n];
        Arrays.fill(isAlive, true);

        int aliveCount = n;

        while (aliveCount > 0) {
            totalRequests += totalReqPerSecond;

            Server target = pq.poll();
            target.health -= k;

            if (target.health <= 0) {
                isAlive[target.id] = false;
                totalReqPerSecond -= target.req;
                aliveCount--;
            } else {
                pq.add(target);
            }
        }

        totalRequests += 1;
        return totalRequests;
    }
}

