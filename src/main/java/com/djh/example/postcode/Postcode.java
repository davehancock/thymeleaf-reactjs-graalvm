package com.djh.example.postcode;

import lombok.Value;

/**
 * @author David Hancock
 */
@Value
public class Postcode {

    private final String postcode;
    private final String country;
    private final String region;
    private final String longitude;
    private final String latitude;
}
