package io;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import org.apache.commons.io.FileUtils;

//Написать программу, которая копирует все файлы из заданного каталога в целевой, 
//при этом переименовывая их в случайном порядке, назначая имена файлам из числовой 
//последовательности от 1 до N, где N — это количество файлов в каталоге. 
//При этом имена файлов не могут повторяться. 

public class RandomCopyFiles {
	
	ArrayList <File> inputFilesList = new ArrayList <File>();
	ArrayList <Integer> outputNamesList = new ArrayList<Integer>();
	public final String MASK = ".JPG"; //Маска копируемых файлов

	public static void main(String[] args) {
		
		RandomCopyFiles myobj = new RandomCopyFiles();
		myobj.randomCopyFiles();
		
	}
	
	public void randomCopyFiles(){
		
		//1. Наименование исх. и конечных каталогов
	
		String nameInputDir = "e:/Sony/"; 
		
		String nameOutputDir = "d:/4/"; 
		
		// 2 Создание конечной директории и удаление файлов в ней если они есть
		
		File inDir = new File (nameInputDir);
		
		File destDir = new File(nameOutputDir);
		
		if (!inDir.exists()){
			System.out.println("Не существует исходного каталога!: " + nameInputDir);
			System.exit(0);
		}
		
		if (inDir.isFile()){
			System.out.println("Вместо исходного каталога указан файл! Просьба проверить.");
			System.exit(0);
		}
		
		if (!destDir.exists()) destDir.mkdir(); //Если нет конечного каталога, создаем его.
		
		File[] infiles = inDir.listFiles();
		
		File[] outfiles = destDir.listFiles();
		
		if (outfiles.length > 0) {      //Если в конечном каталоге есть файлы - удаляем их
			for (File f:outfiles){
				f.delete();
			}
		}
		
		//3. Вызов метода allFilesByMask, формирующего коллекцию файлов в исх. каталоге и его подкаталогах, отфильтрованных по маске 
		
		allFilesByMask(infiles); 		
		
		if (inputFilesList.size() == 0) {
			System.out.println("Нечего копировать!");
			System.exit(0);
		}
		
		//4. Вызов метода random, формирующего коллекцию имен конечных файлов, созданных в случайном порядке и уникальных
		
		random();
		
		//5. Создаем папку temp в конечном каталоге
		
		File destDirTemp = new File(nameOutputDir + "temp");
		
		if (!destDirTemp.exists()) destDirTemp.mkdir();
		
		//6. Копируем файлы по одному в каталог temp, переименовываем их и перемещаем в конечный каталог
		
		try {
			
			for (int i = 0; i < inputFilesList.size(); i++){
			FileUtils.copyFileToDirectory(inputFilesList.get(i), destDirTemp);
			File oldFile = new File(nameOutputDir + "temp\\" + inputFilesList.get(i).getName());// ссылка на скопированный файл в temp
			File newFile = new File(nameOutputDir + outputNamesList.get(i) + MASK); //ссылку на новый файл в конечной директории
			 if(!oldFile.renameTo(newFile)) System.out.println("File " + inputFilesList.get(i).getName() + " rename failed!"); //переименовываем и перемещаем
		       
			}
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
		//7. Удаляем папку temp
		
		if (destDirTemp.exists()) destDirTemp.delete();
		
		System.out.println("Копирование завершилось успешно. Кол-во скопированных файлов: " + outputNamesList.size());

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
