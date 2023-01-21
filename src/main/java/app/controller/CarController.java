package app.controller;

import app.service.CarService;
import app.service.CarServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CarController {
    private final CarService carService = new CarServiceImpl();

    @GetMapping(value = "/cars")
    public String printWelcome(@RequestParam(value="count", required=false, defaultValue="-1") int count, ModelMap model) {
        model.addAttribute("cars", carService.getListByCount(count));
        return "cars";
    }
}
