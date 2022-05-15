package hyk.springframework.lostandfoundsystem.domain;

import hyk.springframework.lostandfoundsystem.enums.State;
import lombok.*;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;

/**
 * @author Htoo Yanant Khin
 **/
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class Address {
    @Embedded
    private State state;

    private String city;

    private String street;
}
