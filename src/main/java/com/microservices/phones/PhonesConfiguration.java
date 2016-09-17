package com.microservices.phones;

import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import javax.sql.DataSource;

import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;

@Configuration
@ComponentScan
@EntityScan("com.microservices.phones")
@EnableJpaRepositories("com.microservices.phones")
@PropertySource("classpath:db-config.properties")
public class PhonesConfiguration {

	protected Logger logger;

	public PhonesConfiguration() {
		logger = Logger.getLogger(getClass().getName());
	}

	@Bean
	public DataSource dataSource() {
		logger.info("dataSource() invoked");

		// Create an in-memory H2 relational database containing some demo
		DataSource dataSource = (new EmbeddedDatabaseBuilder()).addScript("classpath:testdb/schema.sql")
				.addScript("classpath:testdb/data.sql").build();

		logger.info("dataSource = " + dataSource);

		// Sanity check
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		List<Map<String, Object>> accounts = jdbcTemplate.queryForList("SELECT number FROM T_PHONENUMBER");
		logger.info("System has " + accounts.size() + " phoneNumber");

		return dataSource;
	}
}
