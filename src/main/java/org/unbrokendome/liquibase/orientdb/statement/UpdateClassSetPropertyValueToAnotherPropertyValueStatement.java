package org.unbrokendome.liquibase.orientdb.statement;

import liquibase.statement.AbstractSqlStatement;
import liquibase.statement.SqlStatement;
import org.unbrokendome.liquibase.orientdb.common.OrientSqlMethod;

public class UpdateClassSetPropertyValueToAnotherPropertyValueStatement extends AbstractSqlStatement {
    private String className;
    private String sourcePropertyName;
    private String targetPropertyName;
    private OrientSqlMethod sqlMethod;


    public String getClassName() {
        return className;
    }


    public UpdateClassSetPropertyValueToAnotherPropertyValueStatement setClassName(String className) {
        this.className = className;
        return this;
    }


    public String getSourcePropertyName() {
        return sourcePropertyName;
    }


    public UpdateClassSetPropertyValueToAnotherPropertyValueStatement setSourcePropertyName(String sourcePropertyName) {
        this.sourcePropertyName = sourcePropertyName;
        return this;
    }


    public String getTargetPropertyName() {
        return targetPropertyName;
    }


    public UpdateClassSetPropertyValueToAnotherPropertyValueStatement setTargetPropertyName(String targetPropertyName) {
        this.targetPropertyName = targetPropertyName;
        return this;
    }


    public OrientSqlMethod getSqlMethod() {
        return sqlMethod;
    }


    public SqlStatement setSqlMethod(OrientSqlMethod sqlMethod) {
        this.sqlMethod = sqlMethod;
        return this;
    }
}
