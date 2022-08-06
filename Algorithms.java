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
    

    public static void main (String[] args) {
       int array[] = new int[2000]; 
        Random r = new Random(); 

        for (int i = 0; i < array.length; i++) {
            array[i] = r.nextInt(2000);
        }
        
        int k = 2000; 

       
        long start = System.nanoTime(); 

        //Algorithms alg= new Algorithms(); 
        Algorithms.quickSortIterative(array, 0, array.length - 1); 
        System.out.println(array[k-1]); 

        long end = System.nanoTime(); 
        long total = end - start;
        System.out.println("The run time is: "); 
        System.out.println(total); 
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

