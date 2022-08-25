package com.mrityunjoy.springbootconcepts;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;

import com.mrityunjoy.springbootconcepts.model.Student;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootApplication
public class SpringBootConceptsApplication {

	private static AnnotationConfigApplicationContext annotationConfigApplicationContext;

	@Autowired
	public SpringBootConceptsApplication(AnnotationConfigApplicationContext annotationConfigApplicationContext) {
		SpringBootConceptsApplication.annotationConfigApplicationContext = annotationConfigApplicationContext;
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringBootConceptsApplication.class, args);
		
		Student beluga = annotationConfigApplicationContext.getBean("beluga", Student.class);
		
		log.info(String.format("Name beluga: %s", beluga.getName()));
		
		annotationConfigApplicationContext.registerBean("hacker", Student.class, () -> {
			Student hacker = new Student();
			hacker.setName("hecker");
			return hacker;
		});
		
		Student hacker = annotationConfigApplicationContext.getBean("hacker", Student.class);
		
		log.info(String.format("Name hacker: %s", hacker.getName()));
		
		Student skittle = annotationConfigApplicationContext.getBean("skittle", Student.class);

		log.info(String.format("Name skittle: %s", skittle.getName()));

		Student skittleAgain = annotationConfigApplicationContext.getBean("skittle", Student.class);

		log.info(String.format("Name skittleAgain: %s", skittleAgain.getName()));

		Student god = annotationConfigApplicationContext.getBean("god", Student.class);

		log.info(String.format("Name god: %s", god.getName()));

		Student godAgain = annotationConfigApplicationContext.getBean("god", Student.class);

		log.info(String.format("Name godAgain: %s", godAgain.getName()));
	}

	@Bean
	Student beluga() {
		Student beluga = new Student();
		beluga.setName("begula");
		return beluga;
	}
	
	@Bean
	@Scope(BeanDefinition.SCOPE_SINGLETON)
	Student skittle() {
		log.info("Creating skittle bean");
		Student skittle = new Student();
		skittle.setName("skittle");
		return skittle;
	}
	
	@Bean
	@Scope(BeanDefinition.SCOPE_PROTOTYPE)
	Student god() {
		log.info("Creating god bean");
		Student god = new Student();
		god.setName("god");
		return god;
	}
}
