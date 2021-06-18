package com.ems.application.service.config;

import javax.persistence.EntityManager;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


import com.ems.application.service.adapter.EMSServiceAdapter;
import com.ems.application.service.api.EMSService;
import com.ems.domain.spi.EMSPersistancePort;
import com.ems.infrastructure.persistance.hibernate.adapter.EMSSpringHibernateAdapter;
import com.ems.infrastructure.persistance.jpa.adapter.EMSSpringJpaAdapter;
import com.ems.infrastructure.persistance.jpa.repository.EmployeeRespository;

import io.opentracing.Tracer;





@Configuration
public class ApplicationConfiguration {

    @Bean
    public EMSService getProductService(EMSPersistancePort emsPersistencePort, Tracer tracer) {
        return new EMSServiceAdapter(emsPersistencePort,tracer);
    }
    
    @Bean
	public ModelMapper getModelMapper() {
    	ModelMapper mapper = new ModelMapper();
    	mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		return mapper;
	}
    
  /*  @Bean
    public EMSPersistancePort getProductPersistencPort(EmployeeRespository employeeRepository,ModelMapper mapper) {
        return new EMSSpringJpaAdapter(employeeRepository,mapper);
    }
   */
    @Bean
    public EMSPersistancePort getProductPersistencPort(EntityManager entityManager,ModelMapper mapper) {
        return new EMSSpringHibernateAdapter(entityManager,mapper);
    }
	
}
