package org.hibernate.examples.mapping.associations.onetomany.list;

import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.examples.model.AbstractHibernateEntity;
import org.hibernate.examples.utils.HashTool;
import org.hibernate.examples.utils.ToStringHelper;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * org.hibernate.examples.mapping.associations.onetomany.list.OneToManyFather
 *
 * @author 배성혁 sunghyouk.bae@gmail.com
 * @since 2013. 11. 29. 오후 1:12
 */
@Entity
@org.hibernate.annotations.Cache(region = "example", usage = CacheConcurrencyStrategy.READ_WRITE)
public class OneToManyFather extends AbstractHibernateEntity<Long> {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    // inverse=false
    @OneToMany(cascade = { CascadeType.ALL }, fetch = FetchType.LAZY)
    @JoinTable(name = "OneToMany_Father_Child")
    @LazyCollection(LazyCollectionOption.EXTRA)
    @OrderColumn(name = "birthday")                     // @OrderColumn 으로 정렬하여 List를 만듭니다.
    private List<OneToManyChild> children = new ArrayList<OneToManyChild>();

    @Override
    public int hashCode() {
        return HashTool.compute(name);
    }

    @Override
    public ToStringHelper buildStringHelper() {
        return super.buildStringHelper()
                    .add("name", name);
    }

    private static final long serialVersionUID = 7615097053691800164L;

    public Long getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public List<OneToManyChild> getChildren() {
        return this.children;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setChildren(List<OneToManyChild> children) {
        this.children = children;
    }

    protected void setId(Long id) {
        this.id = id;
    }
}
