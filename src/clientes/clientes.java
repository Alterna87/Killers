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
import javax.swing.table.DefaultTableModel;
import javax.swing.ListSelectionModel;
import javax.swing.JScrollPane;

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
	public JComboBox cmbBusqueda;
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
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 843, 559);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(21, 44, 578, 165);
		panel.setBorder(new TitledBorder(null, "Datos del Cliente", TitledBorder.LEADING, TitledBorder.TOP, null, Color.RED));
		contentPane.add(panel);
		panel.setLayout(null);
		
		final JLabel lblNombre = new JLabel("Nombre:");
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
		
		final JLabel lblApellidoMaterno = new JLabel("Apellido Materno:");
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
		btnNuevo.setBounds(111, 11, 89, 23);
		btnNuevo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Habilitar();
			}
		});
		contentPane.add(btnNuevo);
		
		JButton btnGuardar = new JButton("Guardar");
		btnGuardar.setBounds(210, 11, 89, 23);
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
		contentPane.add(btnGuardar);
		
		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.setBounds(311, 11, 89, 23);
		contentPane.add(btnEliminar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(407, 11, 89, 23);
		contentPane.add(btnCancelar);
				
		
		final JLabel lblNombrebus = new JLabel("Nombre:");
		lblNombrebus.setBounds(21, 250, 75, 14);
		contentPane.add(lblNombrebus);
		
		txtbusNombre = new JTextField();
		txtbusNombre.setBounds(111, 244, 111, 20);
		contentPane.add(txtbusNombre);
		txtbusNombre.setColumns(10);
		
		final JLabel lblApellidoPaterno = new JLabel("Apellido Paterno: ");
		lblApellidoPaterno.setBounds(232, 247, 104, 14);
		contentPane.add(lblApellidoPaterno);
		
		txtbusPaterno = new JTextField();
		txtbusPaterno.setBounds(328, 244, 86, 20);
		contentPane.add(txtbusPaterno);
		txtbusPaterno.setColumns(10);
		
		final JLabel lblApellidoMaterno_1 = new JLabel("Apellido Materno:");
		lblApellidoMaterno_1.setBounds(424, 247, 96, 14);
		contentPane.add(lblApellidoMaterno_1);
		
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.setBounds(232, 216, 89, 23);
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				BusquedaTabla();
			}
		});
		contentPane.add(btnBuscar);
		
		txtbusMaterno = new JTextField();
		txtbusMaterno.setBounds(518, 244, 107, 20);
		contentPane.add(txtbusMaterno);
		txtbusMaterno.setColumns(10);
		
		JLabel lblBuscarPor = new JLabel("Buscar Por:");
		lblBuscarPor.setBounds(10, 218, 91, 14);
		contentPane.add(lblBuscarPor);
		
		final JLabel lblEmpresabus = new JLabel("Empresa:");
		lblEmpresabus.setBounds(21, 250, 89, 14);
		contentPane.add(lblEmpresabus);
		
		final JLabel lblNumCliente = new JLabel("Num Cliente: ");
		lblNumCliente.setBounds(21, 250, 89, 14);
		contentPane.add(lblNumCliente);
		
		final JComboBox<String> cmbBusqueda = new JComboBox<String>();
		cmbBusqueda.setBounds(99, 216, 123, 20);
		contentPane.add(cmbBusqueda);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(-5, 298, 810, 165);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setFillsViewportHeight(true);
		scrollPane.setViewportView(table);

		
	
		cmbBusqueda.addItem("Persona");
		cmbBusqueda.addItem("Empresa");
		cmbBusqueda.addItem("Num Cliente");
		cmbBusqueda.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if(cmbBusqueda.getSelectedIndex()==0){
					lblNombrebus.setVisible(true);
					lblEmpresabus.setVisible(false);
					lblNumCliente.setVisible(false);
					lblApellidoPaterno.setVisible(true);
					lblApellidoMaterno_1.setVisible(true);
					txtbusPaterno.setVisible(true);
					txtbusMaterno.setVisible(true);
					txtbusNombre.requestFocus();
					
					
				} else {
					if(cmbBusqueda.getSelectedIndex()==1){
						lblNombrebus.setVisible(false);
						lblEmpresabus.setVisible(true);
						lblNumCliente.setVisible(false);
						lblApellidoPaterno.setVisible(false);
						lblApellidoMaterno_1.setVisible(false);
						txtbusPaterno.setVisible(false);
						txtbusMaterno.setVisible(false);
						txtbusNombre.requestFocus();
						
					} else {if(cmbBusqueda.getSelectedIndex()==2){
						lblNombrebus.setVisible(false);
						lblEmpresabus.setVisible(false);
						lblNumCliente.setVisible(true);
						lblApellidoPaterno.setVisible(false);
						lblApellidoMaterno_1.setVisible(false);
						txtbusPaterno.setVisible(false);
						txtbusMaterno.setVisible(false);
						txtbusNombre.requestFocus();
						
					}
				}
			
				}
			}
		});
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
	
	void BusquedaTabla(){
		try{
			String[]titulos= {"Num Cliente","Nombre", "Direccion","Referencia","RFC","Empresa","Telefono","Celular","E-Mail","Tipo Cliente"};
		model= new DefaultTableModel(null, titulos);
		
		String consulta="SELECT * FROM clientes WHERE nombre='"+txtbusNombre.getText()+"'";
		sent=conn.createStatement();
		ResultSet rs= sent.executeQuery(consulta);
		String[] regreso= new String[10];
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
		
		
		} catch(Exception e){
			JOptionPane.showMessageDialog(null, "Error: "+e.getMessage());
		
		}
		
	}
}
