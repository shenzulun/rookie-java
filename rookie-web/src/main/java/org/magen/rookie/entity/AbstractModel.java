package org.magen.rookie.entity;

import org.apache.commons.lang.builder.ToStringBuilder;

public abstract class AbstractModel implements java.io.Serializable{
	
	private static final long serialVersionUID = 6057714499194440346L;

	@Override
	public String toString() {		
	    return ToStringBuilder.reflectionToString(this);
	}
	
	protected void desc(){}
}
