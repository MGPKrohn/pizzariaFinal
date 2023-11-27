package pizzaria.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pizzaria.dto.SaborDTO;
import pizzaria.entity.Sabor;
import pizzaria.repository.SaborRepository;

@Service
public class SaborService {

    @Autowired
    private SaborRepository saborRepository;

    public List<SaborDTO> listAll(){
        List<Sabor> lista = saborRepository.findAll();
        List<SaborDTO> listaDTO = new ArrayList<>();

        for(int i=0; i<lista.size(); i++)
            listaDTO.add(this.toSaborDTO(lista.get(i)));

        return listaDTO;
    }

    public SaborDTO save(SaborDTO saborDTO){
        Sabor sabor = this.toSabor(saborDTO);

        Sabor saboresalva = saborRepository.save(sabor);

        return this.toSaborDTO(saboresalva);
    }

    public SaborDTO toSaborDTO(Sabor sabor) {
        SaborDTO saborDTO = new SaborDTO();
        saborDTO.setId(sabor.getId());
        saborDTO.setNome(sabor.getNome());
        return saborDTO;
    }

    public Sabor toSabor(SaborDTO saborDTO) {
        Sabor sabor = new Sabor();
        sabor.setId(saborDTO.getId());
        sabor.setNome(saborDTO.getNome());
        return sabor;
    }

}