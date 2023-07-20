package Classes;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ClusterizacaoNaive {

    private final int iteracoesTotais;

    public ClusterizacaoNaive(int iteracoesTotais) {
        this.iteracoesTotais = iteracoesTotais;
    }

    public void clusterizacao(int qtdEntradas) throws IOException {
        List<Long> tempos = new ArrayList<>();
        int iteracoes = 0;

        FileWriter fileWriter = new FileWriter("registro.txt", true);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

        bufferedWriter.write("\n*** Algoritmo Naive: ***\n\n");

        Cluster raiz = null;

        while(iteracoes < iteracoesTotais){

            List<Cluster> listaCluster = new ArrayList<>();

            /* Teste
                List<Ponto> listaPonto = new ArrayList<>(){{
                    add(new Ponto(4,10));
                    add(new Ponto(7,10));
                    add(new Ponto(4,8));
                    add(new Ponto(6,8));
                    add(new Ponto(3,4));
                    add(new Ponto(2,2));
                    add(new Ponto(5,2));
                    add(new Ponto(10,5));
                    add(new Ponto(12,6));
                    add(new Ponto(11,4));
                    add(new Ponto(9,3));
                    add(new Ponto(12,3));
                }};

                for (Ponto p : listaPonto) {
                    Cluster c = new Cluster(p);
                    listaCluster.add(c);
                }
            // */

            // /* quando não for teste
            for (int i = 0; i < qtdEntradas; i++) {
                Cluster c = new Cluster(new Ponto(qtdEntradas));
                listaCluster.add(c);
            } // */

            long startTime = System.currentTimeMillis();

            while (listaCluster.size() > 1) {
                Distancia menor = calcularDistancias(listaCluster);
                Cluster novoCluster = new Cluster(menor);
                raiz = novoCluster; // Teste
                listaCluster.remove(menor.getC1()); listaCluster.remove(menor.getC2());
                listaCluster.add(novoCluster);
            }

            long endTime = System.currentTimeMillis();
            tempos.add(endTime-startTime);

            bufferedWriter.write("Entradas: " + qtdEntradas + "\nTempo: " + (endTime - startTime) + " milissegundos\n");

            iteracoes++;
        }

        bufferedWriter.write("\nMedia: " + fazMedia(tempos) + "\n\n");

        raiz.getRaiz().mostra(); // Teste
        bufferedWriter.close();
    }

    private long fazMedia(List<Long> tempos){
        long soma = 0;
        for (long tempo : tempos){
            soma+= tempo;
        }
        return soma / iteracoesTotais;

    }

    private Distancia calcularDistancias(List<Cluster> listaCluster) {
        Distancia menor = null;

        for (int i = 0; i < listaCluster.size()-1; i++) {
            for (int j = i + 1; j < listaCluster.size(); j++) {
                Distancia d = new Distancia(listaCluster.get(i), listaCluster.get(j));

                if (menor == null)
                    menor = d;
                else if (menor.compareTo(d) > 0)
                    menor = d;
            }
        }

        return menor;
    }

}
