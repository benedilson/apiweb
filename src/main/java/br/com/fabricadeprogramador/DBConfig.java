package br.com.fabricadeprogramador;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
public class DBConfig {
	
	@Bean
    public DataSource dataSource(){
       DriverManagerDataSource dataSource = new DriverManagerDataSource();
       dataSource.setDriverClassName("oracle.jdbc.driver.OracleDriver");
       dataSource.setUrl("jdbc:oracle:thin:@10.138.100.128:1521/GETRANDEV");
       dataSource.setUsername( "C##DESENV" );
       dataSource.setPassword( "D123456" );
       return dataSource;
    }
	
}
