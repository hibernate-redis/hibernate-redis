package org.hibernate.examples.mapping.associations.onetoone.unidirectionalManyToOne;

import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.examples.model.AbstractHibernateEntity;
import org.hibernate.examples.utils.HashTool;
import org.hibernate.examples.utils.ToStringHelper;

import javax.persistence.*;

/**
 * Cavalier가 Horse와 1:1 매핑인데, 두 엔티티는 서로 독립적인 Id를 가집니다.
 *
 * @author 배성혁 sunghyouk.bae@gmail.com
 * @since 2013. 11. 29. 오후 2:45
 */
@Entity
@org.hibernate.annotations.Cache(region = "example", usage = CacheConcurrencyStrategy.READ_WRITE)
@DynamicInsert
@DynamicUpdate
public class Cavalier extends AbstractHibernateEntity<Long> {

    @Id
    @GeneratedValue
    @Column(name = "horseId")
    private Long id;

    private String name;

    /**
     * FetchType.LAZY 라면 outer join 이 아니라 따로 로드된다.
     */
    @OneToOne // (fetch = FetchType.LAZY)
    @JoinColumn(name = "horseId")
    // @LazyToOne(LazyToOneOption.PROXY)
    private Horse horse;

    @Override
    public int hashCode() {
        return HashTool.compute(name);
    }

    @Override
    public ToStringHelper buildStringHelper() {
        return super.buildStringHelper()
                    .add("name", name);
    }

    private static final long serialVersionUID = 7850333928981763050L;

    public Long getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public Horse getHorse() {
        return this.horse;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setHorse(Horse horse) {
        this.horse = horse;
    }

    protected void setId(Long id) {
        this.id = id;
    }
}
