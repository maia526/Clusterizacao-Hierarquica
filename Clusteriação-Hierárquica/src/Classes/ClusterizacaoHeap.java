package Classes;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ClusterizacaoHeap {

    private final int iteracoesTotais;
    private HeapBinariaMinima distancias;
    private int tamHeap;
    Cluster raiz;

    public ClusterizacaoHeap(int iteracoesTotais) {
        this.iteracoesTotais = iteracoesTotais;
    }

    public void clusterizacao(int qtdEntradas) throws IOException {
        this.tamHeap = (qtdEntradas*(qtdEntradas-1))/2;
        int iteracoes = 0;
        List<Long> tempos = new ArrayList<>();
        FileWriter fileWriter = new FileWriter("registro.txt", true);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

        bufferedWriter.write("\n*** Algoritmo com Heap Minima: ***\n\n");

        while(iteracoes < iteracoesTotais){
            List<Cluster> listaCluster = new ArrayList<>();

            /* //Teste
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

            // /* se não for teste
            for (int i = 0; i < qtdEntradas; i++) {
                Cluster c = new Cluster(new Ponto(qtdEntradas));
                listaCluster.add(c);
            }
            // */

            long startTime = System.currentTimeMillis();

            distancias = calcularDistancias(listaCluster); // O(n²)

            while (listaCluster.size() > 1) { //O(n²logn)

                Distancia menor = distancias.get(1);
                Cluster novoCluster = new Cluster(menor);
                raiz = novoCluster; //Teste

                listaCluster.remove(menor.getC1()); // O(n)
                listaCluster.remove(menor.getC2()); // O(n)

                distancias = distanciasNaoPertencem(menor); // O(n)
                calcularDistanciasCluster(listaCluster, novoCluster); //(nlogn)

                // distancias = novasDistancias(menor, listaCluster, novoCluster); Outra possibilidade -> O(nlogn)
                listaCluster.add(novoCluster); // O(1)
            }

            long endTime = System.currentTimeMillis();
            tempos.add(endTime-startTime);

            bufferedWriter.write("Entradas: " + qtdEntradas + "\nTempo: " + (endTime - startTime) + " milissegundos\n");

            iteracoes++;
        }


        bufferedWriter.write("\nMedia: " + fazMedia(tempos) + "\n\n");

        raiz.getRaiz().mostra(); //Teste
        bufferedWriter.close();
    }

    private long fazMedia(List<Long> tempos){
        long soma = 0;
        for (long tempo : tempos){
            soma+= tempo;
        }
        return soma / iteracoesTotais;

    }

    private HeapBinariaMinima novasDistancias(Distancia distancia, List<Cluster> listaCluster, Cluster novo){
        Distancia[] novasDistancias = new Distancia[distancias.getN()];
        Distancia[] vetor = distancias.getVetor();
        int contador = 0;

        for (int i = 1; i < distancias.getN(); i++) {
            if (!vetor[i].possuiCluster(distancia)) {
                novasDistancias[contador++] = vetor[i]; //O(logn)
            }
        }

        for (Cluster c : listaCluster) {
            novasDistancias[contador++] = new Distancia(c, novo);
        }

        return new HeapBinariaMinima(tamHeap, contador, novasDistancias);

    }

    private HeapBinariaMinima calcularDistancias(List<Cluster> listaCluster) {
        Distancia[] vetor = new Distancia[tamHeap];
        int contador = 0;

        for (int i = 0; i < listaCluster.size()-1; i++) { // O(n²)
            for (int j = i + 1; j < listaCluster.size(); j++) {
                vetor[contador++] = new Distancia(listaCluster.get(i), listaCluster.get(j));
            }
        }

        return new HeapBinariaMinima(tamHeap, contador, vetor); // O(n)
    }

    private HeapBinariaMinima distanciasNaoPertencem(Distancia distancia){
        Distancia[] permanecer = new Distancia[distancias.getN()];
        Distancia[] vetor = distancias.getVetor();
        int contador = 0;

        for (int i = 1; i < distancias.getN(); i++) {
            if (!vetor[i].possuiCluster(distancia)) {
                permanecer[contador++] = vetor[i];
            }
        }

        return new HeapBinariaMinima(tamHeap, contador, permanecer);
    }

    private void calcularDistanciasCluster(List<Cluster> listaCluster, Cluster novo) {
        for (Cluster c : listaCluster) {
            distancias.insere(new Distancia(c, novo));
        }
    }

}
