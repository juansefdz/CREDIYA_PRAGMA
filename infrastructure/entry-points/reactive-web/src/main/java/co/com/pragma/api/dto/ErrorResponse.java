package co.com.pragma.api.dto;

import java.time.Instant;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class ErrorResponse {
    private final String timestamp = Instant.now().toString();
    private final int status;
    private final String error;
    private final String message;
}