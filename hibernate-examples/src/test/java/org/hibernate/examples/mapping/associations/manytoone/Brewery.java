package org.hibernate.examples.mapping.associations.manytoone;

import org.hibernate.annotations.*;
import org.hibernate.examples.model.AbstractHibernateEntity;
import org.hibernate.examples.utils.HashTool;
import org.hibernate.examples.utils.ToStringHelper;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * org.hibernate.examples.mapping.associations.manytoone.Brewery
 *
 * @author 배성혁 sunghyouk.bae@gmail.com
 * @since 2013. 11. 29. 오전 9:40
 */
@Entity
@org.hibernate.annotations.Cache(region = "example", usage = CacheConcurrencyStrategy.READ_WRITE)
public class Brewery extends AbstractHibernateEntity<Long> {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    @OneToMany(cascade = { CascadeType.ALL }, orphanRemoval = true)
    @LazyCollection(LazyCollectionOption.EXTRA)
    Set<Beer> beers = new HashSet<Beer>();

    @ManyToOne(fetch = FetchType.LAZY)
    @LazyToOne(LazyToOneOption.PROXY)
    @JoinColumn(name = "vendorId")
    private BeerVendor vendor;

    @Override
    public int hashCode() {
        return HashTool.compute(name);
    }

    @Override
    public ToStringHelper buildStringHelper() {
        return super.buildStringHelper()
                    .add("name", name);
    }

    private static final long serialVersionUID = -7350106569344824229L;

    public Long getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public Set<Beer> getBeers() {
        return this.beers;
    }

    public BeerVendor getVendor() {
        return this.vendor;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBeers(Set<Beer> beers) {
        this.beers = beers;
    }

    public void setVendor(BeerVendor vendor) {
        this.vendor = vendor;
    }

    protected void setId(Long id) {
        this.id = id;
    }
}
