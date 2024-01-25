package me.app.service.subject;

import me.app.service.observer.EmailObserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EmailConsoleSubject extends AbstractEmailSubject {

    private static final Logger logger = Logger.getLogger(EmailConsoleSubject.class.getSimpleName());

    private static final String EMAIL_VALID_EXPRESSION = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$";

    public EmailConsoleSubject(Set<EmailObserver> emailObservers) {
        super(emailObservers);
    }

    @Override
    protected List<String> supplyEmails() {
        return readConsole();
    }

    private List<String> readConsole() {
        final BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));
        final List<String> emails = new ArrayList<>();

        while (true) {
            final Optional<String> optionalEmail = readConsoleLine(consoleReader);
            if (optionalEmail.isPresent())
                optionalEmail
                        .filter(this::validEmail)
                        .ifPresent(emails::add);
            else break;

        }

        return emails;

    }

    private Optional<String> readConsoleLine(BufferedReader consoleReader) {
        String email;

        try {
            email = consoleReader.readLine();
            return email == null
                    ? Optional.empty()
                    : Optional.of(email);
        } catch (IOException e) {
            logger.log(Level.WARNING, "Can't read line:", e);
            return Optional.empty();
        }

    }

    private boolean validEmail(String email) {

        if (email.matches(EMAIL_VALID_EXPRESSION))
            return true;
        else {
            final String warningMessage = String.format("Email [%s] is not valid", email);
            logger.warning(warningMessage);
            return false;
        }

    }

}
