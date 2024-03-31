package br.com.patrick.ToDoListAPI.controller;
import br.com.patrick.ToDoListAPI.Repository.Repositorio;
import br.com.patrick.ToDoListAPI.model.Tarefa;
import br.com.patrick.ToDoListAPI.service.Servico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class Controle {

    @Autowired
    private Repositorio acao;

    @Autowired
    private Servico servico;

    // Cadastrar uma nova Tarefa
    @PostMapping("/api")
    public Tarefa cadastrar(@RequestBody Tarefa obj){
        return acao.save(obj);
    }

    // Selecionar todas Tarefa
    @GetMapping("/api")
    public ResponseEntity<?> selecionar(){
        return servico.selecionar();
    }

    //Selecionar Tarefa pelo Codigo
    @GetMapping("/api/{codigo}")
    public ResponseEntity<?> selecionarPeloCodigo(@PathVariable int codigo) {
        return servico.selecionarPeloCodigo(codigo);
    }


    //Editar Tarefa
    @PutMapping("/api")
    public ResponseEntity<?> editar(@RequestBody Tarefa obj) {
        return servico.editar(obj);
    }

    //Deletar Tarefa
    @DeleteMapping("/api/{codigo}")
    public ResponseEntity<?> remover(@PathVariable int codigo){
      return servico.remover(codigo);
    }

    //Contar quantas Tarefas tem
    @GetMapping("/api/contador")
    public long contador() {
        return acao.count();
    }

    //Ordena as tarefas pela ordem alfabetica
    @GetMapping("/api/ordenarTitulos")
    public List<Tarefa> ordenarTitulos() {
        return acao.findByOrderByTitulo();
    }



    @GetMapping("/status")
    public ResponseEntity<?> status() {
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    //Ver Tarefas
    @PostMapping("/tarefa")
    public Tarefa tarefa(@RequestBody Tarefa t) {
        return t;
    }



}
