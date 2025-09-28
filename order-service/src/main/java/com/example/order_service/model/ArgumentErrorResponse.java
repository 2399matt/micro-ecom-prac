package com.example.order_service.model;

import java.util.List;

public record ArgumentErrorResponse(String error, List<String> errorDetails) {
}
