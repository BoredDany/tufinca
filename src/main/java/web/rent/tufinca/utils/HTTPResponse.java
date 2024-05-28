package web.rent.tufinca.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import web.rent.tufinca.dtos.UserDTO;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class HTTPResponse {

    private String error;
    private HttpStatus status;
    private Map<String, Object> data;

    public static ResponseEntity<HTTPResponse> build(String error, String message, Object data, HttpStatus status) {
        HTTPResponse response = new HTTPResponse();
        Map<String, Object> setData = new HashMap<>();
        setData.put("message", message);
        setData.put("results", data);
        response.setData(setData);
        response.setError(error);
        response.setStatus(status);

        return ResponseEntity.status(response.getStatus()).body(response);
    }

    public static Long getUserIDFromAuth(Authentication auth) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(auth.getPrincipal().toString(), UserDTO.class).getIdUser();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return -1L;
        }
    }
}
