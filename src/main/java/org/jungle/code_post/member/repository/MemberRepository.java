package org.jungle.code_post.member.repository;

import org.jungle.code_post.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, String> {
    public Optional<Member> findByUsername(String username);
    public boolean existsByUsername(String username);
}
