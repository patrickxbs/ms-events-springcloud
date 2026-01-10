Events Microservices 
---
Este projeto consiste na implementação de uma arquitetura de microserviços, voltada para a gestão de eventos, inscrições e pagamentos. O principal objetivo foi aplicar conceitos de Sistemas Distribuídos, com foco em comunicação síncrona e integração entre serviços.

Funcionalidades:
---

Cadastro e consulta de eventos.

Inscrição nos eventos.

Processamento de pagamentos das inscrições.

Serviços implementados:
---
Service Discovery (Eureka Server)

Config Server [[link do repositorio](https://github.com/patrickxbs/configserver-event-repository)]

API Gateway

Circuit Breaker (resiliência e tratamento de falhas)

Microserviço de Evento (gerenciamento de eventos)

Microserviço de Inscrição (Gerenciamento de inscrições nos eventos)

Microserviço de Pagamento (Processamento de transações financeiras)

<img width="1187" height="551" alt="image" src="https://github.com/user-attachments/assets/439c0388-0161-4ae0-99f0-b12703e52942" />

Tecnologias usadas:
---
Spring Boot (JPA, Web)

Spring Cloud Netflix (Config Server, Discovery, Gateway, Circuit Breaker)

Spring Cloud Open Feign

MySQL

MongoDB

Docker e Docker Compose

Como Rodar o Projeto
---

**Pré-requisitos:**

Docker e Docker Compose instalados.

**Passo a Passo:**

Baixe o arquivo `docker-compose.yaml`.

**No terminal, na pasta do arquivo, rode o comando:**

```bash
docker-compose up -d
```

Verificar os serviços:
------
Acesse o Painel do Eureka: http://localhost:8761

API Gateway estará disponível em: http://localhost:8765
