package es.caib.seycon.ng.model;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import java.util.Set;

import org.hibernate.HibernateException;
import org.hibernate.LockMode;
import org.hibernate.MappingException;
import org.hibernate.dialect.Dialect;
import org.hibernate.dialect.DialectFactory;
import org.hibernate.dialect.MySQL5InnoDBDialect;
import org.hibernate.dialect.MySQLDialect;
import org.hibernate.dialect.Oracle10gDialect;
import org.hibernate.dialect.SQLServerDialect;
import org.hibernate.dialect.lock.LockingStrategy;
import org.hibernate.exception.SQLExceptionConverter;
import org.hibernate.exception.ViolatedConstraintNameExtracter;
import org.hibernate.persister.entity.Lockable;
import org.hibernate.sql.CaseFragment;
import org.hibernate.sql.JoinFragment;

import es.caib.seycon.ng.config.Config;

public class CustomDialect extends Dialect {
    Dialect proxyDialect;
    
    public static Class dialectClass = null; 

    public CustomDialect() {
        super();
        if (dialectClass != null)
        {
        	try
			{
				proxyDialect = (Dialect) dialectClass.newInstance();
			}
			catch (InstantiationException e)
			{
                throw new RuntimeException("Unable to get dialect for class "+dialectClass.getName(), e); //$NON-NLS-1$
			}
			catch (IllegalAccessException e)
			{
                throw new RuntimeException("Unable to get dialect for class "+dialectClass.getName(), e); //$NON-NLS-1$
			}
        }
        else
        {
	        String type = System.getProperty("dbDriverString"); //$NON-NLS-1$
	        if (type == null) {
	            try {
	                String driver = Config.getConfig().getDB();
	                driver = driver.substring(driver.indexOf(":")+1); //$NON-NLS-1$
	                type = driver.substring(0, driver.indexOf(":")); //$NON-NLS-1$
	            } catch (Exception e) {
	                throw new RuntimeException("Unable to get dialect for database", e); //$NON-NLS-1$
	            }
	        }
	        if ("mysql".equals(type))  //$NON-NLS-1$
	        {
	            proxyDialect = new MySQL5InnoDBDialect();
	        } else if ("oracle".equals (type)) { //$NON-NLS-1$
	            proxyDialect = new Oracle10gDialect();
	        } else if ("sqlserver".equals(type)) { //$NON-NLS-1$
	        	proxyDialect = new SQLServerDialect();
	        } else {
	            throw new RuntimeException("Unable to get dialect for database type ["+type+"]"); //$NON-NLS-1$ //$NON-NLS-2$
	        }
        }
    }

    public String getTypeName(int code) throws HibernateException {
        return proxyDialect.getTypeName(code);
    }

    public String getTypeName(int code, int length, int precision, int scale)
            throws HibernateException {
        return proxyDialect.getTypeName(code, length, precision, scale);
    }

    public String getCastTypeName(int code) {
        return proxyDialect.getCastTypeName(code);
    }

    public String getHibernateTypeName(int code) throws HibernateException {
        return proxyDialect.getHibernateTypeName(code);
    }

    public String getHibernateTypeName(int code, int length, int precision, int scale)
            throws HibernateException {
        return proxyDialect.getHibernateTypeName(code, length, precision, scale);
    }

    public Set getKeywords() {
        return proxyDialect.getKeywords();
    }

    public Class getNativeIdentifierGeneratorClass() {
        return es.caib.seycon.ng.model.identity.IdentityGenerator.class;
    }

    public boolean supportsIdentityColumns() {
        return proxyDialect.supportsIdentityColumns();
    }

    public boolean supportsInsertSelectIdentity() {
        return proxyDialect.supportsInsertSelectIdentity();
    }

    public boolean hasDataTypeInIdentityColumn() {
        return proxyDialect.hasDataTypeInIdentityColumn();
    }

