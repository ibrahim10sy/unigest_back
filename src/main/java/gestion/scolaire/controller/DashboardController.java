package gestion.scolaire.controller;

import org.springframework.web.bind.annotation.*;

import gestion.scolaire.dto.DashboardStatsDTO;
import gestion.scolaire.service.DashboardService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/dashboard")
@RequiredArgsConstructor
public class DashboardController {

    private final DashboardService dashboardService;

    @GetMapping("/stats")
    public DashboardStatsDTO getStats() {
        return dashboardService.getDashboardStats();
    }
}
