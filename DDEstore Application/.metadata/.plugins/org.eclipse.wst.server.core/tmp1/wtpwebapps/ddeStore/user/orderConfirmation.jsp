<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Confirmare Comanda</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        .overlay {
            position: fixed;
            top: 0;
            left: 0;
            width: 100%;
            height: 100%;
            background: rgba(0, 0, 0, 0.5);
            z-index: 999;
        }

        .popup {
            position: fixed;
            top: 50%;
            left: 50%;
            transform: translate(-50%, -50%);
            background-color: #eaf7ea; /* Fundal verzui deschis */
            width: 50%; /* Card de lățimea 50% din ecran */
            padding: 30px; /* Spațiu interior mai generos */
            box-shadow: 0 6px 12px rgba(0, 0, 0, 0.3); /* Umbra mai proeminentă */
            text-align: center;
            border-radius: 15px; /* Colțuri mai rotunjite */
            z-index: 1000;
            border: 2px solid #8fcf8f; /* Margine verde pastel */
        }

        .popup h3 {
            margin-top: 0;
            color: #4a8f4a; /* Text verde mai închis */
            font-size: 1.5rem;
        }

        .popup p {
            margin-top: 10px;
            color: #3d7f3d; /* Text verde complementar */
            font-size: 1rem;
        }
    </style>
</head>
<body>
    <div class="overlay"></div>
    <div class="popup">
        <h3>Comanda a fost plasata cu succes!</h3>
        <p>Veti fi redirectionat catre pagina de produse in cateva secunde...</p>
    </div>

    <script>
        setTimeout(function () {
            window.location.href = "/ddeStore/ProductFilterServlet";
        }, 3000);
    </script>
</body>
</html>
