
public class RadixSortSorter implements Runnable {
	private int[] a;
	private int[] b;
	private int[] c;
	private int radix;
	private int start;
	private int end;
	
	public RadixSortSorter(int[] a, int[] b, int[] c, int radix, int start, int end) {
		this.a = a;
		this.b = b;
		this.c = c;
		this.radix = radix;
		this.start = start;
		this.end = end;
	}
	
	public void run() {
		for(int i = 0; i < radix-1; i++) {
			for(int j = start; j < end; j++) {
				if(j == 0) {
					RadixSort.radixLSD(i, a, b, 0, c[j]);
				} else {
					RadixSort.radixLSD(i, a, b, c[j-1], c[j]);
				}
			}
		}
	}
}