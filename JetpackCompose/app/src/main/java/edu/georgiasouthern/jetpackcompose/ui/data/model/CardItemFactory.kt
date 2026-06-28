package edu.georgiasouthern.jetpackcompose.ui.data.model

fun FlowTotals.toCardItem(): CardItem = CardItem(
    title = "Flow Totals",
    fields = listOf(
        CardField(
            icon = "water_drop",
            label = "Effluent Flow",
            value = "%.2f MGD".format(effluentFlow),
            validationState = if (effluentFlow in 0.0..10.0) CardValidationState.VALID else CardValidationState.WARNING
        ),
        CardField(
            icon = "delete",
            label = "Waste Flow",
            value = "%.2f MGD".format(wasteFlow),
            validationState = if (wasteFlow in 0.0..5.0) CardValidationState.VALID else CardValidationState.WARNING
        ),
        CardField(
            icon = "replay",
            label = "Return Flow",
            value = "%.2f MGD".format(returnFlow),
            validationState = if (returnFlow in 0.0..5.0) CardValidationState.VALID else CardValidationState.WARNING
        ),
        CardField(
            icon = "calculate",
            label = "Computed Flow",
            value = "%.2f MGD".format(computedFlow),
            validationState = if (computedFlow in 0.0..10.0) CardValidationState.VALID else CardValidationState.WARNING
        )
    )
)

fun InfluentTankReading.toCardItem(): CardItem = CardItem(
    title = "Influent Tank",
    fields = listOf(
        CardField(
            icon = "water_drop",
            label = "Dissolved Oxygen",
            value = "%.2f mg/L".format(dissolvedOxygen),
            validationState = if (dissolvedOxygen in 0.5..6.0) CardValidationState.VALID else CardValidationState.WARNING
        ),
        CardField(
            icon = "thermostat",
            label = "Temperature",
            value = "%.1f °C".format(temperature),
            validationState = if (temperature in 10.0..30.0) CardValidationState.VALID else CardValidationState.WARNING
        ),
        CardField(
            icon = "bolt",
            label = "ORP",
            value = "%.0f mV".format(orp),
            validationState = if (orp in -500.0..500.0) CardValidationState.VALID else CardValidationState.WARNING
        ),
        CardField(
            icon = "science",
            label = "pH",
            value = "%.2f".format(ph),
            validationState = if (ph in 6.5..8.5) CardValidationState.VALID else CardValidationState.WARNING
        )
    )
)

fun EffluentTankReading.toCardItem(): CardItem = CardItem(
    title = "Effluent Tank",
    fields = listOf(
        CardField(
            icon = "water_drop",
            label = "Dissolved Oxygen",
            value = "%.2f mg/L".format(dissolvedOxygen),
            validationState = if (dissolvedOxygen in 2.0..8.0) CardValidationState.VALID else CardValidationState.WARNING
        ),
        CardField(
            icon = "thermostat",
            label = "Temperature",
            value = "%.1f °C".format(temperature),
            validationState = if (temperature in 10.0..30.0) CardValidationState.VALID else CardValidationState.WARNING
        ),
        CardField(
            icon = "bolt",
            label = "ORP",
            value = "%.0f mV".format(orp),
            validationState = if (orp in -500.0..500.0) CardValidationState.VALID else CardValidationState.WARNING
        ),
        CardField(
            icon = "science",
            label = "pH",
            value = "%.2f".format(ph),
            validationState = if (ph in 6.5..8.5) CardValidationState.VALID else CardValidationState.WARNING
        )
    )
)

fun AerationStationReading.toCardItem(basinNumber: Int): CardItem = CardItem(
    title = "Aeration Basin $basinNumber",
    fields = listOf(
        CardField(
            icon = "water_drop",
            label = "Dissolved Oxygen",
            value = "%.2f mg/L".format(dissolvedOxygen),
            validationState = if (dissolvedOxygen in 1.0..4.0) CardValidationState.VALID else CardValidationState.WARNING
        ),
        CardField(
            icon = "thermostat",
            label = "Temperature",
            value = "%.1f °C".format(temperature),
            validationState = if (temperature in 10.0..30.0) CardValidationState.VALID else CardValidationState.WARNING
        ),
        CardField(
            icon = "bolt",
            label = "ORP",
            value = "%.0f mV".format(orp),
            validationState = if (orp in -300.0..300.0) CardValidationState.VALID else CardValidationState.WARNING
        ),
        CardField(
            icon = "science",
            label = "pH",
            value = "%.2f".format(ph),
            validationState = if (ph in 6.5..8.5) CardValidationState.VALID else CardValidationState.WARNING
        )
    )
)

