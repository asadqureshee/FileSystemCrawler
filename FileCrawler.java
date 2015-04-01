package lab6;
import java.util.*;
import java.io.*;
import java.util.Set;

import org.apache.commons.collections4.MultiMap;
import org.apache.commons.collections4.map.MultiValueMap;


public class FileCrawler {
	
	public MultiMap<String, String> multiMap = new MultiValueMap(); 
 WorkQueue workQ;
	static int i = 0;
	String myQueue[];
	static int j = 0;
	String entries[]; 

	private class Worker implements Runnable {

		private WorkQueue queue;


		public Worker(WorkQueue q) {
			queue = q;
		}

		public void run() {
			//System.out.println("Please enter the name that you want to search");
			//
			//String chk = "dsa.txt";
			String name;
			while ((name = queue.remove()) != null) {
				File file = new File(name);
				entries =  file.list();
				

				
				if (entries == null)
					continue;
				for (String entry : entries) {
					if (entry.compareTo(".") == 0)
						continue;
					if (entry.compareTo("..") == 0)
						continue;
					String fn = name + "\\" + entry;

					System.out.println(fn);
					//System.out.println(name);
					multiMap.put(entry, fn);
				
					File chk = new File(entry);
					try {
					    Scanner scanner = new Scanner(chk);

					    //now read the file line by line...
					   // int lineNum = 0;
					    while (scanner.hasNextLine()) {
					        String line = scanner.nextLine();
					        //lineNum++;
					        if(line.contains("d")) { 
					        	multiMap.put("dsa.txt",fn);    
					        
					        }
					    }
					} catch(FileNotFoundException e) { 
					    //handle this
					}
					
					
				}
				
				}
			
		
				if(multiMap.get("dsa.txt") !=null){
					System.out.println();
					System.out.println("File found!");
					
					System.out.println("Key = " + "dsa.txt");
					System.out.println("Values = " + multiMap.get("dsa.txt"));
					
					
					}
					
				
			}
			
	}
		
	

	public FileCrawler() {
		workQ = new WorkQueue();
	}

	public Worker createWorker() {
		return new Worker(workQ);
	}


	// need try ... catch below in case the directory is not legal



	public void processDirectory(String dir) {

		try{

			File file = new File(dir);
			if (file.isDirectory()) {
				String entries[] = file.list();
				if (entries != null)
					workQ.add(dir);

				for (String entry : entries) {
					String subdir;
					if (entry.compareTo(".") == 0)
						continue;
					if (entry.compareTo("..") == 0)
						continue;
					if (dir.endsWith("//")) {
						subdir = dir+entry;


					}
					else {
						subdir = dir+"//"+entry;

						processDirectory(subdir);

					}

				}
			}}catch(Exception e){}
	}

}
