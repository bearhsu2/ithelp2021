package idv.kuma.itehlp2021.scholarship.command.usecase;

import idv.kuma.itehlp2021.scholarship.command.Application;
import idv.kuma.itehlp2021.scholarship.command.ApplicationRepository;
import idv.kuma.itehlp2021.scholarship.command.Scholarship;
import idv.kuma.itehlp2021.scholarship.command.ScholarshipRepository;
import idv.kuma.itehlp2021.scholarship.command.adapter.ApplicationForm;
import idv.kuma.itehlp2021.scholarship.command.adapter.ClientSideErrorException;
import idv.kuma.itehlp2021.scholarship.command.adapter.ServerSideErrorException;
import idv.kuma.itehlp2021.student.Student;
import idv.kuma.itehlp2021.student.register.StudentRepository;
import org.springframework.stereotype.Component;

@Component
public class ApplyScholarshipService {

    private final ScholarshipRepository scholarshipRepository;
    private final ApplicationRepository applicationRepository;
    StudentRepository studentRepository;


    public ApplyScholarshipService(StudentRepository studentRepository, ScholarshipRepository scholarshipRepository, ApplicationRepository applicationRepository) {
        this.studentRepository = studentRepository;
        this.scholarshipRepository = scholarshipRepository;
        this.applicationRepository = applicationRepository;
    }

    public void apply(ApplicationForm applicationForm) throws ClientSideErrorException, ServerSideErrorException {

        // 調閱學生資料
        Student student = findStudent(applicationForm);

        // 調閱獎學金規定的資料
        Scholarship scholarship = findScholarship(applicationForm);

        // 查驗是否符合資格
        checkDeadline(scholarship);

        // 查驗是否符合資格
        checkProgramIsPhD(scholarship, student);

        // 填寫正式申請書
        Application application = applicationForm.toApplication();

        // 存檔
        // 思考題：萬一 create 時，DB 資料已存在，該怎麼辦？
        createApplication(application);

    }

    private Student findStudent(ApplicationForm applicationForm) throws ClientSideErrorException, ServerSideErrorException {
        try {
            return studentRepository.find(applicationForm.getStudentId())
                    .orElseThrow(() -> new ClientSideErrorException("cannot find student", 987));
        } catch (RepositoryAccessDataFailException e) {
            throw new ServerSideErrorException("failed to retrieve student data", 666);
        }
    }

    private Scholarship findScholarship(ApplicationForm applicationForm) throws ClientSideErrorException, ServerSideErrorException {
        try {
            return scholarshipRepository.findOptional(applicationForm.getScholarshipId())
                    .orElseThrow(() -> new ClientSideErrorException("cannot find scholarship", 369));
        } catch (RepositoryAccessDataFailException e) {
            throw new ServerSideErrorException("failed to retrieve scholarship data", 666);
        }
    }

    private void checkDeadline(Scholarship scholarship) throws ClientSideErrorException {

        if (scholarship.checkDeadline()) {
            throw new ClientSideErrorException("application over time", 374);
        }
    }

    private void checkProgramIsPhD(Scholarship scholarship, Student student) throws ClientSideErrorException {
        if (!scholarship.checkQualification(student)) {
            throw new ClientSideErrorException("this scholarship is for PhD students only", 375);
        }
    }

    private void createApplication(Application application) throws ServerSideErrorException {
        try {
            this.applicationRepository.create(application);
        } catch (RepositoryAccessDataFailException e) {
            throw new ServerSideErrorException("failed to create application", 666);
        }
    }
}