    public String appendIdentitySelectToInsert(String insertString) {
        return proxyDialect.appendIdentitySelectToInsert(insertString);
    }

    public String getIdentitySelectString(String table, String column, int type)
            throws MappingException {
        return proxyDialect.getIdentitySelectString(table, column, type);
    }

    public String getIdentityColumnString(int type) throws MappingException {
        return proxyDialect.getIdentityColumnString(type);
    }

    public String getIdentityInsertString() {
        return proxyDialect.getIdentityInsertString();
    }

    public boolean supportsSequences() {
        return proxyDialect.supportsSequences();
    }

    public boolean supportsPooledSequences() {
        return proxyDialect.supportsPooledSequences();
    }

    public String getSequenceNextValString(String sequenceName) throws MappingException {
        return proxyDialect.getSequenceNextValString(sequenceName);
    }

    public String getSelectSequenceNextValString(String sequenceName) throws MappingException {
        return proxyDialect.getSelectSequenceNextValString(sequenceName);
    }

    public String[] getCreateSequenceStrings(String sequenceName) throws MappingException {
        return proxyDialect.getCreateSequenceStrings(sequenceName);
    }

    public String[] getCreateSequenceStrings(String sequenceName, int initialValue,
            int incrementSize) throws MappingException {
        return proxyDialect.getCreateSequenceStrings(sequenceName, initialValue, incrementSize);
    }

    public String[] getDropSequenceStrings(String sequenceName) throws MappingException {
        return proxyDialect.getDropSequenceStrings(sequenceName);
    }

    public String getQuerySequencesString() {
        return proxyDialect.getQuerySequencesString();
    }

    public String getSelectGUIDString() {
        return proxyDialect.getSelectGUIDString();
    }

    public boolean supportsLimit() {
        return proxyDialect.supportsLimit();
    }

    public boolean supportsLimitOffset() {
        return proxyDialect.supportsLimitOffset();
    }

    public boolean supportsVariableLimit() {
        return proxyDialect.supportsVariableLimit();
    }

    public boolean bindLimitParametersInReverseOrder() {
        return proxyDialect.bindLimitParametersInReverseOrder();
    }

    public boolean bindLimitParametersFirst() {
        return proxyDialect.bindLimitParametersFirst();
    }

    public boolean useMaxForLimit() {
        return proxyDialect.useMaxForLimit();
    }

    public String getLimitString(String query, int offset, int limit) {
        return proxyDialect.getLimitString(query, offset, limit);
    }

    public LockingStrategy getLockingStrategy(Lockable lockable, LockMode lockMode) {
        return proxyDialect.getLockingStrategy(lockable, lockMode);
    }

    public String getForUpdateString(LockMode lockMode) {
        return proxyDialect.getForUpdateString(lockMode);
    }

    public String getForUpdateString() {
        return proxyDialect.getForUpdateString();
    }

    public boolean forUpdateOfColumns() {
        return proxyDialect.forUpdateOfColumns();
    }

    public boolean supportsOuterJoinForUpdate() {
        return proxyDialect.supportsOuterJoinForUpdate();
    }

    public String getForUpdateString(String aliases) {
        return proxyDialect.getForUpdateString(aliases);
    }

    public String getForUpdateNowaitString() {
        return proxyDialect.getForUpdateNowaitString();
    }

    public String getForUpdateNowaitString(String aliases) {
        return proxyDialect.getForUpdateNowaitString(aliases);
    }

    public String appendLockHint(LockMode mode, String tableName) {
        return proxyDialect.appendLockHint(mode, tableName);
    }

    public String applyLocksToSql(String sql, Map aliasedLockModes, Map keyColumnNames) {
        return proxyDialect.applyLocksToSql(sql, aliasedLockModes, keyColumnNames);
    }

    public String getCreateTableString() {
        return proxyDialect.getCreateTableString();
    }

    public String getCreateMultisetTableString() {
        return proxyDialect.getCreateMultisetTableString();
    }

