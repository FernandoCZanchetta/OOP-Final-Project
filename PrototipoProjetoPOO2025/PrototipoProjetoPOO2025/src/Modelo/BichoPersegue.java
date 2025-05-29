package Modelo;

import Auxiliar.Consts;
import Auxiliar.Desenho;
import auxiliar.Posicao;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.io.IOException;
import java.io.Serializable;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class BichoPersegue extends Personagem  implements Serializable{
    private Hero hero;
    private int contador;

    public BichoPersegue(String sNomeImagePNG, Hero h) {
        super(sNomeImagePNG);
        this.hero = h;
    }
    public void autoDesenho(){
        Posicao hp = this.hero.getPosicao();
        
        if(contador == 5) {
            if(hp.getColuna() - pPosicao.getColuna() > 0 && hp.getLinha() - pPosicao.getLinha() > 0) {
                this.setPosicao(pPosicao.getLinha() + 1, pPosicao.getColuna() + 1);
            } else if (hp.getColuna() - pPosicao.getColuna() < 0 && hp.getLinha() - pPosicao.getLinha() < 0) {
                this.setPosicao(pPosicao.getLinha() - 1, pPosicao.getColuna() - 1);
            } else if (hp.getColuna() - pPosicao.getColuna() > 0 && hp.getLinha() - pPosicao.getLinha() < 0) {
                this.setPosicao(pPosicao.getLinha() - 1, pPosicao.getColuna() + 1);
            } else if (hp.getColuna() - pPosicao.getColuna() < 0 && hp.getLinha() - pPosicao.getLinha() > 0) {
                this.setPosicao(pPosicao.getLinha() + 1, pPosicao.getColuna() - 1);
            } else if(hp.getColuna() - pPosicao.getColuna() == 0 && hp.getLinha() - pPosicao.getLinha() > 0) {
                this.setPosicao(pPosicao.getLinha() + 1, pPosicao.getColuna());
            } else if (hp.getColuna() - pPosicao.getColuna() == 0 && hp.getLinha() - pPosicao.getLinha() < 0) {
                this.setPosicao(pPosicao.getLinha() - 1, pPosicao.getColuna());
            } else if (hp.getColuna() - pPosicao.getColuna() > 0 && hp.getLinha() - pPosicao.getLinha() == 0) {
                this.setPosicao(pPosicao.getLinha(), pPosicao.getColuna() + 1);
            } else if (hp.getColuna() - pPosicao.getColuna() < 0 && hp.getLinha() - pPosicao.getLinha() == 0) {
                this.setPosicao(pPosicao.getLinha(), pPosicao.getColuna() - 1);
            }
            contador = 0;
        } else {
            this.setPosicao(pPosicao.getLinha(), pPosicao.getColuna());
        }
        
        
        super.autoDesenho();
        contador++;
    }
}
