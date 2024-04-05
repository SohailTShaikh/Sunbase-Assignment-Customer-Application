package com.SunbaseAssignment.CRUDAppForCustomer.DTO;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDTO {

    @NotEmpty(message = "This field is required")
    private String firstName;

    @NotEmpty(message = "This field is required")
    private String lastName;

    @NotEmpty(message = "This field is required")
    private  String streetName;

    @NotEmpty(message = "This field is required")
    private String address;

    @NotEmpty(message = "This field is required")
    private String city;

    @NotEmpty(message = "This field is required")
    private String state;

    @NotEmpty(message = "This field is required")
    private String email;

    @NotNull(message = "This field is required")
    private Double phoneNumber;

}
