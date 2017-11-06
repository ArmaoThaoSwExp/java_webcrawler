package com.armao.app;

/*
 * Enumeration for database types.
 */
enum DbTypes {
    SQLLITE
}

/*
 * Handle database
 */
public class DbFactory {
    private DbInterface db;
    static class UnsupportedDbTypesException extends Exception {
        public UnsupportedDbTypesException(String message) {
            super(message);
        }
    }

    public DbFactory(String url, DbTypes dbType, boolean deletePrevDB) throws UnsupportedDbTypesException{
        if (dbType == DbTypes.SQLLITE) {
            this.db = new SqlLiteDb(url, deletePrevDB);
        }
        else {
            throw new UnsupportedDbTypesException(
                    String.format("Database type %d is not supported. Refer to DbTypes for supported DBs.", dbType));
        }
    }

    public void deleteDb(String url) {
        /* Delete database */
    }
}
