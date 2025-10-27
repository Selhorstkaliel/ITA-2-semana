<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Resultado da Tradução</title>
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
        .result-box {
            background-color: #f9f9f9;
            padding: 20px;
            border-radius: 4px;
            margin: 20px 0;
            border-left: 4px solid #4CAF50;
        }
        .result-label {
            color: #666;
            font-weight: bold;
            margin-bottom: 5px;
        }
        .result-value {
            font-size: 24px;
            color: #333;
            margin-bottom: 15px;
        }
        .back-button {
            display: inline-block;
            width: 100%;
            padding: 12px;
            background-color: #2196F3;
            color: white;
            text-align: center;
            text-decoration: none;
            border-radius: 4px;
            font-size: 16px;
            font-weight: bold;
            box-sizing: border-box;
        }
        .back-button:hover {
            background-color: #0b7dda;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>Resultado da Tradução</h1>
        <div class="result-box">
            <div class="result-label">Palavra em Inglês:</div>
            <div class="result-value" id="originalWord"><%= request.getAttribute("originalWord") %></div>
            
            <div class="result-label">Tradução em Português:</div>
            <div class="result-value" id="translation"><%= request.getAttribute("translation") %></div>
        </div>
        <a href="<%= request.getContextPath() %>/index.jsp" class="back-button">Nova Tradução</a>
    </div>
</body>
</html>
