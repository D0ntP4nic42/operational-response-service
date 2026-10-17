package br.petroedge.oprs.api;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication(scanBasePackages = { "br.petroedge.oprs.api", "br.petroedge.oprs.common" })
public class OprsApiApplication {
    public static void main(String[] args) {
        new SpringApplicationBuilder(OprsApiApplication.class)
                .profiles("api")
                .run(args);
    }
}
