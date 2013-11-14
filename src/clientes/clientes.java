package clientes;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.*;
import conexion.bd;
import javax.swing.JTable;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.ListSelectionModel;
import javax.swing.JScrollPane;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.SystemColor;

@SuppressWarnings("unused")
public class clientes extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtNombre;
	private JTextField txtApePaterno;
	private JTextField txtApeMaterno;
	private JTextField txtEmpresa;
	private JTextField txtDireccion;
	private JTextField txtReferencia;
	private JTextField txtTelefono;
	private JTextField txtCelular;
	private JTextField txtMail;
	private JTextField txtrfc;
	private JComboBox<String> cmbTipoCliente;
	public JComboBox<String> cmbBusqueda;
	public JLabel validacion;
	public JButton btnActualizar;
	public JButton btnGuardar;
	public JButton btnEliminar;
	public DefaultTableModel model;
	/**
	 * Launch the application.
	 */
	
	Connection conn;
	Statement sent;
	private JTextField txtbusNombre;
	private JTextField txtbusPaterno;
	private JTextField txtbusMaterno;
	private JTable table;
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					clientes frame = new clientes();
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
	public clientes() {
		setTitle("Killers-Clientes");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 843, 559);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(21, 44, 799, 165);
		panel.setBorder(new TitledBorder(null, "Datos del Cliente", TitledBorder.LEADING, TitledBorder.TOP, null, Color.RED));
		contentPane.add(panel);
		panel.setLayout(null);
		
		final JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setBounds(10, 26, 56, 14);
		panel.add(lblNombre);
		
		txtNombre = new JTextField();
		txtNombre.setBackground(Color.LIGHT_GRAY);
		txtNombre.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent evt) {
				char c=evt.getKeyChar();
				if((c<'a'|| c>'z') &&( c<'A' ||c>'z') && ((c!='ñ' && c!='Ñ') && (c!=(char)KeyEvent.VK_SPACE)))
					evt.consume();
				
			}
			@Override
			public void keyReleased(KeyEvent e) {
				txtNombre.setText(txtNombre.getText().toUpperCase());
			}
		});
		txtNombre.setBounds(70, 23, 188, 20);
		panel.add(txtNombre);
		txtNombre.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Apellido Paterno:");
		lblNewLabel.setBounds(283, 26, 96, 14);
		panel.add(lblNewLabel);
		
		txtApePaterno = new JTextField();
		txtApePaterno.setBackground(Color.LIGHT_GRAY);
		txtApePaterno.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent evt) {
				char c=evt.getKeyChar();
				if(c<'a'|| c>'z' || c<'A' ||c>'z') evt.consume();
			}
			@Override
			public void keyReleased(KeyEvent e) {
			txtApePaterno.setText(txtApePaterno.getText().toUpperCase());
			}
		});
		txtApePaterno.setBounds(399, 23, 117, 20);
		panel.add(txtApePaterno);
		txtApePaterno.setColumns(10);
		
		final JLabel lblApellidoMaterno = new JLabel("Apellido Materno:");
		lblApellidoMaterno.setBounds(540, 23, 105, 14);
		panel.add(lblApellidoMaterno);
		
		txtApeMaterno = new JTextField();
		txtApeMaterno.setBackground(Color.LIGHT_GRAY);
		txtApeMaterno.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent evt) {
				char c= evt.getKeyChar();
				if (c<'a'|| c>'z' || c<'A' ||c>'z') evt.consume();
			}
			@Override
			public void keyReleased(KeyEvent e) {
				txtApeMaterno.setText(txtApeMaterno.getText().toUpperCase());
			}
		});
		txtApeMaterno.setBounds(644, 20, 105, 20);
		panel.add(txtApeMaterno);
		txtApeMaterno.setColumns(10);
		
		JLabel lblEmpresa = new JLabel("Empresa:");
		lblEmpresa.setBounds(10, 64, 56, 14);
		panel.add(lblEmpresa);
		
		txtEmpresa = new JTextField();
		txtEmpresa.setBackground(Color.LIGHT_GRAY);
		txtEmpresa.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
			txtEmpresa.setText(txtEmpresa.getText().toUpperCase());
			}
		});
		txtEmpresa.setBounds(67, 61, 191, 20);
		panel.add(txtEmpresa);
		txtEmpresa.setColumns(10);
		
		JLabel lblDireccion = new JLabel("Direccion");
		lblDireccion.setBounds(283, 61, 55, 14);
		panel.add(lblDireccion);
		
		txtDireccion = new JTextField();
		txtDireccion.setBackground(Color.LIGHT_GRAY);
		txtDireccion.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
			txtDireccion.setText(txtDireccion.getText().toUpperCase());
			}
		});
		txtDireccion.setBounds(348, 58, 382, 20);
		panel.add(txtDireccion);
		txtDireccion.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Referencias:");
		lblNewLabel_1.setBounds(10, 98, 78, 14);
		panel.add(lblNewLabel_1);
		
		txtReferencia = new JTextField();
		txtReferencia.setBackground(Color.LIGHT_GRAY);
		txtReferencia.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
			txtReferencia.setText(txtReferencia.getText().toUpperCase());
			}
		});
		txtReferencia.setBounds(98, 95, 241, 20);
		panel.add(txtReferencia);
		txtReferencia.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Telefono:");
		lblNewLabel_2.setBounds(361, 95, 69, 14);
		panel.add(lblNewLabel_2);
		
		txtTelefono = new JTextField();
		txtTelefono.setBackground(Color.LIGHT_GRAY);
		txtTelefono.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent evt) {
			char c= evt.getKeyChar();
			
			if ((c<'0'|| c>'9') || (txtTelefono.getText().length()==10))

				evt.consume(); 
			}
		});
		txtTelefono.setBounds(440, 92, 86, 20);
		panel.add(txtTelefono);
		txtTelefono.setColumns(10);
		
		JLabel lblCelular = new JLabel("Celular:");
		lblCelular.setBounds(575, 95, 46, 14);
		panel.add(lblCelular);
		
		txtCelular = new JTextField();
		txtCelular.setBackground(Color.LIGHT_GRAY);
		txtCelular.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent evt) {
			char c= evt.getKeyChar();
			
			if ((c<'0'|| c>'9') || (txtTelefono.getText().length()==10))
				evt.consume(); 
							}
		});
		txtCelular.setBounds(620, 92, 86, 20);
		panel.add(txtCelular);
		txtCelular.setColumns(10);
		
		JLabel lblTipoDeCliente = new JLabel("Tipo de Cliente:");
		lblTipoDeCliente.setBounds(10, 137, 86, 14);
		panel.add(lblTipoDeCliente);
		
		JLabel lblEmail = new JLabel("E-Mail:");
		lblEmail.setBounds(269, 137, 46, 14);
		panel.add(lblEmail);
		
		txtMail = new JTextField();
		txtMail.setBackground(Color.LIGHT_GRAY);
		txtMail.setBounds(313, 134, 170, 20);
		panel.add(txtMail);
		txtMail.setColumns(10);
		
		JLabel lblNewRfc = new JLabel("RFC:");
		lblNewRfc.setBounds(504, 134, 56, 14);
		panel.add(lblNewRfc);
		
		txtrfc = new JTextField();
		txtrfc.setBackground(Color.LIGHT_GRAY);
		txtrfc.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
			char c=e.getKeyChar();
			if(c<'0'|| c>'9' || c<'A' || c>'Z' ){
				if(txtrfc.getText().length()==13)
					e.consume();
			}				 
			}
			@Override
			public void keyReleased(KeyEvent e) {
			txtrfc.setText(txtrfc.getText().toUpperCase());
			}
		});
		txtrfc.setBounds(545, 131, 150, 20);
		panel.add(txtrfc);
		txtrfc.setColumns(10);
		
		cmbTipoCliente = new JComboBox<String>();
		cmbTipoCliente.setBackground(Color.LIGHT_GRAY);
		cmbTipoCliente.setBounds(98, 134, 128, 20);
		panel.add(cmbTipoCliente);
		cmbTipoCliente.addItem("CASA-HABITACION");
		cmbTipoCliente.addItem("COMERCIO");
		cmbTipoCliente.addItem("INDUSTRIA");
		final JLabel validacion = new JLabel("");
		validacion.setForeground(Color.RED);
		validacion.setBounds(589, 19, 238, 14);
		contentPane.add(validacion);
		final JButton btnGuardar = new JButton("Guardar");
		btnGuardar.setBounds(296, 10, 89, 23);
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {			
							
				Object combo= cmbTipoCliente.getSelectedItem();
				String tipo= String.valueOf(combo);
			try {
				Bordes();
				validacion.setText(" ");
				if(cmbTipoCliente.getSelectedIndex()==0){
					if(txtNombre.getText().equals("")){
				txtNombre.setBorder(BorderFactory.createLineBorder(Color.RED, 1));
					validacion.setText("Llene los campos");
					}
					if(txtApePaterno.getText().equals("")) {
						txtApePaterno.setBorder(BorderFactory.createLineBorder(Color.RED, 1));
						validacion.setText("Llene los campos");
					}
					if(txtApeMaterno.getText().equals("")) {
						txtApeMaterno.setBorder(BorderFactory.createLineBorder(Color.RED,1));
						}
					if(txtDireccion.getText().equals("")){
						txtDireccion.setBorder(BorderFactory.createLineBorder(Color.red,1)); }
					if(validacion.getText().equals(" ")){
						
						String SQL="INSERT INTO clientes(nombre, apellidopaterno, apellidomaterno,"+
									"empresa, direccion, referencia, telefono, celular, tipocliente, email, rfc)"+
												"VALUES(?,?,?,?,?,?,?,?,?,?,?)";
										PreparedStatement ps= conn.prepareStatement(SQL);
										ps.setString(1, txtNombre.getText());
										ps.setString(2, txtApePaterno.getText());
										ps.setString(3, txtApeMaterno.getText());
										ps.setString(4, txtEmpresa.getText());
										ps.setString(5, txtDireccion.getText());
										ps.setString(6, txtReferencia.getText());
										ps.setString(7, txtTelefono.getText());
										ps.setString(8, txtCelular.getText());
										ps.setString(9,tipo);
										ps.setString(10, txtMail.getText());
										ps.setString(11, txtrfc.getText());
										int n=ps.executeUpdate();
										if(n>0){
											JOptionPane.showMessageDialog(null, "Datos guardados correctamente");
											Clean();
										}
							
							
					
					}
				} else {
						if(txtEmpresa.getText().equals("")){				
							txtEmpresa.setBorder(BorderFactory.createLineBorder(Color.RED,1));
							validacion.setText("Llene los campos");}
						if(txtDireccion.getText().equals("")){
							txtDireccion.setBorder(BorderFactory.createLineBorder(Color.RED,1));
							validacion.setText("Llene los campos");}
						if(txtrfc.getText().equals("")){
						txtrfc.setBorder(BorderFactory.createLineBorder(Color.RED,1));
						validacion.setText("Llene los campos");
						}
						if(validacion.getText().equals(" ")){
							
							String SQL="INSERT INTO clientes(nombre, apellidopaterno, apellidomaterno,"+
										"empresa, direccion, referencia, telefono, celular, tipocliente, email, rfc)"+
													"VALUES(?,?,?,?,?,?,?,?,?,?,?)";
											PreparedStatement ps= conn.prepareStatement(SQL);
											ps.setString(1, txtNombre.getText());
											ps.setString(2, txtApePaterno.getText());
											ps.setString(3, txtApeMaterno.getText());
											ps.setString(4, txtEmpresa.getText());
											ps.setString(5, txtDireccion.getText());
											ps.setString(6, txtReferencia.getText());
											ps.setString(7, txtTelefono.getText());
											ps.setString(8, txtCelular.getText());
											ps.setString(9,tipo);
											ps.setString(10, txtMail.getText());
											ps.setString(11, txtrfc.getText());
											int n=ps.executeUpdate();
											if(n>0){
												JOptionPane.showMessageDialog(null, "Datos guardados correctamente");
												Clean();
											}
								
								
						
						}
				}
			
		
				} catch(SQLException e) {
				JOptionPane.showMessageDialog(null, "Error: "+e.getMessage());
				}
				}	
			}
	 );
		contentPane.add(btnGuardar);
		
		
		final JButton btnActualizar = new JButton("Actualizar");
		btnActualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Actualizar();
			}
		});
		btnActualizar.setBounds(397, 10, 89, 23);
		contentPane.add(btnActualizar);
		
		final JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int fila =table.getSelectedRow();
				try{
					Object [] opciones ={"Aceptar","Cancelar"};
					int eleccion = JOptionPane.showOptionDialog(null,"¿Desea eliminar definitivamente al Cliente?","Mensaje de Confirmacion",
					JOptionPane.YES_NO_OPTION,
					JOptionPane.QUESTION_MESSAGE,null,opciones,"Aceptar");
					if (eleccion == JOptionPane.YES_OPTION){							
					String SQL="DELETE FROM clientes WHERE num_cliente='"+table.getValueAt(fila, 0)+"'";
					sent=conn.createStatement();
					int n=sent.executeUpdate(SQL);
					if(n>0){
						JOptionPane.showMessageDialog(null, "Cliente dado de Baja");
					}
					}
					
				} catch(Exception e){
					JOptionPane.showMessageDialog(null, "Error: "+ e.getMessage());
				}
			}
		});
		btnEliminar.setBounds(493, 10, 89, 23);
		contentPane.add(btnEliminar);
		
		JButton btnNuevo = new JButton("Editar");
		btnNuevo.setBounds(197, 10, 89, 23);
		btnNuevo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Clean();
				btnGuardar.setEnabled(true);
				Habilitar();
			}
		});
		contentPane.add(btnNuevo);
		
		final JLabel lblNombrebus = new JLabel("Nombre:");
		lblNombrebus.setBounds(21, 250, 75, 14);
		contentPane.add(lblNombrebus);
		
		txtbusNombre = new JTextField();
		txtbusNombre.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				txtbusNombre.setText(txtbusNombre.getText().toUpperCase());
			}
		});
		txtbusNombre.setBounds(111, 244, 111, 20);
		contentPane.add(txtbusNombre);
		txtbusNombre.setColumns(10);
		
		final JLabel lblApellidoPaterno = new JLabel("Apellido Paterno: ");
		lblApellidoPaterno.setBounds(232, 247, 104, 14);
		contentPane.add(lblApellidoPaterno);
		
		txtbusPaterno = new JTextField();
		txtbusPaterno.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent evt) {
				char c=evt.getKeyChar();
				if(c<'a'|| c>'z' || c<'A' ||c>'z') evt.consume();
			}
			@Override
			public void keyReleased(KeyEvent e) {
				txtbusPaterno.setText(txtbusPaterno.getText().toUpperCase());
			}
		});
		txtbusPaterno.setBounds(332, 247, 140, 20);
		contentPane.add(txtbusPaterno);
		txtbusPaterno.setColumns(10);
		
		final JLabel lblApellidoMaterno_1 = new JLabel("Apellido Materno:");
		lblApellidoMaterno_1.setBounds(508, 247, 111, 14);
		contentPane.add(lblApellidoMaterno_1);
		
		txtbusMaterno = new JTextField();
		txtbusMaterno.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				txtbusMaterno.setText(txtbusMaterno.getText().toUpperCase());
			}
			@Override
			public void keyTyped(KeyEvent evt) {
				char c=evt.getKeyChar();
				if(c<'a'|| c>'z' || c<'A' ||c>'z') evt.consume();
			}
		});
		txtbusMaterno.setBounds(617, 244, 107, 20);
		contentPane.add(txtbusMaterno);
		txtbusMaterno.setColumns(10);
		
		JLabel lblBuscarPor = new JLabel("Buscar Por:");
		lblBuscarPor.setBounds(10, 218, 91, 14);
		contentPane.add(lblBuscarPor);
		
		final JLabel lblEmpresabus = new JLabel("Empresa:");
		lblEmpresabus.setForeground(Color.BLACK);
		lblEmpresabus.setBounds(21, 250, 89, 14);
		contentPane.add(lblEmpresabus);
		
		final JLabel lblNumCliente = new JLabel("Num Cliente: ");
		lblNumCliente.setBounds(21, 250, 89, 14);
		contentPane.add(lblNumCliente);
		
		final JComboBox<String> cmbBusqueda = new JComboBox<String>();
		cmbBusqueda.setBounds(99, 216, 123, 20);
		contentPane.add(cmbBusqueda);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 298, 810, 165);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setBackground(SystemColor.control);
		table.setFillsViewportHeight(true);
		table.setSurrendersFocusOnKeystroke(true);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent evt) {
				btnActualizar.setEnabled(true);
				btnEliminar.setEnabled(true);
				btnGuardar.setEnabled(false);
				Habilitar();
				if(evt.getButton()==1){
					int fila=table.getSelectedRow();
					try{
						String actualizar="SELECT * FROM clientes WHERE num_cliente='"+table.getValueAt(fila, 0)+"'";
						sent=conn.createStatement();
						ResultSet rs=sent.executeQuery(actualizar);
						rs.next();
						txtNombre.setText(rs.getString("nombre"));
						txtApePaterno.setText(rs.getString("apellidopaterno"));
						txtApeMaterno.setText(rs.getString("apellidomaterno"));
						txtEmpresa.setText(rs.getString("empresa"));
						txtDireccion.setText(rs.getString("direccion"));
						txtReferencia.setText(rs.getString("referencia"));
						txtTelefono.setText(rs.getString("Telefono"));
						cmbTipoCliente.setSelectedItem(rs.getString("tipocliente"));
						txtCelular.setText(rs.getString("celular"));
						txtMail.setText(rs.getString("email"));
						txtrfc.setText(rs.getString("rfc"));
					}catch(Exception e){
						
						
					}
				}
			}
		});
		scrollPane.setViewportView(table);
		
		final JButton btnBuscarEmpresa = new JButton("Buscar");
		btnBuscarEmpresa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				BusquedaTablaEmpresa();
			}
		});
		btnBuscarEmpresa.setBounds(232, 214, 89, 23);
		contentPane.add(btnBuscarEmpresa);
		
		final JButton btnBuscarNum = new JButton("Buscar");
		btnBuscarNum.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BuscarTablaNumero();
			}
		});
		btnBuscarNum.setBounds(230, 214, 89, 23);
		contentPane.add(btnBuscarNum);
		
		final JButton btnBuscarPersona = new JButton("Buscar");
		btnBuscarPersona.setBounds(230, 214, 89, 23);
		contentPane.add(btnBuscarPersona);
		
	
		btnBuscarPersona.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			BusquedaTablaPersona();
			}
		});
		cmbBusqueda.addItem(" ");
		cmbBusqueda.addItem("Persona");
		cmbBusqueda.addItem("Empresa");
		cmbBusqueda.addItem("Num Cliente");
		cmbBusqueda.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				txtbusNombre.setText("");
				txtbusPaterno.setText("");
				txtbusMaterno.setText("");
				if(cmbBusqueda.getSelectedIndex()==1){
					lblNombrebus.setVisible(true);
					lblEmpresabus.setVisible(false);
					lblNumCliente.setVisible(false);
					lblApellidoPaterno.setVisible(true);
					lblApellidoMaterno_1.setVisible(true);
					txtbusPaterno.setVisible(true);
					txtbusMaterno.setVisible(true);
					txtbusNombre.requestFocus();
					btnBuscarPersona.setVisible(true);
					btnBuscarEmpresa.setVisible(false);
					btnBuscarNum.setVisible(false);
				
					
					
				} else {
					if(cmbBusqueda.getSelectedIndex()==2){
						lblNombrebus.setVisible(false);
						lblEmpresabus.setVisible(true);
						lblNumCliente.setVisible(false);
						lblApellidoPaterno.setVisible(false);
						lblApellidoMaterno_1.setVisible(false);
						txtbusPaterno.setVisible(false);
						txtbusMaterno.setVisible(false);
						txtbusNombre.requestFocus();
						btnBuscarPersona.setVisible(false);
						btnBuscarEmpresa.setVisible(true);
						btnBuscarNum.setVisible(false);
						
						
					} else {if(cmbBusqueda.getSelectedIndex()==3){
						lblNombrebus.setVisible(false);
						lblEmpresabus.setVisible(false);
						lblNumCliente.setVisible(true);
						lblApellidoPaterno.setVisible(false);
						lblApellidoMaterno_1.setVisible(false);
						txtbusPaterno.setVisible(false);
						txtbusMaterno.setVisible(false);
						txtbusNombre.requestFocus();
						btnBuscarEmpresa.setVisible(false);
						btnBuscarPersona.setVisible(false);
						btnBuscarNum.setVisible(true);
					
						
					} else {
						lblNombrebus.setVisible(false);
						lblEmpresabus.setVisible(false);
						lblNumCliente.setVisible(false);
						lblApellidoPaterno.setVisible(false);
						lblApellidoMaterno_1.setVisible(false);
						txtbusNombre.setVisible(false);
						txtbusPaterno.setVisible(false);
						txtbusMaterno.setVisible(false);
						btnBuscarPersona.setVisible(false);
						btnBuscarEmpresa.setVisible(false);
						btnBuscarNum.setVisible(false);
						
					}
				}
			
				}
			}
		});
		
		btnGuardar.setEnabled(false);
		btnActualizar.setEnabled(false);
		btnEliminar.setEnabled(false);
		btnBuscarPersona.setVisible(false);
		btnBuscarEmpresa.setVisible(false);
		btnBuscarNum.setVisible(false);
		lblNombrebus.setVisible(true);
		lblEmpresabus.setVisible(false);
		lblNumCliente.setVisible(false);
		lblApellidoPaterno.setVisible(true);
		lblApellidoMaterno_1.setVisible(true);
		Desabilitar();
		conn= bd.getConnect();
		
	}

	void Habilitar(){
		
		txtNombre.setEnabled(true);
		txtApePaterno.setEnabled(true);
		txtApeMaterno.setEnabled(true);
		txtDireccion.setEnabled(true);
		txtTelefono.setEnabled(true);
		txtEmpresa.setEnabled(true);
		txtCelular.setEnabled(true);
		txtMail.setEnabled(true);
		txtrfc.setEnabled(true);
		txtReferencia.setEnabled(true);
		cmbTipoCliente.setEnabled(true);
		txtNombre.requestFocus();
	}
	void Desabilitar(){
	
		
		txtNombre.setEnabled(false);
		txtApePaterno.setEnabled(false);
		txtApeMaterno.setEnabled(false);
		txtDireccion.setEnabled(false);
		txtTelefono.setEnabled(false);
		txtEmpresa.setEnabled(false);
		txtCelular.setEnabled(false);
		txtMail.setEnabled(false);
		txtrfc.setEnabled(false);
		txtReferencia.setEnabled(false);
		cmbTipoCliente.setEnabled(false);
		txtNombre.requestFocus();
	}
	
	void BusquedaTablaPersona(){
	
		try{
		 	btnGuardar.setEnabled(false);
			btnActualizar.setEnabled(false);
			btnEliminar.setEnabled(false);
			Clean();
				if(txtbusNombre.getText().equals("")) {
				txtbusNombre.setBorder(BorderFactory.createLineBorder(Color.RED, 1));
				validacion.setText("Llene los campos"); }
				else {
			
			if(txtbusPaterno.getText().equals("")) {
				txtbusPaterno.setBorder(BorderFactory.createLineBorder(Color.RED, 1));
				validacion.setText("Llene los campos"); }
			else {	
				
			if(txtbusMaterno.getText().equals("")) {
				txtbusMaterno.setBorder(BorderFactory.createLineBorder(Color.RED, 1));
				validacion.setText("Llene los campos");	}		
			
			else { 
		Bordes();		
		String[]titulos= {"Num Cliente","Nombre", "Direccion","Referencia","RFC","Empresa","Telefono","Celular","E-Mail","Tipo Cliente"};
		model= new DefaultTableModel(null, titulos);
		String consulta="SELECT * FROM clientes WHERE nombre='"+txtbusNombre.getText()+"' AND apellidopaterno='"+txtbusPaterno.getText()+"'"+
		"OR apellidomaterno='"+txtbusMaterno.getText()+"'";
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
			model.addRow(regreso);
		}
		table.setModel(model);
			 }
				}
	
	 
		} }catch(Exception e){
			JOptionPane.showMessageDialog(null, "Error: "+e.getMessage());
		
		}
	
		
	}
 void BusquedaTablaEmpresa(){
	 try{
		 Clean();
		 if(txtbusNombre.getText().equals("")) {
			 txtbusNombre.setBorder(BorderFactory.createLineBorder(Color.RED,1));
			 validacion.setText("Introduce la empresa");
			 
		 } else {
			 Bordes();
	
			String[]titulos= {"Num Cliente","Nombre", "Direccion","Referencia","RFC","Empresa","Telefono","Celular","E-Mail","Tipo Cliente"};
			model= new DefaultTableModel(null, titulos);
		
			String consulta="SELECT * FROM clientes WHERE empresa='"+txtbusNombre.getText()+"'";
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
				model.addRow(regreso); 
			}
		
			table.setModel(model);
			
		 }
			} catch(Exception e){
				JOptionPane.showMessageDialog(null, "Error: "+e.getMessage());
			
			}

	 
	 
 }
 
  void BuscarTablaNumero(){
	  try{
			 
			 if(txtbusNombre.getText().equals("")) {
				 txtbusNombre.setBorder(BorderFactory.createLineBorder(Color.RED,1));
				 validacion.setText("Introduce el Numero del Cliente");
				 
			 } else {
	 
				 Clean();
				 Bordes();
				String[]titulos= {"Num Cliente","Nombre", "Direccion","Referencia","RFC","Empresa","Telefono","Celular","E-Mail","Tipo Cliente"};
				model= new DefaultTableModel(null, titulos);
			
				String consulta="SELECT * FROM clientes WHERE num_cliente='"+txtbusNombre.getText()+"' AND apellidopaterno='"+txtbusPaterno.getText()+"' AND apellidomaterno='"+txtbusMaterno.getText()+"'";
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
					model.addRow(regreso); 
				}
			
				table.setModel(model);
				
			 }
				} catch(Exception e){
					JOptionPane.showMessageDialog(null, "Error: "+e.getMessage());
				
				}
  }
  
    void Actualizar(){
    	
    	Object combo= cmbTipoCliente.getSelectedItem();
		String tipo= String.valueOf(combo);

	try {
		String SQL="UPDATE clientes SET nombre=?, apellidopaterno=?, apellidomaterno=?, empresa=?, direccion=?, referencia=?, telefono=?, celular=?, tipocliente=?, email=?, rfc=?"+
	"WHERE num_cliente=?";
		int fila=table.getSelectedRow();
		String dato=(String) table.getValueAt(fila, 0);
		PreparedStatement ps= conn.prepareStatement(SQL);
		ps.setString(1, txtNombre.getText());
		ps.setString(2, txtApePaterno.getText());
		ps.setString(3, txtApeMaterno.getText());
		ps.setString(4, txtEmpresa.getText());
		ps.setString(5, txtDireccion.getText());
		ps.setString(6, txtReferencia.getText());
		ps.setString(7, txtTelefono.getText());
		ps.setString(8, txtCelular.getText());
		ps.setString(9,tipo);
		ps.setString(10, txtMail.getText());
		ps.setString(11, txtrfc.getText());
		ps.setString(12, dato);
		int n=ps.executeUpdate();
		if(n>0){
			JOptionPane.showMessageDialog(null, "Datos actualizados correctamente");
			BuscarTablaNumero();
		}
	} catch(SQLException e) {
		JOptionPane.showMessageDialog(null, "Error: "+e.getMessage());
		
	}	 
	  
  }
      void Bordes(){
    	txtNombre.setBorder(BorderFactory.createLineBorder(Color.GRAY,1));
    	txtApePaterno.setBorder(BorderFactory.createLineBorder(Color.GRAY,1));
    	txtApeMaterno.setBorder(BorderFactory.createLineBorder(Color.GRAY,1));
    	txtDireccion.setBorder(BorderFactory.createLineBorder(Color.GRAY,1));
    	txtEmpresa.setBorder(BorderFactory.createLineBorder(Color.GRAY,1));
    	txtrfc.setBorder(BorderFactory.createLineBorder(Color.GRAY,1));	
    	txtbusNombre.setBorder(BorderFactory.createLineBorder(Color.GRAY,1));	
    	txtbusPaterno.setBorder(BorderFactory.createLineBorder(Color.GRAY,1));	
    	txtbusMaterno.setBorder(BorderFactory.createLineBorder(Color.GRAY,1));	
    }
      
      void Clean(){
    	  txtNombre.setText("");
    	  txtApePaterno.setText("");
    	  txtApeMaterno.setText("");
    	  txtEmpresa.setText("");
    	  txtDireccion.setText("");
    	  txtReferencia.setText("");
    	  txtTelefono.setText("");
    	  txtCelular.setText("");
    	  txtMail.setText("");
    	  txtrfc.setText("");   	  
      }
}
