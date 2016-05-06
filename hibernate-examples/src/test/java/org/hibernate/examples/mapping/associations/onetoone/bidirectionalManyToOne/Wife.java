package org.hibernate.examples.mapping.associations.onetoone.bidirectionalManyToOne;

import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.examples.model.AbstractHibernateEntity;
import org.hibernate.examples.utils.HashTool;
import org.hibernate.examples.utils.ToStringHelper;

import javax.persistence.*;

/**
 * org.hibernate.examples.mapping.associations.onetoone.bidirectionalManyToOne.Wife
 *
 * @author 배성혁 sunghyouk.bae@gmail.com
 * @since 2013. 11. 29. 오후 3:11
 */
@Entity
@org.hibernate.annotations.Cache(region = "example", usage = CacheConcurrencyStrategy.READ_WRITE)
@DynamicInsert
@DynamicUpdate
public class Wife extends AbstractHibernateEntity<Long> {

    @Id
    @GeneratedValue
    @Column(name = "wifeId")
    private Long id;

    private String name;

    @OneToOne(mappedBy = "wife", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Husband husband;

    @Override
    public int hashCode() {
        return HashTool.compute(name);
    }

    @Override
    public ToStringHelper buildStringHelper() {
        return super.buildStringHelper()
                    .add("name", name);
    }

    private static final long serialVersionUID = -8695477909351734067L;

    public Long getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public Husband getHusband() {
        return this.husband;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setHusband(Husband husband) {
        this.husband = husband;
    }

    protected void setId(Long id) {
        this.id = id;
    }
}
