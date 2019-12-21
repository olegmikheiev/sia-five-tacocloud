package sia.five.tacocloud.domain;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Data
public class Taco {
    @NotNull
    @Size(min = 3, message = "Name should be at least 3 characters long")
    private String name;
    @Size(min = 1, message = "You must choose at least one ingredient")
    private List<String> ingredients;
}
