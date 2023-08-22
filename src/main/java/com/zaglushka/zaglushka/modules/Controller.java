package com.zaglushka.zaglushka.modules;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.networknt.schema.JsonSchema;
import com.networknt.schema.JsonSchemaFactory;
import com.networknt.schema.SpecVersion;
import com.networknt.schema.ValidationMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.io.InputStream;
import java.util.Set;

@RestController
public class Controller {
    //GET
    @GetMapping("/users")
    public ResponseEntity<?> createPassport(Users users){
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    //POST
    @PostMapping("/users")
    public ResponseEntity<?> createPassportValidation(@RequestBody String requestStr) throws JsonProcessingException {
        InputStream schemaAsStream = Controller.class.getClassLoader().getResourceAsStream("UsersSchema.json");
        JsonSchema schema = JsonSchemaFactory.getInstance(SpecVersion.VersionFlag.V7).getSchema(schemaAsStream);

        ObjectMapper om = new ObjectMapper();
        om.setPropertyNamingStrategy(PropertyNamingStrategy.KEBAB_CASE);
        JsonNode jsonNode = om.readTree(requestStr);

        Set<ValidationMessage> errors = schema.validate(jsonNode);
        StringBuilder errorsCombined = new StringBuilder();
        for (ValidationMessage error : errors) {
            errorsCombined.append(error.toString()).append("\n");
        }

        if (errors.size() > 0)
            throw new RuntimeException("Error! " + errorsCombined);

        Users request = om.readValue(requestStr, Users.class);
        return new ResponseEntity<>(request, HttpStatus.OK);
    }
}
