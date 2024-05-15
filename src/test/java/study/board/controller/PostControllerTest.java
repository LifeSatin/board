package study.board.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.SessionAttribute;
import study.board.dto.request.BoardNameRequestDto;
import study.board.dto.request.LoginRequestDto;
import study.board.dto.request.PostUpdateRequestDto;
import study.board.dto.request.SignupRequestDto;

import static org.junit.jupiter.api.Assertions.*;

class PostControllerTest {

    @Autowired AuthController authController;
    @Autowired
    BoardController boardController;
    @Autowired
    PostController postController;

    @BeforeEach
    public void init() {
        SignupRequestDto signUpDto = new SignupRequestDto("test", "0000", "test");
        authController.signup(signUpDto);
        LoginRequestDto loginDto = new LoginRequestDto("test", "0000");
        authController.login(loginDto, new MockHttpServletRequest());
        BoardNameRequestDto boardDto = new BoardNameRequestDto("전체 글 보기");
        boardController.createBoard(boardDto);
    }

    @Test
    void search() {
    }

    @Test
    void viewPost() {

    }

    @Test
    void getComments() {
    }

    @Test
    void getNotices() {
    }

    @Test
    void createPost() {

    }

    @Test
    void createComments() {
    }

    @Test
    void updatePost() {
    }

    @Test
    void deletePost() {
    }
}