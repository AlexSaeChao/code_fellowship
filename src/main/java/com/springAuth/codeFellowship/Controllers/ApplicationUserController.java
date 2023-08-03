package com.springAuth.codeFellowship.Controllers;

import com.springAuth.codeFellowship.Models.Post;
import com.springAuth.codeFellowship.Models.ApplicationUser;
import com.springAuth.codeFellowship.Repos.ApplicationUserRepository;
import com.springAuth.codeFellowship.Repos.PostRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Controller
public class ApplicationUserController {

    @Autowired
    private ApplicationUserRepository applicationUserRepository;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private HttpServletRequest request;

    // Get Mapping Methods

    @GetMapping("/login")
    public String getLoginPage() {
        return "login.html";
    }

    @GetMapping("/signup")
    public String getSignupPage() {
        return "signup.html";
    }

    @GetMapping("/users")
    public String getAllUsers(Model model) {
        List<ApplicationUser> users = applicationUserRepository.findAll();
        model.addAttribute("users", users);
        return "showallusers.html";
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
        // throw new ResourceNotFoundException("It's a 404");

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
        m.addAttribute("applicationUserFirstName", applicationUser.getFirstName());
        m.addAttribute("applicationUserLastName", applicationUser.getLastName());
        m.addAttribute("applicationUserId", applicationUser.getId());

        m.addAttribute("testDate", LocalDateTime.now());

        List<Post> posts = applicationUser.getPosts(); // Fetch the posts associated with the applicationUser
        m.addAttribute("posts", posts);

        return "/user-info.html";
    }

    // Post Mapping Methods

    @PostMapping("/create-post")
    public RedirectView createPost(Principal principal, String body) {
        if (principal != null) {
            String username = principal.getName();
            ApplicationUser applicationUser = applicationUserRepository.findByUsername(username);

            Post post = new Post(body, applicationUser);
            post.setBody(body);
            post.setCreatedAt(LocalDateTime.now());
            post.setApplicationUser(applicationUser);
            postRepository.save(post);
        }
        Long userId = applicationUserRepository.findByUsername(principal.getName()).getId();

        return new RedirectView("/users/" + userId);
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

    // Put Mapping Method

    @PutMapping("/users/{id}")
    public RedirectView editUserInfo(Principal p, @PathVariable Long id, String username, String firstName, String lastName) {
        if (p != null) {
            ApplicationUser applicationUser = applicationUserRepository.findById(id).orElseThrow();
            applicationUser.setUsername(username);
            applicationUser.setFirstName(firstName);
            applicationUser.setLastName(lastName);
            applicationUserRepository.save(applicationUser);
        }
        return new RedirectView("/users/" + id);
    }

    // Other Helper Methods

    public void authWithHttpServletRequest(String username, String password) {
        try {
            request.login(username, password);
        } catch (ServletException e) {
            System.out.println("Error while logging in.");
            e.printStackTrace();
        }
    }
}
