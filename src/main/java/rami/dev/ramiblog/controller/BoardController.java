package rami.dev.ramiblog.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import rami.dev.ramiblog.core.auth.MyUserDetails;
import rami.dev.ramiblog.dto.board.BoardRequest;
import rami.dev.ramiblog.service.BoardService;

@Controller
@RequiredArgsConstructor
@Slf4j
public class BoardController {
    private final BoardService boardService;

    //RestAPI 주소 설계 규칙에서 자원에는 복수를 붙인다.
    @GetMapping({"/", "/board"})
    public String main(){
        return "board/main";
    }

    @GetMapping("/s/board/saveForm")
    public String saveForm() {
        return "board/saveForm";
    }

    @PostMapping("/s/board/save")
    public String save(BoardRequest.SaveInDTO saveInDTO, @AuthenticationPrincipal MyUserDetails myUserDetails){
        boardService.글쓰기(saveInDTO, myUserDetails.getUser().getId());
        return "redirect:/";
    }
}
