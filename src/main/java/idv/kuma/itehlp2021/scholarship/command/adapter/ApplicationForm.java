package idv.kuma.itehlp2021.scholarship.command.adapter;

import idv.kuma.itehlp2021.scholarship.command.entity.Application;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ApplicationForm {
    private long studentId;
    private long scholarshipId;

    public Application toApplication() {
        return new Application(studentId, scholarshipId);
    }
}
