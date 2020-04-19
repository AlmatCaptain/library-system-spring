package kz.home.librarysystem.controller;

import kz.home.librarysystem.model.Book;
import kz.home.librarysystem.model.Member;
import kz.home.librarysystem.repository.MemberRepository;
import kz.home.librarysystem.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/members")
public class MemberController {

    @Autowired
    private MemberService memberService;

    @RequestMapping({ "/hello" })
    public String firstPage() {
        return "Hello World";
    }

    @GetMapping("")
    public List<Member> findAll() {
        return memberService.getAllUsers();
    }

//    @GetMapping("/{id}")
//    public Member getMemberById(@PathVariable("id") Long id) {
//        return memberRepository.findById(id).get();
//    }

    @GetMapping("/create")
    public void addMember(String username, String password) {
        Member member = new Member();
        member.setPassword(password);
        member.setName(username);

        memberService.createUser(member);
    }

//    @PutMapping("/{id}")
//    public Member updateUser(@PathVariable Long id,
//                             @RequestBody Member member) {
//        member.setId(id);
//        return memberRepository.saveAndFlush(member);
//    }
//
//    @PatchMapping("/{id}")
//    public Member updateName(@PathVariable Long id,
//                             @RequestParam String name) {
//        Member member = memberRepository.findById(id).get();
//        member.setName(name);
//        return memberRepository.saveAndFlush(member);
//    }
//
//    @DeleteMapping("/{id}")
//    public String deleteGenreById(@PathVariable("id") Long id) {
//        memberRepository.deleteById(id);
//        return "Deleted!";
//    }

}
