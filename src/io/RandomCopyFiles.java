package io;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import org.apache.commons.io.FileUtils;

//�������� ���������, ������� �������� ��� ����� �� ��������� �������� � �������, 
//��� ���� �������������� �� � ��������� �������, �������� ����� ������ �� �������� 
//������������������ �� 1 �� N, ��� N � ��� ���������� ������ � ��������. 
//��� ���� ����� ������ �� ����� �����������. 

public class RandomCopyFiles {
	
	ArrayList <File> inputFilesList = new ArrayList <File>();
	ArrayList <Integer> outputNamesList = new ArrayList<Integer>();
	public final String MASK = ".JPG"; //����� ���������� ������

	public static void main(String[] args) {
		
		RandomCopyFiles myobj = new RandomCopyFiles();
		myobj.randomCopyFiles();
		
	}
	
	public void randomCopyFiles(){
		
		//1. ������������ ���. � �������� ���������
	
		String nameInputDir = "e:/Sony/"; 
		
		String nameOutputDir = "d:/4/"; 
		
		// 2 �������� �������� ���������� � �������� ������ � ��� ���� ��� ����
		
		File inDir = new File (nameInputDir);
		
		File destDir = new File(nameOutputDir);
		
		if (!inDir.exists()){
			System.out.println("�� ���������� ��������� ��������!: " + nameInputDir);
			System.exit(0);
		}
		
		if (inDir.isFile()){
			System.out.println("������ ��������� �������� ������ ����! ������� ���������.");
			System.exit(0);
		}
		
		if (!destDir.exists()) destDir.mkdir(); //���� ��� ��������� ��������, ������� ���.
		
		File[] infiles = inDir.listFiles();
		
		File[] outfiles = destDir.listFiles();
		
		if (outfiles.length > 0) {      //���� � �������� �������� ���� ����� - ������� ��
			for (File f:outfiles){
				f.delete();
			}
		}
		
		//3. ����� ������ allFilesByMask, ������������ ��������� ������ � ���. �������� � ��� ������������, ��������������� �� ����� 
		
		allFilesByMask(infiles); 		
		
		if (inputFilesList.size() == 0) {
			System.out.println("������ ����������!");
			System.exit(0);
		}
		
		//4. ����� ������ random, ������������ ��������� ���� �������� ������, ��������� � ��������� ������� � ����������
		
		random();
		
		//5. ������� ����� temp � �������� ��������
		
		File destDirTemp = new File(nameOutputDir + "temp");
		
		if (!destDirTemp.exists()) destDirTemp.mkdir();
		
		//6. �������� ����� �� ������ � ������� temp, ��������������� �� � ���������� � �������� �������
		
		try {
			
			for (int i = 0; i < inputFilesList.size(); i++){
			FileUtils.copyFileToDirectory(inputFilesList.get(i), destDirTemp);
			File oldFile = new File(nameOutputDir + "temp\\" + inputFilesList.get(i).getName());// ������ �� ������������� ���� � temp
			File newFile = new File(nameOutputDir + outputNamesList.get(i) + MASK); //������ �� ����� ���� � �������� ����������
			 if(!oldFile.renameTo(newFile)) System.out.println("File " + inputFilesList.get(i).getName() + " rename failed!"); //��������������� � ����������
		       
			}
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
		//7. ������� ����� temp
		
		if (destDirTemp.exists()) destDirTemp.delete();
		
		System.out.println("����������� ����������� �������. ���-�� ������������� ������: " + outputNamesList.size());

	}
	
	private void random(){
		
		Random random = new Random();
		int temp;

		do{	
			temp = random.nextInt(inputFilesList.size()) + 1;
			for (Integer f:outputNamesList){
				if (f == temp){
					temp = -1;
					break;
				}
			}
			if (temp != -1) outputNamesList.add(temp);
			
		}while(outputNamesList.size() < inputFilesList.size());		
		
	}
		

	private void allFilesByMask(File[] files) {
		
		for(File f:files){
			
			if(f.isDirectory()){
   
				File[] subdir = f.listFiles();
				   
				allFilesByMask(subdir);
				
			}
			
			if(f.isFile() && f.getName().endsWith(MASK)) inputFilesList.add(f);
			
		  }
		
	}

}