    public boolean supportsTemporaryTables() {
        return proxyDialect.supportsTemporaryTables();
    }

    public String generateTemporaryTableName(String baseTableName) {
        return proxyDialect.generateTemporaryTableName(baseTableName);
    }

    public String getCreateTemporaryTableString() {
        return proxyDialect.getCreateTemporaryTableString();
    }

    public String getCreateTemporaryTablePostfix() {
        return proxyDialect.getCreateTemporaryTablePostfix();
    }

    public Boolean performTemporaryTableDDLInIsolation() {
        return proxyDialect.performTemporaryTableDDLInIsolation();
    }

    public boolean dropTemporaryTableAfterUse() {
        return proxyDialect.dropTemporaryTableAfterUse();
    }

    public int registerResultSetOutParameter(CallableStatement statement, int position)
            throws SQLException {
        return proxyDialect.registerResultSetOutParameter(statement, position);
    }

    public ResultSet getResultSet(CallableStatement statement) throws SQLException {
        return proxyDialect.getResultSet(statement);
    }

    public boolean supportsCurrentTimestampSelection() {
        return proxyDialect.supportsCurrentTimestampSelection();
    }

    public boolean isCurrentTimestampSelectStringCallable() {
        return proxyDialect.isCurrentTimestampSelectStringCallable();
    }

    public String getCurrentTimestampSelectString() {
        return proxyDialect.getCurrentTimestampSelectString();
    }

    public String getCurrentTimestampSQLFunctionName() {
        return proxyDialect.getCurrentTimestampSQLFunctionName();
    }

    public SQLExceptionConverter buildSQLExceptionConverter() {
        return proxyDialect.buildSQLExceptionConverter();
    }

    public ViolatedConstraintNameExtracter getViolatedConstraintNameExtracter() {
        return proxyDialect.getViolatedConstraintNameExtracter();
    }

    public String getSelectClauseNullString(int sqlType) {
        return proxyDialect.getSelectClauseNullString(sqlType);
    }

    public boolean supportsUnionAll() {
        return proxyDialect.supportsUnionAll();
    }

    public JoinFragment createOuterJoinFragment() {
        return proxyDialect.createOuterJoinFragment();
    }

    public CaseFragment createCaseFragment() {
        return proxyDialect.createCaseFragment();
    }

    public String getNoColumnsInsertString() {
        return proxyDialect.getNoColumnsInsertString();
    }

    public String getLowercaseFunction() {
        return proxyDialect.getLowercaseFunction();
    }

    public String transformSelectString(String select) {
        return proxyDialect.transformSelectString(select);
    }

    public int getMaxAliasLength() {
        return proxyDialect.getMaxAliasLength();
    }

    public String toBooleanValueString(boolean bool) {
        return proxyDialect.toBooleanValueString(bool);
    }

    public char openQuote() {
        return proxyDialect.openQuote();
    }

    public char closeQuote() {
        return proxyDialect.closeQuote();
    }

    public boolean hasAlterTable() {
        return proxyDialect.hasAlterTable();
    }

    public boolean dropConstraints() {
        return proxyDialect.dropConstraints();
    }

    public boolean qualifyIndexName() {
        return proxyDialect.qualifyIndexName();
    }

    public boolean supportsUnique() {
        return proxyDialect.supportsUnique();
    }

    public boolean supportsUniqueConstraintInCreateAlterTable() {
        return proxyDialect.supportsUniqueConstraintInCreateAlterTable();
    }

    public String getAddColumnString() {
        return proxyDialect.getAddColumnString();
    }

    public String getDropForeignKeyString() {
        return proxyDialect.getDropForeignKeyString();
    }

    public String getTableTypeString() {
        return proxyDialect.getTableTypeString();
    }

    public String getAddForeignKeyConstraintString(String constraintName, String[] foreignKey,
            String referencedTable, String[] primaryKey, boolean referencesPrimaryKey) {
        return proxyDialect.getAddForeignKeyConstraintString(constraintName, foreignKey,
                referencedTable, primaryKey, referencesPrimaryKey);
    }

