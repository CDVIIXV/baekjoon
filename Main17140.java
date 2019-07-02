package baekjoon;

import java.util.Arrays;
import java.util.Scanner;

public class Main17140 {
	
	static final int MAX_TIME = 100, MAX_SIZE = 100;
	
	static int setRow, setColumn, setValue;
	static int rowCount, columnCount;
	
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		setRow = input.nextInt() - 1;
		setColumn = input.nextInt() - 1;
		setValue = input.nextInt();
		rowCount = columnCount = 3;
		int[][] matrix = new int[MAX_SIZE][MAX_SIZE];
		for(int rowIndex = 0; rowIndex < rowCount; ++rowIndex)
			for(int columnIndex = 0; columnIndex < columnCount; ++columnIndex)
				matrix[rowIndex][columnIndex] = input.nextInt();
		input.close();
		boolean find = false;
		for(int t=0; t<=MAX_TIME && !find; ++t) {
			if(matrix[setRow][setColumn] == setValue) {
				find = true;
				System.out.println(t);
			}
			else if(rowCount >= columnCount)
				matrix = calculate(matrix, true);
			else
				matrix = calculate(matrix, false);
		}
		if(!find)
			System.out.println(-1);
	}
	
	static int[][] calculate(int[][] matrix, boolean isRow) {
		int[][] newMatrix = new int[MAX_SIZE][MAX_SIZE];
		int lineCount = isRow ? rowCount : columnCount;
		int indexCount = isRow ? columnCount : rowCount;
		int maxLineCount = 0;
		for(int line = 0; line < lineCount; ++line) {
			int[] countBox = new int[MAX_SIZE+1];
			for(int index = 0; index < indexCount; ++index) {
				if(isRow && matrix[line][index] > 0)
					++countBox[matrix[line][index]];
				else if(!isRow && matrix[index][line] > 0)
					++countBox[matrix[index][line]];
			}
			int[] result = changedLookAndSaySquence(countBox);
			for(int index = 0; index < result.length; ++index) {
				if(isRow)
					newMatrix[line][index] = result[index];
				else
					newMatrix[index][line] = result[index];
			}
			if(result.length > maxLineCount)
				maxLineCount = result.length;
		}
		if(isRow)
			columnCount = maxLineCount;
		else
			rowCount = maxLineCount;
		return newMatrix;
	}
	
	static int[] changedLookAndSaySquence(int[] countBox) {
		int validCount = 0;
		int[] index = new int[countBox.length]; 
		int[][] sort = new int[countBox.length][countBox.length];
		for(int i = 1; i < countBox.length; ++i)
			if(countBox[i] > 0) {
				sort[countBox[i]][index[countBox[i]]++] = i;
				++validCount;
			}
		for(int i=1; i<countBox.length; ++i)
			Arrays.sort(sort[i], 0, index[i]);
		int resultIndex = 0;
		int[] result = new int[validCount*2];
		for(int i = 1; i < countBox.length && validCount > 0; ++i) {
			for(int j = 0; j < index[i]; ++j) {
				result[resultIndex++] = sort[i][j];
				result[resultIndex++] = i;
				--validCount;
			}
		}
		return result;
	}
}