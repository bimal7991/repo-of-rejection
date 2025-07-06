package Amazon;


/*
Question 1
1. Code Question 1

Some data analysts at Amazon are analyzing the outliers in data that contains two co-related features. The
features are represented as two arrays of nintegers each, feature1, and feature2. A data set is considered free
of outliers if for any two indices /and /where 0 s i <j < n, if feature1[i] > feature1[j], then feature2[i]>
feature2(j] or if feature1[i] < feature1(j], then feature2[i] < feature2(j].
Note that if feature1[i]= feature1(j], then the data set is not considered to be free of outliers.
Given the arrays, feature1 and feature2, find the length of the largest array of indices /1, 12, 13 ... ik, such that
data formed by these indices i.e. [feature1[i1], feature1[12] .... feature1[ik]] and [feature2[1],
feature2[i2] .... feature2[ik]] is free of outliers.

Example
Suppose n = 5, feature1 =(4, 5,3,1, 2], and feature2=[2, 1, 3, 4, 5].

It is optimal to choose the indices [3, 4]. The data for featuret is [1, 2] and for feature2 is [4, 5], Here
feature1[0] < feature1[1] and feature2[0] <feature2[1], therefore the condition holds true. Since is it not
possible to select a larger subset without violating the conditions, the answer is 2 i.e. the size of the chosen
subset.

Function Description
Complete the function findLargestValidFeatureSet in the editor below.

findLargestValidFeatureSet takes the following arguments:
int feature1[n]: the values of the first feature
int feature2[n): the values of the second feature

Returns
int: the length of the largest array of indices to create a dataset free of outliers

Constraints

. 1 sns 103
* 1 s featuref[i], feature2(i] ≤10ª

*/



import java.util.*;
public class AmazonOA_7_6_25 {
    public static void main(String[] args) {
        int[] feature1 = {4, 5, 3, 1, 2};
        int[] feature2 = {2, 1, 3, 4, 5};

        System.out.println(findLargestValidFeatureSet(feature1, feature2)); // Output: 2
    }

    public static int findLargestValidFeatureSet(int[] feature1, int[] feature2) {
        int n = feature1.length;

        // Pair feature1 and feature2
        int[][] pairs = new int[n][2];
        for (int i = 0; i < n; i++) {
            pairs[i][0] = feature1[i];
            pairs[i][1] = feature2[i];
        }

        // Sort by feature1
        Arrays.sort(pairs, (a, b) -> Integer.compare(a[0], b[0]));

        // Build feature2 list, skipping duplicates in feature1
        List<Integer> feature2List = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (i > 0 && pairs[i][0] == pairs[i - 1][0]) {
                continue; // Skip duplicates in feature1
            }
            feature2List.add(pairs[i][1]);
        }

        // Find LIS in feature2List
        return lengthOfLIS(feature2List);
    }
    private static int lengthOfLIS(List<Integer> nums) {
        List<Integer> lis = new ArrayList<>();
        for (int num : nums) {
            int idx = Collections.binarySearch(lis, num);
            if (idx < 0) idx = -idx - 1;

            if (idx == lis.size()) {
                lis.add(num);
            } else {
                lis.set(idx, num);
            }
        }
        return lis.size();
    }



}
