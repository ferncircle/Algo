package com.chawkalla.algorithms;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Random;

public class FileExpand {

	public static void main(String[] args)throws Exception{
		String inputFileName="D:\\svn\\sandbox\\test\\input.xml";
		String outputFileName="D:\\svn\\sandbox\\test\\output.xml";

		expandInputFile(inputFileName, outputFileName, 10);
	}

	public static void expandInputFile(String inFile, String outFile, int totalTimes) throws Exception{
		int i=0;
		Random r=new Random(10000);
		while(i<totalTimes){
			BufferedReader br = new BufferedReader(new FileReader(inFile));
			BufferedWriter out = new BufferedWriter(new FileWriter(outFile, true));
			String line;
			while ((line = br.readLine()) != null) {
				line=line.replaceAll("<name>([a-zA-Z]*)</name>", "<name>$1"+r.nextInt()+"</name>");
				out.write(line);
				out.newLine();
			}
			br.close();
			out.close();
			System.out.println("finished copying instances="+i++);
		}
	}

}
