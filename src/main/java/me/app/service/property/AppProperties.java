package me.app.service.property;

public class AppProperties {

    public static final int MAX_DOMAIN_INFO_CONSOLE_ROWS =
            Integer.parseInt(System.getProperty("max.domain.info.console.rows", "10"));

    private AppProperties() {
    }

}
