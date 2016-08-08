package produto;

import java.security.GeneralSecurityException;
import java.util.Arrays;

import javax.management.RuntimeErrorException;

/**
 * Classe que representa um repositório de produtos usando arrays como estrutura sobrejacente. 
 * Alguns métodos (atualizar, remover e procurar) ou executam com sucesso ou retornam um erro.
 * Para o caso desde exercício, o erro será representado por uma RuntimeException que não precisa
 * ser declarada na clausula "throws" do mos metodos. 
 * 
 * Obs: Note que você deve utilizar a estrutura de dados array e não implementações de array 
 * prontas da API Collections de Java (como ArrayList, por exemplo).
 * 
 * @author Adalberto
 *
 */
public class RepositorioProdutoPerecivelArray {

	/**
	 * A estrutura (array) onde os produtos sao mantidos.
	 */
	private ProdutoPerecivel[] produtos;
	
	/**
	 * A posicao do ultimo elemento inserido no array de produtos. o valor inicial é -1 
	 * para indicar que nenhum produto foi ainda guardado no array.
	 */
	private int index = -1;
	
	public RepositorioProdutoPerecivelArray(int size) {
		super();
		this.produtos = new ProdutoPerecivel[size];
	}
	
	/**
	 * Recebe o codigo do produto e devolve o indice desse produto no array ou -1 caso ele 
	 * nao se encontre no array. Esse método é util apenas na implementacao com arrays 
	 * por questoes de localizacao. Outras classes que utilizam outras estruturas internas 
	 * podem nao precisar desse método.
	 * 
	 * @param codigo
	 * @return
	 */
	private int procurarIndice(int codigo){
		for (int i = 0; i < index; i++) {
			if (produtos[i].getCodigo() == codigo) {
				return i;
			}
		}
		return -1;
	}

	/**
	 * Recebe o codigo e diz se tem produto com  esse codigo armazenado
	 * 
	 * @param codigo
	 * @return
	 */
	public boolean existe(int codigo){
		for (int i = 0; i < index; i++) {
			if (produtos[i].getCodigo() == codigo) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Insere um novo produto (sem se preocupar com duplicatas)
	 */
	public void inserir(ProdutoPerecivel produto){
		if (index < produtos.length) {
			index++;
			produtos[index] =  produto;
		} else {
			throw new IllegalAccessError("Sem epaco para inserir mais produtos");
		}
		
	}
	
	/**
	 * Atualiza um produto armazenado ou retorna um erro caso o produto nao esteja no array. 
	 * Note que, para localizacao, o código do produto será utilizado.
	 */
	public void atualizar(ProdutoPerecivel produto){
		int indice =  procurarIndice(produto.getCodigo());
		if (indice >= 0) {
			produtos[indice] = produto;
		} else {
			throw  new IllegalAccessError("Nao existe um produto com esse codigo para ser atualizado.");
		}
	}
	
	/**
	 * Remove produto com determinado codigo, se existir, ou entao retorna um erro, caso contrário.
	 * Note que a remoção NÃO pode deixar "buracos" no array.
	 * @param codigo
	 */
	public void remover(int codigo){
		int indice = procurarIndice(codigo);
		
		if (indice >= 0) {
			produtos[indice] = null;
			
			for (int i = indice; i < produtos.length - 1 ; i++) {
				produtos[i] = produtos[i + 1]; 
				produtos[i + 1] = null;
			}
		} else {
			throw new IllegalAccessError("Este produto nao esta no repositorio.");
		}
		
		
	}
	
	/**
	 * Retorna um produto com determinado codigo ou entao um erro, caso o produto nao esteja armazenado
	 * 
	 * @param codigo
	 * @return
	 */
	public ProdutoPerecivel procurar(int codigo){
		//TODO Implement your code here
		throw new UnsupportedOperationException("Not implemented yet!");
	}
	
	public void imprimeArray() {
		System.out.println(Arrays.toString(produtos));
	}
}
