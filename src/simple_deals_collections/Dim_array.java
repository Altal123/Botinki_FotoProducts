package simple_deals_collections;

import java.util.Scanner;

//Сортировка двумерного массива в виде песочных часов.

public class Dim_array {

	private int[][] arr;
	private int size;
	
	public static void main(String[] args) {
          new Dim_array().doActions();		
	}
		
	private void doActions(){
		
		do{
		  size = Integer.valueOf(keyboard("Input matrix size: "));
		
		  arr = new int [size][size];
		  
		  matix(arr);		  
		
		  String confirm = keyboard("Try again? y/n");
		  
		  if (confirm.equalsIgnoreCase("n")) break;
		
		}
		while(true);

	}

	private void matix(int[][] arr) {
		for (int row = 0; row < size; row++ ){
			
			for (int column = row; column < size - row; column++){
				arr[row][column] = 1;
				arr[size - row - 1][column] = 1;
			}
			
			
			for (int column = 0; column < size; column++ ){
				
				System.out.print(arr[row][column]); //вывод готового массива на экран				

			}
			System.out.println();
			
		}
	}
	
private String keyboard(String message) { 
		
		 System.out.println(message); 
	     Scanner scan = new Scanner(System.in); 
	     String rez = scan.next(); 
	     scan.reset(); 
	     return rez; 
	}

}
