package hello.springmvc.basic.response;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ResponseViewController {

    /**
     * ModelAndView 를 반환하는 경우(responseViewV1)
     * ⇒ 객체에서 View를 꺼내어 물리적인 뷰 이름으로 완성한 뒤 뷰를 찾아 렌더링을 한다.
     * @return
     */
    @RequestMapping("/response-view-v1")
    public ModelAndView responseViewV1() {
        ModelAndView mv = new ModelAndView("/response/hello").addObject("data", "hello");

        return mv;
    }

    /**
     * String을 반환하는 경우(responseViewV2)
     * ⇒ @ResponseBody(혹은 클래스레벨에서 @RestController)가 없으면 response/hello라는 문자가  뷰 리졸버로 전달되어 실행되서 뷰를 찾고 렌더링을 한다.
     * ⇒ @ResponseBody(혹은 클래스레벨에서 @RestController)가 있으면 뷰 리졸버를 실행하지 않고 HTTP 메세지 바디에 직접 response/hello 라는 문자가 입력된다.
     * ⇒ 위 코드에서는 /response/hello를 반환하는데 뷰 리졸버는 물리적 이름을 찾아서 렌더링을 실행한다.
     * → 실행: templates/response/hello.html
     * @param model
     * @return
     */
    @RequestMapping("/response-view-v2")
    public String responseViewV2(Model model) {

        model.addAttribute("data", "hello");
        return "response/hello";
    }

    /**
     * void를 반환하는 경우(responseViewV3)
     * ⇒ @Controller를 사용하고 HttpServletResponse, OutputStream(Writer)같은 HTTP 메세지 바디를 처리하는 파라미터가 없으면 요청 URL을 참고해서 논리 뷰 이름으로 사용한다.
     * ⇒ 위 코드에서 경로는  요청 URL인 (/response/hello)를 사용한다.
     * ⇒ 이 방식은 명시성이 너무 떨어지고 이런 케이스가 나오는 경우도 거의 없어 권장하지 않는다.
     * @param model
     */
    @RequestMapping("/response/hello")
    public void responseViewV3(Model model) {
        model.addAttribute("data", "hello!!");
    }
}