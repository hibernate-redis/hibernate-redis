package org.hibernate.examples.mapping.associations.onetomany.set;

import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.examples.model.AbstractHibernateEntity;
import org.hibernate.examples.utils.HashTool;
import org.hibernate.examples.utils.ToStringHelper;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * org.hibernate.examples.mapping.associations.onetomany.set.ProductItem
 *
 * @author 배성혁 sunghyouk.bae@gmail.com
 * @since 2013. 11. 29. 오후 2:15
 */
@Entity
@org.hibernate.annotations.Cache(region = "example", usage = CacheConcurrencyStrategy.READ_WRITE)
@DynamicInsert
@DynamicUpdate
public class ProductItem extends AbstractHibernateEntity<Long> {

    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private String description;
    private BigDecimal initialPrice;
    private BigDecimal reservePrice;

    @Temporal(TemporalType.DATE)
    private Date startDate;

    @Temporal(TemporalType.DATE)
    private Date endDate;

    @CollectionTable(name = "ProductItemImages", joinColumns = { @JoinColumn(name = "productItemId") })
    @ElementCollection(targetClass = ProductImage.class)
    @org.hibernate.annotations.Cascade(org.hibernate.annotations.CascadeType.ALL)
    private Set<ProductImage> images = new HashSet<ProductImage>();

    public boolean removeImage(ProductImage image) {
        return images.remove(image);
    }

    @Override
    public int hashCode() {
        return HashTool.compute(name);
    }

    @Override
    public ToStringHelper buildStringHelper() {
        return super.buildStringHelper()
                    .add("name", name)
                    .add("initialPrice", initialPrice)
                    .add("reservePrice", reservePrice);
    }

    private static final long serialVersionUID = 9176636190484261550L;

    public Long getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getDescription() {
        return this.description;
    }

    public BigDecimal getInitialPrice() {
        return this.initialPrice;
    }

    public BigDecimal getReservePrice() {
        return this.reservePrice;
    }

    public Date getStartDate() {
        return this.startDate;
    }

    public Date getEndDate() {
        return this.endDate;
    }

    public Set<ProductImage> getImages() {
        return this.images;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setInitialPrice(BigDecimal initialPrice) {
        this.initialPrice = initialPrice;
    }

    public void setReservePrice(BigDecimal reservePrice) {
        this.reservePrice = reservePrice;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public void setImages(Set<ProductImage> images) {
        this.images = images;
    }

    protected void setId(Long id) {
        this.id = id;
    }
}
