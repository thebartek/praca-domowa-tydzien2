package it.marczuk.pracadomowa_tydzien2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("START")
public class ShopServiceStart {
    private MainService mainService;

    @Autowired
    public ShopServiceStart(MainService mainService) {
        this.mainService = mainService;
        mainService.addRandomProduct();
        System.out.println("Total price: " + mainService.getTotalPrice());
    }
}
