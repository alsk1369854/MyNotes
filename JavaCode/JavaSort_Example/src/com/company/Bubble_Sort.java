package com.company;

import java.util.Arrays;

public class Bubble_Sort {

    public static void main(String[] args){
        // Test data
        int[] data;
        data = new int[]{5, 4, 8, -5, -7, -5, 1, 6, 4};
//      data = new int[]{ 2, -1, 2, 5, 4, 1, -5, -8};

        // before data
        System.out.println(Arrays.toString(data));

        // Bubble Sort O(n^2)
        // Is Stable Sort(穩定的)
        // last value => true: low to height; false: height to low
        double startTime = System.currentTimeMillis();
        bubbleSort(data, false);
        double endTime = System.currentTimeMillis();
        System.out.println("Spend Time: " + (endTime - startTime));

        // sorted data
        System.out.println(Arrays.toString(data));

    }

    static void bubbleSort(int[] arrdata, boolean sortType){
        for(int i = 0; i < arrdata.length; i++){
            for(int j=0; j< arrdata.length - i - 1; j++){
                if(sortType ? (arrdata[j] > arrdata[j+1]) : (arrdata[j] < arrdata[j+1])){
                    int temp = arrdata[j];
                    arrdata[j] = arrdata[j+1];
                    arrdata[j+1] = temp;
                }
            }
        }
    }
}

