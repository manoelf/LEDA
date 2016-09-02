
public class Roleta {
	
	private int[] array;
	
	public Roleta(int[] array) {
		this.array = array;
	}
	
	public int giraRoleta(int n) {
		int result = array[0];
		
		for (int i = 0; i < n; i++) {
			result = array[i % array.length];
		}
		return result;
	}
	
	
	public static void main(String[] args) {
		int[] array = new int[] { 2, 5, 6, 12, 43, 23, 13, 7, 67, 3};
		
		Roleta roleta = new Roleta(array);
		
		assert roleta.giraRoleta(10) == 3;
		assert roleta.giraRoleta(15) == 43;
		assert roleta.giraRoleta(20) == 3;
		assert roleta.giraRoleta(11) == 2;
		assert roleta.giraRoleta(29) == 67;
		
	}
	
	
	
}
