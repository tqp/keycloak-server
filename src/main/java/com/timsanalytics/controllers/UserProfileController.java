package com.timsanalytics.controllers;

import com.timsanalytics.models.KeyValue;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/v1/profile/")
@Tag(
        name = "User Profile",
        description = "User Profile"
)
public class UserProfileController {
    private final Logger logger = LoggerFactory.getLogger(getClass().getName());

    @ResponseBody
    @RequestMapping(
            value = "",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Operation(
            summary = "Get Open Endpoint Response",
            description = "Get Open Endpoint Response",
            tags = {"User Profile"}
    )
    public ResponseEntity<KeyValue<String, String>> getOpenEndpointResponse() {
        this.logger.trace("UserProfileController -> getOpenEndpointResponse");
        try {
            return ResponseEntity.ok().body(new KeyValue<>("open-endpoint", "success"));
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }
}
