package sia.five.tacocloud.domain;

import lombok.Data;
import org.hibernate.validator.constraints.CreditCardNumber;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
public class Order {
    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "Street is required")
    private String street;

    @NotBlank(message = "City is required")
    private String city;

    @NotBlank(message = "State is required")
    private String state;

    @NotBlank(message = "Zip code is required")
    private String zip;

    @Size(min = 16, message = "Card number length should be 16 digits")
    @Digits(integer = 16, fraction = 0, message = "Card number is not correct")
    @CreditCardNumber(message = "Not a valid credit card number")
    private String ccNumber;

    @Pattern(regexp = "^(0[1-9]|1[0-2])([\\\\/])([1-9][0-9])$", message = "Format should be 'MM/DD'")
    private String ccExpiration;

    @Size(min = 3, message = "CVV length should be 3 digits")
    @Digits(integer = 3, fraction = 0, message = "Invalid CVV number")
    private String ccCVV;
}
