package Servicios;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.HeadlessException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.Spring;

import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import javax.swing.JButton;
import java.sql.*;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

import conexion.bd;
import clientes.clientes;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.JTextComponent;

import java.awt.Color;
import javax.swing.JMenuBar;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.FlowLayout;
import com.toedter.calendar.JCalendar;
import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JTextFieldDateEditor;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.UIManager;
import javax.swing.JInternalFrame;
import javax.swing.JToolBar;
import javax.swing.ScrollPaneConstants;

@SuppressWarnings({ "serial", "unused" })
public class ordservicio extends JFrame {
	//Declaramos las variables como las etiquetas y las cajas de texto

	private JPanel contentPane;
	private JTextField txtNombre;
	private JTextField txtPaterno;
	private JTextField txtMaterno;
	
	/**
	 * Launch the application.
	 */
	//Declaramos las variables de la conexion	
	Connection conn;
	Statement sent;
	
	private JTable table;
	private JTextField txtdireccion;
	private JTextField txtrfc;
	private JTextField txtnumcliente;
	private JTextField txttelefono;
	private JTextField txtcelular;
	private JTextField txttecnico;
	private JTextField txtObservaciones;
	private JTextField txtcosto;
	public JLabel validacioncamp;
	public DefaultTableModel modeltable;
	public JComboBox<String> cmbtiposerv;
	public JComboBox<String> cmbplaga;
	public JComboBox<String> cmbhoras;
	public JComboBox<String> cmbmedio;
	public JComboBox<String> cmbformas;
	@SuppressWarnings("rawtypes")
	public JComboBox cmbBusqueda;
	public JButton btnBuscarNumero;
	public JLabel lblNombre;
	public JLabel lblEmpresa;
	public JLabel lblApellidoPaterno;
	public JLabel lblApellidoMaterno;
	public JLabel lblNumero;
	public JButton btnBuscarPersona;
	public JButton btnBuscarEmpresa;
	public JPanel scrollPane;
	public JLabel lblDireccion;
	public JLabel lblRFC;
	public JLabel lblNumeroDeCliente;
	public JTextField txtelefono;
	public JLabel lblTelefono;
	public JLabel lblCelular;
	public JLabel lblObservaciones;
	public JLabel lblmedio;
	public JLabel lblcosto;
	public JButton btnGuardar;
	public JButton btnOrdenServicio;
	public JDateChooser escogerfechas;
	public JButton BuscarSS;
	public JLabel lblHora;
	public JLabel lblFormaDePago;
	public JComboBox<String> cmbforma;
	public JLabel lblPlagaEnEl;
	public JLabel tbtTecnico;
	public JLabel lblFecha;
	public JDateChooser dateChooser;
	public Integer bandera;
	public JPanel panel;
	public JComboBox<String> cmbBusquedaAct;
	private JTextField textField;
	private JTextField textField_2;
	private JTextField txtciudad;
	private JTextField txtnombreapellidos;
	private JTextField txtempresa;
	private JTextField textField_4;
	//TERMINA LAS DECALRACIONES DE LOS COMPONENTES
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ordservicio frame = new ordservicio();
					frame.setVisible(true);
					JMenuBar jmb= new JMenuBar();
					frame.setJMenuBar(jmb);					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ordservicio() {
		conn= bd.getConnect();
		bandera=1;
		setTitle("Killers-Servicios");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1004, 640);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);			
		final JComboBox<String> cmbBusqueda = new JComboBox<String>();
		cmbBusqueda.setBounds(21, 39, 133, 20);
		contentPane.add(cmbBusqueda);
		final JComboBox<String> cmbBusquedaAct = new JComboBox<String>();
		cmbBusquedaAct.setBounds(21, 39, 133, 20);
		contentPane.add(cmbBusquedaAct);
		final JLabel validacioncamp = new JLabel("");
		validacioncamp.setForeground(Color.RED);
		validacioncamp.setBounds(443, 45, 207, 14);
		contentPane.add(validacioncamp);
		//CREACION DE ELEMENTOS
		final JLabel lblNombre = new JLabel("Nombre: ");
		final JLabel lblEmpresa = new JLabel("Empresa:");
		final JLabel lblApellidoPaterno = new JLabel("Apellido Paterno:");
		final JLabel lblApellidoMaterno = new JLabel("Apellido Materno:");
		final JLabel lblNumero = new JLabel("Num Cliente");
		final JComboBox<String> cmbtiposerv = new JComboBox<String>();
		final JComboBox<String> cmbplaga = new JComboBox<String>();
		final JButton btnBuscarEmpresa = new JButton("Buscar");
		txtMaterno = new JTextField();
		final JButton btnBuscarNumero = new JButton("Buscar");
		table = new JTable();
		final JScrollPane scrollPane = new JScrollPane();
		final JButton btnBuscarPersona = new JButton("Buscar");
		final JPanel datoscliente = new JPanel();
		datoscliente.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Datos del Cliente", TitledBorder.LEADING, TitledBorder.TOP, null, Color.RED));
		JLabel lblDireccion = new JLabel("Direccion:");
		JLabel lblRFC = new JLabel("RFC:");
		lblRFC.setBounds(10, 112, 44, 14);
		datoscliente.add(lblRFC);	
		final JPanel datos_ss = new JPanel();
		txtdireccion = new JTextField();
		txtdireccion.setForeground(Color.BLACK);
		txtdireccion.setBackground(Color.WHITE);
		txtdireccion.setBounds(75, 137, 316, 20);
		datoscliente.add(txtdireccion);
		txtdireccion.setColumns(10);	
		txtrfc = new JTextField();
		txtrfc.setForeground(Color.BLACK);
		txtrfc.setBackground(Color.WHITE);
		txtrfc.setBounds(43, 109, 124, 20);
		datoscliente.add(txtrfc);
		txtrfc.setColumns(10);
		txtnumcliente = new JTextField();
		txtnumcliente.setForeground(Color.BLACK);
		txtnumcliente.setBackground(Color.WHITE);
		txtnumcliente.setColumns(10);
		txtnumcliente.setBounds(122, 21, 124, 20);
		datoscliente.add(txtnumcliente);
		txttelefono = new JTextField();
		txttelefono.setForeground(Color.BLACK);
		txttelefono.setBackground(Color.WHITE);
		txttelefono.setColumns(10);
		txttelefono.setBounds(79, 168, 124, 20);
		datoscliente.add(txttelefono);		
		JLabel lblTelefono = new JLabel("Telefono:");
		lblTelefono.setBounds(10, 171, 58, 14);
		datoscliente.add(lblTelefono);
		JLabel lblCelular = new JLabel("Celular:");
		lblCelular.setBounds(10, 202, 58, 14);
		datoscliente.add(lblCelular);
		txtcelular = new JTextField();
		txtcelular.setForeground(Color.BLACK);
		txtcelular.setBackground(Color.WHITE);
		txtcelular.setColumns(10);
		txtcelular.setBounds(79, 199, 124, 20);
		datoscliente.add(txtcelular);
		JLabel lblTipoDeServicio = new JLabel("Tipo de Servicio:");
		lblTipoDeServicio.setBounds(10, 21, 101, 14);
		datos_ss.add(lblTipoDeServicio);		
		JLabel lblFormaDePago = new JLabel("Forma de Pago:");
		lblFormaDePago.setBounds(274, 87, 93, 14);
		datos_ss.add(lblFormaDePago);
		JLabel lblPlagaEnEl = new JLabel("Plaga en el Area:");
		lblPlagaEnEl.setBounds(10, 57, 101, 14);
		datos_ss.add(lblPlagaEnEl);
		JLabel tbtTecnico = new JLabel("Tecnico:");
		tbtTecnico.setBounds(276, 54, 55, 14);
		datos_ss.add(tbtTecnico);
		txttecnico = new JTextField();
		txttecnico.setBackground(new Color(255, 255, 255));
		txttecnico.setBounds(340, 51, 195, 20);
		datos_ss.add(txttecnico);
		txttecnico.setColumns(10);
		JLabel lblFecha = new JLabel("Fecha de Servicio:");
		lblFecha.setBounds(274, 124, 93, 14);
		datos_ss.add(lblFecha);
		final JDateChooser dateChooser = new JDateChooser();
		dateChooser.setDateFormatString("dd/MM/yyyy");
		dateChooser.setBounds(386, 118, 107, 20);
		datos_ss.add(dateChooser);
		JLabel lblObservaciones_1 = new JLabel("Observaciones:");
		lblObservaciones_1.setBounds(10, 165, 115, 14);
		datos_ss.add(lblObservaciones_1);
		txtObservaciones = new JTextField();
		txtObservaciones.setBackground(new Color(255, 255, 255));
		txtObservaciones.setBounds(109, 162, 426, 20);
		datos_ss.add(txtObservaciones);
		txtObservaciones.setColumns(10);
		JLabel lblmedio_1 = new JLabel("Tecnicas:");
		lblmedio_1.setBounds(10, 88, 101, 14);
		datos_ss.add(lblmedio_1);
		final JComboBox<String> cmbtecnicas = new JComboBox<String>();
		cmbtecnicas.setBounds(109, 82, 145, 20);
		datos_ss.add(cmbtecnicas);
		JLabel lblcosto_1 = new JLabel("Costo:");
		lblcosto_1.setBounds(302, 18, 46, 14);
		datos_ss.add(lblcosto_1);
		txtcosto = new JTextField();
		txtcosto.setBackground(new Color(255, 255, 255));
		txtcosto.setBounds(358, 15, 93, 20);
		datos_ss.add(txtcosto);
		txtcosto.setColumns(10);
		final JButton btnAct = new JButton("Actualizar");
		final JPanel Servicios = new JPanel();
		final JDateChooser escogerfechas = new JDateChooser();
		final JButton btnBuscarfecha = new JButton("Buscar");
		//FINAL DE LA DECLARACION DE ELEMENTOS//
		
		btnBuscarNumero.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
		
				try{
					Clean();
					Gray();
					 if(txtNombre.getText().isEmpty()) {
						 validacioncamp.setText("Introduce el Numero del Cliente");
						 txtNombre.setBorder(BorderFactory.createLineBorder(Color.RED,1));
						 txtNombre.requestFocus();
					 } else {
						 if(bandera==1){
						String[]titulos= {"Num Cliente","Nombre","Empresa","Direccion","Referencia","RFC","Telefono","Celular","E-Mail","Tipo Cliente"};
						modeltable= new DefaultTableModel(null, titulos);
				
						String consulta="SELECT * FROM clientes WHERE num_cliente='"+txtNombre.getText()+"'";
						sent=conn.createStatement();
						String[] regreso= new String[10];
						ResultSet rs= sent.executeQuery(consulta);
						while (rs.next()){
							regreso[0]=rs.getString("num_cliente");
							regreso[1]=rs.getString("nombre");
							regreso[2]=rs.getString("empresa");
							regreso[3]=rs.getString("direccion");
							regreso[4]=rs.getString("referencia");
							regreso[5]=rs.getString("rfc");
							regreso[6]=rs.getString("telefono");
							regreso[7]=rs.getString("celular");
							regreso[8]=rs.getString("email");
							regreso[9]=rs.getString("tipocliente");
							modeltable.addRow(regreso); 
						}
						scrollPane.setVisible(true);
						table.setVisible(true);
						txttecnico.setEnabled(false);
						txtObservaciones.setEnabled(false);
						table.setModel(modeltable);
		
						 } else if(bandera==2){
							 String[]titulos= {"Numero SS","num Cliente","Empresa" ,"Tipo Servicio","Horas","Forma Pago","Telefono","Costo","Fecha Solicud","Tipo Cliente"};
								modeltable= new DefaultTableModel(null, titulos);
								String consulta="SELECT * FROM clientes INNER JOIN servicio on clientes.num_cliente=servicio.num_cliente AND clientes.num_cliente='"+txtNombre.getText()+"' AND servicio.valido='0'" ;
								sent=conn.createStatement();
								String[] regreso= new String[10];
								ResultSet rs= sent.executeQuery(consulta);
								while (rs.next()){
									regreso[0]=rs.getString("num_ss");
									regreso[1]=rs.getString("num_cliente");
									regreso[1]=rs.getString("nombre");
									regreso[2]=rs.getString("empresa");
									regreso[3]=rs.getString("tiposervicio");
									regreso[4]=rs.getString("horas");
									regreso[5]=rs.getString("formapago");
									regreso[6]=rs.getString("telefono");
									regreso[7]=rs.getString("costo");
									regreso[8]=rs.getString("fecha");
									regreso[9]=rs.getString("tipocliente");
									modeltable.addRow(regreso);
								}
								scrollPane.setVisible(true);
								table.setVisible(true);
								table.setModel(modeltable);
								txttecnico.setEnabled(false);
								txtObservaciones.setEnabled(false);						
						 }				
					 } 
						} catch(Exception e){
							JOptionPane.showMessageDialog(null, "Error: "+e.getMessage());
						}
			}
		});
		btnBuscarNumero.setBounds(229, 63, 89, 23);
		contentPane.add(btnBuscarNumero);
		lblNombre.setBounds(10, 67, 55, 14);
		contentPane.add(lblNombre);
		lblEmpresa.setBounds(10, 68, 55, 14);
		contentPane.add(lblEmpresa);
		txtNombre = new JTextField();
		txtNombre.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent evt) {
			}
			@Override
			public void keyReleased(KeyEvent arg0) {
				txtNombre.setText(txtNombre.getText().toUpperCase());
			}
		});
		txtNombre.setBounds(86, 64, 133, 20);
		contentPane.add(txtNombre);
		txtNombre.setColumns(10);		
		lblApellidoPaterno.setBounds(244, 68, 97, 14);
		contentPane.add(lblApellidoPaterno);
		txtPaterno = new JTextField();
		txtPaterno.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				txtPaterno.setText(txtPaterno.getText().toUpperCase());
			}
			@Override
			public void keyTyped(KeyEvent evt) {
				char c=evt.getKeyChar();
				if(c<'a'|| c>'z' || c<'A' ||c>'z') evt.consume();
			}
		});
		txtPaterno.setColumns(10);
		txtPaterno.setBounds(351, 63, 133, 20);
		contentPane.add(txtPaterno);
		
		lblApellidoMaterno.setBounds(494, 67, 115, 14);
		contentPane.add(lblApellidoMaterno);		
		txtMaterno.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent evt) {
				char c=evt.getKeyChar();
				if(c<'a'|| c>'z' || c<'A' ||c>'z') evt.consume();
			}
			@Override
			public void keyReleased(KeyEvent e) {
			txtMaterno.setText(txtMaterno.getText().toUpperCase());
			}
		});
		txtMaterno.setColumns(10);
		txtMaterno.setBounds(605, 63, 143, 20);
		contentPane.add(txtMaterno);
		
		lblNumero.setBounds(10, 71, 76, 14);
		contentPane.add(lblNumero);
		
		datos_ss.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Datos Solicitud de Servicio", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(255, 0, 0)));
		datos_ss.setBounds(432, 169, 547, 262);
		contentPane.add(datos_ss);
		datos_ss.setLayout(null);
		cmbtiposerv.setBounds(107, 18, 120, 20);
		datos_ss.add(cmbtiposerv);
		cmbtiposerv.addItem("CORRECTIVO");
		cmbtiposerv.addItem("PREVENTIVO");
		cmbtiposerv.addItem("TRATAMIENTO");
		
		cmbplaga.setBounds(109, 54, 120, 20);
		datos_ss.add(cmbplaga);
		
		cmbplaga.addItem("RASTREROS");
		cmbplaga.addItem("ALEMAÑAS");
		cmbplaga.addItem("TERMITAS");
		cmbplaga.addItem("ROEDORES");
		cmbplaga.addItem("ALACRANES");
		cmbplaga.addItem("ARAÑAS");
		cmbplaga.addItem("HORMIGAS");
		cmbplaga.addItem("GARRAPATAS");
		cmbplaga.addItem("PULGAS");
		cmbplaga.addItem("VOLADORES");
		
		cmbtecnicas.addItem("NINGUNO");
		cmbtecnicas.addItem("ASPERCION");
		cmbtecnicas.addItem("NEBULIZACION");
		cmbtecnicas.addItem("TERMO");
		cmbtecnicas.addItem("PUNTEO DE GEL");
		cmbtecnicas.addItem("CORRECTIVO");
		cmbtecnicas.addItem("PREVENTIVO");
		
		
		final JComboBox<String> cmbforma = new JComboBox<String>();
		cmbforma.setBounds(365, 82, 128, 20);
		datos_ss.add(cmbforma);
		final JButton btnGuardar_1 = new JButton("Guardar");	
		btnGuardar_1.setBounds(231, 206, 93, 23);
		datos_ss.add(btnGuardar_1);
		
		textField_4 = new JTextField();
		textField_4.setBackground(new Color(255, 255, 255));
		textField_4.setEnabled(false);
		textField_4.setColumns(10);
		textField_4.setBounds(78, 221, 86, 20);
		datos_ss.add(textField_4);
		
		JLabel lblAplicaciones = new JLabel("Aplicaciones:");
		lblAplicaciones.setBounds(10, 224, 101, 14);
		datos_ss.add(lblAplicaciones);
		
		JLabel lblAplicacion = new JLabel("Aplicacion:");
		lblAplicacion.setBounds(10, 193, 68, 14);
		datos_ss.add(lblAplicacion);
		
		textField = new JTextField();
		textField.setBounds(78, 190, 86, 20);
		datos_ss.add(textField);
		textField.setColumns(10);
		
		JLabel lblHora_1 = new JLabel("Hora:");
		lblHora_1.setBounds(10, 121, 68, 14);
		datos_ss.add(lblHora_1);
		
		textField_2 = new JTextField();
		textField_2.setBounds(68, 118, 86, 20);
		datos_ss.add(textField_2);
		textField_2.setColumns(10);
		final JButton btnOrdenServicio_1 = new JButton("Orden Servicio");
		btnOrdenServicio_1.setBounds(358, 206, 107, 23);
		datos_ss.add(btnOrdenServicio_1);
		
		btnOrdenServicio_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try{
		
					@SuppressWarnings("deprecation")
					JasperReport ubicacion = (JasperReport) JRLoader.loadObject("prueba.jasper");
					JasperPrint print= JasperFillManager.fillReport(ubicacion, null, conn);
					JasperViewer view=new JasperViewer(print, false);
					view.setVisible(true);
				} catch(HeadlessException | JRException e){
						JOptionPane.showConfirmDialog(null, "Error: "+e.getMessage());
					}
			}
		});
		btnOrdenServicio_1.setEnabled(true);
		btnOrdenServicio_1.setVisible(true);
		
		btnGuardar_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				//SACANDO LAS CADENAS DE TEXTO DE LOS COMBOS
				
				Object tiposervicio= cmbtiposerv.getSelectedItem();
				String tiposerv= String.valueOf(tiposervicio);
				Object plagaserv=cmbplaga.getSelectedItem();
				String plaga=String.valueOf(plagaserv);
			
				Object formapago=cmbforma.getSelectedItem();
				String forma=String.valueOf(formapago);
				Object medioenterado=cmbtecnicas.getSelectedItem();
				String medio=String.valueOf(medioenterado);				
				try{
					Gray();
					if(txttecnico.getText().equals("")){
						txttecnico.setBorder(BorderFactory.createLineBorder(Color.RED,1));
						validacioncamp.setText("Llene los campos");
						txttecnico.requestFocus();
					} else if(txtcosto.getText().equals("")){
						txtcosto.setBorder(BorderFactory.createLineBorder(Color.RED,1));
						validacioncamp.setText("Llene los campos");
						txtcosto.requestFocus(); }
					else if(dateChooser.getDate()==null){
							validacioncamp.setText("Introdusca la fecha del servicio");	
						
					} else {
						int anio= dateChooser.getCalendar().get(Calendar.YEAR);
						int mes= dateChooser.getCalendar().get(Calendar.MARCH)+1;
						int dia= dateChooser.getCalendar().get(Calendar.DAY_OF_MONTH);
						String fecha_serv= dia+"/"+mes+"/"+anio;
					
				String SQL="INSERT INTO servicio(num_cliente, tiposervicio, plaga,"+
						"fecha_servicio, horas, formapago, clave_tecnico, observaciones, costo, mediocontacto, fecha, valido)"+
									"VALUES(?,?,?,?,?,?,?,?,?,?,Now(),'0')";
							PreparedStatement ps= conn.prepareStatement(SQL);
							ps.setString(1, txtnumcliente.getText());
							ps.setString(2, tiposerv);
							ps.setString(3, plaga);
							ps.setString(4, fecha_serv);
							/*ps.setInt(5);*/
							ps.setString(6, forma);
							ps.setString(7, txttecnico.getText());
							ps.setString(8, txtObservaciones.getText());
							ps.setString(9, txtcosto.getText());
							ps.setString(10, medio);	
							int n=ps.executeUpdate();
							if(n>0){
								JOptionPane.showMessageDialog(null, "Solicud de Servicio Completado");
								btnOrdenServicio.setVisible(true);
								Clean();
							}					
					}					
			} catch(SQLException e) {
				JOptionPane.showMessageDialog(null, "Error: "+e.getMessage());
			}
			}
		});
		btnGuardar_1.setVisible(false);
		cmbforma.addItem("EFECTIVO");
		cmbforma.addItem("TARJETA DE CREDITO");
		cmbforma.addItem("CHEQUE");
		
		btnBuscarPersona.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try{
					Clean();
					Gray();
					validacioncamp.setText("");
					
					if(txtNombre.getText().isEmpty()) {
						txtNombre.setBorder(BorderFactory.createLineBorder(Color.RED, 1));
						validacioncamp.setText("Llene los campos");
						txtNombre.requestFocus();
						}
						if(txtPaterno.getText().isEmpty()) {
						txtPaterno.setBorder(BorderFactory.createLineBorder(Color.RED, 1));
						validacioncamp.setText("Llene los campos");
						txtPaterno.requestFocus();
						}
					 if(txtMaterno.getText().isEmpty()) {
						txtMaterno.setBorder(BorderFactory.createLineBorder(Color.RED, 1));
						validacioncamp.setText("Llene los campos");
						txtMaterno.requestFocus();}				
					else 
					if(bandera==1){			
				String[]titulos= {"Num Cliente","Nombre", "Direccion","Referencia","RFC","Empresa","Telefono","Celular","E-Mail","Tipo Cliente"};
				modeltable= new DefaultTableModel(null, titulos);
				String consulta="SELECT * FROM clientes WHERE nombre='"+txtNombre.getText()+"' AND apellidopaterno='"+txtPaterno.getText()+"'"+
				"OR apellidomaterno='"+txtMaterno.getText()+"'";
				sent=conn.createStatement();
				String[] regreso= new String[10];
				ResultSet rs= sent.executeQuery(consulta);
				while (rs.next()){
					regreso[0]=rs.getString("num_cliente");
					regreso[1]=rs.getString("nombre");
					regreso[2]=rs.getString("direccion");
					regreso[3]=rs.getString("referencia");
					regreso[4]=rs.getString("rfc");
					regreso[5]=rs.getString("empresa");
					regreso[6]=rs.getString("telefono");
					regreso[7]=rs.getString("celular");
					regreso[8]=rs.getString("email");
					regreso[9]=rs.getString("tipocliente");
					modeltable.addRow(regreso);
				}
				scrollPane.setVisible(true);
				table.setVisible(true);
				table.setModel(modeltable);
				txttecnico.setEnabled(false);
				txtObservaciones.setEnabled(false);
			
					} else if(bandera==2){
						String[]titulos= {"Numero SS","Nombre", "Tipo Servicio","Fecha de Servicio","Horas","Forma Pago","Telefono","Costo","Fecha","Tipo Cliente"};
						modeltable= new DefaultTableModel(null, titulos);
						String consulta="SELECT * FROM clientes INNER JOIN servicio on clientes.num_cliente=servicio.num_cliente and clientes.nombre='"+txtNombre.getText()+"' AND clientes.apellidopaterno='"+txtPaterno.getText()+"' AND clientes.apellidomaterno='"+txtMaterno.getText()+"'";
						sent=conn.createStatement();
						String[] regreso= new String[10];
						ResultSet rs= sent.executeQuery(consulta);
						
						while (rs.next()){
							regreso[0]=rs.getString("num_ss");
							regreso[1]=rs.getString("nombre");
							regreso[2]=rs.getString("tiposervicio");
							regreso[3]=rs.getString("fecha_servicio");
							regreso[4]=rs.getString("horas");
							regreso[5]=rs.getString("formapago");
							regreso[6]=rs.getString("telefono");
							regreso[7]=rs.getString("costo");
							regreso[8]=rs.getString("fecha");
							regreso[9]=rs.getString("tipocliente");
							modeltable.addRow(regreso);
						}
						scrollPane.setVisible(true);
						table.setVisible(true);
						table.setModel(modeltable);
						txttecnico.setEnabled(false);
						txtObservaciones.setEnabled(false);	
					}
			}catch(Exception e){
					JOptionPane.showMessageDialog(null, "Error: "+e.getMessage());
				}
			}
		});
		btnBuscarPersona.setBounds(164, 36, 89, 23);
		contentPane.add(btnBuscarPersona);
		btnBuscarEmpresa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				scrollPane.setVisible(true);
				Clean();
				table.setVisible(true);
				txttecnico.setEnabled(false);
				txtObservaciones.setEnabled(false);
				 if(txtNombre.getText().isEmpty()) {
					 validacioncamp.setText("Introduce el Nombre de la Empresa");
					 txtNombre.setBorder(BorderFactory.createLineBorder(Color.RED,1));
					 txtNombre.requestFocus();
				 } else{
				Gray();	
			BuscarEmpresa();	
			}
			}
		});
		btnBuscarEmpresa.setBounds(229, 62, 89, 23);
		contentPane.add(btnBuscarEmpresa);
		scrollPane.setBounds(15, 92, 964, 61);
		contentPane.add(scrollPane);
		table.setFillsViewportHeight(true);
		table.setSurrendersFocusOnKeystroke(true);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table.doLayout();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent evit) {
				
				if(evit.getButton()==1){
					btnOrdenServicio_1.setEnabled(true);
					btnGuardar_1.setEnabled(true);
					int fila=table.getSelectedRow();
					try{
						if(bandera==1){
						String buscar="SELECT * FROM clientes WHERE num_cliente LIKE '%"+table.getValueAt(fila, 0)+"%'";
						sent=conn.createStatement();
						ResultSet rs=sent.executeQuery(buscar);
						rs.next();
						txtnumcliente.setText(rs.getString("num_cliente"));
						txtnombreapellidos.setText(rs.getString("nombre")+" "+rs.getString("apellidopaterno")+" "+rs.getString("apellidomaterno"));
						txtempresa.setText(rs.getString("empresa"));
						txtdireccion.setText(rs.getString("direccion"));
						txttelefono.setText(rs.getString("Telefono"));
						txtcelular.setText(rs.getString("celular"));
						txtrfc.setText(rs.getString("rfc"));
						txtciudad.setText(rs.getString("ciudad"));
						datoscliente.setVisible(true);
						datos_ss.setVisible(true);
						btnGuardar_1.setVisible(true);
						Hability();
						}
					else if(bandera==2){
						String actualizar="SELECT * FROM servicio INNER JOIN clientes ON servicio.num_cliente=clientes.num_cliente AND servicio.num_ss='"+table.getValueAt(fila, 0)+"'";
						sent=conn.createStatement();
						ResultSet rs=sent.executeQuery(actualizar);
						rs.next();
						txtnumcliente.setText(rs.getString("num_cliente"));
						txtdireccion.setText(rs.getString("direccion"));
						txttelefono.setText(rs.getString("Telefono"));
						txtcelular.setText(rs.getString("celular"));
						txtrfc.setText(rs.getString("rfc"));
						///DATOS DEL SERVICIO
						cmbtiposerv.setSelectedItem(rs.getString("tiposervicio"));
						cmbplaga.setSelectedItem(rs.getString("plaga"));
						txttecnico.setText(rs.getString("clave_tecnico"));
						cmbforma.setSelectedItem(rs.getString("formapago"));
						txtObservaciones.setText(rs.getString("observaciones"));
						txtcosto.setText(rs.getString("costo"));
						datoscliente.setVisible(true);
						datos_ss.setVisible(true);
						btnAct.setVisible(true);
						btnAct.setEnabled(true);
						Servicios.setVisible(true);
						Hability();				
					}
					} catch(Exception e){		
						JOptionPane.showMessageDialog(null, "Error: "+e.getMessage());	
					}
				}
			} 
		});
		scrollPane.setViewportView(table);	
		datoscliente.setBounds(21, 169, 401, 262);
		contentPane.add(datoscliente);
		datoscliente.setLayout(null);
		datoscliente.setVisible(false);
		
		lblDireccion.setBounds(10, 140, 74, 14);
		datoscliente.add(lblDireccion);
		
		JLabel lblNumeroDeCliente = new JLabel("Numero de Cliente:");
		lblNumeroDeCliente.setBounds(10, 24, 102, 14);
		datoscliente.add(lblNumeroDeCliente);
		
		JLabel lblCiudad = new JLabel("Ciudad:");
		lblCiudad.setBounds(10, 237, 58, 14);
		datoscliente.add(lblCiudad);
		
		txtciudad = new JTextField();
		txtciudad.setForeground(Color.BLACK);
		txtciudad.setBackground(Color.WHITE);
		txtciudad.setEnabled(false);
		txtciudad.setColumns(10);
		txtciudad.setBounds(79, 234, 124, 20);
		datoscliente.add(txtciudad);
		
		JLabel lblnombrecliente = new JLabel("Nombre:");
		lblnombrecliente.setBounds(10, 55, 58, 14);
		datoscliente.add(lblnombrecliente);
		
		txtnombreapellidos = new JTextField();
		txtnombreapellidos.setForeground(Color.BLACK);
		txtnombreapellidos.setEnabled(false);
		txtnombreapellidos.setColumns(10);
		txtnombreapellidos.setBackground(Color.WHITE);
		txtnombreapellidos.setBounds(90, 52, 289, 20);
		datoscliente.add(txtnombreapellidos);
		
		txtempresa = new JTextField();
		txtempresa.setForeground(Color.BLACK);
		txtempresa.setEnabled(false);
		txtempresa.setColumns(10);
		txtempresa.setBackground(Color.WHITE);
		txtempresa.setBounds(90, 80, 289, 20);
		datoscliente.add(txtempresa);
		
		JLabel lblEmpresa_1 = new JLabel("Empresa:");
		lblEmpresa_1.setBounds(10, 83, 74, 14);
		datoscliente.add(lblEmpresa_1);
		
		//Agregando el menu		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 238, 21);
		contentPane.add(menuBar);
		
		JMenu mnNuevo = new JMenu("Nuevo");
		menuBar.add(mnNuevo);
				
		JMenuItem mntmOrdenDeServicio = new JMenuItem("Solicitud de Servicio....");
		mntmOrdenDeServicio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				setTitle("Killers-Nueva Solicitud de Servicio");
			
				cmbBusqueda.setVisible(true);
				cmbBusqueda.setSelectedIndex(0);
				/*cmbBusquedaAct.setVisible(false);
				btnAct.setEnabled(false);
				btnAct.setVisible(false);
				escogerfechas.setVisible(false);
				lblEmpresa.setVisible(false);
				lblNombre.setVisible(false);
				lblNumero.setVisible(false);
				lblApellidoPaterno.setVisible(false);
				lblApellidoMaterno.setVisible(false);
				txtPaterno.setVisible(false);
				txtMaterno.setVisible(false);
				txtNombre.setVisible(false);
				btnBuscarPersona.setVisible(false);
				btnBuscarEmpresa.setVisible(false);
				btnBuscarNumero.setVisible(false);	
				btnOrdenServicio_1.setEnabled(false);
				btnOrdenServicio_1.setVisible(false);
				btnBuscarfecha.setVisible(false);*/
				
				btnGuardar_1.setVisible(false);
				scrollPane.setVisible(false);
				scrollPane.setBounds(15, 92, 740, 80);
				datoscliente.setVisible(false);
				datos_ss.setVisible(false);
				Servicios.setVisible(false);
				bandera=1;
				Desabilitar();
			}
		});
		mnNuevo.add(mntmOrdenDeServicio);
		JMenuItem mntmNewMenuItem = new JMenuItem("Clientes...");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				clientes cliente= new clientes();
				cliente.setVisible(true);		
			}
		});
		mnNuevo.add(mntmNewMenuItem);
		JMenu mnActualizar = new JMenu("Actualizar");
		menuBar.add(mnActualizar);
		
		JMenuItem mntmServicios = new JMenuItem("Servicios...");
		mntmServicios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setTitle("Killers-Actualizacion de Datos Servicios");
				cmbBusquedaAct.setVisible(true);
				cmbBusquedaAct.setSelectedIndex(0);
				cmbBusqueda.setVisible(false);
				btnAct.setEnabled(false);
				btnAct.setVisible(false);
				escogerfechas.setVisible(false);
				lblEmpresa.setVisible(false);
				lblNombre.setVisible(false);
				lblNumero.setVisible(false);
				lblApellidoPaterno.setVisible(false);
				lblApellidoMaterno.setVisible(false);
				txtPaterno.setVisible(false);
				txtMaterno.setVisible(false);
				txtNombre.setVisible(false);
				btnBuscarPersona.setVisible(false);
				btnBuscarEmpresa.setVisible(false);
				btnBuscarNumero.setVisible(false);	
				btnOrdenServicio_1.setEnabled(false);
				btnOrdenServicio_1.setVisible(false);
				btnBuscarfecha.setVisible(false);
				btnGuardar_1.setEnabled(false);
				btnGuardar_1.setVisible(false);
				scrollPane.setVisible(false);
				scrollPane.setBounds(15,97,740,74);
				datoscliente.setVisible(false);
				datos_ss.setVisible(false);
				Servicios.setVisible(false);
				Desabilitar();
				bandera=2;
			}
		});
		mnActualizar.add(mntmServicios);
		JMenu mnC = new JMenu("Reportes");
		menuBar.add(mnC);
		
		JMenuItem mntmDelDia = new JMenuItem("Del Dia");
		mntmDelDia.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setTitle("Killers- Reportes del Dia");
				cmbBusquedaAct.setVisible(false);
				cmbBusqueda.setVisible(false);
				btnAct.setEnabled(false);
				btnAct.setVisible(false);
				escogerfechas.setVisible(false);
				lblEmpresa.setVisible(false);
				lblNombre.setVisible(false);
				lblNumero.setVisible(false);
				lblApellidoPaterno.setVisible(false);
				lblApellidoMaterno.setVisible(false);
				txtPaterno.setVisible(false);
				txtMaterno.setVisible(false);
				txtNombre.setVisible(false);
				btnBuscarPersona.setVisible(false);
				btnBuscarEmpresa.setVisible(false);
				btnBuscarNumero.setVisible(false);	
				btnOrdenServicio_1.setEnabled(false);
				btnOrdenServicio_1.setBounds(596,495,103,23);	
				btnOrdenServicio_1.setVisible(false);
				btnGuardar_1.setEnabled(false);
				btnGuardar_1.setVisible(false);
				datoscliente.setVisible(false);
				datos_ss.setVisible(false);
				Servicios.setVisible(false);

				Desabilitar();
				Calendar fecha = new GregorianCalendar();
		        int anio = fecha.get(Calendar.YEAR);
		        int mes = fecha.get(Calendar.MONTH)+1;
		        int dia = fecha.get(Calendar.DAY_OF_MONTH);
		        String fecha_actual=anio+"-"+mes+"-"+dia;
				bandera=3;
					try {
					String[]titulos= {"Numero SS","Nombre","Empresa" ,"Tipo Servicio","Horas","Forma Pago","Telefono","Costo","Fecha Solicud","Tipo Cliente"};
					modeltable= new DefaultTableModel(null, titulos);
					String consulta="SELECT * FROM servicio INNER JOIN clientes ON servicio.num_cliente=clientes.num_cliente AND servicio.fecha='"+fecha_actual+"' AND servicio.valido='0'";
					sent=conn.createStatement();
					String[] regreso= new String[10];
					ResultSet rs= sent.executeQuery(consulta);
					while (rs.next()){
						regreso[0]=rs.getString("num_ss");
						regreso[1]=rs.getString("nombre");
						regreso[1]=rs.getString("nombre");
						regreso[2]=rs.getString("empresa");
						regreso[3]=rs.getString("tiposervicio");
						regreso[4]=rs.getString("horas");
						regreso[5]=rs.getString("formapago");
						regreso[6]=rs.getString("telefono");
						regreso[7]=rs.getString("costo");
						regreso[8]=rs.getString("fecha");
						regreso[9]=rs.getString("tipocliente");
						modeltable.addRow(regreso);
					}
					table.setModel(modeltable);	
					scrollPane.setVisible(true);
					scrollPane.setBounds(15, 97, 740, 360);
					table.setVisible(true);
					btnOrdenServicio_1.setVisible(true);
					btnOrdenServicio_1.setEnabled(true);
					btnOrdenServicio_1.setBounds(596,495,103,23);
					} catch(Exception e){
					JOptionPane.showMessageDialog(null, "Error: "+e.getMessage());
					}
					}
						});
		mnC.add(mntmDelDia);
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Ordenes de Servicio");
		mntmNewMenuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setTitle("Killers-Ordenes de Servicio");
				cmbBusqueda.setVisible(false);
				cmbBusquedaAct.setVisible(false);
				btnAct.setEnabled(false);
				btnAct.setVisible(false);
				escogerfechas.setVisible(true);
				lblEmpresa.setVisible(true);
				lblEmpresa.setText("Fecha:");
				lblNombre.setVisible(false);
				lblNumero.setVisible(false);
				lblApellidoPaterno.setVisible(false);
				lblApellidoMaterno.setVisible(false);
				txtPaterno.setVisible(false);
				txtMaterno.setVisible(false);
				txtNombre.setVisible(false);
				btnBuscarPersona.setVisible(false);
				btnBuscarEmpresa.setVisible(false);
				btnBuscarNumero.setVisible(false);	
				btnOrdenServicio_1.setEnabled(false);
				btnOrdenServicio_1.setVisible(false);
				btnOrdenServicio_1.setBounds(596,495,103,23);	
				btnBuscarfecha.setVisible(true);
				btnGuardar_1.setEnabled(false);
				btnGuardar_1.setVisible(false);
				scrollPane.setVisible(false);
				scrollPane.setBounds(596,495,103,23);
				datoscliente.setVisible(false);
				datos_ss.setVisible(false);
				Servicios.setVisible(false);
				bandera=4;
			}
		});
		mnC.add(mntmNewMenuItem_1);
		
		JMenuItem mntmDelDa = new JMenuItem("Servicios");
		mntmDelDa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setTitle("Killers-Servicios");
				cmbBusqueda.setVisible(false);
				cmbBusquedaAct.setVisible(false);
				btnAct.setEnabled(false);
				btnAct.setVisible(false);
				escogerfechas.setVisible(true);
				lblEmpresa.setVisible(true);
				lblEmpresa.setText("Fecha:");
				lblNombre.setVisible(false);
				lblNumero.setVisible(false);
				lblApellidoPaterno.setVisible(false);
				lblApellidoMaterno.setVisible(false);
				txtPaterno.setVisible(false);
				txtMaterno.setVisible(false);
				txtNombre.setVisible(false);
				btnBuscarPersona.setVisible(false);
				btnBuscarEmpresa.setVisible(false);
				btnBuscarNumero.setVisible(false);	
				btnOrdenServicio_1.setEnabled(false);
				btnOrdenServicio_1.setVisible(false);
				btnOrdenServicio_1.setBounds(596,495,103,23);	
				btnBuscarfecha.setVisible(true);
				btnGuardar_1.setEnabled(false);
				btnGuardar_1.setVisible(false);
				scrollPane.setVisible(false);
				scrollPane.setBounds(596,495,103,23);
				datoscliente.setVisible(false);
				datos_ss.setVisible(false);
				Servicios.setVisible(false);	
				bandera=5;
			}
		});
		mnC.add(mntmDelDa);		
		cmbBusqueda.addItem("Buscar Por:");
		cmbBusqueda.addItem("Persona");
		cmbBusqueda.addItem("Empresa");
		cmbBusqueda.addItem("Num Cliente");
		cmbBusqueda.addItemListener(new ItemListener() {
	
			public void itemStateChanged(ItemEvent arg0) {
				if(cmbBusqueda.getSelectedItem()=="Persona"){
					lblEmpresa.setVisible(false);
					lblNombre.setVisible(true);
					lblNumero.setVisible(false);
					lblApellidoPaterno.setVisible(true);
					lblApellidoMaterno.setVisible(true);
					txtPaterno.setVisible(true);
					txtMaterno.setVisible(true);
					txtNombre.setVisible(true);
					btnBuscarPersona.setVisible(true);
					btnBuscarEmpresa.setVisible(false);
					btnBuscarNumero.setVisible(false);
					txtNombre.requestFocus();					
				} else { if(cmbBusqueda.getSelectedItem()=="Empresa"){
					
				
					lblEmpresa.setVisible(true);
					lblNombre.setVisible(false);
					lblNumero.setVisible(false);
					lblApellidoPaterno.setVisible(false);
					lblApellidoMaterno.setVisible(false);
					txtPaterno.setVisible(false);
					txtMaterno.setVisible(false);
					txtNombre.setVisible(true);
					btnBuscarPersona.setVisible(false);
					btnBuscarEmpresa.setVisible(true);
					btnBuscarNumero.setVisible(false);
					txtNombre.requestFocus();
				} else {
					if(cmbBusqueda.getSelectedIndex()==3){
						 table.setEnabled(true);
						lblEmpresa.setVisible(false);
						lblNombre.setVisible(false);
						lblNumero.setVisible(true);
						lblApellidoPaterno.setVisible(false);
						lblApellidoMaterno.setVisible(false);
						txtPaterno.setVisible(false);
						txtMaterno.setVisible(false);
						txtNombre.setVisible(true);
						btnBuscarPersona.setVisible(false);
						btnBuscarEmpresa.setVisible(false);
						btnBuscarNumero.setVisible(true);
						txtNombre.requestFocus();
					} else {
						if(cmbBusqueda.getSelectedIndex()==4) {
						lblEmpresa.setVisible(false);
						lblNombre.setVisible(false);
						lblNumero.setVisible(false);
						lblApellidoPaterno.setVisible(false);
						lblApellidoMaterno.setVisible(false);
						txtPaterno.setVisible(false);
						txtMaterno.setVisible(false);
						txtNombre.setVisible(false);
						btnBuscarPersona.setVisible(false);
						btnBuscarEmpresa.setVisible(false);
						btnBuscarNumero.setVisible(false);
					}
				}}
				}
				}
		});				
		escogerfechas.setBounds(86, 63, 133, 20);
		contentPane.add(escogerfechas);
		
		Servicios.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Servicios", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		Servicios.setBounds(21, 465, 635, 137);
		contentPane.add(Servicios);
		Servicios.setLayout(null);
		((JTextFieldDateEditor)dateChooser.getDateEditor()).setEditable(false);
		
		btnAct.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			
				try{
					String SQL="UPDATE servicio SET tiposervicio=?, plaga=?, horas=?, formapago=?, clave_tecnico=?, observaciones=?, costo=?"+
							"WHERE num_ss=?";
								int fila=table.getSelectedRow();
								String dato=(String) table.getValueAt(fila, 0);
								PreparedStatement ps= conn.prepareStatement(SQL);
								ps.setString(1, String.valueOf(cmbtiposerv.getSelectedItem()));
								ps.setString(2, String.valueOf(cmbplaga.getSelectedItem()));
								//ps.setInt(3, cmbhoras_1.getSelectedIndex()-1);
								ps.setString(4, String.valueOf(cmbforma.getSelectedItem()));;
								ps.setString(5, txttecnico.getText());
								ps.setString(6, txtObservaciones.getText());
								ps.setString(7, txtcosto.getText());
								//ps.setString(8, fecha_inicio);
								//ps.setString(9,fecha_fin);
								ps.setString(10, dato);
								int n=ps.executeUpdate();
								if(n>0){
									JOptionPane.showMessageDialog(null, "Datos de Servicio Guardados");
								}
							} catch(SQLException e) {
								JOptionPane.showMessageDialog(null, "Error: "+e.getMessage());
							}	 
			}
		});
		btnAct.setBounds(672, 461, 89, 23);
		contentPane.add(btnAct);
		cmbBusquedaAct.setVisible(false);
		cmbBusquedaAct.addItem("Buscar Por...");
		cmbBusquedaAct.addItem("Fecha");
		cmbBusquedaAct.addItem("Persona");
		cmbBusquedaAct.addItem("Empresa");
		cmbBusquedaAct.addItem("Num Cliente");
		
		cmbBusquedaAct.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				
				if(cmbBusquedaAct.getSelectedItem()=="Fecha"){
					escogerfechas.setVisible(true);
					lblEmpresa.setText("Fecha:");
					btnBuscarEmpresa.setVisible(false);
					btnBuscarfecha.setVisible(true);
					lblEmpresa.setVisible(true);
					lblNombre.setVisible(false);
					lblNumero.setVisible(false);
					lblApellidoPaterno.setVisible(false);
					lblApellidoMaterno.setVisible(false);
					txtPaterno.setVisible(false);
					txtMaterno.setVisible(false);
					txtNombre.setVisible(false);
					btnBuscarPersona.setVisible(false);
					btnBuscarNumero.setVisible(false);
					}
				
				else if(cmbBusquedaAct.getSelectedItem()=="Persona"){
						lblEmpresa.setVisible(false);
						lblNombre.setVisible(true);
						lblNumero.setVisible(false);
						lblApellidoPaterno.setVisible(true);
						lblApellidoMaterno.setVisible(true);
						txtPaterno.setVisible(true);
						txtMaterno.setVisible(true);
						txtNombre.setVisible(true);
						btnBuscarfecha.setVisible(false);
						btnBuscarPersona.setVisible(true);
						btnBuscarEmpresa.setVisible(false);
						btnBuscarNumero.setVisible(false);
						txtNombre.requestFocus();
								
					} else  if(cmbBusquedaAct.getSelectedItem()=="Empresa"){	
					
						lblEmpresa.setVisible(true);
						lblEmpresa.setText("Empresa:");
						lblNombre.setVisible(false);
						lblNumero.setVisible(false);
						lblApellidoPaterno.setVisible(false);
						lblApellidoMaterno.setVisible(false);
						txtPaterno.setVisible(false);
						txtMaterno.setVisible(false);
						txtNombre.setVisible(true);
						btnBuscarPersona.setVisible(false);
						btnBuscarEmpresa.setVisible(true);
						btnBuscarNumero.setVisible(false);
						btnBuscarfecha.setVisible(false);
						txtNombre.requestFocus();
					} else 
						if(cmbBusquedaAct.getSelectedItem()=="Num Cliente"){
				
							lblEmpresa.setVisible(false);
							lblNombre.setVisible(false);
							lblNumero.setVisible(true);
							lblApellidoPaterno.setVisible(false);
							lblApellidoMaterno.setVisible(false);
							txtPaterno.setVisible(false);
							txtMaterno.setVisible(false);
							txtNombre.setVisible(true);
							btnBuscarPersona.setVisible(false);
							btnBuscarEmpresa.setVisible(false);
							btnBuscarNumero.setVisible(true);
							btnBuscarfecha.setVisible(false);
							txtNombre.requestFocus();
						}			
						
					}
				}	
	);
		//OCULTANDO ELEMENTOS//
		btnAct.setEnabled(false);
		btnAct.setVisible(false);
		escogerfechas.setVisible(false);
		lblEmpresa.setVisible(false);
		lblNombre.setVisible(false);
		lblNumero.setVisible(false);
		lblApellidoPaterno.setVisible(false);
		lblApellidoMaterno.setVisible(false);
		txtPaterno.setVisible(false);
		txtMaterno.setVisible(false);
		txtNombre.setVisible(false);
		btnBuscarPersona.setVisible(false);
		btnBuscarEmpresa.setVisible(false);
		btnBuscarNumero.setVisible(false);	
		btnBuscarfecha.setVisible(false);
	
		btnBuscarfecha.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				 	int anio= escogerfechas.getCalendar().get(Calendar.YEAR);
					int mes= escogerfechas.getCalendar().get(Calendar.MARCH)+1;
					int dia= escogerfechas.getCalendar().get(Calendar.DAY_OF_MONTH);
					String fecha_ss= dia+"/"+mes+"/"+anio;
					try {
						if(bandera==2 || bandera==4){
					String[]titulos= {"Numero SS","num Cliente","Empresa" ,"Tipo Servicio","Horas","Forma Pago","Telefono","Costo","Fecha Solicud","Tipo Cliente"};
					modeltable= new DefaultTableModel(null, titulos);
					String consulta="SELECT * FROM clientes INNER JOIN servicio on clientes.num_cliente=servicio.num_cliente AND servicio.fecha_servicio='"+fecha_ss+"' AND servicio.valido='0'" ;
					sent=conn.createStatement();
					String[] regreso= new String[10];
					ResultSet rs= sent.executeQuery(consulta);
					while (rs.next()){
						regreso[0]=rs.getString("num_ss");
						regreso[1]=rs.getString("num_cliente");
						regreso[1]=rs.getString("nombre");
						regreso[2]=rs.getString("empresa");
						regreso[3]=rs.getString("tiposervicio");
						regreso[4]=rs.getString("horas");
						regreso[5]=rs.getString("formapago");
						regreso[6]=rs.getString("telefono");
						regreso[7]=rs.getString("costo");
						regreso[8]=rs.getString("fecha");
						regreso[9]=rs.getString("tipocliente");
						modeltable.addRow(regreso);
					}
					table.setModel(modeltable);	
					table.setVisible(true);
					scrollPane.setVisible(true);
					txttecnico.setEnabled(false);
					txtObservaciones.setEnabled(false);
					scrollPane.setBounds(15, 97, 740, 74);
					if(bandera==4){
						scrollPane.setBounds(15, 97, 740, 360);
						btnOrdenServicio_1.setVisible(true);
						btnOrdenServicio_1.setEnabled(true);
					}
					} else if(bandera==5){
						String[]titulos= {"Numero SS","num Cliente","Empresa" ,"Tipo Servicio","Horas","Forma Pago","Telefono","Costo","Fecha Solicud","Tipo Cliente"};
						modeltable= new DefaultTableModel(null, titulos);
						String consulta="SELECT * FROM clientes INNER JOIN servicio on clientes.num_cliente=servicio.num_cliente AND servicio.fecha_servicio='"+fecha_ss+"' AND servicio.valido='1'" ;
						sent=conn.createStatement();
						String[] regreso= new String[10];
						ResultSet rs= sent.executeQuery(consulta);
						while (rs.next()){
							regreso[0]=rs.getString("num_ss");
							regreso[1]=rs.getString("num_cliente");
							regreso[1]=rs.getString("nombre");
							regreso[2]=rs.getString("empresa");
							regreso[3]=rs.getString("tiposervicio");
							regreso[4]=rs.getString("horas");
							regreso[5]=rs.getString("formapago");
							regreso[6]=rs.getString("telefono");
							regreso[7]=rs.getString("costo");
							regreso[8]=rs.getString("fecha");
							regreso[9]=rs.getString("tipocliente");
							modeltable.addRow(regreso);
						}
						table.setModel(modeltable);	
						table.setVisible(true);
						scrollPane.setVisible(true);
						scrollPane.setBounds(15, 97, 740, 360);
						btnOrdenServicio_1.setVisible(true);
						btnOrdenServicio_1.setEnabled(true);
										
					}
						
					} catch(Exception e){
					JOptionPane.showMessageDialog(null, "Error: "+e.getMessage());
					}
					}				
	
		});
		btnBuscarfecha.setBounds(229, 63, 89, 23);
		contentPane.add(btnBuscarfecha);
		cmbBusqueda.setVisible(false);
		scrollPane.setVisible(false);
		datos_ss.setVisible(false);
		Servicios.setVisible(false);
		Desabilitar();
	
	}
	
	//EMPIEZAN LOS METODOS
	void Desabilitar(){
		table.setVisible(false);
		txtdireccion.setEnabled(false);
		txtnumcliente.setEnabled(false);
		txtrfc.setEnabled(false);
		txttelefono.setEnabled(false);
		txtcelular.setEnabled(false);
		txttecnico.setEnabled(false);
		txtObservaciones.setEnabled(false);	
		txtcosto.setEnabled(false);
		
	}
	void BuscarEmpresa(){
		try{
			 if(bandera==1){
				String[]titulos= {"Num Cliente","Nombre","Empresa","Direccion","Referencia","RFC","Telefono","Celular","E-Mail","Tipo Cliente"};
				modeltable= new DefaultTableModel(null, titulos);
				String consulta="SELECT * FROM clientes WHERE empresa LIKE '%"+txtNombre.getText()+"%'";
				sent=conn.createStatement();
				String[] regreso= new String[10];
				ResultSet rs= sent.executeQuery(consulta);
				while (rs.next()){
					regreso[0]=rs.getString("num_cliente");
					regreso[1]=rs.getString("nombre");
					regreso[2]=rs.getString("empresa");
					regreso[3]=rs.getString("direccion");
					regreso[4]=rs.getString("referencia");
					regreso[5]=rs.getString("rfc");
					regreso[6]=rs.getString("telefono");
					regreso[7]=rs.getString("celular");
					regreso[8]=rs.getString("email");
					regreso[9]=rs.getString("tipocliente");
					modeltable.addRow(regreso); 
				}
				table.setModel(modeltable);

				 } else if(bandera==2){
					 String[]titulos= {"Numero SS","num Cliente","Empresa" ,"Tipo Servicio","Horas","Forma Pago","Telefono","Costo","Fecha Solicud","Tipo Cliente"};
						modeltable= new DefaultTableModel(null, titulos);
						String consulta="SELECT * FROM clientes INNER JOIN servicio on clientes.num_cliente=servicio.num_cliente AND clientes.empresa='"+txtNombre.getText()+"'" ;
						sent=conn.createStatement();
						String[] regreso= new String[10];
						ResultSet rs= sent.executeQuery(consulta);
						while (rs.next()){
							regreso[0]=rs.getString("num_ss");
							regreso[1]=rs.getString("num_cliente");
							regreso[1]=rs.getString("nombre");
							regreso[2]=rs.getString("empresa");
							regreso[3]=rs.getString("tiposervicio");
							regreso[4]=rs.getString("horas");
							regreso[5]=rs.getString("formapago");
							regreso[6]=rs.getString("telefono");
							regreso[7]=rs.getString("costo");
							regreso[8]=rs.getString("fecha");
							regreso[9]=rs.getString("tipocliente");
							modeltable.addRow(regreso);
						}
						table.setModel(modeltable);						
				 }		 
				} catch(Exception e){
					JOptionPane.showMessageDialog(null, "Error: "+e.getMessage());
				}
	}
	void Clean(){
		txtnumcliente.setText("");
		txtdireccion.setText("");
		txtrfc.setText("");
		txttelefono.setText("");
		txtcelular.setText("");				
	}
 void Gray(){
	txtNombre.setBorder(BorderFactory.createLineBorder(Color.GRAY,1));
	txtPaterno.setBorder(BorderFactory.createLineBorder(Color.GRAY,1));
	txtMaterno.setBorder(BorderFactory.createLineBorder(Color.GRAY,1));
	txttecnico.setBorder(BorderFactory.createLineBorder(Color.GRAY,1));
	
}
 void Hability(){
	txttecnico.setEnabled(true);
	txtObservaciones.setEnabled(true);
	txtcosto.setEnabled(true);		
 }
}
