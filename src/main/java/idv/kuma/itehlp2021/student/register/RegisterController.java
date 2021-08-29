package idv.kuma.itehlp2021.student.register;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
@Slf4j
public class RegisterController {

    private final RegisterService service;

    public RegisterController(RegisterService service) {
        this.service = service;
    }

    @PostMapping("/register")
    public ResponseEntity<ApiResponse> register(@RequestBody RegisterRequest request) {
        try {
            service.execute(request);

            // ApiResponse 是前後端共同講好的回傳資料格式
            // 這裡與前端說好，如果成功就直接回 200 就好，內容可以「空白」
            return ResponseEntity.ok(ApiResponse.empty());
        } catch (StudentNotExistException e) {
            // 我們不認為這是系統出錯，所以寫 info 就好，以免過多 error 在 log 裡干擾閱讀
            log.info("Student not found. " + e.getMessage());

            // 用 Http 400 告訴前端這是用戶有問題，
            // 並在 body 裡帶一個特殊代碼「987」，代表「用戶不存在」
            return ResponseEntity.status(400).body(ApiResponse.bad(987));
        }
    }
}





