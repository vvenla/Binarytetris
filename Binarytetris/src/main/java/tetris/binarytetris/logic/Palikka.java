/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tetris.binarytetris.logic;

/**
 *
 * @author Venla Viljamaa
 */
public class Palikka {

    private int arvo;

    public Palikka(int arvo) {
        this.arvo = arvo;
    }

    public Palikka() {
        this.arvo = 0;
    }

    public int getArvo() {
        return this.arvo;
    }

    public void setArvo(int arvo) {
        this.arvo = arvo;
    }

    @Override
    public String toString() {
        return "" + this.arvo;
    }

}
