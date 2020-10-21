package com.training.admissions.controller;


import com.training.admissions.dto.CandidateDTO;
import com.training.admissions.dto.CandidateProfileDTO;
import com.training.admissions.entity.Candidate;
import com.training.admissions.service.CandidateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Slf4j
@Controller
public class CandidateController {

    private final CandidateService candidateService;


    public CandidateController(CandidateService candidateService) {
        this.candidateService = candidateService;

    }

    @PostMapping("/api/candidate/registration")
    public String createCandidate(@Valid CandidateDTO candidateDTO,
                                  BindingResult bindingResultCandidate,
                                  @Valid CandidateProfileDTO candidateProfileDTO,
                                  BindingResult bindingResultProfile, Model model) {

        if (bindingResultCandidate.hasErrors() || bindingResultProfile.hasErrors()) {

            Map<String, String> errors = new HashMap<>();
            errors.putAll(bindingResultCandidate.getFieldErrors()
                    .stream()
                    .collect(Collectors.toMap(fieldError -> fieldError.getField()+"Error", DefaultMessageSourceResolvable::getDefaultMessage)));
            errors.putAll(bindingResultProfile.getFieldErrors()
                    .stream()
                    .collect(Collectors.toMap(fieldError -> fieldError.getField()+"Error", FieldError::getDefaultMessage)));

            model.addAttribute("candidateDTO", candidateDTO);
            model.addAttribute("candidateProfileDTO", candidateProfileDTO);
            model.mergeAttributes(errors);
            return "/registration";
        }
        candidateService.createCandidate(candidateDTO, candidateProfileDTO);
        log.info("new user " + candidateDTO.getUsername() + " created!");
        return "redirect:/auth/login";
    }


    @DeleteMapping("/api/candidate/{id}")
    public void deleteById(@PathVariable Long id) {
        candidateService.deleteById(id);
    }


    @GetMapping("/api/candidate/profile")
    public String getCandidateProfile(@AuthenticationPrincipal User currentUser
            , Model model) {
        model.addAttribute("candidate", candidateService.getByUsername(currentUser.getUsername()));
        return "/candidate/candidate_profile";
    }

    @GetMapping("/api/candidate/edit/{id}")
    public String getById(@PathVariable Long id, Model model) {
        Candidate candidate = candidateService.getById(id);
        model.addAttribute("candidate", candidate);
        return "/candidate/candidate_profile_edit";

    }


    @PostMapping("/api/candidate/update")
    public String updateCandidate(CandidateProfileDTO candidateProfileDTO
    ) {
        candidateService.updateCandidateProfile(candidateProfileDTO);
        return "redirect:/api/candidate/profile";
    }


    @GetMapping("/admin/candidate")
    public String getAllCandidates(
            @PageableDefault(sort = {"id"}, direction = Sort.Direction.ASC, size = 5) Pageable pageable,
            Model model) {
        Page<Candidate> page = candidateService.getAllCandidates(pageable);
        model.addAttribute("page", page);
        model.addAttribute("url", "/admin/candidate");

        return "/admin/candidates";
    }

    @GetMapping("/admin/candidate/edit/{id}")
    public String getCandidateById(@PathVariable Long id, Model model) {

        model.addAttribute("candidate", candidateService.getById(id));
        return "/admin/candidates-edit";

    }

    @PostMapping("/admin/candidate/edit/{id}")
    public String updateCandidate(CandidateDTO candidateDTO) {
        candidateService.updateCandidate(candidateDTO);
        return "redirect:/admin/candidate";

    }

    @PostMapping("/admin/candidate/delete/{id}")
    public String deleteRequest(@PathVariable(name = "id") Long id) {
        candidateService.deleteById(id);
        return "redirect:/admin/candidate";
    }


}


