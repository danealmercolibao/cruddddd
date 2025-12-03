package colibaocrud;

import java.sql.Connection;

public class ColibaoCRUD {

    public static void main(String[] args) {
          Connection conn = SqlConnection.getConnection();
          boolean isVisible = true;
          loginpage lp = new loginpage();
          crudpage cp = new crudpage();
          lp.setLocationRelativeTo(null);
       
          lp.setVisible(isVisible);
    }
    
}
