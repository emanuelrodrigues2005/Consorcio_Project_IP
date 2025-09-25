# ConsorX - Sistema de Gerenciamento de Consórcios Automotivos

![Java](https://img.shields.io/badge/Java-21-blue?style=for-the-badge&logo=java)
![JavaFX](https://img.shields.io/badge/JavaFX-21-orange?style=for-the-badge&logo=data:image/svg+xml;base64,PHN2ZyB4bWxucz0iaHR0cDovL3d3dy53My5vcmcvMjAwMC9zdmciIHdpZHRoPSIxZW0iIGhlaWdodD0iMWVtIiB2aWV3Qm94PSIwIDAgMjQgMjQiPjxwYXRoIGZpbGw9ImN1cnJlbnRDb2xvciIgZD0iTTE2LjQ3IDE2LjgxNWwyLjM0NS0xLjI0MmMuMjYtLjEzOC4zODctLjQ1OC4zMDgtLjc0NmwtMS4zOC00Ljc2MmEuNzQ5LjczOCAwIDAgMC0uNjc4LS41NTdoLS4wMDhabS0zLjU4OC02LjU5bC0yLjM5IDEuODlhLjUuNSAwIDAgMS0uNjA3LS4wMDJsLTIuMDgtMS44MDNhLjUuNSAwIDAgMS0uMTk4LS41OThsLjM1NS0xLjY0N2EuNS41IDAgMCAxIC40NzgtLjM5N2g2LjYwOGEuNS41IDAgMCAxIC40NzguMzk3bC4zNTQgMS42NDdhLjUuNSAwIDAgMS0uMTk3LjU5OG0tMS4yIDEwLjM3aC0zLjU5M2MtMS42NTIgMC0yLjk5NS0xLjM0My0yLjk5NS0yLjk5NXYtNi4yNmMwLTEuNjUzIDEuMzQzLTIuOTk1IDIuOTk1LTIuOTk1aDEuMjM4bC0uMjE1LS45OTVhMS41IDEuNSAwIDAgMSAuNTkyLTEuNzkzTDEwLjgxIDMuNmEuNS41IDAgMCAxIC4zNTQtLjEwN2g3LjM4N2ExLjUgMS41IDAgMCAxIDEuNDE3IDEuMDcxbDEuMzgxIDQuNzYyYTEuNzQ4IDEuNzUgMCAwIDEgLjA1OCAxLjA1M2wtMi4yMDQgNi4yNDRhMS41IDEuNSAwIDAgMS0xLjQxIDEuMDdoLS4zNjdsLS4xMTYtLjAzN2wtMy45MjgtMi4wODVsLS4xNzItLjA5MmwtMS45MiAzLjYxM2wtMS4wMzggMS45NTZhLjUuNSAwIDAgMS0uNDUuMjc3Ii8+PC9zdmc+)
![Maven](https://img.shields.io/badge/Maven-3.9-red?style=for-the-badge&logo=apache-maven)
![License](https://img.shields.io/badge/License-MIT-green?style=for-the-badge)

Um sistema de desktop robusto para gerenciamento de consórcios automotivos, desenvolvido em Java com interface gráfica em JavaFX.

## 📋 Índice

- [Sobre o Projeto](#-sobre-o-projeto)
- [✨ Funcionalidades Principais](#-funcionalidades-principais)
- [🛠️ Tecnologias Utilizadas](#️-tecnologias-utilizadas)
- [🏛️ Arquitetura do Projeto](#️-arquitetura-do-projeto)
- [🚀 Como Executar](#-como-executar)
- [👥 Equipe](#-equipe)
- [📜 Licença](#-licença)

## 📖 Sobre o Projeto

O **ConsorX** é um sistema de software criado para simplificar a administração de consórcios de automóveis. O projeto oferece uma plataforma intuitiva para a aquisição de bens por meio de uma poupança coletiva, atendendo tanto aos clientes (consorciados) quanto aos administradores.

-   **Para Clientes:** Permite a adesão a grupos de consórcio, visualização de parcelas, acompanhamento do saldo devedor e verificação das contemplações mensais.
-   **Para Administradores:** Oferece ferramentas para gerenciar grupos, visualizar a lista de participantes, emitir relatórios, simular contemplações e muito mais.

Este projeto foi desenvolvido como parte da disciplina de Introdução à Programação II, com foco na aplicação de conceitos de Orientação a Objetos, persistência de dados e desenvolvimento de interfaces gráficas.

## ✨ Funcionalidades Principais

O sistema foi projetado para atender aos seguintes requisitos:

-   **Gerenciamento de Clientes:** Cadastro, leitura, atualização e exclusão (CRUD) de clientes, incluindo nome, CPF, telefone e e-mail.
-   **Gerenciamento de Grupos:** Criação e administração de grupos de consórcio, com detalhes sobre o bem, valor total, número de participantes e taxa de administração.
-   **Gestão de Contratos:** Vinculação de clientes a grupos, com controle sobre parcelas pagas, saldo devedor e status (ativo, contemplado, encerrado).
-   **Pagamentos e Parcelas:** Cálculo automático do valor das parcelas e registro de pagamentos, com atualização do saldo devedor.
-   **Sorteios e Contemplações:** Simulação de sorteios mensais para contemplar aleatoriamente um participante ativo do grupo.
-   **Relatórios e Finanças:** Geração de relatórios de saldo devedor, estatísticas financeiras (valor arrecadado e pendente) e histórico de contemplações.
-   **Validação e Regras de Negócio:** Validação de dados de entrada (CPF, e-mail) e bloqueio de operações em grupos encerrados.

## 🛠️ Tecnologias Utilizadas

-   **Linguagem:** Java 21
-   **Interface Gráfica (GUI):** JavaFX e FXML
-   **Gerenciador de Dependências:** Apache Maven
-   **Persistência de Dados:** Serialização de objetos Java (`.dat` files)

## 🏛️ Arquitetura do Projeto

O sistema foi estruturado utilizando o padrão de arquitetura **Model-View-Controller (MVC)** para garantir a separação entre a lógica de negócio, os dados e a interface do usuário.

-   **Model:** Representa os dados e a lógica de negócio da aplicação. Inclui classes como `Cliente`, `GrupoConsorcio`, `Contrato` e `Boleto`.
-   **View:** É a camada de apresentação, responsável pela interface gráfica. Foi construída com FXML (`.fxml` files) para definir a estrutura das telas de forma declarativa.
-   **Controller:** Atua como intermediário entre o Model e a View. As classes de controller (ex: `TelaLoginClienteController`) manipulam os eventos da interface e acionam a lógica de negócio.

Adicionalmente, o projeto utiliza o padrão **Facade** (`ConsorcioFachada`) para prover uma interface unificada e simplificada para as operações do sistema, e o padrão **Repository** para abstrair a camada de acesso aos dados.

## 🚀 Como Executar

Para executar o projeto localmente, siga os passos abaixo.

### Pré-requisitos

-   JDK 21 ou superior.
-   Apache Maven.
-   Git.

### Passos

1.  **Clone o repositório:**
    ```bash
    git clone [https://github.com/emanuelrodrigues2005/consorcio_project_ip.git](https://github.com/emanuelrodrigues2005/consorcio_project_ip.git)
    cd consorcio_project_ip
    ```

2.  **Execute a aplicação com Maven:**
    O plugin do JavaFX configurado no `pom.xml` cuidará de compilar e executar o projeto.
    ```bash
    mvn clean javafx:run
    ```

## 👥 Equipe

-   Emanuel José Tenório Rodrigues
-   Gustavo Henrique Evangelista da Silva
-   João Ricardo Ferreira de Andrade Barbosa
-   Josué Costa das Silva
-   Lucas Cavalcanti Ramos de Albuquerque

## 📜 Licença

Este projeto é distribuído sob a licença MIT. Veja o arquivo [LICENSE](LICENSE) para mais detalhes.
