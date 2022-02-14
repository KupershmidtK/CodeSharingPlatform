<!DOCTYPE HTML>
<html>
    <head>
        <meta charset="utf-8">
        <title>Code</title>
        <style>
            html {
                font-size: 16px;
            }
            #code_snippet {
                max-width : 75vw;
                max-height : 50vh;
                resize : both;
                overflow : auto;
                border: 1px solid gray;
                background-color : whitesmoke;
            }
            #load_date {
                color : darkgreen
            }
        </style>
        <link rel="stylesheet"
               href="//cdn.jsdelivr.net/gh/highlightjs/cdn-release@11.4.0/build/styles/default.min.css">
        <script src="//cdn.jsdelivr.net/gh/highlightjs/cdn-release@11.4.0/build/highlight.min.js"></script>
        <script>hljs.initHighlightingOnLoad();</script>
    </head>
    <body>
        <span id="load_date">${code.date!error}</span><br>
        <#if code.views gt 0>
          <span id="views_restriction"><code><b>${code.leftViews!error}</b> more views allowed</span><br>
        </#if>
        <#if code.time gt 0>
          <span id="time_restriction">This code will be available for <b><code>${code.leftTime!error}</b> seconds</span><br>
        </#if>
        <pre id="code_snippet"><code>${code.code!error}</code></pre>
    </body>
</html>