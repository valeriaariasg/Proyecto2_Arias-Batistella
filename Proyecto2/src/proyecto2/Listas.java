/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto2;

import javax.swing.JOptionPane;

/**
 *
 * @author fabba
 */
public class Listas {
    private int first;
    private int last;
    private int size;
    private Nodos[] array;
    
    public Listas() {
        this.first = this.last = -1;
        this.size = 0;
        this.array = new Nodos[26];
    }
    
    public boolean isEmpty(){
        return first == -1;
    }
    
    public boolean isFull(){
        return size == this.array.length;
    }
    
    public void agg(String titulo, String autores, String resumen, String pClaves){
        if(!this.isFull()){
            int ascii = titulo.charAt(0) -65;
            Nodos nodito = new Nodos(titulo, autores, resumen, pClaves);
            int position = this.espacioV1(ascii);
            this.array[position] = nodito;
            if(this.isEmpty()){
                this.first = this.last = position;
            }else if(this.last < position){
                this.array[this.last].setNext(position);
                this.last = position;
            }else if(this.first > position){
                this.array[position].setNext(this.first);
                this.first = position;
            }else{
                int aux = buscar(position);
                this.array[position].setNext(this.array[aux].getNext());
                this.array[aux].setNext(position);
            }
            size++;
        }else{
            JOptionPane.showMessageDialog(null, "No se pueden añadir más");
        }
    }
    
    public int buscar(int nuevo){
        int aux = this.first;
        for(int i = 0; i < this.size; i++){
            if(array[aux].getNext() > nuevo){
                return aux;
            }
            aux = array[aux].getNext();
        }
        return -1;
    }
    
    public int espacioV1(int pos){
        if(this.array[pos]==null){
            return pos;
        }else{
            if(pos == 25){
                int aux = espacioV2(pos-1);
                return aux;
            }else{
                int aux = espacioV1(pos+1);
                return aux;
            }
        }
    }
    
    public int espacioV2(int pos){
        if(this.array[pos]==null){
            return pos;
        }else{
            if(pos == 0){
                int aux = espacioV1(pos+1);
                return aux;
            }else{
                int aux = espacioV2(pos-1);
                return aux;
            }
        }
    }
    
    public String print(int num){
        int aiuto = 0;
        String info = this.array[num].getTitle() + "\nAutores: " + this.array[num].getAutores() + "\n";
        String resumen = this.array[num].getResumen();
        String[] linea = resumen.split("\n");
        String[] palabras = this.array[num].getpClave().split(", ");
        for (int i = 0; i < palabras.length; i++) {
            for (int j = 0; j < linea.length; j++) {
                if(linea[j].contains(palabras[i])){
                    aiuto++;
                }
            }
            info += palabras[i] + ": " + aiuto + "\n";
            aiuto = 0;
        }
        return info;
    }

    /**
     * @return the first
     */
    public int getFirst() {
        return first;
    }

    /**
     * @param first the first to set
     */
    public void setFirst(int first) {
        this.first = first;
    }

    /**
     * @return the last
     */
    public int getLast() {
        return last;
    }

    /**
     * @param last the last to set
     */
    public void setLast(int last) {
        this.last = last;
    }

    /**
     * @return the size
     */
    public int getSize() {
        return size;
    }

    /**
     * @param size the size to set
     */
    public void setSize(int size) {
        this.size = size;
    }

    /**
     * @return the array
     */
    public Nodos[] getArray() {
        return array;
    }

    /**
     * @param array the array to set
     */
    public void setArray(Nodos[] array) {
        this.array = array;
    }
}
