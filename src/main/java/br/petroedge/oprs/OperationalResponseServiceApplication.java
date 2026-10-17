package br.petroedge.oprs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"br.petroedge.oprs.api", "br.petroedge.oprs.common"})
public class OperationalResponseServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(OperationalResponseServiceApplication.class, args);
    }
}
