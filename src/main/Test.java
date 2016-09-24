package main;

import java.io.File;

import Algorithm.MinerKNN;

public class Test {
	public static void main(String[] args){
		File train = new File("data/train.csv");
		File test = new File("data/test.csv");
		String filepath = "data/result2.csv";
		MinerKNN knn = new MinerKNN(train, test, filepath);
		System.out.println("算法结束");
	}
}
