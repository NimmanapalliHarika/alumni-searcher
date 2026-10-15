package com.freightfox.alumni.dto;

import jakarta.validation.constraints.NotBlank;

public record SearchRequest(
        @NotBlank(message = "University is required") String university,
        @NotBlank(message = "Designation is required") String designation,
        Integer passoutYear) {}
