package hyk.springframework.lostandfoundsystem.web.controller;

import hyk.springframework.lostandfoundsystem.domain.LostFoundItem;
import hyk.springframework.lostandfoundsystem.services.LostFoundItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

/**
 * @author Htoo Yanant Khin
 **/
@RequiredArgsConstructor
@Controller
@RequestMapping("/lostFound")
public class LostFoundItemController {
    private static final String ITEM_CREATE_OR_UPDATE_FORM = "lostfound/lostFoundReportForm";

    private final LostFoundItemService lostFoundItemService;

    @GetMapping("/show")
    public String showAllLostFoundItems(Model model) {
        model.addAttribute("lostFoundItems", lostFoundItemService.findAllLostFoundItems());
        return "lostfound/allLostFoundItems";
    }

    @GetMapping("/show/{itemId}")
    public String showByItemId(@PathVariable UUID itemId, Model model) {
        model.addAttribute("lostFoundItem", lostFoundItemService.findLostFoundItemById(itemId));
        return "lostfound/lostFoundItemDetail";
    }

    @GetMapping("show/current/{accountId}")
    public String showByUserAccountId(@PathVariable UUID accountId, Model model) {
        model.addAttribute("lostFoundItemByAccountId",
                lostFoundItemService.findLostFoundItemByAccountId(accountId));
        return "lostfound/reportedItems";
    }

    @GetMapping("/new")
    public String initCreateItemForm(Model model){
        model.addAttribute("lostFoundItem", LostFoundItem.builder().build());
        return ITEM_CREATE_OR_UPDATE_FORM;
    }

    @PostMapping("/new")
    public String processCreateItemForm(@Valid @ModelAttribute("lostFoundItem") LostFoundItem lostFoundItem) {
        LostFoundItem newItem = LostFoundItem.builder()
                .type(lostFoundItem.getType())
                .title(lostFoundItem.getTitle())
                .lostFoundDate(lostFoundItem.getLostFoundDate())
                .lostFoundLocation(lostFoundItem.getLostFoundLocation())
                .description(lostFoundItem.getDescription())
                .reporterName(lostFoundItem.getReporterName())
                .reporterEmail(lostFoundItem.getReporterEmail())
                .reporterPhoneNo(lostFoundItem.getReporterPhoneNo())
                .category(lostFoundItem.getCategory())
                .createdBy(lostFoundItem.getCreatedBy())
                .modifiedBy(lostFoundItem.getModifiedBy())
                .account(lostFoundItem.getAccount()).build();

        LostFoundItem savedItem = lostFoundItemService.saveLostFoundItem(newItem);
        return "redirect:/lostFound/show/" + savedItem.getId();
    }

    @GetMapping("/edit/{itemId}")
    public String initUpdateItemForm(@PathVariable UUID itemId, Model model){
        if (lostFoundItemService.findLostFoundItemById(itemId) != null) {
            model.addAttribute("lostFoundItem", lostFoundItemService.findLostFoundItemById(itemId));
        }
        return ITEM_CREATE_OR_UPDATE_FORM;
    }

    @PostMapping("/edit/{itemId}")
    public String processUpdateItemForm(@Valid @ModelAttribute("lostFoundItem") LostFoundItem lostFoundItem, BindingResult result) {
        if (result.hasErrors()) {
            return ITEM_CREATE_OR_UPDATE_FORM;
        } else {
            LostFoundItem savedItem = lostFoundItemService.saveLostFoundItem(lostFoundItem);
            return "redirect:/lostFound/show/" + savedItem.getId();
        }
    }
}
