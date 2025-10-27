# Arquitetura MVC da Aplicação

## Visão Geral do Padrão MVC

O padrão MVC (Model-View-Controller) é uma arquitetura de software que separa a aplicação em três componentes principais:

- **Model (Modelo):** Gerencia os dados e a lógica de negócio
- **View (Visão):** Apresenta os dados ao usuário (interface)
- **Controller (Controlador):** Intermedia as requisições entre Model e View

## Implementação MVC nesta Aplicação

```
┌─────────────────────────────────────────────────┐
│              Usuário (Browser)                  │
└────────────┬────────────────────────┬───────────┘
             │                        │
             │ 1. HTTP Request        │ 4. HTTP Response
             │    (POST /translate)   │    (result.jsp)
             ▼                        ▲
┌────────────────────────────────────────────────┐
│           CONTROLLER LAYER                     │
│                                                │
│  ┌──────────────────────────────────────┐     │
│  │   TranslateServlet.java              │     │
│  │   - Recebe requisição HTTP           │     │
│  │   - Extrai parâmetro "word"          │     │
│  │   - Chama Model para traduzir    ────┼─────┼──┐
│  │   - Prepara dados para View          │     │  │
│  │   - Encaminha para JSP               │     │  │
│  └──────────────────────────────────────┘     │  │
└────────────────────────────────────────────────┘  │
                                                    │
                                                    │ 2. translate()
                                                    │
                                                    ▼
┌────────────────────────────────────────────────┐
│              MODEL LAYER                       │
│                                                │
│  ┌──────────────────────────────────────┐     │
│  │   Dictionary.java                    │     │
│  │   - Carrega dictionary.properties    │     │
│  │   - Armazena traduções               │     │
│  │   - Método translate(word)           │     │
│  │   - Retorna tradução ou palavra      │     │
│  └──────────────────────────────────────┘     │
│                ▲                               │
│                │ 3. Retorna tradução           │
└────────────────┼───────────────────────────────┘
                 │
                 │
┌────────────────┴───────────────────────────────┐
│          DATA LAYER                            │
│                                                │
│  dictionary.properties                         │
│  - hello=olá                                   │
│  - computer=computador                         │
│  - ...                                         │
└────────────────────────────────────────────────┘


            ┌──────────────────────┐
            │    VIEW LAYER        │
            │                      │
            │  index.jsp           │
            │  - Formulário        │
            │  - Input field       │
            │  - Botão traduzir    │
            │                      │
            │  result.jsp          │
            │  - Palavra original  │
            │  - Tradução          │
            │  - Botão voltar      │
            └──────────────────────┘
```

## Detalhamento de Cada Camada

### 1. MODEL (Modelo)

**Arquivo:** `src/main/java/br/ita/mvc/model/Dictionary.java`

**Responsabilidades:**
- Gerenciar o dicionário de traduções
- Carregar dados do arquivo `dictionary.properties`
- Prover lógica de tradução
- Tratar casos de palavras não encontradas

**Código principal:**
```java
public class Dictionary {
    private Properties translations;
    
    // Construtor carrega o dicionário
    public Dictionary() {
        translations = new Properties();
        loadDictionary();
    }
    
    // Carrega traduções do arquivo
    private void loadDictionary() {
        // Lê dictionary.properties do classpath
    }
    
    // Lógica de negócio: traduzir palavra
    public String translate(String word) {
        // Normaliza entrada (lowercase)
        // Busca tradução
        // Retorna tradução ou palavra original
    }
}
```

**Características:**
- ✅ Independente da interface (não conhece JSP/Servlet)
- ✅ Pode ser testado isoladamente
- ✅ Reutilizável em outros contextos
- ✅ Encapsula a lógica de dados

---

### 2. VIEW (Visão)

**Arquivos:**
- `src/main/webapp/index.jsp` - Formulário de entrada
- `src/main/webapp/WEB-INF/result.jsp` - Exibição de resultados

#### index.jsp (Entrada)

**Responsabilidades:**
- Apresentar formulário para o usuário
- Coletar entrada (palavra em inglês)
- Submeter para o Controller

**Código principal:**
```jsp
<form action="translate" method="post">
    <input type="text" id="word" name="word" required>
    <button type="submit">Traduzir</button>
</form>
```

**Características:**
- ✅ Apenas apresentação (HTML/CSS)
- ✅ Não contém lógica de negócio
- ✅ Submete dados via POST para `/translate`

#### result.jsp (Saída)

**Responsabilidades:**
- Exibir palavra original
- Exibir tradução
- Fornecer navegação de volta

**Código principal:**
```jsp
<div id="originalWord"><%= request.getAttribute("originalWord") %></div>
<div id="translation"><%= request.getAttribute("translation") %></div>
<a href="index.jsp">Nova Tradução</a>
```

**Características:**
- ✅ Apenas apresentação
- ✅ Recebe dados via request attributes
- ✅ Não processa dados, apenas exibe

---

### 3. CONTROLLER (Controlador)

**Arquivo:** `src/main/java/br/ita/mvc/controller/TranslateServlet.java`

**Responsabilidades:**
- Receber requisições HTTP
- Extrair parâmetros da requisição
- Invocar o Model para processar dados
- Preparar dados para a View
- Encaminhar para a View apropriada

