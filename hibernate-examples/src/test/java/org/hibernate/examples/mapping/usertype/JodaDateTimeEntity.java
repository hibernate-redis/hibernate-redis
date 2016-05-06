package org.hibernate.examples.mapping.usertype;

import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Type;
import org.hibernate.examples.model.AbstractHibernateEntity;
import org.hibernate.examples.model.DateTimeRange;
import org.hibernate.examples.utils.HashTool;
import org.joda.time.DateTime;

import javax.persistence.*;

/**
 * org.hibernate.examples.mapping.usertype.JodaDateTimeEntity
 *
 * @author 배성혁 sunghyouk.bae@gmail.com
 * @since 2013. 12. 3. 오후 9:08
 */
@Entity
@org.hibernate.annotations.Cache(region = "example", usage = CacheConcurrencyStrategy.READ_WRITE)
@DynamicInsert
@DynamicUpdate
public class JodaDateTimeEntity extends AbstractHibernateEntity<Long> {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "JodaStart")
    @Type(type = "org.hibernate.examples.usertype.JodaDateTimeUserType")
    private DateTime start;

    @Column(name = "JodaEnd")
    @Type(type = "org.hibernate.examples.usertype.JodaDateTimeUserType")
    private DateTime end;


    // 복합 수형인 경우 컬럼들을 명시해줘야 합니다.
    //
    @Embedded
    @AttributeOverrides({
                                @AttributeOverride(name = "startTime", column = @Column(name = "rangeStart1")),
                                @AttributeOverride(name = "endTime", column = @Column(name = "rangeEnd1"))
                        })
    private DateTimeRange range1;

    @Embedded
    @AttributeOverrides({
                                @AttributeOverride(name = "startTime", column = @Column(name = "rangeStart2")),
                                @AttributeOverride(name = "endTime", column = @Column(name = "rangeEnd2"))
                        })
    private DateTimeRange range2;

    @Override
    public int hashCode() {
        return HashTool.compute(start, end, range1, range2);
    }

    private static final long serialVersionUID = -5195581340713775796L;

    public Long getId() {
        return this.id;
    }

    public DateTime getStart() {
        return this.start;
    }

    public DateTime getEnd() {
        return this.end;
    }

    public DateTimeRange getRange1() {
        return this.range1;
    }

    public DateTimeRange getRange2() {
        return this.range2;
    }

    public void setStart(DateTime start) {
        this.start = start;
    }

    public void setEnd(DateTime end) {
        this.end = end;
    }

    public void setRange1(DateTimeRange range1) {
        this.range1 = range1;
    }

    public void setRange2(DateTimeRange range2) {
        this.range2 = range2;
    }

    protected void setId(Long id) {
        this.id = id;
    }
}
