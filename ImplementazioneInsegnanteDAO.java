/*
 * 
 * 
 * 
 * vedere le flag di ritorno se ritornano scambiando true e false
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 */









package ImplementazioneDAO;

import java.beans.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Connessione.ConnessioneDatabase;
import java.util.ArrayList;

import javax.swing.JTextField;

import DAO.InsegnanteDAO;
import Model.Insegnante;
import Model.Studente;
import Model.Test;
import Model.Test;

public class ImplementazioneInsegnanteDAO implements InsegnanteDAO {
	private Connection conn;
	
	public ImplementazioneInsegnanteDAO () {
		try {
			this.conn = ConnessioneDatabase.getInstance().getConnection();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	 
	
	/*@Override
	public ArrayList<Test> TestDaCorreggere(Insegnante ins) {
		try {
			Test t;
			Studente s;
			ArrayList<Test> rit=new ArrayList<Test>();
			PreparedStatement query=this.connection.prepareStatement("SELECT t.nome, t.materia, t.durata, t.data , s.studente, s.data FROM procida.svolgimento as s , procida.test as t WHERE s.test=t.nome AND corretto=false AND test IN(SELECT test FROM procida.creazione WHERE insegnante='" +ins.getlogin() +"')");
			ResultSet res=query.executeQuery();
			while(res.next()) {
				t=new Test(res.getString("nome"), res.getDate("data"), res.getString("materia"), res.getInt("durata"));
				s=new Studente(res.getString("studente"));
				rit.add(new Test(t, s, res.getDate(6)));
			}
			return rit;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}*/

	@Override
	public boolean Registrazione(Insegnante ins) {
		// execute funziona al contrario per queso flag=true
		boolean esito = false;
		try {
			PreparedStatement query = this.conn.prepareStatement("INSERT INTO public.INSEGNANTE VALUES( '"+ins.getLogin()+"','"+ins.getNome()+"','"+ins.getCognome()+"','"+ins.getPassword()+"','"+ ins.getCorso()+"')");
			esito = query.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return esito;

	}
	
	public void Login(Insegnante ins) {
		try {
			PreparedStatement query = this.conn.prepareStatement("Select * From Insegnante Where Login = '"+ ins.getLogin() +"' AND Password = '"+ ins.getPassword()+"'");
			ResultSet set = query.executeQuery();
			if (set.next()) {
				ins.setNome(set.getString("nome"));
				ins.setCognome(set.getString("cognome"));
				ins.setCorso(set.getString("corso"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public boolean LoginB(Insegnante ins) {
		boolean esito = false;
		try {
			PreparedStatement query= this.conn.prepareStatement("SELECT * FROM insegnante WHERE login='"+ins.getLogin()+"' AND password='"+ins.getPassword()+"'");
			ResultSet res=query.executeQuery();
			if(res.next()) {
				esito = true;
			}else {
				esito = false;
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return esito;
	}

	/*@Override
	public boolean SavePassword(String pass, Insegnante ins) {
		// TODO Auto-generated method stub
		boolean flag=true;
		try {
			PreparedStatement query=this.connection.prepareStatement("UPDATE procida.insegnante SET password='"+pass+"' WHERE login='"+ins.getLogin()+"'");
			flag=query.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(!flag) {
			return true;  
		}else {
			return false;
		}
	}*/

	/*@Override
	public boolean NomeCognome(Insegnante ins) {
		// TODO Auto-generated method stub
		boolean flag=false;
		try {
			PreparedStatement query=this.connection.prepareStatement("SELECT nome , cognome FROM procida.insegnante WHERE login='"+ins.getLogin()+"'");
			ResultSet res=query.executeQuery();
			if(res.next()) {
				flag=true;
				ins.setNome(res.getString("nome"));
				ins.setCognome(res.getString("cognome"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return flag;
	}*/

	/*@Override
	public boolean validAut(Insegnante ins) {
		// TODO Auto-generated method stub
		return false;
	}*/

	/*@Override
	public String logUser(String userInput, String password) {
		// TODO Auto-generated method stub
		return null;
	}*/

	/*@Override
	public boolean validAut (Insegnante ins) {
		// TODO Auto-generated method stub
		boolean flag=false;
		try {
			PreparedStatement query=this.connection.prepareStatement("SELECT risposta FROM procida.insegnante WHERE login='"+ins.getLogin()+"'");
			ResultSet res=query.executeQuery();
			if(res.next()) {
				if(ins.getAut().equals(res.getString("risposta"))) {
					flag=true;
				};
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return flag;
	}*/
	/*@Override
	public ArrayList<Test> TestDaPoterEliminare(Insegnante ins) {
		// TODO Auto-generated method stub
		try {
			ArrayList<Test> test=new ArrayList<Test>();
			PreparedStatement query=this.connection.prepareStatement("SELECT nome, materia, durata, data FROM procida.creazione as c, procida.test WHERE c.test=nome AND c.insegnante='"+ins.getLogin()+"' AND NOT EXISTS(SELECT* FROM procida.svolgimento WHERE test=c.test)");
			ResultSet res=query.executeQuery();
			while(res.next()) {
				test.add(new Test(res.getString("nome"),res.getDate("data"),res.getString("materia"), res.getInt("durata")));
			}
			return test;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}*/
	
	
	/*@Override
	public boolean EliminaTestDiretto(Insegnante ins, Test t) {
		// TODO Auto-generated method stub
		boolean flag=false;
		try {
			PreparedStatement query = this.connection.prepareStatement("SELECT test FROM procida.creazione WHERE insegnante='"+ins.getLogin()+"' AND test='"+t.getNome()+"'");
			if(query.execute()) {
				boolean flag2=true;
				ImplementazioneTestDAO imp=new ImplementazioneTestDAO();
				query=this.connection.prepareStatement("DELETE FROM procida.creazione WHERE test='"+t.getNome()+"'");
				flag2=query.execute();
				if(!flag2) {
					flag=imp.EliminaTest(t);
				}else return flag;
			};
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return flag;
	}*/

}