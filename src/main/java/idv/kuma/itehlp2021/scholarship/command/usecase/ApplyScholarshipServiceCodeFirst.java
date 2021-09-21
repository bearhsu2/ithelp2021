package idv.kuma.itehlp2021.scholarship.command.usecase;

import idv.kuma.itehlp2021.scholarship.command.adapter.ApplicationForm;
import idv.kuma.itehlp2021.scholarship.command.adapter.ClientSideErrorException;
import idv.kuma.itehlp2021.scholarship.command.adapter.ServerSideErrorException;


public class ApplyScholarshipServiceCodeFirst {

    private final ApplicationChecker checker;
    private final ApplicationRepository applicationRepository;

    public ApplyScholarshipServiceCodeFirst(ApplicationChecker checker, ApplicationRepository applicationRepository) {
        this.checker = checker;
        this.applicationRepository = applicationRepository;
    }


    public void apply(ApplicationForm applicationForm) throws ClientSideErrorException, ServerSideErrorException {
        if (this.checker.checkTime(applicationForm)) {

            this.applicationRepository.create(applicationForm);
        }
    }
}
