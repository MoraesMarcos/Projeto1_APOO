# 🛒 Sistema de Pedidos com Simulação de Notificações

Este projeto é um sistema simples de pedidos para uma loja virtual, desenvolvido em **Java puro**, com foco em **orientação a objetos**, menus via terminal e **simulação de notificações e relatórios**.

---

## 🎯 Objetivo

Criar um sistema que permita:

- Cadastro de clientes e produtos  
- Criação de pedidos com múltiplos itens  
- Cálculo de frete com duas estratégias  
- Envio simulado de notificações (e-mail, SMS, WhatsApp)  
- Geração de relatórios em formato texto simples e JSON simulado  

---

## 🧱 Tecnologias Utilizadas

- Java (JDK padrão)
- Paradigma: Programação Orientada a Objetos
- Interface: Terminal (linha de comando)
- Sem bibliotecas externas ou frameworks

---

## 📁 Estrutura de Pastas

src/

├── app/ # Classe principal (Main.java)

├── model/ # Entidades: Cliente, Produto, Pedido, ItemPedido

├── servico/ # Cálculo de frete e notificações

├── relatorio/ # Geração de relatórios

└── util/ # Entrada de dados com Scanner

---

## ✅ Funcionalidades

### 👤 Cadastro de Cliente

- Nome
- CPF (somente números, 11 dígitos)
- E-mail (validação básica)
- Telefone (DDD + número, 11 dígitos)

### 📦 Cadastro de Produto

- Nome
- Preço
- Peso (em kg)

### 📝 Criação de Pedido

- Escolha do cliente
- Adição de produtos com quantidade
- Escolha da forma de cálculo do frete:
  - Por peso: R$ 5,00/kg
  - Por distância: R$ 0,50/km
- Cálculo automático do total e total com frete

### 📲 Notificações

Simulação de envio de confirmação por:
- E-mail (usa o campo `email`)
- SMS (usa o campo `telefone`)
- WhatsApp (usa o campo `telefone`)

### 🧾 Relatórios

Geração em dois formatos:
- Texto simples (exibido no terminal)
- JSON simulado (montagem manual do JSON)

---

## ▶️ Como Executar

1. Clone o repositório:

   git clone https://github.com/MoraesMarcos/Projeto1_APOO

2. JDK24
   
   24 - stream gatherers
   
4. Compile os arquivos:
   
    src/**/*.java

5. Execute o programa:

    src app.Main

