package io.github.alvdelci.libraryapi.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
public class DatabaseConfiguration {

    @Value("${spring.datasource.url}")
    String url;
    @Value("${spring.datasource.username}")
    String username;
    @Value("${spring.datasource.password}")
    String password;
    @Value("${spring.datasource.driver-class-name}")
    String driver;

    /*Nao utilizar o DriverManagerDataSource em producao pois ele nao suporta a carga de trabalho. usar apenas para resolver problemas rapidos em desenvolvimento*/
    //@Bean
    public DataSource dataSource() {
        DriverManagerDataSource ds = new DriverManagerDataSource();
        ds.setUrl(url);
        ds.setUsername(username);
        ds.setPassword(password);
        ds.setDriverClassName(driver);
        return ds;
    }


    @Bean
    public DataSource hikariDataSource() {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(url);
        config.setUsername(username);
        config.setPassword(password);
        config.setDriverClassName(driver);

        config.setMaximumPoolSize(10);//Maximo de conexoes liberadas na pool
        config.setMinimumIdle(1);//Tamanho inicial do pool
        config.setPoolName("library-db-pool");
        config.setMaxLifetime(600000); //10 minutos em milisegundos. Tempo que a conexao fica on
        config.setConnectionTimeout(100000); //timeout de conexao. lanca erro se atingir o tempo
        config.setConnectionTestQuery("select 1"); //Query de teste para verificar se a conexao foi estabelecida

        return new HikariDataSource(config);
    }

}
