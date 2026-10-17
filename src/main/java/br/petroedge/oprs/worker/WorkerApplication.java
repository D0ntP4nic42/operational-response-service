package br.petroedge.oprs.worker;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication(scanBasePackages = {"br.petroedge.oprs.worker", "br.petroedge.oprs.common"})
public class WorkerApplication {
    public static void main(String[] args) {
        new SpringApplicationBuilder(WorkerApplication.class)
            .web(WebApplicationType.NONE)
            .profiles("worker")  // ainda pode usar profile pra configuração
            .run(args);
    }
}
