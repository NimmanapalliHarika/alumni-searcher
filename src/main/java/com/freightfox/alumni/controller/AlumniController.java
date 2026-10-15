package com.freightfox.alumni.controller;

import com.freightfox.alumni.dto.ApiResponse;
import com.freightfox.alumni.dto.SearchRequest;
import com.freightfox.alumni.entity.AlumniProfile;
import com.freightfox.alumni.service.AlumniService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/alumni")
public class AlumniController {
    private final AlumniService service;
    public AlumniController(AlumniService service) { this.service = service; }

    @PostMapping("/search")
    public ApiResponse<List<AlumniProfile>> search(@Valid @RequestBody SearchRequest request) {
        return ApiResponse.success(service.search(request));
    }

    @GetMapping("/all")
    public ApiResponse<List<AlumniProfile>> all() {
        return ApiResponse.success(service.all());
    }
}
