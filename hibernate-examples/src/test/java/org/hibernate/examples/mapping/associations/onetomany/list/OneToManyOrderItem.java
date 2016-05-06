package org.hibernate.examples.mapping.associations.onetomany.list;

import org.hibernate.annotations.*;
import org.hibernate.examples.model.AbstractHibernateEntity;
import org.hibernate.examples.utils.HashTool;
import org.hibernate.examples.utils.ToStringHelper;

import javax.persistence.Entity;
import javax.persistence.*;

/**
 * org.hibernate.examples.mapping.associations.onetomany.list.OneToManyOrderItem
 *
 * @author 배성혁 sunghyouk.bae@gmail.com
 * @since 2013. 11. 29. 오후 1:18
 */
@Entity
@org.hibernate.annotations.Cache(region = "example", usage = CacheConcurrencyStrategy.READ_WRITE)
@DynamicInsert
@DynamicUpdate
public class OneToManyOrderItem extends AbstractHibernateEntity<Long> {


    @Id
    @GeneratedValue
    private Long id;

    private String name;

    @ManyToOne
    @LazyToOne(LazyToOneOption.NO_PROXY)
    @JoinColumn(name = "orderId")
    private OneToManyOrder order;

    @Override
    public int hashCode() {
        return HashTool.compute(name);
    }

    @Override
    public ToStringHelper buildStringHelper() {
        return super.buildStringHelper()
                    .add("name", name);
    }

    private static final long serialVersionUID = -4968710913293508239L;

    public Long getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public OneToManyOrder getOrder() {
        return this.order;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setOrder(OneToManyOrder order) {
        this.order = order;
    }

    protected void setId(Long id) {
        this.id = id;
    }
}
