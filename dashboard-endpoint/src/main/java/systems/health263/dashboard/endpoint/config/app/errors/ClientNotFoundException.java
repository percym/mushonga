package systems.health263.dashboard.endpoint.config.app.errors;

/*
 *
 *
 * Copyright © 2018 NewHealth263 (Pty) Ltd. All rights reserved.
 *
 * @author Munyaradzi Takayindisa
 *
 */

/**
 * ClientNotFoundException
 *
 * @author Munyaradzi Takayindisa
 */
public class ClientNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 7108130310448047851L;

    public ClientNotFoundException(String message) {
        super(message);
    }

}
