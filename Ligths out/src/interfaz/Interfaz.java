package interfaz;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import negocio.Juego;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
//TODO: anda cerrando detalles que tenes la fecha de entrega midiendote los talones
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
			_cantidad_de_movimientos.setText("Movimientos: " + _juego.obtener_cantidad_de_movimientos());
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
	private JButton _nivel_anterior;
	private JButton _volver_al_menu;
	private JButton _ver_mejores_puntuaciones;
	private JTextField _nivel_actual;
	private JTextField _objetivo_de_movimientos;
	private JTextField _cantidad_de_movimientos; 
	
	//pantalla de titulo
	private JTextField _titulo;
	private JButton _modo_clasico;
	private JButton _modo_libre;

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
	}

	private void initialize() 
	{
		iniciar_objeto_juego();	
		iniciarFrame();
		iniciarTituloDelJuego();
		iniciar_boton_de_modo_clasico();
		iniciar_boton_de_modo_libre();
		iniciarBotonDeRecords();
	}

	private void iniciarBotonDeRecords() {
		_ver_mejores_puntuaciones = new JButton("Records");
		_ver_mejores_puntuaciones.setBounds(50, 380, 200, 30);
		_ver_mejores_puntuaciones.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				// TODO agregar lo necesario para que se vea la pantalla de records
			}
			
		});
		_frame.add(_ver_mejores_puntuaciones);
	}

	private void iniciarBotonDeMenu() 
	{
		_volver_al_menu = new JButton("Menu");
		_volver_al_menu.setBounds(100, 490, 100, 20);
		_volver_al_menu.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				limpiarBotones();
				_frame.remove(_nivel_actual);
				_frame.remove(_objetivo_de_movimientos);
				_frame.remove(_cantidad_de_movimientos);
				_frame.remove(_recargar_nivel);
				_frame.remove(_sugerir_movimiento);
				_frame.remove(_volver_al_menu);
				_frame.remove(_mostrar_solucion);
				_frame.remove(_nivel_anterior);
				_frame.remove(_siguiente_nivel);
				_frame.repaint();
				_frame.add(_titulo);
				_frame.add(_modo_clasico);
				_frame.add(_modo_libre);
				_frame.add(_ver_mejores_puntuaciones);
				try 
				{
					_juego.reiniciar_juego();
				} catch (IOException e) 
				{
					e.printStackTrace();
				}
			}
		});
		_frame.add(_volver_al_menu);
	}

	private void iniciar_objeto_juego() 
	{
		try 
		{
			_juego = new Juego();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}	
	}

	private void iniciar_boton_de_modo_libre() 
	{
		_modo_libre = new JButton("Modo libre");
		_modo_libre.setBounds(50, 340, 200, 30);
		_modo_libre.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				_frame.remove(_ver_mejores_puntuaciones);
				_frame.remove(_modo_libre);
				_frame.remove(_modo_clasico);
				_frame.remove(_titulo);
				_frame.repaint();
				iniciarBotonDeMenu();
				iniciarTextoDeNivel();
				iniciarTextoDeObjetivo();
				iniciarTextoDeCantidadMovimientos();
				iniciarBotonDeRecargarNivel();
				iniciarBotonDeSugerirMovimiento();
				iniciarBotonDeMostrarSolucion();
				iniciarBotonDeSiguienteNivel();
				iniciarBotonDeNivelAnterior();
				inicializarBotones();
				actualizarBotones();
			}
		});
		_frame.add(_modo_libre);
	}

	private void iniciar_boton_de_modo_clasico() 
	{
		_modo_clasico = new JButton("Modo clasico");
		_modo_clasico.setBounds(50, 300, 200, 30);
		_modo_clasico.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				_frame.remove(_modo_libre);
				_frame.remove(_modo_clasico);
				_frame.remove(_ver_mejores_puntuaciones);
				_frame.remove(_titulo);
				_frame.repaint();
				iniciarBotonDeMenu();
				iniciarTextoDeNivel();
				iniciarTextoDeObjetivo();
				iniciarTextoDeCantidadMovimientos();
				iniciarBotonDeRecargarNivel();
				iniciarBotonDeSugerirMovimiento();
				inicializarBotones();
				actualizarBotones();
			}
		});
		_frame.add(_modo_clasico);
	}

	private void iniciarTituloDelJuego() 
	{
		_titulo = new JTextField("Ligts out!");
		_titulo.setBounds(0, 100, 300, 90);
		_titulo.setFont(new Font("Times New Roman", Font.PLAIN, 75));
		_titulo.setHorizontalAlignment(SwingConstants.CENTER);
		_titulo.setBackground(Color.GRAY);
		_titulo.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		_titulo.setEditable(false);
		_frame.add(_titulo);
	}

	private void iniciarBotonDeNivelAnterior() 
	{
		_nivel_anterior = new JButton("Anterior");
		_nivel_anterior.setBounds(50, 430, 100, 20);
		_nivel_anterior.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				try 
				{
					_juego.cargar_nivel_especifico((_juego.obtener_nivel_actual()-1));
					actualizar_estado();
				} 
				catch (IOException e) 
				{
					e.printStackTrace();
				} 
				catch (IllegalArgumentException e1)
				{
					try 
					{
						_juego.cargar_nivel_especifico(39);
					} 
					catch (IOException e) 
					{
						e.printStackTrace();
					}
					actualizar_estado();
				}
			}
		});
		_frame.add(_nivel_anterior);
	}

	private void iniciarBotonDeSiguienteNivel() 
	{
		_siguiente_nivel = new JButton("Siguiente");
		_siguiente_nivel.setBounds(150, 430, 100, 20);
		_siguiente_nivel.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				try 
				{
					_juego.cargar_siguiente_nivel();
					actualizar_estado();
				}
				catch (IOException e) 
				{
					e.printStackTrace();
				} 
				catch (NullPointerException e1)
				{
					try 
					{
						_juego.cargar_nivel_especifico(0);
					} 
					catch (IOException e) 
					{
						e.printStackTrace();
					}
					actualizar_estado();
				}
			}
		});
		_frame.add(_siguiente_nivel);
	}

	private void iniciarBotonDeMostrarSolucion() 
	{
		_mostrar_solucion = new JButton("Solucion");
		_mostrar_solucion.setBounds(100, 460, 100, 20);
		_mostrar_solucion.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				ArrayList<String> solucion = _juego.obtener_solucion_del_nivel_actual();
				
				for(String movimiento: solucion)
				{
					_botones[Integer.parseInt(movimiento.charAt(0) + "")][Integer.parseInt(movimiento.charAt(1) + "")].setBorder(BorderFactory.createLineBorder(Color.RED));
				}
				
			}
		});
		_frame.add(_mostrar_solucion);
	}

	private void iniciarBotonDeSugerirMovimiento() 
	{
		_sugerir_movimiento = new JButton("ayuda");
		_sugerir_movimiento.setBounds(150, 400, 100, 20);
		_sugerir_movimiento.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				
				String sugerencia = _juego.obtener_sugerencia_de_movimiento();
				_botones[Integer.parseInt(sugerencia.charAt(0)+"")][Integer.parseInt(sugerencia.charAt(1)+"")].setBorder(BorderFactory.createLineBorder(Color.RED));
				
			}
		});
		_frame.add(_sugerir_movimiento);
	}

	private void iniciarBotonDeRecargarNivel() {
		_recargar_nivel = new JButton("recargar");
		_recargar_nivel.setBounds(50, 400, 100, 20);
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
				_cantidad_de_movimientos.setText("Movimietos: " + _juego.obtener_cantidad_de_movimientos());
			}
		});
		_frame.add(_recargar_nivel);
	}

	private void iniciarTextoDeCantidadMovimientos() 
	{
		_cantidad_de_movimientos = new JTextField("Movimientos: " + _juego.obtener_cantidad_de_movimientos());
		_cantidad_de_movimientos.setHorizontalAlignment(SwingConstants.CENTER);
		_cantidad_de_movimientos.setBounds(195, 50, 100, 40);
		_cantidad_de_movimientos.setEditable(false);
		_cantidad_de_movimientos.setBackground(Color.GRAY);
		_cantidad_de_movimientos.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		_frame.add(_cantidad_de_movimientos);
	}

	private void iniciarTextoDeObjetivo() 
	{
		_objetivo_de_movimientos = new JTextField("Objetivo: " + _juego.obtener_solucion_del_nivel_actual().size());
		_objetivo_de_movimientos.setHorizontalAlignment(SwingConstants.CENTER);
		_objetivo_de_movimientos.setBounds(90, 50, 100, 40);
		_objetivo_de_movimientos.setEditable(false);
		_objetivo_de_movimientos.setBackground(Color.GRAY);
		_objetivo_de_movimientos.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		_frame.add(_objetivo_de_movimientos);
	}

	private void iniciarTextoDeNivel() 
	{
		_nivel_actual = new JTextField("Nivel: " + 1);
		_nivel_actual.setHorizontalAlignment(SwingConstants.CENTER);
		_nivel_actual.setBounds(0, 50, 80, 40);
		_nivel_actual.setEditable(false);
		_nivel_actual.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));
		_nivel_actual.setBackground(Color.GRAY);
		_nivel_actual.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		_frame.add(_nivel_actual);
	}

	private void iniciarFrame() 
	{
		_frame = new JFrame("Ligts out!");
		_frame.setBounds(100, 100, 300, 600);
		_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		_frame.getContentPane().setLayout(null);
		_frame.getContentPane().setBackground(Color.GRAY);
		_frame.setResizable(false);
	}
	
	private void actualizar_estado()
	{
		_frame.repaint();
		limpiarBotones();
		inicializarBotones();
		actualizarBotones();
		_cantidad_de_movimientos.setText("Movimietos: " + _juego.obtener_cantidad_de_movimientos());
		_nivel_actual.setText("Nivel: " + (_juego.obtener_nivel_actual()+1));
		_objetivo_de_movimientos.setText("Objetivo: " + _juego.obtener_solucion_del_nivel_actual().size());
	}

	private void inicializarBotones() 
	{
		_botones = new JButton[_juego.obtener_tamaño_de_tablero()][_juego.obtener_tamaño_de_tablero()];
		int x = ((_frame.getWidth() - (50*_juego.obtener_tamaño_de_tablero()))/2) - (2*(_juego.obtener_tamaño_de_tablero()-2));
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
			if(_juego.obtener_nivel_actual() == 9 && _mostrar_solucion == null) 
			{
				finalizarJuego();
			}
			else 
			{
				if(_juego.obtener_nivel_actual() == 39)
				{
					try {
						_juego.reiniciar_juego();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				else 
				{
					try 
					{
						_juego.cargar_siguiente_nivel();
					} catch (IOException e) 
					{
						e.printStackTrace();
					}
				}
				
				actualizar_estado();
			}
			
		}
	}

	private void limpiarBotones() 
	{
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
		limpiarBotones();
		try 
		{
			_juego.reiniciar_juego();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		_frame.remove(_nivel_actual);
		_frame.remove(_objetivo_de_movimientos);
		_frame.remove(_cantidad_de_movimientos);
		_frame.remove(_recargar_nivel);
		_frame.remove(_sugerir_movimiento);
		_frame.repaint();
		_frame.add(_titulo);
		_frame.add(_modo_clasico);
		_frame.add(_modo_libre);
	}
}
