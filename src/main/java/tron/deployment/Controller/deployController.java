package tron.deployment.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequestMapping(value = "/")
public class deployController {

    @RequestMapping(value = "")
    String index(){

        return "index.html";
    }
}
