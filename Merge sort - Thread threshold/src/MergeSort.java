public class MergeSort {
	public static void sort(int[] a, int level) {
		if (a.length < 2) { return; }
		if (level == 0) {
			mergeSort(a);
		} else {

			int[] left  = leftHalf(a);
			int[] right = rightHalf(a);
			
			Thread lThread = new Thread(new Sorter(left,  level-1));
			Thread rThread = new Thread(new Sorter(right, level-1));
			lThread.start();
			rThread.start();
			
			try {
				lThread.join();
				rThread.join();
			} catch (InterruptedException ie) {}
			
			merge(left, right, a);
		}
	}
	
	public static int[] leftHalf(int[] array) {
	    int size1 = array.length / 2;
	    int[] left = new int[size1];
	    for (int i = 0; i < size1; i++) {
	      left[i] = array[i];
	    }
	    return left;
	  }
	 
	public static int[] rightHalf(int[] array) {
	    int size1 = array.length / 2;
	    int size2 = array.length - size1;
	    int[] right = new int[size2];
	    for (int i = 0; i < size2; i++) {
	      right[i] = array[i + size1];
	    }
	    return right;
	  }
	
	// Arranges the elements of the given array into sorted order
	// using the "merge sort" algorithm, which splits the array in half,
	// recursively sorts the halves, then merges the sorted halves.
	// It is O(N log N) for all inputs.
	public static void mergeSort(int[] a) {
		if (a.length >= 2) {
			// split array in half
			int[] left  = leftHalf(a);
			int[] right = rightHalf(a);
			
			// sort the halves
			mergeSort(left);
			mergeSort(right);
			
			// merge them back together
			merge(left, right, a);
		}
	}
	
	// Combines the contents of sorted left/right arrays into output array a.
	// Assumes that left.length + right.length == a.length.
	public static void merge(int[] left, int[] right, int[] a) {
		int i1 = 0;
		int i2 = 0;
		for (int i = 0; i < a.length; i++) {
			if (i2 >= right.length || (i1 < left.length && left[i1] < right[i2])) {
				a[i] = left[i1];
				i1++;
			} else {
				a[i] = right[i2];
				i2++;
			}
		}
	}

	// Swaps the values at the two given indexes in the given array.
	public static final void swap(int[] a, int i, int j) {
		if (i != j) {
			int temp = a[i];
			a[i] = a[j];
			a[j] = temp;
		}
	}
	
	// Returns true if the given array is in sorted ascending order.
	public static boolean isSorted(int[] a) {
		for (int i = 0; i < a.length - 1; i++) {
			if (a[i] > a[i + 1]) {
				return false;
			}
		}
		return true;
	}
}