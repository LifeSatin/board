package study.board.controller;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import study.board.Service.AuthService;
import study.board.Service.MemberService;
import study.board.dto.request.MemberUpdateRequestDto;
import study.board.dto.request.SignupRequestDto;
import study.board.entity.Member;
import study.board.repository.member.MemberRepository;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class UserControllerTest {

    @Autowired
    private UserController userController;
    @Autowired
    private AuthController authController;
    @Autowired
    private MemberService memberService;


    @BeforeEach
    public void init() {
        SignupRequestDto dto = new SignupRequestDto("test", "0000", "test");
        authController.signup(dto);
    }

    @Test
    void getMembers() {
    }

    @Test
    void getMemberPage() {
    }

    @Test
    void updateMember() {
        Member findMember = memberService.searchMemberByUsername("test").orElse(null);
        MemberUpdateRequestDto dto = new MemberUpdateRequestDto("1111", "test1");
        userController.updateMember(dto, findMember.getId());
        assertThat(memberService.searchMemberByUsername("test1").orElse(null)).isEqualTo(findMember);
    }

    @Test
    void deleteMember() {
        Member findMember = memberService.searchMemberByUsername("test").orElse(null);
        userController.deleteMember(findMember.getId());
        Member deletedMember = memberService.searchMemberByUsername("test").orElse(null);
        assertThat(deletedMember).isNull();
    }
}