package br.com.task_manager.task.api.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import com.fasterxml.jackson.databind.ObjectMapper;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.authentication;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;

import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import br.com.task_manager.common.exception.GlobalExceptionHandler;
import br.com.task_manager.common.security.TokenService;
import br.com.task_manager.task.api.dto.TaskRequestDto;
import br.com.task_manager.task.application.service.TaskCreationService;
import br.com.task_manager.task.application.service.TaskDeletionService;
import br.com.task_manager.task.application.service.TaskRecoveryService;
import br.com.task_manager.task.application.service.TaskUpdateService;

import java.util.List;

@WebMvcTest(TaskController.class)
@Import(GlobalExceptionHandler.class)
public class TaskControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private TaskCreationService taskCreationService;

    @MockitoBean
    private TaskRecoveryService taskRecoveryService;

    @MockitoBean
    private TaskDeletionService taskDeletionService;

    @MockitoBean
    private TaskUpdateService taskUpdateService;

    @MockitoBean
    private TokenService tokenService;

    TaskRequestDto invalidRequest;

    @BeforeEach
    void setup() {
        invalidRequest = new TaskRequestDto(null, null, null, null);
    }

    private ResultActions expectValidationErrors(ResultActions result) throws Exception {
        return result
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.title").value("Title cannot be null."))
                .andExpect(jsonPath("$.description").value("Description cannot be null."))
                .andExpect(jsonPath("$.deadline").value("All tasks must have a deadline."));
    }

    @Test
    @WithMockUser
    void shouldReturnBadRequest_WhenCreateRequestIsInvalid() throws Exception {
        ResultActions result = mockMvc.perform(post("/task/create")
                .with(csrf())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(invalidRequest))
        );

        expectValidationErrors(result);
        Mockito.verifyNoInteractions(taskCreationService);
    }

    @Test
    @WithMockUser
    void shouldReturnBadRequest_WhenUpdateRequestIsInvalid() throws Exception {
        ResultActions result = mockMvc.perform(put("/task/1")
                .with(csrf())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(invalidRequest))
        );

        expectValidationErrors(result);
        Mockito.verifyNoInteractions(taskUpdateService);
    }

    @Test
    void shouldReturnUnauthorized_WhenAccessingMyTasksWithoutToken() throws Exception {
        mockMvc.perform(get("/task/my-tasks"))
                .andExpect(status().isUnauthorized());

        Mockito.verifyNoInteractions(taskRecoveryService);
    }

    @Test
    @WithMockUser
    void shouldReturnOk_WhenAccessingMyTasksWithValidToken() throws Exception {
        UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
                1L,   // <-- Long as principal, same as SecurityFilter sets
                null,
                List.of(new SimpleGrantedAuthority("ROLE_USER"))
        );

        mockMvc.perform(get("/task/my-tasks")
                    .with(csrf())
                    .with(authentication(auth))
                )
                .andExpect(status().isOk());
    }
}