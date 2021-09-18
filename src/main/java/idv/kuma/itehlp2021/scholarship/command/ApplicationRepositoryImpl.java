package idv.kuma.itehlp2021.scholarship.command;

import idv.kuma.itehlp2021.scholarship.command.adapter.ApplicationForm;
import org.springframework.stereotype.Component;

@Component
public class ApplicationRepositoryImpl implements ApplicationRepository {
    @Override
    public void create(ApplicationForm application) {
        // do nothing
    }
}
