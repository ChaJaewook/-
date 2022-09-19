package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    @GetMapping("hello")
    public String hello(Model model)
    {
        //Spring이 모델을 만들어서 넣어준다.
        //templates에 있는 hello.html로 연결된다.
        //기본적으로resources/templates/hello를 찾는다.
        //이걸 찾아서 rendering을 한다.
        //resources:templates{viewtemplate}.html
        model.addAttribute("data","spring!!");
        return "hello";
    }

    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam("name") String name, Model model)
    {
        model.addAttribute("name",name);
        return "hello-template";
    }

    @GetMapping("hello-string")
    @ResponseBody
    //http의 헤더와 바디, 그 바디에 이 내용을 직접 넣어주겠다.
    public String helloString(@RequestParam("name") String name)
    {
        return "hello"+name;// ex) hello spring
        //template 엔진과의 차이==> 뷰가 없다.
        //html이 아닌 문자가 그대로 내려간다.
    }

    //문자가 아닌 데이터를 가져올때 API방식을 많이 쓴다.
    //이런식으로 사용하면 json형태로 리턴값을 준다.
    //xml방식은 무겁다.
    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name)
    {
        Hello hello=new Hello();
        hello.setName(name);
        return hello;
    }
    static class Hello
    {

        private String name;
        //자바 bean 규약
        //method를 통해서 접근
        //property방식이라고 하기도 한다.

        //기존에는 viewresolver가 작동하지만
        //response body가 붙으면 string converter, json converter가 붙는다.
        //body에 문자를 직접적으로 반환한다.

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

    }
}
