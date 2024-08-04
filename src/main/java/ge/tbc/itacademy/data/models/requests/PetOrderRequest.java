package ge.tbc.itacademy.data.models.requests;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.*;
import lombok.experimental.Accessors;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@Accessors(chain = true, fluent = true)
@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
@JsonPropertyOrder({"id", "petId", "quantity", "shipDate", "status", "complete"})
public class PetOrderRequest {
    @JsonProperty("petId")
    private int petId;
    @JsonProperty("quantity")
    private int quantity;
    @JsonProperty("id")
    private int id;
    @JsonProperty("shipDate")
    private String shipDate;
    @JsonProperty("complete")
    private boolean complete;
    @JsonProperty("status")
    private String status;
}
