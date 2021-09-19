package idv.kuma.itehlp2021.scholarship.command.usecase;

import idv.kuma.itehlp2021.scholarship.command.adapter.ApplicationForm;
import idv.kuma.itehlp2021.scholarship.command.adapter.ClientSideErrorException;
import idv.kuma.itehlp2021.scholarship.command.adapter.ServerSideErrorException;
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
    private ServerSideErrorException serverSideErrorException;

    @Test
    void all_ok() throws ServerSideErrorException, ClientSideErrorException, RepositoryAccessDataFailException {

        given_student_exists(12345L);

        when_apply_with_form_then_NO_error(application_form(12345L, 98765L));

    }

    private void given_student_exists(long studentId) throws RepositoryAccessDataFailException {
        studentRepository = Mockito.mock(StudentRepository.class);
        Mockito.when(studentRepository.find(studentId))
                .thenReturn(Optional.of(new Student("Michael", "Jordan")));
    }

    private void when_apply_with_form_then_NO_error(ApplicationForm form) throws ClientSideErrorException, ServerSideErrorException {
        applyScholarshipService
                = new ApplyScholarshipService(studentRepository);

        applyScholarshipService.apply(form);
    }

    private ApplicationForm application_form(long studentId, long scholarshipId) {
        return new ApplicationForm(studentId, scholarshipId);
    }

    @Test
    void when_student_not_exist_then_987() throws RepositoryAccessDataFailException {

        given_student_NOT_exists(12345L);

        when_apply_with_form_and_client_side_error_happens(application_form(12345L, 98765L));

        then_client_side_error_code_is(987);

    }

    private void given_student_NOT_exists(long studentId) throws RepositoryAccessDataFailException {
        studentRepository = Mockito.mock(StudentRepository.class);
        Mockito.when(studentRepository.find(studentId))
                .thenReturn(Optional.empty());
    }

    private void when_apply_with_form_and_client_side_error_happens(ApplicationForm applicationForm) {
        applyScholarshipService = new ApplyScholarshipService(studentRepository);

        this.clientSideException = Assertions.assertThrows(ClientSideErrorException.class,
                () -> applyScholarshipService.apply(applicationForm));
    }

    private void then_client_side_error_code_is(int code) {
        Assertions.assertEquals(code, clientSideException.getCode());
    }


    @Test
    void when_DB_fail_on_getting_student_then_666() throws RepositoryAccessDataFailException {

        assume_repository_would_fail_on_getting_student(12345L);

        when_apply_and_fail_on_server_side(application_form(12345L, 98765L));

        then_server_side_error_code_should_be(666);

    }

    private void assume_repository_would_fail_on_getting_student(long studentId) throws RepositoryAccessDataFailException {
        studentRepository = Mockito.mock(StudentRepository.class);
        Mockito.when(studentRepository.find(studentId))
                .thenThrow(new RepositoryAccessDataFailException());
    }

    private void when_apply_and_fail_on_server_side(ApplicationForm applicationForm) {
        applyScholarshipService = new ApplyScholarshipService(studentRepository);

        serverSideErrorException = Assertions.assertThrows(ServerSideErrorException.class,
                () -> applyScholarshipService.apply(applicationForm));
    }

    private void then_server_side_error_code_should_be(int code) {
        Assertions.assertEquals(code, serverSideErrorException.getCode());
    }

}
