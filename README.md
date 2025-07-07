# 🛒 Sistema de Pedidos com Simulação de Notificações

Este projeto, desenvolvido por Marcos Vinicius e Welligton Vinicius, sob a orientação do Professor Delano Helio, é um sistema de pedidos para uma loja virtual.
            
## *Projeto APOO sem Padrões de projeto*

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


# *🏗️ Projeto APOO com Padrões de Projeto*

Esta seção detalha a versão aprimorada do sistema, que incorpora padrões de projeto para uma arquitetura mais modular, flexível e manutenível.

## 🧩 Visão Geral

Este sistema simula uma loja virtual com funcionalidades de **cadastro de clientes e produtos**, **criação de pedidos**, **escolha de frete e notificação**, além da **geração de relatórios**. O projeto aplica diversos **padrões de projeto** da Programação Orientada a Objetos para promover modularidade, coesão e reutilização de código.

---

## 🗂️ Estrutura de Pacotes

```plaintext
app/           → Classe principal (Main)
model/         → Entidades do sistema (Cliente, Produto, Pedido, etc.)
servico/       → Interfaces e implementações para frete e notificações
relatorio/     → Geração de relatórios (Texto e JSON)
util/          → Utilitários de entrada de dados e persistência

```
## 👥 Classes Principais

### `Cliente`
Representa um cliente da loja.

- **Atributos**: `nome`, `cpf`, `email`, `telefone`
- **Imutável** (atributos `final`)
- **Métodos**: getters

---

### `Produto`
Representa um produto disponível.

- **Atributos**: `nome`, `preco`, `peso`
- **Imutável**
- **Métodos**: getters

---

### `ItemPedido`
Composição entre `Pedido` e `Produto`.

- **Atributos**: `produto`, `quantidade`
- **Métodos**:
  - `getTotal()`
  - `getPesoTotal()`

---

### `Pedido`
Representa um pedido feito por um cliente.

- **Atributos**: `cliente`, lista de `ItemPedido`, `freteEscolhido`, `notificador`
- **Métodos**:
  - `adicionarItem(produto, quantidade)`
  - `calcularFrete()`
  - `notificar()`
  - Getters
- **Contém** classe interna `PedidoBuilder` (**Padrão Builder**)

---

## 🚚 Frete (Strategy + Factory)

### Interface `FreteEscolhido`
```java
double calcularFrete(Pedido pedido);
```

### Implementações:
- `FretePeso`: frete por peso total do pedido  
- `FreteDistancia`: frete por distância informada

### Fábricas:
- `FactoryFretePeso`
- `FactoryFreteDistancia`

---

## 📢 Notificações (Strategy + Factory + Decorator)

### Interface `Notificador`
```java
void notificar(Cliente cliente);
```

### Implementações:
- `NotificacaoEmail`
- `NotificacaoSMS`
- `NotificacaoWhatsApp`

### Fábrica:
- `FactoryNotificador`

### Decorator:
- `NotificadorComLog`: adiciona logs à notificação

---

## 📄 Relatórios (Sugestão: Strategy)

### Classes:
- `RelatorioTexto`: imprime o relatório em texto
- `RelatorioJSON`: imprime o relatório em JSON

---

## 🔧 Utilitários

### `Entrada` (Singleton)
Facilita entrada de dados do usuário via console (`int`, `texto`, `double`).

### `Persistencia`
Salva e carrega listas de objetos em arquivos binários usando serialização.

---

## ▶️ Fluxo Principal (`Main`)

1. Exibe menu com opções: cadastrar cliente, produto ou criar pedido.
2. Ao criar pedido:
   - Seleciona cliente e produto
   - Informa quantidade
   - Escolhe tipo de frete e notificação
   - Gera relatório (opcional)
   - Notifica cliente
   - Adiciona pedido à lista

---

## 🧠 Padrões de Projeto Aplicados

| **Padrão**               | **Local de Aplicação**                             |
|--------------------------|----------------------------------------------------|
| `Builder`                | `PedidoBuilder`                                    |
| `Strategy`               | `FreteEscolhido`, `Notificador`                    |
| `Factory Method`         | `FactoryNotificador`, `FactoryFrete*`              |
| `Decorator`              | `NotificadorComLog`                                |
| `Singleton`              | `Entrada`                                          |
