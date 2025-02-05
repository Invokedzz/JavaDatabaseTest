# PorkyStore

PorkyStore é uma loja online desenvolvida em **Java** utilizando **Java Swing** para a interface gráfica, juntamente com outras tecnologias modernas para melhorar a funcionalidade e a segurança do sistema. A aplicação simula um e-commerce completo com funcionalidades de cadastro, carrinho de compras, pagamento e integração com APIs externas.

## Tecnologias Utilizadas

- **Java**: Linguagem de programação principal para o desenvolvimento da aplicação, garantindo performance e escalabilidade.
- **Java Swing**: Biblioteca para a criação da interface gráfica do usuário (GUI), proporcionando uma experiência rica e intuitiva.
- **JUnit**: Framework para realizar testes unitários e de integração, assegurando a estabilidade e a qualidade do código.
- **HttpClient**: Utilizado para realizar requisições HTTP para consumir APIs externas, como o Stripe para pagamentos e a Here para geolocalização.
- **Stripe API**: Integração com a API Stripe para processamento seguro de pagamentos.
- **Here API**: API utilizada para fornecer funcionalidades como geolocalização e mapeamento, permitindo calcular distâncias e localizar as lojas mais próximas do usuário.
- **BCrypt**: Algoritmo utilizado para a criptografia de senhas, garantindo segurança na autenticação de usuários.
- **Maven**: Ferramenta de automação para gerenciar dependências e o ciclo de vida do build do projeto, facilitando o processo de construção e execução da aplicação.

## Funcionalidades

- **Cadastro e Autenticação de Usuários**: Os usuários podem criar contas, autenticar-se e alterar suas informações pessoais.
- **Carrinho de Compras**: Função que permite adicionar, editar e remover itens no carrinho antes de realizar a compra.
- **Pagamento Seguro**: Processamento de pagamentos com a integração da API Stripe, proporcionando uma experiência de pagamento confiável.
- **Testes Automatizados**: O sistema é testado com JUnit para garantir que todos os módulos funcionem corretamente.
