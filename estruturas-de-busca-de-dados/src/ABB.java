import java.util.Comparator;
import java.util.NoSuchElementException;

public class ABB<K, V> implements IMapeamento<K, V> {

    private No<K, V> raiz;
    private Comparator<K> comparador;
    private int tamanho;

    public ABB() {
        init((Comparator<K>) Comparator.naturalOrder());
    }

    public ABB(Comparator<K> comparador) {
        init(comparador);
    }

    private void init(Comparator<K> comparador) {
        raiz = null;
        tamanho = 0;
        this.comparador = comparador;
    }
    @Override
    public int inserir(K chave, V item) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'inserir'");
    }

    @Override
    public V pesquisar(K chave) {
        return pesquisar(this.raiz, chave);       
    }

    private V pesquisar(No<K, V> raizAtual, K procurado){

        int comparacao;
        if(raizAtual == null){
            throw new NoSuchElementException("Não tem esse trem aqui");
        }

        comparacao = comparador.compare(procurado, raizAtual.getChave());

        if(comparacao == 0) {
            return raizAtual.getItem();
        } else if(comparacao < 0) {
            return pesquisar(raizAtual.getEsquerda(), procurado);
        } else {
            return pesquisar(raizAtual.getDireita(), procurado);
        }
    }

    @Override
    public V remover(K chave) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'remover'");
    }

    @Override
    public int tamanho() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'tamanho'");
    }

    @Override
    public String percorrer() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'percorrer'");
    }
    
}
