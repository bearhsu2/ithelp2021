package idv.kuma.itehlp2021.scholarship.command.entity;

import lombok.Data;

@Data
public class Application {
    private final long studentId;
    private final long scholarshipId;

    public Application(long studentId, long scholarshipId) {

        this.studentId = studentId;
        this.scholarshipId = scholarshipId;
    }
}
