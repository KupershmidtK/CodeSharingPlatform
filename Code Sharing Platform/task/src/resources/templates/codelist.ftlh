<!DOCTYPE HTML>
<html>
    <head>
        <meta charset="utf-8">
        <title>Latest</title>
        <style>
            #code_snippet {
                max-width : 75vw;
                max-height : 50vh;
                resize : both;
                overflow : auto;
                border: 1px solid gray;
                background-color : whitesmoke;
                font-size: 16px; }
            #load_date {
                font-size: 16px;
                color : darkgreen }
        </style>
        <link rel="stylesheet"
              href="//cdn.jsdelivr.net/gh/highlightjs/cdn-release@11.4.0/build/styles/default.min.css">
        <script src="//cdn.jsdelivr.net/gh/highlightjs/cdn-release@11.4.0/build/highlight.min.js"></script>
        <script>hljs.initHighlightingOnLoad();</script>
    </head>
    <body>
    <#list snippets as code>
        <span id="load_date">${code.date!error}</span>
        <pre id="code_snippet"><code>${code.code!error}</code></pre>
    </#list>
    </body>
</html>