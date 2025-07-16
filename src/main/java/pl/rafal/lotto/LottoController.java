package pl.rafal.lotto;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LottoController {
    @GetMapping("/")
    public String index() {
        return "index.html";
    }
}
