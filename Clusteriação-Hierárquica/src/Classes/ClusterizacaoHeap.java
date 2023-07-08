package Classes;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ClusterizacaoHeap {
    //teste com 10, 20, 30, 40, 50, 100, 200, 500, 1.000, 5.000, 10.000, 20.000, 50.000 e 100.000
    private final int qtdEntradas = 500;
    private List tempos = new ArrayList<>();
    private int iteracoes = 0;
    private HeapBinariaMinima distancias;

    private int tamHeap = (qtdEntradas*(qtdEntradas-1))/2; //todo: colocar isso depois no construtor
    Cluster raiz;

    public ClusterizacaoHeap() throws IOException {

		while(iteracoes != 10){
        List<Ponto> listaPonto = new ArrayList<>();

            /*List<Ponto> listaPonto = new ArrayList<>(){{
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
            }}; //Para teste*/

            // aqui poderia ser, pq a lista de pontos não vai ser necessária no final: todo: falar com a Maia sobre
            List<Cluster> listaCluster = new ArrayList<>();

            for (int i = 0; i < qtdEntradas; i++) {
                Ponto p = new Ponto(); // Cluster c = new Cluster(new Ponto());
                listaPonto.add(p); // listaCluster.add(c);
            }

            for (Ponto p : listaPonto) {
                Cluster c = new Cluster(p);
                listaCluster.add(c);
            }

            long startTime = System.currentTimeMillis();

            distancias = calcularDistancias(listaCluster); // O(n²)

            while (listaCluster.size() > 1) {

                Distancia menor = distancias.get(1); //a menor distância está no índice 1: Complexidade O(1)
                Cluster novoCluster = new Cluster(menor);
                raiz = novoCluster;

                listaCluster.remove(menor.getC1()); // O(n)
                listaCluster.remove(menor.getC2()); // O(n)
                distancias = removerDistanciasDaLista(menor); // O(n)??
                calcularDistanciasCluster(listaCluster, novoCluster);
                listaCluster.add(novoCluster);
            }

            long endTime = System.currentTimeMillis();
            tempos.add(endTime-startTime);
            FileWriter fileWriter = new FileWriter("registro.txt", true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write("Entradas: " + qtdEntradas + "\nTempo: " + (endTime - startTime) + " milissegundos\n");
            bufferedWriter.close();

            iteracoes++;
        }


        FileWriter fileWriter = new FileWriter("registro.txt", true);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        bufferedWriter.write("\nMédia: " + fazMedia(tempos) + "\n\n");
        bufferedWriter.close();

        //raiz.getRaiz().mostra();
    }

    public long fazMedia(List<Long> tempos){
        long soma = 0;
        for (long tempo : tempos){
            soma+= tempo;
        }
        return soma / 10;

    }

    public HeapBinariaMinima removerDistanciasDaLista(Distancia distancia){
        Distancia[] permanecer = new Distancia[distancias.getN()];
        Distancia[] vetor = distancias.getVetor();
        int contador = 0;

        for (int i = 1; i < distancias.getN(); i++) {
            if (vetor[i].getC1() != distancia.getC1() && vetor[i].getC1() != distancia.getC2() &&
                vetor[i].getC2() != distancia.getC1() && vetor[i].getC2() != distancia.getC2()) {
                permanecer[contador++] = vetor[i]; //O(logn)
            }
        }

        return new HeapBinariaMinima(tamHeap, contador, permanecer);

    }

    public HeapBinariaMinima calcularDistancias(List<Cluster> listaCluster) {
        Distancia[] vetor = new Distancia[tamHeap];
        int contador = 0;

        for (int i = 0; i < listaCluster.size()-1; i++) { // O(n²)
            for (int j = i + 1; j < listaCluster.size(); j++) {
                vetor[contador++] = new Distancia(listaCluster.get(i), listaCluster.get(j));
            }
        }

        return new HeapBinariaMinima(tamHeap, contador, vetor); // O(n)
    }

    public void calcularDistanciasCluster(List<Cluster> listaCluster, Cluster novo) {
        for (Cluster c : listaCluster) {
            distancias.insere(new Distancia(c, novo));
        }
    }

}
