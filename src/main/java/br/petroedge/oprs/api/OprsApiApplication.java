package br.petroedge.oprs.api;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.persistence.autoconfigure.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = {"br.petroedge.oprs.api", "br.petroedge.oprs.common"})
@EnableJpaRepositories(basePackages = {"br.petroedge.oprs.api.repository", "br.petroedge.oprs.common.repository"})
@EntityScan(basePackages = {"br.petroedge.oprs.api.entity", "br.petroedge.oprs.common.entity"})
public class OprsApiApplication {
    public static void main(String[] args) {
        new SpringApplicationBuilder(OprsApiApplication.class).profiles("api").run(args);
    }
}
