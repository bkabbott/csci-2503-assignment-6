package edu.georgiasouthern.jetpackcompose.ui.ui.card

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import edu.georgiasouthern.jetpackcompose.ui.data.model.CardField
import edu.georgiasouthern.jetpackcompose.ui.data.model.CardValidationState

private val iconMap: Map<String, ImageVector> by lazy {
    mapOf(
        "check_circle" to Icons.Default.CheckCircle,
        "warning" to Icons.Default.Warning,
        "error" to Icons.Default.Info,
    )
}

private fun resolveIcon(name: String): ImageVector =
    iconMap[name] ?: Icons.Default.CheckCircle

@Composable
fun CardFieldRow(field: CardField) {
    val shape = RoundedCornerShape(12.dp)
    val borderColor = field.validationState.color.copy(alpha = 0.4f)

    Surface(
        shape = shape,
        color = MaterialTheme.colorScheme.surface,
        modifier = Modifier
            .fillMaxWidth()
            .border(width = 1.dp, color = borderColor, shape = shape)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 14.dp)
        ) {
            Icon(
                imageVector = resolveIcon(field.icon),
                contentDescription = field.icon,
                tint = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.size(24.dp)
            )

            Spacer(modifier = Modifier.width(12.dp))

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = field.label,
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    fontSize = 12.sp
                )
                Text(
                    text = field.value,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }

            field.validationState.iconName?.let { iconName ->
                Spacer(modifier = Modifier.width(8.dp))
                Icon(
                    imageVector = resolveIcon(iconName),
                    contentDescription = iconName,
                    tint = field.validationState.color,
                    modifier = Modifier.size(20.dp)
                )
            }
        }
    }
}
