package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import conexion.ConexionBD;

import modelo.Producto;

public class ProductoDAO 
{
private ConexionBD conexion;
	
	public ProductoDAO()
	{
		this.conexion = new ConexionBD();
	}
	//*******************************************************************************************************
		//FUNCIÓN MOSTRAR
		public ArrayList <Producto> obtenerProductos()
		{
			//Obtenemos conexion a la base de datos.
			Connection con = conexion.getConexion();
			Statement consulta = null;
			ResultSet resultado = null;
			ArrayList<Producto> lista = new ArrayList<Producto>();
			String sSQL;
			
			try
			{
				sSQL = "SELECT * FROM productos";
				consulta = con.createStatement();
				resultado = consulta.executeQuery(sSQL);
				
				//Bucle para recorrer las filas que devuelve la consulta
				while(resultado.next())
				{
					int iRef = resultado.getInt("Ref");
					String sDescripcion = resultado.getString("descripcion");
					String sDenominacion = resultado.getString("denominacion");
					String sModelo = resultado.getString("modelo");
					int iPersonas = resultado.getInt("personas");
					
					Producto prod = new Producto(iRef, sDescripcion, sDenominacion, sModelo, iPersonas);
					lista.add(prod);
				}
			}
			catch (SQLException e)
			{
				System.out.println("Error al realizar la consulta: " + e.getMessage());
			}
			finally
			{
				try
				{
					resultado.close();
					consulta.close();
					conexion.desconectar();
				}
				catch (SQLException e)
				{
					System.out.println("Error al liberar recursos: " + e.getMessage());
				}
				
			}
			return lista;	
		}
}
