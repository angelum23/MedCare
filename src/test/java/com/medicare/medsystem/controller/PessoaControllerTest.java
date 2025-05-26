package com.medicare.medsystem.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.medicare.medsystem.domain.Documento;
import com.medicare.medsystem.domain.Dto.InserirPessoaDto;
import com.medicare.medsystem.domain.Dto.ListarPessoaDto;
import com.medicare.medsystem.domain.Pessoa;
import com.medicare.medsystem.domain.Enum.EnumTipoPessoa;
import com.medicare.medsystem.service.PessoaService;

import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@TestPropertySource(locations = "classpath:application-test.properties")
class PessoaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PessoaService pessoaService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void listarPessoa_quandoRequestValido_retornaLista() throws Exception {
        // Arrange
        EnumTipoPessoa tipoPessoa = EnumTipoPessoa.Paciente;
        
        Pessoa pessoa = new Pessoa();
        pessoa.setId(1);
        pessoa.setNome("Paciente Teste");
        pessoa.setTipo(tipoPessoa);
        
        List<Pessoa> pessoasEsperadas = List.of(pessoa);

        when(pessoaService.listar(any(ListarPessoaDto.class))).thenReturn(pessoasEsperadas);

        // Act & Assert
        mockMvc.perform(get("/Pessoa/ListarPessoa")
                        .param("page", "0")
                        .param("rowsPerPage", "10")
                        .param("tipo", tipoPessoa.name()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$").isNotEmpty())
                .andExpect(jsonPath("$[0].id").value(pessoa.getId()))
                .andExpect(jsonPath("$[0].nome").value(pessoa.getNome()))
                .andExpect(jsonPath("$[0].tipo").value(tipoPessoa.name()));

        // Verifica se o serviço foi chamado com o DTO correto
        verify(pessoaService).listar(any(ListarPessoaDto.class));
    }

    @Test
    void listarPessoa_quandoTipoInvalido_retornaErroComMensagem() throws Exception {
        // Act & Assert - Tipo inválido que não existe no enum
        mockMvc.perform(get("/Pessoa/ListarPessoa")
                        .param("page", "0")
                        .param("rowsPerPage", "10")
                        .param("tipo", "TIPO_INVALIDO"))
                .andExpect(status().isInternalServerError())
                .andExpect(content().string("Tipo de pessoa inválido. Tipos válidos: [Paciente, Medico]"));
    }
    
    @Test
    void listarPessoa_quandoTipoNaoInformado_retornaSucesso() throws Exception {
        // Arrange
        var pessoa = new Pessoa();
        pessoa.setId(1);
        pessoa.setNome("Paciente Teste");
        pessoa.setTipo(EnumTipoPessoa.Paciente);
        
        when(pessoaService.listar(any(ListarPessoaDto.class))).thenReturn(List.of(pessoa));

        // Act & Assert - Não envia o parâmetro tipo
        mockMvc.perform(get("/Pessoa/ListarPessoa")
                        .param("page", "0")
                        .param("rowsPerPage", "10"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$").isNotEmpty())
                .andExpect(jsonPath("$[0].id").value(pessoa.getId()))
                .andExpect(jsonPath("$[0].nome").value(pessoa.getNome()))
                .andExpect(jsonPath("$[0].tipo").value(EnumTipoPessoa.Paciente.name()));
    }
    
    @Test
    void inserirPessoa_quandoRequestValido_retornaSucesso() throws Exception {
        // Arrange
        Pessoa pessoa = new Pessoa();
        pessoa.setNome("Novo Paciente");
        pessoa.setTipo(EnumTipoPessoa.Paciente);
        InserirPessoaDto dto = new InserirPessoaDto(pessoa, Optional.empty());
        
        when(pessoaService.salvarComFoto(any(InserirPessoaDto.class))).thenReturn(123);

        // Act & Assert
        mockMvc.perform(post("/Pessoa/InserirPessoa")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk())
                .andExpect(content().string("Registro inserido com sucesso: 123"));

        verify(pessoaService).salvarComFoto(any(InserirPessoaDto.class));
    }
    
    @Test
    void alterarPessoa_quandoRequestValido_retornaSucesso() throws Exception {
        // Arrange
        Pessoa pessoa = new Pessoa();
        pessoa.setId(1);
        pessoa.setNome("Paciente Atualizado");
        pessoa.setTipo(EnumTipoPessoa.Paciente);
        InserirPessoaDto dto = new InserirPessoaDto(pessoa, Optional.empty());
        
        when(pessoaService.salvarComFoto(any(InserirPessoaDto.class))).thenReturn(456);

        // Act & Assert
        mockMvc.perform(put("/Pessoa/AlterarPessoa")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk())
                .andExpect(content().string("Registro alterado com sucesso: 456"));

        verify(pessoaService).salvarComFoto(any(InserirPessoaDto.class));
    }
    
    @Test
    void inserirPessoa_quandoServiceLancaErro_retornaErro500() throws Exception {
        // Arrange
        Pessoa pessoa = new Pessoa();
        pessoa.setNome("Paciente Inválido");
        InserirPessoaDto dto = new InserirPessoaDto(pessoa, Optional.empty());
        
        String mensagemErro = "Erro ao salvar pessoa";
        when(pessoaService.salvarComFoto(any(InserirPessoaDto.class)))
                .thenThrow(new RuntimeException(mensagemErro));

        // Act & Assert
        mockMvc.perform(post("/Pessoa/InserirPessoa")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isInternalServerError())
                .andExpect(content().string("Erro ao inserir registro! " + mensagemErro));
    }
}
