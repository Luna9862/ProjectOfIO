package WritingAlgorithms;

public class QuickSort {

    // Function to partition the array based on the pivot element
    int partition(int[] arr, int low, int high) {
        int pivot = arr[high];  // Pivot element (chosen as the last element)
        int i = low - 1;  // Index of the smaller element, initially set to low - 1

        // Traverse through the array from the low index to the high index - 1
        for (int j = low; j < high; j++) {
            // If the current element is smaller than or equal to the pivot
            if (arr[j] <= pivot) {
                i++;  // Increment index of smaller element
                // Swap the current element with the element at index i
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }

        // Swap the pivot element with the element at index i + 1 to place it in the correct position
        int temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;

        return i + 1;  // Return the partitioning index
    }

    // The main function that implements QuickSort
    void quickSort(int[] arr, int low, int high) {
        if (low < high) {  // Base case: if the low index is less than the high index
            // Partition the array and get the pivot element index
            int pi = partition(arr, low, high);

            // Recursively sort elements before the partition
            quickSort(arr, low, pi - 1);
            // Recursively sort elements after the partition
            quickSort(arr, pi + 1, high);
        }
    }

    // Utility function to print the array
    void printArray(int[] arr) {
        for (int value : arr) {  // Enhanced for loop to traverse the array
            System.out.print(value + " ");  // Print each element followed by a space
        }
        System.out.println();  // Print a new line after the array is printed
    }

    // Driver code
    public static void main(String[] args) {
        QuickSort ob = new QuickSort();  // Create an instance of the QuickSort class
        int[] arr = {4, 1, 3, 9, 7};  // Initialize an array with elements

        System.out.println("Original Array:");  // Print the original array message
        ob.printArray(arr);  // Call the printArray method to print the original array

        ob.quickSort(arr, 0, arr.length - 1);  // Call the quickSort method to sort the array

        System.out.println("Sorted Array:");  // Print the sorted array message
        ob.printArray(arr);  // Call the printArray method to print the sorted array
    }
}
