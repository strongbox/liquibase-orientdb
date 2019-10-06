package org.unbrokendome.liquibase.orientdb.statement;

import liquibase.statement.AbstractSqlStatement;

public class UpdateClassRemovePropertyStatement extends AbstractSqlStatement {

	private String className;
	private String propertyName;


	public String getClassName() {
		return className;
	}


	public UpdateClassRemovePropertyStatement setClassName(String className) {
		this.className = className;
		return this;
	}


	public String getPropertyName() {
		return propertyName;
	}


	public UpdateClassRemovePropertyStatement setPropertyName(String propertyName) {
		this.propertyName = propertyName;
		return this;
	}

}