fun ClarifierReading.toCardItem(tankNumber: Int): CardItem = CardItem(
    title = "Clarifier $tankNumber",
    fields = listOf(
        CardField(
            icon = "speed",
            label = "RSF",
            value = "%.2f GPD/ft²".format(rsf),
            validationState = if (rsf in 200.0..1200.0) CardValidationState.VALID else CardValidationState.WARNING
        ),
        CardField(
            icon = "visibility",
            label = "Surface Appearance",
            value = surfaceAppearance,
            validationState = CardValidationState.UNCHECKED
        ),
        CardField(
            icon = "straighten",
            label = "Blanket Level",
            value = "%.1f ft".format(blt),
            validationState = if (blt in 0.0..5.0) CardValidationState.VALID else CardValidationState.WARNING
        ),
        CardField(
            icon = "water",
            label = "Clarity Depth",
            value = "%.1f in".format(clarityDepth),
            validationState = if (clarityDepth >= 12.0) CardValidationState.VALID else CardValidationState.WARNING
        ),
        CardField(
            icon = "opacity",
            label = "CSC",
            value = "%.0f mL/L".format(csc),
            validationState = if (csc in 100.0..800.0) CardValidationState.VALID else CardValidationState.WARNING
        ),
        CardField(
            icon = "bolt",
            label = "ORP",
            value = "%.0f mV".format(orp),
            validationState = if (orp in -300.0..300.0) CardValidationState.VALID else CardValidationState.WARNING
        ),
        CardField(
            icon = "replay",
            label = "RAS",
            value = "%.2f MGD".format(ras),
            validationState = if (ras in 0.0..5.0) CardValidationState.VALID else CardValidationState.WARNING
        )
    )
)

fun DewateringReading.toCardItem(): CardItem = CardItem(
    title = "Dewatering",
    fields = listOf(
        CardField(
            icon = "compress",
            label = "Pressure",
            value = "%.1f PSI".format(pressure),
            validationState = if (pressure in 0.0..100.0) CardValidationState.VALID else CardValidationState.WARNING
        ),
        CardField(
            icon = "pie_chart",
            label = "Cake %",
            value = "%.1f%%".format(cakePercent),
            validationState = if (cakePercent in 15.0..30.0) CardValidationState.VALID else CardValidationState.WARNING
        ),
        CardField(
            icon = "pie_chart",
            label = "Raw %",
            value = "%.1f%%".format(rawPercent),
            validationState = if (rawPercent in 1.0..6.0) CardValidationState.VALID else CardValidationState.WARNING
        ),
        CardField(
            icon = "science",
            label = "Polymer",
            value = "%.1f lbs/DT".format(polymer),
            validationState = if (polymer in 5.0..30.0) CardValidationState.VALID else CardValidationState.WARNING
        ),
        CardField(
            icon = "science",
            label = "Raw pH",
            value = "%.2f".format(rawPh),
            validationState = if (rawPh in 6.0..8.0) CardValidationState.VALID else CardValidationState.WARNING
        )
    )
)

fun ChemicalTankLevels.toCardItem(): CardItem = CardItem(
    title = "Chemical Tanks",
    fields = listOf(
        CardField(
            icon = "propane_tank",
            label = "Phos Sulphate",
            value = "%.0f gal".format(phosSulphate),
            validationState = if (phosSulphate > 100.0) CardValidationState.VALID else CardValidationState.WARNING
        ),
        CardField(
            icon = "propane_tank",
            label = "PAC Split",
            value = "%.0f gal".format(pacSplit),
            validationState = if (pacSplit > 100.0) CardValidationState.VALID else CardValidationState.WARNING
        ),
        CardField(
            icon = "propane_tank",
            label = "PAC",
            value = "%.0f gal".format(pac),
            validationState = if (pac > 100.0) CardValidationState.VALID else CardValidationState.WARNING
        )
    )
)

fun UVSystemReading.toCardItem(bankNumber: Int): CardItem = CardItem(
    title = "UV Bank $bankNumber",
    fields = listOf(
        CardField(
            icon = "light_mode",
            label = "Dose",
            value = "%.1f mJ/m²".format(mjPerMeterSq),
            validationState = if (mjPerMeterSq >= 30.0) CardValidationState.VALID else CardValidationState.WARNING
        ),
        CardField(
            icon = "power",
            label = "Power",
            value = "%.0f%%".format(powerPercent),
            validationState = if (powerPercent in 50.0..100.0) CardValidationState.VALID else CardValidationState.WARNING
        ),
        CardField(
            icon = "lightbulb",
            label = "Lamps",
            value = "%.0f".format(lamps),
            validationState = if (lamps > 0.0) CardValidationState.VALID else CardValidationState.INVALID
        ),
        CardField(
            icon = "schedule",
            label = "Lamp Hours",
            value = "%.0f hrs".format(lampHours),
            validationState = if (lampHours < 9000.0) CardValidationState.VALID else CardValidationState.WARNING
        )
    )
)

fun BlowerReading.toCardItem(blowerNumber: Int): CardItem = CardItem(
    title = "Blower $blowerNumber",
    fields = listOf(
        CardField(
            icon = "air",
            label = "Frequency",
            value = "%.1f Hz".format(hz),
            validationState = if (hz in 30.0..60.0) CardValidationState.VALID else CardValidationState.WARNING
        )
    )
)

fun DailyReport.toCardStack(): List<CardItem> = buildList {
    add(flowTotals.toCardItem())
    add(influentTank.toCardItem())
    add(effluentTank.toCardItem())
    aerationReadings.forEachIndexed { index, reading ->
        add(reading.toCardItem(basinNumber = index + 1))
    }
    clarifierReadings.forEachIndexed { index, reading ->
        add(reading.toCardItem(tankNumber = index + 1))
    }
    add(dewatering.toCardItem())
    chemicalTanks.forEach { tank ->
        add(tank.toCardItem())
    }
    add(uvSystem.toCardItem(bankNumber = 1))
    blowers.forEachIndexed { index, blower ->
        add(blower.toCardItem(blowerNumber = index + 1))
    }
}
