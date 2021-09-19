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

    private StudentRepository studentRepository;
    private ApplyScholarshipService applyScholarshipService;
    private ClientSideErrorException clientSideException;

    @Test
    void all_ok() throws DataAccessErrorException, ClientSideErrorException {

        given_student_exists(12345L);

        when_apply_with_form_then_NO_error(application_form(12345L, 98765L));

    }

    private void given_student_exists(long studentId) {
        studentRepository = Mockito.mock(StudentRepository.class);
        Mockito.when(studentRepository.find(studentId))
                .thenReturn(Optional.of(new Student("Michael", "Jordan")));
    }

    private void when_apply_with_form_then_NO_error(ApplicationForm form) throws ClientSideErrorException, DataAccessErrorException {
        applyScholarshipService
                = new ApplyScholarshipService(studentRepository);

        applyScholarshipService.apply(form);
    }

    private ApplicationForm application_form(long studentId, long scholarshipId) {
        return new ApplicationForm(studentId, scholarshipId);
    }

    @Test
    void when_student_not_exist_then_987() {

        given_student_NOT_exists(12345L);

        when_apply_with_form_and_error_happens(application_form(12345L, 98765L));

        then_error_code_is(987);

    }

    private void given_student_NOT_exists(long studentId) {
        studentRepository = Mockito.mock(StudentRepository.class);
        Mockito.when(studentRepository.find(studentId))
                .thenReturn(Optional.empty());
    }

    private void when_apply_with_form_and_error_happens(ApplicationForm applicationForm) {
        applyScholarshipService = new ApplyScholarshipService(studentRepository);

        this.clientSideException = Assertions.assertThrows(ClientSideErrorException.class,
                () -> applyScholarshipService.apply(applicationForm));
    }

    private void then_error_code_is(int code) {
        Assertions.assertEquals(code, clientSideException.getCode());
    }
}
