package WritingAlgorithms;

public class BubbleSort {

    // Function to perform bubble sort on the array
    void bubbleSort(int[] arr, int N) {
        // Outer loop to iterate over each element in the array
        for (int i = 0; i < N - 1; i++) {
            // Inner loop to perform the comparison and swap
            for (int j = 0; j < N - i - 1; j++) {
                // If the current element is greater than the next element
                if (arr[j] > arr[j + 1]) {
                    // Swap the elements
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }

    // Utility function to print the array
    void printArray(int[] arr, int N) {
        // Loop through the array
        for (int i = 0; i < N; i++) {
            System.out.print(arr[i] + " ");  // Print each element followed by a space
        }
        System.out.println();  // Print a new line after the array is printed
    }

    // Driver code
    public static void main(String args[]) {
        BubbleSort ob = new BubbleSort();  // Create an instance of the BubbleSort class
        int arr[] = {4, 1, 3, 9, 7};  // Initialize an array with elements
        int N = arr.length;  // Get the length of the array

        System.out.println("Original Array:");  // Print the original array message
        ob.printArray(arr, N);  // Call the printArray method to print the original array

        ob.bubbleSort(arr, N);  // Call the bubbleSort method to sort the array

        System.out.println("Sorted Array:");  // Print the sorted array message
        ob.printArray(arr, N);  // Call the printArray method to print the sorted array
    }
}
