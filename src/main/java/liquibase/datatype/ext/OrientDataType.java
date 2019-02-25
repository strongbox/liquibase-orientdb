package liquibase.datatype.ext;

import org.unbrokendome.liquibase.orientdb.common.OrientPropertyType;

import liquibase.change.core.LoadDataChange.LOAD_DATA_TYPE;
import liquibase.datatype.LiquibaseDataType;


public abstract class OrientDataType extends LiquibaseDataType {

	public abstract OrientPropertyType toOrientPropertyType();
	
    @Override
    public LOAD_DATA_TYPE getLoadTypeName()
    {
        return null;
    }

}
