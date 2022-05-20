package hyk.springframework.lostandfoundsystem.web.controller;

import hyk.springframework.lostandfoundsystem.domain.LostFoundItem;
import hyk.springframework.lostandfoundsystem.domain.security.User;
import hyk.springframework.lostandfoundsystem.exceptions.ResourceNotFoundException;
import hyk.springframework.lostandfoundsystem.repositories.security.UserRepository;
import hyk.springframework.lostandfoundsystem.security.permission.LostFoundItemCreate;
import hyk.springframework.lostandfoundsystem.security.permission.LostFoundItemDelete;
import hyk.springframework.lostandfoundsystem.security.permission.LostFoundItemRead;
import hyk.springframework.lostandfoundsystem.security.permission.LostFoundItemUpdate;
import hyk.springframework.lostandfoundsystem.services.LostFoundItemService;
import hyk.springframework.lostandfoundsystem.services.UserInfoService;
import hyk.springframework.lostandfoundsystem.util.LoginUserUtil;
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
    private static final String ALL_LOST_FOUND_ITEMS = "lostfound/allLostFoundItems";

    private final LostFoundItemService lostFoundItemService;
    private final UserInfoService userDetailInfoService;
    private final UserRepository userRepository;

    @LostFoundItemRead
    @GetMapping("/show")
    public String showAllLostFoundItems(Model model) {
        model.addAttribute("lostFoundItems", lostFoundItemService.findAllLostFoundItems());
        return ALL_LOST_FOUND_ITEMS;
    }

    @LostFoundItemRead
    @GetMapping("show/current/{userId}")
    public String showByUserAccountId(@PathVariable Integer userId, Model model) {
        model.addAttribute("lostFoundItems",
                lostFoundItemService.findAllLostFoundItemByUserId(userId));
        return ALL_LOST_FOUND_ITEMS;
    }

    @LostFoundItemRead
    @GetMapping("/show/{itemId}")
    public String showByItemId(@PathVariable("itemId") UUID itemId, Model model) {
        model.addAttribute("lostFoundItem", lostFoundItemService.findLostFoundItemById(itemId));
        return "lostfound/lostFoundItemDetail";
    }

    @LostFoundItemCreate
    @GetMapping("/new")
    public String initCreateItemForm(Model model){
        model.addAttribute("lostFoundItem", LostFoundItem.builder().build());
        return ITEM_CREATE_OR_UPDATE_FORM;
    }

    @LostFoundItemCreate
    @PostMapping("/new")
    public String processCreateItemForm(@Valid @ModelAttribute("lostFoundItem") LostFoundItem lostFoundItem) {
        // Get logged in user info
        User user = LoginUserUtil.getLoginUser();

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
                .createdBy(user.getUsername())
                .modifiedBy(user.getUsername())
                .user(userRepository.findById(user.getId()).orElseThrow(ResourceNotFoundException::new)).build();

        LostFoundItem savedItem = lostFoundItemService.saveLostFoundItem(newItem);
        return "redirect:/lostFound/show/" + savedItem.getId();
//        return "redirect:/lostFound/show";
    }

    @LostFoundItemUpdate
    @GetMapping("/edit/{itemId}")
    public String initUpdateItemForm(@PathVariable UUID itemId, Model model){
        if (lostFoundItemService.findLostFoundItemById(itemId) != null) {
            model.addAttribute("lostFoundItem", lostFoundItemService.findLostFoundItemById(itemId));
        }
        return ITEM_CREATE_OR_UPDATE_FORM;
    }

    @LostFoundItemUpdate
    @PostMapping("/edit/{itemId}")
    public String processUpdateItemForm(@Valid LostFoundItem lostFoundItem, BindingResult result) {
        if (result.hasErrors()) {
            return ITEM_CREATE_OR_UPDATE_FORM;
        } else {
            // Get logged in user info
            User user = LoginUserUtil.getLoginUser();

            lostFoundItem.setModifiedBy(user.getUsername());
            LostFoundItem savedItem = lostFoundItemService.saveLostFoundItem(lostFoundItem);
            return "redirect:/lostFound/show/" + savedItem.getId();
//            return "redirect:/lostFound/show";
        }
    }

    @LostFoundItemDelete
    @GetMapping("/delete/{itemId}")
    public String deleteItem(@PathVariable UUID itemId) {
        lostFoundItemService.deleteLostFoundItemById(itemId);
        return "redirect:/lostFound/show/";
    }
}
