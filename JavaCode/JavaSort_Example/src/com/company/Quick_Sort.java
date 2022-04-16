package com.company;

import java.util.Arrays;

public class Quick_Sort {

    public static void main(String[] args) {
        // Test data
        int[] data;
//        data = new int[]{5, 4, 8, -5, -7, -5, 1, 6, 4};
      data = new int[]{ 2, -1, 2, 5, 4, 1, -5, -8};

        // before data
        System.out.println(Arrays.toString(data));

        // Quick Sort O(n log n)
        // Is Unstable Sort (不穩定)
        // last value => true: low to height; false : height to low
        double startTime = System.currentTimeMillis();
        quickSort(data, 0, data.length-1, false);
        double endTime = System.currentTimeMillis();
        System.out.println("Spend Time: " + (endTime - startTime));

        // sorted data
        System.out.println(Arrays.toString(data));
    }

    static void quickSort(int[] arrData, int startIndex, int endIndex, boolean sortType){
        if(startIndex < endIndex){
            int partition = sort(arrData, startIndex, endIndex, sortType);
            quickSort(arrData, startIndex, partition-1, sortType);
            quickSort(arrData, partition+1, endIndex, sortType);
        }
    }
    static int sort(int[] arrData, int startIndex, int endIndex, boolean sortType){
        int temp = arrData[endIndex];
        int i,j;
        for(i = startIndex-1, j = startIndex; j < endIndex; j++){
            if(sortType ? arrData[j] < temp : arrData[j] > temp){
                i++;
                swap(arrData, j, i);
            }
        }
        i++;
        swap(arrData, i, j);
        return i;
    }
    static void swap(int[] arrData, int indexA, int indexB){
        int temp = arrData[indexA];
        arrData[indexA] = arrData[indexB];
        arrData[indexB] = temp;
    }
}
