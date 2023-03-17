package com.example.valorantrecruiting.controller.command;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ApplicantCommand {
    @NotNull
    private Long id;

    @Min(14)
    private Integer age;


    private String role;


    private String rank;


    private Boolean isAccepted;


    @Pattern(regexp = "^https://tracker.gg/valorant/profile/riot")
    private String valorantTrackerLink;

}
