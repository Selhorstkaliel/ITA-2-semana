# Instruções de Implantação e Execução

## Pré-requisitos

1. **Java Development Kit (JDK) 8 ou superior**
   - Verificar instalação: `java -version`
   - Download: https://www.oracle.com/java/technologies/downloads/

2. **Apache Maven**
   - Verificar instalação: `mvn -version`
   - Download: https://maven.apache.org/download.cgi

3. **Apache Tomcat 9 ou superior** (para execução da aplicação)
   - Download: https://tomcat.apache.org/download-90.cgi

4. **ChromeDriver** (para executar os testes Selenium)
   - Download: https://chromedriver.chromium.org/downloads
   - O ChromeDriver deve estar no PATH do sistema

## Compilação

### Opção 1: Compilar apenas
```bash
mvn clean compile
```

### Opção 2: Gerar o arquivo WAR (recomendado)
```bash
mvn clean package
```

Este comando irá:
- Compilar todo o código Java
- Copiar os recursos (dictionary.properties)
- Criar o arquivo WAR em `target/translator-webapp.war`

## Execução da Aplicação

### Opção 1: Deploy no Apache Tomcat

1. Certifique-se de que o Tomcat está parado
2. Copie o arquivo `target/translator-webapp.war` para a pasta `webapps` do Tomcat:
   ```bash
   cp target/translator-webapp.war $TOMCAT_HOME/webapps/
   ```
3. Inicie o Tomcat:
   ```bash
   # No Windows
   %TOMCAT_HOME%\bin\startup.bat
   
   # No Linux/Mac
   $TOMCAT_HOME/bin/startup.sh
   ```
4. Acesse a aplicação em: http://localhost:8080/translator-webapp

### Opção 2: Usando Maven Tomcat Plugin (desenvolvimento)

Adicione o seguinte plugin ao `pom.xml` (já incluído se necessário):

```bash
mvn tomcat7:run
```

## Executar os Testes Selenium

### Pré-requisitos para os Testes

1. Certifique-se de que a aplicação está rodando (ver seção anterior)
2. Tenha o ChromeDriver instalado e no PATH
3. O ChromeDriver deve ser compatível com a versão do Chrome instalada

### Executar todos os testes

```bash
mvn test
```

### Executar um teste específico

```bash
# Teste 1: palavra "hello"
mvn test -Dtest=TranslatorSeleniumTest#testTranslateExistingWord_Hello

# Teste 2: palavra "computer"
mvn test -Dtest=TranslatorSeleniumTest#testTranslateExistingWord_Computer

# Teste 3: palavra que não existe no dicionário
mvn test -Dtest=TranslatorSeleniumTest#testTranslateNonExistingWord
```

### Importante para os Testes

- A aplicação **DEVE** estar rodando em http://localhost:8080/translator-webapp antes de executar os testes
- Se estiver usando uma porta ou contexto diferente, modifique a variável `baseUrl` na classe `TranslatorSeleniumTest.java`
- Os testes rodam em modo headless (sem abrir o navegador visualmente)

## Estrutura do Projeto

```
translator-webapp/
├── pom.xml                                    # Configuração Maven
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── br/ita/mvc/
│   │   │       ├── model/
│   │   │       │   └── Dictionary.java        # Model - Gerencia traduções
│   │   │       └── controller/
│   │   │           └── TranslateServlet.java  # Controller - Processa requisições
│   │   ├── resources/
│   │   │   └── dictionary.properties          # Arquivo com 25 traduções
│   │   └── webapp/
│   │       ├── index.jsp                      # View - Formulário de entrada
│   │       └── WEB-INF/
│   │           ├── result.jsp                 # View - Exibe resultado
│   │           └── web.xml                    # Configuração do Servlet
│   └── test/
│       └── java/
│           └── br/ita/mvc/test/
│               └── TranslatorSeleniumTest.java # 3 testes funcionais
└── README.md
```

## Funcionalidades Implementadas

✅ **Camada Model (Modelo)**
- Classe `Dictionary` que carrega e gerencia traduções
- Lê traduções do arquivo `dictionary.properties`
- Retorna a palavra original se não houver tradução

✅ **Camada Controller (Controlador)**
- Servlet `TranslateServlet` mapeado para `/translate`
- Recebe palavra do usuário via POST
- Usa o Model para traduzir
- Encaminha para a View apropriada

✅ **Camada View (Visão)**
- `index.jsp`: Interface amigável para entrada de palavras
- `result.jsp`: Exibe palavra original e tradução
- Design responsivo com CSS

✅ **Testes Selenium**
- 3 testes automatizados conforme especificação
- Testa palavras existentes e não existentes
- Validação completa do fluxo da aplicação

## Palavras Disponíveis no Dicionário

O arquivo `dictionary.properties` contém 25 pares de tradução:
- hello, world, computer, book, cat, dog, house, car, tree, water
- food, sun, moon, star, school, teacher, student, friend, family, love
- time, work, city, country, music

## Solução de Problemas

### Erro: "Unable to find dictionary.properties"
- Certifique-se de que o arquivo está em `src/main/resources/`
- Execute `mvn clean package` novamente

### Testes Selenium falham
- Verifique se a aplicação está rodando
- Verifique se o ChromeDriver está instalado e no PATH
- Verifique se a porta/URL está correta (http://localhost:8080/translator-webapp)

### Erro de compilação
- Verifique se o JDK 8+ está instalado
- Execute `mvn clean` e tente novamente

## Exportar para Eclipse/NetBeans

### Para Eclipse:
```bash
mvn eclipse:eclipse
```
Depois importe como "Existing Maven Project"

### Para NetBeans:
File → Open Project → Selecione a pasta do projeto
(NetBeans reconhece automaticamente projetos Maven)
