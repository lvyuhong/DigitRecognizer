package DataInput;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TestData {
	private List<List<String>> testData = new ArrayList<List<String>>(); //无标签数据	
	public TestData(File file){
		CalculateTest(file);
	}
	/**
	 *由文件获取test数据
	 *解析数据*/
	public void CalculateTest(File file){
		
		BufferedReader br = null;
		try {
			InputStream in = new FileInputStream(file);
			 InputStreamReader inr = new InputStreamReader(in);	    	 
	    	 br = new BufferedReader(inr);  
	         String tempString = br.readLine();  
	         System.out.println("Test.csv is reading..."); 
	         int line = 0;
	         while ((tempString = br.readLine()) != null) {  	               
	             String[] temp = tempString.split(",");
	             List<String> tline = new ArrayList<String>();
	             for(int i=0;i<temp.length;i++){
	            	 String s = temp[i].equals("0") ? "0" : "1";
	            	 tline.add(s);
	             }
	             testData.add(tline);
	             //System.out.println("read test line:"+line);
	             line++;	             
	         }  
	         inr.close(); 
	         br.close();
	         System.out.println("test.csv read over");
	     } catch (Throwable e) {  
	         e.printStackTrace();  
	     } 		
	}
	public List<List<String>> getTestData(){
		return testData;
	}
	public void setTestData(List<List<String>> testList){
		testData = testList;
	}
}
