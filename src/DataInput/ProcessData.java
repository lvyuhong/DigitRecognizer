package DataInput;

//import java.io.File;
//import java.io.FileReader;
//import java.io.BufferedReader;
import java.io.*;
import java.lang.reflect.Array;
import java.util.List;
import java.lang.*;

import javax.print.DocFlavor.STRING;

public class ProcessData {
	File train;
	File test;	
	List<String> listlable;
	DataItems dataItems;
	List<List<String>> trainDatas;
	List<List<String>> testDatas;
	public void ProcessData(File train,File test) throws IOException{
		this.train = train;
		this.test = test;
		
		DataItems trainDataItems = readFile(train);
		listlable = trainDataItems.listLable;
		trainDatas = trainDataItems.datas;
		
		DataItems testDataItems = readFile(test);
		testDatas = testDataItems.datas;
		
		
	}
	public List<String> getLable(File file) throws IOException{
		List<String> listlable=null;
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("ming.txt")));
		String dataLine = null;
		while((dataLine=br.readLine())!=null){
			String[] temp = dataLine.split(",");
			String lable = temp[0];
			listlable.add(lable);
		}
		return listlable;
	}
	public DataItems readFile(File file) throws IOException{
		DataItems dataItems = null;
		
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("ming.txt")));
		String dataLine = null;
		while((dataLine=br.readLine())!=null){
			String[] temp = dataLine.split(",");
			
			//生成DataItem
			DataItem dataItem = new DataItem();
			for(int i=0;i<temp.length;i++){
				dataItem.dataAndLable.add(temp[i]);
			}
			for(int i=1;i<temp.length;i++){
				dataItem.data.add(temp[i-1]);
			}			
			dataItem.lable = temp[0];
			
			//生成DataItems
			dataItems.items.add(dataItem);
			dataItems.datas.add(dataItem.data);
			dataItems.dataAndLables.add(dataItem.dataAndLable);
			dataItems.listLable.add(dataItem.lable);
			
		}
		
		return dataItems;
	}
	public void writeFile(File file){
		
	}
	public void main(String[] args) throws IOException{
		File train = new File("data\\train.csv");
		File test = new File("data\\test.csv");
		ProcessData(train,test);
		writeFile(train);
		writeFile(test);
	}
}
