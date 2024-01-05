package com.dtvn.foodorderbackend.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/SSV")
public class SSVController {
    @GetMapping("/bidv/set-access_code")
    public String BIDVGetAccessCode(@RequestParam(name = "code") String accessCode){
        System.out.println(accessCode);
        return "ok";
    }

}
