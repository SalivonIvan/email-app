package me.app.service.subject;

import me.app.service.observer.EmailObserver;
import me.app.service.subject.AbstractEmailSubject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Set;

@ExtendWith(MockitoExtension.class)
class AbstractEmailSubjectTest {

    @Mock
    private EmailObserver emailObserver;

    private AbstractEmailSubject abstractEmailSubjectWithObservers;

    private AbstractEmailSubject abstractEmailSubjectWithoutObservers;

    @BeforeEach
    void init() {
        initAbstractEmailSubjectWithObservers();
    }

    @Test
    void sendEmails_whenObserverExist() {
        abstractEmailSubjectWithObservers.sendEmails();
        Mockito.verify(emailObserver).processEmails(Mockito.anyList());
    }

    @Test
    void sendEmails_whenObserverListIsEmpty() {
        initAbstractEmailSubjectWithoutObservers(Set.of());

        abstractEmailSubjectWithoutObservers.sendEmails();
        Mockito.verifyNoInteractions(emailObserver);
    }

    @Test
    void sendEmails_whenObserverListIsNull() {
        initAbstractEmailSubjectWithoutObservers(null);

        abstractEmailSubjectWithoutObservers.sendEmails();
        Mockito.verifyNoInteractions(emailObserver);
    }

    private void initAbstractEmailSubjectWithObservers() {
        abstractEmailSubjectWithObservers = new AbstractEmailSubject(Set.of(emailObserver)) {
            @Override
            protected List<String> supplyEmails() {
                return List.of();
            }
        };
    }

    private void initAbstractEmailSubjectWithoutObservers(Set<EmailObserver> observers) {
        abstractEmailSubjectWithoutObservers = new AbstractEmailSubject(observers) {
            @Override
            protected List<String> supplyEmails() {
                return List.of();
            }
        };
    }

}