package com.pph.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TopProductData {
    /**
     * 所有属性
     */
    public static final List<String> KEYS = Collections.unmodifiableList(Arrays.asList("model",
            "status",
            "manufacturer",
            "standardType",
            "function",
            "formFactor",
            "powerType",
            "plug",
            "inputVoltage",
            "acceptableInputVoltage",
            "frequency",
            "phase",
            "maximumInputCurrent",
            "maximumLineCurrent",
            "deratedInputCurrent",
            "kva",
            "outputVoltage",
            "maximumTotalCurrentDraw",
            "outletNumber",
            "c13",
            "c19",
            "universal",
            "gb10a",
            "gb16a",
            "gbSmallFive",
            "bsEn",
            "din",
            "nema515r",
            "nema520r",
            "nemaL620r",
            "nemaL530r",
            "nemaL630r",
            "outletDetails",
            "shellMaterial",
            "color",
            "inletlocation",
            "connectionSpecification",
            "junctionBox",
            "industrialConnector",
            "accessory",
            "cableType",
            "cableLength",
            "perphaseLight",
            "outletLight",
            "meterDisplay",
            "hotReplacement",
            "workTemperature",
            "workHumidity",
            "elevation",
            "storageTemperature",
            "storageHumidity",
            "storageElevation",
            "unitDimensions",
            "unitWeight",
            "pduMetering",
            "bank",
            "outletMetering",
            "outletSwitching",
            "interface485",
            "interface232",
            "usbAInterface",
            "usbBInterface",
            "sensorInterface",
            "wireCommunication",
            "breakerMonitoring",
            "rcm",
            "neutralConductorMonitoring",
            "networkBandwidth",
            "screenDisplay",
            "selflock",
            "spd",
            "fuse",
            "perphaseBreaker",
            "outletBreaker",
            "conformance",
            "rohs",
            "reach",
            "pep",
            "eoli",
            "ec200666",
            "proposition65Warning",
            "otherEnvironmentalProtection",
            "logo",
            "shippingDimensions",
            "shippingWeigh",
            "specialInstruction",
            "warranty",
            "marketClassification"));

    private Integer id;

    private String model;

    private String status;

    private String manufacturer;

    private String standardType;

    private String function;

    private String formFactor;

    private String powerType;

    private String plug;

    private String inputVoltage;

    private String acceptableInputVoltage;

    private String frequency;

    private String phase;

    private String maximumInputCurrent;

    private String maximumLineCurrent;

    private String deratedInputCurrent;

    private String kva;

    private String outputVoltage;

    private String maximumTotalCurrentDraw;

    private String outletNumber;

    private String c13;

    private String c19;

    private String universal;

    private String gb10a;

    private String gb16a;

    private String gbSmallFive;

    private String bsEn;

    private String din;

    private String nema515r;

    private String nema520r;

    private String nemaL620r;

    private String nemaL530r;

    private String nemaL630r;

    private String outletDetails;

    private String shellMaterial;

    private String color;

    private String inletlocation;

    private String connectionSpecification;

    private String junctionBox;

    private String industrialConnector;

    private String accessory;

    private String cableType;

    private String cableLength;

    private String perphaseLight;

    private String outletLight;

    private String meterDisplay;

    private String hotReplacement;

    private String workTemperature;

    private String workHumidity;

    private String elevation;

    private String storageTemperature;

    private String storageHumidity;

    private String storageElevation;

    private String unitDimensions;

    private String unitWeight;

    private String pduMetering;

    private String bank;

    private String outletMetering;

    private String outletSwitching;

    private String interface485;

    private String interface232;

    private String usbAInterface;

    private String usbBInterface;

    private String sensorInterface;

    private String wireCommunication;

    private String breakerMonitoring;

    private String rcm;

    private String neutralConductorMonitoring;

    private String networkBandwidth;

    private String screenDisplay;

    private String selflock;

    private String spd;

    private String fuse;

    private String perphaseBreaker;

    private String outletBreaker;

    private String conformance;

    private String rohs;

    private String reach;

    private String pep;

    private String eoli;

    private String ec200666;

    private String proposition65Warning;

    private String otherEnvironmentalProtection;

    private String logo;

    private String shippingDimensions;

    private String shippingWeigh;

    private String specialInstruction;

    private String warranty;

    private String marketClassification;

    private Date gmtCreate;

    private Date gmtModify;
}