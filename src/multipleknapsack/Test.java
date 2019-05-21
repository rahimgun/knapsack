package multipleknapsack;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Scanner;

public class Test {

	public static void main(String[] args) throws FileNotFoundException {
		
	
		int numberOfItems = 0;
		int knapsackCapacity = 0;
		int knapsackNumber = 0 ;
		File file = new File("data/sample2a.txt");
		Scanner scan = new Scanner(file);
		while(scan.hasNext()) {
			numberOfItems = scan.nextInt();
			knapsackNumber = scan.nextInt();
			break;
		}
		
		int knapsacksBag [] = new int[knapsackNumber];
		
		
		while(scan.hasNext()) {
			
			for (int i = 0; i < knapsacksBag.length; i++) {
				
				knapsacksBag[i] = scan.nextInt();
			}
			
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
		
	
//		for (int j = 0; j < items.length; j++) {
//			System.out.println(items[j][0]);
//		}
		
		scan.close();
		
		
        MultipleKnapsack knapsacks = new MultipleKnapsack();
        int index = 1;
        String knapid = " " + index ;
        for (int j = 0; j < knapsackNumber; j++) {
        	
        	knapsacks.addKnapsack(new Knapsack(knapsacksBag[j], knapid));
        	index++;
        	knapid = " " + index ;
        	
		}
    

        LinkedList<KnapsackItem> itemsx = new LinkedList<>();
        
        
        int indexItem = 1;
        String itemid = " " + indexItem ;
        for (int j = 1; j < items.length; j++) {
			
        	 itemsx.add(new KnapsackItem(items[j][0], items[j][1], itemid));
        	 indexItem++;itemid = " " + indexItem ;
        	 
        	
		}
        
        
 

        knapsacks.greedyMultipleKnapsack(itemsx);
        knapsacks.calculateValue();
        MultipleKnapsack result = knapsacks.neighborSearch(knapsacks);
        result.printResult();
		
	
		
	
		
		
	}

}
