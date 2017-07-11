package br.com.marcogorak;
import java.sql.SQLException;
import java.util.Scanner;

import br.com.marcogorak.bd.*;


/**
*
* @author Marco Gorak
*/


public class Main {

	public static void main(String[] args) throws SQLException {
		
		Scanner scan = new Scanner(System.in);
		
		//Criando o objeto de conexão com o banco
        Banco bank = new Banco();
        
        
       /** bank.cadastro(1500, "400.000.000-20", "João da Silva", true, 15000);
        bank.cadastro(1560, "300.300.200-20", "Paulo Henrique", true, 1400);
        bank.cadastro(1600, "123.456.789-10", "Marco Aurélio", true, 1300);
        bank.cadastro(3000, "098.765.432-10", "Brian Ashyron", true, 3000);
        bank.cadastro(2000, "450.333.222-20", "Malt Laua", true, 400);
        bank.cadastro(2222, "400.000.000-20", "Tyraell", true, 16000);*/
        
        bank.consulta();
	}

}
