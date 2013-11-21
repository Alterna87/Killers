package Gastos;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JTextFieldDateEditor;
import javax.swing.JComboBox;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.Color;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import conexion.bd;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.AncestorListener;
import javax.swing.event.AncestorEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
public class gastos extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtdiaspagar;
	private JTextField txtpoliza;
	private JTextField txtsubtotal;
	private JTextField txtiva;
	private JTextField txttotal;

	Connection conn;
	Statement sent;
	private JTable table;
	int bandera;
	public DefaultTableModel modeltable;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					gastos frame = new gastos();
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
	public gastos() {
		conn= bd.getConnect();
		bandera=1;
		setTitle("Killers-Gastos");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 506, 440);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);	
		final JLabel lblfechaing = new JLabel("Fecha de Ingreso: ");
		final JLabel lblfechapag = new JLabel("Fecha de Pago: ");
		final JDateChooser fechaingreso = new JDateChooser();
		final JDateChooser fechapago = new JDateChooser();
		final JLabel lblfactura = new JLabel("Factura:");
		final JLabel lblpagado = new JLabel("Pagado:");
		final JComboBox<String> cmbfactura = new JComboBox<String>();
		final JComboBox<String> cmbpagado = new JComboBox<String>();
		final JLabel lblformapag = new JLabel("Forma de Pago:");
		final JComboBox<String> cmbformapago = new JComboBox<String>();
		final JLabel lbldiasapagar = new JLabel("Dias a Pagar:");
		txtdiaspagar = new JTextField();
		final JLabel validar = new JLabel("");
		final JScrollPane scrollPane = new JScrollPane();
		table = new JTable();
		final JLabel lblconcepto = new JLabel("Concepto:");
		final JComboBox<String> cmbconcepto = new JComboBox<String>();
		final JTextArea txtareadescrip = new JTextArea();
		final JLabel lbldescripcion = new JLabel("Descripcion:");
		final JLabel lblpoliza = new JLabel("Poliza:");
		final JLabel lblsubtotal = new JLabel("Subtotal:");
		final JLabel lbliva = new JLabel("IVA(+):");
		final JLabel lbltotal = new JLabel("Total:");
		txtsubtotal = new JTextField();
		txtiva = new JTextField();
		txttotal = new JTextField();
		final JButton btnguardar = new JButton("Guardar");
		final JButton btnBuscar = new JButton("Buscar");
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent evit) {
				if(evit.getButton()==1){					
					int fila=table.getSelectedRow();
				try{
					String buscar="SELECT * FROM gastos WHERE num_gastos='"+table.getValueAt(fila, 0)+"'";
					sent=conn.createStatement();
					ResultSet rs=sent.executeQuery(buscar);
					rs.next();
					Date date = rs.getDate("fecha_pago");
					fechapago.setDate(date);
					cmbformapago.setSelectedItem(rs.getString("formapago"));
					cmbfactura.setSelectedItem(rs.getString("factura"));
					cmbpagado.setSelectedItem(rs.getString("pagado"));
					txtdiaspagar.setText(rs.getString("dias_pagar"));
					txtareadescrip.setText(rs.getString("descripcion"));
					txtpoliza.setText(rs.getString("poliza"));
					txtsubtotal.setText(rs.getString("subtotal"));
					txtiva.setText(rs.getString("iva"));
					txttotal.setText(rs.getString("total"));
					//Ocultando y Mostrando los elementos
					scrollPane.setVisible(false);
					table.setVisible(false);
					lblfechaing.setVisible(true);
					fechaingreso.setVisible(true);
					lblfechapag.setVisible(true);
					fechapago.setVisible(true);
					fechaingreso.setVisible(true);
					lblfactura.setVisible(true);
					cmbfactura.setVisible(true);
					lblpagado.setVisible(true);
					cmbpagado.setVisible(true);
					lblformapag.setVisible(true);
					lbldiasapagar.setVisible(true);
					lblpoliza.setVisible(true);
					lblconcepto.setVisible(true);
					txtpoliza.setVisible(true);
					cmbconcepto.setVisible(true);
					cmbformapago.setVisible(true);
					txtdiaspagar.setVisible(true);
					lbldescripcion.setVisible(true);
					txtareadescrip.setVisible(true);
					lblsubtotal.setVisible(true);
					txtsubtotal.setVisible(true);
					txtiva.setVisible(true);
					lbliva.setVisible(true);
					lbltotal.setVisible(true);
					txttotal.setVisible(true);
					btnguardar.setVisible(true);	
					btnBuscar.setVisible(false);
					
				}catch(Exception e){
					JOptionPane.showMessageDialog(null, "Error: "+e.getMessage());
				}
				}
			}
		});
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int anio= fechaingreso.getCalendar().get(Calendar.YEAR);
				int mes= fechaingreso.getCalendar().get(Calendar.MARCH)+1;
				int dia= fechaingreso.getCalendar().get(Calendar.DAY_OF_MONTH);
				String fechbus= anio+"-"+mes+"-"+dia;
				if(fechaingreso.getDate()==null){
					validar.setText("Introdusca la fecha");
				} else {
					try{
						String[]titulos= {"Num Gastos","Fecha Ingreso","Fecha Pago","Factura","Pagado","Dias a Pagar","Poliza","Forma Pago","Concepto","Descripcion","Subtotal","IVA", "Total"};
						modeltable= new DefaultTableModel(null, titulos);
				
						String consulta="SELECT * FROM gastos WHERE fecha_ingreso='"+fechbus+"'";
						sent=conn.createStatement();
						String[] regreso= new String[13];
						ResultSet rs= sent.executeQuery(consulta);
						while (rs.next()){
							regreso[0]=rs.getString("num_gastos");
							regreso[1]=rs.getString("fecha_ingreso");
							regreso[2]=rs.getString("fecha_pago");
							regreso[3]=rs.getString("factura");
							regreso[4]=rs.getString("pagado");
							regreso[5]=rs.getString("dias_pagar");
							regreso[6]=rs.getString("poliza");
							regreso[7]=rs.getString("formapago");
							regreso[8]=rs.getString("concepto");
							regreso[9]=rs.getString("descripcion");
							regreso[10]=rs.getString("subtotal");
							regreso[11]=rs.getString("iva");
							regreso[12]=rs.getString("total");
							modeltable.addRow(regreso); 
						}
						scrollPane.setVisible(true);
						table.setVisible(true);
						table.setModel(modeltable);
						
					}catch(Exception e){
						JOptionPane.showMessageDialog(null, "Error: "+e.getMessage());
					}
					
				}
			}
					
		});
		validar.setForeground(Color.RED);
		
		
		txtdiaspagar.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent evt) {
				char c=evt.getKeyChar();
				if(c<'0'|| c>'9') evt.consume();
			}
		});
		
		txtpoliza = new JTextField();
		txtpoliza.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent evt) {
				char c=evt.getKeyChar();
				if(c<'0'|| c>'9') evt.consume();
			}
		});
	
		btnguardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(fechaingreso.getDate()==null){
					validar.setText("Ingresar la fecha de Ingreso");					
				}	else if(txttotal.getText().isEmpty()){
					validar.setText("Ingresar el Total");
				}
				else{
					try{
						int anioing= fechaingreso.getCalendar().get(Calendar.YEAR);
					
						int mesing= fechaingreso.getCalendar().get(Calendar.MARCH)+1;
						int diaing= fechaingreso.getCalendar().get(Calendar.DAY_OF_MONTH);
						String fecha_ingreso= anioing+"-"+mesing+"-"+diaing;
						int aniopago=fechapago.getCalendar().get(Calendar.YEAR);
						int mespago=fechapago.getCalendar().get(Calendar.MARCH)+1;
						int diapago=fechapago.getCalendar().get(Calendar.DAY_OF_MONTH);
						String fecha_pago= aniopago+"-"+mespago+"-"+diapago;
						if(bandera==1){
						String SQL="INSERT INTO gastos(fecha_ingreso, fecha_pago,factura ,"+
								"pagado, formapago, concepto, descripcion,dias_pagar,poliza, subtotal, iva, total)"+
											"VALUES(?,?,?,?,?,?,?,?,?,?,?,?)";
									PreparedStatement ps= conn.prepareStatement(SQL);
									ps.setString(1, fecha_ingreso);
									ps.setString(2, fecha_pago);
									ps.setString(3, String.valueOf(cmbfactura.getSelectedItem()));
									ps.setString(4, String.valueOf(cmbpagado.getSelectedItem()));
									ps.setString(5, String.valueOf(cmbformapago.getSelectedItem()));
									ps.setString(6, String.valueOf(cmbconcepto.getSelectedItem()));
									ps.setString(7, txtareadescrip.getText());
									ps.setString(8, txtdiaspagar.getText());
									ps.setString(9, txtpoliza.getText());
									ps.setString(10, txtsubtotal.getText());
									ps.setString(11, txtiva.getText());
									ps.setString(12, txttotal.getText());
									int n=ps.executeUpdate();
									if(n>0){
										JOptionPane.showMessageDialog(null, "Datos de Gastos Guardados");
									}
						}else if(bandera==2){
							String SQL="UPDATE gastos SET fecha_pago=?,factura=?,"+
									"pagado=?, formapago=?, concepto=?, descripcion=?,dias_pagar=?,poliza=?, subtotal=?, iva=?, total=?"+
												"WHERE num_gastos=?";
										int fila=table.getSelectedRow();
										String dato=(String) table.getValueAt(fila, 0);
										PreparedStatement ps= conn.prepareStatement(SQL);
										ps.setString(1, fecha_pago);
										ps.setString(2, String.valueOf(cmbfactura.getSelectedItem()));
										ps.setString(3, String.valueOf(cmbpagado.getSelectedItem()));
										ps.setString(4, String.valueOf(cmbformapago.getSelectedItem()));
										ps.setString(5, String.valueOf(cmbconcepto.getSelectedItem()));
										ps.setString(6, txtareadescrip.getText());
										ps.setString(7, txtdiaspagar.getText());
										ps.setString(8, txtpoliza.getText());
										ps.setString(9, txtsubtotal.getText());
										ps.setString(10, txtiva.getText());
										ps.setString(11, txttotal.getText());
										ps.setString(12, dato);
										int n=ps.executeUpdate();
										if(n>0){
											JOptionPane.showMessageDialog(null, "Datos Actualizados Correctamente");
										}
							
						} else if(bandera==3){
							int fila =table.getSelectedRow();
							try{
								Object [] opciones ={"Aceptar","Cancelar"};
								int eleccion = JOptionPane.showOptionDialog(null,"¿Desea eliminar definitivamente el registro de Gasto?","Mensaje de Confirmacion",
								JOptionPane.YES_NO_OPTION,
								JOptionPane.QUESTION_MESSAGE,null,opciones,"Aceptar");
								if (eleccion == JOptionPane.YES_OPTION){							
								String SQL="DELETE FROM gastos WHERE num_gastos='"+table.getValueAt(fila, 0)+"'";
								sent=conn.createStatement();
								int n=sent.executeUpdate(SQL);
								if(n>0){
									JOptionPane.showMessageDialog(null, "Gastos borrado de la BD");
								}
								}
								
							} catch(Exception e){
								JOptionPane.showMessageDialog(null, "Error: "+ e.getMessage());
							}
						}
								}catch(SQLException e) {
									JOptionPane.showMessageDialog(null, "Error: "+e.getMessage());
								}	 
				} }
			});
		lblfechaing.setBounds(10, 42, 110, 14);
		contentPane.add(lblfechaing);
		lblfechapag.setBounds(10, 71, 98, 14);
		contentPane.add(lblfechapag);		
		fechaingreso.setBounds(130, 34, 93, 20);
		contentPane.add(fechaingreso);
		((JTextFieldDateEditor)fechaingreso.getDateEditor()).setEditable(false);
		fechapago.setBounds(130, 65, 93, 20);
		contentPane.add(fechapago);	
		((JTextFieldDateEditor)fechapago.getDateEditor()).setEditable(false);
		lblfactura.setBounds(289, 37, 84, 14);
		contentPane.add(lblfactura);		
		lblpagado.setBounds(289, 66, 67, 14);
		contentPane.add(lblpagado);	
		cmbfactura.setBounds(366, 34, 67, 20);
		contentPane.add(cmbfactura);
		cmbfactura.addItem("NO");
		cmbfactura.addItem("SI");
		cmbpagado.setBounds(366, 63, 67, 20);
		contentPane.add(cmbpagado);
		cmbpagado.addItem("NO");
		cmbpagado.addItem("SI");
		lblformapag.setBounds(10, 96, 98, 14);
		contentPane.add(lblformapag);
		
		//***MENU****
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 335, 21);
		contentPane.add(menuBar);
		JMenuItem mntmNewMenuItem = new JMenuItem("Nuevo");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				lblfechaing.setVisible(true);
				fechaingreso.setVisible(true);
				lblfechapag.setVisible(true);
				fechapago.setVisible(true);
				fechaingreso.setVisible(true);
				lblfactura.setVisible(true);
				cmbfactura.setVisible(true);
				lblpagado.setVisible(true);
				cmbpagado.setVisible(true);
				lblformapag.setVisible(true);
				lbldiasapagar.setVisible(true);
				lblpoliza.setVisible(true);
				lblconcepto.setVisible(true);
				txtpoliza.setVisible(true);
				cmbconcepto.setVisible(true);
				cmbformapago.setVisible(true);
				txtdiaspagar.setVisible(true);
				lbldescripcion.setVisible(true);
				txtareadescrip.setVisible(true);
				lblsubtotal.setVisible(true);
				txtsubtotal.setVisible(true);
				txtiva.setVisible(true);
				lbliva.setVisible(true);
				lbltotal.setVisible(true);
				txttotal.setVisible(true);
				btnguardar.setVisible(true);
				btnBuscar.setVisible(false);
				btnguardar.setText("Guardar");
				bandera=1;
			}
		});
		menuBar.add(mntmNewMenuItem);	
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Actualizar");
		mntmNewMenuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				lblfechaing.setVisible(true);
				fechaingreso.setVisible(true);
				fechapago.setVisible(false);
				lblfechapag.setVisible(false);
				lblfactura.setVisible(false);
				cmbfactura.setVisible(false);
				lblpagado.setVisible(false);
				cmbpagado.setVisible(false);
				lblformapag.setVisible(false);	
				lbldiasapagar.setVisible(false);
				lblpoliza.setVisible(false);
				lblconcepto.setVisible(false);
				txtpoliza.setVisible(false);
				cmbconcepto.setVisible(false);
				cmbformapago.setVisible(false);
				txtdiaspagar.setVisible(false);
				lbldescripcion.setVisible(false);
				txtareadescrip.setVisible(false);
				lblsubtotal.setVisible(false);
				txtsubtotal.setVisible(false);
				txtiva.setVisible(false);
				lbliva.setVisible(false);
				lbltotal.setVisible(false);
				txttotal.setVisible(false);
				btnguardar.setVisible(false);
				scrollPane.setVisible(false);
				table.setVisible(false);
				btnBuscar.setVisible(true);
				btnguardar.setText("Guardar");
				bandera=2;
			}
		});
		menuBar.add(mntmNewMenuItem_1);
		
		JMenuItem mntmNewMenuItem_2 = new JMenuItem("Eliminar");
		mntmNewMenuItem_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				lblfechaing.setVisible(true);
				fechaingreso.setVisible(true);
				fechapago.setVisible(false);
				lblfechapag.setVisible(false);
				lblfactura.setVisible(false);
				cmbfactura.setVisible(false);
				lblpagado.setVisible(false);
				cmbpagado.setVisible(false);
				lblformapag.setVisible(false);	
				lbldiasapagar.setVisible(false);
				lblpoliza.setVisible(false);
				lblconcepto.setVisible(false);
				txtpoliza.setVisible(false);
				cmbconcepto.setVisible(false);
				cmbformapago.setVisible(false);
				txtdiaspagar.setVisible(false);
				lbldescripcion.setVisible(false);
				txtareadescrip.setVisible(false);
				lblsubtotal.setVisible(false);
				txtsubtotal.setVisible(false);
				txtiva.setVisible(false);
				lbliva.setVisible(false);
				lbltotal.setVisible(false);
				txttotal.setVisible(false);
				btnguardar.setVisible(false);
				btnguardar.setText("Eliminar");
				scrollPane.setVisible(false);
				table.setVisible(false);
				btnBuscar.setVisible(true);
				bandera=3;
			}
		});
		menuBar.add(mntmNewMenuItem_2);
		cmbformapago.setBounds(114, 93, 121, 20);
		contentPane.add(cmbformapago);
		cmbformapago.addItem("CHEQUE");
		cmbformapago.addItem("TRANSFERENCIA");
		cmbformapago.addItem("TARJETA");
		cmbformapago.addItem("CAJA REMISION");
		lbldiasapagar.setBounds(289, 99, 84, 14);
		contentPane.add(lbldiasapagar);		
		
		txtdiaspagar.setBounds(366, 94, 77, 20);
		contentPane.add(txtdiaspagar);
		txtdiaspagar.setColumns(10);
				
		lblpoliza.setBounds(289, 127, 46, 14);
		contentPane.add(lblpoliza);
				
		txtpoliza.setBounds(366, 124, 77, 20);
		contentPane.add(txtpoliza);
		txtpoliza.setColumns(10);
		
		lblconcepto.setBounds(10, 127, 84, 14);
		contentPane.add(lblconcepto);
		
		cmbconcepto.setBounds(114, 124, 121, 20);
		contentPane.add(cmbconcepto);
		cmbconcepto.addItem("OPERACION");
		cmbconcepto.addItem("MANTENIMIENTO");
		cmbconcepto.addItem("PAGOS FIJOS");
		cmbconcepto.addItem("EVENTUAL");
		cmbconcepto.addItem("FELIPE");
		cmbconcepto.addItem("CREDITO");
		cmbconcepto.addItem("PRESTAMO");
			
		txtareadescrip.setBounds(104, 173, 341, 76);
		contentPane.add(txtareadescrip);
		txtareadescrip.setLineWrap(true);	
		
		lbldescripcion.setBounds(10, 178, 84, 14);
		contentPane.add(lbldescripcion);
		
		lblsubtotal.setBounds(307, 270, 66, 14);
		contentPane.add(lblsubtotal);	
		
		lbliva.setBounds(307, 295, 46, 14);
		contentPane.add(lbliva);
		
		lbltotal.setBounds(307, 320, 46, 14);
		contentPane.add(lbltotal);
				
		txtsubtotal.setBounds(380, 265, 57, 20);
		contentPane.add(txtsubtotal);
		txtsubtotal.setColumns(10);
		
		txtiva.setColumns(10);
		txtiva.setBounds(380, 290, 57, 20);
		contentPane.add(txtiva);
		
		txttotal.setColumns(10);
		txttotal.setBounds(380, 317, 57, 20);
		contentPane.add(txttotal);
		
		btnguardar.setBounds(69, 291, 89, 23);
		contentPane.add(btnguardar);
		
		validar.setBounds(289, 12, 156, 14);
		contentPane.add(validar);
		
		scrollPane.setBounds(20, 67, 448, 263);
		contentPane.add(scrollPane);
		
		scrollPane.setViewportView(table);
		btnBuscar.setBounds(233, 33, 89, 23);
		contentPane.add(btnBuscar);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table.doLayout();
		
		
		//**PRINCIPAL**
		lblfechaing.setVisible(false);
		fechaingreso.setVisible(false);
		fechapago.setVisible(false);
		lblfechapag.setVisible(false);
		fechaingreso.setVisible(false);
		lblfactura.setVisible(false);
		cmbfactura.setVisible(false);
		lblpagado.setVisible(false);
		cmbpagado.setVisible(false);
		lblformapag.setVisible(false);	
		lbldiasapagar.setVisible(false);
		lblpoliza.setVisible(false);
		lblconcepto.setVisible(false);
		txtpoliza.setVisible(false);
		cmbconcepto.setVisible(false);
		cmbformapago.setVisible(false);
		txtdiaspagar.setVisible(false);
		lbldescripcion.setVisible(false);
		txtareadescrip.setVisible(false);
		lblsubtotal.setVisible(false);
		txtsubtotal.setVisible(false);
		txtiva.setVisible(false);
		lbliva.setVisible(false);
		lbltotal.setVisible(false);
		txttotal.setVisible(false);
		btnguardar.setVisible(false);
		scrollPane.setVisible(false);
		table.setVisible(false);
		btnBuscar.setVisible(false);
		
		
		
	}
}
