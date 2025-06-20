package com.receitas.repository;

import com.receitas.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.NativeQuery;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Optional<Usuario> findByEmail(String email);

    @NativeQuery("SELECT * FROM usuarios WHERE fk_funcionario = :fk_funcionario")
    Optional<Usuario> findByFuncionario(Long fk_funcionario);

}



