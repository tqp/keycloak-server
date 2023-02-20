package com.timsanalytics.controllers;

import com.timsanalytics.constants.Roles;
import com.timsanalytics.models.KeyValue;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.security.RolesAllowed;
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
@RequestMapping("/api/v1/diagnostics/")
@Tag(
        name = "Diagnostics",
        description = "Diagnostics"
)
public class DiagnosticsController {
    private final Logger logger = LoggerFactory.getLogger(getClass().getName());

    @ResponseBody
    @RequestMapping(
            value = "/health-check",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Operation(
            summary = "Get Health Check Response",
            description = "Get Health Check Response",
            tags = {"Diagnostics"}
    )
    public ResponseEntity<KeyValue<String, String>> getHealthCheck() {
        this.logger.trace("DiagnosticsController -> getHealthCheck");
        try {
            return ResponseEntity.ok().body(new KeyValue<>("health-check", "success"));
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @ResponseBody
    @RequestMapping(
            value = "/open",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Operation(
            summary = "Get Open Endpoint Response",
            description = "Get Open Endpoint Response",
            tags = {"Diagnostics"}
    )
    public ResponseEntity<KeyValue<String, String>> getOpenEndpointResponse() {
        try {
            return ResponseEntity.ok().body(new KeyValue<>("open-endpoint", "success"));
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @ResponseBody
    @RequestMapping(
            value = "/user",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Operation(
            summary = "Get Manager-Restricted Endpoint Response",
            description = "Get Manager-Restricted Endpoint Response",
            tags = {"Diagnostics"}
    )
    @RolesAllowed(Roles.USER)
    public ResponseEntity<KeyValue<String, String>> getUserEndpointResponse() {
        try {
            return ResponseEntity.ok().body(new KeyValue<>("user-endpoint", "success"));
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @ResponseBody
    @RequestMapping(
            value = "/manager",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Operation(
            summary = "Get Manager-Restricted Endpoint Response",
            description = "Get Manager-Restricted Endpoint Response",
            tags = {"Diagnostics"}
    )
    @RolesAllowed(Roles.MANAGER)
    public ResponseEntity<KeyValue<String, String>> getManagerEndpointResponse() {
        try {
            return ResponseEntity.ok().body(new KeyValue<>("manager-endpoint", "success"));
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @ResponseBody
    @RequestMapping(
            value = "/admin",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Operation(
            summary = "Get Admin-Restricted Endpoint Response",
            description = "Get Admin-Restricted Endpoint Response",
            tags = {"Diagnostics"}
    )
    @RolesAllowed(Roles.ADMIN)
    public ResponseEntity<KeyValue<String, String>> getAdminEndpointResponse() {
        try {
            return ResponseEntity.ok().body(new KeyValue<>("admin-endpoint", "success"));
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }
}
