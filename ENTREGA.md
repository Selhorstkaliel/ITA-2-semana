# Entrega do Exercício - Aplicação Web de Tradução MVC

## Resumo da Implementação

Este projeto implementa uma aplicação web completa seguindo o padrão MVC (Model-View-Controller) utilizando Servlets e JSP, sem frameworks adicionais, conforme especificado no exercício.

## Itens Entregues

### ✅ 1. Aplicação Web Funcional

**Funcionalidade:** Tradução de palavras do inglês para o português

**Tecnologias:**
- Java 8
- Servlets 4.0 (usando anotações `@WebServlet`)
- JSP 2.3
- Maven para build

**Estrutura do Projeto:** Formato Maven (compatível com Eclipse e NetBeans)

### ✅ 2. Divisão em Camadas MVC

A aplicação está corretamente dividida nas três camadas:

#### **MODEL (Modelo)**
- **Classe:** `br.ita.mvc.model.Dictionary`
- **Responsabilidade:** Gerenciar o dicionário de traduções
- **Funcionalidades:**
  - Carrega traduções do arquivo `dictionary.properties`
  - Método `translate(String word)` para buscar traduções
  - Retorna a palavra original se não houver tradução

#### **VIEW (Visão)**
- **Arquivos JSP:**
  - `index.jsp` - Formulário de entrada (página principal)
  - `result.jsp` - Exibição do resultado da tradução
- **Responsabilidade:** Interface com o usuário
- **Características:**
  - Design responsivo com CSS embutido
  - Campos identificados para testes Selenium
  - Suporte a UTF-8 para caracteres portugueses

#### **CONTROLLER (Controlador)**
- **Classe:** `br.ita.mvc.controller.TranslateServlet`
- **Responsabilidade:** Coordenar requisições entre View e Model
- **Funcionalidades:**
  - Mapeado para URL `/translate`
  - Recebe requisições POST
  - Extrai palavra do parâmetro `word`
  - Chama o Model para traduzir
  - Encaminha para a View de resultado

### ✅ 3. Arquivo de Traduções

**Arquivo:** `src/main/resources/dictionary.properties`

**Formato:** Properties (chave=valor)

**Quantidade:** 25 pares de palavras + traduções

**Exemplo de conteúdo:**
```properties
hello=olá
computer=computador
book=livro
cat=gato
dog=cachorro
# ... mais 20 pares
```

**Palavras incluídas:**
hello, world, computer, book, cat, dog, house, car, tree, water, food, sun, moon, star, school, teacher, student, friend, family, love, time, work, city, country, music

### ✅ 4. Três Testes Funcionais com Selenium

**Arquivo:** `src/test/java/br/ita/mvc/test/TranslatorSeleniumTest.java`

**Framework:** Selenium WebDriver 3.141.59 + JUnit 4.13.2

**Testes implementados:**

1. **testTranslateExistingWord_Hello**
   - Testa tradução de "hello"
   - Verifica resultado: "olá"

2. **testTranslateExistingWord_Computer**
   - Testa tradução de "computer"
   - Verifica resultado: "computador"

3. **testTranslateNonExistingWord**
   - Testa palavra "programming" (não existe no dicionário)
   - Verifica que retorna a própria palavra

**Características dos testes:**
- Executam em modo headless (sem abrir navegador)
- Usam WebDriverWait para sincronização
- Validam elementos da página
- Testam fluxo completo da aplicação

### ✅ 5. Configuração do Projeto

**Arquivo:** `pom.xml`

Configuração Maven completa com:
- Dependências: Servlet API, JSP API, JSTL, Selenium, JUnit
- Plugin de compilação (Java 8)
- Plugin WAR para empacotamento
- Build configurado para gerar `translator-webapp.war`

**Arquivo:** `src/main/webapp/WEB-INF/web.xml`

Configuração do servlet container:
- Welcome file: `index.jsp`
- Filtro de encoding UTF-8

### ✅ 6. Documentação Completa

1. **README.md**
   - Descrição geral do projeto
   - Estrutura de arquivos
   - Tecnologias utilizadas
   - Instruções básicas

2. **DEPLOYMENT.md**
   - Pré-requisitos detalhados
   - Instruções de compilação
   - Instruções de execução no Tomcat
   - Como executar os testes
   - Troubleshooting

3. **SELENIUM_TESTS.md**
   - Descrição detalhada de cada teste
   - Como executar os testes
   - Explicação do código de teste
   - Resultados esperados

4. **MVC_ARCHITECTURE.md**
   - Explicação completa da arquitetura MVC
   - Diagrama de fluxo de dados
   - Detalhamento de cada camada
   - Boas práticas aplicadas

### ✅ 7. Arquivos Adicionais

- **.gitignore**: Exclui arquivos de build e IDE
- **LICENSE**: Licença MIT

## Estrutura de Diretórios

