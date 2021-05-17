package study.datajpa.controller;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import study.datajpa.entity.Member;
import study.datajpa.repository.MemberRepository;

import javax.swing.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class HelloControllerTest {

    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    MockMvc mockMvc;


    @Test
    public void fsk() throws Exception{
        //given
        Member member = new Member("sangoun", 28);
        memberRepository.save(member);
        //when
        mockMvc.perform(get("/helloDomain/" + member.getId()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string("jpa"));
        //then
    }


    @Test
    public void webPage() throws Exception{
        mockMvc.perform(get("/helloPage"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public static void main(String[] args) {
        System.out.println("5+2=34"+3+4);
        System.out.println("5+2=34"+(3+4));
    }
}