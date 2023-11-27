package pizzaria.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import pizzaria.dto.BalcaoDTO;
import pizzaria.entity.Balcao;
import pizzaria.service.BalcaoService;

import java.util.List;

@RestController
@RequestMapping("/api/balcao")
@CrossOrigin(origins = "http://localhost:4200")
public class BalcaoController {

    @Autowired
    private BalcaoService balcaoService;

    @GetMapping
    public List<BalcaoDTO> getAllBalcoes() {
        return balcaoService.getAllBalcoes();
    }

    @GetMapping("/{id}")
    public BalcaoDTO getBalcaoById(@PathVariable Long id) {
        return balcaoService.getBalcaoById(id);
    }

    @PostMapping
    public ResponseEntity<BalcaoDTO> saveBalcao(@RequestBody BalcaoDTO balcaoDTO) {
        BalcaoDTO savedBalcao = balcaoService.saveBalcao(balcaoDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedBalcao);
    }

    @PutMapping("/{id}")
    public BalcaoDTO updateBalcao(@PathVariable Long id, @RequestBody BalcaoDTO balcaoDTO) {
        return balcaoService.updateBalcao(id, balcaoDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBalcao(@PathVariable Long id) {
        try {
            balcaoService.deleteBalcao(id);
            return ResponseEntity.ok("Balcao deletado com sucesso");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Erro ao deletar balcao: " + e.getMessage());
        }
    }
}
