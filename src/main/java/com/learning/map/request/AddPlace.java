package com.learning.map.request;

public class AddPlace {

    static String reqBody = """
                {
                  "location": {
                    "lat": -38.383494,
                    "lng": 33.427362
                  },
                  "accuracy": 50,
                  "name": "Frontline house",
                  "phone_number": "(+91) 983 893 3937",
                  "address": "29, side layout, cohen 09",
                  "types": [
                    "shoe park",
                    "shop"
                  ]
                }
                """;

    public static String getAddPlacePayload(){
        return reqBody;
    }
}
