package com.hs.mixtape.core;

import com.hs.mixtape.datamodel.Change;
import com.hs.mixtape.datamodel.ChangeList;

public class ChangeListExecutor {
	
	// execute a ChangeList on MixTape, applying each change sequentially
	public static final void applyChangeList(MixTapeProcessor mixTapeProcessor, ChangeList changesList) {
		for(Change change : changesList.getChanges()) {
			ChangeApplicator.applyOperation(mixTapeProcessor, change);
		}
	}
}
