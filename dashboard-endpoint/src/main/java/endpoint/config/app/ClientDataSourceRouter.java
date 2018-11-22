package endpoint.config.app;

/*
 *
 *
 * Copyright © 2018 NewHealth263 (Pty) Ltd. All rights reserved.
 *
 * @author Munyaradzi Takayindisa
 *
 */

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * Router class for DataSources hot swapping
 *
 * @author Munyaradzi Takayindisa
 */
public class ClientDataSourceRouter extends AbstractRoutingDataSource {

    @Override
    protected Object determineCurrentLookupKey() {
        return ClientDatabaseContextHolder.getJndi();
    }
}
