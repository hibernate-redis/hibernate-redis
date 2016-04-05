package org.hibernate.examples.mapping.inheritance.joinedsubclass;

import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.examples.model.AbstractHibernateEntity;
import org.hibernate.examples.utils.HashTool;
import org.hibernate.examples.utils.ToStringHelper;

import javax.persistence.*;

/**
 * org.hibernate.examples.mapping.inheritance.joinedsubclass.Person
 *
 * @author 배성혁 sunghyouk.bae@gmail.com
 * @since 2013. 11. 30. 오후 12:54
 */
@Entity
@Table(name = "JoinedSubclass_Person")
@org.hibernate.annotations.Cache(region = "example", usage = CacheConcurrencyStrategy.READ_WRITE)
@Inheritance(strategy = InheritanceType.JOINED)
@DynamicInsert
@DynamicUpdate
public abstract class Person extends AbstractHibernateEntity<Long> {

    @Id
    @GeneratedValue
    @Column(name = "personId")
    private Long id;

    @Column(name = "personName", nullable = false, length = 128)
    private String name;

    @Column(name = "regidentNo", nullable = false, length = 128)
    private String regidentNo;

    private Integer age;

    @Override
    public int hashCode() {
        return HashTool.compute(name, regidentNo);
    }

    @Override
    public ToStringHelper buildStringHelper() {
        return super.buildStringHelper()
                    .add("name", name)
                    .add("regidentNo", regidentNo);
    }

    private static final long serialVersionUID = 823321933233116966L;

    public Long getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getRegidentNo() {
        return this.regidentNo;
    }

    public Integer getAge() {
        return this.age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRegidentNo(String regidentNo) {
        this.regidentNo = regidentNo;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    protected void setId(Long id) {
        this.id = id;
    }
}
