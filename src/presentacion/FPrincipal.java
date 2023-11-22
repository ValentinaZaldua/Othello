package presentacion;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class FPrincipal extends JFrame {
	private PPantalla pPantalla;
	
	public FPrincipal() {
		this.setTitle("Othello POO 2023");
		this.setSize(600,600);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLayout(new BorderLayout());
		
		JButton bIniciar = new JButton("iniciar");
		this.add(bIniciar, BorderLayout.NORTH);
		bIniciar.addActionListener(new ActionListener(){
		
			public void actionPerformed(ActionEvent e) {
				
			}
		
	});
	
	this.pPantalla = new PPantalla();
	this.add(this.pPantalla, BorderLayout.CENTER);
	
	}
	
	public static void main(String[] args) {
		FPrincipal fPrincipal = new FPrincipal();
		fPrincipal.setVisible(true);
		
	}

}
