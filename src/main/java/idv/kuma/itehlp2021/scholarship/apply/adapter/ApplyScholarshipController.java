package idv.kuma.itehlp2021.scholarship.apply.adapter;

import idv.kuma.itehlp2021.scholarship.apply.ApplyScholarshipService;
import idv.kuma.itehlp2021.student.register.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class ApplyScholarshipController {

    ApplyScholarshipService applyScholarshipService;

    public ApplyScholarshipController(ApplyScholarshipService applyScholarshipService) {
        this.applyScholarshipService = applyScholarshipService;
    }

    @PostMapping("/scholarship/apply")
    public ResponseEntity<ApiResponse> apply(@RequestBody ApplicationForm applicationForm) {
        try {
            applyScholarshipService.apply(applicationForm);
        } catch (ClientSideErrorException e) {
            return ResponseEntity.status(400).body(ApiResponse.bad(e.getCode()));
        } catch (DataAccessErrorException e) {
            return ResponseEntity.status(500).body(ApiResponse.bad(666));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(ApiResponse.bad(999));
        }
        return null;
    }

}
