
public class Bucket {
	
	private int[] array;
	private int counter;
	
	public Bucket(int size) {
		this.array = new int[size];
		this.counter = 0;
	}
	
	public void addToBucket(int value) {
		this.array[counter++] = value;
	}
	
	public int getCounter() {
		return this.counter;
	}
	
	public int[] getBucket() {
		return this.array;
	}
}
