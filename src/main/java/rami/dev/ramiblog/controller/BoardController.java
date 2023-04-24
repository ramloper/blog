package rami.dev.ramiblog.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import rami.dev.ramiblog.core.auth.MyUserDetails;

@Controller
@RequiredArgsConstructor
@Slf4j
public class BoardController {

    //RestAPI 주소 설계 규칙에서 자원에는 복수를 붙인다.
    @GetMapping({"/", "/board"})
    public String main(){
        return "board/main";
    }

    @GetMapping("/s/board/saveForm")
    public String saveForm() {
        return "board/saveForm";
    }
}
