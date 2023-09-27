package org.pack;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


import com.google.gson.JsonParser;

public class Practice1 {
	public static void main(String[] args) {
		try {
			FileReader read=new FileReader("C:\\Users\\LENOVO\\eclipse-workspace\\API_Practice\\src\\test\\resources\\json\\sample.json");
			JSONParser jp=new JSONParser();
			try {
				Object obj = jp.parse(read);
				JSONObject job=(JSONObject)obj;
				String nam =(String) job.get("Name");
				System.out.println(nam);
				JSONArray jar=(JSONArray)job.get("Subject");
				for(Object x:jar)
				{
					System.out.println(x);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
