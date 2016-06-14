package xAd.com.controller;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import xAd.com.operations.ParallelReader;
import xAd.com.operations.PrintOutput;

public class MainController {

	public static List<String> etlInfo;
	public static void main(String[] args) {
		etlInfo=new LinkedList<String>();
		 File file= new File("logs/etl.log");
		 
		if(file.exists())
		{
			file.delete();
		}
		try{
			MainController rf =new MainController();
			ParallelReader p =new ParallelReader();
			p.readDeviceType("in/dimensions/connection_type.json");
			p.readConnectionType("in/dimensions/device_type.json");
			if(args.length==1)
			{
				rf.readAllFilesClick("in/facts/clicks",Integer.parseInt(args[0]));
				rf.readAllFilesImps("in/facts/imps",Integer.parseInt(args[0])); 
				System.out.println("Par to" +Integer.parseInt(args[0]) );
			}
			else
			{
				rf.readAllFilesClick("in/facts/clicks",5);
				rf.readAllFilesImps("in/facts/imps",5); 
				System.out.println("Default par");
			}
			PrintOutput.printOutput("out");
		}
		catch (Exception e){
		}
		
		try {
			FileWriter writer = new FileWriter(file,true);
			int len =etlInfo.size();
			for(int i=0;i<len;i++)
			{
				writer.write(etlInfo.get(i)+"\n");;
			}
			
			writer.flush();
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void readAllFilesClick(String path, double parallel) {
	//	path = "";
		File folder = new File(path);
		File[] listOfFiles = folder.listFiles();
		int start =0, increment =(int) Math.ceil(listOfFiles.length/parallel);
		int multiThread=(int)(parallel<listOfFiles.length?parallel:listOfFiles.length);
		ParallelReader []pr =new ParallelReader[multiThread];
		for(int i=0;i<multiThread;i++)
		{
			if(listOfFiles.length<start+increment){
				//System.out.println(start+"-->"+listOfFiles.length);
				pr[i]=new ParallelReader(listOfFiles, start, listOfFiles.length,1);
			}
			else{
				pr[i]=new ParallelReader(listOfFiles, start, (start+increment),1);
				//System.out.println(start+"-->"+(start+increment));
			}
			start+=increment;
		}
		
		for (ParallelReader parallelReader : pr) {
			try {
				parallelReader.join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
	
	public void readAllFilesImps(String path, double parallel) {
		//path = "H:\\CMPE 281\\jcloud281\\com\\in\\facts\\imps";
		File folder = new File(path);
		File[] listOfFiles = folder.listFiles();
		int start =0, increment =(int) Math.ceil(listOfFiles.length/parallel);
		int multiThread=(int)(parallel<listOfFiles.length?parallel:listOfFiles.length);
		ParallelReader []pr =new ParallelReader[multiThread];
		for(int i=0;i<multiThread;i++)
		{
			if(listOfFiles.length<start+increment){
				//System.out.println(start+"-->"+listOfFiles.length);
				pr[i]=new ParallelReader(listOfFiles, start, listOfFiles.length,0);
			}
			else{
				pr[i]=new ParallelReader(listOfFiles, start, (start+increment),0);
				//System.out.println(start+"-->"+(start+increment));
			}
			start+=increment;
		}
		
		for (ParallelReader parallelReader : pr) {
			try {
				parallelReader.join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
}
