package me.app.service.observer;

import java.util.List;

public interface EmailObserver {

    void processEmails(List<String> emails);

}
