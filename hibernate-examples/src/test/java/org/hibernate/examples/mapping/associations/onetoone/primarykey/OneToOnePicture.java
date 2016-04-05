package org.hibernate.examples.mapping.associations.onetoone.primarykey;

import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.examples.model.AbstractHibernateEntity;
import org.hibernate.examples.utils.HashTool;
import org.hibernate.examples.utils.ToStringHelper;

import javax.persistence.*;

/**
 * org.hibernate.examples.mapping.associations.onetoone.primarykey.OneToOnePicture
 *
 * @author 배성혁 sunghyouk.bae@gmail.com
 * @since 2013. 11. 29. 오후 3:20
 */
@Entity
@org.hibernate.annotations.Cache(region = "example", usage = CacheConcurrencyStrategy.READ_WRITE)
@DynamicInsert
@DynamicUpdate
public class OneToOnePicture extends AbstractHibernateEntity<Long> {

    protected OneToOnePicture() {}

    public OneToOnePicture(OneToOneAuthor author) {
        this.author = author;
    }

    @Id
    @Column(name = "authorId")
    private Long id;

    @MapsId
    @OneToOne
    @JoinColumn(name = "authorId")
    private OneToOneAuthor author;

    private String path;

    @Override
    public int hashCode() {
        return HashTool.compute(author);
    }

    @Override
    public ToStringHelper buildStringHelper() {
        return super.buildStringHelper()
                    .add("author", author)
                    .add("path", path);
    }

    private static final long serialVersionUID = 8050166540766541481L;

    public Long getId() {
        return this.id;
    }

    public OneToOneAuthor getAuthor() {
        return this.author;
    }

    public String getPath() {
        return this.path;
    }

    public void setAuthor(OneToOneAuthor author) {
        this.author = author;
    }

    public void setPath(String path) {
        this.path = path;
    }

    protected void setId(Long id) {
        this.id = id;
    }
}
