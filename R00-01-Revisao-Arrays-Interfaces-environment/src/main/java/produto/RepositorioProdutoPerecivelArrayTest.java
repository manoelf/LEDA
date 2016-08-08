package produto;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.Test;

public class RepositorioProdutoPerecivelArrayTest {

	@Test
	public void testAddProduto() {
		RepositorioProdutoPerecivelArray repositorio = new RepositorioProdutoPerecivelArray(3);
		
		repositorio.inserir(new ProdutoPerecivel(1, "p1", 1, "prd1", null));
		repositorio.inserir(new ProdutoPerecivel(2, "p2", 1, "prd1", null));
		repositorio.inserir(new ProdutoPerecivel(3, "p3", 1, "prd1", null));
		Assert.assertFalse(!repositorio.existe(1));
		
		repositorio.remover(1);
		
		repositorio.imprimeArray();
	
	
	}

}
