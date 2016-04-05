package org.hibernate.examples.mapping.compositeId.manytoone;

import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.examples.model.AbstractHibernateEntity;
import org.hibernate.examples.utils.HashTool;
import org.hibernate.examples.utils.ToStringHelper;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;

/**
 * org.hibernate.examples.mapping.compositeId.manytoone.OrderDetail
 *
 * @author 배성혁 sunghyouk.bae@gmail.com
 * @since 2013. 11. 29. 오후 5:07
 */
@Entity
@Table(name = "CompositeId_OrderDetail")
@org.hibernate.annotations.Cache(region = "example", usage = CacheConcurrencyStrategy.READ_WRITE)
@DynamicInsert
@DynamicUpdate
public class OrderDetail extends AbstractHibernateEntity<OrderDetailIdentifier> {

    protected OrderDetail() {}

    public OrderDetail(Order order, Product product) {
        this.id = new OrderDetailIdentifier(order, product);
    }

    public OrderDetail(OrderDetailIdentifier id) {
        this.id = id;
    }

    @EmbeddedId
    private OrderDetailIdentifier id;

    private BigDecimal unitPrice;
    private Integer quantity;
    private Float discount;


    @Override
    public int hashCode() {
        return HashTool.compute(unitPrice, quantity, discount);
    }

    @Override
    public ToStringHelper buildStringHelper() {
        return super.buildStringHelper()
                    .add("unitPrice", unitPrice)
                    .add("quantity", quantity)
                    .add("discount", discount);
    }

    private static final long serialVersionUID = 6958616166017033341L;

    public OrderDetailIdentifier getId() {
        return this.id;
    }

    public BigDecimal getUnitPrice() {
        return this.unitPrice;
    }

    public Integer getQuantity() {
        return this.quantity;
    }

    public Float getDiscount() {
        return this.discount;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public void setDiscount(Float discount) {
        this.discount = discount;
    }

    protected void setId(OrderDetailIdentifier id) {
        this.id = id;
    }
}
