package idv.kuma.itehlp2021.scholarship.apply;

import idv.kuma.itehlp2021.scholarship.apply.adapter.ApplicationForm;
import idv.kuma.itehlp2021.scholarship.apply.adapter.ScholarshipNotExistException;
import idv.kuma.itehlp2021.student.register.StudentNotExistException;
import org.springframework.stereotype.Component;

@Component
public class ApplyScholarshipService {

    private final ApplicationChecker checker;
    private final ApplicationRepository applicationRepository;

    public ApplyScholarshipService(ApplicationChecker checker, ApplicationRepository applicationRepository) {
        this.checker = checker;
        this.applicationRepository = applicationRepository;
    }

    public void apply(Application application) {

        if (this.checker.checkTime(application)) {
            this.applicationRepository.create(application);
        }
    }

    public void apply(ApplicationForm applicationForm) throws StudentNotExistException, ScholarshipNotExistException {

    }
}
