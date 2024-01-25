package me.app;

import me.app.service.EmailFacade;

import java.util.logging.Logger;

public final class EmailApp {

    private static final Logger logger = Logger.getLogger(EmailApp.class.getSimpleName());

    public static void main(String[] args) {

        logger.info("Get started app!");
        EmailFacade.runApp();
        logger.info("Get finished app!");

    }

}
