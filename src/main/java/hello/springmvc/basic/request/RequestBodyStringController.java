package hello.springmvc.basic.request;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;
import java.nio.charset.StandardCharsets;

@Slf4j
@Controller
public class RequestBodyStringController {


    /**
     * HttpServletRequst에서 getInputStream()으로 읽어와서 문자열로 변환해서 읽을 수 있다.
     */
    @PostMapping("/request-body-string-v1")
    public void requestBodyString(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ServletInputStream inputStream = request.getInputStream();
        String string = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);

        log.info("messageBody={}", string);

        response.getWriter().write("ok");
    }

    /**
     * 매개변수에서 바로 inputStream 과 writer를 받을수도 있다.
     */
    @PostMapping("/request-body-string-v2")
    public void requestBodyStringV2(InputStream inputStream, Writer writer) throws IOException {
        String string = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);

        log.info("messageBody={}", string);

        writer.write("ok");
    }

    /**
     * HttpEntity를 사용해서 더 편리하게 조회가 가능하다.
     */
    @PostMapping("/request-body-string-v3")
    public HttpEntity<String> requestBodyStringV3(HttpEntity<String> httpEntity){

        log.info("messageBody={}", httpEntity.getBody());

        return new HttpEntity<>("ok");
    }

    /**
     * @RequestBody라는 애노테이션을 이용해 더 간편하게 요청 메세지 바디를 받을수도 있다.
     */
    @ResponseBody
    @PostMapping("/request-body-string-v4")
    public String requestBodyStringV4(@RequestBody String body){

        log.info("messageBody={}", body);

        return "ok";
    }
}