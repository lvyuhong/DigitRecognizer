package DataInput;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 一个dataItems表示一个文件
 * **/
public class DataItems {
	public List<DataItem> items;
	public List<List<String>> datas;
	public List<List<String>> dataAndLables;
	public List<String> listLable;
	public DataItems(){
		items = new ArrayList<DataItem>();
		datas = new ArrayList<List<String>>();
		dataAndLables = new ArrayList<List<String>>();
		listLable = new ArrayList<String>();
	}
}
