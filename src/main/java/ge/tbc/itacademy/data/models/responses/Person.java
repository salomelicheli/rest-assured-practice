package ge.tbc.itacademy.data.models.responses;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonPropertyOrder({"name", "height", "mass", "hair_color", "skin_color", "eye_color", "birth_year",
        "gender", "homeworld", "films", "species", "vehicles", "starships", "created", "edited"})
@AllArgsConstructor
public class Person {
    @JsonProperty("name")
    private String name;
    @JsonProperty("height")
    private int height;
    @JsonProperty("mass")
    private double mass;
    @JsonProperty("hair_color")
    private String hairColor;
    @JsonProperty("skin_color")
    private String skinColor;
    @JsonProperty("eye_color")
    private String eyeColor;
    @JsonProperty("birth_year")
    private String birthYear;
    @JsonProperty("gender")
    private String gender;
    @JsonProperty("homeworld")
    private String homeworld;
    @JsonProperty("films")
    private List<String> films;
    @JsonProperty("species")
    private List<String> species;
    @JsonProperty("vehicles")
    private List<String> vehicles;
    @JsonProperty("starships")
    private List<String> starships;
    @JsonProperty("created")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSSSS'Z'")
    private LocalDateTime created;
    @JsonProperty("edited")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSSSS'Z'")
    private LocalDateTime edited;
    @JsonProperty("url")
    private String url;
}
