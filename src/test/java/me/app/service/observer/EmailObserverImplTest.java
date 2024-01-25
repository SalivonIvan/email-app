package me.app.service.observer;

import me.app.service.dto.DomainInfoDTO;
import me.app.service.producer.DomainInfoProducer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class EmailObserverImplTest {

    @Mock
    private DomainInfoProducer domainInfoProducer;

    @InjectMocks
    private EmailObserverImpl emailObserver;

    @Test
    void processEmails_whenEmptyEmails() {
        emailObserver.processEmails(List.of());

        Mockito.verifyNoInteractions(domainInfoProducer);
    }

    @Test
    void processEmails_whenNotEmptyEmails() {
        emailObserver.processEmails(List.of("joeblogs@saldoaaps.com", "andrew.smith@gmail.com",
                "jon.smith@gmail.com", "marry.smith@gmail.com"));

        Mockito.verify(domainInfoProducer).produce(Mockito.assertArg(actual -> {
            assertEquals(2, actual.size());
            assertEquals(new DomainInfoDTO("saldoaaps.com", 1), actual.get(0));
            assertEquals(new DomainInfoDTO("gmail.com", 3), actual.get(1));
        }));
    }
}