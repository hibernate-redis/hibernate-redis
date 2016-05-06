package org.hibernate.examples.mapping.associations.onetoone.unidirectionalOneToOne;

import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.examples.model.AbstractHibernateEntity;
import org.hibernate.examples.utils.HashTool;
import org.hibernate.examples.utils.ToStringHelper;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * org.hibernate.examples.mapping.associations.onetoone.unidirectionalOneToOne.Vehicle
 *
 * @author 배성혁 sunghyouk.bae@gmail.com
 * @since 2013. 11. 29. 오후 3:36
 */
@Entity
@org.hibernate.annotations.Cache(region = "example", usage = CacheConcurrencyStrategy.READ_WRITE)
@DynamicInsert
@DynamicUpdate
public class Vehicle extends AbstractHibernateEntity<Long> {

    @Id
    @GeneratedValue
    @Column(name = "vehicleId")
    private Long id;

    private String brand;

    @Override
    public int hashCode() {
        return HashTool.compute(brand);
    }

    @Override
    public ToStringHelper buildStringHelper() {
        return super.buildStringHelper()
                    .add("brand", brand);
    }

    private static final long serialVersionUID = -5118283969168355563L;

    public Long getId() {
        return this.id;
    }

    public String getBrand() {
        return this.brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    protected void setId(Long id) {
        this.id = id;
    }
}
