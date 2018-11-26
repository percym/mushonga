package systems.health263.dashboard.endpoint.config.app;

/*
 * Copyright © 2018 NewHealth263 (Pty) Ltd. All rights reserved.
 *
 * @author Munyaradzi Takayindisa
 *
 */

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jndi.JndiTemplate;
import systems.health263.dashboard.utility.enums.ClientDatabase;

import javax.naming.NamingException;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * Configuration file for DataSources that will be hot swapped when the application is running
 *
 * @author Munyaradzi Takayindisa
 */
@Configuration
public class PersistenceJNDIConfig {

    private static final String jndiUrl = "java:comp/env/jdbc/";

    @Bean(value = "dova", destroyMethod = "")
    public DataSource dova() throws NamingException {
        return (DataSource) new JndiTemplate().lookup(jndiUrl + ClientDatabase.DOVA.getDisplayName());
    }

    @Bean(value = "gonda", destroyMethod = "")
    public DataSource gonda() throws NamingException {
        return (DataSource) new JndiTemplate().lookup(jndiUrl + ClientDatabase.GONDA.getDisplayName());
    }

    @Bean(value = "test", destroyMethod = "")
    public DataSource test() throws NamingException {
        return (DataSource) new JndiTemplate().lookup(jndiUrl + ClientDatabase.TEST.getDisplayName());
    }

    @Bean(value = "dashboard", destroyMethod = "")
    public DataSource dashboard() throws NamingException {
        return (DataSource) new JndiTemplate().lookup(jndiUrl + ClientDatabase.DOVA.getDisplayName());
    }




    @Primary
    @Bean(destroyMethod = "")
    public DataSource datasource() throws NamingException {
        Map<Object, Object> targetDataSources = new HashMap<>();
        targetDataSources.put(ClientDatabase.TEST, test());
        targetDataSources.put(ClientDatabase.GONDA, gonda());
        targetDataSources.put(ClientDatabase.DOVA, dova());
        ClientDataSourceRouter clientRoutingDatasource = new ClientDataSourceRouter();
        clientRoutingDatasource.setTargetDataSources(targetDataSources);
        clientRoutingDatasource.setDefaultTargetDataSource(dova());
        return clientRoutingDatasource;
    }
}