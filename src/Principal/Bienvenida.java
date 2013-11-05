package Principal;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import clientes.clientes;

import Servicios.ordservicio;

public class Bienvenida extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Bienvenida frame = new Bienvenida();
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
	public Bienvenida() {
		setTitle("Killer's");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 775, 485);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 356, 21);
		contentPane.add(menuBar);
		
		JMenuItem mntmServicios = new JMenuItem("SERVICIOS");
		mntmServicios.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				ordservicio servicio= new ordservicio();
				servicio.setVisible(true);
				servicio.setLocationRelativeTo(contentPane);
				
			}
		});
		menuBar.add(mntmServicios);
		
		JMenuItem mntmClientes = new JMenuItem("CLIENTES");
		mntmClientes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clientes vtncliente=new clientes();
				vtncliente.setVisible(true);
				vtncliente.setLocationRelativeTo(contentPane);
				
			}
		});
		menuBar.add(mntmClientes);
		
		JMenuItem mntmGastos = new JMenuItem("GASTOS");
		menuBar.add(mntmGastos);
	}
}
