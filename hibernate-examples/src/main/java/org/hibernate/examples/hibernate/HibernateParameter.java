package org.hibernate.examples.hibernate;

import org.hibernate.examples.AbstractNamedParameter;
import org.hibernate.examples.utils.ToStringHelper;
import org.hibernate.type.StandardBasicTypes;
import org.hibernate.type.Type;

/**
 * Hibernate Query 에 사용할 인자 정보를 표현합니다.
 *
 * @author 배성혁 sunghyouk.bae@gmail.com
 * @since 2013. 11. 28. 오후 1:59
 */
public class HibernateParameter extends AbstractNamedParameter {

    private org.hibernate.type.Type paramType = StandardBasicTypes.SERIALIZABLE;

    public HibernateParameter(String name, Object value) {
        super(name, value);
    }

    public HibernateParameter(String name, Object value, org.hibernate.type.Type paramType) {
        super(name, value);
        this.paramType = paramType;
    }

    @Override
    public ToStringHelper buildStringHelper() {
        return super.buildStringHelper().add("paramType", paramType);
    }

    private static final long serialVersionUID = -228167603287695079L;

    public Type getParamType() {
        return this.paramType;
    }

    public void setParamType(Type paramType) {
        this.paramType = paramType;
    }
}
