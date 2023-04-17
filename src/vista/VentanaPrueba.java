package vista;
import java.awt.EventQueue;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.net.URL;
import javax.swing.JOptionPane;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Image;
import java.awt.PageAttributes.MediaType;
import java.awt.event.ActionEvent;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import java.io.*;

import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JScrollPane;


public class VentanaPrueba extends JFrame {

	
	private JPanel contentPane;
	private JTextField textArchivo;
	private JTextField textDestino;
	private File archivoSeleccionado;
	private File archivoDestino;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaPrueba frame = new VentanaPrueba();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public VentanaPrueba() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 617, 322);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		JLabel lblNewLabel = new JLabel("Seleccione el archivo que desea copiar:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setBounds(42, 42, 270, 29);
		contentPane.add(lblNewLabel);
		
		JLabel lblArchivo = new JLabel("Archivo:");
		lblArchivo.setHorizontalAlignment(SwingConstants.RIGHT);
		lblArchivo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblArchivo.setBounds(23, 96, 64, 29);
		contentPane.add(lblArchivo);
		
		textArchivo = new JTextField();
		textArchivo.setFont(new Font("Tahoma", Font.PLAIN, 13));
		textArchivo.setBounds(97, 98, 374, 26);
		contentPane.add(textArchivo);
		textArchivo.setColumns(10);
		
		JLabel lblDestino = new JLabel("Destino:");
		lblDestino.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDestino.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblDestino.setBounds(23, 163, 64, 29);
		contentPane.add(lblDestino);
		
		textDestino = new JTextField();
		textDestino.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textDestino.setColumns(10);
		textDestino.setBounds(97, 165, 374, 26);
		contentPane.add(textDestino);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				dispose();
			}
		});
		btnCancelar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnCancelar.setBounds(319, 229, 116, 29);
		contentPane.add(btnCancelar);
		
		JButton btnExplorar = new JButton("Explorar");
		btnExplorar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				//SELECCIONAR VIDEO A INSERTAR
								
				int iSeleccion;
				
		        JFileChooser ExploradorArchivos = new JFileChooser();
		        FileNameExtensionFilter filtro = new FileNameExtensionFilter("Archivos de video", "mp4", "avi", "mov", "wmv", "flv","mkv");
		        
		        ExploradorArchivos.setFileFilter(filtro);
		        
		        
		        // Explorador de archivos para seleccionar un video
		        iSeleccion = ExploradorArchivos.showOpenDialog(null);
		        
		        // Si se selecciona un video, elegir donde guardarlo
		        if (iSeleccion == JFileChooser.APPROVE_OPTION) 
		        {
		        
		        		archivoSeleccionado = ExploradorArchivos.getSelectedFile();
			            System.out.println(archivoSeleccionado.getName());
		        }
		        
	        // Si el archivo que deseamos copiar se encuentra en formato .AVI o .avi, se recogerá la ruta del archivo sin más. En caso contrario 
	        // se abrirá el programa seleccionado abajo para convertir previamente el video a formato .avi
		        
		        if (archivoSeleccionado.getName().contains(".AVI") || archivoSeleccionado.getName().contains(".avi"))
	        	{
	            	textArchivo.setText(archivoSeleccionado.getName());
	        	}
	            else
	            {
	       
	            	try 
	            	{
	/**
	 * A continuación se muestra la ruta del programa necesario para la conversion y compresion de los videos (en nuestro caso Prism), que habra que descargar
	 * y cambiar la ruta abajo indicada por la ruta en la que tengamos nuestro .exe (Se establece a la hora de instalar el programa descargado.
	 * Este programa puede ser sustituido por cualquier otro apto para cambiar formato de video.
	 */
	            		
        		    String rutaApp = "C:\\Program Files (x86)\\NCH Software\\Prism\\Prism.exe";
        		    Runtime.getRuntime().exec(rutaApp);
	            	} 
	            	catch (IOException e2) 
	            	{
	            		e2.printStackTrace();
	            	}
	            }
			        
			}
		});
		btnExplorar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnExplorar.setBounds(488, 96, 93, 29);
		contentPane.add(btnExplorar);
		
		JButton btnDestino = new JButton("Destino");
		btnDestino.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				//GUARDADO DEL VIDEO SELECCIONADO
			       
		        JFileChooser ExploradorArchivos2 = new JFileChooser();
		        File RutaVideo;
		        int iSeleccionado;
		        String ArchivoCopiado;
		        
		        ArchivoCopiado = textArchivo.getText();
		        ArchivoCopiado = ArchivoCopiado.substring(0, ArchivoCopiado.length() - 4);
		        
		        // Seleccion de donde guardarlo
		        ExploradorArchivos2.setFileSelectionMode(JFileChooser.FILES_ONLY);
		        
		        FileNameExtensionFilter filtro2 = new FileNameExtensionFilter("Archivos de video", "mp4", "avi", "mov", "wmv", "flv", "mkv");
		        ExploradorArchivos2.setFileFilter(filtro2);
		        
		        //copiado del nombre del archivo anterior
		        File archivo = new File(ArchivoCopiado);
		        ExploradorArchivos2.setSelectedFile(archivo);
		        
		        // Explorador de archivos para guardar un archivo
		        iSeleccionado = ExploradorArchivos2.showSaveDialog(null);
		        
		        // Una vez pulsado el boton de guardar se recoge la ruta absoluta del video
		        if (iSeleccionado == JFileChooser.APPROVE_OPTION) 
		        {
		           archivoDestino = ExploradorArchivos2.getSelectedFile();
		           
		            System.out.println("Archivo guardado como: " + archivoDestino.getAbsolutePath());
		            textDestino.setText(archivoDestino.getAbsolutePath());
		        }
			}
		});
		btnDestino.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnDestino.setBounds(488, 163, 93, 29);
		contentPane.add(btnDestino);
		
		JButton btnGuardar = new JButton("Guardar");
		JProgressBar progressBar = new JProgressBar(0, 100);
		progressBar.setStringPainted(true);
		progressBar.setVisible(false);
		btnGuardar.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				String sExtension;
				String sDestino;
				String sNombreArchivoCompleto;
				
				if(textArchivo.getText().equals("") || textDestino.getText().equals(""))
				{
					JOptionPane.showMessageDialog(null, "Seleccione archivo y destino", "Error", JOptionPane.WARNING_MESSAGE);
				}
				else if (textDestino.getText().equals(""))
				{
					JOptionPane.showMessageDialog(null, "Seleccione destino del archivo", "Error", JOptionPane.WARNING_MESSAGE);
				}
				else
				{
					sDestino = textDestino.getText();
					sExtension = "." + archivoSeleccionado.getName().substring(archivoSeleccionado.getName().lastIndexOf(".") + 1);
					sNombreArchivoCompleto = sDestino + sExtension;

					System.out.println("La ruta completa del archivo es: " + sNombreArchivoCompleto);

				// Copiar el archivo seleccionado a la nueva ubicación
					try 
					{
						Path fuente = Paths.get(archivoSeleccionado.getAbsolutePath());
						Path destino = Paths.get(sNombreArchivoCompleto);
					      
					    BarraProgreso ventana = new BarraProgreso(fuente, archivoSeleccionado, sNombreArchivoCompleto);
					    ventana.setVisible(true);
					    
					    Files.copy(fuente, destino, StandardCopyOption.REPLACE_EXISTING);
					    System.out.println("Archivo copiado.");
					} 
					catch (IOException e2) 
					{
					    System.out.println("Error al copiar el archivo: " + e2.getMessage());
					} 
				
				}
			
				textArchivo.setText("");
				textDestino.setText("");
			  
			 }
		});
		btnGuardar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnGuardar.setBounds(148, 229, 116, 29);
		contentPane.add(btnGuardar);
		
		
	}
}
