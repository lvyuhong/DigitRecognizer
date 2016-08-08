package DataInput;

import java.util.List;

public class TestData {
	private List<String> testData;
	public TestData(List<String> testData){
		this.testData = testData;
	}
	public List<String> getTestData(){
		return testData;
	}
	public void setTestData(List<String> testList){
		testData = testList;
	}
}
