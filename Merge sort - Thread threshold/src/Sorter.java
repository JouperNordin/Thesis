public class Sorter implements Runnable {
		private int[] a;
		private int level;
		
		public Sorter(int[] a, int level) {
			this.a = a;
			this.level = level;
		}
		
		public void run() {
			MergeSort.sort(a, level);
		}
	}