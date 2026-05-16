package com.learning.map.request;

public class UpdatePlace {

    static String requestBody = """
            {
                "place_id": "%s",
                "address": "70 winter walk, USA",
                "key": "qaclick123"
            }
            """;

    public static String getUpdatePlaceRequestPayload(String placeId){
        return requestBody.formatted(placeId);
    }
}
