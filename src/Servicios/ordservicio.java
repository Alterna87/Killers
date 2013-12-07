/*
 DEVELOPMENT BY FRANCISCA VELUETA FIGUEROA 2013-2014
 */
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
import javax.swing.JCheckBox;
import java.awt.SystemColor;
import javax.swing.border.LineBorder;

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
	private JTextField txtaplicacion;
	private JTextField txthora;
	private JTextField txtciudad;
	private JTextField txtnombreapellidos;
	private JTextField txtempresa;
	private JTextField txtaplicaciones;
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
		final JButton btnBuscarEmpresa = new JButton("Buscar");
		txtMaterno = new JTextField();
		final JButton btnBuscarNumero = new JButton("Buscar");
		table = new JTable();
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		final JScrollPane scrollPane = new JScrollPane();
		final JButton btnBuscarPersona = new JButton("Buscar");
		final JPanel datoscliente = new JPanel();
		datoscliente.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Datos del Cliente", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(255, 0, 0)));
		JLabel lblDireccion = new JLabel("Direccion:");
		JLabel lblRFC = new JLabel("RFC:");
		lblRFC.setBounds(10, 112, 44, 14);
		datoscliente.add(lblRFC);	
		final JPanel datos_ss = new JPanel();
		txtrfc = new JTextField();
		txtrfc.setEditable(false);
		txtrfc.setForeground(SystemColor.desktop);
		txtrfc.setBackground(Color.WHITE);
		txtrfc.setBounds(43, 109, 124, 20);
		datoscliente.add(txtrfc);
		txtrfc.setColumns(10);
		txtnumcliente = new JTextField();
		txtnumcliente.setEditable(false);
		txtnumcliente.setForeground(SystemColor.desktop);
		txtnumcliente.setBackground(Color.WHITE);
		txtnumcliente.setColumns(10);
		txtnumcliente.setBounds(125, 21, 124, 20);
		datoscliente.add(txtnumcliente);
		txttelefono = new JTextField();
		txttelefono.setEditable(false);
		txttelefono.setForeground(SystemColor.desktop);
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
		txtcelular.setEditable(false);
		txtcelular.setForeground(SystemColor.desktop);
		txtcelular.setBackground(Color.WHITE);
		txtcelular.setColumns(10);
		txtcelular.setBounds(79, 199, 124, 20);
		datoscliente.add(txtcelular);
		JLabel lblTipoDeServicio = new JLabel("Tipo de Servicio:");
		lblTipoDeServicio.setBounds(10, 21, 101, 14);
		datos_ss.add(lblTipoDeServicio);		
		JLabel lblFormaDePago = new JLabel("Forma de Pago:");
		lblFormaDePago.setBounds(174, 273, 93, 14);
		datos_ss.add(lblFormaDePago);
		JLabel lblPlagaEnEl = new JLabel("Plaga en el Area:");
		lblPlagaEnEl.setBounds(10, 56, 83, 14);
		datos_ss.add(lblPlagaEnEl);
		JLabel tbtTecnico = new JLabel("Tecnico:");
		tbtTecnico.setBounds(184, 186, 55, 14);
		datos_ss.add(tbtTecnico);
		txttecnico = new JTextField();
		final JCheckBox rastreros = new JCheckBox("Rastreros");
		final JCheckBox alemanias = new JCheckBox("Alema\u00F1as");
		final JCheckBox termitas = new JCheckBox("Termitas");
		final JCheckBox roedores = new JCheckBox("Roedores");
		final JCheckBox pulgas = new JCheckBox("Pulgas");
		final JCheckBox puntogel = new JCheckBox("Punto de Gel");
		final JCheckBox termo = new JCheckBox("Termo");
		final JCheckBox correctivo = new JCheckBox("Correctivo");
		final JCheckBox preventivo = new JCheckBox("Preventivo");
		final JLabel lblCiudad = new JLabel("Ciudad:");
		txtempresa = new JTextField();
		txtempresa.setEditable(false);
		final JLabel lblnombrecliente = new JLabel("Nombre:");
		final JLabel lblEmpresa_1 = new JLabel("Empresa:");
		final JButton btnOrdenServicio_1 = new JButton("Orden Servicio");
		
		
		txttecnico.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				txttecnico.setText(txttecnico.getText().toUpperCase());
			}
			@Override
			public void keyTyped(KeyEvent evt) {
				char c=evt.getKeyChar();
				if((c<'a'|| c>'z') &&( c<'A' ||c>'z') && ((c!='ñ' && c!='Ñ') && (c!=(char)KeyEvent.VK_SPACE)))
					evt.consume();
			}
		});
		txttecnico.setBackground(new Color(255, 255, 255));
		txttecnico.setBounds(248, 183, 261, 20);
		datos_ss.add(txttecnico);
		txttecnico.setColumns(10);
		JLabel lblFecha = new JLabel("Fecha de Servicio:");
		lblFecha.setBounds(174, 161, 110, 14);
		datos_ss.add(lblFecha);
		final JDateChooser dateChooser = new JDateChooser();
		dateChooser.setDateFormatString("dd/MM/yyyy");
		dateChooser.setBounds(286, 155, 115, 20);
		datos_ss.add(dateChooser);
		JLabel lblObservaciones_1 = new JLabel("Observaciones:");
		lblObservaciones_1.setBounds(10, 227, 115, 14);
		datos_ss.add(lblObservaciones_1);
		txtObservaciones = new JTextField();
		
		
		txtObservaciones.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				txtObservaciones.setText(txtObservaciones.getText().toUpperCase());
			}
		});
		txtObservaciones.setBackground(new Color(255, 255, 255));
		txtObservaciones.setBounds(109, 224, 426, 20);
		datos_ss.add(txtObservaciones);
		txtObservaciones.setColumns(10);
		JLabel lblmedio_1 = new JLabel("Tecnicas:");
		lblmedio_1.setBounds(10, 111, 57, 14);
		datos_ss.add(lblmedio_1);
		JLabel lblcosto_1 = new JLabel("Costo:");
		lblcosto_1.setBounds(10, 274, 46, 14);
		datos_ss.add(lblcosto_1);
		txtcosto = new JTextField();
		txtcosto.setBackground(new Color(255, 255, 255));
		txtcosto.setBounds(66, 271, 93, 20);
		datos_ss.add(txtcosto);
		txtcosto.setColumns(10);
		final JDateChooser escogerfechas = new JDateChooser();
		final JButton btnBuscarfecha = new JButton("Buscar");
		final JCheckBox alacranes = new JCheckBox("Alacranes");
		final JCheckBox aranias = new JCheckBox("Ara\u00F1as");
		final JCheckBox hormigas = new JCheckBox("Hormigas");
		final JCheckBox garrapatas = new JCheckBox("Garrapatas");
		final JCheckBox voladores = new JCheckBox("Voladores");
		final JCheckBox aspercion = new JCheckBox("Aspercion");
		final JCheckBox nebulizacion = new JCheckBox("Nebulizacion");
		final JLabel lblNumeroDeCliente = new JLabel("Numero de Cliente:");
		txtciudad = new JTextField();
		txtciudad.setEditable(false);
		txtnombreapellidos = new JTextField();
		txtnombreapellidos.setEditable(false);
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
						 validacioncamp.setText("");
						 if(bandera==1){
						String[]titulos= {"Num Cliente","Nombre","Empresa","Direccion","Referencia","RFC","Telefono","Celular","E-Mail","Tipo Cliente"};
						modeltable= new DefaultTableModel(null, titulos);
						String consulta="SELECT * FROM clientes WHERE num_cliente='"+txtNombre.getText()+"'";
						sent=conn.createStatement();
						String[] regreso= new String[10];
						ResultSet rs= sent.executeQuery(consulta);
						while (rs.next()){
							regreso[0]=rs.getString("num_cliente");
							regreso[1]=rs.getString("nombre")+" "+rs.getString("apellidopaterno")+" "+rs.getString("apellidomaterno");
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
						table.setModel(modeltable);
						 } else if(bandera==2){
							 String[]titulos= {"Numero SS","Num Cliente","Nombre" ,"Empresa","Tipo Servicio","Plagas","Tecnicas","Fecha Servicio","Hora","Tecnico","Fecha SS"};
								modeltable= new DefaultTableModel(null, titulos);
								String consulta="SELECT * FROM clientes INNER JOIN solicitud_servicio ON clientes.num_cliente=solicitud_servicio.num_cliente AND clientes.num_cliente='"+txtNombre.getText()+"' AND solicitud_servicio.valido='0'" ;
								sent=conn.createStatement();
								String[] regreso= new String[11];
								ResultSet rs= sent.executeQuery(consulta);
								while (rs.next()){
									regreso[0]=rs.getString("num_ss");
									regreso[1]=rs.getString("num_cliente");
									regreso[2]=rs.getString("nombre")+" "+rs.getString("apellidopaterno")+" "+rs.getString("apellidomaterno");;
									regreso[3]=rs.getString("empresa");
									regreso[4]=rs.getString("tipo_servicio");
									regreso[5]=rs.getString("plagas");
									regreso[6]=rs.getString("tecnicas");
									regreso[7]=rs.getString("fecha_servicio");
									regreso[8]=rs.getString("hora");
									regreso[9]=rs.getString("tecnico");
									regreso[10]=rs.getString("fecha_ss");
									modeltable.addRow(regreso);
								}
								scrollPane.setVisible(true);
								table.setVisible(true);
								table.setModel(modeltable);					
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
		
		datos_ss.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Datos Solicitud de Servicio", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(255, 0, 0)));
		datos_ss.setBounds(414, 177, 565, 330);
		contentPane.add(datos_ss);
		datos_ss.setLayout(null);
		cmbtiposerv.setBounds(107, 18, 120, 20);
		datos_ss.add(cmbtiposerv);
		cmbtiposerv.addItem("CORRECTIVO");
		cmbtiposerv.addItem("PREVENTIVO");
		cmbtiposerv.addItem("TRATAMIENTO");
		
		
		final JComboBox<String> cmbforma = new JComboBox<String>();
		cmbforma.setBounds(265, 268, 128, 20);
		datos_ss.add(cmbforma);
		final JButton btnGuardar_1 = new JButton("Guardar");	
		btnGuardar_1.setBounds(159, 298, 93, 23);
		datos_ss.add(btnGuardar_1);
		
		txtaplicaciones = new JTextField();
		txtaplicaciones.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent evt) {
				char c= evt.getKeyChar();
				if (c<'0'|| c>'9')
					evt.consume(); 
			}
		});
		txtaplicaciones.setBackground(new Color(255, 255, 255));
		txtaplicaciones.setColumns(10);
		txtaplicaciones.setBounds(102, 158, 55, 20);
		datos_ss.add(txtaplicaciones);
		
		JLabel lblAplicaciones = new JLabel("Aplicaciones:");
		lblAplicaciones.setBounds(10, 163, 101, 14);
		datos_ss.add(lblAplicaciones);
		
		JLabel lblAplicacion = new JLabel("Aplicacion:");
		lblAplicacion.setBounds(10, 189, 68, 14);
		datos_ss.add(lblAplicacion);
		
		txtaplicacion = new JTextField();
		txtaplicacion.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent evt) {
				char c= evt.getKeyChar();
				if (c<'0'|| c>'9')
					evt.consume(); 			
			}
		});
		txtaplicacion.setBounds(78, 186, 86, 20);
		datos_ss.add(txtaplicacion);
		txtaplicacion.setColumns(10);
		
		JLabel lblHora_1 = new JLabel("Hora:");
		lblHora_1.setBounds(428, 158, 55, 14);
		datos_ss.add(lblHora_1);
		
		txthora = new JTextField();
		txthora.setBounds(473, 155, 86, 20);
		datos_ss.add(txthora);
		txthora.setColumns(10);
		
		btnOrdenServicio_1.setBounds(286, 298, 128, 23);
		datos_ss.add(btnOrdenServicio_1);
		
		btnOrdenServicio_1.addActionListener(new ActionListener() {
			@SuppressWarnings("unchecked")
			public void actionPerformed(ActionEvent arg0) {
				//ORDENES DE SERVICIO
					if(bandera==2){
						try{
						String orden="INSERT INTO ordenservicio(numero_ss,num_cliente, aplicacion,"+
									"aplicaciones, valido, fecha)"+
									"VALUES(?,?,?,?,'0', Now())";
						int fila=table.getSelectedRow();
						Object [] opciones ={"Aceptar","Cancelar"};
						String dato=(String) table.getValueAt(fila, 0);
						PreparedStatement ps= conn.prepareStatement(orden);
						ps.setString(1,dato);
						ps.setString(2,txtnumcliente.getText());
						ps.setString(3,txtaplicacion.getText());
						ps.setString(4, txtaplicaciones.getText());
						int n=ps.executeUpdate();
						ResultSet mt=ps.getGeneratedKeys();
						int clave=0;
						while(mt.next()){ 
						 clave=mt.getInt(1);
						}
						String ss="UPDATE solicitud_servicio SET valido='1'"+
								"WHERE num_ss=?";
						int fila2=table.getSelectedRow();
						PreparedStatement pt=conn.prepareStatement(ss);
						pt.setString(1,dato);
						int mn=pt.executeUpdate();
						if(n>0 && mn>0){
							int eleccion = JOptionPane.showOptionDialog(null,"¿Desea Imprimir la Orden de Servicio?","Mensaje de Confirmacion",
									JOptionPane.YES_NO_OPTION,
									JOptionPane.QUESTION_MESSAGE,null,opciones,"Aceptar");
									if (eleccion == JOptionPane.YES_OPTION){
										try{
										String ubicacion = "Reportes/OrdenServicio1.jasper";
										@SuppressWarnings("rawtypes")
										Map parametros=new HashMap();
										parametros.put("neworden", clave);
										JasperPrint print= JasperFillManager.fillReport(ubicacion, parametros,conn);
										JasperViewer view=new JasperViewer(print, false);
										view.setTitle("Orden de Servicio");
										view.setVisible(true);
									} catch(HeadlessException | JRException e){
										JOptionPane.showConfirmDialog(null, "Error: "+e.getMessage());
										}
										
									}
						
				
					}				
						}catch(SQLException e){
							JOptionPane.showMessageDialog(null, "Error: "+e.getMessage());
						}	
						
					} 
			if (bandera==6){
				
				
				
			}
			}
		});
			
		rastreros.setBounds(99, 42, 90, 23);
		datos_ss.add(rastreros);		
		alemanias.setBounds(191, 42, 93, 23);
		datos_ss.add(alemanias);		
		termitas.setBounds(286, 42, 83, 23);
		datos_ss.add(termitas);		
		roedores.setBounds(381, 42, 87, 23);
		datos_ss.add(roedores);
		alacranes.setBounds(470, 42, 89, 23);
		datos_ss.add(alacranes);
		aranias.setBounds(99, 68, 73, 23);
		datos_ss.add(aranias);
		hormigas.setBounds(191, 68, 93, 23);
		datos_ss.add(hormigas);
		garrapatas.setBounds(286, 68, 93, 23);
		datos_ss.add(garrapatas);		
		pulgas.setBounds(381, 68, 68, 23);
		datos_ss.add(pulgas);
		voladores.setBounds(470, 68, 86, 23);
		datos_ss.add(voladores);
		aspercion.setBounds(68, 94, 83, 23);
		datos_ss.add(aspercion);
		puntogel.setBounds(159, 94, 108, 23);
		datos_ss.add(puntogel);
		nebulizacion.setBounds(265, 94, 97, 23);
		datos_ss.add(nebulizacion);
		termo.setBounds(68, 120, 73, 23);
		datos_ss.add(termo);
		correctivo.setBounds(159, 120, 93, 23);
		datos_ss.add(correctivo);
		preventivo.setBounds(265, 120, 104, 23);
		datos_ss.add(preventivo);
		final JButton btnAct = new JButton("Actualizar");
		btnAct.setBounds(159, 298, 115, 23);
		datos_ss.add(btnAct);
		
		btnAct.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//SACANDO EQUIVALENCIAS DE PLAGAS//
				int rastrerosc;int alemaniasc; int termitasc;	int roedoresc;int alacranesc;
				int araniasc;int hormigasc;int garrapatasc; int pulgasc; int voladoresc;
				String rastreross="", alemaniass="", termitass="", roedoress="", alacraness="", araniass="", hormigass="", garrapatass="", pulgass="", voladoress="";
				int aspercionc; int puntogelc; int nebulizacionc; int termoc; int correctivoc; int preventivoc;		
				String aspercions="", puntogels="", nebulizacions="", termos="", correctivos="", preventivos="";
				try{
					//PREPARACION DE LAS VARIABLES PARA EL UPDATE DE LA BD
					if(rastreros.isSelected()){
						 rastreross=" RASTREROS";
					}else rastreross="";
					if(alemanias.isSelected()){
					alemaniass=" ALEMAÑAS";
					}else alemaniass="";
					if(termitas.isSelected()){
						termitass=" TERMITAS";
					}else termitass="";
					if(roedores.isSelected()){
						roedoress=" ROEDORES";
					}else roedoress="";
					if(alacranes.isSelected()){
					}else alacraness="";
					if(aranias.isSelected()){
						araniass=" ARAÑAS";
					}else araniass="";
					if(hormigas.isSelected()){
						hormigass=" HORMIGAS";
					}else hormigass="";
					if(garrapatas.isSelected()){
					garrapatass=" GARRAPATAS";
					}else garrapatass="";
					if(pulgas.isSelected()){	
					 pulgass=" PULGAS";
					}else pulgass="";
					if(voladores.isSelected()){
						 voladoress=" VOLADORES";
					}else voladoress="";
					if(aspercion.isSelected()){	
						aspercions=" ASPERCION";
					}else aspercions="";
					if(puntogel.isSelected()){	
						puntogels=" PUNTO DE GEL";
					}else puntogels="";
					if(nebulizacion.isSelected()){	
						 nebulizacions=" NEBULIZACION";
					}else nebulizacions="";
					if(termo.isSelected()){
						 termos=" TERMO";
					}else termos="";
					if(correctivo.isSelected()){
						 correctivos=" CORRECTIVO";
					}else correctivos="";
					if(preventivo.isSelected()){
						preventivos=" PREVENTIVO";
					}else preventivos="";
					String plagas=rastreross+alemaniass+termitass+roedoress+alacraness+araniass+hormigass+
							garrapatass+pulgass+voladoress;
					String tecnica=aspercions+puntogels+nebulizacions+termos+correctivos+preventivos;
					int anio= dateChooser.getCalendar().get(Calendar.YEAR);
					int mes= dateChooser.getCalendar().get(Calendar.MARCH)+1;
					int dia= dateChooser.getCalendar().get(Calendar.DAY_OF_MONTH);
					String fecha_serv= anio+"-"+mes+"-"+dia;
					//TERMINA LA PREPARACION EMPIEZAN LAS QUERYS
				
			String SQL="UPDATE solicitud_servicio SET tipo_servicio=?, plagas=?,"+
					"tecnicas=?, fecha_servicio=?, hora=?, tecnico=?, observaciones=?, aplicaciones=?, aplicacion=?, costo=? WHERE num_ss=?";
						int fila=table.getSelectedRow();
						String dato=(String) table.getValueAt(fila, 0);
						PreparedStatement ps= conn.prepareStatement(SQL);
						ps.setString(1, String.valueOf(cmbtiposerv.getSelectedItem()));
						ps.setString(2,plagas);
						ps.setString(3, tecnica);
						ps.setString(4, fecha_serv);
						ps.setString(5, txthora.getText());
						ps.setString(6, txttecnico.getText());
						ps.setString(7, txtObservaciones.getText());
						ps.setString(8, txtaplicaciones.getText());
						ps.setString(9, txtaplicacion.getText());
						ps.setString(10, txtcosto.getText());
						ps.setString(11, dato);
						//ps.setString(10, String.valueOf(cmbmedio.getSelectedItem()));
						int n=ps.executeUpdate();
						if(n>0){
							JOptionPane.showMessageDialog(null, "Solicud de Servicio Actualizado en la BD");
							btnOrdenServicio.setVisible(true);
						}								
		} catch(SQLException e) {
			JOptionPane.showMessageDialog(null, "Error: "+e.getMessage());
		}
			}
		});
		//OCULTANDO ELEMENTOS//
		btnAct.setEnabled(false);
		btnAct.setVisible(false);
		
		btnGuardar_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				//SACANDO EQUIVALENCIAS DE PLAGAS//
				int rastrerosc;int alemaniasc; int termitasc;	int roedoresc;int alacranesc;
				int araniasc;int hormigasc;int garrapatasc; int pulgasc; int voladoresc;
				String rastreross="", alemaniass="", termitass="", roedoress="", alacraness="", araniass="", hormigass="", garrapatass="", pulgass="", voladoress="";
				int aspercionc; int puntogelc; int nebulizacionc; int termoc; int correctivoc; int preventivoc;		
				String aspercions="", puntogels="", nebulizacions="", termos="", correctivos="", preventivos="";
				try{
					Gray();
			 if(dateChooser.getDate()==null){
							validacioncamp.setText("Introdusca la fecha del servicio");	
					} else {
						//PREPARACION DE LAS VARIABLES PARA EL INSERT DE LA BD
						if(rastreros.isSelected()){
							 rastreross=" RASTREROS";
						}else rastreross="";
						if(alemanias.isSelected()){
						alemaniass=" ALEMAÑAS";
						}else alemaniass="";
						if(termitas.isSelected()){
							termitass=" TERMITAS";
						}else termitass="";
						if(roedores.isSelected()){
							roedoress=" ROEDORES";
						}else roedoress="";
						if(alacranes.isSelected()){
						}else alacraness="";
						if(aranias.isSelected()){
							araniass=" ARAÑAS";
						}else araniass="";
						if(hormigas.isSelected()){
							hormigass=" HORMIGAS";
						}else hormigass="";
						if(garrapatas.isSelected()){
						garrapatass=" GARRAPATAS";
						}else garrapatass="";
						if(pulgas.isSelected()){	
						 pulgass=" PULGAS";
						}else pulgass="";
						if(voladores.isSelected()){
							 voladoress=" VOLADORES";
						}else voladoress="";
						if(aspercion.isSelected()){	
							aspercions=" ASPERCION";
						}else aspercions="";
						if(puntogel.isSelected()){	
							puntogels=" PUNTO DE GEL";
						}else puntogels="";
						if(nebulizacion.isSelected()){	
							 nebulizacions=" NEBULIZACION";
						}else nebulizacions="";
						if(termo.isSelected()){
							 termos=" TERMO";
						}else termos="";
						if(correctivo.isSelected()){
							 correctivos=" CORRECTIVO";
						}else correctivos="";
						if(preventivo.isSelected()){
							preventivos=" PREVENTIVO";
						}else preventivos="";
						String plagas=rastreross+alemaniass+termitass+roedoress+alacraness+araniass+hormigass+
								garrapatass+pulgass+voladoress;
						String tecnica=aspercions+puntogels+nebulizacions+termos+correctivos+preventivos;
						int anio= dateChooser.getCalendar().get(Calendar.YEAR);
						int mes= dateChooser.getCalendar().get(Calendar.MARCH)+1;
						int dia= dateChooser.getCalendar().get(Calendar.DAY_OF_MONTH);
						String fecha_serv= anio+"-"+mes+"-"+dia;
						//TERMINA LA PREPARACION EMPIEZAN LAS QUERYS
					
				String SQL="INSERT INTO solicitud_servicio(num_cliente,tipo_servicio, plagas,"+
						"tecnicas, fecha_servicio, hora, tecnico, observaciones, aplicaciones, aplicacion, costo,fecha_ss, valido)"+
									"VALUES(?,?,?,?,?,?,?,?,?,?,?,Now(),'0')";
							PreparedStatement ps= conn.prepareStatement(SQL);
							ps.setString(1, txtnumcliente.getText());
							ps.setString(2, String.valueOf(cmbtiposerv.getSelectedItem()));
							ps.setString(3,plagas);
							ps.setString(4, tecnica);
							ps.setString(5, fecha_serv);
							ps.setString(6, txthora.getText());
							ps.setString(7, txttecnico.getText());
							ps.setString(8, txtObservaciones.getText());
							ps.setString(9, txtaplicaciones.getText());
							ps.setString(10, txtaplicacion.getText());
							ps.setString(11, txtcosto.getText());
							//ps.setString(10, String.valueOf(cmbmedio.getSelectedItem()));
							int n=ps.executeUpdate();
							if(n>0){
								JOptionPane.showMessageDialog(null, "Solicud de Servicio Guardada en la BD");
							}				
						}				
			} catch(Exception e) {
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
					
					 if(txtNombre.getText().isEmpty()) {
						 validacioncamp.setText("Introduce el Nombre del Cliente");
						 txtNombre.setBorder(BorderFactory.createLineBorder(Color.RED,1));
						 txtNombre.requestFocus();
					 } else	 if(txtPaterno.getText().isEmpty()){
							 validacioncamp.setText("Introduce el Apellido Paterno");
							 txtPaterno.setBorder(BorderFactory.createLineBorder(Color.RED,1));
							 txtPaterno.requestFocus(); 
						 } else if(txtMaterno.getText().isEmpty()){
							 validacioncamp.setText("Introduce el Apellido Materno");
							 txtMaterno.setBorder(BorderFactory.createLineBorder(Color.RED,1));
							 txtMaterno.requestFocus(); 
						 }
						 else{
						 validacioncamp.setText("");
						 if(bandera==1){
						String[]titulos= {"Num Cliente","Nombre","Empresa","Direccion","Referencia","RFC","Telefono","Celular","E-Mail","Tipo Cliente", "Ciudad"};
						modeltable= new DefaultTableModel(null, titulos);
						String consulta="SELECT * FROM clientes WHERE nombre='"+txtNombre.getText()+"' OR apellidopaterno='"+txtPaterno.getText()+"' OR apellidomaterno='"+txtMaterno.getText()+"'";
						sent=conn.createStatement();
						String[] regreso= new String[11];
						ResultSet rs= sent.executeQuery(consulta);
						while (rs.next()){
								regreso[0]=rs.getString("num_cliente");
								regreso[1]=rs.getString("nombre")+" "+rs.getString("apellidopaterno")+" "+rs.getString("apellidomaterno");
								regreso[2]=rs.getString("empresa");
								regreso[3]=rs.getString("direccion");
								regreso[4]=rs.getString("referencia");
								regreso[5]=rs.getString("rfc");
								regreso[6]=rs.getString("telefono");
								regreso[7]=rs.getString("celular");
								regreso[8]=rs.getString("email");
								regreso[9]=rs.getString("tipocliente");
								regreso[10]=rs.getString("ciudad");
								modeltable.addRow(regreso); 
							}
						scrollPane.setVisible(true);
						table.setVisible(true);
						table.setModel(modeltable);
			
						 } else if(bandera==2){
							 String[]titulos= {"Numero SS","Num Cliente","Nombre" ,"Empresa","Tipo Servicio","Plagas","Tecnicas","Fecha Servicio","Hora","Tecnico","Fecha SS"};
								modeltable= new DefaultTableModel(null, titulos);
								String consulta="SELECT * FROM clientes INNER JOIN solicitud_servicio ON clientes.num_cliente=solicitud_servicio.num_cliente AND clientes.nombre LIKE '%"+txtNombre.getText()+"%' AND clientes.apellidopaterno LIKE '%"+txtPaterno.getText()+"%' AND clientes.apellidomaterno LIKE '%"+txtMaterno.getText()+"%' AND solicitud_servicio.valido='0'" ;
								sent=conn.createStatement();
								String[] regreso= new String[11];
								ResultSet rs= sent.executeQuery(consulta);
							
								while (rs.next()){
									regreso[0]=rs.getString("num_ss");
									regreso[1]=rs.getString("num_cliente");
									regreso[2]=rs.getString("nombre")+" "+rs.getString("apellidopaterno")+" "+rs.getString("apellidomaterno");;
									regreso[3]=rs.getString("empresa");
									regreso[4]=rs.getString("tipo_servicio");
									regreso[5]=rs.getString("plagas");
									regreso[6]=rs.getString("tecnicas");
									regreso[7]=rs.getString("fecha_servicio");
									regreso[8]=rs.getString("hora");
									regreso[9]=rs.getString("tecnico");
									regreso[10]=rs.getString("fecha_ss");
									modeltable.addRow(regreso);
								}
								scrollPane.setVisible(true);
								table.setVisible(true);
								table.setModel(modeltable);					
						 }
					 } 
						} catch(Exception e){
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
				 if(txtNombre.getText().isEmpty()) {
					 validacioncamp.setText("Introduce el Nombre de la Empresa");
					 txtNombre.setBorder(BorderFactory.createLineBorder(Color.RED,1));
					 txtNombre.requestFocus();
				 } else{
				validacioncamp.setText("");
					 Gray();	
			BuscarEmpresa();	
			}
			}
		});
		btnBuscarEmpresa.setBounds(229, 62, 89, 23);
		contentPane.add(btnBuscarEmpresa);
		scrollPane.setBounds(15, 92, 964, 61);
		contentPane.add(scrollPane);
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
						}
					else if(bandera==2 || bandera==5){
						String actualizar="SELECT * FROM solicitud_servicio INNER JOIN clientes ON solicitud_servicio.num_cliente=clientes.num_cliente AND solicitud_servicio.num_ss='"+table.getValueAt(fila, 0)+"'";
						sent=conn.createStatement();
						ResultSet rs=sent.executeQuery(actualizar);
						rs.next();
						//**DATOS CLIENTE**
						txtnumcliente.setText(rs.getString("num_cliente"));
						txtnombreapellidos.setText(rs.getString("nombre")+" "+rs.getString("apellidopaterno")+" "+rs.getString("apellidomaterno"));
						txtempresa.setText(rs.getString("empresa"));
						txtdireccion.setText(rs.getString("direccion"));
						txttelefono.setText(rs.getString("Telefono"));
						txtcelular.setText(rs.getString("celular"));
						txtrfc.setText(rs.getString("rfc"));
						txtciudad.setText(rs.getString("ciudad"));
						//**DATOS DEL SERVICIO**
						cmbtiposerv.setSelectedItem(rs.getString("tipo_servicio"));
						txtaplicaciones.setText(rs.getString("aplicaciones"));
						txtaplicacion.setText(rs.getString("aplicacion"));
						txtcosto.setText(rs.getString("costo"));
						txttecnico.setText(rs.getString("tecnico"));
						txtObservaciones.setText(rs.getString("observaciones"));
						txthora.setText(rs.getString("hora"));
						Date date = rs.getDate("fecha_servicio");
						dateChooser.setDate(date);
						//DATOS DE LAS PLAGAS
						String rastrerosq="SELECT plagas FROM solicitud_servicio WHERE num_ss='"+table.getValueAt(fila, 0)+"' AND plagas LIKE '%RASTREROS%'";
						ResultSet es=sent.executeQuery(rastrerosq);
						if(es.next()){
							rastreros.setSelected(true);
						}
						String alemaniasq="SELECT plagas FROM solicitud_servicio WHERE num_ss='"+table.getValueAt(fila, 0)+"' AND plagas LIKE '%ALEMAÑAS%'";
						ResultSet ale=sent.executeQuery(alemaniasq);
						if(ale.next()){
							alemanias.setSelected(true);
						}
						String termitasq="SELECT plagas FROM solicitud_servicio WHERE num_ss='"+table.getValueAt(fila, 0)+"' AND plagas LIKE '%TERMITAS%'";
						ResultSet ter=sent.executeQuery(termitasq);
						if(ter.next()){
							termitas.setSelected(true);
						}
						String roedoresq="SELECT plagas FROM solicitud_servicio WHERE num_ss='"+table.getValueAt(fila, 0)+"' AND plagas LIKE '%ROEDORES%'";
						ResultSet roe=sent.executeQuery(roedoresq);
						if(roe.next()){
							roedores.setSelected(true);
						}
						String alacranesq="SELECT plagas FROM solicitud_servicio WHERE num_ss='"+table.getValueAt(fila, 0)+"' AND plagas LIKE '%ALACRANES%'";
						ResultSet ala=sent.executeQuery(alacranesq);
						if(ala.next()){
							alacranes.setSelected(true);
						}
						String araniasq="SELECT plagas FROM solicitud_servicio WHERE num_ss='"+table.getValueAt(fila, 0)+"' AND plagas LIKE '%ARAÑAS%'";
						ResultSet ara=sent.executeQuery(araniasq);
						if(ara.next()){
							aranias.setSelected(true);
						}
						String hormigasq="SELECT plagas FROM solicitud_servicio WHERE num_ss='"+table.getValueAt(fila, 0)+"' AND plagas LIKE '%HORMIGAS%'";
						ResultSet hor=sent.executeQuery(alemaniasq);
						if(hor.next()){
							hormigas.setSelected(true);
						}
						String garrapatasq="SELECT plagas FROM solicitud_servicio WHERE num_ss='"+table.getValueAt(fila, 0)+"' AND plagas LIKE '%GARRAPATAS%'";
						ResultSet gar=sent.executeQuery(alemaniasq);
						if(gar.next()){
							garrapatas.setSelected(true);
						}
						String pulgasq="SELECT plagas FROM solicitud_servicio WHERE num_ss='"+table.getValueAt(fila, 0)+"' AND plagas LIKE '%PULGAS%'";
						ResultSet pul=sent.executeQuery(alemaniasq);
						if(pul.next()){
							pulgas.setSelected(true);
						}
						String voldaroressq="SELECT plagas FROM solicitud_servicio WHERE num_ss='"+table.getValueAt(fila, 0)+"' AND plagas LIKE '%VOLADORES%'";
						ResultSet vol=sent.executeQuery(alemaniasq);
						if(vol.next()){
							voladores.setSelected(true);
						}
						//TERMINA DATOS DE PLAGAS, EMPIEZA DATOS TECNICAS
						String aspercionq="SELECT tecnicas FROM solicitud_servicio WHERE num_ss='"+table.getValueAt(fila, 0)+"' AND tecnicas LIKE '%ASPERCION%'";
						ResultSet asp=sent.executeQuery(aspercionq);
						if(asp.next()){
							aspercion.setSelected(true);
						}
						String puntogelq="SELECT tecnicas FROM solicitud_servicio WHERE num_ss='"+table.getValueAt(fila, 0)+"' AND tecnicas LIKE '%PUNTO DE GEL%'";
						ResultSet pung=sent.executeQuery(puntogelq);
						if(pung.next()){
							puntogel.setSelected(true);
						}
						String nebulizacionq="SELECT tecnicas FROM solicitud_servicio WHERE num_ss='"+table.getValueAt(fila, 0)+"' AND tecnicas LIKE '%NEBULIZACION%'";
						ResultSet neb=sent.executeQuery(nebulizacionq);
						if(neb.next()){
							nebulizacion.setSelected(true);
						}
						String termoq="SELECT tecnicas FROM solicitud_servicio WHERE num_ss='"+table.getValueAt(fila, 0)+"' AND tecnicas LIKE '%TERMO%'";
						ResultSet term=sent.executeQuery(termoq);
						if(term.next()){
							termo.setSelected(true);
						}
						String correctivoq="SELECT tecnicas FROM solicitud_servicio WHERE num_ss='"+table.getValueAt(fila, 0)+"' AND tecnicas LIKE '%CORRECTIVO%'";
						ResultSet cor=sent.executeQuery(correctivoq);
						if(cor.next()){
							correctivo.setSelected(true);
						}
						String preventivoq="SELECT tecnicas FROM solicitud_servicio WHERE num_ss='"+table.getValueAt(fila, 0)+"' AND tecnicas LIKE '%PREVENTIVO%'";
						ResultSet prev=sent.executeQuery(preventivoq);
						if(prev.next()){
							preventivo.setSelected(true);
						}
						//FINALIZA LOS DATOS DE TECNICA
						datoscliente.setVisible(true);
						datos_ss.setVisible(true);
						btnAct.setVisible(true);
						btnAct.setEnabled(true);
						btnOrdenServicio_1.setVisible(true);
						if(bandera==5){
							scrollPane.setVisible(false);
							table.setVisible(false);						
						}
					}
					} catch(Exception e){		
						JOptionPane.showMessageDialog(null, "Error: "+e.getMessage());	
					}
				}
			} 
		});
		scrollPane.setViewportView(table);	
		datoscliente.setBounds(10, 177, 401, 267);
		contentPane.add(datoscliente);
		datoscliente.setLayout(null);
		datoscliente.setVisible(false);	
		lblDireccion.setBounds(10, 140, 74, 14);
		datoscliente.add(lblDireccion);
		lblNumeroDeCliente.setBounds(10, 24, 127, 14);
		datoscliente.add(lblNumeroDeCliente);		
		lblCiudad.setBounds(10, 237, 58, 14);
		datoscliente.add(lblCiudad);		
		txtciudad.setForeground(SystemColor.desktop);
		txtciudad.setBackground(Color.WHITE);
		txtciudad.setColumns(10);
		txtciudad.setBounds(79, 234, 192, 20);
		datoscliente.add(txtciudad);		
		lblnombrecliente.setBounds(10, 55, 58, 14);
		datoscliente.add(lblnombrecliente);
		txtnombreapellidos.setForeground(SystemColor.desktop);
		txtnombreapellidos.setColumns(10);
		txtnombreapellidos.setBackground(Color.WHITE);
		txtnombreapellidos.setBounds(90, 52, 289, 20);
		datoscliente.add(txtnombreapellidos);		
		txtempresa.setForeground(SystemColor.desktop);
		txtempresa.setColumns(10);
		txtempresa.setBackground(Color.WHITE);
		txtempresa.setBounds(90, 80, 289, 20);
		datoscliente.add(txtempresa);
		lblEmpresa_1.setBounds(10, 83, 74, 14);
		datoscliente.add(lblEmpresa_1);
		txtdireccion = new JTextField();
		txtdireccion.setEditable(false);
		txtdireccion.setBounds(75, 137, 316, 20);
		datoscliente.add(txtdireccion);
		txtdireccion.setForeground(SystemColor.desktop);
		txtdireccion.setBackground(Color.WHITE);
		txtdireccion.setColumns(10);	
		
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
				escogerfechas.setVisible(true);
				lblEmpresa.setVisible(true);
				lblEmpresa.setText("Empresa:");
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
				btnBuscarfecha.setVisible(true);
				cmbBusquedaAct.setVisible(false);
				cmbBusqueda.setVisible(true);
				cmbBusqueda.setSelectedIndex(0);
				btnGuardar_1.setVisible(false);
				scrollPane.setVisible(false);
				scrollPane.setBounds(15,92,964,61);
				datoscliente.setVisible(false);
				datos_ss.setVisible(false);
				escogerfechas.setVisible(false);
				btnBuscarfecha.setVisible(false);
				lblEmpresa.setVisible(false);
				bandera=1;
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
		
		JMenuItem mntmNewMenuItem_2 = new JMenuItem("Orden de Servicio");
		mntmNewMenuItem_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setTitle("Killers-Nueva Orden de Servicio");
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
				btnBuscarfecha.setVisible(true);
				btnGuardar_1.setEnabled(false);
				btnGuardar_1.setVisible(false);
				scrollPane.setVisible(false);
				scrollPane.setBounds(596,495,103,23);
				datoscliente.setVisible(false);
				datos_ss.setVisible(false);
				bandera=5;
			}
		});
		mnNuevo.add(mntmNewMenuItem_2);
		JMenu mnActualizar = new JMenu("Actualizar");
		menuBar.add(mnActualizar);
		
		JMenuItem mntmServicios = new JMenuItem("Solicitud de Servicio...");
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
				scrollPane.setBounds(15,92,964,61);
				datoscliente.setVisible(false);
				datos_ss.setVisible(false);
				bandera=2;
			}
		});
		mnActualizar.add(mntmServicios);
		
		JMenuItem mntmServicios_1 = new JMenuItem("Orden de Servicio...");
		mntmServicios_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setTitle("Killers-Actualizar Orden de Servicio");
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
				btnBuscarfecha.setVisible(true);
				btnGuardar_1.setEnabled(false);
				btnGuardar_1.setVisible(false);
				scrollPane.setVisible(false);
				scrollPane.setBounds(596,495,103,23);
				datoscliente.setVisible(false);
				datos_ss.setVisible(false);
				bandera=6;
			}
		});
		mnActualizar.add(mntmServicios_1);
		JMenu mnC = new JMenu("Reportes");
		menuBar.add(mnC);
		
		JMenuItem mntmDelDia = new JMenuItem("Del d\u00EDa");
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
						regreso[1]=rs.getString("nombre")+" "+rs.getString("apellidopaterno")+" "+rs.getString("apellidomaterno");
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
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Solicitudes de Servicio");
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
				bandera=4;
			}
		});
		mnC.add(mntmNewMenuItem_1);
		
		JMenu mnNewMenu = new JMenu("Ordenes de Servicio...");
		mnC.add(mnNewMenu);
		
		JMenuItem mntmActivos = new JMenuItem("Activos");
		mnNewMenu.add(mntmActivos);
		
		JMenuItem mntmNewMenuItem_3 = new JMenuItem("Atendidos");
		mnNewMenu.add(mntmNewMenuItem_3);
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
		((JTextFieldDateEditor)dateChooser.getDateEditor()).setEditable(false);
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
		btnOrdenServicio_1.setVisible(false);
	
		btnBuscarfecha.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				 	int anio= escogerfechas.getCalendar().get(Calendar.YEAR);
					int mes= escogerfechas.getCalendar().get(Calendar.MARCH)+1;
					int dia= escogerfechas.getCalendar().get(Calendar.DAY_OF_MONTH);
					String fecha_ss= anio+"-"+mes+"-"+dia;
					try {
						if(bandera==2 || bandera==4){
					String[]titulos= {"Numero SS","Num Cliente","Nombre" ,"Empresa","Tipo Servicio","Plagas","Tecnicas","Fecha Servicio","Hora","Tecnico","Fecha SS"};
					modeltable= new DefaultTableModel(null, titulos);
					String consulta="SELECT * FROM clientes INNER JOIN solicitud_servicio ON clientes.num_cliente=solicitud_servicio.num_cliente AND solicitud_servicio.fecha_ss='"+fecha_ss+"'" ;
					sent=conn.createStatement();
					String[] regreso= new String[11];
					ResultSet rs= sent.executeQuery(consulta);
					while (rs.next()){
						regreso[0]=rs.getString("num_ss");
						regreso[1]=rs.getString("num_cliente");
						regreso[2]=rs.getString("nombre")+" "+rs.getString("apellidopaterno")+" "+rs.getString("apellidomaterno");;
						regreso[3]=rs.getString("empresa");
						regreso[4]=rs.getString("tipo_servicio");
						regreso[5]=rs.getString("plagas");
						regreso[6]=rs.getString("tecnicas");
						regreso[7]=rs.getString("fecha_servicio");
						regreso[8]=rs.getString("hora");
						regreso[9]=rs.getString("tecnico");
						regreso[10]=rs.getString("fecha_ss");
						modeltable.addRow(regreso);
					}
					scrollPane.setVisible(true);
					table.setVisible(true);
					table.setModel(modeltable);	
					
					if(bandera==4){
						scrollPane.setBounds(15, 97, 740, 360);
						btnOrdenServicio_1.setVisible(true);
						btnOrdenServicio_1.setEnabled(true);
					}
					} else if(bandera==5){
						datoscliente.setVisible(false);
						datos_ss.setVisible(false);						
						String[]titulos= {"Numero SS","num Cliente","Empresa" ,"Tipo Servicio","Horas","Forma Pago","Telefono","Costo","Fecha Solicud","Tipo Cliente"};
						modeltable= new DefaultTableModel(null, titulos);
						String consulta="SELECT * FROM clientes INNER JOIN solicitud_servicio ON clientes.num_cliente=solicitud_servicio.num_cliente AND solicitud_servicio.fecha_ss='"+fecha_ss+"' AND solicitud_servicio.valido='0'" ;
						sent=conn.createStatement();
						String[] regreso= new String[11];
						ResultSet rs= sent.executeQuery(consulta);
						
						while (rs.next()){
							regreso[0]=rs.getString("num_ss");
							regreso[1]=rs.getString("num_cliente");
							regreso[2]=rs.getString("nombre")+" "+rs.getString("apellidopaterno")+" "+rs.getString("apellidomaterno");;
							regreso[3]=rs.getString("empresa");
							regreso[4]=rs.getString("tipo_servicio");
							regreso[5]=rs.getString("plagas");
							regreso[6]=rs.getString("tecnicas");
							regreso[7]=rs.getString("fecha_servicio");
							regreso[8]=rs.getString("hora");
							regreso[9]=rs.getString("tecnico");
							regreso[10]=rs.getString("fecha_ss");
							modeltable.addRow(regreso);
						}
						scrollPane.setVisible(true);
						table.setVisible(true);
						table.setModel(modeltable);
						scrollPane.setBounds(15, 97, 740, 360);
										
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
		Desabilitar();
	
	}
	
	//EMPIEZAN LOS METODOS
	void Desabilitar(){
		table.setVisible(false);
		txtciudad.setEnabled(false);
		
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
					regreso[1]=rs.getString("nombre")+" "+rs.getString("apellidopaterno")+" "+rs.getString("apellidomaterno");
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
					 String[]titulos= {"Numero SS","Num Cliente","Nombre" ,"Empresa","Tipo Servicio","Plagas","Tecnicas","Fecha Servicio","Hora","Tecnico","Fecha SS"};
						modeltable= new DefaultTableModel(null, titulos);
						String consulta="SELECT * FROM clientes INNER JOIN solicitud_servicio on clientes.num_cliente=solicitud_servicio.num_cliente AND clientes.empresa LIKE'%"+txtNombre.getText()+"%'" ;
						sent=conn.createStatement();
						String[] regreso= new String[11];
						ResultSet rs= sent.executeQuery(consulta);
						while (rs.next()){
							regreso[0]=rs.getString("num_ss");
							regreso[1]=rs.getString("num_cliente");
							regreso[2]=rs.getString("nombre")+" "+rs.getString("apellidopaterno")+" "+rs.getString("apellidomaterno");;
							regreso[3]=rs.getString("empresa");
							regreso[4]=rs.getString("tipo_servicio");
							regreso[5]=rs.getString("plagas");
							regreso[6]=rs.getString("tecnicas");
							regreso[7]=rs.getString("fecha_servicio");
							regreso[8]=rs.getString("hora");
							regreso[9]=rs.getString("tecnico");
							regreso[10]=rs.getString("fecha_ss");
							modeltable.addRow(regreso);
						}
						table.setModel(modeltable);						
				 }
				} catch(Exception e){
					JOptionPane.showMessageDialog(null, "Error: "+e.getMessage());
				}
	}
	void Clean(){
		//DATOS DEL CLIENTE
		txtnumcliente.setText("");
		txtnombreapellidos.setText("");
		txtdireccion.setText("");
		txtempresa.setText("");
		txtrfc.setText("");
		txttelefono.setText("");
		txtcelular.setText("");	
		txtciudad.setText("");

		
	}
 void Gray(){
	txtNombre.setBorder(BorderFactory.createLineBorder(Color.GRAY,1));
	txtPaterno.setBorder(BorderFactory.createLineBorder(Color.GRAY,1));
	txtMaterno.setBorder(BorderFactory.createLineBorder(Color.GRAY,1));
	txttecnico.setBorder(BorderFactory.createLineBorder(Color.GRAY,1));

	
}
 void Hability(){	
 }
}
