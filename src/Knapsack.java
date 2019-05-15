import java.io.File;
import java.io.FileNotFoundException;
import java.io.Reader;
import java.io.StringReader;
import java.io.Writer;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Knapsack {
	public static void knapsack(int knapsack[][], int items[][]) {
		int row = knapsack.length;
		int column = knapsack[0].length;
		int knapsackCapacity = column - 1;
		for(int i = 0 ; i < row ; i++) {
			for(int j = 0; j < column ;j ++) {
				knapsack[i][j] = F(items , knapsack , i, j);
			}
		}
	}
	public static int F(int[][] items, int[][] knapsack, int i, int knapsackCapacity) {
		if(i == 0 || knapsackCapacity == 0) {
			return 0;
		}
		if(knapsackCapacity - items[i][1] < 0) {
			return F(items, knapsack, i - 1, knapsackCapacity);
		}
		else {
			return Math.max(F(items, knapsack, i - 1, knapsackCapacity), items[i][0] + F(items, knapsack, i - 1, knapsackCapacity - items[i][1]));
		}
	}
	public static int[] findSubset(int[][] knapsack , int[][] items) {
		int numberOfitems = knapsack.length - 1;
		int knapsackCapacity = knapsack[0].length - 1;
		int[] subset = new int[numberOfitems + 1];
		for(int i = numberOfitems; i >= 1 ; i--) {
			if(knapsack[i][knapsackCapacity] > knapsack[i - 1][knapsackCapacity]) {
				subset[i] = 1;
				knapsackCapacity = knapsackCapacity - items[i][1];
			}
		}
		return subset;
	}
	public static void main(String[] args) throws IOException {
		
		int numberOfItems = 0;
		int knapsackCapacity = 0;
		File file = new File("data/sample1a.txt");
		Scanner scan = new Scanner(file);
		while(scan.hasNext()) {
			numberOfItems = scan.nextInt();
			knapsackCapacity = scan.nextInt();
			break;
		}
		//first column stores value of item 
		//second column stores weight of item
		int items[][] = new int[numberOfItems + 1][2];
		int i = 1;
		while(scan.hasNext()) {
			items[i][0] = scan.nextInt();
			items[i][1] = scan.nextInt();
			i++;
		}
		scan.close();
		int knapsack[][] = new int[numberOfItems + 1][knapsackCapacity + 1];
		/*for(int a = 0; a < numberOfItems + 1 ; a++) {
			knapsack[a][0] = 0;
		}
		for(int a = 0; a <knapsackCapacity + 1 ; a++) {
			knapsack[0][a] = 0;
		}*/
		knapsack(knapsack, items);
		System.out.println(knapsack[4][20]);
		int[] subset = new int[numberOfItems + 1];
		subset = findSubset(knapsack, items);
		for(int i1 = 1;i1<subset.length;i1++) {
			System.out.println(subset[i1]);
		}
	}

}