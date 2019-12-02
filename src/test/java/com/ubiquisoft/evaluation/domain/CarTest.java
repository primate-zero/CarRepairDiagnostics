package com.ubiquisoft.evaluation.domain;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

public class CarTest {
    private Car testee;
    private List<Part> parts = new ArrayList<>();

    @Before
    public void setUp() {
        testee = new Car();
        parts = new ArrayList<>();
        testee.setParts(parts);
    }
    @Test
    public void getMissingPartsMapReturnsFullMapForEmptyCar() {
        Map<PartType, Integer> missingParts = testee.getMissingPartsMap();
        assertTrue(missingParts.containsKey(PartType.TIRE));
        assertEquals(4, (int) missingParts.get(PartType.TIRE));
        assertTrue(missingParts.containsKey(PartType.ELECTRICAL));
        assertTrue(missingParts.containsKey(PartType.ENGINE));
        assertTrue(missingParts.containsKey(PartType.FUEL_FILTER));
        assertTrue(missingParts.containsKey(PartType.OIL_FILTER));
    }

    @Test
    public void getMissingPartsMapReturnsCorrectNumberOfTires() {
        addPart(PartType.TIRE);
        Map<PartType, Integer> missingParts = testee.getMissingPartsMap();
        assertTrue(missingParts.containsKey(PartType.TIRE));
        assertEquals(3, (int) missingParts.get(PartType.TIRE));
        addPart(PartType.TIRE);
        missingParts = testee.getMissingPartsMap();
        assertTrue(missingParts.containsKey(PartType.TIRE));
        assertEquals(2, (int) missingParts.get(PartType.TIRE));
        addPart(PartType.TIRE);
        missingParts = testee.getMissingPartsMap();
        assertTrue(missingParts.containsKey(PartType.TIRE));
        assertEquals(1, (int) missingParts.get(PartType.TIRE));
        addPart(PartType.TIRE);
        missingParts = testee.getMissingPartsMap();
        assertFalse(missingParts.containsKey(PartType.TIRE));
    }
    @Test
    public void getMissingPartsMapReturnsEmptyMapForCompleteCar() {
        addPart(PartType.TIRE);
        addPart(PartType.TIRE);
        addPart(PartType.TIRE);
        addPart(PartType.TIRE);
        addPart(PartType.ELECTRICAL);
        addPart(PartType.ENGINE);
        addPart(PartType.OIL_FILTER);
        addPart(PartType.FUEL_FILTER);
        Map<PartType, Integer> missingParts = testee.getMissingPartsMap();
        assertFalse(missingParts.containsKey(PartType.TIRE));
        assertFalse(missingParts.containsKey(PartType.ELECTRICAL));
        assertFalse(missingParts.containsKey(PartType.ENGINE));
        assertFalse(missingParts.containsKey(PartType.OIL_FILTER));
        assertFalse(missingParts.containsKey(PartType.FUEL_FILTER));
    }
    @Test
    public void getMissingPartsMapReturnsEmptyMapForOverstuffedCar() {
        addPart(PartType.TIRE);
        addPart(PartType.TIRE);
        addPart(PartType.TIRE);
        addPart(PartType.TIRE);
        addPart(PartType.TIRE);
        addPart(PartType.ELECTRICAL);
        addPart(PartType.ELECTRICAL);
        addPart(PartType.ENGINE);
        addPart(PartType.ENGINE);
        addPart(PartType.OIL_FILTER);
        addPart(PartType.OIL_FILTER);
        addPart(PartType.FUEL_FILTER);
        addPart(PartType.FUEL_FILTER);
        Map<PartType, Integer> missingParts = testee.getMissingPartsMap();
        assertFalse(missingParts.containsKey(PartType.TIRE));
        assertFalse(missingParts.containsKey(PartType.ELECTRICAL));
        assertFalse(missingParts.containsKey(PartType.ENGINE));
        assertFalse(missingParts.containsKey(PartType.OIL_FILTER));
        assertFalse(missingParts.containsKey(PartType.FUEL_FILTER));
    }

    private void addPart(PartType partToAdd) {
        Part newPart = new Part();
        newPart.setType(partToAdd);
        parts.add(newPart);
        testee.setParts(parts);
    }
}