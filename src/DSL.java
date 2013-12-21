import org.junit.Assert;
import org.junit.Test;


public class DSL {

    static final String LINE_SEPARATOR = System.getProperty("line.separator");

    @Test
    public void testCreateTablel() {
        final String RESULT =
            "create table EMPLOYEE"                                          + LINE_SEPARATOR +
            "("                                                              + LINE_SEPARATOR +
            "  ID char(10) primary key,"                                     + LINE_SEPARATOR +
            "  NAME varchar(100) not null,"                                  + LINE_SEPARATOR +
            "  ADDRESS varchar(500) not null,"                               + LINE_SEPARATOR +
            "  DEPT_CD char(8) references DEPT(CODE),"                       + LINE_SEPARATOR +
            "  BIRTHDAY date,"                                               + LINE_SEPARATOR +
            "  EMP_TYPE char(1) references EMP_TYPE(CD) on delete cascade"   + LINE_SEPARATOR +
            ")"
            ;

        String sql = new CreateTable("EMPLOYEE")
            .column("ID").character(10).primaryKey()
            .column("NAME").varchar(100).notNull()
            .column("ADDRESS").notNull().varchar(500)
            .column("DEPT_CD").character(8).foreignConstraint("DEPT", "CODE")
            .column("BIRTHDAY").date()
            .column("EMP_TYPE").character(1).foreignConstraint("EMP_TYPE", "CD").onDeleteCascade()
            .toString();

        System.out.println(sql);
        Assert.assertEquals(RESULT, sql);
    }

}
