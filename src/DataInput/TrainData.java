package DataInput;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.text.html.HTMLDocument.Iterator;

public class TrainData {	
	private Map<String, List<String>> trainData;
	private List<String> Lable;
	public TrainData(Map<String, List<String>> trainData){
		this.trainData = trainData;
		this.Lable = CalculateLable(trainData);		
	}
	public List<String> CalculateLable(Map<String, List<String>> trainData){
		List<String> list = new ArrayList<String>();		
			
			for(Map.Entry<String, List<String>> entry : trainData.entrySet()){
				list.add(entry.getKey());
			}
		
		return list;
	}
	
	public Map<String, List<String>> getTrainData(){
		return trainData;
	}
	public void setTrainData(Map<String, List<String>> trainData){
		this.trainData = trainData;
	}
	public List<String> getLable(){
		return Lable;
	}
	public void setLable(List<String> lable){
		this.Lable = lable;
	}
}
