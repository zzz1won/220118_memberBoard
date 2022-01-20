package com.z1won.memberboard.repository;

import com.z1won.memberboard.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<MemberEntity,Long> {
    MemberEntity findByMemberEmail(String memberEmail);
}
