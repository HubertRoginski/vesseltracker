package pl.hr.vesseltracker.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import pl.hr.vesseltracker.service.user.AuthenticationUserService;

@Controller
public class HomepageController
{
    private final AuthenticationUserService authenticationUserService;

    public HomepageController(final AuthenticationUserService authenticationUserService)
    {
        this.authenticationUserService = authenticationUserService;
    }

    @GetMapping("/")
    public String homepage(final ModelMap modelMap,
        @AuthenticationPrincipal final org.springframework.security.core.userdetails.User authenticationUser)
    {
        authenticationUserService.setAuthorizationInfo(modelMap, authenticationUser);
        modelMap.addAttribute("pageName", "home");
        return "homepage";
    }

}