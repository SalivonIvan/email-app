package me.app.service;

import me.app.service.observer.EmailObserver;
import me.app.service.observer.EmailObserverImpl;
import me.app.service.producer.DomainInfoConsoleProducer;
import me.app.service.producer.DomainInfoProducer;
import me.app.service.subject.EmailConsoleSubject;
import me.app.service.subject.EmailSubject;

import java.util.Set;

public final class EmailFacade {

    private EmailFacade() {

    }

    public static void runApp() {
        final DomainInfoProducer domainInfoProducer = new DomainInfoConsoleProducer();
        final EmailObserver emailObserver = new EmailObserverImpl(domainInfoProducer);
        final EmailSubject emailSubject = new EmailConsoleSubject(Set.of(emailObserver));
        emailSubject.sendEmails();
    }

}
