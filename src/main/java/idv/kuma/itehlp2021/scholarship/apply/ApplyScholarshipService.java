package idv.kuma.itehlp2021.scholarship.apply;

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
}
