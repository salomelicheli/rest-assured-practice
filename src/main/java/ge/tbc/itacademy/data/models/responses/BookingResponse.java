package ge.tbc.itacademy.data.models.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@JsonPropertyOrder({"firstname", "lastname", "totalprice", "depositpaid", "bookingdates", "additionalneeds"})
public class BookingResponse {
    @JsonProperty("firstname")
    private String someRandomFirstName;
    @JsonProperty("additionalneeds")
    private String guestsAdditionalNeeds;
    @JsonProperty("bookingdates")
    private BookingDatesResponse datesOfBooking;
    @JsonProperty("totalprice")
    private int prices;
    @JsonProperty("depositpaid")
    private boolean depositPaidByTheGuest;
    @JsonProperty("lastname")
    private String someRandomLastName;
}
