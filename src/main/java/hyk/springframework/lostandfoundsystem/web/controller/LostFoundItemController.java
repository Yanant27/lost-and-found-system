package hyk.springframework.lostandfoundsystem.web.controller;

import hyk.springframework.lostandfoundsystem.domain.LostFoundItem;
import hyk.springframework.lostandfoundsystem.domain.security.User;
import hyk.springframework.lostandfoundsystem.services.LostFoundItemService;
import hyk.springframework.lostandfoundsystem.util.LoginUserUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.UUID;

/**
 * @author Htoo Yanant Khin
 **/
@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping("/lostFound")
public class LostFoundItemController {
    private static final String ITEM_CREATE_OR_UPDATE_FORM = "lostfound/lostFoundReportForm";
    private static final String ALL_LOST_FOUND_ITEMS = "lostfound/allLostFoundItems";

    private final LostFoundItemService lostFoundItemService;

    @GetMapping("/show")
    public String showAllLostFoundItems(Model model) {
        log.debug("LostFoundItem Controller - Show all lost/found items");
        model.addAttribute("lostFoundItems", lostFoundItemService.findAllItems());
        return ALL_LOST_FOUND_ITEMS;
    }

    @GetMapping("show/current/{userId}")
    public String showByUserId(@PathVariable Integer userId, Model model) {
        log.debug("LostFoundItem Controller - Show all lost/found items by user ID: " + userId);
        model.addAttribute("lostFoundItems",
                lostFoundItemService.findAllItemsByUserId(userId));
        return ALL_LOST_FOUND_ITEMS;
    }

    @GetMapping("/show/{itemId}")
    public String showByItemId(@PathVariable("itemId") UUID itemId, Model model) {
        log.debug("LostFoundItem Controller - Show lost/found item by item ID: " + itemId);
        model.addAttribute("lostFoundItem", lostFoundItemService.findItemById(itemId));
        return "lostfound/lostFoundItemDetail";
    }

    // admin, user
    @GetMapping("/new")
    public String initCreateItemForm(Model model) {
        log.debug("LostFoundItem Controller - Show lost/found report creation form");
        model.addAttribute("lostFoundItem", LostFoundItem.builder().build());
        return ITEM_CREATE_OR_UPDATE_FORM;
    }

    @PostMapping("/new")
    // @Valid parameter must be followed by BindingResult parameter
    public String processCreateItemForm(@Valid LostFoundItem lostFoundItem, BindingResult result) {
        log.debug("LostFoundItem Controller - Process lost/found report creation - Start");

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
                .modifiedBy(user.getUsername()).build();
//                .user(userService.findUserById(user.getId())).build();

        if (result.hasErrors()) {
            return ITEM_CREATE_OR_UPDATE_FORM;
        } else {
            LostFoundItem savedItem = lostFoundItemService.saveItem(newItem);
            log.debug("LostFoundItem Controller - Process lost/found report creation - End");
            return "redirect:/lostFound/show/" + savedItem.getId();
        }
    }

    @GetMapping("/edit/{itemId}")
    public String initUpdateItemForm(@PathVariable UUID itemId, Model model) {
        log.debug("LostFoundItem Controller - Show lost/found report update form - Start");
        LostFoundItem lostFoundItem = lostFoundItemService.findItemById(itemId);
        checkPermission(lostFoundItem);
        model.addAttribute("lostFoundItem", lostFoundItem);
        return ITEM_CREATE_OR_UPDATE_FORM;
    }

    @PostMapping("/edit")
    // @Valid parameter must be followed by BindingResult parameter
    public String processUpdateItemForm(@Valid LostFoundItem lostFoundItem, BindingResult result) {
        log.debug("LostFoundItem Controller - Process lost/found report update - Start");
        if (result.hasErrors()) {
            return ITEM_CREATE_OR_UPDATE_FORM;
        } else {
            LostFoundItem savedItem = lostFoundItemService.saveItem(lostFoundItem);
            log.debug("LostFoundItem Controller - Process lost/found report update -End");
            return "redirect:/lostFound/show/" + savedItem.getId();
        }
    }

    @GetMapping("/delete/{itemId}")
    public String deleteItem(@PathVariable UUID itemId) {
        checkPermission(lostFoundItemService.findItemById(itemId));
        lostFoundItemService.deleteItemById(itemId);
        log.debug("LostFoundItem Controller - Delete lost/found report by ID: " + itemId);
        return "redirect:/lostFound/show/";
    }

    /**
     * To ensure that "USER" role can only have access to its data, not other user's data
     * "ADMIN" role can have access to all users' data
     */
    private void checkPermission(LostFoundItem lostFoundItem) {
        log.debug("LostFoundItem Controller - Check permission");
        if (!LoginUserUtil.isAdmin() &&
                !lostFoundItem.getUser().getId().equals(LoginUserUtil.getLoginUser().getId())) {
            throw new AccessDeniedException("You don't have the permission to perform " +
                    "this operation on other user's data");
        }
    }
}
