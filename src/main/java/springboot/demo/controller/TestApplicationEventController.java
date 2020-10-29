package springboot.demo.controller;

import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * @author luozhenhong
 * @version 1.0
 * @date 2020/10/12 9:44
 */
@RestController
@RequestMapping("/event")
@RequiredArgsConstructor
public class TestApplicationEventController {

    private final ApplicationEventPublisher eventPublisher;

    @GetMapping("test")
//    @Transactional(rollbackFor = Exception.class)
    public Object test(String param) {
        System.out.printf("event begin with param: [%s] \n", param);

        eventPublisher.publishEvent(
                TestEvent.builder()
                        .eventId("eventId-tests")
                        .eventTime(new Date())
                        .build()
        );

        System.out.printf("event end with param: [%s] \n", param);
        return "success";
    }

    @Component
    static class TestEventListener {

        @Async
        @EventListener(classes = TestEvent.class)
//        @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
        public void testEventListener(TestEvent event) {
            System.out.println("event-listener, event: " + event);
        }
    }



    @Data
    @Builder(toBuilder = true)
    static class TestEvent {

        private Date eventTime;

        private String eventId;
    }
}
