package idv.kuma.itehlp2021.scholarship.notify;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;

import static org.mockito.ArgumentMatchers.any;

class SendResultEmailServiceSilentTest {
    @Test
    void silent_send() {

        Mailer mailer = Mockito.mock(Mailer.class);
        ExecutorService es = Mockito.mock(ExecutorService.class);

        SendResultEmailService service = new SendResultEmailService(mailer, es);

        service.silentSend(
                Arrays.asList(
                        new ScholarshipResult(),
                        new ScholarshipResult(),
                        new ScholarshipResult()
                )
        );

        ArgumentCaptor<Runnable> captor = ArgumentCaptor.forClass(Runnable.class);
        Mockito.verify(es, Mockito.times(3)).submit(captor.capture());

        List<Runnable> runnables = captor.getAllValues();

        runnables.forEach(
                Runnable::run
        );

        Mockito.verify(mailer, Mockito.times(3)).silentSend(any(ScholarshipResult.class));


    }
}