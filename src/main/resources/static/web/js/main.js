var webSocketUri = 'ws://localhost:8080/';
var webSocket;

function message() {
    return $('<div/>', { class:  'message' });
}

function openWebSocket() {
    webSocket = new WebSocket(webSocketUri);
    webSocket.onopen = function(e) { onOpen(e) };
    webSocket.onclose = function(e) { onClose(e) };
    webSocket.onmessage = function(e) { onMessage(e) };
    webSocket.onerror = function(e) { onError(e) };
}

function closeWebSocket() {
    if (webSocket != null) {
        webSocket.close();
    }
}

function onOpen(e)
{
    $('#messageRow').prepend(message().text("Connection opened").addClass('info'));
    $('#status').addClass('connected');
}

function onClose(e)
{
    $('#messageRow').prepend(message().text("Connection closed").addClass('info'));
    $('#status').removeClass('connected');
}

function onError(e)
{
    $('#messageRow').prepend(message().text("ERROR: " + e.data).addClass('error'));
}

function onMessage(e)
{
    $('#messageRow').prepend(message().JSONView(e.data, { collapsed: true }));
}


$(function () {

    $('#status').click(function(){
        switch($('#status').hasClass('connected')) {
            case true:
                closeWebSocket();
                break;
            case false:
                openWebSocket();
                break;
        }
    });

    openWebSocket();

});