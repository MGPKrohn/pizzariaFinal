package pizzaria.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pizzaria.dto.SaborDTO;
import pizzaria.service.SaborService;

@RestController
@RequestMapping("/api/sabor")
@CrossOrigin(origins = "http://localhost:4200")
public class SaborController {

    @Autowired
    private SaborService saborService;

    @GetMapping
    private ResponseEntity<List<SaborDTO>> listAll(){
        try {
            List<SaborDTO> lista = saborService.listAll();
            return new ResponseEntity<>(lista, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping
    private ResponseEntity<SaborDTO> save(@RequestBody SaborDTO saborDTO){
        try {
            SaborDTO saborSalva = saborService.save(saborDTO);
            return new ResponseEntity<>(saborSalva, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

}