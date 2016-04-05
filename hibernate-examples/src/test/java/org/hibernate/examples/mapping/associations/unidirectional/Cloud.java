package org.hibernate.examples.mapping.associations.unidirectional;

import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.examples.model.AbstractHibernateEntity;
import org.hibernate.examples.utils.HashTool;
import org.hibernate.examples.utils.ToStringHelper;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * org.hibernate.examples.mapping.associations.unidirectional.Cloud
 *
 * @author 배성혁 sunghyouk.bae@gmail.com
 * @since 2013. 11. 29. 오후 3:59
 */
@Entity
@org.hibernate.annotations.Cache(region = "example", usage = CacheConcurrencyStrategy.READ_WRITE)
@DynamicInsert
@DynamicUpdate
public class Cloud extends AbstractHibernateEntity<Long> {

    @Id
    @GeneratedValue
    private Long id;

    private String type;

    private Double length;

    @OneToMany(cascade = { CascadeType.ALL })
    @JoinTable
    private Set<SnowFlake> producedSnowFlakes = new HashSet<SnowFlake>();

    @Override
    public int hashCode() {
        return HashTool.compute(type, length);
    }

    @Override
    public ToStringHelper buildStringHelper() {
        return super.buildStringHelper()
                    .add("type", type)
                    .add("length", length);
    }

    private static final long serialVersionUID = 8894797675268174082L;

    public Long getId() {
        return this.id;
    }

    public String getType() {
        return this.type;
    }

    public Double getLength() {
        return this.length;
    }

    public Set<SnowFlake> getProducedSnowFlakes() {
        return this.producedSnowFlakes;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setLength(Double length) {
        this.length = length;
    }

    public void setProducedSnowFlakes(Set<SnowFlake> producedSnowFlakes) {
        this.producedSnowFlakes = producedSnowFlakes;
    }
}
