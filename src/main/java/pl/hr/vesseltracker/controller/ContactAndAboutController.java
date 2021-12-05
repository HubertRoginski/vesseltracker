package pl.hr.vesseltracker.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import pl.hr.vesseltracker.service.user.AuthenticationUserService;

@Controller
public class ContactAndAboutController
{
    private final AuthenticationUserService authenticationUserService;

    public ContactAndAboutController(final AuthenticationUserService authenticationUserService)
    {
        this.authenticationUserService = authenticationUserService;
    }

    @GetMapping("/contact")
    public String contact(
        final ModelMap modelMap,
        @AuthenticationPrincipal final org.springframework.security.core.userdetails.User authenticationUser)
    {
        authenticationUserService.setAuthorizationInfo(modelMap, authenticationUser);
        return "contact";
    }

    @GetMapping("/about")
    public String about(
        final ModelMap modelMap,
        @AuthenticationPrincipal final org.springframework.security.core.userdetails.User authenticationUser)
    {
        authenticationUserService.setAuthorizationInfo(modelMap, authenticationUser);
        return "about";
    }

}
