package kz.home.librarysystem.repository;

import kz.home.librarysystem.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member,Long> {
}