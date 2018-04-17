package dev.andrei.mario.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Utils {

	private static BufferedReader br;

	public static String loadFileAsString(String path){
		StringBuilder builder = new StringBuilder();
		
		try{
			br = new BufferedReader(new FileReader(path));
			String line;
			
			while((line = br.readLine()) != null){
				builder.append(line + "\n");
			}
				
			return builder.toString();
			
		}catch(IOException e){
			e.printStackTrace();
			return "";
		}
		
	}
	
	public static int parseInt(String number){
		
		return Integer.parseInt(number);
	}
}