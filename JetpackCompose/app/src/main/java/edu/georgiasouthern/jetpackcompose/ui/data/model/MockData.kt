package edu.georgiasouthern.jetpackcompose.ui.data.model

object MockDataFactory {

    val sampleReport: DailyReport = DailyReport(
        reportId = 1001,
        reportDate = "2026-03-25",
        created = "2026-03-25T06:00:00Z",
        modified = "2026-03-25T14:30:00Z",
        isSubmitted = false,
        weather = Weather(
            condition = "Partly Cloudy",
            high = 78.0,
            low = 62.0
        ),
        flowTotals = FlowTotals(
            effluentFlow = 3.45,
            wasteFlow = 0.82,
            returnFlow = 1.20,
            computedFlow = 4.27
        ),
        influentTank = InfluentTankReading(
            dissolvedOxygen = 1.85,
            temperature = 21.3,
            orp = -145.0,
            ph = 7.12
        ),
        effluentTank = EffluentTankReading(
            dissolvedOxygen = 5.40,
            temperature = 20.8,
            orp = 210.0,
            ph = 7.05
        ),
        aerationReadings = listOf(
            AerationStationReading(
                dissolvedOxygen = 2.10,
                temperature = 21.0,
                orp = 85.0,
                ph = 7.08
            ),
            AerationStationReading(
                dissolvedOxygen = 2.35,
                temperature = 21.1,
                orp = 92.0,
                ph = 7.10
            ),
            AerationStationReading(
                dissolvedOxygen = 1.95,
                temperature = 20.9,
                orp = 78.0,
                ph = 7.06
            )
        ),
        clarifierReadings = listOf(
            ClarifierReading(
                rsf = 650.0,
                surfaceAppearance = "Clear, no floating solids",
                blt = 1.8,
                clarityDepth = 18.0,
                csc = 350.0,
                orp = 180.0,
                ras = 1.10
            ),
            ClarifierReading(
                rsf = 680.0,
                surfaceAppearance = "Slight surface sheen",
                blt = 2.1,
                clarityDepth = 16.5,
                csc = 375.0,
                orp = 175.0,
                ras = 1.15
            )
        ),
        dewatering = DewateringReading(
            pressure = 42.5,
            cakePercent = 22.0,
            rawPercent = 3.8,
            polymer = 18.5,
            rawPh = 6.85
        ),
        chemicalTanks = listOf(
            ChemicalTankLevels(
                phosSulphate = 850.0,
                pacSplit = 620.0,
                pac = 1100.0
            )
        ),
        uvSystem = UVSystemReading(
            mjPerMeterSq = 45.2,
            powerPercent = 85.0,
            lamps = 48.0,
            lampHours = 4520.0
        ),
        blowers = listOf(
            BlowerReading(hz = 45.0),
            BlowerReading(hz = 42.5),
            BlowerReading(hz = 47.0)
        ),
        user = OperatorProfile(
            firstName = "Jane",
            lastName = "Mitchell",
            phone = "555-0142"
        )
    )
}
