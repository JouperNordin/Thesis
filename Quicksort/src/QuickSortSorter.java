
public class QuickSortSorter implements Runnable {
	private int[] a;
	private int level;
	private int min;
	private int max;
	
	public QuickSortSorter(int[] a, int min, int max, int level) {
		this.a = a;
		this.level = level;
		this.min = min;
		this.max = max;
	}
	
	public void run() {
		QuickSort.sort(a, min, max, level);
	}
}
