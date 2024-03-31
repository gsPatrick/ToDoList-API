package br.com.patrick.ToDoListAPI.Repository;

import br.com.patrick.ToDoListAPI.model.Tarefa;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Repositorio extends CrudRepository<Tarefa, Integer> {

    List<Tarefa> findAll(); // Cria uma lista para mostrar todas as tarefas
    Tarefa findByCodigo(int codigo); // Chama o objeto para procurar a tarefa pelo codigo o "id"

    List<Tarefa> findByOrderByTitulo(); // Cria uma lista para procurar a tarefa pelo Titulo
    int countByCodigo(int codigo); // Conta quantos codigos existem, serve para fazer a verificação se o codigo existe ou não

}
