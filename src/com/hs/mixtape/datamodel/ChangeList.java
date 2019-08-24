package com.hs.mixtape.datamodel;

import java.util.List;
/**
 * 
 * ChangeList is the datamodel for multiple changes to be applied
 * 
 * <pre>
 * {
 *    "changes" : [
 *    	#change,
 *    	...
 *    ]
 * }
 * </pre>
 *
 */
public class ChangeList {
	private List<Change> changes;
	public ChangeList() {
		super();
	}
	public List<Change> getChanges() {
		return changes;
	}
	public void setChanges(List<Change> changes) {
		this.changes = changes;
	}
	

}
