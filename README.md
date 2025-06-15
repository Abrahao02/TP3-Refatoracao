# TP3-Refatoracao: Refatoração de Sistema de Faturamento e E-mails

Este projeto é uma refatoração completa de um sistema simples de geração de faturas e envio de e-mails para clientes de um e-commerce.

## ✅ Objetivos da Refatoração

- Aplicar boas práticas de orientação a objetos
- Reduzir acoplamento
- Melhorar encapsulamento
- Tornar o código mais coeso, seguro e testável

## 🚀 Como executar

1. Compile o projeto:
```bash
javac App.java
```

2. Execute o projeto:
```bash
java App
```

## 💻 Saída esperada:

```
Cliente: João
1x Notebook - R$3500.0
2x Mouse - R$80.0
Subtotal: R$3660.0
Desconto: R$366.0
Total final: R$3294.0
Enviando e-mail para joao@email.com: Pedido recebido! Obrigado pela compra.
```

## ✔️ Requisitos atendidos:

| Requisito                                                             | Aplicação feita                                                                     |
| --------------------------------------------------------------------- | ----------------------------------------------------------------------------------- |
| 1. **Encapsular registros e coleções**                                | `Order` usa lista privada de `Item`. Nada público exposto.                          |
| 2. **Substituir primitivos por objetos**                              | Criada classe `Item`.                                                               |
| 3. **Ocultar delegados**                                              | `Order.confirmOrder()` chama `EmailService`, ocultando-o do `main`.                 |
| 4. **Redistribuição de funções**                                      | Calcular subtotal e gerar invoice movido para `Order`; impressão para `Invoice`.    |
| 5. **Reposicionar campos em classes apropriadas**                     | Nome e email encapsulados na classe `Client`.                                       |
| 6. **Substituir código embutido por funções auxiliares**              | Métodos como `totalPrice()`, `calculateTotal()`, `print()` extraídos.               |
| 7. **Remover código morto e proteger sistema contra inconsistências** | Adicionados validadores em `Item`; removidas listas paralelas e atributos públicos. |

## 📝 Autor

Eduardo de Sá Abrahão
