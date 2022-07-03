package br.ce.wcaquino.taskbackend.controller;

import br.ce.wcaquino.taskbackend.model.Task;
import br.ce.wcaquino.taskbackend.repo.TaskRepo;
import br.ce.wcaquino.taskbackend.utils.ValidationException;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;

import static org.junit.Assert.*;

public class TaskControllerTest{

    @Mock
    private TaskRepo todoRepo;

    @InjectMocks
    private TaskController controller;

    @Before
    public void before(){
        MockitoAnnotations.initMocks(this);
    }


    @Test
    public void nao_deve_salvar_tarefa_sem_descricao() {

        Task todo = new Task();
        todo.setDueDate(LocalDate.now());

        try {
            controller.save(todo);
            fail("Não deveria ter chegado nesse ponto");
        } catch (ValidationException e) {
            assertEquals("Fill the task description", e.getMessage());
        }
    }

    @Test
    public void nao_deve_salvar_tarefa_sem_data() {
        Task todo = new Task();
        todo.setTask("Descição");

        try {
            controller.save(todo);
            fail("Não deveria ter chegado nesse ponto");
        } catch (ValidationException e) {
            assertEquals("Fill the due date", e.getMessage());
        }
    }

    @Test
    public void nao_deve_salvar_tarefa_com_data_passada() {

        Task todo = new Task();
        todo.setTask("Descição");
        todo.setDueDate(LocalDate.of(2010,01,01));

        try {
            controller.save(todo);
            fail("Não deveria ter chegado nesse ponto");
        } catch (ValidationException e) {
            assertEquals("Due date must not be in past", e.getMessage());
        }

    }

    @Test
    public void deve_salvar_tarefa_com_sucesso() throws ValidationException {

        Task todo = new Task();
        todo.setTask("Descição");
        todo.setDueDate(LocalDate.now());
        controller.save(todo);
        Mockito.verify(todoRepo).save(todo);
    }

}