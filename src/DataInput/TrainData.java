package DataInput;

import java.awt.Label;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.text.html.HTMLDocument.Iterator;

public class TrainData {		
	private List<List<String>> trainData = new ArrayList<List<String>>(); //无标签数据
	private List<String> lable = new ArrayList<String>();//标签

	public TrainData(File file){		
		CalculateTrain(file);		
	}
	
	/**
	 * 解析文件获取train数据
	 * */
	public void CalculateTrain(File file){		
		BufferedReader br = null;
		try {
			 InputStream in = new FileInputStream(file);
			 InputStreamReader inr = new InputStreamReader(in);	    	 
	    	 br = new BufferedReader(inr);  
	         String tempString = br.readLine();  
	         System.out.println("train.csv is reading..."); 
	         int line = 0;
	         while ((tempString = br.readLine()) != null) {  	               
	             String[] temp = tempString.split(",");
	             lable.add(temp[0]);
	             List<String> tline = new ArrayList<String>();
	             for(int i=1;i<temp.length;i++){
	            	 String s = temp[i].equals("0") ? "0" : "1";
	            	 tline.add(s);
	             }
	             trainData.add(tline);
	             //System.out.println("read train line:"+line);
	             line++;
	         }  
	         inr.close(); 
	         br.close();
	         System.out.println("train.csv read over");
	     } catch (Throwable e) {  
	         e.printStackTrace();  
	     } 
		
	}
	
	public List<List<String>> getTrainData(){
		return trainData;
	}
	public void setTrainData(List<List<String>> trainData){
		this.trainData = trainData;
	}
	public List<String> getLable(){
		return lable;
	}
	public void setLable(List<String> lable){
		this.lable = lable;
	}
}
