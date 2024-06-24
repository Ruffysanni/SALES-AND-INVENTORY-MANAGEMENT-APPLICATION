package com.INGRYD.INGRYD_CRM.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1")
public class LandingPageController {

    @GetMapping("/home")
    public String landingPage() {
        return "homepage";
    }
    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }
    @GetMapping("/admin/dashboard")
    public String adminLandingPage() {
        return "admin_landing_page";
    }
    @GetMapping("/salesManager/dashboard")
    public String SalesManagerLandingPage() {
        return "Sales Manager landing_page";
    }
}
