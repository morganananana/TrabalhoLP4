import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class Tabela {
	 public static void main(String[] args) {

	        Connection conn = null;
	        Statement stmt = null;
	        try {
	            conn =  ConexaoDB.getConexao();

	            //STEP 3: Executa a query
	            System.out.println("Criando tabela na Base de Dados...");


	            stmt = conn.createStatement();

	            String sql =  "CREATE TABLE   PESSOA " +
	                    " (id INTEGER not NULL, "
	                    + "nome VARCHAR(255),"
	                    + "sobrenome VARCHAR(255), "
	                    + "telefone VARCHAR(120), "
	                    + "email VARCHAR(150),"
	                    + "idade INTEGER, "
	                    + "PRIMARY KEY ( id ))";


	            stmt.executeUpdate(sql);


	            System.out.println("Tabela criada na Base de dados...");
	        } catch(SQLException se) {
	            //Handle errors for JDBC
	            se.printStackTrace();
	        } catch(Exception e) {
	            //Handle errors for Class.forName
	            e.printStackTrace();
	        } finally {
	            //finally block used to close resources
	            try{
	                if(stmt!=null) stmt.close();
	            } catch(SQLException se2) {
	            } // nothing we can do
	            try {
	                if(conn!=null) conn.close();
	            } catch(SQLException se){
	                se.printStackTrace();
	            } //end finally try
	        } //end try
	        System.out.println("Goodbye!");
	    }
}
