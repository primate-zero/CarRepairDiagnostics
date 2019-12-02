package com.ubiquisoft.evaluation.domain;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Car {

	private String year;
	private String make;
	private String model;

	private List<Part> parts;

	public Map<PartType, Integer> getMissingPartsMap() {
		/*
		 * Return map of the part types missing.
		 *
		 * Each car requires one of each of the following types:
		 *      ENGINE, ELECTRICAL, FUEL_FILTER, OIL_FILTER
		 * and four of the type: TIRE
		 *
		 * Example: a car only missing three of the four tires should return a map like this:
		 *
		 *      {
		 *          "TIRE": 3
		 *      }
		 */
		Map<PartType, Integer> missingParts = populateMapOfAllParts();
		for (Part part : parts) {
			PartType partType = part.getType();
		    if (missingParts.containsKey(partType) && partType.equals(PartType.TIRE)) {
		    	if (missingParts.get(partType) == 1) {
		    		missingParts.remove(partType);
				} else {
		    		missingParts.put(partType, missingParts.get(partType) - 1);
				}
			} else {
				missingParts.remove(partType);
			}
		}
		return missingParts;
	}
	private Map<PartType, Integer> populateMapOfAllParts() {
		Map<PartType, Integer> partTypeMap = new HashMap<>();
		for (PartType type : PartType.values()) {
			if (type.equals(PartType.TIRE)) {
				partTypeMap.put(type, 4);
			} else {
				partTypeMap.put(type, 1);
			}
		}
		return partTypeMap;
	}

	@Override
	public String toString() {
		return "Car{" +
				       "year='" + year + '\'' +
				       ", make='" + make + '\'' +
				       ", model='" + model + '\'' +
				       ", parts=" + parts +
				       '}';
	}

	/* --------------------------------------------------------------------------------------------------------------- */
	/*  Getters and Setters *///region
	/* --------------------------------------------------------------------------------------------------------------- */

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getMake() {
		return make;
	}

	public void setMake(String make) {
		this.make = make;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public List<Part> getParts() {
		return parts;
	}

	public void setParts(List<Part> parts) {
		this.parts = parts;
	}

	/* --------------------------------------------------------------------------------------------------------------- */
	/*  Getters and Setters End *///endregion
	/* --------------------------------------------------------------------------------------------------------------- */

}
