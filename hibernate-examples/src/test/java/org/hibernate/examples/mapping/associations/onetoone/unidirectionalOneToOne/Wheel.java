package org.hibernate.examples.mapping.associations.onetoone.unidirectionalOneToOne;

import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.examples.model.AbstractHibernateEntity;
import org.hibernate.examples.utils.HashTool;
import org.hibernate.examples.utils.ToStringHelper;

import javax.persistence.*;

/**
 * org.hibernate.examples.mapping.associations.onetoone.unidirectionalOneToOne.Wheel
 *
 * @author 배성혁 sunghyouk.bae@gmail.com
 * @since 2013. 11. 29. 오후 3:36
 */
@Entity
@org.hibernate.annotations.Cache(region = "example", usage = CacheConcurrencyStrategy.READ_WRITE)
@DynamicInsert
@DynamicUpdate
public class Wheel extends AbstractHibernateEntity<Long> {

    @Id
    @Column(name = "vehicleId")
    private Long id;

    private String name;

    private double diameter;

    @MapsId
    @OneToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    // @PrimaryKeyJoinColumn
    @JoinColumn(name = "vehicleId")
    private Vehicle vehicle;

    @Override
    public int hashCode() {
        return HashTool.compute(name, diameter);
    }

    @Override
    public ToStringHelper buildStringHelper() {
        return super.buildStringHelper()
                    .add("name", name)
                    .add("diameter", diameter);
    }

    private static final long serialVersionUID = -6537387709923172615L;

    public Long getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public double getDiameter() {
        return this.diameter;
    }

    public Vehicle getVehicle() {
        return this.vehicle;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDiameter(double diameter) {
        this.diameter = diameter;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    protected void setId(Long id) {
        this.id = id;
    }
}
