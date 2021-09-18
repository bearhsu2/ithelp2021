package idv.kuma.itehlp2021.scholarship.command.usecase;

import idv.kuma.itehlp2021.scholarship.command.adapter.ApplicationForm;
import idv.kuma.itehlp2021.scholarship.command.adapter.ClientSideErrorException;
import idv.kuma.itehlp2021.student.register.StudentRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

class ApplyScholarshipServiceTest {

    @Test
    void student_not_exist() {

        StudentRepository studentRepository = Mockito.mock(StudentRepository.class);
        Mockito.when(studentRepository.find(12345L))
                .thenReturn(Optional.empty());


        ApplyScholarshipService applyScholarshipService
                = new ApplyScholarshipService(studentRepository);

        ApplicationForm form = new ApplicationForm(12345L, 55688L);

        ClientSideErrorException exception = Assertions.assertThrows(
                ClientSideErrorException.class,
                () -> applyScholarshipService.apply(form)
        );

        Assertions.assertEquals(987, exception.getCode());

    }
}
