# Projeto de Sistema de Gestão para Agência de Viagens

## 1. Orientações Gerais
Este trabalho será realizado em grupos de até cinco alunos e será dividido em 4 sprints com prazos previamente definidos. Cada sprint deverá ser entregue de forma incremental no Canvas. É permitida a discussão de estratégias com os colegas, mas a modelagem e implementação deve ser feita pelos integrantes do grupo. Cópias de modelos e programas (mesmo que parciais) não serão aceitas. Somente um arquivo compactado por grupo contendo todos os entregáveis da sprint deve ser postado. A composição dos grupos não poderá ser alterada ao longo do semestre.

## 2. Objetivo
O objetivo deste trabalho é desenvolver um modelo de classes e implementar um sistema de gestão para uma Agência de Viagens. O projeto será desenvolvido em sprints, envolvendo a modelagem de classes e a implementação dessas classes em Java. Os requisitos de cada sprint deverão ser atendidos tanto na modelagem quanto no código-fonte, que deve ser testado por meio de testes unitários.

### Dinâmica de Trabalho
- Cada sprint se inicia em uma quarta-feira, quando os requisitos serão apresentados.
- A modelagem das classes deverá ser feita em um Diagrama de Classes (em PDF), de forma incremental, adicionando novos requisitos à medida que forem surgindo nas sprints seguintes.
- O código-fonte deve ser incrementado conforme os novos requisitos, mantendo o que foi implementado anteriormente.
- O Diagrama de Classes valerá 2 pontos em cada sprint, e o restante da pontuação será atribuída ao código-fonte.

### Pontuação
- Sprint 1: 3 pontos
- Sprints 2, 3 e 4: 4 pontos cada

## 3. Requisitos Funcionais

### 3.1. Sprint 1
Na primeira sprint, o desenvolvimento do sistema será iniciado e deverá atender aos seguintes requisitos. O sistema permitirá que a agência de viagens venda passagens aéreas aos seus clientes, os viajantes.

#### Funcionalidades:
1. **Cadastro de Funcionários da Agência**:
    - Nome, CPF e e-mail.
   
2. **Cadastro de Usuários para os Funcionários**:
    - Permitir que funcionários realizem cadastros no sistema.
   
3. **Autenticação de Funcionários**:
    - Acesso ao sistema via login e senha.

4. **Cadastro de Companhias Aéreas**:
    - Nome, código, razão social e CNPJ.

5. **Cadastro de Aeroportos**:
    - Nome, sigla (3 letras), cidade, estado e país.

6. **Cadastro de Passagens Aéreas**:
    - Aeroporto de origem e destino.
    - Data e horário do voo.
    - Código do voo (padrão XX9999).
    - Companhia aérea responsável.
    - Tarifas: básica, business, premium, bagagem adicional e moeda (por padrão em reais).

7. **Compra de Passagens com Vários Voos**:
    - O valor da tarifa total será a soma dos valores dos voos da passagem.

8. **Valores de Bagagens**:
    - Definido por cada companhia aérea.

9. **Remuneração da Agência**:
    - Calculada a partir de uma taxa fixa sobre o valor das passagens (exceto bagagens).

---

## Como Rodar o Projeto

1. **Clone o repositório**:
   ```bash
   git clone https://github.com/lucasjacome/Trabalho-Pratico-PM.git

2. **Navegue até o diretório do projeto**:

    cd Trabalho-Pratico-PM

3. **Compile e execute o projeto:**

    javac Main.java
    java Main
