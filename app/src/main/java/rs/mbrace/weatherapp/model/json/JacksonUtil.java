package rs.mbrace.weatherapp.model.json;


import com.fasterxml.jackson.databind.ObjectMapper;

public class JacksonUtil {

    private static final ObjectMapper mapper = new ObjectMapper();

    public static ObjectMapper getObjectMapper(){
        return mapper;
    }
}
