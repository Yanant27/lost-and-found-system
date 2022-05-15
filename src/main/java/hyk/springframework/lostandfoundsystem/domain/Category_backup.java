package hyk.springframework.lostandfoundsystem.domain;

import lombok.*;

import javax.persistence.Column;

/**
 * @author Htoo Yanant Khin
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
//@Entity
//@AttributeOverride(name = "id", column = @Column(name = "category_id"))
public class Category_backup extends BaseEntity {

    @Column(name = "category_name")
    private String categoryName;

    @Column(name = "category_description")
    private String categoryDescription;

//    @OneToMany(mappedBy = "category") // non-owning side
//    private Set<LostFoundItem> lostFoundItems;
}
