package com.practice.controller;

import com.practice.domain.request.UserLoginRequest;
import com.practice.exception.UserLoginFailedException;
import com.practice.service.UserService;
import java.util.Objects;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/cs")
@RequiredArgsConstructor
public class UserLoginController {
    private final UserService userService;

    @GetMapping("/login")
    public String login(HttpServletRequest request) {
        HttpSession session = request.getSession(true);

        String userId = (String) session.getAttribute("id");
        if (Objects.nonNull(userId)) {
            boolean isAdmin = Objects.equals(session.getAttribute("isAdmin"), 1);
            return "redirect:" + (isAdmin ? "/cs/admin/" : "/cs/" + userId);
        }

        return "loginForm";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute UserLoginRequest user,
                        HttpServletRequest request) {

        if (userService.matches(user)) {
            HttpSession session = request.getSession(true);
            session.setAttribute("id", user.getId());

            boolean isAdmin = (user.getIsAdmin() == 1);
            session.setAttribute("isAdmin", isAdmin);

            return "redirect:" + (isAdmin ? "/cs/admin/" : "/cs/" + user.getId());

        } else {
            throw new UserLoginFailedException();
        }
    }
}
