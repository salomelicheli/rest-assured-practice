package ge.tbc.itacademy.data.models.requests;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Getter
@Setter
@EqualsAndHashCode
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BookingRequest {
    private String firstname;
    private String additionalneeds;
    private BookingDatesRequest bookingdates;
    private int totalprice;
    private boolean depositpaid;
    private String lastname;
    @JsonIgnore
    private int salesPrice;
    private String passportNo;
}
