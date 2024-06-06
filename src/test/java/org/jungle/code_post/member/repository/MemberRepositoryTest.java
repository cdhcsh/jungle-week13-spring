package org.jungle.code_post.member.repository;

import jakarta.transaction.Transactional;
import org.jungle.code_post.member.entity.Member;
import org.jungle.code_post.post.entity.Post;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayName("Member Repository 테스트")
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Transactional
public class MemberRepositoryTest {
    @Autowired
    MemberRepository memberRepository;

    @AfterEach
    public void clean(){
        memberRepository.deleteAll();
    }
    @Test
    @DisplayName("맴버 회원가입 테스트")
    public void signIn(){

        Member insert_member = memberRepository.save(Member.builder()
                .username("cdh")
                .password("1234")
                .build());

        Member saved_member = memberRepository.findById(insert_member.getId()).get();

        System.out.println(saved_member);

        assertThat(insert_member).isEqualTo(saved_member);

    }

    @Test
    @DisplayName("없는 맴버 조회 테스트")
    public void loginFailNoExist(){
        memberRepository.save(Member.builder()
                .username("cdh")
                .password("1234")
                .build());

        Optional<Member> find_member = memberRepository.findByUsername("none");

        assertThatThrownBy(find_member::get).isInstanceOf(NoSuchElementException.class);
    }
}
