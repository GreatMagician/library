jQuery("document").ready(function() {
    getAuthors();
    $('#nameBook').change(function () {
        if (selectAuthorId === undefined){
            $('#saveBook').attr('disabled', 'disabled');
            $('#authorSelect').css('visibility', 'visible');
        }
    });

});

// id выбранного автора
var selectAuthorId;

/**
 *  Загрузить всех авторов
 */
function getAuthors() {
    $.ajax({
        url: '/getAuthors',
        type: 'POST',
        dataType: 'JSON'
    }).done(function (json) {
        for (var i=0; i<json.length; i++){
            $('#authorList').append('<a class="list-group-item" id="author-' + json[i].id +
                '" href="#" onclick="getBooks(' + json[i].id + ')"> ' + json[i].name + ' ' + json[i].family + '</a>')
        }
    }).fail(function (xhr, status, errorThrown) {
        console.log(xhr, status, errorThrown);
    });
}

/**
 * получить книги автора
 * @param id автора
 */
function getBooks(id) {
    selectAuthorId = id;
    $('#authorList').children().removeClass('active');
    $('#author-' + id).addClass('active');
    $('#saveBook').removeAttr('disabled');
    $('#authorSelect').css('visibility', 'hidden');
    $('#param').val(id);
    $('#bookList').html('');
    $.ajax({
        url: '/getBooks',
        type: 'POST',
        dataType: 'JSON',
        data: {
            id: id
        }
    }).done(function (json) {
        for (var i=0; i<json.length; i++){
            $('#bookList').append('<a class="list-group-item" id="book-' + json[i].id +
                '" href="#" onclick="seeBook(' + json[i].id + ')"> ' + json[i].name +'</a>')
        }
    }).fail(function (xhr, status, errorThrown) {
        console.log(xhr, status, errorThrown);
    });

}
var defaultPhoto = 'resources/img/no_cover.png';
var book;
/**
 *  Посмотреть информацию о книге
 * @param id книги
 */
function seeBook(id) {
    clearBook();
    $.ajax({
        url: '/seeBook',
        type: 'POST',
        dataType: 'JSON',
        data: {
            id: id
        }
    }).done(function (json) {
        book = json;
        if (json.pathCover === null) json.pathCover = defaultPhoto;
        $('#bookName').append('<h3 class="centered">'+ json.name + '</h3>');
        $('#cover').append('<img src="'+ json.pathCover +'" class="img-rounded"  height="236">');
        $('#description').append(' <textarea class="form-control" rows="10" id="comment">'+ json.description + '</textarea>' +
                '<br/>'+
                '<input type="button" class="btn btn-info" value="Сохранить" onclick="editBook()">' +
                '<input type="button" class="btn btn-danger" value="Удалить" onclick="deleteBook('+ json.id +')">'+
                    '<form id="upload-file-form">' +
                        '<label for="upload-file-input">Добавить обложку:</label>' +
                        '<input id="upload-file-input" type="file" name="uploadfile" accept="*" />'+
                        '<input type="hidden" id="upload-file-book-id" value="'+ json.id +'" />'+
                    '</form>');
        $("#upload-file-input").on("change", uploadFile);
    }).fail(function (xhr, status, errorThrown) {
        console.log(xhr, status, errorThrown);
    });
}

function editBook() {
    book.description = $('#comment').val();
    $.ajax({
        type: 'PUT',
        url:  '/editBook',
        contentType: 'application/json; charset=utf-8',
        data: JSON.stringify(book),
        dataType: 'json',
        success: function(result) {
            if(result){
                alert('Изменения сохранены');
            }
        },
        error: function(jqXHR, textStatus, errorThrown) {
            console.log("Ошибка - " + jqXHR.status + ' ' + jqXHR.responseText);
        }
    });
}

function deleteBook(id) {
    $.ajax({
        url: '/deleteBook',
        type: 'POST',
        dataType: 'JSON',
        data: {
            id: id
        }
    }).done(function (json) {
        clearBook();
        $('#book-' + id).remove(); //удаляем элемент
    }).fail(function (jqXHR, textStatus, errorThrown) {
        console.log("Ошибка - " + jqXHR.status + ' ' + jqXHR.responseText);
    });

}

// очистить от книги
function clearBook() {
    $('#cover').html('');
    $('#description').html('');
    $('#bookName').html('');
}

// загрузка файла
function uploadFile() {
    $.ajax({
        url: "/uploadFile",
        type: "POST",
        data: new FormData($("#upload-file-form")[0]),
        enctype: 'multipart/form-data',
        processData: false,
        contentType: false,
        cache: false,
        success: function (path) {
            book.pathCover = path;
            editBook();
            $('#cover').html('');
            $('#cover').append('<img src="'+ path +'" class="img-rounded"  height="236">');
        },
        error: function (jqXHR, textStatus, errorThrown) {
            console.log("Ошибка - " + jqXHR.status + ' ' + jqXHR.responseText);
        }
    });
}