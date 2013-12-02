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
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

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
	public DefaultTableModel model;
	/**
	 * Launch the application.
	 */
	
	Connection conn;
	Statement sent;
	private JTextField txtbusNombre;
	private JTextField txtbusPaterno;
	private JTextField txtbusMaterno;
	private JTextField txtciudad;
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
		setBounds(100, 100, 843, 524);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(21, 44, 799, 192);
		panel.setBorder(new TitledBorder(null, "Datos del Cliente", TitledBorder.LEADING, TitledBorder.TOP, null, Color.RED));
		contentPane.add(panel);
		panel.setLayout(null);
		
		final JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setBounds(10, 26, 56, 14);
		panel.add(lblNombre);
		
		txtNombre = new JTextField();
		txtNombre.setBackground(Color.WHITE);
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
		txtApePaterno.setBackground(Color.WHITE);
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
		//DECLARACION DE ELEMENTOS
		final JLabel lblApellidoMaterno = new JLabel("Apellido Materno:");
		txtApeMaterno = new JTextField();
		JLabel lblEmpresa = new JLabel("Empresa:");
		txtEmpresa = new JTextField();
		JLabel lblDireccion = new JLabel("Direccion");
		JLabel lblNewLabel_1 = new JLabel("Referencias:");
		txtReferencia = new JTextField();
		txtDireccion = new JTextField();
		JLabel lblNewLabel_2 = new JLabel("Telefono:");
		txtTelefono = new JTextField();
		final JLabel lblCelular = new JLabel("Celular:");
		txtCelular = new JTextField();
		final JLabel lblTipoDeCliente = new JLabel("Tipo de Cliente:");
		final JLabel lblEmail = new JLabel("E-Mail:");
		txtMail = new JTextField();
		final JLabel lblNewRfc = new JLabel("RFC:");
		cmbTipoCliente = new JComboBox<String>();
		txtrfc = new JTextField();
		txtciudad = new JTextField();
		JLabel lblciudad = new JLabel("Ciudad:");
		final JLabel validacion = new JLabel("");
		final JLabel lblNombrebus = new JLabel("Nombre:");
		txtbusNombre = new JTextField();
		final JButton btnBuscarEmpresa = new JButton("Buscar");
		final JLabel lblApellidoPaterno = new JLabel("Apellido Paterno:");
		txtbusPaterno = new JTextField();
		final JLabel lblApellidoMaterno_1 = new JLabel("Apellido Materno:");
		txtbusMaterno = new JTextField();
		final JLabel lblBuscarPor = new JLabel("Buscar Por:");
		final JLabel lblEmpresabus = new JLabel("Empresa:");
		final JLabel lblNumCliente = new JLabel("Num Cliente:");
		final JComboBox<String> cmbBusqueda = new JComboBox<String>();
		JScrollPane scrollPane = new JScrollPane();
		final JButton btnBuscarNum = new JButton("Buscar");
		final JButton btnBuscarPersona = new JButton("Buscar");
		table = new JTable();		
		panel.add(txtApePaterno);
		txtApePaterno.setColumns(10);		
		lblApellidoMaterno.setBounds(540, 23, 105, 14);
		panel.add(lblApellidoMaterno);		
		txtApeMaterno.setBackground(Color.WHITE);
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
		lblEmpresa.setBounds(10, 64, 56, 14);
		panel.add(lblEmpresa);		
		txtEmpresa.setBackground(Color.WHITE);
		txtEmpresa.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
			txtEmpresa.setText(txtEmpresa.getText().toUpperCase());
			}
		});
		txtEmpresa.setBounds(67, 61, 191, 20);
		panel.add(txtEmpresa);
		txtEmpresa.setColumns(10);
		lblDireccion.setBounds(283, 61, 55, 14);
		panel.add(lblDireccion);
		txtDireccion.setBackground(Color.WHITE);
		txtDireccion.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
			txtDireccion.setText(txtDireccion.getText().toUpperCase());
			}
		});
		txtDireccion.setBounds(348, 58, 382, 20);
		panel.add(txtDireccion);
		txtDireccion.setColumns(10);
		lblNewLabel_1.setBounds(10, 98, 78, 14);
		panel.add(lblNewLabel_1);
		txtReferencia.setBackground(Color.WHITE);
		txtReferencia.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
			txtReferencia.setText(txtReferencia.getText().toUpperCase());
			}
		});
		txtReferencia.setBounds(98, 95, 319, 20);
		panel.add(txtReferencia);
		txtReferencia.setColumns(10);
		lblNewLabel_2.setBounds(427, 98, 69, 14);
		panel.add(lblNewLabel_2);		
		txtTelefono.setBackground(Color.WHITE);
		txtTelefono.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent evt) {
			char c= evt.getKeyChar();	
			if ((c<'0'|| c>'9') || (txtTelefono.getText().length()==10))
				evt.consume(); 
			}
		});
		txtTelefono.setBounds(506, 89, 86, 20);
		panel.add(txtTelefono);
		txtTelefono.setColumns(10);
		lblCelular.setBounds(630, 98, 46, 14);
		panel.add(lblCelular);
		txtCelular.setBackground(Color.WHITE);
		txtCelular.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent evt) {
				char c= evt.getKeyChar();	
				if ((c<'0'|| c>'9') || (txtCelular.getText().length()==10))
					evt.consume(); 
				}
		});
		txtCelular.setBounds(686, 89, 86, 20);
		panel.add(txtCelular);
		txtCelular.setColumns(10);
		lblTipoDeCliente.setBounds(10, 134, 86, 14);
		panel.add(lblTipoDeCliente);
		lblEmail.setBounds(269, 134, 46, 14);
		panel.add(lblEmail);
		txtMail.setBackground(Color.WHITE);
		txtMail.setBounds(313, 131, 170, 20);
		panel.add(txtMail);
		txtMail.setColumns(10);
		lblNewRfc.setBounds(504, 131, 56, 14);
		panel.add(lblNewRfc);
		txtrfc.setBackground(Color.WHITE);
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
		txtrfc.setBounds(545, 128, 150, 20);
		panel.add(txtrfc);
		txtrfc.setColumns(10);
		cmbTipoCliente.setBackground(Color.LIGHT_GRAY);
		cmbTipoCliente.setBounds(98, 131, 128, 20);
		panel.add(cmbTipoCliente);
		txtciudad.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent evt) {
				char c=evt.getKeyChar();
				if((c<'a'|| c>'z') &&( c<'A' ||c>'z') && ((c!='ñ' && c!='Ñ') && (c!=(char)KeyEvent.VK_SPACE)))
					evt.consume();
			}
			@Override
			public void keyReleased(KeyEvent e) {
				txtciudad.setText(txtciudad.getText().toUpperCase());
			}
		});
		txtciudad.setEnabled(false);
		txtciudad.setColumns(10);
		txtciudad.setBackground(Color.WHITE);
		txtciudad.setBounds(108, 162, 170, 20);
		panel.add(txtciudad);
		lblciudad.setBounds(10, 165, 78, 14);
		panel.add(lblciudad);
		final JButton btnGuardar_1 = new JButton("Guardar");
		btnGuardar_1.setBounds(323, 162, 89, 23);
		panel.add(btnGuardar_1);
		btnGuardar_1.addActionListener(new ActionListener() {
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
									"empresa, direccion, referencia, telefono, celular, tipocliente, email, rfc, ciudad)"+
												"VALUES(?,?,?,?,?,?,?,?,?,?,?,?)";
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
										ps.setString(12, txtciudad.getText());
										int n=ps.executeUpdate();
										if(n>0){
											JOptionPane.showMessageDialog(null, "Datos del Cliente Guardados Correctamente");
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
										"empresa, direccion, referencia, telefono, celular, tipocliente, email, rfc,ciudad)"+
													"VALUES(?,?,?,?,?,?,?,?,?,?,?,?)";
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
											ps.setString(12, txtciudad.getText());
											int n=ps.executeUpdate();
											if(n>0){
												JOptionPane.showMessageDialog(null, "Datos del Cliente Guardados Correctamente");
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
		btnGuardar_1.setEnabled(false);
		final JButton btnActualizar_1 = new JButton("Actualizar");
		btnActualizar_1.setBounds(424, 162, 110, 23);
		panel.add(btnActualizar_1);
		btnActualizar_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Actualizar();
			}
		});
		btnActualizar_1.setEnabled(false);
		final JButton btnEliminar_1 = new JButton("Eliminar");
		btnEliminar_1.setBounds(544, 161, 89, 23);
		panel.add(btnEliminar_1);
		btnEliminar_1.addActionListener(new ActionListener() {
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
		btnEliminar_1.setEnabled(false);
		cmbTipoCliente.addItem("CASA-HABITACION");
		cmbTipoCliente.addItem("COMERCIO");
		cmbTipoCliente.addItem("INDUSTRIA");
		validacion.setForeground(Color.RED);
		validacion.setBounds(589, 19, 238, 14);
		contentPane.add(validacion);
		lblNombrebus.setBounds(20, 276, 75, 14);
		contentPane.add(lblNombrebus);
		txtbusNombre.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				txtbusNombre.setText(txtbusNombre.getText().toUpperCase());
			}
		});
		txtbusNombre.setBounds(120, 273, 111, 20);
		contentPane.add(txtbusNombre);
		txtbusNombre.setColumns(10);
		lblApellidoPaterno.setBounds(241, 276, 104, 14);
		contentPane.add(lblApellidoPaterno);
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
		txtbusPaterno.setBounds(341, 273, 140, 20);
		contentPane.add(txtbusPaterno);
		txtbusPaterno.setColumns(10);
		lblApellidoMaterno_1.setBounds(517, 276, 111, 14);
		contentPane.add(lblApellidoMaterno_1);
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
		txtbusMaterno.setBounds(626, 273, 107, 20);
		contentPane.add(txtbusMaterno);
		txtbusMaterno.setColumns(10);		
		lblBuscarPor.setBounds(10, 243, 91, 14);
		contentPane.add(lblBuscarPor);
		lblEmpresabus.setForeground(Color.BLACK);
		lblEmpresabus.setBounds(21, 276, 89, 14);
		contentPane.add(lblEmpresabus);
		lblNumCliente.setBounds(21, 276, 89, 14);
		contentPane.add(lblNumCliente);
		cmbBusqueda.setBounds(99, 241, 123, 20);
		contentPane.add(cmbBusqueda);
		scrollPane.setBounds(10, 309, 810, 140);
		contentPane.add(scrollPane);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent evt) {
                btnActualizar_1.setEnabled(true);
                btnEliminar_1.setEnabled(true);
                btnGuardar_1.setEnabled(false);
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
                                txtciudad.setText(rs.getString("ciudad"));
                                Habilitar();
                        }catch(Exception e){
                        	JOptionPane.showMessageDialog(null, "Error: "+e.getMessage());                                
                        }
                }
        }
				
				
		});
		scrollPane.setViewportView(table);
		btnBuscarEmpresa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				BusquedaTablaEmpresa();
			}
		});
		btnBuscarEmpresa.setBounds(232, 239, 89, 23);
		contentPane.add(btnBuscarEmpresa);
		btnBuscarNum.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BuscarTablaNumero();
			}
		});
		btnBuscarNum.setBounds(230, 239, 89, 23);
		contentPane.add(btnBuscarNum);
		btnBuscarPersona.setBounds(232, 241, 89, 23);
		contentPane.add(btnBuscarPersona);	
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 404, 21);
		contentPane.add(menuBar);
		
		JMenuItem mntmNuevo = new JMenuItem("Nuevo");
		mntmNuevo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setTitle("Killers-Clientes-Nuevo");
				Clean();
				btnActualizar_1.setEnabled(false);
				btnEliminar_1.setEnabled(false);
				btnGuardar_1.setEnabled(true);
				Habilitar();
			}
		});
		menuBar.add(mntmNuevo);
		
		JMenuItem mntmActualizar = new JMenuItem("Actualizar");
		mntmActualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setTitle("Killers-Clientes-Actualizar");
				btnActualizar_1.setEnabled(true);
				btnEliminar_1.setEnabled(false);
				btnGuardar_1.setEnabled(false);
			}
		});
		menuBar.add(mntmActualizar);
		
		JMenuItem mntmEliminar = new JMenuItem("Eliminar");
		mntmEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setTitle("Killers-Clientes-Eliminar");
				btnActualizar_1.setEnabled(false);
				btnEliminar_1.setEnabled(true);
				btnGuardar_1.setEnabled(false);
			}
		});
		menuBar.add(mntmEliminar);
		btnBuscarPersona.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				validacion.setText("");
				setTitle("Killers-Clientes");
				Bordes();
			 	/*btnGuardar.setEnabled(false);
				btnActualizar.setEnabled(false);
				btnEliminar.setEnabled(false);*/
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
				BusquedaTablaPersona();
				}
				}
					}		
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
					txtbusNombre.setVisible(true);
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
						txtbusNombre.setVisible(true);
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
						txtbusNombre.setVisible(true);
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
		txtciudad.setEnabled(true);
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
		txtciudad.setEnabled(false);
		txtNombre.requestFocus();
	}
	
	void BusquedaTablaPersona(){
		try{
		String[]titulos= {"Num Cliente","Nombre","Empresa","Direccion","Referencia","RFC","Telefono","Celular","E-Mail","Tipo Cliente", "Ciudad"};
		model= new DefaultTableModel(null, titulos);
		String consulta="SELECT * FROM clientes WHERE nombre='"+txtbusNombre.getText()+"' OR apellidopaterno='"+txtbusPaterno.getText()+"' OR apellidomaterno='"+txtbusMaterno.getText()+"'";
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
			model.addRow(regreso); 
		}
		table.setModel(model);
			 }
		catch(Exception e){
			JOptionPane.showMessageDialog(null, "Error: "+e.getMessage());
		}	
	}
 void BusquedaTablaEmpresa(){
	 try{
		 validacion.setText("");
		 setTitle("Killers-Clientes");
		 Clean();
		 if(txtbusNombre.getText().equals("")) {
			 txtbusNombre.setBorder(BorderFactory.createLineBorder(Color.RED,1));
			 validacion.setText("Introduce la empresa");
		 } else {
			 Bordes();
			String[]titulos= {"Num Cliente","Nombre","Empresa","Direccion","Referencia","RFC","Telefono","Celular","E-Mail","Tipo Cliente", "Ciudad"};
			model= new DefaultTableModel(null, titulos);
			String consulta="SELECT * FROM clientes WHERE empresa LIKE '%"+txtbusNombre.getText()+"%'";
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
		  setTitle("Killers-Clientes");
		  validacion.setText("");
			 if(txtbusNombre.getText().equals("")) {
				 txtbusNombre.setBorder(BorderFactory.createLineBorder(Color.RED,1));
				 validacion.setText("Introduce el Numero del Cliente");	 
			 } else {
				Clean();
				Bordes();
				String[]titulos= {"Num Cliente","Nombre","Empresa","Direccion","Referencia","RFC","Telefono","Celular","E-Mail","Tipo Cliente", "Ciudad"};
				model= new DefaultTableModel(null, titulos);
				String consulta="SELECT * FROM clientes WHERE num_cliente='"+txtbusNombre.getText()+"' AND apellidopaterno='"+txtbusPaterno.getText()+"' AND apellidomaterno='"+txtbusMaterno.getText()+"'";
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
					model.addRow(regreso); 
				}
				table.setModel(model);
			 }
				} catch(Exception e){
					JOptionPane.showMessageDialog(null, "Error: "+e.getMessage());
				}
  }
    void Actualizar(){
	try {
		String SQL="UPDATE clientes SET nombre=?, apellidopaterno=?, apellidomaterno=?, empresa=?, direccion=?, referencia=?, telefono=?, celular=?, tipocliente=?, email=?, rfc=?, ciudad=?"+
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
		ps.setString(9, String.valueOf(cmbTipoCliente.getSelectedItem()));
		ps.setString(10, txtMail.getText());
		ps.setString(11, txtrfc.getText());
		ps.setString(12, txtciudad.getText());
		ps.setString(13, dato);
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
    	  txtciudad.setText("");
      }
}
