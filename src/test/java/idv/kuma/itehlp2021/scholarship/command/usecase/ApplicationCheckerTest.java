package idv.kuma.itehlp2021.scholarship.command.usecase;

import idv.kuma.itehlp2021.scholarship.command.adapter.ApplicationForm;
import idv.kuma.itehlp2021.scholarship.command.adapter.ScholarshipRepositoryImpl;
import idv.kuma.itehlp2021.scholarship.command.entity.Scholarship;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDate;


class ApplicationCheckerTest {
    @Test
    void all_ok() {

        ScholarshipRepositoryImpl fakeRepository = Mockito.mock(ScholarshipRepositoryImpl.class);
        Mockito.when(fakeRepository.find(55688L)).thenReturn(new Scholarship(LocalDate.MAX));


        LocalDate expected = LocalDate.of(2029, 12, 31);
        Mockito.mockStatic(LocalDate.class).when(LocalDate::now).thenReturn(expected);


        ApplicationChecker service = new ApplicationChecker(fakeRepository);

        ApplicationForm applicationForm = new ApplicationForm(777L, 55688L);

        boolean actual = service.checkTime(applicationForm);

        Assertions.assertTrue(actual);


    }
}