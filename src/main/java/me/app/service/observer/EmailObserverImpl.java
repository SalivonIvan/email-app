package me.app.service.observer;

import me.app.service.dto.DomainInfoDTO;
import me.app.service.producer.DomainInfoProducer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

public class EmailObserverImpl implements EmailObserver {

    private static final Logger logger = Logger.getLogger(EmailObserverImpl.class.getSimpleName());

    private static final char EMAIL_DELIMITER = '@';

    private final DomainInfoProducer domainInfoProducer;

    public EmailObserverImpl(DomainInfoProducer domainInfoProducer) {
        this.domainInfoProducer = domainInfoProducer;
    }

    @Override
    public void processEmails(List<String> emails) {

        if (!emails.isEmpty())
            process(emails);
        else
            logger.info("There had been no emails, the processing was not done");
    }

    private void process(List<String> emails) {

        final Map<String, Integer> domainToReappearance = new HashMap<>();

        emails.forEach(email -> {
            final String domainName = extractDomainName(email);
            domainToReappearance.put(domainName, domainToReappearance.getOrDefault(domainName, 0) + 1);
        });

        domainInfoProducer.produce(domainToReappearance
                .entrySet()
                .stream()
                .map(stringIntegerEntry -> new DomainInfoDTO(stringIntegerEntry.getKey(), stringIntegerEntry.getValue()))
                .toList()
        );

    }

    private String extractDomainName(String email) {
        return email.substring(email.indexOf(EMAIL_DELIMITER) + 1);
    }

}
