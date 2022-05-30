package hyk.springframework.lostandfoundsystem.bootstrap;

import hyk.springframework.lostandfoundsystem.domain.Address;
import hyk.springframework.lostandfoundsystem.domain.LostFoundItem;
import hyk.springframework.lostandfoundsystem.domain.security.Authority;
import hyk.springframework.lostandfoundsystem.domain.security.Role;
import hyk.springframework.lostandfoundsystem.domain.security.User;
import hyk.springframework.lostandfoundsystem.enums.Category;
import hyk.springframework.lostandfoundsystem.enums.State;
import hyk.springframework.lostandfoundsystem.enums.Type;
import hyk.springframework.lostandfoundsystem.repositories.LostFoundItemRepository;
import hyk.springframework.lostandfoundsystem.repositories.security.AuthorityRepository;
import hyk.springframework.lostandfoundsystem.repositories.security.RoleRepository;
import hyk.springframework.lostandfoundsystem.repositories.security.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Htoo Yanant Khin
 **/
@Slf4j
@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {

    private final LostFoundItemRepository lostFoundItemRepository;
    private final AuthorityRepository authorityRepository;
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        loadData();
    }

    private void loadData() {
        // Create lost/found item authority for admin
        Authority createAdmin = authorityRepository.save(Authority.builder().permission("CREATE_ADMIN").build());
        Authority readAdmin = authorityRepository.save(Authority.builder().permission("READ_ADMIN").build());
        Authority updateAdmin = authorityRepository.save(Authority.builder().permission("UPDATE_ADMIN").build());
        Authority deleteAdmin = authorityRepository.save(Authority.builder().permission("DELETE_ADMIN").build());

        Authority createUser = authorityRepository.save(Authority.builder().permission("CREATE_USER").build());
        Authority readUser = authorityRepository.save(Authority.builder().permission("READ_USER").build());
        Authority updateuser = authorityRepository.save(Authority.builder().permission("UPDATE_USER").build());
        Authority deleteUser = authorityRepository.save(Authority.builder().permission("DELETE_USER").build());

        // Create admin and user role
        Role adminRole = roleRepository.save(Role.builder().name("ADMIN").build());
        Role userRole = roleRepository.save(Role.builder().name("USER").build());

        adminRole.setAuthorities(new HashSet<>(Set.of(createAdmin, readAdmin, updateAdmin, deleteAdmin)));
        userRole.setAuthorities(new HashSet<>(Set.of(createUser, readUser, updateuser, deleteUser)));

        roleRepository.saveAll(Arrays.asList(adminRole, userRole));
        log.debug("Role Data loaded. Total Count: " + roleRepository.count());
        log.debug("Authority Data loaded. Total Count: " + authorityRepository.count());

        LostFoundItem item1 = LostFoundItem.builder()
                .type(Type.LOST)
                .title("Missing a wrist watch")
                .lostFoundDate(LocalDate.parse("2022-02-11"))
                .lostFoundLocation("Room 21, Building 12")
                .reporterName("War War")
                .reporterEmail("warwar@gmail.com")
                .reporterPhoneNo("09111222333")
                .createdBy("warwar")
                .modifiedBy("warwar")
                .category(Category.ACCESSORIES)
                .description("Lorem ipsum dolor sit amet, consectetur adipiscing elit, " +
                        "sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.")
                .build();

        LostFoundItem item2 = LostFoundItem.builder()
                .type(Type.FOUND)
                .title("Hey ! I found a power-bank")
                .lostFoundDate(LocalDate.parse("2022-02-03"))
                .lostFoundLocation("Library")
                .reporterName("Lin Htet")
                .reporterEmail("linhtet@gmail.com")
                .reporterPhoneNo("09111222444")
                .createdBy("linhtet")
                .modifiedBy("linhtet")
                .category(Category.ELECTRONIC)
                .description("Lorem ipsum dolor sit amet, consectetur adipiscing elit, " +
                        "sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.")
                .build();

        LostFoundItem item3 = LostFoundItem.builder()
                .type(Type.LOST)
                .title("I missed a key")
                .lostFoundDate(LocalDate.parse("2022-02-19"))
                .lostFoundLocation("Room 10, building 10")
                .reporterName("Shine")
                .reporterEmail("shine@gmail.com")
                .reporterPhoneNo("09111222555")
                .createdBy("shine")
                .modifiedBy("shine")
                .category(Category.OTHERS)
                .description("Lorem ipsum dolor sit amet, consectetur adipiscing elit, " +
                        "sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.")
                .build();

        LostFoundItem item4 = LostFoundItem.builder()
                .type(Type.FOUND)
                .title("Who is missing a raincoat?")
                .lostFoundDate(LocalDate.parse("2022-02-26"))
                .lostFoundLocation("Room 9, building 21")
                .reporterName("Eaint Chit")
                .reporterEmail("eaintchit@gmail.com")
                .reporterPhoneNo("09111222666")
                .createdBy("eaintchit")
                .modifiedBy("eaintchit")
                .category(Category.CLOTHING)
                .description("Lorem ipsum dolor sit amet, consectetur adipiscing elit, " +
                        "sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.")
                .build();

        LostFoundItem item5 = LostFoundItem.builder()
                .type(Type.LOST)
                .title("Does anyone find a pair of black lady shoes?")
                .lostFoundDate(LocalDate.parse("2022-03-11"))
                .lostFoundLocation("Near building 12")
                .reporterName("Khin Pyone")
                .reporterEmail("khinpyone@gmail.com")
                .reporterPhoneNo("09111222777")
                .createdBy("khinpyone")
                .modifiedBy("khinpyone")
                .category(Category.FOOTWEAR)
                .description("Lorem ipsum dolor sit amet, consectetur adipiscing elit, " +
                        "sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.")
                .build();

        LostFoundItem item6 = LostFoundItem.builder()
                .type(Type.FOUND)
                .title("Someone left her shoes in front of my room")
                .lostFoundDate(LocalDate.parse("2022-03-11"))
                .lostFoundLocation("Room 2, building 12")
                .reporterName("Ye Aung")
                .reporterEmail("yeaung@gmail.com")
                .reporterPhoneNo("09111222888")
                .createdBy("yeaung")
                .modifiedBy("yeaung")
                .category(Category.FOOTWEAR)
                .description("Lorem ipsum dolor sit amet, consectetur adipiscing elit, " +
                        "sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.")
                .build();

        User admin = userRepository.save(
                User.builder()
                        .username("admin")
                        .password(passwordEncoder.encode("Admin11@"))
                        .confirmedPassword("Admin11@")
                        .role(adminRole)
                        .fullName("Admin")
                        .phoneNumber("09123456789")
                        .email("hykadmin@gmail.com")
                        .address(Address.builder().city("Mudon").state(State.MON).street("Aung Thiri Street").build())
                        .build());


        User user1 = User.builder()
                .username("warwar")
                .password(passwordEncoder.encode("Warwar11@"))
                .confirmedPassword("Warwar11@")
                .role(userRole)
                .fullName("War War")
                .phoneNumber("09111222333")
                .email("warwar@gmail.com")
                .address(Address.builder().city("Mudon").state(State.MON).street("Aung Thiri Street").build())
                .lostFoundItems(List.of(item1))
                .build();

        User user2 = User.builder()
                .username("linhtet")
                .password(passwordEncoder.encode("Linhtet11@"))
                .confirmedPassword("Linhtet11@")
                .role(userRole)
                .fullName("Lin Htet")
                .phoneNumber("09111222444")
                .email("linhtet@gmail.com")
                .address(Address.builder().city("Mudon").state(State.MON).street("Aung Thiri Street").build())
                .lostFoundItems(List.of(item2))
                .build();

        User user3 = User.builder()
                .username("shine")
                .password(passwordEncoder.encode("Shine11@"))
                .confirmedPassword("Shine11@")
                .role(userRole)
                .fullName("Shine")
                .phoneNumber("09111222555")
                .email("shine@gmail.com")
                .address(Address.builder().city("Mudon").state(State.MON).street("Aung Thiri Street").build())
                .lostFoundItems(List.of(item3))
                .build();

        User user4 = User.builder()
                .username("eaintchit")
                .password(passwordEncoder.encode("Eaint11@"))
                .confirmedPassword("Shine11@")
                .role(userRole)
                .fullName("EaintChit")
                .phoneNumber("09111222666")
                .email("eaintchit@gmail.com")
                .address(Address.builder().city("Mudon").state(State.MON).street("Aung Thiri Street").build())
                .lostFoundItems(List.of(item4))
                .build();

        User user5 = User.builder()
                .username("khinpyone")
                .password(passwordEncoder.encode("Khinpyone11@"))
                .confirmedPassword("Khinpyone11@")
                .role(userRole)
                .fullName("Khin Pyone")
                .phoneNumber("09111222777")
                .email("khinpyone@gmail.com")
                .address(Address.builder().city("Mudon").state(State.MON).street("Aung Thiri Street").build())
                .lostFoundItems(List.of(item5))
                .build();

        User user6 = User.builder()
                .username("yeaung")
                .password(passwordEncoder.encode("Yeaung11@"))
                .confirmedPassword("Yeaung11@")
                .role(userRole)
                .fullName("Ye Aung")
                .phoneNumber("09111222888")
                .email("yeaung@gmail.com")
                .address(Address.builder().city("Mudon").state(State.MON).street("Aung Thiri Street").build())
                .lostFoundItems(List.of(item6))
                .build();

        item1.setUser(user1);
        item2.setUser(user2);
        item3.setUser(user3);
        item4.setUser(user4);
        item5.setUser(user5);
        item6.setUser(user6);

        userRepository.saveAll(Arrays.asList(admin, user1, user2, user3, user4, user5, user6));

        log.debug("Lost/Found Data Loaded. Total: " + lostFoundItemRepository.count());
        log.debug("User Data Loaded: " + userRepository.count());

//        lostFoundItemRepository.save(item1);
//        lostFoundItemRepository.save(item2);
//        lostFoundItemRepository.save(item3);
//        lostFoundItemRepository.save(item4);
//        lostFoundItemRepository.save(item5);
//        lostFoundItemRepository.save(item6);
    }
}
