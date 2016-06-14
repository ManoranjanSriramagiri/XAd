package xAd.com.operations;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import xAd.com.controller.MainController;
import xAd.com.model.Clicks;
import xAd.com.model.Imps;

public class ParallelReader extends Thread{
	public static HashMap<String,HashMap<String,Clicks>> clicks =new HashMap<String,HashMap<String,Clicks>>();
	public static HashMap<String,HashMap<String,Imps>> imps =new HashMap<String,HashMap<String,Imps>>();
	public static HashMap<String,String> deviceType =new HashMap<String,String>();
	public static HashMap<String,String> connectionType =new HashMap<String,String>();
	
	public ParallelReader(File[] listOfFiles, int start, int end,int flag)  {
		for(int i=start;i<end;i++)
		{
			//System.out.println(listOfFiles[i].getName());	
			ParallelReader pr =new ParallelReader();
			if(flag==1)
				pr.readClicks(listOfFiles[i].getPath(),listOfFiles[i].getName());
			else {
				pr.readImps(listOfFiles[i].getPath(),listOfFiles[i].getName());
			}
		}
	}
	public ParallelReader()
	{
		
	}
	
	public void readClicks(String filePath,String fileName)
	{
		HashMap<String,Clicks> tempClicks =new HashMap<String, Clicks>();
		try {
			List<String>lines = Files.readAllLines(Paths.get(filePath), StandardCharsets.UTF_8);
			Iterator<String> line = lines.iterator();
			while(line.hasNext())
			{
				String [] words= line.next().split(",");
				if(words.length==3)
				{
					//System.out.println(fileName);
					Clicks c =new Clicks();
					c.unix_timestamp=TimeConverter.timeConverter(words[0]);
					c.transaction_id=words[1];
					c.clicks=Integer.parseInt(words[2]);
					tempClicks.put(c.transaction_id,c);
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		clicks.put(fileName, tempClicks);
		
	}
	
	public void readImps(String filePath,String fileName)
	{
		String etl=TimeConverter.timeConverter()+"INFO Hour" +fileName.replace(".csv", "")+"ETL start";
		long start=System.currentTimeMillis();
		MainController.etlInfo.add(etl);
		HashMap<String,Imps> tempImps =new HashMap<String, Imps>();
		try {
			List<String>lines = Files.readAllLines(Paths.get(filePath), StandardCharsets.UTF_8);
			Iterator<String> line = lines.iterator();
			while(line.hasNext())
			{
				String [] words= line.next().split(",");
				if(words.length==5)
				{
					Imps c =new Imps();
					c.unix_timestamp=TimeConverter.timeConverter(words[0]);
					c.transaction_id=words[1];
					c.connection_type=connectionType.get(words[2]);
					c.device_type=deviceType.get(words[3]);
					c.imps=Integer.parseInt(words[4]);
					tempImps.put(c.transaction_id,c);
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		imps.put(fileName, tempImps);
		long end =System.currentTimeMillis();
		
		etl=TimeConverter.timeConverter()+"INFO Hour" +fileName.replace(".csv", "")+"ETL complete"+", elapsed time: "+(end-start)/1000+"s";
		MainController.etlInfo.add(etl);
	}
	
	public void readDeviceType(String filePath)
	{
		try {
			List<String>lines = Files.readAllLines(Paths.get(filePath), StandardCharsets.UTF_8);
			Iterator<String> line = lines.iterator();
			while(line.hasNext())
			{
				String temp = line.next();
				temp=temp.replace("[", "").replace("]", "").replace("\"", "");
				String []values=temp.split(",");
				if(values.length>1)
				{
					deviceType.put(values[0].trim(), values[1].trim());
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		/*for (Map.Entry<String, String> m : deviceType.entrySet()) {
			System.out.println(m.getKey()+"-->"+m.getValue());
		}*/
	}
	
	public void readConnectionType(String filePath)
	{
		try {
			List<String>lines = Files.readAllLines(Paths.get(filePath), StandardCharsets.UTF_8);
			Iterator<String> line = lines.iterator();
			while(line.hasNext())
			{
				String temp = line.next();
				temp=temp.replace("[", "").replace("]", "").replace("\"", "");
				String []values=temp.split(",");
				if(values.length>1)
				{
					connectionType.put(values[0].trim(), values[1].trim());
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		/*for (Map.Entry<String, String> m : connectionType.entrySet()) {
			System.out.println(m.getKey()+"-->"+m.getValue());
		}*/
	}
}
