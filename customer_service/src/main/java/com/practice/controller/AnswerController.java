package com.practice.controller;

import com.practice.domain.enums.InquiryType;
import com.practice.domain.request.AnswerRequest;
import com.practice.service.AnswerService;
import com.practice.service.InquiryService;
import com.practice.service.UserService;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.validation.ValidationException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/cs/admin")
@RequiredArgsConstructor
public class AnswerController {
    private final AnswerService answerService;

    private final InquiryService inquiryService;

    private final UserService userService;

    @GetMapping("/answer/{seq}")
    public String answerForm(@PathVariable("seq") Long seq, Model model) {
        model.addAttribute("inquiry", inquiryService.getInquiry(seq));
        model.addAttribute("types", InquiryType.values());
        model.addAttribute("view", true);

        return "inquire";
    }

    @PostMapping("/answer")
    public String answer(@Validated @ModelAttribute AnswerRequest answer,
                         BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new ValidationException();
        }

        answerService.answer(answer.getId(), answer.getAnswerer(),
                LocalDate.parse(answer.getAnsweredAt(), DateTimeFormatter.ofPattern("yyyy-MM-dd")), answer.getAnswer());

        return "redirect:/cs/admin";
    }
}
