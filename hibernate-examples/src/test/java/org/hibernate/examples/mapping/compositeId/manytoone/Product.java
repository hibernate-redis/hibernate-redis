package org.hibernate.examples.mapping.compositeId.manytoone;

import org.hibernate.annotations.*;
import org.hibernate.examples.model.AbstractHibernateEntity;
import org.hibernate.examples.utils.HashTool;
import org.hibernate.examples.utils.ToStringHelper;

import javax.persistence.CascadeType;
import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

/**
 * org.hibernate.examples.mapping.compositeId.manytoone.Product
 *
 * @author 배성혁 sunghyouk.bae@gmail.com
 * @since 2013. 11. 29. 오후 5:07
 */
@Entity
@Table(name = "CompositeId_Product")
@org.hibernate.annotations.Cache(region = "example", usage = CacheConcurrencyStrategy.READ_WRITE)
@DynamicInsert
@DynamicUpdate
public class Product extends AbstractHibernateEntity<Long> {

    @Id
    @GeneratedValue
    @Column(name = "productId")
    private Long id;

    @Column(name = "productName")
    private String name;

    @OneToMany(mappedBy = "id.product", cascade = { CascadeType.ALL }, orphanRemoval = true)
    @LazyCollection(value = LazyCollectionOption.EXTRA)
    private Set<OrderDetail> orderDetails = new HashSet<OrderDetail>();

    @Override
    public int hashCode() {
        return HashTool.compute(name);
    }

    @Override
    public ToStringHelper buildStringHelper() {
        return super.buildStringHelper()
                    .add("name", name);
    }

    private static final long serialVersionUID = -4725360631652953447L;

    public Long getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public Set<OrderDetail> getOrderDetails() {
        return this.orderDetails;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setOrderDetails(Set<OrderDetail> orderDetails) {
        this.orderDetails = orderDetails;
    }

    protected void setId(Long id) {
        this.id = id;
    }
}
