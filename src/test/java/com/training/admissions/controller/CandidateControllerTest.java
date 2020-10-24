package com.training.admissions.controller;

import com.training.admissions.entity.Candidate;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource("/application-test.properties")
@WithUserDetails(value = "admin")
public class CandidateControllerTest {
    @Autowired
    private MockMvc mockMvc;


    @Test
    @Sql(value = {"/create-user-before.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    public void getCandidateById() throws Exception {
        this.mockMvc.perform(get("/admin/candidate/edit/2"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("candidate",
                        Matchers.hasProperty("id", Matchers.equalTo(2L))))
                .andExpect(model().attribute("candidate",
                        Matchers.hasProperty("username", Matchers.equalToIgnoringCase("q"))))
                .andExpect(model().attribute("candidate",
                        Matchers.hasProperty("candidateProfile", Matchers.hasProperty("email",
                                Matchers.equalToIgnoringCase("user@user.com")))));
    }

    @Test
    @Sql(value = {"/create-user-before.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    public void getCandidateByIdNotExists() throws Exception {
        this.mockMvc.perform(get("/admin/candidate/edit/24"))
                .andExpect(redirectedUrl("/admin/workspace"));

    }

    @Test
    @Sql(value = {"/create-user-before.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    public void getAllCandidates() throws Exception {
        this.mockMvc.perform(get("/admin/candidate"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("page",
                        Matchers.hasItems(
                                Matchers.<Candidate>hasProperty("username", Matchers.equalToIgnoringCase("admin")),
                                Matchers.<Candidate>hasProperty("username", Matchers.equalToIgnoringCase("q")))));


    }

}