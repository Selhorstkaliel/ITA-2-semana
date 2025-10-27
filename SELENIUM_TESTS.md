# Documentação dos Testes Selenium

## Visão Geral

Este documento descreve os 3 testes funcionais implementados usando Selenium WebDriver para validar a aplicação web de tradução.

## Classe de Teste: TranslatorSeleniumTest

Localização: `src/test/java/br/ita/mvc/test/TranslatorSeleniumTest.java`

### Configuração dos Testes

#### Método `setUp()` - Executado antes de cada teste
```java
@Before
public void setUp()
```

Responsabilidades:
- Configura o ChromeDriver em modo headless (não abre janela do navegador)
- Adiciona opções para compatibilidade com ambientes CI/CD
- Inicializa o WebDriver

Opções configuradas:
- `--headless`: Executa sem interface gráfica
- `--no-sandbox`: Desabilita sandbox (necessário em alguns ambientes)
- `--disable-dev-shm-usage`: Previne problemas de memória em containers

#### Método `tearDown()` - Executado após cada teste
```java
@After
public void tearDown()
```

Responsabilidades:
- Fecha o navegador
- Libera recursos do WebDriver

### Os 3 Testes Funcionais

## Teste 1: testTranslateExistingWord_Hello

**Objetivo:** Validar a tradução de uma palavra que existe no dicionário.

**Palavra testada:** `hello`

**Tradução esperada:** `olá`

**Fluxo do teste:**
1. Navega para a página inicial (`index.jsp`)
2. Localiza o campo de entrada de texto usando `id="word"`
3. Digita "hello" no campo
4. Localiza e clica no botão "Traduzir"
5. Aguarda o carregamento da página de resultado
6. Verifica se os elementos de resultado existem
7. Valida que a palavra original exibida é "hello"
8. Valida que a tradução exibida é "olá"

**Asserções realizadas:**
```java
assertEquals("Original word should be 'hello'", "hello", originalWordElement.getText());
assertEquals("Translation should be 'olá'", "olá", translationElement.getText());
```

---

## Teste 2: testTranslateExistingWord_Computer

**Objetivo:** Validar a tradução de outra palavra diferente que existe no dicionário.

**Palavra testada:** `computer`

**Tradução esperada:** `computador`

**Fluxo do teste:**
1. Navega para a página inicial (`index.jsp`)
2. Localiza o campo de entrada de texto usando `id="word"`
3. Digita "computer" no campo
4. Localiza e clica no botão "Traduzir"
5. Aguarda o carregamento da página de resultado
6. Verifica se os elementos de resultado existem
7. Valida que a palavra original exibida é "computer"
8. Valida que a tradução exibida é "computador"

**Asserções realizadas:**
```java
assertEquals("Original word should be 'computer'", "computer", originalWordElement.getText());
assertEquals("Translation should be 'computador'", "computador", translationElement.getText());
```

---

## Teste 3: testTranslateNonExistingWord

**Objetivo:** Validar o comportamento quando uma palavra NÃO existe no dicionário.

**Palavra testada:** `programming`

**Comportamento esperado:** A própria palavra deve ser retornada (sem tradução)

**Fluxo do teste:**
1. Navega para a página inicial (`index.jsp`)
2. Localiza o campo de entrada de texto usando `id="word"`
3. Digita "programming" no campo (palavra que não existe no dicionário)
4. Localiza e clica no botão "Traduzir"
5. Aguarda o carregamento da página de resultado
6. Verifica se os elementos de resultado existem
7. Valida que a palavra original exibida é "programming"
8. Valida que a "tradução" também é "programming" (palavra original retornada)

**Asserções realizadas:**
```java
assertEquals("Original word should be 'programming'", "programming", originalWordElement.getText());
assertEquals("Translation should be 'programming' (word not found)", "programming", translationElement.getText());
```

---

## Estratégias de Localização de Elementos

Os testes utilizam diferentes estratégias do Selenium para localizar elementos:

### Por ID
```java
driver.findElement(By.id("word"))          // Campo de entrada
driver.findElement(By.id("originalWord"))  // Palavra original no resultado
driver.findElement(By.id("translation"))   // Tradução no resultado
```

### Por Seletor CSS
```java
driver.findElement(By.cssSelector("button[type='submit']"))  // Botão de submit
```

## Sincronização e Esperas

Os testes utilizam `WebDriverWait` para garantir que os elementos estejam presentes antes de interagir:

```java
WebDriverWait wait = new WebDriverWait(driver, 10);
wait.until(ExpectedConditions.presenceOfElementLocated(By.id("translation")));
```

Isso previne falhas por elementos não carregados.

## Como Executar os Testes

### Pré-requisitos
1. Aplicação rodando em http://localhost:8080/translator-webapp
2. ChromeDriver instalado e no PATH do sistema
3. Maven instalado

### Executar todos os testes
```bash
mvn test
```

### Executar teste específico
```bash
# Teste 1
mvn test -Dtest=TranslatorSeleniumTest#testTranslateExistingWord_Hello

# Teste 2
mvn test -Dtest=TranslatorSeleniumTest#testTranslateExistingWord_Computer

# Teste 3
mvn test -Dtest=TranslatorSeleniumTest#testTranslateNonExistingWord
```

### Saída Esperada
```
[INFO] Tests run: 3, Failures: 0, Errors: 0, Skipped: 0
[INFO] BUILD SUCCESS
```

## Resultados dos Testes

Quando os testes são executados com sucesso, você verá:

```
Running br.ita.mvc.test.TranslatorSeleniumTest
Tests run: 3, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: X.XXX sec

Results :

Tests run: 3, Failures: 0, Errors: 0, Skipped: 0
```

## Configuração Personalizada

Para alterar a URL base da aplicação, modifique a variável na classe de teste:

```java
private String baseUrl = "http://localhost:8080/translator-webapp";
```

Para executar com navegador visível (não headless), remova ou comente a linha:

```java
// options.addArguments("--headless");
```

## Troubleshooting

### Problema: "Session not created: This version of ChromeDriver only supports Chrome version X"
**Solução:** Atualize o ChromeDriver para a versão compatível com seu Chrome

### Problema: "Connection refused"
**Solução:** Certifique-se de que a aplicação está rodando na URL correta

### Problema: "Element not found"
**Solução:** Verifique se os IDs dos elementos no JSP correspondem aos usados nos testes

### Problema: Testes muito lentos
**Solução:** Os testes podem demorar alguns segundos devido aos waits. Isso é normal.

## Cobertura dos Testes

Os 3 testes cobrem:
- ✅ Tradução de palavra existente (2 casos diferentes)
- ✅ Comportamento com palavra não existente
- ✅ Navegação entre páginas
- ✅ Submissão de formulário
- ✅ Exibição de resultados
- ✅ Fluxo completo da aplicação

## Melhores Práticas Aplicadas

1. **Isolamento:** Cada teste é independente
2. **Setup/Teardown:** Recursos são criados e liberados adequadamente
3. **Esperas explícitas:** Uso de WebDriverWait previne race conditions
4. **Asserções claras:** Mensagens descritivas em cada asserção
5. **Nomes descritivos:** Nomes de métodos indicam claramente o que está sendo testado
