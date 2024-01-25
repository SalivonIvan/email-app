package me.app.service.subject;

import me.app.service.observer.EmailObserver;

import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

public abstract class AbstractEmailSubject implements EmailSubject {

    private static final Logger logger = Logger.getLogger(AbstractEmailSubject.class.getSimpleName());

    private Set<EmailObserver> emailObservers;

    protected AbstractEmailSubject(Set<EmailObserver> emailObservers) {
        initEmailObservers(emailObservers);
    }

    protected abstract List<String> supplyEmails();

    @Override
    public void sendEmails() {

        final List<String> emails = supplyEmails();

        emailObservers
                .forEach(emailObserver -> emailObserver.processEmails(emails));

    }

    private void initEmailObservers(Set<EmailObserver> emailObservers) {
        if (emailObservers == null || emailObservers.isEmpty()) {
            final String message = String.format("Any %s implementation will not be applied to process email list!"
                    , EmailObserver.class.getSimpleName());
            logger.warning(message);
            this.emailObservers = Set.of();
        } else this.emailObservers = emailObservers;
    }

}
