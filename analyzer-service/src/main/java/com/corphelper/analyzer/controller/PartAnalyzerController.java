package com.corphelper.analyzer.controller;

import com.corphelper.analyzer.service.PartAnalyzerService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Data
@RequiredArgsConstructor
public class PartAnalyzerController {

    private final PartAnalyzerService partAnalyzerService;




}