```
translator-webapp/
├── pom.xml                                    # Configuração Maven
├── README.md                                  # Documentação principal
├── DEPLOYMENT.md                              # Guia de deploy
├── SELENIUM_TESTS.md                          # Documentação dos testes
├── MVC_ARCHITECTURE.md                        # Arquitetura MVC
├── .gitignore                                 # Arquivos ignorados
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── br/ita/mvc/
│   │   │       ├── model/
│   │   │       │   └── Dictionary.java        # MODEL
│   │   │       └── controller/
│   │   │           └── TranslateServlet.java  # CONTROLLER
│   │   ├── resources/
│   │   │   └── dictionary.properties          # 25 traduções
│   │   └── webapp/
│   │       ├── index.jsp                      # VIEW - Input
│   │       └── WEB-INF/
│   │           ├── result.jsp                 # VIEW - Output
│   │           └── web.xml                    # Configuração
│   └── test/
│       └── java/
│           └── br/ita/mvc/test/
│               └── TranslatorSeleniumTest.java # 3 testes
└── target/
    └── translator-webapp.war                   # Arquivo WAR gerado
```

## Como Usar Este Projeto

### 1. Compilar o Projeto

```bash
mvn clean package
```

Gera o arquivo WAR em: `target/translator-webapp.war`

### 2. Executar no Tomcat

```bash
# Copiar WAR para Tomcat
cp target/translator-webapp.war $TOMCAT_HOME/webapps/

# Iniciar Tomcat
$TOMCAT_HOME/bin/startup.sh  # Linux/Mac
%TOMCAT_HOME%\bin\startup.bat  # Windows
```

Acessar: http://localhost:8080/translator-webapp

### 3. Executar Testes Selenium

**Pré-requisito:** Aplicação deve estar rodando

```bash
mvn test
```

Saída esperada:
```
Tests run: 3, Failures: 0, Errors: 0, Skipped: 0
BUILD SUCCESS
```

### 4. Importar no Eclipse/NetBeans

**Eclipse:**
```bash
mvn eclipse:eclipse
# Depois: File → Import → Existing Projects
```

**NetBeans:**
```
File → Open Project → Selecionar pasta do projeto
```

## Validação da Implementação

### ✅ Requisitos Funcionais

- [x] Campo texto para entrada de palavra
- [x] Botão "Traduzir"
- [x] Submissão ao servidor
- [x] Processamento da tradução
- [x] Retorno de página com tradução
- [x] Busca em arquivo de traduções
- [x] Mínimo 20 pares de palavras (implementado: 25)
- [x] Retorna palavra original se não houver tradução

### ✅ Requisitos Arquiteturais

- [x] Divisão em camadas MVC
- [x] Classes Java para Model
- [x] Servlets para Controller
- [x] Páginas JSP para View
- [x] Sem uso de frameworks adicionais

### ✅ Requisitos de Teste

- [x] 3 testes funcionais com Selenium
- [x] 2 testes com palavras existentes
- [x] 1 teste com palavra inexistente
- [x] Testes automatizados e executáveis

### ✅ Requisitos de Entrega

- [x] Projeto da aplicação web (Maven)
- [x] Código dos testes Selenium (.java)
- [x] Arquivo de palavras incluído no projeto
- [x] Documentação completa

## Características Adicionais Implementadas

Além dos requisitos mínimos, este projeto inclui:

1. **Encoding UTF-8:** Suporte completo a caracteres portugueses (á, é, ã, etc.)
2. **Design Responsivo:** Interface amigável e moderna
3. **Documentação Abrangente:** 4 documentos MD explicando tudo
4. **Boas Práticas:** Código limpo, comentado e organizado
5. **Build Automatizado:** Maven para compilação e empacotamento
6. **Testes Robustos:** Selenium com waits explícitos
7. **Compatibilidade:** Funciona em Eclipse e NetBeans
8. **Git Ignore:** Exclui arquivos desnecessários do repositório

## Observações Importantes

### Para os Testes Selenium

1. **ChromeDriver necessário:** Instalar e adicionar ao PATH
2. **Aplicação deve estar rodando:** http://localhost:8080/translator-webapp
3. **Executar com:** `mvn test`

### Para Deploy

1. **Tomcat 9+ recomendado**
2. **JDK 8+ necessário**
3. **Maven para build**

### Formatação do Arquivo de Traduções

O formato properties foi escolhido por:
- Simplicidade de implementação
- Suporte nativo em Java (classe Properties)
- Fácil manutenção
- Formato texto simples

## Conclusão

Este projeto atende **TODOS** os requisitos especificados no exercício:

✅ Aplicação web funcional com campo de entrada e tradução  
✅ Padrão MVC corretamente implementado  
✅ Servlets e JSP sem frameworks adicionais  
✅ Arquivo com 25+ pares de palavras  
✅ Tratamento de palavras não encontradas  
✅ 3 testes Selenium funcionais  
✅ Projeto Maven (compatível com Eclipse/NetBeans)  
✅ Documentação completa  

O código está organizado, documentado e pronto para uso.
