package rami.dev.ramiblog.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.asm.IModelFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import rami.dev.ramiblog.core.auth.MyUserDetails;
import rami.dev.ramiblog.dto.board.BoardRequest;
import rami.dev.ramiblog.model.board.Board;
import rami.dev.ramiblog.service.BoardService;

@Controller
@RequiredArgsConstructor
@Slf4j
public class BoardController {
    private final BoardService boardService;

    //RestAPI 주소 설계 규칙에서 자원에는 복수를 붙인다.
    @GetMapping({"/", "/board"})
    public String main(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "") String keyword,
            Model model
    ){
        Page<Board> boardPG = boardService.글목록보기(page, keyword);
        model.addAttribute("boardPG", boardPG);
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
    @GetMapping( "/board/{id}")
    public String detail(@PathVariable Long id, Model model){
        Board board = boardService.게시글상세보기(id);
        model.addAttribute("board", board);
        return "board/detail";
    }
}
