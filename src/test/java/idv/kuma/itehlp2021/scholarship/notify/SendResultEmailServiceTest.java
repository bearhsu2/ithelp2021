package idv.kuma.itehlp2021.scholarship.notify;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.stubbing.OngoingStubbing;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class SendResultEmailServiceTest {

    private final Mailer mailer = Mockito.mock(Mailer.class);
    private List<Future<Boolean>> futures;
    private SendResultEmailService service = new SendResultEmailService(mailer);

    @Test
    void when_send_returns_future() throws ExecutionException, InterruptedException {

        // 準備假 Mailer
        Mailer mailer = Mockito.mock(Mailer.class);
        SendResultEmailService service = new SendResultEmailService(mailer);

        // 假 Mailer 會回傳兩個 true，一個 false
        when(mailer.send(any(ScholarshipResult.class)))
                .thenReturn(true, true, false);

        // 跑起來
        List<Future<Boolean>> futures = service.send(
                Arrays.asList(
                        new ScholarshipResult(),
                        new ScholarshipResult(),
                        new ScholarshipResult()
                ));

        // 檢查 Future 裡 true 與 false 的個數
        int goods = 0;
        int bads = 0;
        for (Future<Boolean> future : futures) {
            if (future.get()) {
                goods++;
            } else {
                bads++;
            }
        }
        assertEquals(2, goods);
        assertEquals(1, bads);

    }


    @Test
    void when_send_returns_future_refactor_the_test() throws ExecutionException, InterruptedException {

        // 假 Mailer 會回傳兩個 true，一個 false
        assume_mailer_execution_result_would_be(true, true, false);

        // 跑起來
        when_send_with_results(3);

        // 檢查 Future 裡 true 與 false 的個數
        then_counts_in_futures_will_be(true, 2);
        then_counts_in_futures_will_be(false, 1);

    }

    private void then_counts_in_futures_will_be(boolean target, int expected) throws InterruptedException, ExecutionException {
        assertEquals(expected, count_in_futures(target));
    }

    private void assume_mailer_execution_result_would_be(boolean first, boolean... followings) {
        OngoingStubbing<Boolean> stubbing =
                when(mailer.send(any(ScholarshipResult.class))).thenReturn(first);

        for (boolean expected : followings) {
            stubbing.thenReturn(expected);
        }
    }

    private void when_send_with_results(int number) {
        futures = this.service.send(results(number));
    }

    private List<ScholarshipResult> results(int number) {

        List<ScholarshipResult> results = new ArrayList<>();
        for (int i = 0; i < number; i++) {
            results.add(new ScholarshipResult());
        }
        return results;
    }

    private int count_in_futures(boolean target) throws InterruptedException, ExecutionException {
        int counts = 0;
        for (Future<Boolean> future : futures) {
            if (future.get() == target) {
                counts++;
            }
        }
        return counts;
    }
}