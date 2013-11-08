package Servicios;

import java.awt.BorderLayout;
import java.awt.EventQueue;

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

import conexion.bd;
import clientes.clientes;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.JTextComponent;

import java.awt.Color;
import javax.swing.JTextArea;
import javax.swing.JMenuBar;
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
	
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ordservicio frame = new ordservicio();
					frame.setVisible(true);
					JMenuBar jmb= new JMenuBar();
					frame.setJMenuBar(jmb);
					/*Agregando Menu
					JMenu nuevo = new JMenu("Nuevo");
					jmb.add(nuevo);
					JMenuItem orden = new JMenuItem("Orden de Servicio...");
					nuevo.add(orden);*/
					
					
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
		
		setTitle("Killers- Servicios");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 715, 494);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
			
		final JComboBox<String> cmbBusqueda = new JComboBox<String>();
		cmbBusqueda.setBounds(21, 21, 133, 20);
		contentPane.add(cmbBusqueda);
		
		final JLabel validacioncamp = new JLabel("");
		validacioncamp.setForeground(Color.RED);
		validacioncamp.setBounds(443, 29, 207, 14);
		contentPane.add(validacioncamp);
		
		final JButton btnBuscarNumero = new JButton("Buscar");
		btnBuscarNumero.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				try{
					 
					 if(txtNombre.getText().equals("")) {
						 txtNombre.setBorder(BorderFactory.createLineBorder(Color.RED,1));
						 validacioncamp.setText("Introduce el Numero del Cliente");
						 
					 } else {
					
						String[]titulos= {"Num Cliente","Nombre", "Direccion","Referencia","RFC","Empresa","Telefono","Celular","E-Mail","Tipo Cliente"};
						modeltable= new DefaultTableModel(null, titulos);
				
						String consulta="SELECT * FROM clientes WHERE num_cliente='"+txtNombre.getText()+"'";
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
					
						table.setModel(modeltable);
						
					 }
						} catch(Exception e){
							JOptionPane.showMessageDialog(null, "Error: "+e.getMessage());
						}
			}

		});
		btnBuscarNumero.setBounds(229, 46, 89, 23);
		contentPane.add(btnBuscarNumero);
		
		final JLabel lblNombre = new JLabel("Nombre: ");
		lblNombre.setBounds(10, 55, 55, 14);
		contentPane.add(lblNombre);
		
		final JLabel lblEmpresa = new JLabel("Empresa:");
		lblEmpresa.setBounds(10, 52, 55, 14);
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
		txtNombre.setBounds(86, 49, 133, 20);
		contentPane.add(txtNombre);
		txtNombre.setColumns(10);
		
		final JLabel lblApellidoPaterno = new JLabel("Apellido Paterno:");
		lblApellidoPaterno.setBounds(252, 51, 97, 14);
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
				if (c<'A' || c>'Z') evt.consume();
			}
		});
		txtPaterno.setColumns(10);
		txtPaterno.setBounds(359, 48, 104, 20);
		contentPane.add(txtPaterno);
		
		final JLabel lblApellidoMaterno = new JLabel("Apellido Materno:");
		lblApellidoMaterno.setBounds(473, 51, 115, 14);
		contentPane.add(lblApellidoMaterno);
		
		txtMaterno = new JTextField();
		txtMaterno.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent evt) {
			char c=evt.getKeyChar();
			if(c<'A' || c>'Z') evt.consume();
			}
			@Override
			public void keyReleased(KeyEvent e) {
			txtMaterno.setText(txtMaterno.getText().toUpperCase());
			}
		});
		txtMaterno.setColumns(10);
		txtMaterno.setBounds(580, 48, 109, 20);
		contentPane.add(txtMaterno);
		
		final JLabel lblNumero = new JLabel("Num Cliente");
		lblNumero.setBounds(10, 55, 76, 14);
		contentPane.add(lblNumero);
	
		
		final JButton btnBuscarPersona = new JButton("Buscar");
		btnBuscarPersona.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try{
					
					if(txtNombre.getText().equals("")) {
						txtNombre.setBorder(BorderFactory.createLineBorder(Color.RED, 1));
						validacioncamp.setText("Llene los campos");
						txtNombre.requestFocus();
						}
						else if(txtPaterno.getText().equals("")) {
						txtPaterno.setBorder(BorderFactory.createLineBorder(Color.RED, 1));
						validacioncamp.setText("Llene los campos");
						txtPaterno.requestFocus();
						}
					else if(txtMaterno.getText().equals("")) {
						txtMaterno.setBorder(BorderFactory.createLineBorder(Color.RED, 1));
						validacioncamp.setText("Llene los campos");
						txtMaterno.requestFocus();}		
					
					else { 
						Bordes();
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
				table.setModel(modeltable);
			
					}
			 
			}catch(Exception e){
					JOptionPane.showMessageDialog(null, "Error: "+e.getMessage());
				
				}
			
			}
		});
		btnBuscarPersona.setBounds(164, 20, 89, 23);
		contentPane.add(btnBuscarPersona);
		
		final JButton btnBuscarEmpresa = new JButton("Buscar");
		btnBuscarEmpresa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			BuscarEmpresa();	
			}
		});
		btnBuscarEmpresa.setBounds(229, 46, 89, 23);
		contentPane.add(btnBuscarEmpresa);	
	
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(21, 97, 629, 74);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent evt) {
				if(evt.getButton()==1){
					int fila=table.getSelectedRow();
					try{
						String actualizar="SELECT * FROM clientes WHERE num_cliente='"+table.getValueAt(fila, 0)+"'";
						sent=conn.createStatement();
						ResultSet rs=sent.executeQuery(actualizar);
						rs.next();
						txtNombre.setText(rs.getString("nombre"));
						txtnumcliente.setText(rs.getString("num_cliente"));
						txtdireccion.setText(rs.getString("direccion"));
						txttelefono.setText(rs.getString("Telefono"));
						txtcelular.setText(rs.getString("celular"));
						txtrfc.setText(rs.getString("rfc"));
					}catch(Exception e){		
						JOptionPane.showMessageDialog(null, "Error: "+e.getMessage());	
					}
				}
			}
		});
		table.setFillsViewportHeight(true);
		scrollPane.setViewportView(table);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Datos del Cliente", TitledBorder.LEADING, TitledBorder.TOP, null, Color.BLACK));
		panel.setBounds(21, 182, 645, 89);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblDireccion = new JLabel("Direccion:");
		lblDireccion.setBounds(256, 24, 74, 14);
		panel.add(lblDireccion);
		
		JLabel lblRFC = new JLabel("RFC:");
		lblRFC.setBounds(10, 55, 44, 14);
		panel.add(lblRFC);
		
		txtdireccion = new JTextField();
		txtdireccion.setBounds(321, 21, 306, 20);
		panel.add(txtdireccion);
		txtdireccion.setColumns(10);
		
		txtrfc = new JTextField();
		txtrfc.setBounds(43, 52, 124, 20);
		panel.add(txtrfc);
		txtrfc.setColumns(10);
		
		txtnumcliente = new JTextField();
		txtnumcliente.setColumns(10);
		txtnumcliente.setBounds(122, 21, 124, 20);
		panel.add(txtnumcliente);
		
		JLabel lblNumeroDeCliente = new JLabel("Numero de Cliente:");
		lblNumeroDeCliente.setBounds(10, 24, 102, 14);
		panel.add(lblNumeroDeCliente);
		
		txttelefono = new JTextField();
		txttelefono.setColumns(10);
		txttelefono.setBounds(256, 52, 124, 20);
		panel.add(txttelefono);
		
		JLabel lblTelefono = new JLabel("Telefono:");
		lblTelefono.setBounds(187, 55, 58, 14);
		panel.add(lblTelefono);
		
		JLabel lblCelular = new JLabel("Celular:");
		lblCelular.setBounds(389, 55, 58, 14);
		panel.add(lblCelular);
		
		txtcelular = new JTextField();
		txtcelular.setColumns(10);
		txtcelular.setBounds(458, 52, 124, 20);
		panel.add(txtcelular);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Datos del Servicio", TitledBorder.LEADING, TitledBorder.TOP, null, Color.RED));
		panel_1.setBounds(31, 282, 635, 82);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblTipoDeServicio = new JLabel("Tipo de Servicio:");
		lblTipoDeServicio.setBounds(10, 21, 101, 14);
		panel_1.add(lblTipoDeServicio);
		
		final JComboBox<String> cmbtiposerv = new JComboBox<String>();
		cmbtiposerv.setBounds(107, 18, 120, 20);
		panel_1.add(cmbtiposerv);
		cmbtiposerv.addItem("Correctivo");
		cmbtiposerv.addItem("Preventivo");
		cmbtiposerv.addItem("Tratamiento");
		
		
		JLabel lblHora = new JLabel("Horas:");
		lblHora.setBounds(10, 48, 46, 14);
		panel_1.add(lblHora);
		
		JLabel lblFormaDePago = new JLabel("Forma de Pago:");
		lblFormaDePago.setBounds(174, 52, 93, 14);
		panel_1.add(lblFormaDePago);
		
		final JComboBox<String> cmbforma = new JComboBox<String>();
		cmbforma.setBounds(265, 47, 128, 20);
		panel_1.add(cmbforma);
		cmbforma.addItem("EFECTIVO");
		cmbforma.addItem("TARJETA DE CREDITO");
		cmbforma.addItem("CHEQUE");
		
		JLabel lblPlagaEnEl = new JLabel("Plaga en el Area:");
		lblPlagaEnEl.setBounds(244, 21, 101, 14);
		panel_1.add(lblPlagaEnEl);
		
		JLabel tbtTecnico = new JLabel("Tecnico:");
		tbtTecnico.setBounds(408, 51, 55, 14);
		panel_1.add(tbtTecnico);
		
		txttecnico = new JTextField();
		txttecnico.setBounds(472, 48, 153, 20);
		panel_1.add(txttecnico);
		txttecnico.setColumns(10);
		
		JLabel lblFecha = new JLabel("Fecha:");
		lblFecha.setBounds(473, 21, 101, 14);
		panel_1.add(lblFecha);
		
		final JDateChooser dateChooser = new JDateChooser();
		dateChooser.setDateFormatString("dd/MM/yyyy");
		dateChooser.setBounds(518, 21, 107, 20);
		panel_1.add(dateChooser);
		((JTextFieldDateEditor)dateChooser.getDateEditor()).setEditable(false);
		
		final JComboBox<String> cmbhoras = new JComboBox<String>();
		cmbhoras.setBounds(55, 45, 81, 20);
		panel_1.add(cmbhoras);
		cmbhoras.addItem("1 Hora");
		cmbhoras.addItem("2 Horas");
		cmbhoras.addItem("3 Horas");
		cmbhoras.addItem("4 Horas");
		cmbhoras.addItem("5 Horas");
		
		final JComboBox<String> cmbplaga = new JComboBox<String>();
		cmbplaga.setBounds(343, 18, 120, 20);
		panel_1.add(cmbplaga);
		cmbplaga.addItem("Rastreros");
		cmbplaga.addItem("Alemañas");
		cmbplaga.addItem("Termintas");
		cmbplaga.addItem("Roedores");
		cmbplaga.addItem("Alacranes");
		cmbplaga.addItem("Arañas");
		cmbplaga.addItem("Hormigas");
		cmbplaga.addItem("Garrapatas");
		cmbplaga.addItem("Pulgas");
		cmbplaga.addItem("Voladores");
		
		JLabel lblObservaciones = new JLabel("Observaciones:");
		lblObservaciones.setBounds(21, 375, 115, 14);
		contentPane.add(lblObservaciones);
		
		txtObservaciones = new JTextField();
		txtObservaciones.setBounds(120, 372, 323, 20);
		contentPane.add(txtObservaciones);
		txtObservaciones.setColumns(10);
		
	
		
		JLabel lblmedio = new JLabel("Medio de Contacto:");
		lblmedio.setBounds(21, 403, 101, 14);
		contentPane.add(lblmedio);
		
		JLabel lblcosto = new JLabel("Costo:");
		lblcosto.setBounds(443, 400, 46, 14);
		contentPane.add(lblcosto);
		
		txtcosto = new JTextField();
		txtcosto.setColumns(10);
		txtcosto.setBounds(499, 397, 65, 20);
		contentPane.add(txtcosto);
		
		//Agregando el menu		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 699, 21);
		contentPane.add(menuBar);
		
		JMenu mnNuevo = new JMenu("Nuevo");
		menuBar.add(mnNuevo);
				
		JMenuItem mntmOrdenDeServicio = new JMenuItem("Solicitud de Servicio....");
		mntmOrdenDeServicio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setTitle("Killers-Solicitud de Servicio");	
				
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
		mnActualizar.add(mntmServicios);
		
		JMenu mnC = new JMenu("Reportes");
		menuBar.add(mnC);
		
		JMenuItem mntmDelDa = new JMenuItem("Del D\u00EDa");
		mnC.add(mntmDelDa);
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Generar Orden de Servicio");
		mnC.add(mntmNewMenuItem_1);
		
		final JComboBox<String> cmbmedio = new JComboBox<String>();
		cmbmedio.setBounds(145, 403, 145, 20);
		contentPane.add(cmbmedio);	
		

		
		JButton btnGuardar = new JButton("Guardar");
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				//SACANDO LAS CADENAS DE TEXTO DE LOS COMBOS
				Object tiposervicio= cmbtiposerv.getSelectedItem();
				String tiposerv= String.valueOf(tiposervicio);
				Object plagaserv=cmbplaga.getSelectedItem();
				String plaga=String.valueOf(plagaserv);
				int horas=cmbhoras.getSelectedIndex()+1;
				Object formapago=cmbforma.getSelectedItem();
				String forma=String.valueOf(formapago);
				Object medioenterado=cmbmedio.getSelectedItem();
				String medio=String.valueOf(medioenterado);
				int anio= dateChooser.getCalendar().get(Calendar.YEAR);
				int dia= dateChooser.getCalendar().get(Calendar.MARCH);
				int mes= dateChooser.getCalendar().get(Calendar.DAY_OF_MONTH);
				String fecha_serv= dia+"/"+mes+"/"+anio;		
				try{
					if(txttecnico.getText().equals("")){
						txttecnico.setBorder(BorderFactory.createLineBorder(Color.RED,1));
						validacioncamp.setText("Llene los campos");
						txttecnico.requestFocus();
					} else if(txtcosto.getText().equals("")){
						txtcosto.setBorder(BorderFactory.createLineBorder(Color.RED,1));
						validacioncamp.setText("Llene los campos");
						txtcosto.requestFocus();
					} else {
					Bordes();
					Clean();
				String SQL="INSERT INTO servicio(num_cliente, tiposervicio, plaga,"+
						"fecha_servicio, horas, formapago, clave_tecnico, observaciones, costo, mediocontacto, fecha, valido)"+
									"VALUES(?,?,?,?,?,?,?,?,?,?,Now(),'0')";
							PreparedStatement ps= conn.prepareStatement(SQL);
							ps.setString(1, txtnumcliente.getText());
							ps.setString(2, tiposerv);
							ps.setString(3, plaga);
							ps.setString(4, fecha_serv);
							ps.setInt(5, horas);
							ps.setString(6, forma);
							ps.setString(7, txttecnico.getText());
							ps.setString(8, txtObservaciones.getText());
							ps.setString(9, txtcosto.getText());
							ps.setString(10, medio);	
							int n=ps.executeUpdate();
							if(n>0){
								JOptionPane.showMessageDialog(null, "Solicud de Servicio Completado");
								Clean();
							}
					}				
				
		
			} catch(Exception e) {
				JOptionPane.showMessageDialog(null, "Error: "+e.getMessage());
			}
			}
		});
		btnGuardar.setBounds(600, 371, 89, 23);
		contentPane.add(btnGuardar);
		
		JButton btnOrdenServicio = new JButton("Orden Servicio");
		btnOrdenServicio.setBounds(586, 402, 103, 23);
		contentPane.add(btnOrdenServicio);

		
		cmbBusqueda.addItem("Buscar Por:");
		cmbBusqueda.addItem("Persona");
		cmbBusqueda.addItem("Empresa");
		cmbBusqueda.addItem("Num Cliente");
		cmbBusqueda.addItemListener(new ItemListener() {
			
			public void itemStateChanged(ItemEvent arg0) {
				if(cmbBusqueda.getSelectedIndex()==1){
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
					
					
				} else { if(cmbBusqueda.getSelectedIndex()==2){
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
		});
		
		
		
	  
		Desabilitar();
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
		conn= bd.getConnect();
		
	}
	void Desabilitar(){
		txtdireccion.setEnabled(false);
		txtnumcliente.setEnabled(false);
		txtrfc.setEnabled(false);
		txttelefono.setEnabled(false);
		txtcelular.setEnabled(false);
		
		
	}
	void BuscarEmpresa(){
		try{
			Clean();		
		if(txtNombre.getText().equals("")) {
			 txtNombre.setBorder(BorderFactory.createLineBorder(Color.RED,1));
			 validacioncamp.setText("Introduce la empresa");
			 
		 } else {
		
	
			String[]titulos= {"Num Cliente","Nombre", "Direccion","Referencia","RFC","Empresa","Telefono","Celular","E-Mail","Tipo Cliente"};
			modeltable= new DefaultTableModel(null, titulos);
		
			String consulta="SELECT * FROM clientes WHERE empresa='"+txtNombre.getText()+"'";
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
 void Bordes(){
	txtNombre.setBorder(BorderFactory.createLineBorder(Color.GRAY,1));
	txtPaterno.setBorder(BorderFactory.createLineBorder(Color.GRAY,1));
	txtMaterno.setBorder(BorderFactory.createLineBorder(Color.GRAY,1));
	txttecnico.setBorder(BorderFactory.createLineBorder(Color.GRAY,1));
	validacioncamp.setText("");

	
}
}
