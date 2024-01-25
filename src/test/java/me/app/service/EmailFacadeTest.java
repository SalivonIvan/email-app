package me.app.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

class EmailFacadeTest {

    @Test
    void runApp() {
        final String testEmails = "joeblogs@saldoaaps.com\n" +
                "andrew.smith@gmail.com\n" +
                "test.user@testdomain";
        System.setIn(new ByteArrayInputStream(testEmails.getBytes(StandardCharsets.UTF_8)));

        final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        EmailFacade.runApp();

        Assertions.assertEquals("saldoaaps.com 1" + System.lineSeparator() +
                "gmail.com 1" + System.lineSeparator(), outputStream.toString());
    }
}