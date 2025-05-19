//VERSÃO DIA 09/06 FUNCIONANDO
package VIEW;

import java.awt.Color;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;
import regras.BotaoJogo;
import regras.ControleBotoes;
import regras.EstadosBotoes;

public class InterfaceMain extends JFrame {

    private static final int QNTD_JOGADAS = 2;
    private int jogadas = 0;
    private Timer timer;

    private int tempoRestante = 60;
    private JLabel labelTempo;

    private JPanel painel;
    private List<BotaoJogo> listaControle;
    private List<BotaoJogo> listaSelecionados;
    private ActionListener acaoBotoes;

    private void encerrarJogoPorTempo() {
        int opcao = JOptionPane.showConfirmDialog(null,
                "Tempo esgotado! Deseja voltar para o menu inicial?",
                "Tempo esgotado", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
        if (opcao == JOptionPane.YES_OPTION) {
            voltarParaMenuInicial();
            dispose();
        } else if (opcao == JOptionPane.NO_OPTION) {
            System.exit(0);
        }
    }

    private void encerrarJogoPorParesEncontrados() {

        int opcao = JOptionPane.showConfirmDialog(null,
                "Parabéns! Todos os pares foram encontrados. Deseja voltar para o menu inicial?",
                "Pares Encontrados", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
        if (opcao == JOptionPane.NO_OPTION) {

            dispose();
            System.exit(0);

        } else if (opcao == JOptionPane.YES_OPTION) {

            dispose();
            voltarParaMenuInicial();
        }
    }

    private void voltarParaMenuInicial() {
        MenuInicial menuInicial = new MenuInicial();
        this.dispose();
    }

    public InterfaceMain() {
        super("Jogo da Memoria");

        listaControle = new ArrayList<>();
        listaSelecionados = new ArrayList<>();

        Timer timerTempo = new Timer(1000, (ActionEvent e) -> {
            tempoRestante--;
            labelTempo.setText("Tempo Restante: " + tempoRestante + " segundos");
            if (tempoRestante <= 0 || tempoRestante == 0) {
                encerrarJogoPorTempo();
            }
        });
        timerTempo.start();

        acaoBotoes = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JButton botao = (JButton) e.getSource();
                BotaoJogo botaoJogo = encontrarBotaoJogo(botao);

                if (botaoJogo != null && botaoJogo.getEstado()
                        == EstadosBotoes.SELECIONADO) {
                    trocarEstadoSeSelecionado(botaoJogo);

                } else if (botaoJogo != null && botaoJogo.getEstado()
                        != EstadosBotoes.PARES_ENCONTRADOS) {
                    trocaEstado(botaoJogo);
                    if (jogadas == QNTD_JOGADAS) {
                        verificarPares();
                    }
                }
            }

            private void trocaEstado(BotaoJogo botaoJogo) {
                jogadas++;
                botaoJogo.setEstado(EstadosBotoes.SELECIONADO);
                listaSelecionados.add(botaoJogo);
                botaoJogo.executarAcao();
            }

            private void trocarEstadoSeSelecionado(BotaoJogo botaoJogo) {
                botaoJogo.setEstado(EstadosBotoes.NORMAL);
                botaoJogo.executarAcao();
                jogadas = 0;
                listaSelecionados.clear();
            }

        };

        timer = new Timer(500, (ActionEvent e) -> {
            resetarSelecoes();
            timer.stop();
        });

        //CONFIG PAINEL
        painel = new JPanel();
        this.add(painel);
        painel.setLayout(null);
        painel.setBackground(new Color(242, 243, 244));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setBounds(250, 250, 540, 580);
        this.setVisible(true);
        this.setLocationRelativeTo(null);

        //LABEL TIMER RESTANTE
        labelTempo = new JLabel("Tempo Restante: " + tempoRestante + " segundos");
        labelTempo.setFont(new Font("Arial", Font.BOLD, 17));
        labelTempo.setBounds(130, 10, 300, 20);
        painel.add(labelTempo);

        novoJogo(8);

    }

    private BotaoJogo encontrarBotaoJogo(JButton botao) {
        for (BotaoJogo botaoJogo : listaControle) {
            if (botaoJogo.getBotao() == botao) {
                return botaoJogo;
            }
        }
        return null;
    }

    private void verificarPares() {
        if (listaSelecionados.size() == QNTD_JOGADAS) {
            BotaoJogo botao1 = listaSelecionados.get(0);
            BotaoJogo botao2 = listaSelecionados.get(1);
            if (botao1.getBotao().getText().equals(botao2.getBotao().getText())) {
                botao1.setEstado(EstadosBotoes.PARES_ENCONTRADOS);
                botao2.setEstado(EstadosBotoes.PARES_ENCONTRADOS);
                botao1.executarAcao();
                botao2.executarAcao();
            }
            if (todosParesEncontrados()) {
                timer.stop();
                encerrarJogoPorParesEncontrados();
            } else {
                timer.start();
            }
        }
    }

    private boolean todosParesEncontrados() {
        for (BotaoJogo botao : listaControle) {
            if (botao.getEstado() != EstadosBotoes.PARES_ENCONTRADOS) {
                return false;
            }
        }
        return true;
    }

    private void resetarSelecoes() {
        for (BotaoJogo botao : listaSelecionados) {
            if (botao.getEstado() != EstadosBotoes.PARES_ENCONTRADOS) {
                botao.setEstado(EstadosBotoes.NORMAL);
                botao.executarAcao();
            }
        }
        jogadas = 0;
        listaSelecionados.clear();
    }

    private void novoJogo(int qtdPares) {
        List<Rectangle> posicionamentos = new ArrayList<>();
        List<String> palavras = criarListaDePalavras(qtdPares);

        int posX = 10;
        int posY = 70;

        for (int i = 0; i < (qtdPares * 2); i++) {

            Rectangle rec = new Rectangle(posX, posY, 115, 100);
            posicionamentos.add(rec);
            if (i % 4 == 3) {
                posX = 10;
                posY += 120;
            } else {
                posX += 130;
            }
        }

        for (int j = 0; j < qtdPares; j++) {
            String palavra = palavras.get(j);
            BotaoJogo botao1 = new ControleBotoes(palavra);
            BotaoJogo botao2 = new ControleBotoes(palavra);

            this.listaControle.add(botao1);
            this.listaControle.add(botao2);

            adicionarBotaoAoPainel(botao1, posicionamentos);
            adicionarBotaoAoPainel(botao2, posicionamentos);
        }
    }

    private void adicionarBotaoAoPainel(BotaoJogo botaoJogo, List<Rectangle> posicionamentos) {
        JButton botao = botaoJogo.getBotao();
        botao.addActionListener(this.acaoBotoes);
        this.painel.add(botao);

        int pos = new Random().nextInt(posicionamentos.size());
        botao.setBounds(posicionamentos.get(pos));
        posicionamentos.remove(pos);
    }

    private List<String> criarListaDePalavras(int qtdPares) {
        List<String> palavrasBase = new ArrayList<>();
        palavrasBase.add("JAVA");
        palavrasBase.add("KOTLIN");
        palavrasBase.add("SQL");
        palavrasBase.add("C#");
        palavrasBase.add("PYTHON");
        palavrasBase.add("RUBY");
        palavrasBase.add("JAVASCRIPT");
        palavrasBase.add("GO");

        List<String> palavras = new ArrayList<>();
        for (int i = 0; i < qtdPares; i++) {
            palavras.add(palavrasBase.get(i));
        }

        Collections.shuffle(palavrasBase);
        return palavras;
    }

}
