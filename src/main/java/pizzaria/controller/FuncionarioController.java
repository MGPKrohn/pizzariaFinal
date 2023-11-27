package pizzaria.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import pizzaria.dto.FuncionarioDTO;
import pizzaria.entity.Funcionario;
import pizzaria.repository.FuncionarioRepository;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/funcionario")
@CrossOrigin(origins = "http://localhost:4200")
public class FuncionarioController {

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @GetMapping
    public List<FuncionarioDTO> getAllFuncionarios() {
        List<Funcionario> funcionarios = funcionarioRepository.findAll();
        return mapToDTOList(funcionarios);
    }

    @GetMapping("/{id}")
    public FuncionarioDTO getFuncionarioById(@PathVariable Long id) {
        Funcionario funcionario = funcionarioRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return mapToDTO(funcionario);
    }

    @PostMapping
    public FuncionarioDTO saveFuncionario(@RequestBody FuncionarioDTO funcionarioDTO) {
        Funcionario funcionario = mapToEntity(funcionarioDTO);
        Funcionario savedFuncionario = funcionarioRepository.save(funcionario);
        return mapToDTO(savedFuncionario);
    }

    @PutMapping("/{id}")
    public FuncionarioDTO updateFuncionario(@PathVariable Long id, @RequestBody FuncionarioDTO funcionarioDTO) {
        Funcionario funcionarioToUpdate = funcionarioRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        Funcionario updatedFuncionario = mapToEntity(funcionarioDTO);
        BeanUtils.copyProperties(updatedFuncionario, funcionarioToUpdate, "id");

        Funcionario savedFuncionario = funcionarioRepository.save(funcionarioToUpdate);
        return mapToDTO(savedFuncionario);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteFuncionario(@PathVariable Long id) {
        try {
            funcionarioRepository.deleteById(id);
            return ResponseEntity.ok("Funcionario deletado com sucesso");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Erro ao deletar funcionário: " + e.getMessage());
        }
    }

    // Helper methods to map between Entity and DTO
    private FuncionarioDTO mapToDTO(Funcionario funcionario) {
        FuncionarioDTO funcionarioDTO = new FuncionarioDTO();
        BeanUtils.copyProperties(funcionario, funcionarioDTO);
        return funcionarioDTO;
    }

    private List<FuncionarioDTO> mapToDTOList(List<Funcionario> funcionarios) {
        return funcionarios.stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    private Funcionario mapToEntity(FuncionarioDTO funcionarioDTO) {
        Funcionario funcionario = new Funcionario();
        BeanUtils.copyProperties(funcionarioDTO, funcionario);
        return funcionario;
    }
}
