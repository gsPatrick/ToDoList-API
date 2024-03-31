package br.com.patrick.ToDoListAPI.service;


import br.com.patrick.ToDoListAPI.Repository.Repositorio;
import br.com.patrick.ToDoListAPI.model.Mensagem;
import br.com.patrick.ToDoListAPI.model.Tarefa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class Servico {

    @Autowired
    private Mensagem mensagem;

    @Autowired
    private Repositorio acao;


    //Metodo para cadastrar uma Tarefa
    public ResponseEntity<?> cadastrar(Tarefa obj) {

        if(obj.getTitulo().equals("")){
            mensagem.setMensagem("O titulo precisa ser preenchido");
            return new ResponseEntity<>(mensagem, HttpStatus.BAD_REQUEST);
        } else if (obj.getDescricao().equals("")) {
            mensagem.setMensagem("A descrição precisa ser preenchido");
            return new ResponseEntity<>(mensagem, HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(acao.save(obj), HttpStatus.CREATED);
        }
    }

    //Metodo para selecionar uma Tarefa
    public ResponseEntity<?> selecionar(){
        return new ResponseEntity<>(acao.findAll(), HttpStatus.OK);
    }


    //Metodo para selecionar uma Tarefa pelo codigo
    public ResponseEntity<?> selecionarPeloCodigo(int codigo) {

        if (acao.countByCodigo(codigo) == 0) {
            mensagem.setMensagem("Não foi encontrada nenhuma tarefa");
            return new ResponseEntity<>(mensagem, HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(acao.findByCodigo(codigo), HttpStatus.OK);
        }
    }


    //Metodo para editar uma Tarefa
    public ResponseEntity<?> editar(Tarefa obj) {

        if(acao.countByCodigo(obj.getCodigo()) == 0) {
            mensagem.setMensagem("O código informado não existe.");
            return new ResponseEntity<>(mensagem, HttpStatus.NOT_FOUND);
        } else if(obj.getTitulo().equals("")) {
            mensagem.setMensagem("É necessario informar um titulo");
            return new ResponseEntity<>(mensagem, HttpStatus.BAD_REQUEST);
        } else if(obj.getDescricao().equals("")) {
            mensagem.setMensagem("É necessario informar uma descrição");
            return new ResponseEntity<>(mensagem, HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(acao.save(obj), HttpStatus.OK);
        }
    }

    //Metodo para remover registros
    public ResponseEntity<?> remover (int codigo) {
        if (acao.countByCodigo(codigo) == 0) {
            mensagem.setMensagem("O codígo informado não existe");
            return new ResponseEntity<>(mensagem, HttpStatus.BAD_REQUEST);
        } else {

            Tarefa obj = acao.findByCodigo(codigo);
            acao.delete(obj);

            mensagem.setMensagem("Tarefa removida com sucesso");
            return new ResponseEntity<>(mensagem, HttpStatus.OK);
        }
    }
}
