package idv.kuma.itehlp2021.scholarship.command.usecase;

import idv.kuma.itehlp2021.scholarship.command.adapter.ApplicationForm;
import idv.kuma.itehlp2021.scholarship.command.adapter.ClientSideErrorException;
import idv.kuma.itehlp2021.student.register.StudentRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

class ApplyScholarshipServiceTest {

    private StudentRepository studentRepository;
    private ApplyScholarshipService applyScholarshipService;
    private ClientSideErrorException exception;

    @Test
    void student_not_exist() {

        given_student_not_exists(12345L);

        create_service();

        when_run_then_fail(application_form(12345L, 55688L));

        and_error_code_is(987);

    }

    private ApplicationForm application_form(long studentId, long scholarshipId) {
        return new ApplicationForm(studentId, scholarshipId);
    }

    private void and_error_code_is(int expected) {
        Assertions.assertEquals(expected, exception.getCode());
    }

    private void when_run_then_fail(ApplicationForm form) {
        exception = Assertions.assertThrows(
                ClientSideErrorException.class,
                () -> applyScholarshipService.apply(form)
        );
    }

    private void create_service() {
        applyScholarshipService = new ApplyScholarshipService(studentRepository);
    }

    private void given_student_not_exists(long studentId) {
        studentRepository = Mockito.mock(StudentRepository.class);
        Mockito.when(studentRepository.find(studentId))
                .thenReturn(Optional.empty());
    }
}
