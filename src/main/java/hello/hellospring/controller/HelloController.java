package hello.hellospring.controller;

import org.springframework.core.Conventions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    @GetMapping("hello")
    public String hello(Model model){
        model.addAttribute("data", "hello!!");
        return "hello"; // resources 폴더의 hello를 찾아가서 랜더링 한다(화면을 실행시킨다)
        // 컨트롤러에서 리턴 값으로 문자를 반환하면 뷰 리졸버가 화면을 찾아서 처리한다.
        // resources:templates/ +{ViewName}+ .html
    }

    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam(value = "name") String name, Model model){
        model.addAttribute("name", name);
        return "hello-template";
    }

    // 데이터를 직접 내려주는 방식
    @GetMapping("hello-string")
    @ResponseBody // 바디부에 내가 데이터를 직접 넣어주겠다는 의미
    public String helloString(@RequestParam(value = "name") String name){
        return "hello" + name;
    }

    // json
    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name){
        Hello hello = new Hello();
        hello.setName(name);
        return hello; // 객체 리턴: 객체가 오면 기본 디폴트가 그냥 제이슨 방식으로 데이터를 만들어서 http 응답에 반환하겠다
    }
    
    // 자바빈 표준 방식, 프로퍼티 접근 방식
    static class Hello{
        private String name; // 프라이빗이라 꺼내 쓸 수 없다
        
        // 퍼블릭
        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
