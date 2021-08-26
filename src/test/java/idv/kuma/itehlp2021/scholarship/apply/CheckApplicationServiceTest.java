package idv.kuma.itehlp2021.scholarship.apply;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDate;

class CheckApplicationServiceTest {
    @Test
    void all_ok() {

        ScholarshipRepository fakeRepository = Mockito.mock(ScholarshipRepository.class);
        Mockito.when(fakeRepository.find(777L)).thenReturn(new Scholarship(LocalDate.MAX));



    }
}