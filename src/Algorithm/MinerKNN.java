package Algorithm;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import DataInput.DisMap;
import DataInput.TestData;
import DataInput.TrainData;

public class MinerKNN {
	int k = 20;//k近邻
	TrainData train;
	TestData test;
	String filepath;
	public MinerKNN(File trainfile,File testfile,String filepath){	
		train = new TrainData(trainfile);
		test = new TestData(testfile);
		this.filepath = filepath;
		KNNAlgo();
	}
	public void KNNAlgo(){
		//计算k近邻距离
		System.out.println("=======计算k近邻距离开始========");
		List<List<DisMap>> dislist = CalDistance(); //test.size*train.size
		System.out.println("=======计算k近邻距离结束========");		
		//决策出分类结果
		System.out.println("=======决策分类开始========");
		List<Integer> result = new ArrayList<Integer>(); //test.size
		for(int i=0;i<test.getTestData().size();i++){
		//for(int i=0;i<20;i++){
			int classify = MakeDecision(dislist.get(i));
			result.add(classify);
		}
		System.out.println("=======决策分类结束========");
		//写出结果
		System.out.println("=======结果写入文件开始========");
		WriteResult(result, filepath);
		System.out.println("=======结果写入文件开始========");
	}
	/**
	 * @title CalDistance
	 * @description 计算数据之间的欧式距离
	 * @return List<List<DisMap>> test.size*train.size */
	public List<List<DisMap>> CalDistance(){
		List<List<DisMap>> dislist = new ArrayList<List<DisMap>>();
		for(int i=0;i<test.getTestData().size();i++){
		//for(int i=0;i<20;i++){
			List<DisMap> dis = new ArrayList<DisMap>();
			List<String> line = test.getTestData().get(i);
			for(int k=0;k<train.getTrainData().size();k++){
			//for(int k=0;k<20;k++){
				int distance = calDis(train.getTrainData().get(k), test.getTestData().get(i));							
				DisMap disMap = new DisMap(distance, Integer.parseInt(train.getLable().get(k)));
				dis.add(disMap);
			}
			List<DisMap> k_dis = CalKNearest(dis);
			dislist.add(k_dis);
			System.out.println("计算第"+i+"个距离结束");
		}
		return dislist;
	}
	
	/**
	 * @title CalKNearest
	 * @description 获取k近邻的点(距离最小的k个值)
	 * @return List<DisMap>*/
	public List<DisMap> CalKNearest(List<DisMap> dis){
		List<DisMap> k_dis = new ArrayList<DisMap>(); 
		for(int i=0;i<k;i++){
			k_dis.add(dis.get(i));
		}
		for(int i=k;i<dis.size();i++){
			DisMap disMax = getMax(k_dis);
			if(dis.get(i).getDistance()<disMax.getDistance()){
				k_dis.remove(disMax);
				k_dis.add(dis.get(i));
			}
		}
		return k_dis;
	}
	
	/**
	 * @title MakeDecision
	 * @description 决策 在k个近邻距离中，选出类别个数最大的一类
	 * @return List<DisMap>*/
	public int MakeDecision(List<DisMap> list){
		int classify = 0;
		Map<Integer, Integer> labelNum = new HashMap<Integer, Integer>(); //map<label,count>
		for(int i=0;i<list.size();i++){
			int label = list.get(i).getLable();
			if(labelNum.containsKey(label)){
				int count = labelNum.get(label);
				count++;
				labelNum.put(label,count);
			}
			else {
				labelNum.put(label, 1);
			}
		}
		int max=0;
		for(Map.Entry<Integer, Integer> entry : labelNum.entrySet()){			
			if(entry.getValue()>max){
				max = entry.getValue();
				classify = entry.getKey();
			}
		}
		return classify;
	}
	/**
	 * @title WriteResult
	 * @description 把result的结果写入文件path中
	 * @return List<DisMap>*/
	public void WriteResult(List<Integer> list,String filepath){
		BufferedWriter bw = null;
		
		try{
			OutputStream os = new FileOutputStream(filepath);
			OutputStreamWriter out = new OutputStreamWriter(os);
			bw = new BufferedWriter(out);
			bw.write("ImageId,");
			bw.write("Label");
			for(int i=0;i<list.size();i++){
				String id = String.valueOf(i+1);
				String label = String.valueOf(list.get(i));
				bw.newLine();
				bw.write(id+",");
				bw.write(label);
				bw.flush();
			}
			bw.close();
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	//获取最大值
	public DisMap getMax(List<DisMap> kdis){
		DisMap disMax = new DisMap(0, 0);
		for(int i=0;i<kdis.size();i++){
			DisMap disMap = kdis.get(i);
			if(disMap.getDistance()>disMax.getDistance()){
				disMax = disMap;
			}
		}
		return disMax;
	}
	//计算两个向量的曼哈顿距离
	public int calDis(List<String> list1,List<String> list2){
		int distance = 0;
		for(int i=0;i<list1.size();i++){
			int x = Integer.parseInt(list1.get(i));
			int y = Integer.parseInt(list2.get(i));
			distance += Math.abs(x-y);
		}
		return distance;
	}
}
