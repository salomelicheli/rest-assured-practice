package ge.tbc.itacademy.data.models.responses;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import lombok.*;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@Accessors(chain = true, fluent = true)
@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
@JsonPropertyOrder({"id", "petId", "quantity", "shipDate", "status", "complete"})
public class PetOrderResponse {
    @JsonProperty("petId")
    private int petId;
    @JsonProperty("quantity")
    private int quantity;
    @JsonProperty("id")
    private int id;
    @JsonProperty("shipDate")
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX", shape = JsonFormat.Shape.STRING)
    private LocalDateTime shipDate;
    @JsonProperty("complete")
    private boolean complete;
    @JsonProperty("status")
    private String status;
}
