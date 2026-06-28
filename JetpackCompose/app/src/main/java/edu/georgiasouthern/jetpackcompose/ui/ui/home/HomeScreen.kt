package edu.georgiasouthern.jetpackcompose.ui.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import edu.georgiasouthern.jetpackcompose.ui.data.model.DailyReport
import edu.georgiasouthern.jetpackcompose.ui.data.model.MockDataFactory
import edu.georgiasouthern.jetpackcompose.ui.data.model.toCardStack
import edu.georgiasouthern.jetpackcompose.ui.ui.card.CardStackView
import edu.georgiasouthern.jetpackcompose.ui.ui.swipe.CardAction
import java.time.LocalDate
import java.time.format.DateTimeFormatter

private val AccentBlue = Color(0xFF1976D2)
private val SubmittedGreen = Color(0xFF4CAF50)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen() {
    var showCardReview by remember { mutableStateOf(false) }
    val reports = remember { listOf(MockDataFactory.sampleReport) }

    if (showCardReview) {
        CardReviewScreen(
            report = reports.first(),
            onBack = { showCardReview = false }
        )
    } else {
        ReportListScreen(
            reports = reports,
            onSubmitTap = { showCardReview = true }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ReportListScreen(
    reports: List<DailyReport>,
    onSubmitTap: () -> Unit
) {
    val todayReport = reports.firstOrNull()
    val isSubmitted = todayReport?.isSubmitted ?: false

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "AquaCertify Operators",
                        fontWeight = FontWeight.Bold
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surface
                )
            )
        },
        containerColor = MaterialTheme.colorScheme.background
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 16.dp)
        ) {
            Spacer(modifier = Modifier.height(8.dp))

            // Header section
            Text(
                text = "Hello, Operator",
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = LocalDate.now().format(
                    DateTimeFormatter.ofPattern("EEEE, MMMM d, yyyy")
                ),
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Spacer(modifier = Modifier.height(8.dp))

            // Status badge
            val badgeColor = if (isSubmitted) SubmittedGreen else Color(0xFFFF9800)
            val badgeText = if (isSubmitted) "Submitted" else "Pending"
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(12.dp))
                    .background(badgeColor.copy(alpha = 0.15f))
                    .padding(horizontal = 12.dp, vertical = 4.dp)
            ) {
                Text(
                    text = badgeText,
                    color = badgeColor,
                    style = MaterialTheme.typography.labelMedium,
                    fontWeight = FontWeight.SemiBold
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            // Submit button
            Button(
                onClick = { if (!isSubmitted) onSubmitTap() },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(14.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (isSubmitted) SubmittedGreen else AccentBlue
                )
            ) {
                if (isSubmitted) {
                    Icon(
                        imageVector = Icons.Default.CheckCircle,
                        contentDescription = null,
                        modifier = Modifier.size(18.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                }
                Text(
                    text = if (isSubmitted) "Today's Report Submitted" else "Submit Today's Report",
                    modifier = Modifier.padding(vertical = 4.dp)
                )
            }

            Spacer(modifier = Modifier.height(28.dp))

            // Previous Reports header
            Text(
                text = "Previous Reports",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.SemiBold
            )
            Spacer(modifier = Modifier.height(12.dp))

            // Report tiles
            reports.forEach { report ->
                ReportTile(report = report, onClick = { /* TODO: navigation */ })
                Spacer(modifier = Modifier.height(12.dp))
            }

            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun ReportTile(
    report: DailyReport,
    onClick: () -> Unit
) {
    val date = LocalDate.parse(report.reportDate)
    val weatherIcon = weatherIcon(report.weather.condition)

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        )
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            // Header row
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = date.format(DateTimeFormatter.ofPattern("EEEE, MMM d")),
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.SemiBold
                )
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = weatherIcon,
                        contentDescription = report.weather.condition,
                        modifier = Modifier.size(18.dp),
                        tint = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = report.weather.condition,
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    if (report.isSubmitted) {
                        Spacer(modifier = Modifier.width(8.dp))
                        Icon(
                            imageVector = Icons.Default.Check,
                            contentDescription = "Submitted",
                            modifier = Modifier.size(18.dp),
                            tint = SubmittedGreen
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Metric chips grid (2 columns)
            FlowRow(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                maxItemsInEachRow = 2
            ) {
                MetricChip(
                    icon = Icons.Default.Info,
                    label = "Total Eff. Flow",
                    value = "%.2f MGD".format(report.flowTotals.effluentFlow),
                    modifier = Modifier.weight(1f)
                )
                MetricChip(
                    icon = Icons.Default.Star,
                    label = "Effluent pH",
                    value = "%.2f".format(report.effluentTank.ph),
                    modifier = Modifier.weight(1f)
                )
                if (report.aerationReadings.isNotEmpty()) {
                    MetricChip(
                        icon = Icons.Default.Warning,
                        label = "D.O. Level",
                        value = "%.2f mg/L".format(report.aerationReadings.first().dissolvedOxygen),
                        modifier = Modifier.weight(1f)
                    )
                }
                if (report.clarifierReadings.isNotEmpty()) {
                    MetricChip(
                        icon = Icons.Default.CheckCircle,
                        label = "Clarity Depth",
                        value = "%.1f in".format(report.clarifierReadings.first().clarityDepth),
                        modifier = Modifier.weight(1f)
                    )
                }
            }
        }
    }
}

@Composable
private fun MetricChip(
    icon: ImageVector,
    label: String,
    value: String,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .clip(RoundedCornerShape(10.dp))
            .background(MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f))
            .padding(horizontal = 10.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            modifier = Modifier.size(16.dp),
            tint = MaterialTheme.colorScheme.primary
        )
        Spacer(modifier = Modifier.width(6.dp))
        Column {
            Text(
                text = label,
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Text(
                text = value,
                style = MaterialTheme.typography.bodySmall,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun CardReviewScreen(
    report: DailyReport,
    onBack: () -> Unit
) {
    var acceptedCount by remember { mutableIntStateOf(0) }
    var rejectedCount by remember { mutableIntStateOf(0) }
    val cards = remember { report.toCardStack() }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Review Report") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                },
                actions = {
                    // Accept counter badge
                    Row(
                        modifier = Modifier
                            .clip(RoundedCornerShape(12.dp))
                            .background(SubmittedGreen.copy(alpha = 0.15f))
                            .padding(horizontal = 10.dp, vertical = 4.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Default.CheckCircle,
                            contentDescription = null,
                            modifier = Modifier.size(16.dp),
                            tint = SubmittedGreen
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = "$acceptedCount",
                            color = SubmittedGreen,
                            fontWeight = FontWeight.Bold,
                            fontSize = 14.sp
                        )
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    // Reject counter badge
                    Row(
                        modifier = Modifier
                            .clip(RoundedCornerShape(12.dp))
                            .background(Color(0xFFF44336).copy(alpha = 0.15f))
                            .padding(horizontal = 10.dp, vertical = 4.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Default.CheckCircle,
                            contentDescription = null,
                            modifier = Modifier.size(16.dp),
                            tint = Color(0xFFF44336)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = "$rejectedCount",
                            color = Color(0xFFF44336),
                            fontWeight = FontWeight.Bold,
                            fontSize = 14.sp
                        )
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surface
                )
            )
        },
        containerColor = MaterialTheme.colorScheme.background
    ) { padding ->
        Box(modifier = Modifier.padding(padding)) {
            CardStackView(
                cards = cards,
                onAction = { _, action ->
                    when (action) {
                        is CardAction.Accept -> acceptedCount++
                        is CardAction.Reject -> rejectedCount++
                    }
                }
            )
        }
    }
}

private fun weatherIcon(condition: String): ImageVector {
    val lower = condition.lowercase()
    return when {
        "sun" in lower || "clear" in lower -> Icons.Default.Star
        "cloud" in lower || "overcast" in lower -> Icons.Default.Info
        else -> Icons.Default.Info
    }
}
