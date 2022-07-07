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
public class Funciones {
    public String[] repetidos(String[] nombres){
        String indices = "";
        for (int i = 0; i < nombres.length-1; i++) {
            for (int j = i+1; j < nombres.length; j++) {
                if(nombres[i].equals(nombres[j])){
                    indices += j + ",";
                }
            }
        }
        String[] aja = indices.split(",");
        int[] jaja = new int[aja.length];
        for (int i = 0; i < aja.length; i++) {
            jaja[i] = Integer.parseInt(aja[i]);
        }
        
        String help = "";
        int aiua = 0;
        for (int i = 0; i < nombres.length; i++) {
            for (int j = aiua; j < jaja.length; j++) {
                if(i == jaja[j]){
                    aiua++;
                    break;
                }else{
                    help += nombres[i] + ",";
                }
            }
        }
        
        String[] finito = help.split(",");
        return finito;
    }
}
