package Gastos;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
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
import java.util.Calendar;

import conexion.bd;
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
		validar.setForeground(Color.RED);
		
		
		
		txtdiaspagar.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent evt) {
				char c=evt.getKeyChar();
				if(c<'0'|| c>'9') evt.consume();
			}
		});
		final JLabel lblpoliza = new JLabel("Poliza:");
		txtpoliza = new JTextField();
		txtpoliza.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent evt) {
				char c=evt.getKeyChar();
				if(c<'0'|| c>'9') evt.consume();
			}
		});
		final JLabel lblconcepto = new JLabel("Concepto:");
		final JComboBox<String> cmbconcepto = new JComboBox<String>();
		final JTextArea txtareadescrip = new JTextArea();
		final JLabel lbldescripcion = new JLabel("Descripcion:");
		final JLabel lblsubtotal = new JLabel("Subtotal:");
		final JLabel lbliva = new JLabel("IVA(+):");
		final JLabel lbltotal = new JLabel("Total:");
		txtsubtotal = new JTextField();
		txtiva = new JTextField();
		txttotal = new JTextField();
		final JButton btnguardar = new JButton("Guardar");
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
										JOptionPane.showMessageDialog(null, "Datos de Servicio Guardados");
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
		menuBar.setBounds(0, 0, 279, 21);
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
				
			}
		});
		menuBar.add(mntmNewMenuItem);	
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Actualizar");
		menuBar.add(mntmNewMenuItem_1);
		
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
		
		
		
	}
}
