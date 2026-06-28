package edu.georgiasouthern.jetpackcompose.ui.data.model

data class Weather(
    val condition: String,
    val high: Double,
    val low: Double
)

data class FlowTotals(
    val effluentFlow: Double,
    val wasteFlow: Double,
    val returnFlow: Double,
    val computedFlow: Double
)

data class InfluentTankReading(
    val dissolvedOxygen: Double,
    val temperature: Double,
    val orp: Double,
    val ph: Double
)

data class EffluentTankReading(
    val dissolvedOxygen: Double,
    val temperature: Double,
    val orp: Double,
    val ph: Double
)

data class AerationStationReading(
    val dissolvedOxygen: Double,
    val temperature: Double,
    val orp: Double,
    val ph: Double
)

data class ClarifierReading(
    val rsf: Double,
    val surfaceAppearance: String,
    val blt: Double,
    val clarityDepth: Double,
    val csc: Double,
    val orp: Double,
    val ras: Double
)

data class DewateringReading(
    val pressure: Double,
    val cakePercent: Double,
    val rawPercent: Double,
    val polymer: Double,
    val rawPh: Double
)

data class ChemicalTankLevels(
    val phosSulphate: Double,
    val pacSplit: Double,
    val pac: Double
)

data class UVSystemReading(
    val mjPerMeterSq: Double,
    val powerPercent: Double,
    val lamps: Double,
    val lampHours: Double
)

data class BlowerReading(
    val hz: Double
)

data class OperatorProfile(
    val firstName: String,
    val lastName: String,
    val phone: String
)

data class DailyReport(
    val reportId: Int,
    val reportDate: String,
    val created: String,
    val modified: String,
    val isSubmitted: Boolean,
    val weather: Weather,
    val flowTotals: FlowTotals,
    val influentTank: InfluentTankReading,
    val effluentTank: EffluentTankReading,
    val aerationReadings: List<AerationStationReading>,
    val clarifierReadings: List<ClarifierReading>,
    val dewatering: DewateringReading,
    val chemicalTanks: List<ChemicalTankLevels>,
    val uvSystem: UVSystemReading,
    val blowers: List<BlowerReading>,
    val user: OperatorProfile? = null
)
