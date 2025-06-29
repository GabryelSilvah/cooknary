package com.receitas.controller;

import com.receitas.config.ResponseJson;
import com.receitas.dto.*;
import com.receitas.model.Livro;
import com.receitas.model.Medida;
import com.receitas.service.LivroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/livros")
public class LivroController {

    @Autowired
    private LivroService livroService;

    @GetMapping("/listar")
    public ResponseEntity<ResponseJson> listar() {
        List<LivroDTO> listaLivrosDTO = livroService.listAll();
        return ResponseEntity.ok().body(new ResponseJson(HttpStatus.OK, "Livros listados com sucesso!", listaLivrosDTO));
    }

    @GetMapping("/byId/{id}")
    public ResponseEntity<ResponseJson> buscar_pelo_id(@PathVariable("id") Long id) {
        LivroDTO livroDTO = livroService.listById(id);
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseJson(HttpStatus.OK, "Livro encontrado com sucesso!", livroDTO));
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<ResponseJson> cadastrarCompleto(@RequestBody LivroDTO livro) {
        LivroDTO livroDTO = livroService.saveCompleto(livro);
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseJson(HttpStatus.CREATED, "Livro cadastrado com sucesso!", livroDTO));
    }

    @PostMapping("/cadastrar/only")
    public ResponseEntity<ResponseJson> cadastrarOnly(@RequestBody Livro livro) {
        LivroDTO livroDTO = livroService.save(livro);
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseJson(HttpStatus.CREATED, "Livro cadastrado com sucesso!", livroDTO));
    }


    @PutMapping("/alterar/{id}")
    public ResponseEntity<ResponseJson> alterar(@PathVariable("id") Long id, @RequestBody LivroDTO livro) {
        LivroDTO livroDTO = livroService.updateCompleto(id, livro);
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseJson(HttpStatus.CREATED, "Livro alterado com sucesso!", livroDTO));
    }

    @DeleteMapping("/excluir/{id}")
    public ResponseEntity<ResponseJson> excluir(@PathVariable("id") Long id) {
        Boolean responseDelete = livroService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseJson(HttpStatus.CREATED, "Livro excluido com sucesso!"));
    }

}
