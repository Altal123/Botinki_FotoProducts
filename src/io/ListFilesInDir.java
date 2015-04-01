package io;

import java.io.File;

public class ListFilesInDir {
	
	public static void main (String[] args){
		
	String nameDir = "d:/3";
		
	File dir = new File (nameDir);
	
	File[] files = null;
	
	if(dir.isDirectory()){ 
		files = dir.listFiles();
			
		  fullListInDir(files);
		  
	}else System.out.println(dir.getAbsolutePath());
		  
}

	private static void fullListInDir(File[] files) {
		
		for(File f:files){
			
			   if(f.isFile()) System.out.println(f.getName());
			  
			   else{
				   
				   File[] subdir = f.listFiles();
				   
				   fullListInDir(subdir);
				   			
			       }
		  }
	}
}
