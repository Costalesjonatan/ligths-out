package interfaz;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableModel;

import baseDeDatos.Records;
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
			actualizarBotonesDelTablero();
			actualizarNivel();
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
	private JButton _modo_clasico;
	private JButton _modo_libre;
	private JTextField _nivel_actual;
	private JTextField _objetivo_de_movimientos;
	private JTextField _cantidad_de_movimientos;
	private JTextField _titulo;
	private JTable _records;
	private JTableHeader _encabezadoDeRecords;

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
		iniciarObjetoJuego();	
		iniciarFrame();
		iniciarTituloDelJuego();
		iniciarTextoDeNivel();
		iniciarTextoDeObjetivo();
		iniciarTextoDeCantidadMovimientos();
		iniciarBotonDeMenu();
		iniciarBotonDeModoClasico();
		iniciarBotonDeModoLibre();
		iniciarBotonDeRecords();
		iniciarBotonDeRecargarNivel();
		iniciarBotonDeSugerirMovimiento();
		iniciarBotonDeMostrarSolucion();
		iniciarBotonDeSiguienteNivel();
		iniciarBotonDeNivelAnterior();
		iniciarTablaDeUltimoRecord();
	}
	
	private void iniciarObjetoJuego() 
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
	private void iniciarFrame() 
	{
		_frame = new JFrame("Ligts out!");
		_frame.setBounds(100, 100, 300, 600);
		_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		_frame.getContentPane().setLayout(null);
		_frame.getContentPane().setBackground(Color.GRAY);
		_frame.setResizable(false);
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
		_nivel_actual.setVisible(false);
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
		_objetivo_de_movimientos.setVisible(false);
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
		_cantidad_de_movimientos.setVisible(false);
	}
	private void iniciarTablaDeUltimoRecord()
	{
		String[] nombresColumnas = {"Nombre", "Nivel", "Movimientos"};
		String[][] datosDeTabla = new String[20][3];
		Records recordsActuales = null;
		try {
			recordsActuales = _juego.cargarRecords();
		} catch (IOException e) {
			e.printStackTrace();
		}
		int j = 0;
		for(int i = 0; i < 20; i+=2)
		{
			datosDeTabla[i][0] = recordsActuales.obtenerNombreDeRecord(j);
			datosDeTabla[i][1] = recordsActuales.obtenerNivelDeRecord(j) + "";
			datosDeTabla[i][2] = recordsActuales.obtenerMovimientosDeRecord(j) + "";
			j++;
		}
		TableModel model = new DefaultTableModel (datosDeTabla, nombresColumnas);
		_records = new JTable(model);
		_records.setBounds(0, 50, 300, 400);
		_records.setBackground(Color.GRAY);
		_records.setShowGrid(false);
		_records.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		_encabezadoDeRecords = _records.getTableHeader();
		_encabezadoDeRecords.setBounds(-10, 20, 300, 20);
		_encabezadoDeRecords.setBackground(Color.GRAY);
		_encabezadoDeRecords.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		_encabezadoDeRecords.setResizingAllowed(false);
		_encabezadoDeRecords.setReorderingAllowed(false);
		_encabezadoDeRecords.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		_frame.add(_records ,BorderLayout.CENTER);
		_frame.add(_encabezadoDeRecords ,BorderLayout.NORTH);
		_encabezadoDeRecords.setVisible(false);
		_records.setVisible(false);
	}
	private void iniciarBotonDeMenu() 
	{
		_volver_al_menu = new JButton("Menu");
		_volver_al_menu.setBounds(100, 490, 100, 20);
		_volver_al_menu.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				if(!_records.isVisible())
				{
					limpiarBotonesDelTablero();
					ocultarInterfazDeJuego();
					try 
					{
						_juego.reiniciar_juego();
					} catch (IOException e) 
					{
						e.printStackTrace();
					}
				}
				_records.setVisible(false);
				_encabezadoDeRecords.setVisible(false);
				mostrarInterfazDelMenu();
			}
		});
		_frame.add(_volver_al_menu);
		_volver_al_menu.setVisible(false);
	}
    private void iniciarBotonDeModoClasico() 
	{
		_modo_clasico = new JButton("Modo clasico");
		_modo_clasico.setBounds(50, 300, 200, 30);
		_modo_clasico.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				ocultarInterfazDelMenu();
				mostrarInterfazDeModoClasico();
				inicializarBotonesDelTablero();
				actualizarBotonesDelTablero();
			}
		});
		_frame.add(_modo_clasico);
	}
	private void iniciarBotonDeModoLibre() 
	{
		_modo_libre = new JButton("Modo libre");
		_modo_libre.setBounds(50, 340, 200, 30);
		_modo_libre.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				ocultarInterfazDelMenu();
				mostrarInterfazDeModoClasico();
				mostrarInterfazDeModoLibre();
				inicializarBotonesDelTablero();
				actualizarBotonesDelTablero();
			}
		});
		_frame.add(_modo_libre);
	}
	private void iniciarBotonDeRecords() 
	{
		_ver_mejores_puntuaciones = new JButton("Records");
		_ver_mejores_puntuaciones.setBounds(50, 380, 200, 30);
		_ver_mejores_puntuaciones.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				_encabezadoDeRecords.setVisible(true);
				_records.setVisible(true);
				ocultarInterfazDelMenu();
				_volver_al_menu.setVisible(true);
			}
		});
		_frame.add(_ver_mejores_puntuaciones);
	}
	private void iniciarBotonDeRecargarNivel() 
	{
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
				actualizarBotonesDelTablero();
				_cantidad_de_movimientos.setText("Movimietos: " + _juego.obtener_cantidad_de_movimientos());
			}
		});
		_frame.add(_recargar_nivel);
		_recargar_nivel.setVisible(false);
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
		_sugerir_movimiento.setVisible(false);
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
		_mostrar_solucion.setVisible(false);
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
					actualizarEstadoDelTablero();
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
					actualizarEstadoDelTablero();
				}
			}
		});
		_frame.add(_siguiente_nivel);
		_siguiente_nivel.setVisible(false);
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
					actualizarEstadoDelTablero();
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
					actualizarEstadoDelTablero();
				}
			}
		});
		_frame.add(_nivel_anterior);
		_nivel_anterior.setVisible(false);
	}

	
	private void mostrarInterfazDelMenu()
	{
		_titulo.setVisible(true);
		_modo_clasico.setVisible(true);
		_modo_libre.setVisible(true);
		_ver_mejores_puntuaciones.setVisible(true);
	}
	private void ocultarInterfazDeJuego()
	{
		_nivel_actual.setVisible(false);
		_objetivo_de_movimientos.setVisible(false);
		_cantidad_de_movimientos.setVisible(false);
		_recargar_nivel.setVisible(false);
		_sugerir_movimiento.setVisible(false);
		_volver_al_menu.setVisible(false);
		if(_mostrar_solucion != null)
		{
			_mostrar_solucion.setVisible(false);
			_nivel_anterior.setVisible(false);
			_siguiente_nivel.setVisible(false);
		}
	}
	private void mostrarInterfazDeModoLibre()
	{
		mostrarInterfazDeModoClasico();
		_sugerir_movimiento.setVisible(true);
		_mostrar_solucion.setVisible(true);
	    _siguiente_nivel.setVisible(true);
		_nivel_anterior.setVisible(true);
	}
	private void mostrarInterfazDeModoClasico()
	{
		_volver_al_menu.setVisible(true);
		_nivel_actual.setVisible(true);
		_objetivo_de_movimientos.setVisible(true);
		_cantidad_de_movimientos.setVisible(true);
		_recargar_nivel.setVisible(true);
		_sugerir_movimiento.setVisible(true);
	}
	private void ocultarInterfazDelMenu() 
	{
		_ver_mejores_puntuaciones.setVisible(false);
		_modo_libre.setVisible(false);
		_modo_clasico.setVisible(false);
		_titulo.setVisible(false);
	}
	private void actualizarTextosDeLaInterfazDelJuego()
	{
		_nivel_actual.setText("Nivel: " + _juego.obtener_nivel_actual());
		_objetivo_de_movimientos.setText("Objetivo: " + _juego.obtener_solucion_del_nivel_actual().size());
		_cantidad_de_movimientos.setText("Movimientos: " + _juego.obtener_cantidad_de_movimientos());
	}
	private void actualizarEstadoDelTablero()
	{
		_frame.repaint();
		limpiarBotonesDelTablero();
		inicializarBotonesDelTablero();
		actualizarBotonesDelTablero();
		_cantidad_de_movimientos.setText("Movimietos: " + _juego.obtener_cantidad_de_movimientos());
		_nivel_actual.setText("Nivel: " + (_juego.obtener_nivel_actual()+1));
		_objetivo_de_movimientos.setText("Objetivo: " + _juego.obtener_solucion_del_nivel_actual().size());
	}

	private void inicializarBotonesDelTablero() 
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
	private void actualizarBotonesDelTablero()
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
	
	private void actualizarNivel()   
	{
		if(_juego.termino_el_nivel())
		{	
			if(_juego.obtener_nivel_actual() == 9 && !_mostrar_solucion.isVisible()) 
			{
				finalizoModoClasico();
			}
			else 
			{
				completoUnNivel();
			}	
		}
	}

	private void completoUnNivel() {
		JOptionPane.showMessageDialog(_frame, "Bien hecho!", "Nivel " + (_juego.obtener_nivel_actual()+1) +  " completo.", 1);
		try 
		{
			_juego.cargar_siguiente_nivel();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (NullPointerException e1)
		{
			try 
			{
				_juego.cargar_nivel_especifico(0);
			} 
			catch (IOException e) 
			{
				e.printStackTrace();
			}
		}
		actualizarEstadoDelTablero();
	}

	private void finalizoModoClasico() {
//		try {
//			_juego.guardarRecord();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
		finalizarJuego();
	}
	private void limpiarBotonesDelTablero() 
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
		JOptionPane.showMessageDialog(_frame, "Felicitaciones, Completaste todos los niveles!", "Modo Clasico Terminado.", 1);
		limpiarBotonesDelTablero();
		try 
		{
//			_juego.guardarRecord();
			_juego.reiniciar_juego();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		actualizarTextosDeLaInterfazDelJuego();
		ocultarInterfazDeJuego();
		mostrarInterfazDelMenu();
	}
}
