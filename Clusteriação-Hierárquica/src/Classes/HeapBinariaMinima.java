package Classes;

public class HeapBinariaMinima
{
	private int n;               /* Numero de elementos no heap. */   
	private int tam;             /* Tamanho m�ximo do heap. */
	private Distancia[] vetor;          /* Vetor com elementos. */

	/* Constr�i heap vazio. */
	public HeapBinariaMinima(int tamanho)
	{
		n = 0;
		tam = tamanho;
		vetor = new Distancia[tamanho+1];
	}

	/* Constr�i heap a partir de vetor v. */
	public HeapBinariaMinima(int tamanho, int n, Distancia[] v)
	{
		tam = tamanho;
		vetor = new Distancia[tamanho+1];
		this.n = n;

		for( int i = 0; i < n; i++ )
			vetor[ i + 1 ] = v[ i ];

		constroiHeap();
	}

	/**
	 * Retorna o vetor utilizado na heap
	 * @Complexidade: O(1)
	 */
	public Distancia[] getVetor() {
		return vetor;
	}

	public int getN() {
		return n;
	}

	public Distancia get(int i) {
		return vetor[i];
	}

	/* Testa se a fila de prioridade est� logicamente vazia.
	   Retorna true se vazia, false, caso contr�rio. */
	public boolean vazia()
	{
		return n == 0;
	}

	/* Faz a lista de prioridades logicamente vazia. */
	public void fazVazia()
	{
		n = 0;
	}

	/* Imprime os elementos da heap. */
	public void imprime()
	{
		System.out.print("Conte�do da heap: ");
		
		for(int i = 1; i <= n; i++)
			System.out.print(vetor[i] + " ");

		System.out.println();
	}

	/* Busca o menor item na fila de prioridades.
	   Retorna o menor item em itemMin e true, ou falso se a heap estiver vazia. */
	public Distancia min()
	{
		if (this.vazia())
		{
			System.out.println("Fila de prioridades vazia!");
			return null;
		}

		return vetor[1];
	}

	/* Remove o menor item da lista de prioridades e coloca ele em itemMin. */
	public Distancia removeMin()
	{
		Distancia itemMin;
		
		if(this.vazia())
		{
			System.out.println("Fila de prioridades vazia!");
			return null;
		}

		itemMin = vetor[1];
		vetor[1] = vetor[n];
		n--;
		refaz(1);
		
		return itemMin;
	}

	/**
	 * Remove um elemento da Heap
	 * @Complexidade: O(logn), pois chama o m�todo {@code refaz(i)}, O(logn)
	 */
	public Distancia remove(int i) {
		Distancia removido = vetor[i];
		vetor[i] = vetor[tam];

		n--;

		refaz(i);

		return removido;
	}

	/* M�todo auxiliar que estabelece a propriedade de ordem do heap a 
	 * partir de um vetor arbitr�rio dos itens. */
	private void constroiHeap()
	{
		for( int i = n / 2; i > 0; i-- )
			refaz(i);
	}

	/* M�todo auxiliar para restaurar a propriedade de heap que
	 * n�o est� sendo respeitada pelo n� i. */
	private void refaz(int i)
	{
		Distancia x = vetor[ i ];

		while(2*i <= n)
		{
			int filhoEsq, filhoDir, menorFilho;
			
			filhoEsq = 2*i;
			filhoDir = 2*i + 1;
			
			/* Por enquanto, o menor filho � o da esquerda. */
			menorFilho = filhoEsq;
			
			/* Verifica se o filho direito existe. */
			if(filhoDir <= n)
			{
				 /* Em caso positivo, verifica se � menor que o filho esquerdo. */
				if(vetor[ filhoDir ].compareTo(vetor[ filhoEsq ]) < 0)
					menorFilho = filhoDir;
			}

			/* Compara o valor do menor dos filhos com x. */
			if(vetor[ menorFilho ].compareTo(x) < 0)
				vetor [ i ] = vetor[ menorFilho ];
			else
				break;
			
			/* Como o elemento x que estava na posi��o "i" desceu para o n�vel de baixo, a pr�xima
			 * itera��o vai verificar a possibilidade de trocar esse elemento x (agora na 
			 * posi��o "menorFilho") com um de seus novos filhos. */
			i = menorFilho;
		}
		
		vetor[ i ] = x;
	}

	/* Insere item x na fila de prioridade, mantendo a propriedade do heap.
	 * S�o permitidas duplicatas. */
	public void insere(Distancia x)
	{
		if (tam == n)
		{
			System.out.println("Fila de prioridades cheia!");
			return;
		}

		/* O elemento � inicialmente inserido na primeira posi��o dispon�vel
		 * na �rvore, considerando de cima para baixo, da esquerda para a direita. */
		n++;
		int pos = n;
		
		/* Sentinela utilizada para tratar o caso do pai do n� raiz (de �ndice 1). */
		vetor[0] = x;

		/* Refaz heap (sobe elemento). */
		while(x.compareTo(vetor[pos/2]) < 0)
		{
			vetor[pos] = vetor[ pos/2 ];
			pos /= 2;
		}
		
		vetor[pos] = x;
	}
}