# 🧠 Navi - Tradutor Universal com IA

O **Navi** é um aplicativo web desenvolvido em **Spring Boot** com **Vaadin** e **Spring AI**, capaz de **traduzir textos para diferentes estilos de escrita** (como gíria, juridiquês, fala caipira e mais).  
A aplicação utiliza um modelo de linguagem generativa (como GPT) para reescrever o texto mantendo o significado original, mas adaptando o tom conforme o estilo selecionado.

---

## 🚀 Tecnologias Utilizadas

- **Java 17+**
- **Spring Boot 3+**
- **Spring AI** (integração com modelos generativos)
- **Vaadin Flow** (interface web reativa em Java)
- **OpenAI / Azure OpenAI** (modelo de IA)
- **Maven**

---

## 🖥️ Funcionalidades

✅ Tradução de textos conforme o estilo escolhido  
✅ Interface simples e responsiva feita com Vaadin  
✅ Integração com IA generativa via Spring AI  
✅ Validação de campos (texto vazio ou estilo não selecionado)  
✅ Modo leitura para o texto traduzido  

---

## 🎨 Estilos Disponíveis

- Gíria das ruas  
- Criança de 2 anos  
- Juridiquês  
- Caipira  
- Fausto Silva  

---

## 🧠 Como Funciona

1. O usuário digita o **texto original**.  
2. Escolhe um **estilo de escrita** no seletor.  
3. Clica em **"Traduzir"**.  
4. O app chama o método `translate` do `NaviService`, que envia uma requisição para a **IA generativa** com:  
   - 🧩 Uma **mensagem de sistema** definindo o papel da IA (tradutor criativo).  
   - 💬 Uma **mensagem de usuário** contendo o texto e o estilo desejado.  
5. A IA retorna o **texto traduzido** no estilo escolhido, que é exibido na tela.

---

## 🧑‍💻 Exemplo de Uso

| Texto Original            | Estilo          | Resultado                                                                 |
|----------------------------|-----------------|---------------------------------------------------------------------------|
| "Hoje o dia está bonito!" | Gíria das ruas  | "Mano, o dia tá brabo demais!"                                            |
| "Hoje o dia está bonito!" | Juridiquês      | "Constata-se, de forma inequívoca, que as condições climáticas se apresentam favoráveis." |
| "Hoje o dia está bonito!" | Fausto Silva    | "Ô louco, bicho! Que dia bonito, meu!"                                    |
