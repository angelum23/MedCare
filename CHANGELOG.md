# 📋 CHANGELOG – MedCare

> Registro de tudo que realmente mexemos na refatoração de 2025.  
> A versão abaixo compara a branch `main` (refatorada) com a branch `original`.
> Formato inspirado em [Keep a Changelog](https://keepachangelog.com/).

---

## Versão 1.0.0-Refatorada – 28 mai 2025

### Adicionado
- **Suíte de testes automatizados** usando **JUnit 5** + **Spring MockMvc**, cobrindo:
    - `PessoaController`
    - `AgendamentoController`
    - `GradeHorarioController`
    - Util classes `Paginador` e `ValidacaoUtils`
- **Checkstyle** incluído no projeto com regras customizadas  
  (`LineLength`, `NeedBraces`, `WhitespaceAround`, `MethodLength`, `EmptyBlock`, `EqualsAvoidNull`).
- **Utilitários novos:**
    - `Paginador` – centraliza lógica de paginação.
    - `ValidacaoUtils` – validações reutilizáveis (null, vazio, maior que zero).
- Documentação extra:
    - README completo (instalação, execução, endpoints).
    - Seção “Análise dos Problemas Detectados e Estratégia de Refatoração”.
    - Seção “Pontos Positivos do Projeto Original”.
    - Seção "Descrição dos testes implementados"

### Modificado
- **Renomeação em massa** de variáveis, métodos e parâmetros para nomes claros  
  (ex.: `dto` → `filtros`, `registro` → `entidadeRecuperada`, `ids` → `idsCriados`).
- **Controllers desduplicados:** lógica comum de validação e formatação extraída para utilitários.
- **Endpoint `/GradeHorario/ListarGradeHorario`**: corrigido verbo HTTP (POST → GET), refletido em testes e documentação.
- **Dockerfile** otimizado para build mais leve e cache eficiente.

### Corrigido
- Mensagens de erro agora tratam parâmetros inválidos (enums fora do esperado, datas mal-formadas) nos endpoints de pessoa, agendamento e grade de horário.
- Corrigido `NullPointerException` raro em `AgendamentoService.recuperarPorDia`.
- Ajustada configuração do caminho do Checkstyle (resolvido erro de `LineLength` dentro de `TreeWalker`).

---

