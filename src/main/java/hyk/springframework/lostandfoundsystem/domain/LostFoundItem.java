package hyk.springframework.lostandfoundsystem.domain;

import hyk.springframework.lostandfoundsystem.domain.security.User;
import hyk.springframework.lostandfoundsystem.enums.Category;
import hyk.springframework.lostandfoundsystem.enums.Type;
import hyk.springframework.lostandfoundsystem.validation.ValidEmail;
import hyk.springframework.lostandfoundsystem.validation.ValidPhoneNumber;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Size;
import java.time.LocalDate;

/**
 * @author Htoo Yanant Khin
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
//@AttributeOverride(name = "id", column = @Column(name = "lost_found_item_id"))
public class LostFoundItem extends BaseEntity {
    @Enumerated(EnumType.STRING)
    private Type type;

    @NotEmpty(message = "Title must be filled")
    @Size(max = 150)
    private String title;

    @NotNull(message = "Lost/Found date must be filled")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @PastOrPresent(message = "Must be equal or less than today date")
    private LocalDate lostFoundDate;

    @NotEmpty(message = "Lost/Found location must be filled")
    @Size(max = 150, message = "Must be less than 150 characters")
    private String lostFoundLocation;

    @Size(max = 255, message = "Must be less than 255 characters")
    private String description;

    @NotEmpty(message = "Reporter name must be filled")
    @Size(min = 5, max = 50, message = "Must be between 5 and 50 characters")
    private String reporterName;

    @ValidEmail
    @NotEmpty(message = "Reporter e-mail must be filled")
//    @Email
    private String reporterEmail;

    @NotEmpty(message = "Reporter phone number must be filled")
    @ValidPhoneNumber
    private String reporterPhoneNo;

    @Enumerated(EnumType.STRING)
    private Category category;

    @Column(updatable = false)
    private String createdBy;

    private String modifiedBy;

    @ManyToOne
    private User user;
}