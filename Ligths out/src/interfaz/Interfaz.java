package interfaz;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import negocio.Juego;
import negocio.Solver;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
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
		
			_juego.realizar_movimiento(i, j);
			_contador_de_movimientos++;
			_cantidad_de_movimientos.setText("Movimientos: " + _contador_de_movimientos);
			actualizarBotones();
			try 
			{
				actualizarNivel();
			} catch (InterruptedException e1) 
			{
				e1.printStackTrace();
			}
		}
	}
	
	private JFrame _frame;
	private Juego _juego;
	private JButton[][] _botones;
	private JButton _recargar_nivel;
	private JButton _sugerir_movimiento;
	private JButton _mostrar_solucion;
	private JButton _siguiente_nivel;
	private JTextField _nivel_actual;
	private JTextField _objetivo_de_movimientos;
	private JTextField _cantidad_de_movimientos;
	private int _contador_de_movimientos;

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
		try 
		{
			_juego = new Juego();
		} catch (IOException e) 
		{
			e.printStackTrace();
		}	
		
		_frame = new JFrame();
		_frame.setBounds(100, 100, 300, 600);
		_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		_frame.getContentPane().setLayout(null);
		_frame.getContentPane().setBackground(Color.GRAY);
		_frame.setResizable(false);
		
		_nivel_actual = new JTextField("Nivel: " + 1);
		_nivel_actual.setHorizontalAlignment(SwingConstants.CENTER);
		_nivel_actual.setBounds(0, 50, 80, 40);
		_nivel_actual.setEditable(false);
		_nivel_actual.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));
		_nivel_actual.setBackground(Color.GRAY);
		_nivel_actual.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		_frame.add(_nivel_actual);
		
		_objetivo_de_movimientos = new JTextField("Objetivo: " + Solver.solucion(_juego).size());
		_objetivo_de_movimientos.setHorizontalAlignment(SwingConstants.CENTER);
		_objetivo_de_movimientos.setBounds(90, 50, 100, 40);
		_objetivo_de_movimientos.setEditable(false);
		_objetivo_de_movimientos.setBackground(Color.GRAY);
		_objetivo_de_movimientos.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		_frame.add(_objetivo_de_movimientos);
		
		_contador_de_movimientos = 0;
		
		_cantidad_de_movimientos = new JTextField("Movimientos: " + _contador_de_movimientos);
		_cantidad_de_movimientos.setHorizontalAlignment(SwingConstants.CENTER);
		_cantidad_de_movimientos.setBounds(195, 50, 100, 40);
		_cantidad_de_movimientos.setEditable(false);
		_cantidad_de_movimientos.setBackground(Color.GRAY);
		_cantidad_de_movimientos.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		_frame.add(_cantidad_de_movimientos);
		
		_recargar_nivel = new JButton("recargar");
		_recargar_nivel.setBounds(0, 400, 100, 20);
		_recargar_nivel.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				try 
				{
					_juego.cargar_nivel_especifico(_juego.obtener_nivel_actual());
				} catch (IOException e1) 
				{
					e1.printStackTrace();
				}
				_frame.repaint();
				actualizarBotones();
				_contador_de_movimientos = 0;
				_cantidad_de_movimientos.setText("Movimietos: " + _contador_de_movimientos);
			}
		});
		_frame.add(_recargar_nivel);
		
		_sugerir_movimiento = new JButton("ayuda");
		_sugerir_movimiento.setBounds(100, 400, 100, 20);
		_sugerir_movimiento.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				
				String sugerencia = Solver.sugerir_movmiento(_juego);
				_botones[Integer.parseInt(sugerencia.charAt(0)+"")][Integer.parseInt(sugerencia.charAt(1)+"")].setBorder(BorderFactory.createLineBorder(Color.RED));
				
			}
		});
		_frame.add(_sugerir_movimiento);
		
		_mostrar_solucion = new JButton("Solucion");
		_mostrar_solucion.setBounds(200, 400, 100, 20);
		_mostrar_solucion.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				ArrayList<String> solucion = Solver.solucion(_juego);
				
				for(String movimiento: solucion)
				{
					_botones[Integer.parseInt(movimiento.charAt(0) + "")][Integer.parseInt(movimiento.charAt(1) + "")].setBorder(BorderFactory.createLineBorder(Color.RED));
				}
				
			}
		});
		_frame.add(_mostrar_solucion);
		
		_siguiente_nivel = new JButton("Siguiente");
		_siguiente_nivel.setBounds(100, 430, 100, 20);
		_siguiente_nivel.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				try 
				{
					_juego.cargar_siguiente_nivel();
					_frame.repaint();
					limpiarBotones();
					inicializarBotones();
					actualizarBotones();
					_contador_de_movimientos = 0;
					_cantidad_de_movimientos.setText("Movimietos: " + _contador_de_movimientos);
					_nivel_actual.setText("Nivel: " + (_juego.obtener_nivel_actual()+1));
					_objetivo_de_movimientos.setText("Objetivo: " + Solver.solucion(_juego).size());
				} catch (IOException e) 
				{
					e.printStackTrace();
				}	
			}
		});
		_frame.add(_siguiente_nivel);
	}

	private void inicializarBotones() 
	{
		_botones = new JButton[_juego.obtener_tamaño_de_tablero()][_juego.obtener_tamaño_de_tablero()];
		int x = ((_frame.getWidth() - (50*_juego.obtener_tamaño_de_tablero()))/2) - (2*(_juego.obtener_tamaño_de_tablero()-2));;
		int y = 100;
		
		for(int i = 0; i < _botones.length; i++) 
		{
			for(int j = 0; j < _botones.length; j++) 
			{
				_botones[i][j] = new JButton();
				_botones[i][j].setBounds(x, y, 50, 50);
				_botones[i][j].setEnabled(true);
				_botones[i][j].setText(Integer.toString(i) + Integer.toString(j));
				_botones[i][j].setFont(new Font("Times New Roman", Font.PLAIN, 0));
				_botones[i][j].addActionListener(new localizadorDeClick());
				_frame.getContentPane().add(_botones[i][j]);
				x += 52;
			}
			x = ((_frame.getWidth() - (50*_juego.obtener_tamaño_de_tablero()))/2) - (2*(_juego.obtener_tamaño_de_tablero()-2));
			y += 52;
		}
	}
	
	private void actualizarBotones()
	{	
		for(int i = 0; i < _juego.obtener_tamaño_de_tablero(); i++)
		{
			for(int j = 0; j < _juego.obtener_tamaño_de_tablero(); j++)
			{
				if(_juego.verificar_estado_de_luz(i, j))
				{
					_botones[i][j].setBackground(Color.BLUE);
				}
				else {
					_botones[i][j].setBackground(Color.LIGHT_GRAY);
				}
				_botones[i][j].setBorder(BorderFactory.createLineBorder(Color.BLACK));
			}
		}
	}
	
	private void actualizarNivel() throws InterruptedException  
	{
		if(_juego.termino_el_nivel())
		{	
			_frame.repaint();
//			animacion_fin_nivel();
			try 
			{
				_juego.cargar_siguiente_nivel();
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
			_contador_de_movimientos = 0;
			_cantidad_de_movimientos.setText("Movimietos: " + _contador_de_movimientos);
			_nivel_actual.setText("Nivel: " + (_juego.obtener_nivel_actual()+1));
			_objetivo_de_movimientos.setText("Objetivo: " + Solver.solucion(_juego).size());
		}
	}
	//TODO: tenes que ver como hacer que el sistema haga una pausa
//	private void animacion_fin_nivel() throws InterruptedException
//	{
//		for(int i = 0; i < _botones.length; i++)
//		{
//			for(int j = 0; j < _botones.length; j++)
//			{
//				_botones[i][j].setBackground(Color.GREEN);
//				_botones[i][j].setBackground(Color.LIGHT_GRAY);
//			}
//		}
//	}

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
