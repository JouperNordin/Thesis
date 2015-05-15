
public class RankSort {
	
	public static void sort(int[] a, int level, int[] b) {
		//level = 0: 1 threads
		//level = 1: 2 threads
		//level = 2: 4 threads
		//int level = 1;
	
		if(level == 0) {
			rankSort(a, 1, 0, b);
		}
		if(level == 1) {
			parallelSort(a, b, 2);
		} else if(level == 2) {
			parallelSort(a, b, 4);
		}
	}
	
	private static void parallelSort(int[] a, int[] b, int nrOfThreads) {
		if(nrOfThreads == 2) {
			Thread lThread = new Thread(new RankSortSorter(a, 2, 0, b));
			Thread rThread = new Thread(new RankSortSorter(a, 2, 1, b));
			
			lThread.start();
			rThread.start();
			try {
				lThread.join();
				rThread.join();
			} catch (InterruptedException ie) {}
		} else if(nrOfThreads == 4) {
			Thread lThread = new Thread(new RankSortSorter(a, 4, 0, b));
			Thread rThread = new Thread(new RankSortSorter(a, 4, 1, b));
			Thread l2Thread = new Thread(new RankSortSorter(a, 4, 2, b));
			Thread r2Thread = new Thread(new RankSortSorter(a, 4, 3, b));

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
	}
	
	
	public static void rankSort(int a[], int step, int start, int[] b) {
		
		for(int i = start; i < a.length; i+=step) {
			int index = 0;
			for(int j = 0; j < a.length; j++) {
				if(a[i] > a[j] || (a[i] == a[j] && (j < i))) {
					index++;
				}
			}	
			b[index] = a[i];
		}
	}
	

}
