package vista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import conexion.ConexionBD;
import dao.PersonaDAO;
import dao.ProductoDAO;
import modelo.Persona;
import modelo.Producto;

import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class VentanaUsuario extends JFrame {

	private JPanel contentPane;
	private JTextField textRefer;
private ConexionBD conexion;
	

	/**
	 * Create the frame.
	 */
	public VentanaUsuario() 
	{
		
	this.conexion = new ConexionBD();
		
	//DEFINIR COMBOBOX PRODUCTO
			JComboBox cbProducto = new JComboBox();
			cbProducto.setFont(new Font("Tahoma", Font.PLAIN, 13));
			cbProducto.setBounds(121, 223, 322, 31);
			
			
			ArrayList<Producto> lista = new ArrayList<Producto>();
			
			ProductoDAO productoDAO = new ProductoDAO();
			lista = productoDAO.obtenerDenominacion();
					
			cbProducto.removeAllItems();
					
			for(Producto producto:lista)
			{
				String denominacion = producto.getDenominacion();
				cbProducto.addItem(denominacion);
			}
			cbProducto.setSelectedIndex(0);
			cbProducto.repaint();
			
		//DEFINIR COMBOBOX MODELO
			JComboBox cbModelo = new JComboBox();
			cbModelo.setFont(new Font("Tahoma", Font.PLAIN, 13));
			cbModelo.setBounds(121, 298, 322, 31);
			
		//ASOCIAR ACTIONLISTENER AL COMBOBOX PRODUCTO
			cbProducto.addActionListener(new ActionListener() 
			{
				public void actionPerformed(ActionEvent e) 
				{
					String sDenominacion = cbProducto.getSelectedItem().toString();
								
		//VACIAR Y RELLENAR COMBOBOX MODELO
				cbModelo.removeAllItems();
				ArrayList<Producto> lista2 = new ArrayList<Producto>();
				ProductoDAO producDAO = new ProductoDAO();
				lista2 = producDAO.obtenerProductos(sDenominacion);
							
				for(Producto produc:lista2)
				{
					String descripcion = produc.getDescripcion();
					cbModelo.addItem(descripcion);
				}
				cbModelo.setSelectedIndex(0);
				cbModelo.repaint();
			}
			});
		
		
				
		
				
		
		
	
		
	
		
		setTitle("Visualización de videos");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 478, 563);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
	
		textRefer = new JTextField();
		textRefer.setToolTipText("Inserte el número de referencia del producto sobre el que buscar vídeo");
		textRefer.setFont(new Font("Tahoma", Font.PLAIN, 13));
		textRefer.setBounds(166, 104, 204, 31);
		contentPane.add(textRefer);
		textRefer.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Nº Referencia:");
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel.setBounds(42, 109, 103, 20);
		contentPane.add(lblNewLabel);
		
		JLabel lblO = new JLabel("O:");
		lblO.setHorizontalAlignment(SwingConstants.RIGHT);
		lblO.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblO.setBounds(121, 166, 103, 20);
		contentPane.add(lblO);
		
		JLabel lblProducto = new JLabel("Producto:");
		lblProducto.setHorizontalAlignment(SwingConstants.RIGHT);
		lblProducto.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblProducto.setBounds(40, 228, 71, 20);
		contentPane.add(lblProducto);
		
		JLabel lblModelo = new JLabel("Modelo:");
		lblModelo.setHorizontalAlignment(SwingConstants.RIGHT);
		lblModelo.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblModelo.setBounds(40, 303, 71, 20);
		contentPane.add(lblModelo);
		
		contentPane.add(cbProducto);
		contentPane.add(cbModelo);
	}
	
	
}
