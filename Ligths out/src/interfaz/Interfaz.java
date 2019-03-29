package interfaz;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import negocio.Tablero;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;

public class Interfaz 
{
	
	private class localizadorDeClick implements ActionListener
	{
		public void actionPerformed(ActionEvent e) 
		{
			char posicion_i = e.getActionCommand().charAt(0);
			char posicion_j = e.getActionCommand().charAt(1);
			
			int i = Integer.parseInt("" + posicion_i);
			int j = Integer.parseInt("" + posicion_j);
		
			_tablero.realizarMoviento(i, j);
			actualizarBotones();
			actualizarNivel();
		}
	}
	
	private JFrame _frame;
	private JButton[][] _botones;
	private Tablero _tablero;

	public static void main(String[] args) 
	{
		EventQueue.invokeLater(new Runnable() 
		{
			public void run() 
			{
				try 
				{
					Interfaz window = new Interfaz();
					window._frame.setVisible(true);
					
				} catch (Exception e) 
				{
					e.printStackTrace();
				}
			}
		});
	}

	public Interfaz() 
	{
		initialize();
		inicializarBotones();
		actualizarBotones();
		
	}

	private void initialize() 
	{
		_frame = new JFrame();
		_frame.setBounds(100, 100, 600, 600);
		_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		_frame.getContentPane().setLayout(null);
		
		try {
			_tablero = new Tablero();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}

	private void inicializarBotones() 
	{
		_botones = new JButton[_tablero.getTamaño()][_tablero.getTamaño()];
		int x = 220;
		int y = 170;
		
		for(int i = 0; i < _botones.length; i++) 
		{
			for(int j = 0; j < _botones.length; j++) 
			{
				_botones[i][j] = new JButton();
				_botones[i][j].setBounds(x, y, 40, 40);
				_botones[i][j].setEnabled(true);
				_botones[i][j].setText(Integer.toString(i) + Integer.toString(j));
				_botones[i][j].setFont(new Font("Times New Roman", Font.PLAIN, 0));
				_botones[i][j].addActionListener(new localizadorDeClick());
				_frame.getContentPane().add(_botones[i][j]);
				x += 50;
			}
			x = 220;
			y += 50;
		}
	}
	
	private void actualizarBotones()
	{	
		for(int i = 0; i < _tablero.getTamaño(); i++)
		{
			for(int j = 0; j < _tablero.getTamaño(); j++)
			{
				if(_tablero.estaEncendida(i, j))
				{
					_botones[i][j].setBackground(Color.YELLOW);
				}
				else {
					_botones[i][j].setBackground(Color.LIGHT_GRAY);
				}
			}
		}
	}
	
	private void actualizarNivel() 
	{
		if(_tablero.apagoTodasLasLuces())
		{	
			try 
			{
				_tablero.cambiarNivel();
			} catch (IOException e) 
			{
				e.printStackTrace();
			} catch (NullPointerException e)
			{
				finalizarJuego();
			}
			limpiarBotones();
			inicializarBotones();
			actualizarBotones();
			
		}
	}

	private void limpiarBotones() {
		for(int i = 0; i < _botones.length; i++) 
		{
			for(int j = 0; j < _botones.length; j++) 
			{
				_frame.remove(_botones[i][j]);
				_botones[i][j] = null;
			}
		}
	}
	
	private void finalizarJuego()
	{
		JOptionPane.showMessageDialog(null, "Felicitaciones, Completaste todos los niveles!");
		_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		System.exit(0); 
	}
}
