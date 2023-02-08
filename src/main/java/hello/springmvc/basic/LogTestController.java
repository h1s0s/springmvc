package hello.springmvc.basic;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//@Slf4j
@RestController //Controller 는 반환 값이 String 이면 뷰 이름으로 인식된다. 그래서 뷰를 찾고 뷰가 랜더링 된다. RestController는 반환 값으로 뷰를 찾는 것이 아니라, HTTP 메시지 바디에 바로 입력한다.
public class LogTestController {
    private final Logger log = LoggerFactory.getLogger(getClass()); //getClass() = logTestController.class 현재 나의 클래스를 넘겨줌.

    @RequestMapping("/log-test")
    public String logTest() {
        String name = "Spring";

        /*
        * 로그를 찍을때 레벨을 찍을 수 있음. (trace -> debug -> info -> warn -> error)
        * 기본적으로는 info -> warn -> error만 찍힘.
        * application.properties에 "logging.level.root=레벨" 값으로 변경 가능
        * 보통 로컬서버는 trace 레벨로 두고 개발서버에서는 debug 운영서버에서는 info까지만 사용함
        * Sysout은 level이 없기때문에 다 남게되버림. 그래서 log를 사용
        * */
        log.trace("trace log={}", "{}", name, name); //추적
        log.debug("debug log={}", name); //디버그를 찍어주는 log
        log.debug("debug log={}" + name); //이렇게 사용해도 로그가 찍히긴 하나, 문자 연산이 일어남 레벨에 해당하지 않아도 무의미한 연산이 발생함. 좋지 않은 방법.
        log.info("info log={}", name); //정보를 찍어주는 log
        log.warn("warn log={}", name); //경고를 찍어주는 log
        log.error("error log={}", name); //에러를 찍어주는 log

        //로그를 사용하지 않아도 a+b 계산 로직이 먼저 실행됨, 이런 방식으로 사용하면 X
        log.debug("String concat log=" + name);
        return "ok";
    }
}