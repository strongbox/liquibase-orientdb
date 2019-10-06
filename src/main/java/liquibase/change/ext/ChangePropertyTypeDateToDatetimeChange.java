package liquibase.change.ext;

import liquibase.database.Database;
import liquibase.statement.SqlStatement;
import org.unbrokendome.liquibase.orientdb.common.OrientSqlMethod;
import org.unbrokendome.liquibase.orientdb.statement.CreatePropertyStatement;
import org.unbrokendome.liquibase.orientdb.statement.DropPropertyStatement;
import org.unbrokendome.liquibase.orientdb.statement.UpdateClassRemovePropertyStatement;
import org.unbrokendome.liquibase.orientdb.statement.UpdateClassSetPropertyValueToAnotherPropertyValueStatement;
import org.unbrokendome.liquibase.serialization.Serializable;
import org.unbrokendome.liquibase.serialization.SerializedField;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.UUID;
import java.util.stream.Stream;

@Serializable(name = "changePropertyTypeDateToDatetime", description = "Change property type from date to datetime without losing data",
        namespace = OrientSerializationConstants.ORIENT_CHANGELOG_NAMESPACE)
public class ChangePropertyTypeDateToDatetimeChange extends AbstractMultiStatementOrientChange {

    private String className;
    private String propertyName;


    @SerializedField(description = "The name of the class that contains the property")
    @NotNull
    @Size(min = 1)
    public String getClassName() {
        return className;
    }


    public void setClassName(String className) {
        this.className = className;
    }


    @SerializedField(description = "The name of the property")
    @NotNull
    @Size(min = 1)
    public String getPropertyName() {
        return propertyName;
    }


    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }


    @Override
    public String getConfirmationMessage() {
        return "Property " + className + "." + propertyName + " type changed from 'date' into 'datetime'";
    }

    @Override
    protected Stream<SqlStatement> doGenerateStatements(Database database) {
        Stream.Builder<SqlStatement> streamBuilder = Stream.builder();

        final String tempPropertyName = propertyName + UUID.randomUUID().toString().replace("-", "_");

        streamBuilder.add(new DropPropertyStatement()
                .setClassName(className)
                .setPropertyName(propertyName));

        streamBuilder.add(new UpdateClassSetPropertyValueToAnotherPropertyValueStatement()
                .setClassName(className)
                .setTargetPropertyName(tempPropertyName)
                .setSourcePropertyName(propertyName)
                .setSqlMethod(OrientSqlMethod.AS_DATETIME));

        streamBuilder.add(new UpdateClassRemovePropertyStatement()
                .setClassName(className)
                .setPropertyName(propertyName));

        streamBuilder.add(new UpdateClassSetPropertyValueToAnotherPropertyValueStatement()
                .setClassName(className)
                .setTargetPropertyName(propertyName)
                .setSourcePropertyName(tempPropertyName));

        streamBuilder.add(new UpdateClassRemovePropertyStatement()
                .setClassName(className)
                .setPropertyName(tempPropertyName));

        streamBuilder.add(new CreatePropertyStatement()
                .setClassName(className)
                .setPropertyName(propertyName)
                .setType("datetime"));

        return streamBuilder.build();
    }
}
