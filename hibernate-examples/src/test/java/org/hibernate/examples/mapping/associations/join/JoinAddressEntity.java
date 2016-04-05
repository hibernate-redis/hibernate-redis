package org.hibernate.examples.mapping.associations.join;

import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.examples.model.AbstractHibernateEntity;
import org.hibernate.examples.utils.HashTool;
import org.hibernate.examples.utils.ToStringHelper;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * org.hibernate.examples.mapping.associations.join.JoinAddressEntity
 *
 * @author 배성혁 sunghyouk.bae@gmail.com
 * @since 2013. 11. 28. 오후 11:13
 */
@Entity
@org.hibernate.annotations.Cache(region = "example", usage = CacheConcurrencyStrategy.READ_WRITE)
@DynamicInsert
@DynamicUpdate
public class JoinAddressEntity extends AbstractHibernateEntity<Long> {

    @Id
    @GeneratedValue
    private Long id;

    private String street;
    private String city;
    private String zipcode;

    @Override
    public int hashCode() {
        return HashTool.compute(street, city, zipcode);
    }

    @Override
    public ToStringHelper buildStringHelper() {
        return super.buildStringHelper()
                .add("street", street)
                .add("city", city)
                .add("zipcode", zipcode);
    }

    private static final long serialVersionUID = 6610359502465293056L;

    public Long getId() {
        return this.id;
    }

    public String getStreet() {
        return this.street;
    }

    public String getCity() {
        return this.city;
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

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    protected void setId(Long id) {
        this.id = id;
    }
}
