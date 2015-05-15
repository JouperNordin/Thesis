
public class BucketSortSorter implements Runnable {
	private Bucket[] a;
	private int start;
	private int end;
	
	public BucketSortSorter(Bucket[] a, int start, int end) {
		this.a = a;
		this.start = start;
		this.end = end;
	}
	
	public void run() {
		for(int i = start; i <= end; i++) {
			if(a[i].getCounter() > 0) {
				insertionSort(a[i].getBucket(), a[i].getCounter());
			}
			
		}
	}
	
	private void insertionSort(int[] arr, int length) {
	      int i, j, newValue;
	      for (i = 1; i < length; i++) {
	            newValue = arr[i];
	            j = i;
	            while (j > 0 && arr[j - 1] > newValue) {
	                  arr[j] = arr[j - 1];
	                  j--;
	            }
	            arr[j] = newValue;
	      }
	}
}
