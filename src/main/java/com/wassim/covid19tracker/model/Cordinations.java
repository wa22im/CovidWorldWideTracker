package com.wassim.covid19tracker.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@NoArgsConstructor
public class Cordinations {
    String state ;
    String country ;
    int numberOfCases ;
}
