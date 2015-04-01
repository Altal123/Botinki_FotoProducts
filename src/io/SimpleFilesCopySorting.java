package io;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;

//�������� � ����� ��������� ���������� ��������� � ��������� ������: java -classpath ./bin;./lib/commons-io-2.4.jar io.SimpleFilesCopySorting aa.txt bb.txt asc/desc
//��������� ��������������, ��� ��� ����������� ����� ���� ������ � ���������� ./bin, � ����� ��������� ���� � ���������� commons-io-2.4.jar

public class SimpleFilesCopySorting {

	public static void main(String[] args) {
				
		String nameFileIn = "d:/3/aa.txt"; 
		
		
		String nameFileOut = "d:/3/bb.txt"; 
		
		
		String direction = "asc"; //asc - ���������� ����������, desc - ����������
		
		if (args.length == 3){
			
			nameFileIn = "d:/3/" + args[0];
			
			nameFileOut = "d:/3/" + args[1];
			
			direction = args[2];
			
		}
		
		File fileIn = new File(nameFileIn); //������ �� ��. ����
		File fileOut = new File(nameFileOut); //������ �� ���. ����
		
		try {
			List<String> lines = FileUtils.readLines(fileIn); // ��������� ����� - ��. ����
			
			if (fileOut.exists()) fileOut.delete();
			if (!fileOut.exists()) 	fileOut.createNewFile();
			
			FileUtils.writeLines(fileOut, sortingBySize(lines, direction));
			
			
		} catch (IOException e) {
	
			e.printStackTrace();
		}
			
		
	}
	
	public static List<String> sortingBySize(List<String> content, String direction) {		
		//���������� ������� ������� ����� - �� ����������� �� ������������ ����� (��� ��������)
		
		int index; 
		String temp;
	try{	
		for (int j = 0; j < content.size(); j++){
			index = j;
	    	for (int i = j; i < content.size(); i++){

			    if (content.get(index).length() > content.get(i).length() && direction.equals("asc")) index = i;
			    if (content.get(index).length() < content.get(i).length() && direction.equals("desc")) index = i;
			
		    }
		  temp = content.get(j);
		  content.set(j, content.get(index));
		  content.set(index, temp);		  
		  
		}
		return content;

	} catch(NullPointerException e){
		
		e.printStackTrace();
		return content;
		
	}
  }
	
}
