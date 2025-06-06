package com.receitas.controller;

import com.receitas.config.ResponseJson;
import com.receitas.dto.CategoriaDTO;
import com.receitas.model.Categoria;
import com.receitas.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/receitas/categoria")
public class CategoriaController {

    @Autowired
    CategoriaService categoriaService;

    @GetMapping("/listar")
    public ResponseEntity<ResponseJson> listar() {
        List<CategoriaDTO> listaCategoriasDTO = categoriaService.listAll();
        return ResponseEntity.ok().body(new ResponseJson(HttpStatus.OK, "Categorias listadas com sucesso!", listaCategoriasDTO));
    }

    @GetMapping("/byId/{id}")
    public ResponseEntity<ResponseJson> buscar_pelo_id(@PathVariable("id") Long id) {
        CategoriaDTO categoriaDTO = categoriaService.listById(id);
        return ResponseEntity.ok().body(new ResponseJson(HttpStatus.OK, "Categoria encontrada com sucesso!", categoriaDTO));
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<ResponseJson> cadastrar(@RequestBody Categoria categoria) {
        CategoriaDTO categoriaDTO = categoriaService.save(categoria);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseJson(HttpStatus.CREATED, "Categoria cadastrada com sucesso!", categoriaDTO));
    }

    @PutMapping("/alterar/{id}")
    public ResponseEntity<ResponseJson> alterar(@PathVariable("id") Long id, @RequestBody Categoria categoria) {
        CategoriaDTO categoriaDTO = categoriaService.update(id, categoria);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseJson(HttpStatus.CREATED, "Categorias alterada com sucesso!", categoriaDTO));
    }

    @DeleteMapping("/excluir/{id}")
    public ResponseEntity<ResponseJson> excluir(@PathVariable("id") Long id) {
        Boolean responseDelete = categoriaService.delete(id);
        return ResponseEntity.ok().body(new ResponseJson(HttpStatus.CREATED, "Categorias excluido com sucesso!"));
    }
}
