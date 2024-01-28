package com.practice.controller;

import com.practice.domain.enums.InquiryType;
import com.practice.domain.request.InquiryRegisterRequest;
import com.practice.service.AnswerService;
import com.practice.service.InquiryService;
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
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/cs")
@RequiredArgsConstructor
public class InquiryController {
    private final InquiryService inquiryService;

    private final AnswerService answerService;

    @GetMapping("/{id}")
    public String getInquiries(@PathVariable("id") String customerId, Model model) {
        model.addAttribute("inquiries", inquiryService.getInquiriesWithId(customerId));
        model.addAttribute("types", InquiryType.values());

        return "inquiries";
    }

    @GetMapping("/admin")
    public String getInquiries(Model model) {
        model.addAttribute("inquiries", inquiryService.getInquiriesWithIsAnsweredFalse());

        return "inquiries";
    }

    @PostMapping("/search/{id}")
    public String searchByInquiryType(@PathVariable("id") String customerId,
                                      @RequestParam("type") Integer typeId,
                                      Model model) {
        model.addAttribute("inquiries", inquiryService.getInquiriesWithIdAndType(customerId, typeId));
        model.addAttribute("types", InquiryType.values());

        return "inquiries";
    }

    @GetMapping("/inquiry/{seq}")
    public String inquire(@PathVariable(value = "seq") Long seq, Model model) {
        if (seq != 0) {
            model.addAttribute("inquiry", inquiryService.getInquiry(seq));
            model.addAttribute("answer", answerService.getAnswer(seq));
            model.addAttribute("view", true);
        } else {
            model.addAttribute("view", false);
        }

        model.addAttribute("types", InquiryType.values());

        return "inquire";
    }

    @PostMapping("/inquiry")
    public String inquire(@Validated @ModelAttribute InquiryRegisterRequest inquiry, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new ValidationException();
        }

        inquiryService.inquire(inquiry.getTitle(), inquiry.getType(), inquiry.getInquirer(), inquiry.getContent());

        return "redirect:/cs/" + inquiry.getInquirer();
    }
}
