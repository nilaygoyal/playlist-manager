package com.hs.mixtape.persistence;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.hs.mixtape.datamodel.ChangeList;
import com.hs.mixtape.datamodel.MixTape;

/**
 * 
 * JSON file read/write operations, internally using com.google.gson.Gson
 *
 */
public class JsonAdapter {
	private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
	public static MixTape readMixTape(String filename) throws FileNotFoundException {
		MixTape mixtape = GSON.fromJson(new BufferedReader(new FileReader(new File(filename))), MixTape.class);
		return mixtape;
	}
	
	public static String writeMixTape(MixTape mixtape, String filename) throws IOException {
		String json = GSON.toJson(mixtape);
		FileWriter fileWriter = new FileWriter(filename);
        fileWriter.write(json);
        fileWriter.close();
		return json;
	}
	
	public static ChangeList readChanges(String filename) throws FileNotFoundException {
		ChangeList changeList = GSON.fromJson(new BufferedReader(new FileReader(new File(filename))), ChangeList.class);
		return changeList;
	}
}
