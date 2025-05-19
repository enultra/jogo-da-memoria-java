package regras;

import java.awt.Color;
import java.awt.Font;
import javax.swing.border.LineBorder;

public class ControleBotoes extends BotaoJogo {

    public ControleBotoes(String nmBotao) {
        super(nmBotao);
        this.botao.setFont(new Font("Arial", Font.BOLD, 17));
        this.botao.setBorderPainted(false);
        this.botao.setForeground(new Color(252, 242, 242));
        this.botao.setBackground(new Color(251, 197, 216));
        this.botao.setOpaque(true);
        this.botao.setBorder(new LineBorder(new Color(51, 51, 51)));
    }

    @Override
    public void executarAcao() {
        alterarVisualizacao();
    }

    @Override
    public void alterarVisualizacao() {
        switch (this.estado) {
            case NORMAL:
                botao.setBackground(new Color(251, 197, 216));
                botao.setText("REVELAR");
                botao.setForeground(Color.white);
                botao.setEnabled(true);
                break;
            case SELECIONADO:
                botao.setText(this.nmBotao); 
                botao.setBackground(new Color(245, 136, 177));
                botao.setForeground(Color.white);
                break;
            case PARES_ENCONTRADOS:
                botao.setBackground(new Color(186, 255, 197));
                botao.setText(this.nmBotao);
                botao.setForeground(Color.black);
                botao.setEnabled(false);
                break;
        }
    }
}
