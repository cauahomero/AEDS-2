import java.util.function.Function;
import java.util.function.Predicate;

public class Lista<E> {

	private Celula<E> primeiro;
	private Celula<E> ultimo;
	private int tamanho;
	
	public Lista() {
		
		Celula<E> sentinela = new Celula<>();
		
		this.primeiro = this.ultimo = sentinela;
		this.tamanho = 0;
	}
	
	public boolean vazia() {
		
		return (this.primeiro == this.ultimo);
	}
	
	public void inserir(E novo, int posicao) {
		
		Celula<E> anterior, novaCelula, proximaCelula;
		
		if ((posicao < 0) || (posicao > this.tamanho))
			throw new IndexOutOfBoundsException("Não foi possível inserir o item na lista: "
					+ "a posição informada é inválida!");
		
		anterior = this.primeiro;
		for (int i = 0; i < posicao; i++)
			anterior = anterior.getProximo();
				
		novaCelula = new Celula<>(novo);
			
		proximaCelula = anterior.getProximo();
			
		anterior.setProximo(novaCelula);
		novaCelula.setProximo(proximaCelula);
			
		if (posicao == this.tamanho) 
			this.ultimo = novaCelula;
			
		this.tamanho++;		
	}
	
	public E remover(int posicao) {
		
		Celula<E> anterior, celulaRemovida, proximaCelula;
		
		if (vazia())
			throw new IllegalStateException("Não foi possível remover o item da lista: "
					+ "a lista está vazia!");
		
		if ((posicao < 0) || (posicao >= this.tamanho ))
			throw new IndexOutOfBoundsException("Não foi possível remover o item da lista: "
					+ "a posição informada é inválida!");
			
		anterior = this.primeiro;
		for (int i = 0; i < posicao; i++)
			anterior = anterior.getProximo();
				
		celulaRemovida = anterior.getProximo();
				
		proximaCelula = celulaRemovida.getProximo();
				
		anterior.setProximo(proximaCelula);
		celulaRemovida.setProximo(null);
				
		if (celulaRemovida == this.ultimo)
			this.ultimo = anterior;
				
		this.tamanho--;
				
		return (celulaRemovida.getItem());	
	}

	public void adicionar(E novo) {

		inserir(novo, this.tamanho);
	}

	public E obter(int posicao) {

		if ((posicao < 0) || (posicao >= this.tamanho))
			throw new IndexOutOfBoundsException("Não foi possível obter o item da lista: "
					+ "a posição informada é inválida!");

		Celula<E> atual = this.primeiro.getProximo();
		for (int i = 0; i < posicao; i++)
			atual = atual.getProximo();

		return atual.getItem();
	}

	// Célula sentinela: o primeiro item de verdade está em getPrimeiro().getProximo()
	public Celula<E> getPrimeiro() {

		return this.primeiro;
	}

	public int tamanho() {

		return this.tamanho;
	}

	// Soma o número que o extrator tira de cada item e divide pela quantidade.
	public double calcularMedia(Function<E, Double> extrator) {

		if (vazia())
			throw new IllegalStateException("Não foi possível calcular a média: "
					+ "a lista está vazia!");

		double soma = 0;
		Celula<E> atual = this.primeiro.getProximo();

		while (atual != null) {
			soma += extrator.apply(atual.getItem());
			atual = atual.getProximo();
		}
		
		return soma / this.tamanho;
	}

	// Testa cada item num if e, se der true, coloca ele na lista de resultado.
	public Lista<E> filtrar(Predicate<E> condicao){

		Lista<E> resultado = new Lista<E>();
		Celula<E> aux = primeiro.getProximo();

		while (aux != null) {
			if (condicao.test(aux.getItem())){
				resultado.adicionar(aux.getItem());
			}
			aux = aux.getProximo();
		}

		return resultado;
	}

	@Override
	public String toString() {

		if (vazia())
			return "  (nenhum)";

		Celula<E> atual = this.primeiro.getProximo();

		// Lista de números (ex.: notas) fica numa linha só: [8,50; 9,00; 8,00]
		// "; " separa pra não confundir com a vírgula do decimal
		if (atual.getItem() instanceof Double) {
			StringBuilder sb = new StringBuilder("[");
			while (atual != null) {
				sb.append(String.format("%.2f", atual.getItem()));
				if (atual.getProximo() != null)
					sb.append("; ");
				atual = atual.getProximo();
			}
			return sb.append("]").toString();
		}

		// Lista de objetos (ex.: alunos) fica um por linha
		StringBuilder sb = new StringBuilder();
		while (atual != null) {
			sb.append("  ").append(atual.getItem());
			if (atual.getProximo() != null)
				sb.append("\n");
			atual = atual.getProximo();
		}
		return sb.toString();
	}

}
