package br.com.textgame.ui;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextPane;
import javax.swing.ListSelectionModel;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;
import javax.swing.text.StyledDocument;

import br.com.textgame.bss.Combate;
import br.com.textgame.bss.Exploracao;
import br.com.textgame.vo.Area;
import br.com.textgame.vo.AreaContent;
import br.com.textgame.vo.Jogador;
import br.com.textgame.vo.Monstro;

public class UiPrincipal {

	private JFrame frame;
	private JScrollPane scrollPane;
	private JTable tableArea;
	private JPanel panel_2;
	private JTextPane console;
	private List<Area> listaAreas;
	private JButton btnInteragir;
	private JButton btnEscapar;
	private JButton btnExplorar;
	private Jogador jogador = new Jogador(100);
	private Monstro vidaMonstro = new Monstro(120);
	private JScrollPane consoleScrollPane;

	public UiPrincipal() {
		carregarConfigs();
		iniciarLista();
		appendToPane(console, "Bem vindo!\nComece explorando uma Area.", Color.BLUE);
		
		btnInteragir.setEnabled(false);
		btnEscapar.setEnabled(false);
	}
	
	private void carregarConfigs() {
		
		frame = new JFrame();
		frame.setMinimumSize(new Dimension(800, 600));
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("Arquivo");
		mnNewMenu.setActionCommand("");
		menuBar.add(mnNewMenu);
		
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.NORTH);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		panel_2 = new JPanel();
		panel_2.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.add(panel_2);
		panel_2.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		console = new JTextPane();
		console.setPreferredSize(new Dimension(700, 400));
		console.setMinimumSize(new Dimension(700, 400));
		console.setEditable(true);
		
		consoleScrollPane = new JScrollPane(console);
		panel_2.add(consoleScrollPane);
		
		JPanel panel_1 = new JPanel();
		frame.getContentPane().add(panel_1, BorderLayout.SOUTH);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		Component verticalStrut = Box.createVerticalStrut(20);
		verticalStrut.setPreferredSize(new Dimension(0, 40));
		verticalStrut.setMinimumSize(new Dimension(0, 40));
		panel_1.add(verticalStrut, BorderLayout.SOUTH);
		
		JPanel panel_3 = new JPanel();
		panel_1.add(panel_3, BorderLayout.CENTER);
		panel_3.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_5 = new JPanel();
		panel_3.add(panel_5, BorderLayout.NORTH);
		
		JPanel panel_4 = new JPanel();
		panel_5.add(panel_4);
		panel_4.setBorder(new LineBorder(new Color(0, 0, 0)));
		
		btnExplorar = new JButton("Explorar");
		panel_4.add(btnExplorar);
		
		btnInteragir = new JButton("Atacar");
		panel_4.add(btnInteragir);
		
		btnEscapar = new JButton("Escapar");
		panel_4.add(btnEscapar);
		
		btnExplorar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				listaAreas = Exploracao.getListArea();
				consoleScrollPane.setVisible(false);
				
				carregarLista(listaAreas);
				btnExplorar.setEnabled(false);
				
				frame.validate();
				frame.repaint();
			}

		});
		
		btnInteragir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				appendToPane(console,"\n" +Combate.jogadorAtaca(vidaMonstro), Color.green.darker().darker());
				if(vidaMonstro.getVida() <= 0){
					appendToPane(console,"\n" + "Voce matou o monstro! Parabens!", Color.GREEN.darker().darker().darker());
					btnEscapar.setEnabled(false);
					btnInteragir.setEnabled(false);
					btnExplorar.setEnabled(true);
					return;
				}

				appendToPane(console, "\n" +Combate.monstroAtaca(jogador), Color.RED);
				if(jogador.getVida() <= 0){
					appendToPane(console,"\n" + "Voce morreu", Color.RED);
					btnEscapar.setEnabled(false);
					btnInteragir.setEnabled(false);
					return;
				}
				
				System.out.println("jogador: "+jogador.getVida());
				System.out.println("monstro: "+vidaMonstro.getVida());
			}

		});
		
		btnEscapar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				appendToPane(console, "\nVoce escapou...", Color.BLACK);
				
				btnEscapar.setEnabled(false);
				btnInteragir.setEnabled(false);
				btnExplorar.setEnabled(true);
			}

		});
	}
	
	private void iniciarLista() {

		listaAreas = new ArrayList<Area>();

		DefaultTableModel model = criarModelArea(listaAreas);

		tableArea = new JTable();
		tableArea.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2 && !e.isConsumed()) {
					scrollPane.setVisible(false);
					consoleScrollPane.setVisible(true);
					AreaContent a = Exploracao.carregarTextExploracao(listaAreas.get(tableArea.getSelectedRow()));
					
					appendToPane(console, "\n" + a.getDescExploracao(), Color.BLACK);
					
					if(a.getEnemyid() > 0){
						btnInteragir.setEnabled(true);
						btnEscapar.setEnabled(true);
					}else{
						btnExplorar.setEnabled(true);
					}
				}
			}

		});
		tableArea.setModel(model);
		tableArea.setName("Area");
		tableArea.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		scrollPane = new JScrollPane(tableArea);
		scrollPane.setPreferredSize(new Dimension(700, 400));
		scrollPane.setMinimumSize(new Dimension(700, 400));
		
		panel_2.add(scrollPane);

		scrollPane.setVisible(false);
	}

	private void carregarLista(List<Area> listaArea) {
		
		DefaultTableModel model = criarModelArea(listaArea);
		
		tableArea.setModel(model);
		
		tableArea.setName("Area");
		
		tableArea.getColumnModel().getColumn(0).setPreferredWidth(120);
		tableArea.getColumnModel().getColumn(0).setMaxWidth(120);
		tableArea.getColumnModel().getColumn(0).setMinWidth(120);
		
		
		tableArea.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		tableArea.setVisible(true);
		scrollPane.setVisible(true);
		
	}
	
	private DefaultTableModel criarModelArea(List<Area> lista) {
		@SuppressWarnings("serial")
		DefaultTableModel model = new DefaultTableModel()
		{
			public Class<?> getColumnClass(int column)
			{
				switch(column)
				{
				case 0:
					return String.class;
				case 1:
					return String.class;
				default:
					return String.class;
				}
			}
			
			@Override
			public boolean isCellEditable(int rowIndex, int columnIndex)
			{
				return false;
			}
		};

		model.addColumn("Nome");
		model.addColumn("Descricao");

		if(lista != null){
			for (Area area : lista) {
				model.addRow(new Object[]{area.getNome(), area.getDescricao()});
			}
		}
		return model;
	}

	public void setVisible(Boolean b) {
		frame.setVisible(b);
	}
	
	private void appendToPane(JTextPane tp, String msg, Color c) {
        StyleContext sc = StyleContext.getDefaultStyleContext();
        AttributeSet aset = sc.addAttribute(SimpleAttributeSet.EMPTY, StyleConstants.Foreground, c);

        aset = sc.addAttribute(aset, StyleConstants.FontFamily, "Lucida Console");
        aset = sc.addAttribute(aset, StyleConstants.Alignment, StyleConstants.ALIGN_JUSTIFIED);

        StyledDocument doc = tp.getStyledDocument();
        
        try {
			doc.insertString(doc.getLength(), msg,  aset);
		} catch (BadLocationException e) {
			e.printStackTrace();
		}
        
    }

}
