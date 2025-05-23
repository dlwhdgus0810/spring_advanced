package hello.advanced.trace.hellotrace;

import hello.advanced.trace.TraceStatus;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class HelleTraceV2Test {

    @Test
    void begin_end() {
        HelleTraceV2 trace = new HelleTraceV2();

        TraceStatus status1 = trace.begin("hello1");
        TraceStatus status2 = trace.beginSync(status1.getTraceId(), "hello2");

        trace.end(status2);
        trace.end(status1);
    }

    @Test
    void exception() {
        HelleTraceV2 trace = new HelleTraceV2();

        TraceStatus status1 = trace.begin("hello1");
        TraceStatus status2 = trace.beginSync(status1.getTraceId(), "hello2");


        trace.exception(status2, new IllegalStateException());
        trace.exception(status1, new IllegalStateException());
    }

}