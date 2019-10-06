package org.unbrokendome.liquibase.orientdb.sql;

import liquibase.structure.DatabaseObject;
import org.unbrokendome.liquibase.orientdb.structure.OrientProperty;

import java.util.Collection;
import java.util.Collections;

/**
 * @see <a href="https://orientdb.org/docs/3.0.x/sql/SQL-Update.html">https://orientdb.org/docs/3.0.x/sql/SQL-Update.html</a>
 */
public class UpdateClassRemovePropertySql extends AbstractOrientSql {

	private final OrientProperty property;


	public UpdateClassRemovePropertySql(OrientProperty property) {
		this.property = property;
	}


	@Override
	public String toSql() {
		return String.format("UPDATE %s REMOVE %s", property.getOrientClass().getName(), property.getQualifiedName());
	}


	@Override
	public Collection<? extends DatabaseObject> getAffectedDatabaseObjects() {
		return Collections.singleton(property);
	}
}
