package kz.home.librarysystem.service;

import kz.home.librarysystem.controller.BookController;
import kz.home.librarysystem.model.Member;
import kz.home.librarysystem.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberService implements UserDetailsService {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;


    public List<Member> getAllUsers() {
        return memberRepository.findAll();
    }

    public void createUser(Member member) {
        member.setPassword(passwordEncoder.encode(member.getPassword()));
        memberRepository.saveAndFlush(member);
    }

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        Member member = memberRepository.findByUsername(name);

        if (member == null) {
            throw new UsernameNotFoundException("Member: " + name + " not found!");
        }
        return member;
    }
}
