package jpabook.jpashop.domain.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.domain.repository.MemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional
class MemberServiceTest {

    @Autowired
    MemberService memberService;
    @Autowired
    MemberRepository memberRepository;
    @Autowired
    EntityManager em;

    @Test
    @DisplayName("회원가입")
    public void joinTest() throws Exception {
        //given
        Member member = new Member();
        member.setName("jeon");

        //when
        Long saveId = memberService.join(member);

        //then
        em.flush();
        Assertions.assertThat(member).isSameAs(memberRepository.findOne(saveId));
    }

    @Test
    @DisplayName("중복_회원_예외")
    public void duplicateJoinThrowsTest() throws Exception {
        //given
        Member member1 = new Member();
        member1.setName("jeon");

        Member member2 = new Member();
        member2.setName("jeon");

        //when
        memberService.join(member1);
        IllegalStateException illegalStateException = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        //then
        Assertions.assertThat(illegalStateException.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
    }


}