package pizzaria.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pizzaria.dto.PedidoDTO;
import pizzaria.dto.PedidoProdutoDTO;
import pizzaria.dto.SaborDTO;
import pizzaria.entity.Pedido;
import pizzaria.entity.PedidoProduto;
import pizzaria.entity.Sabor;
import pizzaria.repository.PedidoRepository;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private ProdutoService produtoService;

    @Autowired
    private SaborService saborService;


    public List<PedidoDTO> listAll(){
        List<Pedido> lista = pedidoRepository.findAll();
        List<PedidoDTO> listaDTO = new ArrayList<>();

        for(int i=0; i<lista.size(); i++)
            listaDTO.add(this.toPedidoDTO(lista.get(i)));

        return listaDTO;
    }

    public PedidoDTO save(PedidoDTO pedidoDTO){
        Pedido pedido = this.toPedido(pedidoDTO);

        Pedido pedidosalva = pedidoRepository.save(pedido);

        return this.toPedidoDTO(pedidosalva);
    }




    private PedidoDTO toPedidoDTO(Pedido pedido) {
        PedidoDTO pedidoDTO = new PedidoDTO();
        pedidoDTO.setId(pedido.getId());
        pedidoDTO.setObs(pedido.getObs());

        List<PedidoProdutoDTO> listaProdutos = new ArrayList<>();
        if(pedido.getPedidoProdutoList() != null)
            for(int i=0; i<pedido.getPedidoProdutoList().size(); i++) {
                listaProdutos.add(this.toPedidoProdutoDTO(pedido.getPedidoProdutoList().get(i)));
            }
        pedidoDTO.setPedidoProdutoList(listaProdutos);

        return pedidoDTO;
    }

    private Pedido toPedido(PedidoDTO pedidoDTO) {
        Pedido pedido = new Pedido();
        pedido.setId(pedidoDTO.getId());
        pedido.setObs(pedidoDTO.getObs());

        List<PedidoProduto> listaProdutos = new ArrayList<>();
        if(pedidoDTO.getPedidoProdutoList() != null)
            for(int i=0; i<pedidoDTO.getPedidoProdutoList().size(); i++) {
                listaProdutos.add(this.toPedidoProduto(pedidoDTO.getPedidoProdutoList().get(i), pedido));
            }
        pedido.setPedidoProdutoList(listaProdutos);

        return pedido;
    }




    private PedidoProdutoDTO toPedidoProdutoDTO(PedidoProduto pedidoProduto) {
        PedidoProdutoDTO pedidoProdutoDTO = new PedidoProdutoDTO();

        pedidoProdutoDTO.setId(pedidoProduto.getId());
        if(pedidoProduto.getProduto() != null)
            pedidoProdutoDTO.setProduto(this.produtoService.toProdutoDTO(pedidoProduto.getProduto()));

        List<SaborDTO> listaProdutos = new ArrayList<>();
        if(pedidoProduto.getSabores() != null)
            for(int i=0; i<pedidoProduto.getSabores().size(); i++) {
                listaProdutos.add(this.saborService.toSaborDTO(pedidoProduto.getSabores().get(i)));
            }
        pedidoProdutoDTO.setSabores(listaProdutos);

        return pedidoProdutoDTO;
    }


    private PedidoProduto toPedidoProduto(PedidoProdutoDTO pedidoProdutoDTO, Pedido pedido) {
        PedidoProduto pedidoProduto = new PedidoProduto();

        pedidoProduto.setId(pedidoProdutoDTO.getId());
        pedidoProduto.setPedido(pedido);
        if(pedidoProdutoDTO.getProduto() != null)
            pedidoProduto.setProduto(this.produtoService.toProduto(pedidoProdutoDTO.getProduto()));

        List<Sabor> listaProdutos = new ArrayList<>();
        if(pedidoProdutoDTO.getSabores() != null)
            for(int i=0; i<pedidoProdutoDTO.getSabores().size(); i++) {
                listaProdutos.add(this.saborService.toSabor(pedidoProdutoDTO.getSabores().get(i)));
            }
        pedidoProduto.setSabores(listaProdutos);

        return pedidoProduto;
    }




}