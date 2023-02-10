package hello.springmvc.basic.request;

import com.fasterxml.jackson.databind.ObjectMapper;
import hello.springmvc.basic.HelloData;
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
import java.nio.charset.StandardCharsets;

@Slf4j
@Controller
public class RequestBodyJsonController {

    private ObjectMapper objectMapper = new ObjectMapper();

    /**
     * 예전 방식의 HttpServletRequest, HttpServletResponse 객체에서 메세지 바디를 읽어와 ObjectMapper로 객체 바인딩을 하는 코드
     * @param request
     * @param response
     * @throws IOException
     */
    @PostMapping("/request-body-json-v1")
    public void requestBodyJsonV1(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ServletInputStream inputStream = request.getInputStream();
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);

        log.info("messageBody={}", messageBody);
        HelloData helloData = objectMapper.readValue(messageBody, HelloData.class);
        log.info("helloDate = {}", helloData.toString());

        response.getWriter().write("ok");
    }

    /**
     * 위에서 배운 @RequestBody 애노테이션을 이용해 메세지 바디를 바로 받아서 사용하면 InputStream을 꺼내 서
     * StreamUtili로 변환해줄 필요가 없이 바로 ObjectMapper로 객체 변환을 해줄 수 있다.
     * @param messageBody
     * @return
     * @throws IOException
     */
    @PostMapping("/request-body-json-v2")
    @ResponseBody
    public String requestBodyJsonV2(@RequestBody String messageBody) throws IOException {
        log.info("messageBody={}", messageBody);
        HelloData helloData = objectMapper.readValue(messageBody, HelloData.class);
        log.info("helloDate = {}", helloData.toString());

        return "ok";
    }

    /**
     * RequestBody 를 사용하면 객체를 직접 지정해서 매핑해 줄 수도 있다.
     * @param helloData
     * @return
     */
    @PostMapping("/request-body-json-v3")
    @ResponseBody
    public String requestBodyJsonV3(@RequestBody HelloData helloData){
        log.info("helloDate = {}", helloData.toString());

        return "ok";
    }

    /**
     * HTTP 요청시에 content-type이 application/json인지 확인해야 한다. 그래야 JSON을 처리할 수 있는 HTTP 메세지 컨버터가 실행된다.
     * @param httpEntity
     * @return
     */
    @PostMapping("/request-body-json-v4")
    @ResponseBody
    public String requestBodyJsonV4(HttpEntity<HelloData> httpEntity){
        HelloData helloData = httpEntity.getBody();
        log.info("helloDate = {}", helloData.toString());

        return "ok";
    }

    /**
     * 객체 리턴시 Json 방식으로 리턴된다.
     * @param helloData
     * @return
     */
    @PostMapping("/request-body-json-v5")
    @ResponseBody
    public HelloData requestBodyJsonV5(@RequestBody HelloData helloData){
        log.info("helloDate = {}", helloData.toString());

        return helloData;
    }
}