package com.az.dronehub.dto.error;

import java.util.List;

public record ValidationErrorResponse(List<Violation> violations) { }

