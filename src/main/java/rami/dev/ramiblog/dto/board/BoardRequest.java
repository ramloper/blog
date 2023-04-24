package rami.dev.ramiblog.dto.board;

import lombok.Getter;
import lombok.Setter;
import rami.dev.ramiblog.model.board.Board;
import rami.dev.ramiblog.model.user.User;

public class BoardRequest {

    @Setter
    @Getter
    public static class SaveInDTO {

        private String title;

        private String content;

        public Board toEntity(User user, String thumbnail) {
            return Board.builder()
                    .user(user)
                    .title(title)
                    .content(content)
                    .thumbnail(thumbnail)
                    .build();
        }
    }
}
