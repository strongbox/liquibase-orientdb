package org.unbrokendome.liquibase.orientdb.sql;

import liquibase.structure.DatabaseObject;
import org.unbrokendome.liquibase.orientdb.structure.OrientProperty;

import java.util.Collection;
import java.util.Collections;
import java.util.UUID;

public class ChangePropertyTypeDateToDatetimeSql extends AbstractOrientSql {

	private final OrientProperty property;

	public ChangePropertyTypeDateToDatetimeSql(OrientProperty property) {
		this.property = property;
	}

	@Override
	public String toSql() {
		final String className = property.getOrientClass().getName();
		final String propertyName = property.getName();

		final String tempPropertyName = UUID.randomUUID().toString();
		return "UPDATE " + className + " SET " + tempPropertyName + " = " + propertyName + ".asDatetime();\n" +
				"UPDATE " + className + " REMOVE " + propertyName + ";\n" +
				"UPDATE " + className + " SET " + propertyName + " = " + tempPropertyName + ";\n" +
				"UPDATE " + className + " REMOVE " + tempPropertyName + ";\n" +
				"CREATE PROPERTY " + className + "." + propertyName + " datetime";
	}

	@Override
	public Collection<? extends DatabaseObject> getAffectedDatabaseObjects() {
		return Collections.singleton(property);
	}
}
