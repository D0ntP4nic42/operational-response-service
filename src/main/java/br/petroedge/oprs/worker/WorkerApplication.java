package br.petroedge.oprs.worker;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.persistence.autoconfigure.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication(scanBasePackages = { "br.petroedge.oprs.worker", "br.petroedge.oprs.common" })
@EnableJpaRepositories(basePackages = "br.petroedge.oprs.common.repository")
@EntityScan(basePackages = "br.petroedge.oprs.common")
@EnableScheduling 
public class WorkerApplication {
    public static void main(String[] args) {
        new SpringApplicationBuilder(WorkerApplication.class)
                .web(WebApplicationType.NONE)
                .profiles("worker")
                .run(args);
    }
}
