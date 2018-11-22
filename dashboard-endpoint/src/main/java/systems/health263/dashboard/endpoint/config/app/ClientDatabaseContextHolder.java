package systems.health263.dashboard.endpoint.config.app;

/*
 *
 *
 * Copyright © 2018 NewHealth263 (Pty) Ltd. All rights reserved.
 *
 * @author Munyaradzi Takayindisa
 *
 */

import org.springframework.util.Assert;
import systems.health263.dashboard.utility.enums.ClientDatabase;

public class ClientDatabaseContextHolder {

    private ClientDatabaseContextHolder() {

    }

    private static ThreadLocal<ClientDatabase> context
            = new ThreadLocal<>();

    public static void setJndi(ClientDatabase clientDatabase) {
        Assert.notNull(clientDatabase, "clientDatabase cannot be null");
        context.set(clientDatabase);
    }

    public static ClientDatabase getJndi() {
        return context.get();
    }

    public static void clear() {
        context.remove();
    }
}