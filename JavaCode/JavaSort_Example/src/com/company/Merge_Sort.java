package com.company;

import java.util.Arrays;

public class Merge_Sort {

    public static void main(String[] args) {
        // Test data
        int[] data;
        data = new int[]{5, 4, 8, -5, -7, -5, 1, 6, 4};
//      data = new int[]{ 2, -1, 2, 5, 4, 1, -5, -8};

        // output unsorted data
        System.out.println(Arrays.toString(data));

        // Merge sort O(n log n)
        // Is Unstable Sort (不穩定)
        // last value => true:low to height ; false:height to low
        divide(data, 0, data.length-1, false);

        // output sorted data
        System.out.println(Arrays.toString(data));
    }

    static void divide(int[] arrData, int leftIndex, int rightIndex, boolean sortType){
        if(leftIndex < rightIndex){
            int middleIndex = (leftIndex + rightIndex)/2;
            // left side
            divide(arrData, leftIndex, middleIndex, sortType);
            // right side
            divide(arrData, middleIndex + 1, rightIndex, sortType);
            // combine
            conquer(arrData, leftIndex, middleIndex, rightIndex, sortType);
        }
    }
    static void conquer(int[] arrData, int leftIndex, int middleIndex, int rightIndex, boolean sortType) {
        int[] leftSide = Arrays.copyOfRange(arrData, leftIndex, middleIndex + 2);
        int[] rightSide = Arrays.copyOfRange(arrData, middleIndex + 1, rightIndex + 2);
        // Let array last value is MAX or MIN Integer
        leftSide[leftSide.length-1] = (sortType ? Integer.MAX_VALUE : Integer.MIN_VALUE);
        rightSide[rightSide.length-1] = (sortType ? Integer.MAX_VALUE : Integer.MIN_VALUE);

        int leftPoint = 0;
        int rightPoint = 0;
        for (int i = leftIndex; i <= rightIndex; i++) {
            if ((leftSide[leftPoint] > rightSide[rightPoint]) == sortType) {
                arrData[i] = rightSide[rightPoint];
                rightPoint++;
            } else {
                arrData[i] = leftSide[leftPoint];
                leftPoint++;
            }
        }
    }
}
