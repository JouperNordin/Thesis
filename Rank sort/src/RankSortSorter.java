
public class RankSortSorter implements Runnable {
	private int[] a;
	private int[] b;
	private int step;
	private int start;
	
	public RankSortSorter(int[] a, int step, int start, int[] b) {
		this.a = a;
		this.step = step;
		this.start = start;
		this.b = b;
	}
	
	public void run() {
		RankSort.rankSort(a, step, start, b);
	}
}