package WritingAlgorithms;

import java.util.ArrayList;
import java.util.List;

public class ZAlgorithm {

    // Function to create Z-array for the given string
    private static int[] computeZ(String str) {
        int n = str.length();  // Get the length of the string
        int[] Z = new int[n];  // Initialize the Z-array to store Z-values
        int L = 0, R = 0;  // Initialize the left and right boundary of the current window

        // Iterate over the string to compute Z-values
        for (int i = 1; i < n; i++) {
            // Case 1: When i is outside the current window [L, R]
            if (i > R) {
                L = R = i;  // Set the new window to start at i
                // Expand the window to the right as long as the characters match
                while (R < n && str.charAt(R) == str.charAt(R - L)) {
                    R++;
                }
                Z[i] = R - L;  // Set the Z-value for position i
                R--;  // Adjust the right boundary for the next iterations
            } else {
                // Case 2: When i is within the current window [L, R]
                int K = i - L;  // Calculate the index in the Z-array that corresponds to i
                // If the Z-value of K is less than the remaining length of the window, use it directly
                if (Z[K] < R - i + 1) {
                    Z[i] = Z[K];  // Copy the Z-value from position K
                } else {
                    // Case 3: When Z-value of K is not sufficient to cover the remaining length of the window
                    L = i;  // Move the left boundary to i
                    // Expand the window to the right
                    while (R < n && str.charAt(R) == str.charAt(R - L)) {
                        R++;
                    }
                    Z[i] = R - L;  // Set the Z-value for position i
                    R--;  // Adjust the right boundary for the next iterations
                }
            }
        }
        return Z;  // Return the computed Z-array
    }

    // Function to search for the pattern in the text using Z-algorithm
    public static List<Integer> search(String S, String pat) {
        // Concatenate pattern, a special separator '$', and the text
        String concat = pat + "$" + S;
        int l = concat.length();  // Get the length of the concatenated string
        int[] Z = computeZ(concat);  // Compute Z-array for the concatenated string

        List<Integer> result = new ArrayList<>();  // Initialize a list to store the result indices

        // Iterate over the Z-array to find matches
        for (int i = 0; i < l; i++) {
            // If Z[i] equals the length of the pattern, it indicates a match
            if (Z[i] == pat.length()) {
                result.add(i - pat.length());  // Calculate the starting index (0-based) of the match
                // Note: Convert to 1-based index by adding 1 later if required
            }
        }

        return result;  // Return the list of starting indices
    }

    // Driver code for testing
    public static void main(String[] args) {
        String S1 = "batmanandrobinarebat";  // Example input text 1
        String pat1 = "bat";  // Example input pattern 1
        System.out.println("Pattern found at indices: " + search(S1, pat1));  // Expected output: [1, 18]

        String S2 = "abesdu";  // Example input text 2
        String pat2 = "edu";  // Example input pattern 2
        System.out.println("Pattern found at indices: " + search(S2, pat2));  // Expected output: []
    }
}
