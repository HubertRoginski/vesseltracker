package pl.hr.vesseltracker.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import pl.hr.vesseltracker.entity.User;
import pl.hr.vesseltracker.entity.UserSettings;
import pl.hr.vesseltracker.model.UserSettingsModel;
import pl.hr.vesseltracker.service.user.AuthenticationUserService;
import pl.hr.vesseltracker.service.user.UserService;
import pl.hr.vesseltracker.service.user.UserSettingsService;

import javax.validation.Valid;

@Controller
public class UserSettingsController
{
    private final AuthenticationUserService authenticationUserService;
    private final UserService userService;
    private final UserSettingsService userSettingsService;

    public UserSettingsController(
        final AuthenticationUserService authenticationUserService,
        final UserService userService,
        final UserSettingsService userSettingsService)
    {
        this.authenticationUserService = authenticationUserService;
        this.userService = userService;
        this.userSettingsService = userSettingsService;
    }

    @GetMapping("/user/settings")
    public String getUserSettings(final ModelMap modelMap,
        @AuthenticationPrincipal final org.springframework.security.core.userdetails.User authenticationUser)
    {
        authenticationUserService.setAuthorizationInfo(modelMap, authenticationUser);
        modelMap.addAttribute("pageName", "settings");
        modelMap.addAttribute("userSettings", getUserSettingsModel(modelMap, authenticationUser));
        return "user-settings";
    }

    private UserSettingsModel getUserSettingsModel(final ModelMap modelMap,
        final org.springframework.security.core.userdetails.User authenticationUser)
    {
        final User user = userService.getByUsernameOrEmail(authenticationUser.getUsername());
        final UserSettings userSettings = userSettingsService.getUserSettings(user.getId());
        return new UserSettingsModel(
            userSettings.isShowTime(),
            userSettings.isShowCoordinates(),
            userSettings.isShowCog(),
            userSettings.isShowSog(),
            userSettings.isShowRot(),
            userSettings.isShowNavstat(),
            userSettings.isShowEta(),
            userSettings.isShowDestination(),
            userSettings.isShowHeading(),
            userSettings.isShowDraught(),
            userSettings.isShowSurvey());
    }

    @PostMapping("/user/settings")
    public String saveSettings(@Valid @ModelAttribute("userSettings") final UserSettingsModel userSettingsModel,
        @AuthenticationPrincipal final org.springframework.security.core.userdetails.User authenticationUser)
    {
        final User user = userService.getByUsernameOrEmail(authenticationUser.getUsername());
        if(user != null)
        {
            userSettingsService.saveUserSettings(user.getId(), userSettingsModel);
            return "redirect:/user/settings";
        }
        return "error";
    }

}
