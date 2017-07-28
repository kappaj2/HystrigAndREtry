package za.co.ajk.springretry.retryclient.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import za.co.ajk.springretry.retryclient.services.ShakyBusinessService;

@RestController
public class ShakyRestController {
 
    private final ShakyBusinessService shakyBusinessService;
    
    @Autowired
    public ShakyRestController(ShakyBusinessService shakyBusinessService) {
        this.shakyBusinessService = shakyBusinessService;
    }
    
    @GetMapping("/boom")
    public double boom() throws Exception{
        return shakyBusinessService.deriveNumber();
    }
}
