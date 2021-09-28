package it.euris.academy.centroSportivo.data.dto;

import it.euris.academy.centroSportivo.data.archetype.Dto;
import it.euris.academy.centroSportivo.data.model.Address;
import it.euris.academy.centroSportivo.utils.UT;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class AddressDto implements Dto {

    private String id;
    private String address;
    private String postalCode;
    private String city;
    private String province;
    private String nation;
    private Boolean deleted;

    @Override
    public Address toModel() {
        return Address.builder()
                .id(UT.toLong(id))
                .address(address)
                .postalCode(UT.toInt(postalCode))
                .city(city)
                .province(province)
                .nation(nation)
                .deleted(deleted)
                .build();
    }
}
