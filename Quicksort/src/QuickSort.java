public class QuickSort {

	public static boolean isSorted(int[] a) {
		for (int i = 0; i < a.length - 1; i++) {
			if (a[i] > a[i + 1]) {
				return false;
			}
		}
		return true;
	}
	
	public static void sort(int[] a, int level) {
		//level = 0: 1 threads
		//level = 1: 2 threads
		//level = 2: 4 threads
		sort(a, 0, a.length - 1, level);
	}
		
	public static void sort(int[] a, int min, int max, int level) {
		if (min >= max) { // base case; no need to sort
			return;
		}
		if (level == 0) {
			quickSort(a, min, max);
		} else {
		
		//int pivot = a[min];
		//swap(a, min, max); // move pivot to end
		
		int pivot = -1;
		int pivotIndex = -1;
		if(a[min] < a[max]) {
			if(a[min] < a[max/2]) {
				if(a[max] < a[max/2]) {
					pivot = a[max];
					pivotIndex = max;
				} else {
					pivot = a[max/2];
					pivotIndex = max/2;
				}
			} else {
				pivot = a[min];
				pivotIndex = min;
			}
		} else {
			if(a[min] < a[max/2]) {
				pivot = a[min];
				pivotIndex = min;
			} else {
				if(a[max] < a[max/2]) {
					pivot = a[max/2];
					pivotIndex = max/2;
				} else {
					pivot = a[max];
					pivotIndex = max;
				}
			}
		}
			
		swap(a, pivotIndex, max); // move pivot to end
			
			
			// partition the two sides of the array
			int middle = partition(a, min, max - 1, pivot);
			swap(a, middle, max); // restore pivot to proper location
			// recursively sort the left and right partitions
			
			Thread lThread = new Thread(new QuickSortSorter(a, min, middle-1, level-1));
			Thread rThread = new Thread(new QuickSortSorter(a, middle+1, max, level-1));
			lThread.start();
			rThread.start();
			
			try {
				lThread.join();
				rThread.join();
			} catch (InterruptedException ie) {}
		}
	}
	
	private static void quickSort(int[] a, int min, int max) {
		if (min >= max) { // base case; no need to sort
		return;
		}

		//int pivot = a[min];
		//swap(a, min, max); // move pivot to end
		
		int pivot = -1;
		int pivotIndex = -1;
		if(a[min] < a[max]) {
			if(a[min] < a[max/2]) {
				if(a[max] < a[max/2]) {
					pivot = a[max];
					pivotIndex = max;
				} else {
					pivot = a[max/2];
					pivotIndex = max/2;
				}
			} else {
				pivot = a[min];
				pivotIndex = min;
			}
		} else {
			if(a[min] < a[max/2]) {
				pivot = a[min];
				pivotIndex = min;
			} else {
				if(a[max] < a[max/2]) {
					pivot = a[max/2];
					pivotIndex = max/2;
				} else {
					pivot = a[max];
					pivotIndex = max;
				}
			}
		}
			
		swap(a, pivotIndex, max); // move pivot to end
		
		// partition the two sides of the array
		int middle = partition(a, min, max - 1, pivot);
		swap(a, middle, max); // restore pivot to proper location
		// recursively sort the left and right partitions
		quickSort(a, min, middle - 1);
		quickSort(a, middle + 1, max);
	}
	
	public static final void swap(int[] a, int i, int j) {
		if (i != j) {
			int temp = a[i];
			a[i] = a[j];
			a[j] = temp;
		}
	}
	
	private static int partition(int[] a, int i, int j, int pivot) {
		while (i <= j) {
			// move index markers i,j toward center
			// until we find a pair of out-of-order elements
			while (i <= j && a[i] < pivot) { i++; }
			while (i <= j && a[j] > pivot) { j--; }
			if (i <= j) {
				swap(a, i, j);
				i++;
				j--;
			}
		}
		return i;
	}
}
