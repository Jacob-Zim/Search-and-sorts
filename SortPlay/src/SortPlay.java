
public class SortPlay {

	public static void main(String[] args) {
		int n = 10;
		double[] x = new double[n];
		for (int i=0; i<n; i++) x[i] = 100*Math.random();
		p("Before sorting",x);
		//bubbleSort(x);
		selectionSort(x);
		p("After sorting",x);
	}
	public static void selectionSort(double[] x) {
		for (int i=0; i<x.length-1; i++) {
			int locSmallest = i;
			for (int j=i+1;j<x.length;j++) {
				if (x[j] < x[locSmallest]) {
					locSmallest = j;
				}
			}
			swap(x,i, locSmallest);
			p("after a swap in selectionSort", x);
		}
	}
	public static void bubbleSort(double[] x) {
		boolean anySwaps = true;
		while (anySwaps) {
			anySwaps = false;
			for (int first=0; first<= x.length-2; first++) {
				int second = first +1;
				double v1 = x[first];
				double v2 = x[second];
				if (v1 > v2) {
					anySwaps = true;
					swap(x, first, second);
					p("after swap", x);
				}
			}
			p("after a sweep",x);
		}
	}
	public static void swap(double[] x, int first, int second) {
		double temp = x[first];
		x[first] = x[second];
		x[second] = temp;
	}
	public static void p(String s,double [] x) {
		System.out.println(s);
		for (int i=0; i<x.length; i++) {
			System.out.println(i + ": " + x[i]);
		}
		System.out.println();
	}
	
}
