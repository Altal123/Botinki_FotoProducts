package sortirovka_zolotoe_se4enie;

public class Sort {
	
	//Реализована сортировка методом золотого сечения!!!
	public static int sortirovka(char[] arr, char ch) {
		
		int rez = -1;
		int Inach = 0;               //указатель на 1-й элемент массива
		int Ikonez = arr.length - 1; //указатель на последний элемент массива
		int t = arr.length/2;        //середина массива
		
		while (Ikonez - Inach > 1){
			if (ch > arr[t]) Inach = t;
			else Ikonez = t;
			t = (Inach + Ikonez)/2;
			}
		if (arr[Inach] == ch) rez = Inach;
		else if (arr[Ikonez] == ch) rez = Ikonez;
		return rez;
		}
		
			
	}


