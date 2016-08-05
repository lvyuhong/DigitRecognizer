package DataInput;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * 一个dataItem表示一行数据
 * **/
public class DataItem {
	public List<String> data;
	public List<String> dataAndLable;
	public String lable;
	public DataItem(){
		data = new ArrayList<String>();
		dataAndLable = new ArrayList<String>();
		lable = new String();
	}
}
