package regras;
import javax.swing.JButton;

public abstract class BotaoJogo {
    protected String nmBotao;
    protected JButton botao;
    protected EstadosBotoes estado;

    public BotaoJogo(String nmBotao) {
        this.nmBotao = nmBotao;
        this.estado = EstadosBotoes.NORMAL;
        this.botao = new JButton("REVELAR");
    }

    public abstract void executarAcao();

    public JButton getBotao() {
        return botao;
    }

    public void setEstado(EstadosBotoes estado) {
        this.estado = estado;
    }

    public EstadosBotoes getEstado() {
        return this.estado;
    }

    public abstract void alterarVisualizacao();

    
}
