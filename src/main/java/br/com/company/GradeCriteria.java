package br.com.company;


import br.com.company.util.Import;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SpringBootApplication
public class GradeCriteria implements CommandLineRunner {

    private static Logger LOG = LoggerFactory.getLogger(GradeCriteria.class);

    @Autowired
    private Import newImport;

    public static void main(String[] args) {
        LOG.info("STARTING THE APPLICATION");
        SpringApplication.run(GradeCriteria.class, args);
        LOG.info("APPLICATION FINISHED");
    }

    @Override
    public void run(String... args) {
        LOG.info("EXECUTING : command line runner");
        for (int i = 0; i < args.length; ++i) {
            LOG.info("args[{}]: {}", i, args[i]);
        }

        //newImport.importMangasToDatabse();
        //newImport.importCharacters();
    }
}
