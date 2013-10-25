package clientes;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import java.awt.Color;
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
	/**
	 * Launch the application.
	 */
	
	Connection conn;
	Statement sent;
	private JTable tbtCliente;
	private JTextField txtbusNombre;
	private JTextField txtbusPaterno;
	private JTextField txtbusMaterno;
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
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 695, 536);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Datos del Cliente", TitledBorder.LEADING, TitledBorder.TOP, null, Color.RED));
		panel.setBounds(10, 47, 578, 172);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setBounds(10, 26, 56, 14);
		panel.add(lblNombre);
		
		txtNombre = new JTextField();
		txtNombre.setBounds(70, 23, 86, 20);
		panel.add(txtNombre);
		txtNombre.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Apellido Paterno:");
		lblNewLabel.setBounds(164, 23, 96, 14);
		panel.add(lblNewLabel);
		
		txtApePaterno = new JTextField();
		txtApePaterno.setBounds(270, 20, 86, 20);
		panel.add(txtApePaterno);
		txtApePaterno.setColumns(10);
		
		JLabel lblApellidoMaterno = new JLabel("Apellido Materno:");
		lblApellidoMaterno.setBounds(376, 26, 105, 14);
		panel.add(lblApellidoMaterno);
		
		txtApeMaterno = new JTextField();
		txtApeMaterno.setBounds(480, 23, 86, 20);
		panel.add(txtApeMaterno);
		txtApeMaterno.setColumns(10);
		
		JLabel lblEmpresa = new JLabel("Empresa:");
		lblEmpresa.setBounds(10, 64, 56, 14);
		panel.add(lblEmpresa);
		
		txtEmpresa = new JTextField();
		txtEmpresa.setBounds(67, 61, 117, 20);
		panel.add(txtEmpresa);
		txtEmpresa.setColumns(10);
		
		JLabel lblDireccion = new JLabel("Direccion");
		lblDireccion.setBounds(194, 64, 55, 14);
		panel.add(lblDireccion);
		
		txtDireccion = new JTextField();
		txtDireccion.setBounds(259, 61, 307, 20);
		panel.add(txtDireccion);
		txtDireccion.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Referencias:");
		lblNewLabel_1.setBounds(10, 98, 78, 14);
		panel.add(lblNewLabel_1);
		
		txtReferencia = new JTextField();
		txtReferencia.setBounds(98, 95, 86, 20);
		panel.add(txtReferencia);
		txtReferencia.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Telefono:");
		lblNewLabel_2.setBounds(204, 95, 69, 14);
		panel.add(lblNewLabel_2);
		
		txtTelefono = new JTextField();
		txtTelefono.setBounds(283, 92, 86, 20);
		panel.add(txtTelefono);
		txtTelefono.setColumns(10);
		
		JLabel lblCelular = new JLabel("Celular:");
		lblCelular.setBounds(418, 95, 46, 14);
		panel.add(lblCelular);
		
		txtCelular = new JTextField();
		txtCelular.setBounds(463, 92, 86, 20);
		panel.add(txtCelular);
		txtCelular.setColumns(10);
		
		JLabel lblTipoDeCliente = new JLabel("Tipo de Cliente:");
		lblTipoDeCliente.setBounds(10, 137, 86, 14);
		panel.add(lblTipoDeCliente);
		
		JLabel lblEmail = new JLabel("E-Mail:");
		lblEmail.setBounds(194, 140, 46, 14);
		panel.add(lblEmail);
		
		txtMail = new JTextField();
		txtMail.setBounds(250, 137, 106, 20);
		panel.add(txtMail);
		txtMail.setColumns(10);
		
		JLabel lblNewRfc = new JLabel("RFC:");
		lblNewRfc.setBounds(375, 140, 56, 14);
		panel.add(lblNewRfc);
		
		txtrfc = new JTextField();
		txtrfc.setBounds(416, 137, 150, 20);
		panel.add(txtrfc);
		txtrfc.setColumns(10);
		
		cmbTipoCliente = new JComboBox<String>();
		cmbTipoCliente.setBounds(98, 134, 86, 20);
		panel.add(cmbTipoCliente);
		cmbTipoCliente.addItem("Comercio");
		cmbTipoCliente.addItem("Casa-Habitacion");
		cmbTipoCliente.addItem("Industria");

		
		JButton btnNuevo = new JButton("Nuevo");
		btnNuevo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Habilitar();
			}
		});
		btnNuevo.setBounds(111, 11, 89, 23);
		contentPane.add(btnNuevo);
		
		JButton btnGuardar = new JButton("Guardar");
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Object combo= cmbTipoCliente.getSelectedItem();
				String tipo= String.valueOf(combo);
		
			try {
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
				}
			} catch(SQLException e) {
				JOptionPane.showMessageDialog(null, "Error: "+e.getMessage());
				
			}	
			}
		});
		btnGuardar.setBounds(210, 11, 89, 23);
		contentPane.add(btnGuardar);
		
		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.setBounds(311, 11, 89, 23);
		contentPane.add(btnEliminar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(407, 11, 89, 23);
		contentPane.add(btnCancelar);
		
		tbtCliente = new JTable();
		tbtCliente.setBounds(21, 271, 616, 216);
		contentPane.add(tbtCliente);
		
		JLabel lblNombrebus = new JLabel("Nombre:");
		lblNombrebus.setBounds(20, 243, 46, 14);
		contentPane.add(lblNombrebus);
		
		txtbusNombre = new JTextField();
		txtbusNombre.setBounds(77, 240, 111, 20);
		contentPane.add(txtbusNombre);
		txtbusNombre.setColumns(10);
		
		JLabel lblApellidoPaterno = new JLabel("Apellido Paterno: ");
		lblApellidoPaterno.setBounds(198, 243, 104, 14);
		contentPane.add(lblApellidoPaterno);
		
		txtbusPaterno = new JTextField();
		txtbusPaterno.setBounds(294, 240, 86, 20);
		contentPane.add(txtbusPaterno);
		txtbusPaterno.setColumns(10);
		
		JLabel lblApellidoMaterno_1 = new JLabel("Apellido Materno:");
		lblApellidoMaterno_1.setBounds(390, 243, 89, 14);
		contentPane.add(lblApellidoMaterno_1);
		
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.setBounds(580, 227, 89, 23);
		contentPane.add(btnBuscar);
		
		txtbusMaterno = new JTextField();
		txtbusMaterno.setBounds(477, 240, 86, 20);
		contentPane.add(txtbusMaterno);
		txtbusMaterno.setColumns(10);
		
		JLabel lblBuscarPor = new JLabel("Buscar Por:");
		lblBuscarPor.setBounds(20, 218, 81, 14);
		contentPane.add(lblBuscarPor);
		
		JComboBox cmbBusqueda = new JComboBox();
		cmbBusqueda.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				Object busqueda= cmbTipoCliente.getSelectedItem();
				String combobus= String.valueOf(busqueda);
		
				if (combobus=="Persona"){
			
					
					
									
				}
			}
		});
		cmbBusqueda.setBounds(111, 215, 89, 20);
		contentPane.add(cmbBusqueda);
		cmbBusqueda.addItem("Persona");
		cmbBusqueda.addItem("Empresa");
		cmbBusqueda.addItem("Numero de Cliente");
		
		JLabel lblEmpresabus = new JLabel("Empresa:");
		lblEmpresabus.setBounds(21, 243, 46, 14);
		contentPane.add(lblEmpresabus);
		
		JLabel lblNumCliente = new JLabel("N\u00B0 Cliente: ");
		lblNumCliente.setBounds(21, 243, 46, 14);
		contentPane.add(lblNumCliente);
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
	void Nuevo(){
		
	}
}
