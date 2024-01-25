package me.app.service.subject;

import me.app.service.observer.EmailObserver;
import me.app.service.subject.EmailConsoleSubject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;

@ExtendWith(MockitoExtension.class)
class EmailConsoleSubjectTest {

    @Mock
    private EmailObserver emailObserver;

    private EmailConsoleSubject emailConsoleSubject;

    @BeforeEach
    void init() {
        emailConsoleSubject = new EmailConsoleSubject(Set.of(emailObserver));
    }

    @Test
    void supplyEmails_whenReturnEmptyList() {
        System.setIn(new ByteArrayInputStream("".getBytes(StandardCharsets.UTF_8)));

        assertIterableEquals(Collections.emptyList(), emailConsoleSubject.supplyEmails());

    }

    @Test
    void supplyEmails_whenReturnNonEmptyList() {
        String testEmails = "joeblogs@saldoaaps.com\n" +
                "andrew.smith@gmail.com\n"+
                "test.user@testdomain";
        System.setIn(new ByteArrayInputStream(testEmails.getBytes(StandardCharsets.UTF_8)));

        List<String> expectedDomains = emailConsoleSubject.supplyEmails();

        assertEquals(2, expectedDomains.size());
        assertEquals("joeblogs@saldoaaps.com", expectedDomains.get(0));
        assertEquals("andrew.smith@gmail.com", expectedDomains.get(1));

    }

    @Test
    void supplyEmails_whenThrowException() {
        System.setIn(Mockito.mock(ByteArrayInputStream.class));

        assertIterableEquals(Collections.emptyList(), emailConsoleSubject.supplyEmails());

    }

}