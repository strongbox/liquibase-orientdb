package liquibase.sqlgenerator.ext;

import liquibase.database.Database;
import liquibase.exception.ValidationErrors;
import liquibase.sql.Sql;
import liquibase.sqlgenerator.SqlGeneratorChain;
import liquibase.sqlgenerator.core.AbstractSqlGenerator;
import org.unbrokendome.liquibase.orientdb.sql.ChangePropertyTypeDateToDatetimeSql;
import org.unbrokendome.liquibase.orientdb.statement.ChangePropertyTypeDateToDatetimeStatement;
import org.unbrokendome.liquibase.orientdb.structure.OrientClass;
import org.unbrokendome.liquibase.orientdb.structure.OrientProperty;

public class ChangePropertyTypeDateToDatetimeSqlGenerator extends AbstractSqlGenerator<ChangePropertyTypeDateToDatetimeStatement> {

    @Override
    public ValidationErrors validate(ChangePropertyTypeDateToDatetimeStatement statement, Database database, SqlGeneratorChain sqlGeneratorChain) {
        ValidationErrors validationErrors = new ValidationErrors();

        validationErrors.checkRequiredField("className", statement.getClassName());
        validationErrors.checkRequiredField("propertyName", statement.getPropertyName());

        return validationErrors;
    }


    @Override
    public Sql[] generateSql(ChangePropertyTypeDateToDatetimeStatement statement, Database database, SqlGeneratorChain sqlGeneratorChain) {

        OrientClass orientClass = new OrientClass(statement.getClassName());
        OrientProperty property = new OrientProperty(orientClass, statement.getPropertyName());

        return new Sql[] { new ChangePropertyTypeDateToDatetimeSql(property) };
    }
}
