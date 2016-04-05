package org.hibernate.examples.mapping.embeddable;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.examples.model.AbstractValueObject;
import org.hibernate.examples.utils.HashTool;
import org.hibernate.examples.utils.ToStringHelper;

import javax.persistence.Embeddable;

/**
 * 주소를 나타내는 Value Object (Component) 입니다.
 *
 * @author 배성혁 sunghyouk.bae@gmail.com
 * @since 2013. 11. 30. 오후 12:33
 */
@Embeddable
@DynamicInsert
@DynamicUpdate
public class Address extends AbstractValueObject {

    private String street;

    private String zipcode;

    private String city;

    @Override
    public int hashCode() {
        return HashTool.compute(zipcode, street, city);
    }

    @Override
    public ToStringHelper buildStringHelper() {
        return super.buildStringHelper()
                    .add("zipcode", zipcode)
                    .add("street", street)
                    .add("city", city);
    }

    private static final long serialVersionUID = 526892402316852929L;

    public String getStreet() {
        return this.street;
    }

    public String getZipcode() {
        return this.zipcode;
    }

    public String getCity() {
        return this.city;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
