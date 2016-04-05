package org.hibernate.examples.mapping.compositeId.manytoone;

import org.hibernate.annotations.*;
import org.hibernate.examples.model.AbstractHibernateEntity;
import org.hibernate.examples.utils.HashTool;
import org.hibernate.examples.utils.ToStringHelper;

import javax.persistence.CascadeType;
import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * org.hibernate.examples.mapping.compositeId.manytoone.Order
 *
 * @author 배성혁 sunghyouk.bae@gmail.com
 * @since 2013. 11. 29. 오후 5:07
 */
@Entity
@Table(name = "CompositeId_Order")
@org.hibernate.annotations.Cache(region = "example", usage = CacheConcurrencyStrategy.READ_WRITE)
@DynamicInsert
@DynamicUpdate
public class Order extends AbstractHibernateEntity<Long> {

    @Id
    @GeneratedValue
    @Column(name = "orderId")
    private Long id;

    private String number;

    @Temporal(TemporalType.DATE)
    private Date orderDate;

    @OneToMany(mappedBy = "id.order", cascade = { CascadeType.ALL }, orphanRemoval = true)
    @LazyCollection(LazyCollectionOption.EXTRA)
    private List<OrderDetail> orderDetails = new ArrayList<OrderDetail>();

    @Override
    public int hashCode() {
        return HashTool.compute(number);
    }

    @Override
    public ToStringHelper buildStringHelper() {
        return super.buildStringHelper()
                    .add("number", number)
                    .add("orderDate", orderDate);
    }

    private static final long serialVersionUID = -478214079111379653L;

    public Long getId() {
        return this.id;
    }

    public String getNumber() {
        return this.number;
    }

    public Date getOrderDate() {
        return this.orderDate;
    }

    public List<OrderDetail> getOrderDetails() {
        return this.orderDetails;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public void setOrderDetails(List<OrderDetail> orderDetails) {
        this.orderDetails = orderDetails;
    }

    protected void setId(Long id) {
        this.id = id;
    }
}
