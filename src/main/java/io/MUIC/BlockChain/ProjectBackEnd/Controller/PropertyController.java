package io.MUIC.BlockChain.ProjectBackEnd.Controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.MUIC.BlockChain.ProjectBackEnd.Entity.Property;
import io.MUIC.BlockChain.ProjectBackEnd.Service.PropertyService;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api")
public class PropertyController {
    @Autowired
    private PropertyService propertyService;

    @GetMapping("/property/{propertyId}")
    public ResponseEntity<Property> getPropertyById(@PathVariable("propertyId") String id) {
        try {
            Property property = propertyService.findPropertyById(id);
            if (property == null) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(property, HttpStatus.OK);
        } catch (NullPointerException e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/properties")
    public ResponseEntity<List<Property>> getAllProperties() {
        try {
            List<Property> properties = this.propertyService.findAllProperties();

            if (properties.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(properties, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/properties/by/{searchBy}/{searchValue}")
    public ResponseEntity<List<Property>> getPropertiesBySearchValue(
        @PathVariable("searchBy") String searchBy,
        @PathVariable("searchValue") String searchValue
    ) {
        try {
            List<Property> properties = new ArrayList<>();
            System.out.println(searchValue);
            
            switch (searchBy) {
                case "Name":
                    properties = this.propertyService.findPropertiesByName(searchValue);
                    break;
                case "District":
                    properties = this.propertyService.findPropertiesByDistrict(searchValue);
                    break;
                case "Province":
                    properties = this.propertyService.findPropertiesByProvince(searchValue);
                    break;
                case "Country":
                    properties = this.propertyService.findPropertiesByCountry(searchValue);
                    break;
                case "Building Type":
                    properties = this.propertyService.findPropertiesByBuildingType(searchValue);
                    break;
                default:
                    break;
            }

            if (properties.isEmpty() || properties == null) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(properties, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
