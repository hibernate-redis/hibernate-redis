package org.hibernate.examples.mapping.associations.onetomany.list;

import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.examples.model.AbstractHibernateEntity;
import org.hibernate.examples.utils.HashTool;
import org.hibernate.examples.utils.ToStringHelper;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

/**
 * org.hibernate.examples.mapping.associations.onetomany.list.OneToManyChild
 *
 * @author 배성혁 sunghyouk.bae@gmail.com
 * @since 2013. 11. 29. 오후 1:12
 */
@Entity
@org.hibernate.annotations.Cache(region = "example", usage = CacheConcurrencyStrategy.READ_WRITE)
public class OneToManyChild extends AbstractHibernateEntity<Long> {

    protected OneToManyChild() {}

    public OneToManyChild(String name) {
        this.name = name;
    }

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private Date birthday;

    @Override
    public int hashCode() {
        return HashTool.compute(name);
    }

    @Override
    public ToStringHelper buildStringHelper() {
        return super.buildStringHelper()
                    .add("name", name)
                    .add("birthday", birthday);
    }

    private static final long serialVersionUID = 8340891121768039665L;

    public Long getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public Date getBirthday() {
        return this.birthday;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    protected void setId(Long id) {
        this.id = id;
    }
}
