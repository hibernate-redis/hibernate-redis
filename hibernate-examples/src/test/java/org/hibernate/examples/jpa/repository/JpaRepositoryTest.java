package org.hibernate.examples.jpa.repository;

import org.hibernate.examples.AbstractJpaTest;
import org.hibernate.examples.mapping.Employee;
import org.junit.Test;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

import static org.fest.assertions.Assertions.assertThat;

/**
 * org.hibernate.examples.jpa.repository.JpaRepositoryTest
 *
 * @author 배성혁 sunghyouk.bae@gmail.com
 * @since 2013. 11. 28. 오후 10:58
 */
@Transactional
public class JpaRepositoryTest extends AbstractJpaTest {

    private static final Logger log = org.slf4j.LoggerFactory.getLogger(JpaRepositoryTest.class);
    @Autowired EmployeeRepository empRepository;
    @PersistenceContext EntityManager em;

    @Test
    public void injectEmployeeRepository() throws Exception {
        assertThat(empRepository).isNotNull();
        List<Employee> employees = empRepository.findAll();
        assertThat(employees).isNotNull();
    }

    @Test
    public void employeeFindByEmpNo() {
        Employee emp = new Employee();
        emp.setName("Sunghyouk Bae");
        emp.setEmpNo("21011");
        emp = empRepository.saveAndFlush(emp);
        em.clear();

        Employee loaded = empRepository.findByEmpNo(emp.getEmpNo());
        assertThat(loaded).isNotNull();
        assertThat(loaded).isEqualTo(emp);
        assertThat(loaded.getUpdatedTimestamp()).isNotNull();
        log.debug("Employee=[{}]", loaded);
    }
}
