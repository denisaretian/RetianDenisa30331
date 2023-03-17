package ex2;

public class Ex2 {

	public static void main(String[] args) {
		int[][] m1 = { {2, 3, 1} , { 7, 1, 6 }, { 9, 2, 4 }};
		int[][] m2 = { {8, 5, 3} , { 3, 9, 2 }, { 2, 7, 3 }};
		
		int row = m1.length;
		int col = m1[0].length;
		
		int[][] sum = new int [row][col];
		
		System.out.println("SUM:");
		
		for (int i = 0; i < row; i++) {
			for(int j = 0; j < col; j++) {
				sum[i][j] = m1[i][j] + m2[i][j];
				System.out.print(sum[i][j]+ " ");
			}
			System.out.println();
		}
		
		int[][] prod = new int [row][col];
		
		System.out.println("PROD:");
		
		for(int i = 0; i < row; i++) {
			for(int j = 0; j < col; j++) {
				for(int k = 0; k < row; k++) {
					prod[i][j] = prod[i][j] + m1[i][k]*m2[k][j];		
				}
				System.out.print(prod[i][j]+ " ");
			}
			System.out.println();
		}
		
		
	}

}
