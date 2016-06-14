package xAd.com.operations;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import xAd.com.model.Imps;

public class PrintOutput {
	public static void printOutput(String path) {
		ParallelReader pr =new ParallelReader();
		for(Map.Entry<String, HashMap<String, Imps>> map:pr.imps.entrySet())
		{
			String fileName= map.getKey();
			File file = new File(path+"/"+fileName.replace("csv", "json"));
			if(file.exists())
			{
				file.delete();
			}
			
			try {
				FileWriter writer = new FileWriter(file,true);
				for(Map.Entry<String, Imps> m:map.getValue().entrySet())
				{
					
					writer.write("{\"iso8601_timestamp\":\"");
					writer.write(m.getValue().unix_timestamp+"\",");
					writer.write("\"transaction_id\":\"");
					writer.write(m.getValue().transaction_id+"\",");
					writer.write("\"connection_type\":\"");
					writer.write(m.getValue().connection_type+"\",");
					writer.write("\"device_type\":\"");
					writer.write(m.getValue().device_type+"\",");
					writer.write("\"imps\":");
					writer.write(m.getValue().imps+",");
					writer.write("\"clicks\":");
					try {
						writer.write(pr.clicks.get(fileName).get(m.getKey()).clicks+"}");
					} catch (Exception e) {
						writer.write(0+"}");
						// TODO: handle exception
					}
					
					writer.append('\n');
					//System.out.println("hhh"+file);
				}
				writer.flush();
				writer.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
