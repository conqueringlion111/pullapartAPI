package com.pullapart.api.payload;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SearchPayload {

    public static String searchPayload(List<Integer> locations, int makeID, List<Integer> models, List<Integer> years) throws JsonProcessingException {
        Map<String, Object> payload = new HashMap<>();
        payload.put("Locations", locations);
        payload.put("MakeID", makeID);
        payload.put("Models", models);
        payload.put("Years", years);

        ObjectMapper mapper = new ObjectMapper();
        ObjectWriter writer = mapper.writerWithDefaultPrettyPrinter();
        return writer.writeValueAsString(payload);
    }
}
