package com.springAuth.codeFellowship.Controllers;

import com.springAuth.codeFellowship.Models.ApplicationUser;
import com.springAuth.codeFellowship.Repos.ApplicationUserRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.Date;

@Controller
public class ApplicationUserController {

    @Autowired
    private ApplicationUserRepository applicationUserRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private HttpServletRequest request;

    @GetMapping("/login")
    public String getLoginPage() {
        return "login.html";
    }

    @GetMapping("/signup")
    public String getSignupPage() {
        return "signup.html";
    }

    @GetMapping("/")
    public String getIndexPage(Model model, Principal principal) {
        if (principal != null) {
            String username = principal.getName();
            ApplicationUser applicationUser = applicationUserRepository.findByUsername(username);

            model.addAttribute("username", username);
            model.addAttribute("FirstName", applicationUser.getFirstName());
            model.addAttribute("LastName", applicationUser.getLastName());
        }
        return "index.html";
    }

    @GetMapping("/test")
    public String getTestPage(Principal p, Model m) {
        if (p != null) {
            String username = p.getName();
            ApplicationUser applicationUser = applicationUserRepository.findByUsername(username);

            m.addAttribute("Username", username);
            m.addAttribute("Firstname", applicationUser.getFirstName());
            m.addAttribute("LastName", applicationUser.getLastName());

            System.out.println("Username: " + username);
            System.out.println("FirstName: " + applicationUser.getFirstName());
            System.out.println("LastName: " + applicationUser.getLastName());
        }
        return "test.html";
    }

    @GetMapping("/users/{id}")
    public String getUserInfo(Model m, Principal p, @PathVariable Long id) {
        if (p != null) {
            String username = p.getName();
            ApplicationUser applicationUser = applicationUserRepository.findByUsername(username);

            m.addAttribute("BrowsingUserUsername", username);
            m.addAttribute("BrowsingLastName", applicationUser.getLastName());
            m.addAttribute("BrowsingFirstName", applicationUser.getFirstName());
        }
        ApplicationUser applicationUser = applicationUserRepository.findById(id).orElseThrow();
        m.addAttribute("applicationUserUsername", applicationUser.getUsername());
        m.addAttribute("applicationUserLastName", applicationUser.getLastName());

        m.addAttribute("testDate", LocalDateTime.now());
        
        return "/user-info.html";
    }


    @PostMapping("/signup")
    public RedirectView postSignup(String username, String password, String firstName, String lastName, Date dateOfBirth, String bio) {
        ApplicationUser applicationUser = new ApplicationUser();
        applicationUser.setUsername(username);
        applicationUser.setFirstName(firstName);
        applicationUser.setLastName(lastName);
        applicationUser.setDateOfBirth(dateOfBirth);
        applicationUser.setBio(bio);
        String encryptedPassword = passwordEncoder.encode(password);
        applicationUser.setPassword(encryptedPassword);
        applicationUserRepository.save(applicationUser);
        authWithHttpServletRequest(username, password);
        return new RedirectView("/login");
    }

    public void authWithHttpServletRequest(String username, String password) {
        try {
            request.login(username, password);
        } catch (ServletException e) {
            System.out.println("Error while logging in.");
            e.printStackTrace();
        }
    }

}
