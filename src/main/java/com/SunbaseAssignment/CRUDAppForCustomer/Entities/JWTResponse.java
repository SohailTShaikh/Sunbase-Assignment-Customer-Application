package com.SunbaseAssignment.CRUDAppForCustomer.Entities;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class JWTResponse {

    private String userName;

    private String jwtToken;
}
