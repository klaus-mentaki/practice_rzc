package com.klausTest;

public class ArraySort {
    public static void main(String[] args) {
        int[] arr = new int[]{-45, 0, 3, 12, -23,};
        int j = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[j] > arr[j + 1]) {
               /* int temp = 0;
                temp = arr[j];
                arr[j] = arr[j + 1];
                arr[j+1]=temp;*/
                int temp;
                temp = arr[j-1];
                arr[j-1] = arr[j];
                arr[j]=temp;
            }
        }
        for (int a : arr
        ) {
            System.out.print(a + " ");
        }
    }
}
