package org.hibernate.examples.mapping.inheritance.subclass;

import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.examples.utils.HashTool;
import org.hibernate.examples.utils.ToStringHelper;

import javax.persistence.*;

/**
 * org.hibernate.examples.mapping.inheritance.subclass.CreditCard
 *
 * @author 배성혁 sunghyouk.bae@gmail.com
 * @since 2013. 11. 30. 오후 2:15
 */
@Entity(name = "Subclass_CreditCard")
@DiscriminatorValue(value = "CreditCard")
@org.hibernate.annotations.Cache(region = "example", usage = CacheConcurrencyStrategy.READ_WRITE)
// SecondaryTable은 1:1 join 을 나타냅니다.
@SecondaryTable(name = "Subclass_CreditCard_Card",
                pkJoinColumns = @PrimaryKeyJoinColumn(name = "BillingId"))
@DynamicInsert
@DynamicUpdate
public class CreditCard extends AbstractBilling {

    private String companyName;

    @Column(table = "Subclass_CreditCard_Card", nullable = false)
    private String number;

    @Column(table = "Subclass_CreditCard_Card", nullable = false)
    private Integer expMonth;

    @Column(table = "Subclass_CreditCard_Card", nullable = false)
    private Integer expYear;

    @Override
    public int hashCode() {
        return HashTool.compute(super.hashCode(), companyName, number);
    }

    @Override
    public ToStringHelper buildStringHelper() {
        return super.buildStringHelper()
                    .add("companyName", companyName)
                    .add("number", number)
                    .add("expMonth", expMonth)
                    .add("expYear", expYear);
    }

    private static final long serialVersionUID = 7982685426151968281L;

    public String getCompanyName() {
        return this.companyName;
    }

    public String getNumber() {
        return this.number;
    }

    public Integer getExpMonth() {
        return this.expMonth;
    }

    public Integer getExpYear() {
        return this.expYear;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public void setExpMonth(Integer expMonth) {
        this.expMonth = expMonth;
    }

    public void setExpYear(Integer expYear) {
        this.expYear = expYear;
    }
}
