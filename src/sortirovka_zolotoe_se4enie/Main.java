/**
 * 
 */
package sortirovka_zolotoe_se4enie;

/**
 * @author a.ganushevich
 *
 */
public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		char[] arr = {'a', 'c', 'f', 'm', 'n', 'p', 'r', 't', 'u'};
		char ch = 'c';
				
		//Sort sort = new Sort();
		int rez = Sort.sortirovka(arr, ch);
		
		if (rez >= 0) System.out.println("����� ������� ������� � �������: " + rez);
		else System.out.println("������ ������� ��� � ���. �������!");
		
		//�������� ������ ����������:
		for (int i = 0; i < arr.length ; i++){
			System.out.println(Sort.sortirovka(arr, arr[i]));
		}
	}

}
