package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
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
		//FUNCIÓN MOSTRAR DENOMINACIÓN DEL PRODUCTO
		public ArrayList <Producto> obtenerDenominacion()
		{
			//Obtenemos conexion a la base de datos.
			Connection con = conexion.getConexion();
			Statement consulta = null;
			ResultSet resultado = null;
			ArrayList<Producto> lista = new ArrayList<Producto>();
			String sSQL;
			
			try
			{
				sSQL = "SELECT DISTINCT denominacion FROM productos ORDER BY denominacion;";
				consulta = con.prepareStatement(sSQL);
				resultado = consulta.executeQuery(sSQL);
				
				//Bucle para recorrer las filas que devuelve la consulta
				while(resultado.next())
				{
					String sDenominacion = resultado.getString("denominacion");
				
					Producto prod = new Producto(0, sDenominacion, sDenominacion, sDenominacion, 0);
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
		
		// ***********************************************************************************************************************
		
		//FUNCIÓN MOSTRAR DENOMINACIÓN DEL PRODUCTO
			public ArrayList <Producto> obtenerProductos(String denominacion)
			{
				//Obtenemos conexion a la base de datos.
				Connection con = conexion.getConexion();
				PreparedStatement pstmt = null;
				ResultSet rs2 = null;
				ArrayList<Producto> lista2 = new ArrayList<Producto>();
				String sSQL2;
				
				try
				{
					sSQL2 = "SELECT descripcion FROM productos WHERE denominacion = ? ORDER BY descripcion ASC;";
					pstmt = con.prepareStatement(sSQL2);
					pstmt.setString(1, denominacion);
					rs2 = pstmt.executeQuery();
					
					//Bucle para recorrer las filas que devuelve la consulta
					while(rs2.next())
					{
						String sDescripcion = rs2.getString("descripcion");
						
						Producto produc = new Producto(0, sDescripcion, sDescripcion, sDescripcion, 0);
						lista2.add(produc);
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
						 if (rs2 != null) 
						 {
					            rs2.close();
						 }
						pstmt.close();
						conexion.desconectar();
					}
					catch (SQLException e)
					{
						System.out.println("Error al liberar recursos: " + e.getMessage());
					}
					
				}
				return lista2;	
			}
}
