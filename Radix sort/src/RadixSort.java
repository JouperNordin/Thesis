public class RadixSort {
	
	
	private static void radixMSD(int radix, int[] a, int[] b, int start, int end, int length, int mod) {
		
		int[] count = new int[length];
		//int[] index = new int[10];
		int[] temp = new int[a.length];
		
		for (int i = start; i < end; i++) {
        	int test = (int) (a[i]/ Math.pow(10, radix)) % mod; //radix
        	count[test]++;
        	b[test]++;
        }
        
        for (int k = 1; k < length; k++) {
        	count[k] += count[k-1];
        	b[k] += b[k-1];
        }
        
        for(int i = end-1; i >= start; i--) {
        	int test = (int) (a[i]/ Math.pow(10, radix)) % mod; //radix
        	temp[--count[test]] = a[i];
        }
        
        for(int i = start; i < end; i++) {
        	a[i] = temp[i];
        }
	}
	
	public static void radixLSD(int radix, int[] a, int[] b, int start, int end) {
		if(end <= (start+1)) {
			return;
		}
		
		int[] count = new int[10];
		
		for (int i = start; i < end; i++) {
        	int test = (int) (a[i]/ Math.pow(10, radix)) % 10; //radix
        	count[test]++;
        }
        
        for (int k = 1; k < 10; k++) {
        	count[k] += count[k-1];
        }
        
        for(int i = end-1; i >= start; i--) {
        	int test = (int) (a[i]/ Math.pow(10, radix)) % 10; //radix
        	b[--count[test]+start] = a[i];
        }
        
        for(int i = start; i < end; i++) {
        	a[i] = b[i];
        }
	}
	
	public static void sortParallel(int level, int[] a, int[] b, int[] c, int radix, int length) {
		if(level == 0) {
			for(int i = 0; i < radix; i++) {
	        	radixLSD(i, a, b, 0, a.length);
	        }
		} else if(level == 1) {
			Thread lThread = new Thread(new RadixSortSorter(a, b, c, radix, 0, c.length/2));
			Thread rThread = new Thread(new RadixSortSorter(a, b, c, radix, c.length/2, c.length));
			
			lThread.start();
			rThread.start();
			try {
				lThread.join();
				rThread.join();
			} catch (InterruptedException ie) {}
		} else {
			Thread lThread = new Thread(new RadixSortSorter(a, b, c, radix, 0, c.length/4));
			Thread rThread = new Thread(new RadixSortSorter(a, b, c, radix, c.length/4, c.length/2));
			Thread lThread2 = new Thread(new RadixSortSorter(a, b, c, radix, c.length/2, (c.length/2 + c.length/4)));
			Thread rThread2 = new Thread(new RadixSortSorter(a, b, c, radix, (c.length/2 + c.length/4), c.length));
			
			lThread.start();
			rThread.start();
			lThread2.start();
			rThread2.start();
			try {
				lThread.join();
				rThread.join();
				lThread2.join();
				rThread2.join();
			} catch (InterruptedException ie) {}
		}
		
	}
	
	
	public static void sort(int[] a, int[] b, int maxValue, int level, int length, int mod) {	//max length
		int[] temp = new int[length];
		
		if(level != 0) {
			if(mod == 100) {
				radixMSD(maxValue-2, a, temp, 0, a.length, length, mod);
			} else {
				radixMSD(maxValue-1, a, temp, 0, a.length, length, mod);
			}
		}
		
		sortParallel(level, a, b, temp, maxValue, length);
	}
}