**Código principal:**
```java
@WebServlet("/translate")
public class TranslateServlet extends HttpServlet {
    private Dictionary dictionary;
    
    @Override
    public void init() {
        // Inicializa o Model
        dictionary = new Dictionary();
    }
    
    @Override
    protected void doPost(HttpServletRequest request, 
                         HttpServletResponse response) {
        // 1. Receber dados da View
        String word = request.getParameter("word");
        
        // 2. Processar usando Model
        String translation = dictionary.translate(word);
        
        // 3. Preparar dados para View
        request.setAttribute("originalWord", word);
        request.setAttribute("translation", translation);
        
        // 4. Encaminhar para View
        request.getRequestDispatcher("/WEB-INF/result.jsp")
               .forward(request, response);
    }
}
```

**Características:**
- ✅ Faz a "ponte" entre View e Model
- ✅ Não contém lógica de negócio
- ✅ Não gera HTML diretamente
- ✅ Coordena o fluxo da aplicação

---

## Fluxo de Dados Completo

### Cenário: Usuário traduz a palavra "hello"

1. **View → Controller**
   - Usuário preenche formulário em `index.jsp` com "hello"
   - Clica em "Traduzir"
   - Browser envia POST para `/translate`
   - Parâmetro: `word=hello`

2. **Controller → Model**
   - `TranslateServlet.doPost()` recebe requisição
   - Extrai parâmetro: `word = "hello"`
   - Chama: `dictionary.translate("hello")`

3. **Model → Processamento**
   - `Dictionary.translate()` recebe "hello"
   - Normaliza para lowercase
   - Busca em `translations` (Properties)
   - Encontra: `hello=olá`
   - Retorna: `"olá"`

4. **Controller → View**
   - Recebe `"olá"` do Model
   - Armazena em request attributes:
     - `originalWord = "hello"`
     - `translation = "olá"`
   - Forward para `result.jsp`

5. **View → Usuário**
   - `result.jsp` recupera attributes
   - Renderiza HTML com valores
   - Browser exibe resultado ao usuário

---

## Vantagens da Arquitetura MVC

### 1. Separação de Responsabilidades
Cada componente tem uma função clara e específica.

### 2. Manutenibilidade
Mudanças em uma camada não afetam as outras:
- Mudar banco de dados → apenas Model
- Mudar layout → apenas View
- Mudar fluxo → apenas Controller

### 3. Testabilidade
Cada camada pode ser testada independentemente:
- Model: testes unitários da lógica
- Controller: testes de integração
- View: testes funcionais (Selenium)

### 4. Reutilização
O Model pode ser usado em outros contextos:
- API REST
- Aplicação desktop
- Linha de comando

### 5. Trabalho em Equipe
Diferentes desenvolvedores podem trabalhar em camadas diferentes simultaneamente.

---

## Configuração Adicional

### web.xml

Configura o mapeamento do Servlet e outras configurações:

```xml
<web-app>
    <welcome-file-list>
        <welcome-file>index.jsp</welcome-file>
    </welcome-file-list>
    
    <!-- Filtro de encoding UTF-8 -->
    <filter>
        <filter-name>encodingFilter</filter-name>
        <filter-class>SetCharacterEncodingFilter</filter-class>
        <init-param>
            <param-name>encoding</param-name>
            <param-value>UTF-8</param-value>
        </init-param>
    </filter>
</web-app>
```

**Nota:** O servlet é mapeado via anotação `@WebServlet("/translate")`, não requer configuração adicional no web.xml.

---

## Boas Práticas Aplicadas

1. ✅ **Separation of Concerns:** Cada classe tem uma responsabilidade única
2. ✅ **DRY (Don't Repeat Yourself):** Lógica centralizada no Model
3. ✅ **Single Responsibility:** Uma classe, uma função
4. ✅ **Encapsulamento:** Detalhes de implementação escondidos
5. ✅ **Configuração:** Properties file para dados configuráveis
6. ✅ **Tratamento de encoding:** UTF-8 para caracteres especiais (português)

---

## Possíveis Extensões (Não Implementadas)

Esta seção mostra como o MVC facilita extensões:

### Adicionar persistência em banco de dados
**Mudança:** Apenas Model
```java
// Trocar Properties por DAO/Repository
public class DictionaryDAO {
    public String findTranslation(String word) {
        // SELECT translation FROM dictionary WHERE word = ?
    }
}
```

### Adicionar API REST
**Mudança:** Novo Controller
```java
@RestController
public class TranslateRestController {
    @Autowired
    private Dictionary dictionary; // Mesmo Model!
    
    @PostMapping("/api/translate")
    public String translate(@RequestParam String word) {
        return dictionary.translate(word);
    }
}
```

### Adicionar cache
**Mudança:** Apenas Model
```java
private Map<String, String> cache = new HashMap<>();

public String translate(String word) {
    if (cache.containsKey(word)) return cache.get(word);
    String result = doTranslate(word);
    cache.put(word, result);
    return result;
}
```

---

## Conclusão

A arquitetura MVC implementada nesta aplicação demonstra:
- Separação clara de responsabilidades
- Código organizado e manutenível
- Facilidade de teste e extensão
- Aplicação correta dos princípios de design de software

Esta estrutura é adequada para o exercício proposto e serve como base sólida para aplicações web mais complexas.
