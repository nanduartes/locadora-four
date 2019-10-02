package br.com.locadorafour.projeto.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

// configuração para ler repositório de dados
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "br.com.locadorafour.projeto.repository")
public class DBConfig {

}
