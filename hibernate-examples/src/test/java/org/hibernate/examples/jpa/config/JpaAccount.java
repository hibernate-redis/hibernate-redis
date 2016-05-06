package org.hibernate.examples.jpa.config;

import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.examples.model.AbstractHibernateEntity;
import org.hibernate.examples.utils.HashTool;
import org.hibernate.examples.utils.ToStringHelper;

import javax.persistence.*;


@Entity
@org.hibernate.annotations.Cache(region = "example", usage = CacheConcurrencyStrategy.READ_WRITE)
@NamedQuery(name = "JpaAccount.findByName", query = "select ja from JpaAccount ja where ja.name=?1")
public class JpaAccount extends AbstractHibernateEntity<Long> {

    @Id
    @GeneratedValue
    private Long id;

    private Double cashBalance;

    @Column(name = "AccountName", nullable = false, length = 32)
    private String name;

    @Override
    public int hashCode() {
        return HashTool.compute(name);
    }

    @Override
    public ToStringHelper buildStringHelper() {
        return super.buildStringHelper()
                    .add("name", name);
    }

    private static final long serialVersionUID = 8986275418970766284L;

    public Long getId() {
        return this.id;
    }

    public Double getCashBalance() {
        return this.cashBalance;
    }

    public String getName() {
        return this.name;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setCashBalance(Double cashBalance) {
        this.cashBalance = cashBalance;
    }

    public void setName(String name) {
        this.name = name;
    }
}
