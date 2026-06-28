package edu.georgiasouthern.jetpackcompose.ui.data.model

import androidx.compose.ui.graphics.Color

enum class CardValidationState(val color: Color, val iconName: String?) {
    VALID(Color(0xFF4CAF50), "check_circle"),
    WARNING(Color(0xFFFF9800), "warning"),
    INVALID(Color(0xFFF44336), "error"),
    UNCHECKED(Color(0xFF9E9E9E), null)
}
