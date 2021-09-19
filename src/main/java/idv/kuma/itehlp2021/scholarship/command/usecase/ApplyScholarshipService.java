package idv.kuma.itehlp2021.scholarship.command.usecase;

import idv.kuma.itehlp2021.scholarship.command.adapter.ApplicationForm;
import idv.kuma.itehlp2021.scholarship.command.adapter.ClientSideErrorException;
import idv.kuma.itehlp2021.scholarship.command.adapter.ServerSideErrorException;
import idv.kuma.itehlp2021.student.register.StudentRepository;
import org.springframework.stereotype.Component;

@Component
public class ApplyScholarshipService {

    StudentRepository studentRepository;


    public ApplyScholarshipService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public void apply(ApplicationForm applicationForm) throws ClientSideErrorException, ServerSideErrorException {

        // 調閱學生資料
        try {
            studentRepository.find(applicationForm.getStudentId())
                    .orElseThrow(() -> new ClientSideErrorException("cannot find student", 987));
        } catch (RepositoryAccessDataFailException e) {
            throw new ServerSideErrorException("failed to retrieve student data", 666);
        }

        // 調閱獎學金規定的資料
        // 查驗是否符合資格
        // 填寫正式申請書
        // 存檔

    }
}
