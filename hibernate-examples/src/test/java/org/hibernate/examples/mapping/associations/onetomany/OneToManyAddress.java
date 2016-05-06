package org.hibernate.examples.mapping.associations.onetomany;

import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.examples.model.AbstractHibernateEntity;
import org.hibernate.examples.utils.HashTool;
import org.hibernate.examples.utils.ToStringHelper;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * org.hibernate.examples.mapping.associations.onetomany.OneToManyAddress
 *
 * @author 배성혁 sunghyouk.bae@gmail.com
 * @since 2013. 11. 29. 오후 1:04
 */
@Entity
@org.hibernate.annotations.Cache(region = "example", usage = CacheConcurrencyStrategy.READ_WRITE)
public class OneToManyAddress extends AbstractHibernateEntity<Long> {

    @Id
    @GeneratedValue
    private Long id;

    private String city;

    @Override
    public int hashCode() {
        return HashTool.compute(city);
    }

    @Override
    public ToStringHelper buildStringHelper() {
        return super.buildStringHelper()
                    .add("city", city);
    }

    private static final long serialVersionUID = -8229206528601220447L;

    public Long getId() {
        return this.id;
    }

    public String getCity() {
        return this.city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    protected void setId(Long id) {
        this.id = id;
    }
}
