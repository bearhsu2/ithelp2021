package idv.kuma.itehlp2021.scholarship.command.usecase;

import idv.kuma.itehlp2021.scholarship.command.adapter.ApplicationForm;
import idv.kuma.itehlp2021.scholarship.command.adapter.ClientSideErrorException;
import idv.kuma.itehlp2021.scholarship.command.adapter.ServerSideErrorException;
import idv.kuma.itehlp2021.scholarship.command.entity.Application;
import idv.kuma.itehlp2021.scholarship.command.entity.Scholarship;
import idv.kuma.itehlp2021.student.Student;
import idv.kuma.itehlp2021.student.register.StudentRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;


class ApplyScholarshipServiceTest {


    private static final LocalDate july31 = LocalDate.of(2021, 7, 31);
    private static final LocalDate august01 = LocalDate.of(2021, 8, 1);

    private static MockedStatic<LocalDate> mocked;

    private StudentRepository studentRepository;
    private ApplyScholarshipService applyScholarshipService;
    private ClientSideErrorException clientSideException;
    private ServerSideErrorException serverSideErrorException;
    private ScholarshipRepository scholarshipRepository;
    private ApplicationRepository applicationRepository;

    @BeforeAll
    static void beforeAll() {
        mocked = Mockito.mockStatic(LocalDate.class);
    }

    @BeforeEach
    void setUp() {
        studentRepository = Mockito.mock(StudentRepository.class);
        scholarshipRepository = Mockito.mock(ScholarshipRepository.class);
        applicationRepository = Mockito.mock(ApplicationRepository.class);
        applyScholarshipService = new ApplyScholarshipService(studentRepository, scholarshipRepository, applicationRepository);
    }

    @Test
    void all_ok() throws ServerSideErrorException, ClientSideErrorException, RepositoryAccessDataFailException {

        given_student_exists(12345L, "PhD");

        given_scholarship_exists(98765L, scholarship());

        given_today_is(july31);

        when_apply_with_form_then_NO_error(application_form(12345L, 98765L));


    }

    private void given_student_exists(long studentId, String program) throws RepositoryAccessDataFailException {
        Mockito.when(studentRepository.find(studentId))
                .thenReturn(Optional.of(new Student("Michael", "Jordan", program)));
    }

    private void given_scholarship_exists(long scholarshipId, Scholarship scholarship) throws RepositoryAccessDataFailException {
        Mockito.when(scholarshipRepository.findOptional(scholarshipId))
                .thenReturn(Optional.of(scholarship));
    }

    private Scholarship scholarship() {
        return new Scholarship(LocalDate.MAX);
    }

    private void given_today_is(LocalDate today) {

        mocked.when(LocalDate::now).thenReturn(today);

    }

    private void when_apply_with_form_then_NO_error(ApplicationForm form) throws ClientSideErrorException, ServerSideErrorException {

        applyScholarshipService.apply(form);
    }

    private ApplicationForm application_form(long studentId, long scholarshipId) {
        return new ApplicationForm(studentId, scholarshipId);
    }

    @Test
    void when_student_not_exist_then_987() throws RepositoryAccessDataFailException {


        given_student_NOT_exists(12345L);
        given_scholarship_exists(98765L, scholarship());

        when_apply_with_form_and_client_side_error_happens(application_form(12345L, 98765L));

        then_client_side_error_code_is(987);

    }

    private void given_student_NOT_exists(long studentId) throws RepositoryAccessDataFailException {
        Mockito.when(studentRepository.find(studentId))
                .thenReturn(Optional.empty());
    }

    private void when_apply_with_form_and_client_side_error_happens(ApplicationForm applicationForm) {

        this.clientSideException = Assertions.assertThrows(ClientSideErrorException.class,
                () -> applyScholarshipService.apply(applicationForm));
    }

    private void then_client_side_error_code_is(int code) {
        Assertions.assertEquals(code, clientSideException.getCode());
    }

    @Test
    void when_DB_fail_on_getting_student_then_666() throws RepositoryAccessDataFailException {

        given_scholarship_exists(98765L, scholarship());
        assume_repository_would_fail_on_getting_student(12345L);

        when_apply_and_fail_on_server_side(application_form(12345L, 98765L));

        then_server_side_error_code_should_be(666);

    }

    private void assume_repository_would_fail_on_getting_student(long studentId) throws RepositoryAccessDataFailException {
        Mockito.when(studentRepository.find(studentId))
                .thenThrow(new RepositoryAccessDataFailException());
    }

    private void when_apply_and_fail_on_server_side(ApplicationForm applicationForm) {

        serverSideErrorException = Assertions.assertThrows(ServerSideErrorException.class,
                () -> applyScholarshipService.apply(applicationForm));
    }

    private void then_server_side_error_code_should_be(int code) {
        Assertions.assertEquals(code, serverSideErrorException.getCode());
    }

    @Test
    void when_scholarship_not_exist_then_369() throws RepositoryAccessDataFailException {

        given_student_exists(12345L);
        given_scholarship_NOT_exists(98765L);

        when_apply_with_form_and_client_side_error_happens(application_form(12345L, 98765L));

        then_client_side_error_code_is(369);

    }

    private void given_student_exists(long studentId) throws RepositoryAccessDataFailException {
        Mockito.when(studentRepository.find(studentId))
                .thenReturn(Optional.of(new Student("Michael", "Jordan")));
    }

    private void given_scholarship_NOT_exists(long scholarshipId) throws RepositoryAccessDataFailException {
        Mockito.when(scholarshipRepository.findOptional(scholarshipId))
                .thenReturn(Optional.empty());
    }

    @Test
    void when_DB_fail_on_getting_scholarship_then_666() throws RepositoryAccessDataFailException {

        given_student_exists(12345L);

        assume_DB_would_fail_on_getting_scholarship_data(98765L);

        when_apply_and_fail_on_server_side(application_form(12345L, 98765L));

        then_server_side_error_code_should_be(666);

    }

    private void assume_DB_would_fail_on_getting_scholarship_data(long scholarshipId) throws RepositoryAccessDataFailException {
        Mockito.when(scholarshipRepository.findOptional(scholarshipId))
                .thenThrow(new RepositoryAccessDataFailException());
    }

    @Test
    void when_overtime_then_374() throws RepositoryAccessDataFailException {

        given_student_exists(12345L);
        given_scholarship_exists(98765L, scholarship(july31));
        given_today_is(august01);

        when_apply_with_form_and_client_side_error_happens(application_form(12345L, 98765L));

        then_client_side_error_code_is(374);

    }

    private Scholarship scholarship(LocalDate deadline) {
        return new Scholarship(deadline);
    }

    @Test
    void when_disqualified_then_375() throws RepositoryAccessDataFailException {

        given_student_exists(12345L);

        given_scholarship_exists(98765L, scholarship(july31));

        given_today_is(july31);

        when_apply_with_form_and_client_side_error_happens(application_form(12345L, 98765L));

        then_client_side_error_code_is(375);

    }

    @Test
    void when_DB_fail_on_writing_application_to_DB_then_666() throws RepositoryAccessDataFailException {

        given_student_exists(12345L, "PhD");

        given_scholarship_exists(98765L, scholarship());

        given_today_is(july31);

        assume_DB_would_fail_on_creating_application_data();

        when_apply_and_fail_on_server_side(application_form(12345L, 98765L));

        then_server_side_error_code_should_be(666);

    }

    private void assume_DB_would_fail_on_creating_application_data() throws RepositoryAccessDataFailException {
        Mockito.doThrow(new RepositoryAccessDataFailException())
                .when(applicationRepository).create(any(Application.class));
    }


}
