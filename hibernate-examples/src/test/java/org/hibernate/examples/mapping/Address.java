package org.hibernate.examples.mapping;

import org.hibernate.examples.model.AbstractValueObject;
import org.hibernate.examples.utils.HashTool;
import org.hibernate.examples.utils.ToStringHelper;

import javax.persistence.Embeddable;

/**
 * org.hibernate.examples.mapping.Address
 *
 * @author 배성혁 sunghyouk.bae@gmail.com
 * @since 2013. 11. 28. 오전 9:42
 */
@Embeddable
public class Address extends AbstractValueObject {

    private String street;
    private String city;
    private String state;
    private String country;

    private String zipcode;

    @Override
    public int hashCode() {
        return HashTool.compute(zipcode, street, city, state, country);
    }

    @Override
    public ToStringHelper buildStringHelper() {
        return super.buildStringHelper()
                    .add("zipcode", zipcode)
                    .add("country", country)
                    .add("state", state)
                    .add("city", city)
                    .add("street", street);
    }


    private static final long serialVersionUID = -6441062979081642183L;

    public String getStreet() {
        return this.street;
    }

    public String getCity() {
        return this.city;
    }

    public String getState() {
        return this.state;
    }

    public String getCountry() {
        return this.country;
    }

    public String getZipcode() {
        return this.zipcode;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }
}
