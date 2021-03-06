package io.MUIC.BlockChain.ProjectBackEnd.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import java.util.Arrays;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Property {

    @Id
    private String id;

    private String name;

    private String addressNumber;

    private String district;

    private String province;

    private String country;

    private boolean activeStatus;

    private String buildingType;

    private String salePrice;

    private String rentPrice;

    private String sellPeriod;

    @Override
    public String toString() {
        return String.format(
                "Property[Name='%s', Address='%s']",
                name, Arrays.toString(new String[]{addressNumber, district, province, country}));
    }


}

