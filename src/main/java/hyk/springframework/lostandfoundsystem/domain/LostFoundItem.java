package hyk.springframework.lostandfoundsystem.domain;

import hyk.springframework.lostandfoundsystem.enums.Type;
import hyk.springframework.lostandfoundsystem.enums.backup.Category;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
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

//    @Column(name = "post_type")
    @Enumerated(EnumType.STRING)
    private Type type;

//    @Column(name = "lost_found_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate lostFoundDate;

//    @Column(name = "lost_found_location")
    private String lostFoundLocation;

//    @Column(name = "reporter_name")
    private String reporterName;

//    @Column(name = "reporter_email")
    private String reporterEmail;

//    @Column(name = "reporter_phone_no")
//    @Pattern(regexp = "^[09-]\\d{9}$")
    private String reporterPhoneNo;

    @Embedded
    private Category category;

    @Column(updatable = false)
    private String createdBy;

    private String modifiedBy;
}