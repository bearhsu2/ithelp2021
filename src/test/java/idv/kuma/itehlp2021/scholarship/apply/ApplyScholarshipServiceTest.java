package idv.kuma.itehlp2021.scholarship.apply;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.*;

class ApplyScholarshipServiceTest {
    @Test
    void check_ok_then_create() {

        Application application = new Application(777L);

        CheckApplicationService checker = mock(CheckApplicationService.class);
        when(checker.checkTime(application)).thenReturn(true);

        ApplicationRepository repository = mock(ApplicationRepository.class);

        new ApplyScholarshipService(checker, repository).apply(application);

        verify(repository, times(1)).create(application);


    }
}