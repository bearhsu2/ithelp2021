package idv.kuma.itehlp2021.scholarship.command.usecase;

import idv.kuma.itehlp2021.scholarship.command.ApplicationChecker;
import idv.kuma.itehlp2021.scholarship.command.ApplicationRepository;
import idv.kuma.itehlp2021.scholarship.command.adapter.ApplicationForm;
import idv.kuma.itehlp2021.scholarship.command.adapter.ClientSideErrorException;
import idv.kuma.itehlp2021.scholarship.command.adapter.DataAccessErrorException;
import org.springframework.stereotype.Component;

@Component
public class ApplyScholarshipService {

    private final ApplicationChecker checker;
    private final ApplicationRepository applicationRepository;

    public ApplyScholarshipService(ApplicationChecker checker, ApplicationRepository applicationRepository) {
        this.checker = checker;
        this.applicationRepository = applicationRepository;
    }


    public void apply(ApplicationForm applicationForm) throws ClientSideErrorException, DataAccessErrorException {
        if (this.checker.checkTime(applicationForm)) {

            this.applicationRepository.create(applicationForm);
        }
    }
}
