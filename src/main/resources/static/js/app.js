var ws;
var userid = 0;
var sessionid;
var message;
var time;
var clientHttpSession;
var username = '';
var isOpen = false;
var chatId = 0;
var Message;
var operatorAvatarImage = 'http://www.konex.com.ua/konex24/avatars/operator.jpg';
var userAvatarImage = 'http://www.konex.com.ua/konex24/avatars/0.jpg';
var baseUrl = '/';

/////////////////////////////////////////////////////////////////////
function updateTarget() {
    return (window.location.protocol == 'http:' ? 'ws://' : 'wss://') + window.location.host + baseUrl + "chatbot";
}

function connect() {
    ws = new WebSocket(updateTarget());

    ws.onopen = function (event) {
        ws.send('Поехали!');
    };

    ws.onclose = function () {
        connect()
    };

    ws.onmessage = function (event) {
        onMessage(JSON.parse(event.data));
    };

    ws.onerror = function (err) {
        ws.close();
    };
}

/////////////////////////////////////////////////////////////////////
Message = function (arg) {
    this.text = arg.text, this.message_side = arg.message_side, this.title = arg.title;
    this.draw = function (_this) {
        return function () {
            var $message;
            $message = $($('.message_template').clone().html());
            $message.addClass(_this.message_side).find('.text').html(_this.text);
            $message.find('.title').html(_this.title);
            $message.find('.avatar-image').attr("src", arg.message_side == 'right' ? operatorAvatarImage : userAvatarImage);
            $('.messages').append($message);
            // return setTimeout(function () {
            return $message.addClass('appeared');
            // }, 0);
        };
    }(this);
    return this;
};

function addMessage(text) {
    var $messages, message;
    if (text.message.trim() === '') {
        return;
    }
    $messages = $('.messages');
    message = new Message({
        text: text.message,
        message_side: text.isUser ? 'left' : 'right',
        title: text.isUser ? getCurrentTime() + ' • Ви' : 'Фармацевт, Марина • ' + getCurrentTime()
    });
    message.draw();

    return $messages.animate({scrollTop: $messages.prop('scrollHeight')}, 300);
}

function addOptions(options) {
    $messages = $('.messages');
    selectOptions = '<li style="text-align: center">';

    $.each(options, function (index, obj) {
        selectOptions = selectOptions + '<button onclick="ws.send('+ "'" + obj + "'" + ')" type="button" class="option">' + obj + '</button>';
    });

    selectOptions = selectOptions + '</li>';

    $messages.append(selectOptions);

    $messages.animate({scrollTop: $messages.prop('scrollHeight')}, 0);
}

function getCurrentTime() {
    return new Date().toLocaleTimeString('en-US', {hour: '2-digit', minute: '2-digit'});     //12
}

function convertTo12(time) {
    time = time.toString().match(/^([01]\d|2[0-3])(:)([0-5]\d)(:[0-5]\d)?$/) || [time];

    if (time.length > 1) {
        time = time.slice(1);
        time[5] = +time[0] < 12 ? ' AM' : ' PM';
        time[0] = +time[0] % 12 || 12;
    }
    return time.join('');
}

/////////////////////////////////////////////////////////////////////
function onMessage(data) {
    returnMessage = data;

    var text = {
        message: data.message,
        isUser: data.isUser
    };

    addMessage(text);

    if (data.isSelect) {
        addOptions(data.options);
    }
}

/////////////////////////////////////////////////////////////////////
function sendMessage() {
    if ($('.message_input').val().trim() != "") {
        ws.send($('.message_input').val());
        $('.message_input').val('');
    }
}

function closeChat() {
    isOpen = false;
    if (ws != undefined && ws.readyState === ws.OPEN && clientHttpSession != undefined) {
        ws.send(JSON.stringify({
            clientHttpSession: clientHttpSession,
            id: userid,
            close: true
        }));
    }
}

/////////////////////////////////////////////////////////////////////
$(document).on("keyup", ".message_input", function (e) {
    if (e.keyCode == 13) {
        sendMessage();
    }
});

$(document).on("click", ".send_message", function (e) {
    sendMessage();
});

document.addEventListener("DOMContentLoaded", ready);

function ready() {
    $(document).ready(function () {
        connect();

        var originalSize = $(window).width() + $(window).height();

        $(".messages").css("height", $(window).height() - $(".bottom_wrapper").height() - 40);

        $(window).resize(function () {
            if ($(window).width() + $(window).height() != originalSize) {
                $(".messages").css("height", $(window).height() - $(".bottom_wrapper").height() - 40);
            } else {
                $(".messages").css("height", $(window).height() - $(".bottom_wrapper").height() - 40);
            }

            $('.messages').animate({scrollTop: $('.messages').prop('scrollHeight')}, 300);
        });
    });
}