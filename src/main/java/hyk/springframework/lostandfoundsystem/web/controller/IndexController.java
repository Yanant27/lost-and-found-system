package hyk.springframework.lostandfoundsystem.web.controller;

import hyk.springframework.lostandfoundsystem.enums.Type;
import hyk.springframework.lostandfoundsystem.services.LostFoundItemService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author Htoo Yanant Khin
 **/
@Slf4j
@RequiredArgsConstructor
@Controller
public class IndexController {
    private final LostFoundItemService lostFoundItemService;

    @GetMapping({"", "/", "/index"})
    public String countLostItem(Model model) {
        log.debug("Index Controller - Count lost/found items");
        model.addAttribute("lostItemsCount",
                lostFoundItemService.countItemByType(Type.LOST));
        model.addAttribute("foundItemsCount",
                lostFoundItemService.countItemByType(Type.FOUND));
        return "index";
    }
}
