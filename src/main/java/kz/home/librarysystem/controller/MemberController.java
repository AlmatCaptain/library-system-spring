package kz.home.librarysystem.controller;

import kz.home.librarysystem.model.Book;
import kz.home.librarysystem.model.Member;
import kz.home.librarysystem.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class MemberController {

    private final MemberRepository memberRepository;

    @Autowired
    public MemberController(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public List<Member> findAll(){
        return memberRepository.findAll();
    }

    public Member getMemberById(Long id){
        return memberRepository.findById(id).get();
    }

    public void addMember(Member m){
        memberRepository.save(m);
    }

}
