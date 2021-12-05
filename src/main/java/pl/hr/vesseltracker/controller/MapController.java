package pl.hr.vesseltracker.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.hr.vesseltracker.service.map.TrackService;
import pl.hr.vesseltracker.service.user.AuthenticationUserService;
import pl.hr.vesseltracker.service.user.UserService;

@Controller
public class MapController
{
    private final UserService userService;
    private final AuthenticationUserService authenticationUserService;
    private final TrackService trackService;

    public MapController(
        final UserService userService,
        final AuthenticationUserService authenticationUserService,
        final TrackService trackService)
    {
        this.userService = userService;
        this.authenticationUserService = authenticationUserService;
        this.trackService = trackService;
    }

    @GetMapping("/map")
    public String getMap(
        final ModelMap modelMap,
        @AuthenticationPrincipal final org.springframework.security.core.userdetails.User authenticationUser,
        @RequestParam(required = false) final Integer vesselMmsi)
    {
        authenticationUserService.setAuthorizationInfo(modelMap, authenticationUser);
        modelMap.addAttribute("pageName", "map");
        modelMap.addAttribute("tracks", trackService.getTracks());
        modelMap.addAttribute("vesselMmsi", vesselMmsi);
        return "map";
    }

}
