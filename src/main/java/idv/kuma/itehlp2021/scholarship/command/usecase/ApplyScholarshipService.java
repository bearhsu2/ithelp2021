package idv.kuma.itehlp2021.scholarship.command.usecase;

import idv.kuma.itehlp2021.scholarship.command.adapter.ApplicationForm;
import idv.kuma.itehlp2021.scholarship.command.adapter.ClientSideErrorException;
import idv.kuma.itehlp2021.scholarship.command.adapter.DataAccessErrorException;
import org.springframework.stereotype.Component;

@Component
public class ApplyScholarshipService {

    public void apply(ApplicationForm applicationForm) throws ClientSideErrorException, DataAccessErrorException {

    }
}
