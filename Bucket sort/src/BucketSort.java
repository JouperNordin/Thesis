public class BucketSort {
	private static int NR_OF_BUCKETS = -1;
	private static int LENGTH = -1; 
	private static Bucket[] buckets;
	
	public BucketSort(int arrayLength) {
		
		if(arrayLength == 16) {
			LENGTH = arrayLength;
			NR_OF_BUCKETS = LENGTH/4;
			buckets = new Bucket[NR_OF_BUCKETS];

			for(int i = 0; i < buckets.length; i++) {
				buckets[i] = new Bucket(16);
			}
		} else  {
			LENGTH = arrayLength;
			NR_OF_BUCKETS = LENGTH/128;
			buckets = new Bucket[NR_OF_BUCKETS];

			for(int i = 0; i < buckets.length; i++) {
				buckets[i] = new Bucket(256);
			}
		}
	}
	
	public void sort(int[] a, int level) {
		//level = 0: 1 threads
		//level = 1: 2 threads
		//level = 2: 4 threads
		bucketSortParallel(a, level);
	}
	
	public void bucketSortParallel(int[] a, int level) {
		if (level == 0) {
			bucketSort(a);
		} else {
			int min = a[0];
			int max = a[a.length-1];
			for (int key : a) {
				if (key < min) {
					min = key;
				} else {
					if(key > max) {
						max = key;
					}
				}
			}

			for (int key : a) {
				int temp = key / (LENGTH/NR_OF_BUCKETS);
				buckets[temp].addToBucket(key);
			}
			
			if(level == 1) {
				Thread lThread = new Thread(new BucketSortSorter(buckets, 0, (buckets.length/2)-1));
				Thread rThread = new Thread(new BucketSortSorter(buckets, buckets.length/2, buckets.length-1));
				lThread.start();
				rThread.start();
				try {
					lThread.join();
					rThread.join();
				} catch (InterruptedException ie) {}
			} else {
				Thread lThread = new Thread(new BucketSortSorter(buckets, 0, (buckets.length/4)-1));
				Thread rThread = new Thread(new BucketSortSorter(buckets, buckets.length/4, (buckets.length/2)-1));
				Thread l2Thread = new Thread(new BucketSortSorter(buckets, buckets.length/2, ((buckets.length-1)-(buckets.length/4)-1)));
				Thread r2Thread = new Thread(new BucketSortSorter(buckets, (buckets.length-1)-(buckets.length/4), buckets.length-1));
				lThread.start();
				rThread.start();
				l2Thread.start();
				r2Thread.start();
				
				try {
					lThread.join();
					rThread.join();
					l2Thread.join();
					r2Thread.join();
				} catch (InterruptedException ie) {}
			}
			
			int index = 0;
			
			for(int i = 0; i < buckets.length; i++) {
				for(int j = 0; j < buckets[i].getCounter(); j++) {
					a[index++] = buckets[i].getBucket()[j];
				}
			}
		}
	}
	
	public void bucketSort(int[] a) {
		int min = a[0];
		int max = a[a.length-1];
		for (int key : a) {
			if (key < min) {
				min = key;
			} else {
				if(key > max) {
					max = key;
				}
			}
		}
		
		for (int key : a) {
			int temp = key / (LENGTH/NR_OF_BUCKETS);
			buckets[temp].addToBucket(key);
		}
		
		for(int i = 0; i < buckets.length; i++) {
			insertionSort(buckets[i].getBucket(), buckets[i].getCounter());
		}
		
		int index = 0;
		
		for(int i = 0; i < buckets.length; i++) {
			for(int j = 0; j < buckets[i].getCounter(); j++) {
				a[index++] = buckets[i].getBucket()[j];
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
	
	public boolean isSorted(int[] a) {
		for (int i = 0; i < a.length - 1; i++) {
			if (a[i] > a[i + 1]) {
				return false;
			}
		}
		return true;
	}

}
