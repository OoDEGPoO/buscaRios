/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package buscarios;

/**
 *
 * @author diego
 */
public class BuscaRios {

    /**
     * @param args the command line arguments
     */
    
    public static double funcion(double x) {
        //x^3-100x
        return (x * x * x) - (x * 100);
    }
    
    public static double funcion2(double x){
        //x^2-50x+300
        return (x*x) - (50*x) + 300;
    }

    public static double[] BuscaRios3003(double inicio, double fin, double error, int contador) {
        contador++;
        System.out.println(contador);
        
        double[] out = null;
        
        double p1 = inicio + (((fin - inicio) * 1) / 5);
        double p2 = inicio + (((fin - inicio) * 2) / 5);
        double p3 = inicio + (((fin - inicio) * 3) / 5);
        double p4 = inicio + (((fin - inicio) * 4) / 5);
/*
        double pInicio = funcion(inicio);   //profunidad punto inicial
        double pP1 = funcion(p1);           //profundidad 1/5
        double pP2 = funcion(p2);           //profundidad 2/5
        double pP3 = funcion(p3);           //profundidad 3/5
        double pP4 = funcion(p4);           //profundidad 4/5
        double pFin = funcion(fin);         //profundidad punto final
        */
        double pInicio = funcion2(inicio);   //profunidad punto inicial
        double pP1 = funcion2(p1);           //profundidad 1/5
        double pP2 = funcion2(p2);           //profundidad 2/5
        double pP3 = funcion2(p3);           //profundidad 3/5
        double pP4 = funcion2(p4);           //profundidad 4/5
        double pFin = funcion2(fin);         //profundidad punto final

        if (pInicio < pP1 || pFin < pP4) {//en caso de fallo en la busqueda
            System.out.println("falla " + inicio + ", " + fin);
            return out;
        } else if (error >= Math.abs(fin - inicio)) { //valor absoluto //saltamos
            out = new double[2];
            out[0] = inicio;
            out[1] = fin;
            System.out.println("encontrado " + inicio + ", " + fin);
            return out;
        } else {
            if (pP1 > pP2 && pP2 > pP3 && pP3 > pP4) { //buscamos en la última sección
                out = BuscaRios3003(p4, fin, error, contador);
                if (out != null) return out;
            }
            if (pP1 > pP2 && pP2 > pP3) { //buscamos en la penúltima sección
                out = BuscaRios3003(p3, p4, error, contador);
                if (out != null) return out;
            }
            if (pP1 > pP2 && pP3 < pP4){ //buscamos en la tercera sección
                out = BuscaRios3003(p2, p3, error, contador);
                if (out != null) return out;
            }
            if (pP2 < pP3 && pP3 < pP4){ //buscamos en la segunda sección
                out = BuscaRios3003(p2, p3, error, contador);
                if (out != null) return out;
            }
            if (pP1 < pP2 && pP2 < pP3 && pP3 < pP4){ //buscamos en la primera sección
                out = BuscaRios3003(p2, p3, error, contador);
                if (out != null) return out;
            }
            
            System.out.println("falla " + inicio + ", " + fin);
            return out;
        }
    }

    
    public static void main(String[] args) {
        double[] h = BuscaRios3003(7, 43, 0.2, 0);
        
        System.out.println("p1: " + h[0] + "\np2: " + h[1]);
    }
    
}
