package br.com.marcogorak.bd;

/**
 *
 * @author Marco Gorak
 */

import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Banco {

    private static Connection con = null;
    
public void conectar() {
     
    System.out.println("Conectando Banco...");
        try {
            //Classe do Driver de conexão com o Banco
            Class.forName("com.mysql.jdbc.Driver");
            
            con = DriverManager.getConnection("jdbc:mysql://testeback2017.mysql.dbaas.com.br/testeback2017", "testeback2017", "teste1020");
             System.out.println("Banco conectado com sucesso");
        } catch (ClassNotFoundException ex) {
            System.out.println("Driver não encontrado!");
            //Logger.getLogger(Banco.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            System.out.println("Banco não encontrado!");
            //Logger.getLogger(Banco.class.getName()).log(Level.SEVERE, null, ex);
        }
}   

public void cadastro (int id, String cpf, String nome, boolean status, int total) {
    
	conectar();
	
    //String query = "INSERT INTO tb_customer_account VALUES ('"+id+"','"+cpf+"','"+nome+"','"+status+"','"+total+"')";
    
    String query = "INSERT INTO tb_customer_account (id_custumer, cpf_cnpj, nm_customer, is_active, vl_total) VALUES (?,?,?,?,?)";
        try {
            // Gerando um objeto PreparedStatement passando a query como parametro
            PreparedStatement st = con.prepareStatement(query);
            
            st.setInt(1, id);
            st.setString(2, cpf);
            st.setString(3, nome);
            st.setBoolean(4, status);
            st.setInt(5, total);
            
            st.execute();
            
            //Fechando conexão com o Banco de dados
            st.close();
            con.close();
            
        } catch (SQLException ex) {
            System.out.println("Comando SQL inválido: " + query);
            Logger.getLogger(Banco.class.getName()).log(Level.SEVERE, null, ex);
        }
}

//Query de Consulta
public void consulta() throws SQLException {  
		
	conectar();

    	int media=0;
    	int i=1;
        String query = "SELECT id_custumer, cpf_cnpj, nm_customer, is_active, vl_total FROM tb_customer_account WHERE (id_custumer > 1499 AND id_custumer < 2701) AND vl_total > 559 ORDER BY vl_total DESC";
        ArrayList result = new ArrayList(); 
        
         try {
            // Gerando um objeto PreparedStatement passando a query como parametro
            PreparedStatement st = con.prepareStatement(query);
            ResultSet rs = st.executeQuery();
            
            while (rs.next()) {  
                String txt_id = rs.getString("id_custumer");  
                String txt_cpf= rs.getString("cpf_cnpj");
                String txt_customer = rs.getString("nm_customer");
                boolean txt_active = rs.getBoolean("is_active");
                int txt_valor = rs.getInt("vl_total");
                
                
                String total = "ID: "+ txt_id + " CPF: " + txt_cpf + " Nome: " + txt_customer +" Status: "+ txt_active +" Saldo: "+ txt_valor +"\n";
                
                System.out.println(total);
                
                
                media = (media+txt_valor)/i;
             i++;   
            } 
            System.out.println("A média de Saldo dos clientes selecionados é "+ media);
            
             st.close();
            con.close(); 
            
            
        } catch (SQLException ex) {
            System.out.println("Comando SQL inválido: " + query);
            Logger.getLogger(Banco.class.getName()).log(Level.SEVERE, null, ex);
        }
}

}
            
 
/*
 * CREATE TABLE `testeback2017`.`tb_customer_account` ( `id_custumer` BIGINT NOT NULL , `cpf_cnpj` VARCHAR(25) NOT NULL , `nm_customer` VARCHAR(200) NOT NULL , `is_active` BOOLEAN NOT NULL , `vl_total` REAL NOT NULL ) ENGINE = InnoDB;
 */
     