    public String getAddPrimaryKeyConstraintString(String constraintName) {
        return proxyDialect.getAddPrimaryKeyConstraintString(constraintName);
    }

    public boolean hasSelfReferentialForeignKeyBug() {
        return proxyDialect.hasSelfReferentialForeignKeyBug();
    }

    public String getNullColumnString() {
        return proxyDialect.getNullColumnString();
    }

    public boolean supportsCommentOn() {
        return proxyDialect.supportsCommentOn();
    }

    public String getTableComment(String comment) {
        return proxyDialect.getTableComment(comment);
    }

    public String getColumnComment(String comment) {
        return proxyDialect.getColumnComment(comment);
    }

    public boolean supportsIfExistsBeforeTableName() {
        return proxyDialect.supportsIfExistsBeforeTableName();
    }

    public boolean supportsIfExistsAfterTableName() {
        return proxyDialect.supportsIfExistsAfterTableName();
    }

    public boolean supportsColumnCheck() {
        return proxyDialect.supportsColumnCheck();
    }

    public boolean supportsTableCheck() {
        return proxyDialect.supportsTableCheck();
    }

    public boolean supportsCascadeDelete() {
        return proxyDialect.supportsCascadeDelete();
    }

    public boolean supportsNotNullUnique() {
        return proxyDialect.supportsNotNullUnique();
    }

    public String getCascadeConstraintsString() {
        return proxyDialect.getCascadeConstraintsString();
    }

    public boolean supportsEmptyInList() {
        return proxyDialect.supportsEmptyInList();
    }

    public boolean areStringComparisonsCaseInsensitive() {
        return proxyDialect.areStringComparisonsCaseInsensitive();
    }

    public boolean supportsRowValueConstructorSyntax() {
        return proxyDialect.supportsRowValueConstructorSyntax();
    }

    public boolean supportsRowValueConstructorSyntaxInInList() {
        return proxyDialect.supportsRowValueConstructorSyntaxInInList();
    }

    public boolean useInputStreamToInsertBlob() {
        return proxyDialect.useInputStreamToInsertBlob();
    }

    public boolean supportsParametersInInsertSelect() {
        return proxyDialect.supportsParametersInInsertSelect();
    }

    public boolean supportsResultSetPositionQueryMethodsOnForwardOnlyCursor() {
        return proxyDialect.supportsResultSetPositionQueryMethodsOnForwardOnlyCursor();
    }

    public boolean supportsCircularCascadeDeleteConstraints() {
        return proxyDialect.supportsCircularCascadeDeleteConstraints();
    }

    public boolean supportsSubselectAsInPredicateLHS() {
        return proxyDialect.supportsSubselectAsInPredicateLHS();
    }

    public boolean supportsExpectedLobUsagePattern() {
        return proxyDialect.supportsExpectedLobUsagePattern();
    }

    public boolean supportsLobValueChangePropogation() {
        return proxyDialect.supportsLobValueChangePropogation();
    }

    public boolean supportsUnboundedLobLocatorMaterialization() {
        return proxyDialect.supportsUnboundedLobLocatorMaterialization();
    }

    public boolean supportsSubqueryOnMutatingTable() {
        return proxyDialect.supportsSubqueryOnMutatingTable();
    }

    public boolean supportsExistsInSelect() {
        return proxyDialect.supportsExistsInSelect();
    }

    public boolean doesReadCommittedCauseWritersToBlockReaders() {
        return proxyDialect.doesReadCommittedCauseWritersToBlockReaders();
    }

    public boolean doesRepeatableReadCauseReadersToBlockWriters() {
        return proxyDialect.doesRepeatableReadCauseReadersToBlockWriters();
    }

    public boolean supportsBindAsCallableArgument() {
        return proxyDialect.supportsBindAsCallableArgument();
    }
    
    
}
