package zarema_hibernate;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import pojos.Perfumes;

public class Zarema_Hibernate {  

    public static void main(String[] args) {
        Session roster = connection.Controller.getSessionFactory().openSession();
        Transaction trans = roster.beginTransaction();

        System.out.println("");
        System.out.println("Данные из таблицы:");
        list(roster.createCriteria(pojos.Perfumes.class).list());

        System.out.println("Добавление новых данных в таблицу.");
        //Сохранение данных в таблицу
        roster.save(new pojos.Perfumes("Хлоя", "15500 тг", "бергамот", "50 мл"));
        roster.save(new pojos.Perfumes("Шанель", "17200 тг", "миндаль", "50 мл"));
        roster.save(new pojos.Perfumes("Прада", "10000 тг", "древесина яблони", "30 мл"));
        roster.save(new pojos.Perfumes("Кацо", "20000 тг", "лунный цветок", "100 мл"));
        list(roster.createCriteria(pojos.Perfumes.class).list());

        System.out.println("Удаление записи, где присутствует аромат - 'бергамот'.");
        pojos.Perfumes bergamot = (pojos.Perfumes) roster.createCriteria(pojos.Perfumes.class)
                .add(Restrictions.eq("flavor", "бергамот"))
                .uniqueResult();
        roster.delete(bergamot);
        list(roster.createCriteria(pojos.Perfumes.class).list());

        System.out.println("Изменение аромата  'древесина яблони' на 'вишня'");
        pojos.Perfumes cherry = (pojos.Perfumes) roster.createCriteria(pojos.Perfumes.class)
                .add(Restrictions.eq("flavor", "древесина яблони"))
                .uniqueResult();
        cherry.setName("вишня");
        roster.update(cherry);
        list(roster.createCriteria(pojos.Perfumes.class).list());

        //Изменение данных 3 игрока - Томас
        System.out.println("Изменение данных духов Хлоя");
        pojos.Perfumes perfumechange = (pojos.Perfumes) roster.load(pojos.Perfumes.class, 3);
        perfumechange.setFlavor("брусника");
        perfumechange.setPrice("17000");

        roster.update(perfumechange);
        list(roster.createCriteria(pojos.Perfumes.class).list());


    }

    private static void list(List<Perfumes> names) {
        names.forEach(System.out::println);
        System.out.println("");
    }
}
