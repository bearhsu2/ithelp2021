package idv.kuma.itehlp2021.scholarship.apply;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.time.LocalDate;


class CheckApplicationServiceTest {
    @Test
    void all_ok() {

        ScholarshipRepository fakeRepository = Mockito.mock(ScholarshipRepository.class);
        Mockito.when(fakeRepository.find(777L)).thenReturn(new Scholarship(LocalDate.MAX));


        LocalDate expected = LocalDate.of(2029, 12, 31);
        Mockito.mockStatic(LocalDate.class).when(LocalDate::now).thenReturn(expected);


        CheckApplicationService service = new CheckApplicationService(fakeRepository);

        Application application = new Application(777L);

        boolean actual = service.checkTime(application);

        Assertions.assertTrue(actual);



    }
}