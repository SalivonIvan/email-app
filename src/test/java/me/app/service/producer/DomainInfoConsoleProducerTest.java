package me.app.service.producer;

import me.app.service.dto.DomainInfoDTO;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;

class DomainInfoConsoleProducerTest {

    private final DomainInfoConsoleProducer producer = new DomainInfoConsoleProducer();

    private ByteArrayOutputStream outputStream;

    @BeforeEach
    void init() {
        outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
    }

    @AfterEach
    void doPostAction() {
        outputStream.reset();
    }

    @Test
    void produce_whenNotEmptyStream() {

        producer.produce(List.of(new DomainInfoDTO("apache.com", 5)
                , new DomainInfoDTO("google.com", 10)
                , new DomainInfoDTO("facebook.com", 4)));

        Assertions.assertEquals("google.com 10" + System.lineSeparator() +
                "apache.com 5" + System.lineSeparator() +
                "facebook.com 4" + System.lineSeparator(), outputStream.toString());
    }

    @Test
    void produce_whenEmptyStream() {

        producer.produce(List.of());

        Assertions.assertEquals("", outputStream.toString());
    }


}