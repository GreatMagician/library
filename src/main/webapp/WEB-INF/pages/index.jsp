<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <%--Настройки безопастности --%>
    <meta name="_csrf" content="${_csrf.token}"/>
    <meta name="_csrf_header" content="${_csrf.headerName}"/>

    <link rel="stylesheet" type="text/css" href="webjars/bootstrap/3.3.7/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="resources/css/main.css"/>

    <title>Библиотека</title>
</head>

<body>
<h1 class="centered">Электронная библиотека</h1>
<div class="row">
    <div id="column1" class="col-md-3">
        <h2 class="centered">Авторы</h2>
        <div id="authorList" class="list-group">

        </div>
    </div>
    <div id="column2" class="col-md-3">
        <h2 class="centered">Книги автора</h2>
        <div id="bookList" class="list-group">

        </div>
    </div>
    <div id="column3" class="col-md-6">
        <h2 class="centered">О книге</h2>
        <div class="row">
            <div id="bookName"></div>
            <div id="cover" class="col-md-3"></div>
            <div id="description" class="col-md-9"></div>
        </div>
    </div>
</div>
<br/><br/>
<%--добавить автора --%>
<div class="container">
    <div class="row">
        <div class="col-md-6">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h4 class="centered"> Добавить автора </h4>
                </div>
                <div class="panel-body">
                    <form:form method="post" commandName="newAuthor" action="/addAuthor"
                               charset="utf-8" accept-charset="UTF-8">
                        <div class="form-group">
                            <label for="name">Имя <span class="mandatory">*</span></label>
                            <input class="form-control" type="text" id="name" required  name="name"  path="name"/>
                        </div>
                        <div class="form-group">
                            <label for="family">Фамилия <span class="mandatory">*</span></label>
                            <input class="form-control" type="text" id="family" name="family" path="family" required/>
                        </div>
                        <button id="save" type="submit" class="btn btn-success">Сохранить</button>
                    </form:form>
                </div>
            </div>
        </div>
        <div class="col-md-6">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h4 class="centered">  Добавить книгу выделенному автору </h4>
                </div>
                <div class="panel-body">
                    <div id="authorSelect" class="alert alert-danger" role="alert">
                        Автор не выбран.
                    </div>
                    <form:form method="post" commandName="newBook" action="/addBook"
                               charset="utf-8" accept-charset="UTF-8">
                        <div class="form-group">
                            <label for="nameBook">Название <span class="mandatory">*</span></label>
                            <input class="form-control" type="text" id="nameBook" required  name="name"  path="name"/>
                        </div>
                        <div class="form-group">
                            <label for="descriptionForm">Описание <span class="mandatory">*</span></label>
                            <input class="form-control" type="text" id="descriptionForm" name="description" path="description" required/>
                        </div>
                        <input id="param" name="id" hidden>
                        <button id="saveBook" type="submit" class="btn btn-success">Сохранить</button>
                    </form:form>
                </div>
            </div>

        </div>


    </div>
</div>
<script type="text/javascript" src="webjars/jquery/3.1.1-1/jquery.min.js"></script>
<script type="text/javascript" src="webjars/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script type="text/javascript" src="resources/js/main.js"></script>

</body>
</html>
