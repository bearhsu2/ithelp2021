package idv.kuma.itehlp2021.scholarship.command.usecase;

import idv.kuma.itehlp2021.scholarship.command.adapter.ApplicationForm;
import idv.kuma.itehlp2021.scholarship.command.adapter.ClientSideErrorException;
import idv.kuma.itehlp2021.scholarship.command.adapter.DataAccessErrorException;
import idv.kuma.itehlp2021.student.Student;
import idv.kuma.itehlp2021.student.register.StudentRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

class ApplyScholarshipServiceTest {
    @Test
    void all_ok() throws DataAccessErrorException, ClientSideErrorException {


        StudentRepository studentRepository = Mockito.mock(StudentRepository.class);
        Mockito.when(studentRepository.find(12345L))
                .thenReturn(Optional.of(new Student("Michael", "Jordan")));


        ApplyScholarshipService applyScholarshipService
                = new ApplyScholarshipService(studentRepository);

        ApplicationForm applicationForm
                = new ApplicationForm(12345L, 98765L);

        applyScholarshipService.apply(applicationForm);

    }

    @Test
    void when_student_not_exist_then_987() {

        StudentRepository studentRepository = Mockito.mock(StudentRepository.class);
        Mockito.when(studentRepository.find(12345L))
                .thenReturn(Optional.empty());


        ApplyScholarshipService applyScholarshipService
                = new ApplyScholarshipService(studentRepository);

        ApplicationForm applicationForm
                = new ApplicationForm(12345L, 98765L);

        ClientSideErrorException actualException = Assertions.assertThrows(ClientSideErrorException.class,
                () -> applyScholarshipService.apply(applicationForm));

        Assertions.assertEquals(987, actualException.getCode());

    }
}
