package liquibase.sqlgenerator.ext;

import liquibase.database.Database;
import liquibase.exception.ValidationErrors;
import liquibase.sql.Sql;
import liquibase.sqlgenerator.SqlGeneratorChain;
import liquibase.sqlgenerator.core.AbstractSqlGenerator;
import org.unbrokendome.liquibase.orientdb.sql.UpdateClassSetPropertyValueToAnotherPropertyValueSql;
import org.unbrokendome.liquibase.orientdb.statement.UpdateClassSetPropertyValueToAnotherPropertyValueStatement;
import org.unbrokendome.liquibase.orientdb.structure.OrientClass;
import org.unbrokendome.liquibase.orientdb.structure.OrientProperty;

public class UpdateClassSetPropertyValueToAnotherPropertyValueSqlGenerator extends AbstractSqlGenerator<UpdateClassSetPropertyValueToAnotherPropertyValueStatement> {

    @Override
    public ValidationErrors validate(UpdateClassSetPropertyValueToAnotherPropertyValueStatement statement, Database database,
                                     SqlGeneratorChain sqlGeneratorChain) {

        ValidationErrors validationErrors = new ValidationErrors();
        validationErrors.checkRequiredField("className", statement.getClassName());
        validationErrors.checkRequiredField("targetPropertyName", statement.getTargetPropertyName());
        validationErrors.checkRequiredField("sourcePropertyName", statement.getSourcePropertyName());

        return validationErrors;
    }

    @Override
    public Sql[] generateSql(UpdateClassSetPropertyValueToAnotherPropertyValueStatement statement, Database database, SqlGeneratorChain sqlGeneratorChain) {

        OrientClass orientClass = new OrientClass(statement.getClassName());
        OrientProperty sourceProperty = new OrientProperty(orientClass, statement.getSourcePropertyName());
        OrientProperty targetProperty = new OrientProperty(orientClass, statement.getTargetPropertyName());

        return new Sql[] { new UpdateClassSetPropertyValueToAnotherPropertyValueSql(sourceProperty, targetProperty, statement.getSqlMethod()) };
    }
}
