package app;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.Usuario;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;

public class FrmListado extends JFrame {
	
	private JTextArea txtSalida;
	private JPanel contentPane;
	static EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("jpa_sesion02");
	static EntityManager em = fabrica.createEntityManager();
	private JTextField txtUsuario;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmListado frame = new FrmListado();
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
	public FrmListado() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 361);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton btnNewButton = new JButton("Listado");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				listado();
			}
		});
		btnNewButton.setBounds(324, 29, 89, 23);
		contentPane.add(btnNewButton);
		
		txtUsuario = new JTextField();
		txtUsuario.setBounds(122, 30, 161, 20);
		contentPane.add(txtUsuario);
		txtUsuario.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Tipo:");
		lblNewLabel.setBounds(20, 33, 102, 14);
		contentPane.add(lblNewLabel);
		
		JTextArea txtSalida = new JTextArea();
		txtSalida.setBounds(20, 73, 392, 226);
		contentPane.add(txtSalida);
		
	}
	
	
	void listado() {
		// LEER LOS CAMPOS
		String nombre = txtUsuario.getText();
		
		EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("jpa_sesion02");
		EntityManager em = fabrica.createEntityManager();
		
		List<Usuario> lstUsuarios = 
				em.createQuery("select u from Usuario u where u.nom_usua like concat('%',:xtipo,'%')", Usuario.class).
				setParameter("xtipo",nombre).
				getResultList();
			
			for (Usuario u : lstUsuarios) {
				imprimir("Código..: " + u.getCod_usua());
				imprimir("Nombre..: " + u.getNom_usua());
				imprimir("Apellido..: " + u.getApe_usua());
				imprimir("Correo..: " + u.getCorreo());
			}
		
		}
	
		void imprimir(String s){
			txtSalida.append(s + "\n");
		}
	}
