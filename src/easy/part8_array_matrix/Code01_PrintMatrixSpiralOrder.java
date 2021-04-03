package easy.part8_array_matrix;

import java.math.BigDecimal;

/**
 * 螺旋打印矩阵
 * @author liq
 *
 */
public class Code01_PrintMatrixSpiralOrder {

	public static void SpiralOrderPrint(int[][] matrix) {
		//左上角初始点
		int tRow = 0;
		int tCol = 0;
		//右下角初始点
		int dRow = matrix.length - 1;
		int dCol = matrix[0].length -1;
		
		while(tRow <= dRow && tCol <= dCol) { 
			printRound(matrix, tRow++, tCol++, dRow--, dCol--);	//逐层打印
		}
		
	}

	private static void printRound(int[][] matrix, int tRow, int tCol, int dRow, int dCol) {
		//判断边界条件：若是一行或者一列
		//矩阵是一行数据
		if (tRow == dRow) {
			for(int i = 0; i <= dCol; i++) {
				System.out.print(matrix[tRow][i] +" ");
			}
		}else if(tCol == dCol) {
			//矩阵是一列数据
			for(int i = 0; i <= dRow; i++) {
				System.out.print(matrix[i][dCol]+" ");
			}
		}else {
			int curRow = tRow;
			int curCol = tCol;
			//打印四个边
			while(curCol < dCol) {
				System.out.print(matrix[curRow][curCol]+" ");
				curCol ++;
			}
			while(curRow < dRow) {
				System.out.print(matrix[curRow][curCol]+" ");
				curRow ++;
			}
			while(curCol > tCol) {
				System.out.print(matrix[curRow][curCol]+" ");
				curCol --;
			}
			while(curRow > tRow) {
				System.out.print(matrix[curRow][curCol]+" ");
				curRow--;
			}
		}
	}

	public static void printTest(int[][] matrix) {
		int tRow = 0;
		int tCol = 0;
		int dRow = matrix.length - 1;
		int dCol = matrix.length - 1;
		while (tRow <= dRow && tCol <= dCol) {
			printR(matrix, tRow++, dRow--, tCol++, dCol--);
		}
	}

	private static void printR(int[][] matrix, int tRow, int dRow, int tCol, int dCol) {
		if (tRow == dRow) {
			for (int i = tCol; i <= dCol; i++) {
				System.out.println(matrix[tRow][i]);
			}
		} else if (tCol == dCol) {
			for (int i = tRow; i <= dRow; i++) {
				System.out.println(matrix[i][tCol]);
			}
		} else {
			int curRow = tRow;
			int curCol = tCol;
			while (curCol < dCol) {
				System.out.print(matrix[tRow][curCol ++] + " ");
			}
			while (curRow < dRow) {
				System.out.print(matrix[curRow ++][dCol] + " ");
			}
			while (curCol > tCol) {
				System.out.print(matrix[dRow][curCol --] + " ");
			}
			while (curRow > tRow) {
				System.out.print(matrix[curRow --][tCol] + " ");
			}
		}
	}


	public static void main(String[] args) {
//		int[][] matrix = { { 1, 2, 3, 4 }, { 5, 6, 7, 8 }, { 9, 10, 11, 12 },
//				{ 13, 14, 15, 16 } };
//		SpiralOrderPrint(matrix);
//		System.out.println();
//		printTest(matrix);

		BigDecimal bigDecimal = new BigDecimal("0.22400000000");
		System.out.println(bigDecimal.toString());
	}
}
