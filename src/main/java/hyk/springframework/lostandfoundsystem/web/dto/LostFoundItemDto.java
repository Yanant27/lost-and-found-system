package hyk.springframework.lostandfoundsystem.web.dto;

import hyk.springframework.lostandfoundsystem.domain.security.User;
import hyk.springframework.lostandfoundsystem.enums.Category;
import hyk.springframework.lostandfoundsystem.enums.Type;
import hyk.springframework.lostandfoundsystem.validation.ValidPhoneNumber;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Size;
import java.time.LocalDate;

/**
 * @author Htoo Yanant Khin
 **/
public class LostFoundItemDto {
    @Enumerated(EnumType.STRING)
    private Type type;

    @NotEmpty
    @Size(max = 150)
    private String title;

    @PastOrPresent
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate lostFoundDate;

    @NotEmpty
    @Size(max = 150)
    private String lostFoundLocation;

    @Size(max = 255)
    private String description;

    @NotEmpty
    @Size(min = 5, max = 50)
    private String reporterName;

    //    @ValidEmail
    @NotEmpty
//    @Email
    private String reporterEmail;

    @NotEmpty
    @ValidPhoneNumber
    private String reporterPhoneNo;

    @Enumerated(EnumType.STRING)
    private Category category;

    @Column(updatable = false)
    private String createdBy;

    private String modifiedBy;

    private User user;
}
