package com;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomersController {
    @Autowired
    CustomersScoresServiceImpl customersScoresService;

    @RequestMapping("/CustomerAverageScore")
    public Double getCustomerAverageScore(@RequestParam String id)
    {
        return customersScoresService.getAverageScore(id);
    }

}
