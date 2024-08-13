package WritingAlgorithms;

public class CountingSort {

    // Function to sort the string using Counting Sort
    public static String countSort(String arr) {
        int n = arr.length();  // Get the length of the input string

        // Create a count array to store the frequency of each character
        int[] count = new int[26];  // There are 26 lowercase English letters

        // Output array to store the sorted characters
        char[] output = new char[n];

        // Step 1: Count the frequency of each character in the input string
        for (int i = 0; i < n; i++) {
            count[arr.charAt(i) - 'a']++;  // Increment the count of the character
        }

        // Step 2: Update the count array to store the actual positions of characters
        for (int i = 1; i < 26; i++) {
            count[i] += count[i - 1];  // Cumulative count
        }

        // Step 3: Build the output character array
        for (int i = n - 1; i >= 0; i--) {
            char ch = arr.charAt(i);  // Get the character at the current position
            output[count[ch - 'a'] - 1] = ch;  // Place the character in the output array
            count[ch - 'a']--;  // Decrease the count for the character
        }

        // Convert the output array to a string and return it
        return new String(output);
    }

    // Driver code for testing
    public static void main(String[] args) {
        String s1 = "bubble";  // New example input string 1
        String s2 = "algorithm";  // New example input string 2

        System.out.println("Original String: " + s1);
        System.out.println("Sorted String: " + countSort(s1));  // Expected output: "bbbelu"

        System.out.println("Original String: " + s2);
        System.out.println("Sorted String: " + countSort(s2));  // Expected output: "aghilmort"
    }
}
