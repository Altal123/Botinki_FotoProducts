package sortirovka_zolotoe_se4enie;

public class Sort {
	
	//����������� ���������� ������� �������� �������!!!
	public static int sortirovka(char[] arr, char ch) {
		
		int rez = -1;
		int Inach = 0;               //��������� �� 1-� ������� �������
		int Ikonez = arr.length - 1; //��������� �� ��������� ������� �������
		int t = arr.length/2;        //�������� �������
		
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


