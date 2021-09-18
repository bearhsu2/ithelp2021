package idv.kuma.itehlp2021.scholarship.command;


import idv.kuma.itehlp2021.scholarship.command.adapter.ApplicationForm;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class ApplicationChecker {

    private final ScholarshipRepository scholarshipRepository;

    public ApplicationChecker(ScholarshipRepository scholarshipRepository) {
        this.scholarshipRepository = scholarshipRepository;
    }

    public boolean checkTime(ApplicationForm applicationForm) {

        Scholarship scholarship = scholarshipRepository.find(applicationForm.getScholarshipId());

        LocalDate deadline = scholarship.getDeadline();

        LocalDate today = LocalDate.now();

        return today.isEqual(deadline)
                || today.isBefore(deadline);

    }
}

