<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <title>Продажи</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

        <link href="http://code.jquery.com/ui/1.11.3/themes/redmond/jquery-ui.css" rel="stylesheet" type="text/css" />
        <link href="${pageContext.request.contextPath}/js/jtable-2.4.0/themes/lightcolor/blue/jtable.css" rel="stylesheet" type="text/css" />
        <link href="${pageContext.request.contextPath}/js/jquery-validation-engine-2.6.4/validationEngine.jquery.css" rel="stylesheet" type="text/css" />

        <script type="text/javascript" src="http://code.jquery.com/jquery-2.1.3.min.js"></script>
        <script type="text/javascript" src="http://code.jquery.com/ui/1.11.3/jquery-ui.min.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/jtable-2.4.0/jquery.jtable.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/jtable-2.4.0/localization/jquery.jtable.ru.js"></script>

        <script type="text/javascript" src="${pageContext.request.contextPath}/js/utils-grid.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/sale-grid.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-validation-engine-2.6.4/jquery.validationEngine.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-validation-engine-2.6.4/jquery.validationEngine-ru.js"></script>

        <script type="text/javascript">
            // <![CDATA[
            window.contextPath = "${pageContext.request.contextPath}";
            // ]]>
        </script>
    </head>
    <body>
        <div id="saleGrid"></div>
    </body>
</html>
