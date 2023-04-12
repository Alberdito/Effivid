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
import javax.swing.JOptionPane;
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
import javax.swing.JButton;
import javax.swing.JTextPane;

public class VentanaUsuario extends JFrame {

	private JPanel contentPane;
	private JTextField txtRef;
private ConexionBD conexion;
	

	/**
	 * Create the frame.
	 */
	public VentanaUsuario() 
	{
		setResizable(false);
		
	this.conexion = new ConexionBD();
		
	//DEFINIR COMBOBOX PRODUCTO
			JComboBox cbProducto = new JComboBox();
			cbProducto.setFont(new Font("Tahoma", Font.PLAIN, 13));
			cbProducto.setBounds(134, 192, 322, 31);
			
			
			ArrayList<Producto> lista = new ArrayList<Producto>();
			
			ProductoDAO productoDAO = new ProductoDAO();
			lista = productoDAO.obtenerDenominacion();
					
			cbProducto.removeAllItems();
			cbProducto.addItem("- Seleccione un producto -" );
			
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
			cbModelo.setBounds(134, 254, 322, 31);
			
			cbModelo.addItem("- Seleccione el modelo -" );
			
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
		setBounds(100, 100, 531, 436);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
	
		txtRef = new JTextField();
		txtRef.setToolTipText("Inserte el número de referencia del producto sobre el que desee ver el vídeo");
		txtRef.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtRef.setBounds(134, 104, 322, 31);
		contentPane.add(txtRef);
		txtRef.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Nº Referencia:");
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel.setBounds(10, 109, 94, 20);
		contentPane.add(lblNewLabel);
		
		JLabel lblO = new JLabel("________________________________________________________________");
		lblO.setHorizontalAlignment(SwingConstants.RIGHT);
		lblO.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblO.setBounds(25, 145, 461, 20);
		contentPane.add(lblO);
		
		JLabel lblProducto = new JLabel("Producto:");
		lblProducto.setHorizontalAlignment(SwingConstants.RIGHT);
		lblProducto.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblProducto.setBounds(33, 197, 71, 20);
		contentPane.add(lblProducto);
		
		JLabel lblModelo = new JLabel("Modelo:");
		lblModelo.setHorizontalAlignment(SwingConstants.RIGHT);
		lblModelo.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblModelo.setBounds(33, 259, 71, 20);
		contentPane.add(lblModelo);
		
		contentPane.add(cbProducto);
		contentPane.add(cbModelo);
		
		JLabel lblNewLabel_1 = new JLabel("Elija una de las dos opciones:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_1.setBounds(25, 39, 358, 31);
		contentPane.add(lblNewLabel_1);
		
		JButton btnVer = new JButton("Ver");
		btnVer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				String iRef;
				String sProducto;
				String sModelo;
				
				iRef = txtRef.getText();
				sProducto = cbProducto.getSelectedItem().toString();
				sModelo = cbModelo.getSelectedItem().toString();
				
				System.out.println(iRef);
				System.out.println(sProducto);
				System.out.println(sModelo);
				
				if (txtRef.equals("") || sProducto.equals("- Seleccione un producto -") || sModelo.equals("- Seleccione el modelo -"))
				{
					JOptionPane.showMessageDialog(null,"Rellene alguna de las dos opciones:", "Error", JOptionPane.WARNING_MESSAGE);
				}
				
				
			}
		});
		btnVer.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnVer.setBounds(106, 336, 131, 31);
		contentPane.add(btnVer);
		
		JButton btnCerrar = new JButton("Cerrar");
		btnCerrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				dispose();
			}
		});
		btnCerrar.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnCerrar.setBounds(311, 336, 131, 31);
		contentPane.add(btnCerrar);
		
		
	}
}
