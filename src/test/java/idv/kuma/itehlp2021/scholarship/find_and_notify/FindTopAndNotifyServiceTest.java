package idv.kuma.itehlp2021.scholarship.find_and_notify;

import idv.kuma.itehlp2021.scholarship.Transcript;
import idv.kuma.itehlp2021.scholarship.notify.SendResultEmailService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;

class FindTopAndNotifyServiceTest {

    @Test
    void one_student() {

        TranscriptRepository repository = Mockito.mock(TranscriptRepository.class);
        Mockito.when(repository.findHighestScore("2021-fall", 9527L))
                .thenReturn(Arrays.asList(
                        new Transcript(55688L)
                ));

        SendResultEmailService emailService = Mockito.mock(SendResultEmailService.class);

        FindTopAndNotifyService service = new FindTopAndNotifyService(repository, emailService);

        service.execute("2021-fall", 9527L);

        Mockito.verify(emailService, Mockito.times(1))
                .send(55688L, "Congratulations! You've got Scholarship");


    }
}