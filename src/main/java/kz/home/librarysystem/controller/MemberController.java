package kz.home.librarysystem.controller;

import kz.home.librarysystem.model.Member;
import kz.home.librarysystem.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/members")
public class MemberController {

    @Autowired
    private MemberService memberService;

    @RequestMapping({"/hello"})
    public String firstPage() {
        return "Hello World";
    }

    @GetMapping("")
    public List<Member> findAll() {
        return memberService.getAllUsers();
    }

    @GetMapping("/{id}")
    public Member getMemberById(@PathVariable("id") Long id) {
        return memberService.getMemberById(id);
    }

    @GetMapping("/admin/create")
    public void addMember(String username, String password) {
        Member member = new Member();
        member.setPassword(password);
        member.setUsername(username);

        memberService.createUser(member);
    }


    @PutMapping("/admin/update/{id}")
    public void updateMember(@PathVariable Long id, @RequestBody Member member) {
        member.setId(id);
        memberService.updateMember(id, member);
    }

    @PatchMapping("/admin/update/{id}")
    public void updateUserName(@PathVariable Long id, @RequestParam String name) {
        memberService.updateUserName(id, name);
    }

    @DeleteMapping("/admin/delete/{id}")
    public void deleteMemberById(@PathVariable("id") Long id) {
        memberService.deleteMemberById(id);
    }
}
