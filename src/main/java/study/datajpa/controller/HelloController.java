package study.datajpa.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import study.datajpa.dto.MemberDto;
import study.datajpa.entity.Member;
import study.datajpa.entity.Team;
import study.datajpa.repository.MemberRepository;
import study.datajpa.repository.TeamRepository;

import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class HelloController {

    private final MemberRepository memberRepository;
    private final TeamRepository teamRepository;

    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }

    @ResponseBody
    @GetMapping("/helloDomain/{id}")
    public String domainConvert(@PathVariable("id") Member member) {
        return member.getUsername();
    }

    @ResponseBody
    @GetMapping("/helloPage")
    public Page<MemberDto> helloPage(@PageableDefault(size = 12, sort = "username", direction = Sort.Direction.DESC) Pageable pageable) {

        Team team = new Team("sangoun94");
        teamRepository.save(team);
        Optional<Team> teamOptional = teamRepository.findById(team.getId());
        Member member = new Member("sangoun", 28,teamOptional.get());
        memberRepository.save(member);

        Page<Member> pageAll = memberRepository.findAll(pageable);
        Page<MemberDto> map = pageAll.map(MemberDto::new);
        return map;
    }

}
