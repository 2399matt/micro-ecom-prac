package com.example.auth.exception;

import java.util.List;

public record ArgumentErrorResponse(String message, List<String> details) {
}
