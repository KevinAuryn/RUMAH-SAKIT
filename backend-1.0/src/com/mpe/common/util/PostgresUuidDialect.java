package com.mpe.common.util;

import java.sql.Types;

import org.hibernate.dialect.PostgreSQL9Dialect;
import org.hibernate.metamodel.spi.TypeContributions;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.type.PostgresUUIDType;
import org.hibernate.type.descriptor.sql.BinaryTypeDescriptor;
import org.hibernate.type.descriptor.sql.SqlTypeDescriptor;

public class PostgresUuidDialect extends PostgreSQL9Dialect{
	
	 public PostgresUuidDialect() {
	        super();

	        registerColumnType(Types.BLOB, "bytea");
	    }

	    @Override
	    public SqlTypeDescriptor remapSqlTypeDescriptor(SqlTypeDescriptor sqlTypeDescriptor) {
	        if (sqlTypeDescriptor.getSqlType() == Types.BLOB) {
	            return BinaryTypeDescriptor.INSTANCE;
	        }
	        return super.remapSqlTypeDescriptor(sqlTypeDescriptor);
	    }

	    @Override
	    public void contributeTypes(final TypeContributions typeContributions, final ServiceRegistry serviceRegistry) {
	        super.contributeTypes(typeContributions, serviceRegistry);
	        typeContributions.contributeType(new JHipsterPostgresUUIDType());
	    }

	    protected static class JHipsterPostgresUUIDType extends PostgresUUIDType {

	        @Override
	        public String getName() {
	            return "uuid-jhipster";
	        }

	        @Override
	        protected boolean registerUnderJavaType() {
	            return true;
	        }
	    }
}
