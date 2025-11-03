import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.NoSuchElementException;

public class BuscaBinaria<T extends Comparable<T>> implements IBuscador<T> {
    private T[] dados;
    private int comparacoes;
    private LocalDateTime inicio, termino;
    private Comparator<T> comparador;

	private void init(T[] dados, Comparator<T> comparador) {
        this.comparador = comparador;
        this.dados = dados;
    }

    public BuscaBinaria(T[] dados) {
        init(dados, T::compareTo);
    }

    public BuscaBinaria(T[] dados, Comparator<T> comparador) {
        init(dados, comparador);
    }

    @Override
    public long getComparacoes() {
        return comparacoes;
    }

    @Override
    public double getTempo() {
        return Duration.between(inicio, termino).toNanos();
    }
    @Override
    public T buscar(T dado) {

        T encontrado;

        comparacoes = 0;
        inicio = LocalDateTime.now();
    
        encontrado = buscar(dado, 0, dados.length - 1);
        
        termino = LocalDateTime.now();
        
        if(encontrado == null){
            throw new NoSuchElementException("O elemeto não existe");
        }
        return encontrado;
    }

    private T buscar(T dado, int posIni, int posFim){

        int meio;
        int comparacao;

        if (posFim < posIni){
            return null;
        }

        meio = (posIni + posFim ) / 2;
        comparacoes++;
        comparacao = comparador.compare(dados[meio],dado);

        if (comparador.compare(dados[meio], dado) == 0) {
            return dados[meio];
        }else if (comparacao < 0) {
            return buscar(dado, meio + 1, posFim);
        }else {
            return buscar(dado, meio -1, posFim);
        }

    }
}