package com.mpe.common.util;
/**
 * @author Agung Hadiwaluyo
 *
 */
public class Sorting {
	
	public static long[] selectionSort(long[] arr) {
      int i, j, minIndex;
      long tmp;
      int n = arr.length;
      for (i = 0; i < n - 1; i++) {
           minIndex = i;
            for (j = i + 1; j < n; j++)
                  if (arr[j] < arr[minIndex])
                        minIndex = j;
            if (minIndex != i) {
                  tmp = arr[i];
                  arr[i] = arr[minIndex];
                  arr[minIndex] = tmp;
            }
      }
      return arr;
    }
	
	public static long[] insertionSort(long[] arr) {
      int i, j;
      long newValue;
      for (i = 1; i < arr.length; i++) {
            newValue = arr[i];
            j = i;
            while (j > 0 && arr[j - 1] > newValue) {
                  arr[j] = arr[j - 1];
                  j--;
            }
            arr[j] = newValue;
      }
      return arr;
    }

    public static long[] bubbleSort(long[] arr) {
      boolean swapped = true;
      int j = 0;
      long tmp;
      while (swapped) {
            swapped = false;
            j++;
            for (int i = 0; i < arr.length - j; i++) {
                  if (arr[i] > arr[i + 1]) {
                        tmp = arr[i];
                        arr[i] = arr[i + 1];
                        arr[i + 1] = tmp;
                        swapped = true;
                  }
             }
        }
      return arr;
     }
	   
    static int partition(long arr[], int left, int right) {
      int i = left, j = right;
      long tmp;
      long pivot = arr[(left + right) / 2];
      while (i <= j) {
            while (arr[i] < pivot)
                  i++;
            while (arr[j] > pivot)
                  j--;
            if (i <= j) {
                  tmp = arr[i];
                  arr[i] = arr[j];
                  arr[j] = tmp;
                  i++;
                  j--;
            }
      };
      return i;
    }
	    
	public static long[] quickSort(long arr[], int left, int right) {
      int index = partition(arr, left, right);
      if (left < index - 1)
            quickSort(arr, left, index - 1);
      if (index < right)
            quickSort(arr, index, right);
      return arr;
    }
	
	

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		long[] is = {11,5,7,0,12,-3};
		long[] is2 = selectionSort(is);
		
		for (int i=0; i<is2.length; i++) {
			System.out.println(""+is2[i]);
		}

	}

}
