package com.training.admissions.controller;

import com.training.admissions.dto.FacultyDTO;
import com.training.admissions.service.StatementService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class StatementController {

    private final StatementService statementService;

    public StatementController(StatementService statementService) {
        this.statementService = statementService;
    }

    @GetMapping("/admin/statement/faculty/{id}")
    public String facultyStatement(@PathVariable(name = "id") Long id, Model model) {
        model.addAttribute("faculty_statement", statementService.getStatementForFacultyWithId(id));
        model.addAttribute("faculty_id", id);
        return "/admin/statement";
    }


    @PostMapping("/admin/statement/finalize")
    public String facultyStatementFinalize(FacultyDTO facultyDTO,
                                           @AuthenticationPrincipal User currentUser) {
        statementService.facultyStatementFinalize(facultyDTO, currentUser.getUsername());

        return "redirect:/admin/pdf/download";
    }


    @GetMapping(value = "/admin/pdf/download")
    public ResponseEntity<byte[]> getPDF() {
        return statementService.getPDF();
    }


}
