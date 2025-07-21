import java.io.*;
import java.util.*;

public class Solution {

    public static int twoStacks(int maxSum, List<Integer> a, List<Integer> b) {
        int i = 0, j = 0, currSum = 0, maxCount = 0;

        // Take from stack a
        while (i < a.size() && currSum + a.get(i) <= maxSum) {
            currSum += a.get(i);
            i++;
        }

        if (currSum > maxSum) {
            i--;
            maxCount = Math.max(maxCount, i);
        } else {
            maxCount = Math.max(maxCount, i);
            i--;
        }

        // Now try taking from stack b
        while (j < b.size() && currSum <= maxSum) {
            currSum += b.get(j);
            j++;

            while (currSum > maxSum && i >= 0) {
                currSum -= a.get(i);
                i--;
            }

            if (currSum <= maxSum) {
                maxCount = Math.max(maxCount, i + j + 1);
            }
        }

        return maxCount;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int g = Integer.parseInt(bufferedReader.readLine().trim());

        for (int gItr = 0; gItr < g; gItr++) {
            String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

            int n = Integer.parseInt(firstMultipleInput[0]);
            int m = Integer.parseInt(firstMultipleInput[1]);
            int maxSum = Integer.parseInt(firstMultipleInput[2]);

            List<Integer> a = new ArrayList<>();
            List<Integer> b = new ArrayList<>();

            String[] aItems = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");
            for (int i = 0; i < n; i++) {
                a.add(Integer.parseInt(aItems[i]));
            }

            String[] bItems = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");
            for (int i = 0; i < m; i++) {
                b.add(Integer.parseInt(bItems[i]));
            }

            int result = twoStacks(maxSum, a, b);

            bufferedWriter.write(String.valueOf(result));
            bufferedWriter.newLine();
        }

        bufferedReader.close();
        bufferedWriter.close();
    }
}
