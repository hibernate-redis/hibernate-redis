package org.hibernate.examples.mapping.associations.manytomany;

import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.examples.model.AbstractHibernateEntity;
import org.hibernate.examples.utils.HashTool;
import org.hibernate.examples.utils.ToStringHelper;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * 은행 계좌
 *
 * @author 배성혁 sunghyouk.bae@gmail.com
 * @since 2013. 11. 28. 오후 11:45
 */
@Entity
@org.hibernate.annotations.Cache(region = "example", usage = CacheConcurrencyStrategy.READ_WRITE)
public class BankAccount extends AbstractHibernateEntity<Long> {

    @Id
    @GeneratedValue
    @Column(name = "accountId")
    private Long id;

    /**
     * 은행 계좌 번호
     */
    @Column(length = 32)
    private String accountNumber;

    /**
     * 은행 계정은 소유자가 여러명이 된다.
     */
    // many-to-many 에서는 둘 중 하나는 mappedBy 를 지정해야 한다. ( hbm에서는 inverse )
    @ManyToMany(mappedBy = "bankAccounts", cascade = { CascadeType.REFRESH })
    private Set<AccountOwner> owners = new HashSet<AccountOwner>();

    @Override
    public int hashCode() {
        return HashTool.compute(accountNumber);
    }

    @Override
    public ToStringHelper buildStringHelper() {
        return super.buildStringHelper()
                    .add("accountNumber", accountNumber);
    }

    private static final long serialVersionUID = -4101571945249649442L;

    public Long getId() {
        return this.id;
    }

    public String getAccountNumber() {
        return this.accountNumber;
    }

    public Set<AccountOwner> getOwners() {
        return this.owners;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public void setOwners(Set<AccountOwner> owners) {
        this.owners = owners;
    }

    protected void setId(Long id) {
        this.id = id;
    }
}
