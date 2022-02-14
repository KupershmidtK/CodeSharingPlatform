<!DOCTYPE HTML>
<html>
    <head>
        <meta charset="utf-8">
        <title>Create</title>
        <style>
            #code_snippet {
                width : 50vw;
                height : 50vh;
                border: 1px solid gray;
                background-color : whitesmoke;
                font-size: 16px;
            }
            #send_snippet {
                background-color: #4CAF50; /* Green */
                border: double;
                color: white;
                padding: 15px 32px;
                text-align: center;
                text-decoration: none;
                display: inline-block;
                font-size: 16px;
                border-radius: 8px;
            }
        </style>
        <script>
            function send() {
                var object = {
                    "code": document.getElementById("code_snippet").value,
                    "time": document.getElementById("time_restriction").value,
                    "views": document.getElementById("views_restriction").value
                };
                let json = JSON.stringify(object);

                let xhr = new XMLHttpRequest();
                xhr.open("POST", '/api/code/new', false)
                xhr.setRequestHeader('Content-type', 'application/json; charset=utf-8');
                xhr.send(json);

                if (xhr.status == 200) {
                    alert("Success!");
                }
            }
        </script>
    </head>
    <body>
        <textarea id="code_snippet"> ... </textarea><br>
        <p>Time restriction: <input id="time_restriction" type="text"/> </p>
        <p>Views restriction: <input id="views_restriction" type="text"/> </p>
        <button id="send_snippet" type="submit" onclick="send()">Submit</button>
    </body>
</html>