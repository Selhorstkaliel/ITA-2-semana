<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Tradutor Inglês-Português</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            max-width: 600px;
            margin: 50px auto;
            padding: 20px;
            background-color: #f5f5f5;
        }
        .container {
            background-color: white;
            padding: 30px;
            border-radius: 8px;
            box-shadow: 0 2px 4px rgba(0,0,0,0.1);
        }
        h1 {
            color: #333;
            text-align: center;
        }
        .form-group {
            margin-bottom: 20px;
        }
        label {
            display: block;
            margin-bottom: 5px;
            color: #555;
            font-weight: bold;
        }
        input[type="text"] {
            width: 100%;
            padding: 10px;
            border: 1px solid #ddd;
            border-radius: 4px;
            box-sizing: border-box;
            font-size: 16px;
        }
        button {
            width: 100%;
            padding: 12px;
            background-color: #4CAF50;
            color: white;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            font-size: 16px;
            font-weight: bold;
        }
        button:hover {
            background-color: #45a049;
        }
        .info {
            margin-top: 20px;
            padding: 10px;
            background-color: #e7f3fe;
            border-left: 4px solid #2196F3;
            font-size: 14px;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>Tradutor Inglês-Português</h1>
        <form action="translate" method="post">
            <div class="form-group">
                <label for="word">Digite uma palavra em inglês:</label>
                <input type="text" id="word" name="word" required 
                       placeholder="Digite uma palavra..." autocomplete="off">
            </div>
            <button type="submit">Traduzir</button>
        </form>
        <div class="info">
            <strong>Dica:</strong> Tente palavras como: hello, computer, book, cat, dog, house, etc.
        </div>
    </div>
</body>
</html>
