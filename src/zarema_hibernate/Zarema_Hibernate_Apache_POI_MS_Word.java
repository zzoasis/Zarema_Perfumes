package zarema_hibernate;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import pojos.Perfumes;

public class Zarema_Hibernate_Apache_POI_MS_Word {  

    public static void main(String[] args) throws FileNotFoundException, IOException {
       try {
        XWPFDocument document = new XWPFDocument();
        FileOutputStream out = new FileOutputStream(new File("Perfumes.docx"));
        Session roster = connection.Controller.getSessionFactory().openSession();
        Transaction trans = roster.beginTransaction();

        System.out.println("");
        System.out.println("Данные из таблицы:");
        list(roster.createCriteria(pojos.Perfumes.class).list());

        System.out.println("Добавление новых данных в таблицу.");
        //Сохранение данных в таблицу
        roster.save(new pojos.Perfumes("Хлоя", "15500 тг", "Бергамот", "50 мл"));
        roster.save(new pojos.Perfumes("Шанель", "17200 тг", "Миндаль", "50 мл"));
        roster.save(new pojos.Perfumes("Прада", "10000 тг", "Древесина яблони", "30 мл"));
        roster.save(new pojos.Perfumes("Кацо", "20000 тг", "Лунный цветок", "100 мл"));
        list(roster.createCriteria(pojos.Perfumes.class).list());

        System.out.println("Удаление записи, где присутствует аромат - 'Бергамот'.");
        pojos.Perfumes bergamot = (pojos.Perfumes) roster.createCriteria(pojos.Perfumes.class)
                .add(Restrictions.eq("flavor", "Бергамот"))
                .uniqueResult();
        roster.delete(bergamot);
        list(roster.createCriteria(pojos.Perfumes.class).list());

        System.out.println("Изменение аромата  'Древесина яблони' на 'Вишня'");
        pojos.Perfumes cherry = (pojos.Perfumes) roster.createCriteria(pojos.Perfumes.class)
                .add(Restrictions.eq("flavor", "Древесина яблони"))
                .uniqueResult();
        cherry.setFlavor("Вишня");
        roster.update(cherry);
        list(roster.createCriteria(pojos.Perfumes.class).list());

        //Изменение данных 3 игрока - Томас
        System.out.println("Изменение данных духов Пандора");
        pojos.Perfumes perfumechange = (pojos.Perfumes) roster.load(pojos.Perfumes.class, 3);
        perfumechange.setFlavor("Брусника");
        perfumechange.setPrice("17000");

        roster.update(perfumechange);
        list(roster.createCriteria(pojos.Perfumes.class).list());
        XWPFTable table = document.createTable();
		
      XWPFTableRow tableRowOne = table.getRow(0);
      tableRowOne.getCell(0).setText("название");
      tableRowOne.addNewTableCell().setText("цена");
      tableRowOne.addNewTableCell().setText("аромат");
      tableRowOne.addNewTableCell().setText("объем");
		

      XWPFTableRow tableRowTwo = table.createRow();
      tableRowTwo.getCell(0).setText("Версаче");
      tableRowTwo.getCell(1).setText("16800 тг");
      tableRowTwo.getCell(2).setText("Лотос");
      tableRowTwo.getCell(3).setText("50 мл");
      
      XWPFTableRow tableRowThree = table.createRow();
      tableRowThree.getCell(0).setText("Диор");
      tableRowThree.getCell(1).setText("21500 тг");
      tableRowThree.getCell(2).setText("Сицилийский мандарин");
      tableRowThree.getCell(3).setText("100 мл");
	
      XWPFTableRow tableRowFour = table.createRow();
      tableRowFour.getCell(0).setText("Пандора");
      tableRowFour.getCell(1).setText("17000 тг");
      tableRowFour.getCell(2).setText("Брусника");
      tableRowFour.getCell(3).setText("20 мл");
      
      XWPFTableRow tableRowFive = table.createRow();
      tableRowFive.getCell(0).setText("Хьюго Босс");
      tableRowFive.getCell(1).setText("28520 тг");
      tableRowFive.getCell(2).setText("Гвоздика");
      tableRowFive.getCell(3).setText("100 мл");
      
      XWPFTableRow tableRowSix = table.createRow();
      tableRowSix.getCell(0).setText("Шанель");
      tableRowSix.getCell(1).setText("17200 тг");
      tableRowSix.getCell(2).setText("Миндаль");
      tableRowSix.getCell(3).setText("50 мл");
      
      XWPFTableRow tableRowSeven = table.createRow();
      tableRowSeven.getCell(0).setText("Прада");
      tableRowSeven.getCell(1).setText("10000 тг");
      tableRowSeven.getCell(2).setText("Вишня");
      tableRowSeven.getCell(3).setText("30 мл");
      
      XWPFTableRow tableRowEight = table.createRow();
      tableRowEight.getCell(0).setText("Кацо");
      tableRowEight.getCell(1).setText("20000 тг");
      tableRowEight.getCell(2).setText("Лунный цветок");
      tableRowEight.getCell(3).setText("100 мл");
	
    document.write(out);
       out.close();
       }
       catch(IOException e) {
      System.out.println(e);
  }
  System.out.println("Документ с измененными данными создан");
    }
    

    private static void list(List<Perfumes> names) {
        names.forEach(System.out::println);
        System.out.println("");
        
        
    }
    
    
}
