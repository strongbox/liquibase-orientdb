package liquibase.sqlgenerator.ext;

import liquibase.database.Database;
import liquibase.exception.ValidationErrors;
import liquibase.sql.Sql;
import liquibase.sqlgenerator.SqlGeneratorChain;
import liquibase.sqlgenerator.core.AbstractSqlGenerator;
import org.unbrokendome.liquibase.orientdb.sql.UpdateClassRemovePropertySql;
import org.unbrokendome.liquibase.orientdb.statement.UpdateClassRemovePropertyStatement;
import org.unbrokendome.liquibase.orientdb.structure.OrientClass;
import org.unbrokendome.liquibase.orientdb.structure.OrientProperty;

public class UpdateClassRemovePropertySqlGenerator extends AbstractSqlGenerator<UpdateClassRemovePropertyStatement> {

    @Override
    public ValidationErrors validate(UpdateClassRemovePropertyStatement statement, Database database,
                                     SqlGeneratorChain sqlGeneratorChain) {

        ValidationErrors validationErrors = new ValidationErrors();
        validationErrors.checkRequiredField("className", statement.getClassName());
        validationErrors.checkRequiredField("propertyName", statement.getPropertyName());

        return validationErrors;
    }

    @Override
    public Sql[] generateSql(UpdateClassRemovePropertyStatement statement, Database database, SqlGeneratorChain sqlGeneratorChain) {

        OrientClass orientClass = new OrientClass(statement.getClassName());
        OrientProperty property = new OrientProperty(orientClass, statement.getPropertyName());

        return new Sql[] { new UpdateClassRemovePropertySql(property) };
    }
}
