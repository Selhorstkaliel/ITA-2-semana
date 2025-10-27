# Tradutor Inglês-Português - Aplicação Web MVC

Este projeto é uma aplicação web desenvolvida utilizando o padrão MVC (Model-View-Controller) com Servlets e JSP, sem frameworks adicionais.

## Descrição

A aplicação permite que o usuário digite uma palavra em inglês e obtenha sua tradução em português. Se a palavra não existir no dicionário, a própria palavra será retornada.

## Arquitetura MVC

### Model (Modelo)
- **Dictionary.java**: Classe responsável por gerenciar o dicionário de traduções. Carrega as traduções de um arquivo properties e fornece o método `translate()` para buscar traduções.

### View (Visão)
- **index.jsp**: Página principal com formulário para entrada da palavra
- **result.jsp**: Página que exibe o resultado da tradução

### Controller (Controlador)
- **TranslateServlet.java**: Servlet que processa as requisições de tradução. Recebe a palavra do usuário, utiliza o modelo Dictionary para traduzir, e encaminha para a view de resultado.

## Estrutura do Projeto

```
translator-webapp/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── br/ita/mvc/
│   │   │       ├── model/
│   │   │       │   └── Dictionary.java
│   │   │       └── controller/
│   │   │           └── TranslateServlet.java
│   │   ├── resources/
│   │   │   └── dictionary.properties (25 pares de palavras)
│   │   └── webapp/
│   │       ├── index.jsp
│   │       └── WEB-INF/
│   │           ├── result.jsp
│   │           └── web.xml
│   └── test/
│       └── java/
│           └── br/ita/mvc/test/
│               └── TranslatorSeleniumTest.java
└── pom.xml
```

## Dicionário

O arquivo `dictionary.properties` contém mais de 20 pares de palavras em inglês e suas traduções em português:
- hello=olá
- computer=computador
- book=livro
- cat=gato
- dog=cachorro
- E mais...

## Testes Funcionais com Selenium

O projeto inclui 3 testes funcionais implementados com Selenium:

1. **testTranslateExistingWord_Hello**: Testa a tradução da palavra "hello" (existe no dicionário)
2. **testTranslateExistingWord_Computer**: Testa a tradução da palavra "computer" (existe no dicionário)
3. **testTranslateNonExistingWord**: Testa a tradução da palavra "programming" (não existe no dicionário)

### Executar os Testes

**Pré-requisitos:**
- Java JDK 8 ou superior
- Maven
- ChromeDriver instalado e configurado no PATH

**Comando:**
```bash
mvn test
```

## Como Compilar e Executar

### Compilar o projeto
```bash
mvn clean package
```

### Executar em um servidor
1. Copie o arquivo `target/translator-webapp.war` para a pasta `webapps` do Tomcat
2. Inicie o Tomcat
3. Acesse: http://localhost:8080/translator-webapp

## Tecnologias Utilizadas

- Java 8
- Servlets 4.0
- JSP 2.3
- Maven
- Selenium 3.141.59
- JUnit 4.13.2

## Autor

Desenvolvido como exercício prático de aplicação web MVC com Servlets e JSP.
