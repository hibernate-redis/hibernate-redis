package org.hibernate.examples.mapping.associations.manytoone;

import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.examples.model.AbstractHibernateEntity;
import org.hibernate.examples.utils.HashTool;
import org.hibernate.examples.utils.ToStringHelper;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * org.hibernate.examples.mapping.associations.manytoone.SalesForce
 *
 * @author 배성혁 sunghyouk.bae@gmail.com
 * @since 2013. 11. 29. 오전 10:32
 */
@Entity
@org.hibernate.annotations.Cache(region = "example", usage = CacheConcurrencyStrategy.READ_WRITE)
public class SalesForce extends AbstractHibernateEntity<Long> {

    protected SalesForce() {}

    public SalesForce(String corporation) {
        this.corporation = corporation;
    }

    @Id
    @GeneratedValue
    private Long id;

    private String corporation;

    @OneToMany(mappedBy = "salesForce", cascade = { CascadeType.ALL }, orphanRemoval = true)
    @LazyCollection(LazyCollectionOption.EXTRA)
    private Set<SalesGuy> salesGuys = new HashSet<SalesGuy>();

    @Override
    public int hashCode() {
        return HashTool.compute(corporation);
    }

    @Override
    public ToStringHelper buildStringHelper() {
        return super.buildStringHelper()
                    .add("corporation", corporation);
    }

    private static final long serialVersionUID = -6886236659031901168L;

    public Long getId() {
        return this.id;
    }

    public String getCorporation() {
        return this.corporation;
    }

    public Set<SalesGuy> getSalesGuys() {
        return this.salesGuys;
    }

    public void setCorporation(String corporation) {
        this.corporation = corporation;
    }

    public void setSalesGuys(Set<SalesGuy> salesGuys) {
        this.salesGuys = salesGuys;
    }

    protected void setId(Long id) {
        this.id = id;
    }
}
