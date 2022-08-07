import java.util.*;

public class Algorithms
{
   
    public void algorithmOne(int mergeSortArray[], int x, int y, int z)
    {

        int q = 0; 
        int w = 0;
        int k = x;

        int subArray1 = (y - x) + 1;
        int subArray2 = (z - y);
 
     
        int tempArray1 [] = new int[subArray1];
        int tempArray2 [] = new int[subArray2];
 
        for (int i = 0; i < subArray1; ++i)
            tempArray1[i] = mergeSortArray[x + i];
        for (int j = 0; j < subArray2; ++j)
            tempArray2 [j] = mergeSortArray[y + 1 + j];
 
    
        while (q < subArray1 && w < subArray2) {
            if (tempArray1[q] <= tempArray2 [w]) {
                mergeSortArray[k] = tempArray1 [q];
                q++;
            }
            else {
                mergeSortArray[k] = tempArray2 [w];
                w++;
            }
            k++;
        }
 
        while (q < subArray1) {
            mergeSortArray[k] = tempArray1[q];
            q++;
            k++;
        }
 
       
        while (w < subArray2) {
            mergeSortArray[k] = tempArray2 [w];
            w++;
            k++;
        }
    }
 

    public void mergeSort(int array[], int x, int r)
    {
        if (x < r) {
          
            int m = x + ((r- x)/2);
 
            mergeSort(array, x, m);
            mergeSort(array, m + 1, r);
 
            algorithmOne(array, x, m, r);
        }
    }
    

    public static int quickSortIterative (int mergeArray[], int small, int large) {
        int i = (small - 1);
        int tempArray;
        int pivotPosition = mergeArray[large];
        for (int j = small; j <= large - 1; j++) {
            if (mergeArray[j] <= pivotPosition) {
                i++;


                tempArray = mergeArray[i]; 
                mergeArray[i] = mergeArray[j];
                mergeArray[j] = tempArray; 
            }
        }

        tempArray = mergeArray[i + 1]; 
        mergeArray[i + 1] = mergeArray[large]; 
        mergeArray[large] = tempArray;

        return (i + 1); 

    }

    


    public static int quickSortRecursive (int[] quickSortArray, int small, int large) {
        int pivotPosition = quickSortArray[large];
        int pivot1 = small; 
        for (int i = small; i <= large; i++) {
            if (quickSortArray[i] < pivotPosition) {
                int tempArray = quickSortArray[i]; 
                quickSortArray[i] = quickSortArray[pivot1];
                quickSortArray[pivot1] = tempArray;
                pivot1++; 
            }
        }

        int tempArray = quickSortArray[large]; 
        quickSortArray[large] = quickSortArray[pivot1];
        quickSortArray[pivot1] = tempArray; 

        return pivot1; 
    }



    public static int FindMedian(int[] arr, int left, int right){
        Arrays.sort(arr, left, right);

        return arr[left + (right - left)/2];

    }

    public static int Partition(int[] arr, int left, int right, int value){
        int count;

        for (count = left; count < right; count++){
            if (arr[count] == value){
                int temp = arr[count];
                arr[count] = arr[right];
                arr[right] = temp;

            }
        }

        count = left;
        for (int i = left; i < right; i++) {
            if (arr[i] <= value) {
                int temp = arr[count];
                arr[count] = arr[i];
                arr[i] = temp;

                count++;
            }
        }
        int temp = arr[count];
        arr[count] = arr[right];
        arr[right] = temp;

        return count;

    }


    public static  int mmRulePartition (int[] arr, int left, int right, int k){
        if (k > 0 && k <= right - left + 1){
            int numOfElements = right - left + 1;
            int numOfGroups = (numOfElements + 6) / 7;
            int[] median = new int[numOfGroups];
            int count;

            for(count = 0; count < numOfElements / 7; count++){
                median[count]= FindMedian(arr,left + count * 7,left + count * 7 + 7);

            }

            if (count * 7 < numOfElements){
                median[count]= FindMedian(arr, left + count * 7, left + count * 7 + numOfElements % 7);
                count++;

            }

            int mOfm;
            if (count == 1){
                mOfm = median[count - 1];
            }else {
                mOfm = mmRulePartition(median, 0, count - 1, count / 2);
            }

            int location = Partition(arr, left, right, mOfm);

            if (location - left == k - 1){
                return arr[location];

            }else if (location - left > k - 1){
                return mmRulePartition(arr, left, location - 1, k);

            }
            else {
                return mmRulePartition(arr, location + 1, right, k - location + left - 1);

            }

        }else {
            int v = -1;
            return v;
        }


    }


    public static int[] fillArray(int[] arr, int n){
        Random r = new Random();
        for (int i = 0; i < arr.length; i++) {
            arr[i] = r.nextInt(2000);
        }
        return arr;
    }

    public static void main (String[] args) {
        Algorithms alg= new Algorithms();
        int n = 2000;
        long sum = 0;

        int array[] = new int[n];
        fillArray(array,n);

        int k = 1;


        for (int i = 0; i < 15; i++){
            long start = System.nanoTime();
            int result = mmRulePartition(array, 0, array.length - 1, k);
            long end = System.nanoTime();
            long total = end - start;
            sum += total;
//            System.out.println("The result is: " + result);
            System.out.print("The "+ i+1 +  " run time is: ");
            System.out.println(total);
        }
        System.out.println();
        System.out.println("The average time is: " + sum/15 + " nanoseconds");


//        Algorithms alg= new Algorithms();
//        quickSortIterative(array, 0, array.length - 1);
//        System.out.println(array[k-1]);


}
}



 
    
    
    

    /*public static void main (String[] args) {

        int array[] = new int[2000]; 
        Random r = new Random(); 

        for (int i = 0; i < array.length; i++) {
            array[i] = r.nextInt(2000);
        }
        int k = 2000; 

        long start = System.nanoTime(); 

        Algorithms alg= new Algorithms(); 
        alg.mergeSort(array, 0, array.length - 1); 
        System.out.println(array[k-1]); 

        long end = System.nanoTime(); 
        long total = end - start;
        System.out.println("The run time is: "); 
        System.out.println(total); 
    


       
    }
}
*/

