package org.hibernate.examples.mapping.compositeId.idclass;

import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.examples.model.AbstractValueObject;
import org.hibernate.examples.utils.HashTool;
import org.hibernate.examples.utils.ToStringHelper;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;

/**
 * org.hibernate.examples.mapping.compositeId.idclass.IdClassCar
 *
 * @author 배성혁 sunghyouk.bae@gmail.com
 * @since 2013. 11. 29. 오후 4:35
 */
@Entity
@org.hibernate.annotations.Cache(region = "example", usage = CacheConcurrencyStrategy.READ_WRITE)
@IdClass(CarIdentifier.class)
@DynamicInsert
@DynamicUpdate
public class IdClassCar extends AbstractValueObject {

    @Id
    private String brand;

    @Id
    private int year;

    private String serialNo;

    @Override
    public int hashCode() {
        return HashTool.compute(brand, year);
    }

    @Override
    public ToStringHelper buildStringHelper() {
        return super.buildStringHelper()
                    .add("brand", brand)
                    .add("year", year)
                    .add("serialNo", serialNo);
    }

    private static final long serialVersionUID = -2144857053612105427L;

    public String getBrand() {
        return this.brand;
    }

    public int getYear() {
        return this.year;
    }

    public String getSerialNo() {
        return this.serialNo;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setSerialNo(String serialNo) {
        this.serialNo = serialNo;
    }
}
