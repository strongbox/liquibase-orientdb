package org.unbrokendome.liquibase.orientdb.sql;

import liquibase.structure.DatabaseObject;
import org.unbrokendome.liquibase.orientdb.common.OrientSqlMethod;
import org.unbrokendome.liquibase.orientdb.structure.OrientProperty;

import java.util.Collection;
import java.util.Collections;

/**
 * @see <a href="https://orientdb.org/docs/3.0.x/sql/SQL-Update.html">https://orientdb.org/docs/3.0.x/sql/SQL-Update.html</a>
 */
public class UpdateClassSetPropertyValueToAnotherPropertyValueSql extends AbstractOrientSql {

	private final OrientProperty sourceProperty;
	private final OrientProperty targetProperty;
	private final OrientSqlMethod sqlMethod;


	public UpdateClassSetPropertyValueToAnotherPropertyValueSql(OrientProperty sourceProperty, OrientProperty targetProperty, OrientSqlMethod sqlMethod) {
		this.sourceProperty = sourceProperty;
		this.targetProperty = targetProperty;
		this.sqlMethod = sqlMethod;
	}


	@Override
	public String toSql() {
		return String.format("UPDATE %s SET %s = %s", sourceProperty.getOrientClass().getName(), targetProperty.getQualifiedName(), applySqlMethodIfPresent(sourceProperty.getQualifiedName(), sqlMethod));
	}

	private String applySqlMethodIfPresent(String fieldName, OrientSqlMethod sqlMethod) {
		if (sqlMethod == null) {
			return fieldName;
		}

		switch (sqlMethod) {
		case AS_DATE:
			return fieldName + ".asDate()";
		case AS_DATETIME:
			return fieldName + ".asDateTime()";
		default:
			throw new IllegalArgumentException("Unknown OrientDb SQL method: " + sqlMethod);
		}
	}

	@Override
	public Collection<? extends DatabaseObject> getAffectedDatabaseObjects() {
		return Collections.singleton(targetProperty);
	}
}
