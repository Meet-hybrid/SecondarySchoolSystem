package com.hybrid.SecondarySchoolSystem.controller;

import com.hybrid.SecondarySchoolSystem.dto.request.UploadResultRequestDTO;
import com.hybrid.SecondarySchoolSystem.dto.response.ResultResponseDTO;
import com.hybrid.SecondarySchoolSystem.entity.Result;
import com.hybrid.SecondarySchoolSystem.service.ResultService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/result")
public class ResultController {

    private final ResultService resultService;

    public ResultController(ResultService resultService) {
        this.resultService = resultService;
    }

    @PostMapping("/upload")
    public ResponseEntity<ResultResponseDTO> uploadResult(@RequestBody UploadResultRequestDTO dto) {
        Result result = resultService.uploadResult(
                dto.getStudentId(),
                dto.getSubject(),
                dto.getScore(),
                dto.getGrade()
        );
        return ResponseEntity.status(HttpStatus.CREATED).body(convertToDTO(result));
    }

    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<ResultResponseDTO>> getStudentResults(@PathVariable String studentId) {
        List<Result> results = resultService.getStudentResults(studentId);
        return ResponseEntity.ok(results.stream().map(this::convertToDTO).collect(Collectors.toList()));
    }

    @GetMapping("/all")
    public ResponseEntity<List<ResultResponseDTO>> getAllResults() {
        List<Result> results = resultService.getAllResults();
        return ResponseEntity.ok(results.stream().map(this::convertToDTO).collect(Collectors.toList()));
    }

    @GetMapping("/student/{studentId}/subject/{subject}")
    public ResponseEntity<ResultResponseDTO> getResultByStudentAndSubject(
            @PathVariable String studentId,
            @PathVariable String subject) {
        Result result = resultService.getResultByStudentAndSubject(studentId, subject);
        if (result != null) {
            return ResponseEntity.ok(convertToDTO(result));
        }
        return ResponseEntity.notFound().build();
    }

    private ResultResponseDTO convertToDTO(Result result) {
        return new ResultResponseDTO(
                result.getId(),
                result.getStudentId(),
                result.getSubject(),
                result.getScore(),
                result.getGrade()
        );
    }
}
