package hyk.springframework.lostandfoundsystem.bootstrap;

import hyk.springframework.lostandfoundsystem.domain.Account;
import hyk.springframework.lostandfoundsystem.domain.LostFoundItem;
import hyk.springframework.lostandfoundsystem.enums.Category;
import hyk.springframework.lostandfoundsystem.enums.Type;
import hyk.springframework.lostandfoundsystem.repositories.LostFoundItemRepository;
import hyk.springframework.lostandfoundsystem.repositories.UserAccountInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

/**
 * @author Htoo Yanant Khin
 **/
@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {
    private final LostFoundItemRepository lostFoundItemRepository;
    private final UserAccountInfoRepository userAccountInfoRepository;

    @Override
    public void run(String... args) throws Exception {
        loadData();
    }

    private void loadData() {
        Account user1 = Account.builder().build();
        Account user2 = Account.builder().build();

        userAccountInfoRepository.save(user1);
        userAccountInfoRepository.save(user2);

        LostFoundItem item1 = LostFoundItem.builder()
                .type(Type.LOST)
                .title("Missing a wrist watch")
                .lostFoundDate(LocalDate.parse("2022-02-11"))
                .lostFoundLocation("Room 21, Building 12")
                .reporterName("War War")
                .reporterEmail("warwar@gmail.com")
                .reporterPhoneNo("09-111222333")
                .category(Category.ACCESSORIES)
                .description("Lorem ipsum dolor sit amet, consectetur adipiscing elit, " +
                        "sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.")
                .account(user1)
                .build();

        LostFoundItem item2 = LostFoundItem.builder()
                .type(Type.FOUND)
                .title("Hey ! I found a power-bank")
                .lostFoundDate(LocalDate.parse("2022-02-03"))
                .lostFoundLocation("Library")
                .reporterName("Lin Htet")
                .reporterEmail("linhtet@gmail.com")
                .reporterPhoneNo("09-111222444")
                .category(Category.ELECTRONIC)
                .description("Lorem ipsum dolor sit amet, consectetur adipiscing elit, " +
                        "sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.")
                .account(user1)
                .build();

        LostFoundItem item3 = LostFoundItem.builder()
                .type(Type.LOST)
                .title("I missed a key")
                .lostFoundDate(LocalDate.parse("2022-02-19"))
                .lostFoundLocation("Room 10, building 10")
                .reporterName("Shine")
                .reporterEmail("shine@gmail.com")
                .reporterPhoneNo("09-111222555")
                .category(Category.OTHERS)
                .description("Lorem ipsum dolor sit amet, consectetur adipiscing elit, " +
                        "sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.")
                .account(user1)
                .build();

        LostFoundItem item4 = LostFoundItem.builder()
                .type(Type.FOUND)
                .title("Who is missing a raincoat?")
                .lostFoundDate(LocalDate.parse("2022-02-26"))
                .lostFoundLocation("Room 9, building 21")
                .reporterName("Eaint Chit")
                .reporterEmail("eaintchit@gmail.com")
                .reporterPhoneNo("09-111222666")
                .category(Category.CLOTHING)
                .description("Lorem ipsum dolor sit amet, consectetur adipiscing elit, " +
                        "sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.")
                .account(user2)
                .build();

        LostFoundItem item5 = LostFoundItem.builder()
                .type(Type.LOST)
                .title("Does anyone find a pair of black lady shoes?")
                .lostFoundDate(LocalDate.parse("2022-03-11"))
                .lostFoundLocation("Near building 12")
                .reporterName("Khin Pyone")
                .reporterEmail("khinpyone@gmail.com")
                .reporterPhoneNo("09-111222777")
                .category(Category.FOOTWEAR)
                .description("Lorem ipsum dolor sit amet, consectetur adipiscing elit, " +
                        "sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.")
                .account(user2)
                .build();

        LostFoundItem item6 = LostFoundItem.builder()
                .type(Type.FOUND)
                .title("Someone left her shoes in front of my room")
                .lostFoundDate(LocalDate.parse("2022-03-11"))
                .lostFoundLocation("Room 2, building 12")
                .reporterName("Ye Aung")
                .reporterEmail("yeaung@gmail.com")
                .reporterPhoneNo("09-111222888")
                .category(Category.FOOTWEAR)
                .description("Lorem ipsum dolor sit amet, consectetur adipiscing elit, " +
                        "sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.")
                .account(user2)
                .build();

        lostFoundItemRepository.save(item1);
        lostFoundItemRepository.save(item2);
        lostFoundItemRepository.save(item3);
        lostFoundItemRepository.save(item4);
        lostFoundItemRepository.save(item5);
        lostFoundItemRepository.save(item6);

        System.out.println("Data Loaded. Total: " + lostFoundItemRepository.count());
    }
}
