//VERSÃO DIA 10/06 
package VIEW;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;

import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import javax.swing.JTextArea;

public class MenuInicial extends JFrame {

    private JPanel painel;
    private JFrame frame;
    public JLabel labelMenuInical;

    public MenuInicial() {
        super("Jogo da Memória");

        frame = this;
        painel = new JPanel();

        labelMenuInical = new JLabel("BEM VINDO!");

        //labelMenuInical.setForeground(new Color(41, 27, 36));
        labelMenuInical.setFont(new Font("Monaco", Font.BOLD, 25));
        labelMenuInical.setHorizontalAlignment(JLabel.CENTER);
        labelMenuInical.setBounds(140, 0, 250, 100);

        JTextArea instrucoesJogo = new JTextArea();
        instrucoesJogo.setEditable(false);
        instrucoesJogo.setText("INSTRUÇÕES DO JOGO: \n\n"
                + "1. Oito pares palavras terão de ser encontradas.\n"
                + "2. Clique em duas cartas para revela-las \n"
                + "3. Se as palavras forem iguais, elas ficaram viradas para cima.\n"
                + "4. Caso não sejam iguais, as cartas seram escondidas novamente.\n"
                + "5. Você tera 45 segundos para resolver antes do jogar parar."
                + "Quando estiver pronto clique no botão de 'Iniciar'."
        );

        instrucoesJogo.setFont(new Font("Consolas", Font.PLAIN, 16));
        instrucoesJogo.setBackground(new Color(251, 197, 216));
        instrucoesJogo.setEditable(false);
        instrucoesJogo.setLineWrap(true);
        instrucoesJogo.setWrapStyleWord(true);
        instrucoesJogo.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        instrucoesJogo.setBounds(25, 80, 470, 270);

        painel.add(instrucoesJogo);

        this.add(painel);
        painel.setLayout(null);
        painel.setBackground(new Color(243, 244, 246));

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setBounds(250, 250, 535, 500);
        this.setLocationRelativeTo(null);
        this.setResizable(false);

        criarMenu();
        this.setVisible(true);
    }

    //CRIAÇÃO MENU BOTAO E ADD LABEL E INSTRUÇÕES
    private void criarMenu() {

        //Criação e estilização do botão de iniciar jogo
        JButton botaoIniciarJogo = new JButton();
        botaoIniciarJogo.setText("Iniciar");

        //ESTILIZAÇÃO BOTAO INICIAR
        botaoIniciarJogo.setFont(new Font("Arial", Font.PLAIN, 20));
        botaoIniciarJogo.setForeground(Color.black);
        botaoIniciarJogo.setBackground(new Color(139, 238, 172));
        botaoIniciarJogo.setFocusPainted(false);
        botaoIniciarJogo.setBounds(200, 360, 110, 30);
//        botaoIniciarJogo.setBorderPainted(false);

        //MUDANÇA DE CURSOR QUANDO PASSADO NO BOTAO
        botaoIniciarJogo.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                botaoIniciarJogo.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                botaoIniciarJogo.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }
        });

        botaoIniciarJogo.addActionListener((ActionEvent e) -> {
            InterfaceMain tela = new InterfaceMain();
            frame.dispose();
        });

        //Criação e estilização botao de integrantes do grupo
        JButton botaoGrupoIntegrantes = new JButton("+");

        botaoGrupoIntegrantes.setFont(new Font("Arial", Font.PLAIN, 13));
        botaoGrupoIntegrantes.setForeground(Color.black);
        botaoGrupoIntegrantes.setBackground(new Color(139, 238, 172));
        botaoGrupoIntegrantes.setFocusPainted(false);
        botaoGrupoIntegrantes.setBorderPainted(false);
        botaoGrupoIntegrantes.setBounds(230, 400, 50, 30);

        botaoGrupoIntegrantes.addActionListener((ActionEvent e) -> {
            GrupoIntegrantes();
        });

        botaoGrupoIntegrantes.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                botaoGrupoIntegrantes.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                botaoGrupoIntegrantes.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }
        });

        this.painel.add(botaoGrupoIntegrantes);
        this.painel.add(botaoIniciarJogo);
        this.painel.add(labelMenuInical);

    }

    //Criação do painel de integrantes do grupo
    private void GrupoIntegrantes() {
        //CONFIG PAINEL
        JFrame grupoAlunasPainel = new JFrame("GRUPO");
        grupoAlunasPainel.setSize(500, 400);
        grupoAlunasPainel.setLocationRelativeTo(null);
        grupoAlunasPainel.setLayout(null);
        grupoAlunasPainel.setResizable(false);
        grupoAlunasPainel.setVisible(true);
        grupoAlunasPainel.getContentPane().setBackground(new Color(243, 244, 246));

        //CONFIG LABEL
        JLabel labelTitulo = new JLabel("INTEGRANTES");
        labelTitulo.setFont(new Font("Monaco", Font.BOLD, 25));
        labelTitulo.setHorizontalAlignment(JLabel.CENTER);
        labelTitulo.setBounds(100, 10, 300, 50);

        //CONFIG TEXT AREA
        JTextArea grupoIntegrantes = new JTextArea();
        grupoIntegrantes.setBackground(new Color(251, 197, 216));
        grupoIntegrantes.setEditable(false);
        grupoIntegrantes.setFont(new Font("Consolas", Font.PLAIN, 17));
        grupoIntegrantes.setBounds(20, 60, 450, 200);
        grupoIntegrantes.setText("Lion Carlos Silva Rueda - RA: 12524128180");
        grupoAlunasPainel.add(labelTitulo);
        grupoAlunasPainel.add(grupoIntegrantes);
    }
}
