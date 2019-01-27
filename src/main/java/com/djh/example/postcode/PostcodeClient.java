package com.djh.example.postcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author David Hancock
 */
public class PostcodeClient {

    public List<Postcode> retrievePostcodesFor(String query) {

        // Lets just stub some for now, this likely could go off to some API...
        List<Postcode> postcodes = new ArrayList<>();
        postcodes.add(new Postcode("ST3 5HD", "Staffordshire", "United Kingdom", "1.1", "1.2"));
        postcodes.add(new Postcode("ST3 5HY", "Staffordshire", "United Kingdom", "1.1", "1.2"));
        return postcodes;
    }

}
