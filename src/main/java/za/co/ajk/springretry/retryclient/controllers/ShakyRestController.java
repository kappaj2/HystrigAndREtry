package za.co.ajk.springretry.retryclient.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import za.co.ajk.springretry.retryclient.services.ShakyBusinessServiceImpl;

@RestController
public class ShakyRestController {
 
    private final ShakyBusinessServiceImpl shakyBusinessService;
    
    @Autowired
    public ShakyRestController(ShakyBusinessServiceImpl shakyBusinessService) {
        this.shakyBusinessService = shakyBusinessService;
    }
    
    @GetMapping("/boom")
    public double boom() throws Exception{
        int offset = 10; // not used at the moment
        return shakyBusinessService.deriveNumber(offset);
    }
}
