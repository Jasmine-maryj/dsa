class MergeSort{

  int[] sortArr(int[] arr, int n) 
    { 
        return mergeSort(arr);
    }
  
    int[] mergeSort(int[] arr) {
      if(arr.length == 1){
        return arr;
      }

      int mid = arr.length / 2;

      int[] left = mergeSort(Arrays.copyOfRange(arr, 0, mid));
      int[] right = mergeSort(Arrays.copyOfRange(arr, mid, arr.length));

      return mergeSort(left, right);
    }
    
    int[] merge(int[] first, int[] second) {
      int[] merged = new int[first.length + second.length];

      int i = 0; 
      int j = 0;
      int k = 0;

      while(i < first.length && j < second.length){
        if(first[i] < second[j]){
          merged[k] = first[i];
          i++;
        }
        if(first[i] > second[j]){
          merged[k] = second[j];
          j++;
        }
        k++;
      }

      while(i < first.length){
        merged[k] = first[i];
          i++;
          k++;
      }
      while(j < second.length){
        merged[k] = second[j];
          j++;
          k++;
      }

      return merged;
    }
}
