/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto2;

/**
 *
 * @author fabba
 */
public class Nodos {
    private int next;
    private String title;
    private String autores;
    private String resumen;
    private String pClave;

    public Nodos(String title, String autores, String resumen, String pClave) {
        this.next = -1;
        this.title = title;
        this.autores = autores;
        this.resumen = resumen;
        this.pClave = pClave;
    }

    /**
     * @return the next
     */
    public int getNext() {
        return next;
    }

    /**
     * @param next the next to set
     */
    public void setNext(int next) {
        this.next = next;
    }

    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return the autores
     */
    public String getAutores() {
        return autores;
    }

    /**
     * @param autores the autores to set
     */
    public void setAutores(String autores) {
        this.autores = autores;
    }

    /**
     * @return the resumen
     */
    public String getResumen() {
        return resumen;
    }

    /**
     * @param resumen the resumen to set
     */
    public void setResumen(String resumen) {
        this.resumen = resumen;
    }

    /**
     * @return the pClave
     */
    public String getpClave() {
        return pClave;
    }

    /**
     * @param pClave the pClave to set
     */
    public void setpClave(String pClave) {
        this.pClave = pClave;
    }
}
