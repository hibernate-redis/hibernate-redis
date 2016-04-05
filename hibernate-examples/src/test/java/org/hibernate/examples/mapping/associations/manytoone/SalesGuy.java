package org.hibernate.examples.mapping.associations.manytoone;

import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.examples.model.AbstractHibernateEntity;
import org.hibernate.examples.utils.HashTool;
import org.hibernate.examples.utils.ToStringHelper;

import javax.persistence.*;

/**
 * org.hibernate.examples.mapping.associations.manytoone.SalesGuy
 *
 * @author 배성혁 sunghyouk.bae@gmail.com
 * @since 2013. 11. 29. 오전 10:32
 */
@Entity
@org.hibernate.annotations.Cache(region = "example", usage = CacheConcurrencyStrategy.READ_WRITE)
public class SalesGuy extends AbstractHibernateEntity<Long> {

    protected SalesGuy() {}

    public SalesGuy(String name) {
        this.name = name;
    }

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    private SalesForce salesForce;

    @Override
    public int hashCode() {
        return HashTool.compute(name);
    }

    @Override
    public ToStringHelper buildStringHelper() {
        return super.buildStringHelper()
                    .add("name", name);
    }

    private static final long serialVersionUID = -1171720938973299196L;

    public Long getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public SalesForce getSalesForce() {
        return this.salesForce;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSalesForce(SalesForce salesForce) {
        this.salesForce = salesForce;
    }

    protected void setId(Long id) {
        this.id = id;
    }
}
