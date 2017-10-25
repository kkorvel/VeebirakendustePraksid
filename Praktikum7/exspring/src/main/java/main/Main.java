package main;

import config.Config;
import dao.PersonDao;
import dao.SetupDao;
import model.Money;
import model.Person;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import service.TransferService;

public class Main {

    public static void main(String[] args) throws Exception {

        new SetupDao().createSchema();

        ApplicationContext ctx =
              new AnnotationConfigApplicationContext(Config.class);

        JdbcTemplate jdbcTemplate = ctx.getBean(JdbcTemplate.class);
        System.out.println(jdbcTemplate);
//        TransferService transferService = ctx.getBean(TransferService.class);

//        System.out.println(transferService);
//        System.out.println(transferService.getBankService());
//        jdbcTemplate.execute("INSERT INTO person values (1, 'John')");
//        jdbcTemplate.execute("INSERT INTO person values (1, 'Jill')");
//        PersonDao personDao = ctx.getBean(TransferService.class);

        TransferService ctxBean = ctx.getBean(TransferService.class);
//
        ctxBean.transfer(new Money(1,"EUR"), "A", "B");
//        personDao.save(new Person("Jill"));
//        System.out.println(personDao.getAllPersonsJpa());
        ((ConfigurableApplicationContext) ctx).close();
    }
}