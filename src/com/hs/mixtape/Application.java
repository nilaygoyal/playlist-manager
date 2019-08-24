package com.hs.mixtape;

import com.hs.mixtape.core.ChangeListExecutor;
import com.hs.mixtape.core.MixTapeProcessor;
import com.hs.mixtape.datamodel.ChangeList;
import com.hs.mixtape.datamodel.MixTape;
import com.hs.mixtape.persistence.JsonAdapter;

public class Application {
	public static void main(String[] args) throws Exception {
		
		if(args.length != 3) {
			throw new RuntimeException("Inavild arguments, "
					+ "usage - \'mixtape.json changelist.json output.json\'");
		}
		String mixtapeFile = args[0];
		String changeFile = args[1];
		String outputFile = args[2];
		
		// read mixtape json
		MixTape mixTape = JsonAdapter.readMixTape(mixtapeFile);
		// initiate processor with indexing
		MixTapeProcessor mixTapeProcessor = new MixTapeProcessor(mixTape);
		// read changes file
		ChangeList changes = JsonAdapter.readChanges(changeFile);
		// apply changes to mixtape via processor
		ChangeListExecutor.applyChangeList(mixTapeProcessor, changes);
		// write output json
		JsonAdapter.writeMixTape(mixTape, outputFile);
		
		
	}
	
}
