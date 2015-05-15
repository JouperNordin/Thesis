public class Sorter implements Runnable {
	private boolean dir;
	private int level;
	private int min;
	private int max;
	
	public Sorter(int min, int max, boolean dir, int level) {
		this.dir = dir;
		this.level = level;
		this.min = min;
		this.max = max;
	}
	
	public void run() {
		BitonicSort.bitonicSort(min, max, dir, level);
	}
}
