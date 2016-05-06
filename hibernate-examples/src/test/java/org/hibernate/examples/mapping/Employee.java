package org.hibernate.examples.mapping;

import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Type;
import org.hibernate.examples.model.AbstractHibernateEntity;
import org.hibernate.examples.model.UpdatedTimestampEntity;
import org.hibernate.examples.utils.HashTool;
import org.hibernate.examples.utils.ToStringHelper;
import org.joda.time.DateTime;
import org.slf4j.Logger;

import javax.persistence.*;
import java.util.Date;

/**
 * org.hibernate.examples.mapping.Employee
 *
 * @author 배성혁 sunghyouk.bae@gmail.com
 * @since 2013. 11. 28. 오후 3:49
 */
@Entity
@org.hibernate.annotations.Cache(region = "example", usage = CacheConcurrencyStrategy.READ_WRITE)
@DynamicInsert
@DynamicUpdate
@SequenceGenerator(name = "employee_seq", sequenceName = "employee_seq")
public class Employee extends AbstractHibernateEntity<Long> implements UpdatedTimestampEntity {

    private static final Logger log = org.slf4j.LoggerFactory.getLogger(Employee.class);
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "employee_seq")
    @Column(name = "employeeId")
    private Long id;

    @Column(name = "empNo", nullable = false, length = 32)
    private String empNo;

    @Column(name = "employeeName", nullable = false, length = 32)
    private String name;

    @Column(name = "email", length = 32)
    private String email;

    @Temporal(TemporalType.DATE)
    private Date birthday;

    @Lob
    private byte[] binaryData;

    @Type(type = "org.hibernate.examples.usertype.JodaDateTimeUserType")
    private DateTime hireDate;
    /**
     * UserType 예
     */
    @Type(type = "org.hibernate.examples.usertype.JodaDateTimeUserType")
    private DateTime updatedTimestamp;

    /**
     * 엔티티의 최근 갱신 일자를 수정합니다.
     */
    @PrePersist
    @Override
    public void updateUpdatedTimestamp() {
        updatedTimestamp = DateTime.now();
        log.trace("저장하기 전에 UpdatedTimestamp를 갱신합니다. updatedTimestamp=[{}]", updatedTimestamp);
    }

    @Override
    public int hashCode() {
        return HashTool.compute(empNo, name);
    }

    @Override
    public ToStringHelper buildStringHelper() {
        return super.buildStringHelper()
                    .add("id", id)
                    .add("empNo", empNo)
                    .add("name", name)
                    .add("email", email);
    }

    private static final long serialVersionUID = 6878934074258579705L;

    public Long getId() {
        return this.id;
    }

    public String getEmpNo() {
        return this.empNo;
    }

    public String getName() {
        return this.name;
    }

    public String getEmail() {
        return this.email;
    }

    public Date getBirthday() {
        return this.birthday;
    }

    public byte[] getBinaryData() {
        return this.binaryData;
    }

    public DateTime getHireDate() {
        return this.hireDate;
    }

    public DateTime getUpdatedTimestamp() {
        return this.updatedTimestamp;
    }

    public void setEmpNo(String empNo) {
        this.empNo = empNo;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public void setBinaryData(byte[] binaryData) {
        this.binaryData = binaryData;
    }

    public void setHireDate(DateTime hireDate) {
        this.hireDate = hireDate;
    }

    public void setUpdatedTimestamp(DateTime updatedTimestamp) {
        this.updatedTimestamp = updatedTimestamp;
    }

    protected void setId(Long id) {
        this.id = id;
    }
}
