package org.hibernate.examples.mapping.associations.onetomany.map;

import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.examples.model.AbstractHibernateEntity;
import org.hibernate.examples.utils.HashTool;
import org.hibernate.examples.utils.ToStringHelper;

import javax.persistence.*;
import java.util.HashMap;
import java.util.Map;

/**
 * org.hibernate.examples.mapping.associations.onetomany.map.OneToManyCar
 *
 * @author 배성혁 sunghyouk.bae@gmail.com
 * @since 2013. 11. 29. 오후 1:25
 */
@Entity
@org.hibernate.annotations.Cache(region = "example", usage = CacheConcurrencyStrategy.READ_WRITE)
public class OneToManyCar extends AbstractHibernateEntity<Long> {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    @CollectionTable(name = "OneToMany_Car_Option_Map", joinColumns = { @JoinColumn(name = "carId") })
    @MapKeyClass(String.class) // Map의 Key 에 해당하는 놈의 수형
    @ElementCollection(targetClass = String.class, fetch = FetchType.EAGER)
    private Map<String, String> options = new HashMap<String, String>();

    @CollectionTable(name = "OneToMany_Car_Option_Table", joinColumns = { @JoinColumn(name = "carId") })
    @MapKeyClass(String.class)
    @ElementCollection(targetClass = OneToManyCarOption.class, fetch = FetchType.EAGER)
    private Map<String, OneToManyCarOption> carOptions = new HashMap<String, OneToManyCarOption>();

    @Override
    public int hashCode() {
        return HashTool.compute(name);
    }

    @Override
    public ToStringHelper buildStringHelper() {
        return super.buildStringHelper()
                    .add("name", name);
    }

    private static final long serialVersionUID = 6690100694736931758L;

    public Long getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public Map<String, String> getOptions() {
        return this.options;
    }

    public Map<String, OneToManyCarOption> getCarOptions() {
        return this.carOptions;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setOptions(Map<String, String> options) {
        this.options = options;
    }

    public void setCarOptions(Map<String, OneToManyCarOption> carOptions) {
        this.carOptions = carOptions;
    }

    protected void setId(Long id) {
        this.id = id;
    }
}
