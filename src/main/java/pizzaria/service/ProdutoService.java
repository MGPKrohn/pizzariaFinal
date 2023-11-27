package pizzaria.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pizzaria.dto.ProdutoDTO;
import pizzaria.entity.Produto;
import pizzaria.repository.ProdutoRepository;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    public List<ProdutoDTO> listAll(){
        List<Produto> lista = produtoRepository.findAll();
        List<ProdutoDTO> listaDTO = new ArrayList<>();

        for(int i=0; i<lista.size(); i++)
            listaDTO.add(this.toProdutoDTO(lista.get(i)));

        return listaDTO;
    }

    public ProdutoDTO save(ProdutoDTO produtoDTO){
        Produto produto = this.toProduto(produtoDTO);

        Produto produtosalva = produtoRepository.save(produto);

        return this.toProdutoDTO(produtosalva);
    }

    public ProdutoDTO toProdutoDTO(Produto produto) {
        ProdutoDTO produtoDTO = new ProdutoDTO();
        produtoDTO.setId(produto.getId());
        produtoDTO.setNome(produto.getNome());
        produtoDTO.setValor(produto.getValor());
        produtoDTO.setTemSabores(produto.isTemSabores());
        produtoDTO.setMaximoSabores(produto.getMaximoSabores());
        return produtoDTO;
    }

    public Produto toProduto(ProdutoDTO produtoDTO) {
        Produto produto = new Produto();
        produto.setId(produtoDTO.getId());
        produto.setNome(produtoDTO.getNome());
        produto.setValor(produtoDTO.getValor());
        produto.setTemSabores(produtoDTO.isTemSabores());
        produto.setMaximoSabores(produtoDTO.getMaximoSabores());
        return produto;
    }

}